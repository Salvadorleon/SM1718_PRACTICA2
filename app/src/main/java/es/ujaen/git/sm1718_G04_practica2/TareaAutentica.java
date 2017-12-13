package es.ujaen.git.sm1718_G04_practica2;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Juan Carlos on 13/12/2017.
 */

public class TareaAutentica extends AsyncTask<ConnectionUserData, Integer, String> {
    private ConnectionUserData data;
    private Context mContext=null;

    public TareaAutentica(Context contex){
        mContext=contex;
    }

    public String doInBackground(ConnectionUserData... param) {
        boolean error = true;

        try {
            data = param[0];
            Socket client = new Socket(InetAddress.getByName("www4.ujaen.es"), 80); //innetaddres con la ip ue me proporciona el usuario
            BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            DataOutputStream output = new DataOutputStream(client.getOutputStream());
            String cadena = "GET /~jccuevas/ssmm/autentica.php?user=" + data.getUser() + "&pass=" + data.getPass() + "\r\n\r\nHTTP/1.1\r\n";
            output.write(cadena.getBytes());
            output.flush();
            String line = null;

            while ((line = input.readLine()) != null) {
                Log.d("Tarea",line);
                if (line.startsWith("SESION-ID=")) {

                    String params[] = line.split("&");
                    if (params.length == 2) {
                        String sesionID[] = params[0].split("=");
                        String expires[] = params[1].split("=");
                        if (sesionID != null && expires != null) {
                            String SesionIDend = sesionID[1];
                            Log.d("SesionID=", SesionIDend);
                            String expiresEnd = expires[1];
                            Log.d("Expiracion=", expiresEnd);
                            //TODO Agregar atributos a la clase ConnectionUserData para sesion id y expires.
                            //y guardarlos
                            return "OK";
                        }
                    }


                }
            }


        } catch (UnknownHostException e) {
            error = true;
            e.printStackTrace();
        } catch (IOException e) {
            error = true;
            e.printStackTrace();
        }


        return "ERROR";
    }

    /**
     * @param result OK si la operación fue correcta y si no otor valor
     */
    public void onPostExecute(String result) {

        if (result.compareToIgnoreCase("OK") == 0) {
            Intent nueva = new Intent(mContext, ServiceActivity.class);
            nueva.putExtra(ServiceActivity.PARAM_USER, data.getUser());
            nueva.putExtra("param_pass", data.getPass());
            nueva.putExtra("param_ip", data.getConnectionIP());
            nueva.putExtra("param_port", data.getConnectionPort());
            //TODO guarar en las preferencias compartidas los datos USER PASS EXPIRES
            mContext.startActivity(nueva);
        } else {
            Toast.makeText(mContext, "Error autenticando a " + data.getUser(), Toast.LENGTH_LONG).show();

        }
    }

}
