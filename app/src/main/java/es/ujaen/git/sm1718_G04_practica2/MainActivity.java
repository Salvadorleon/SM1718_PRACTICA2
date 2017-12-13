package es.ujaen.git.sm1718_G04_practica2;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    private String datosvolatiles = "Hola";
    private TextView volatil = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            datosvolatiles = savedInstanceState.getString("volatil", datosvolatiles);
        }

        volatil = (TextView) findViewById(R.id.volatil);
        volatil.setText(datosvolatiles);

        //TODO Cargar EXPIRES de preferencias compartidas
        //Si sigue siendo mayor EXPIRES que la fecha actual hacer conexión
        boolean sesionOK=false;
        if(sesionOK){
            //TODO preparo mi clase ConnectionUserData con lo que tenga guardado en preferencias compartidas
            ConnectionUserData data = new ConnectionUserData("e","e","e",(short)80);
            TareaAutentica tarea = new TareaAutentica(this);
            tarea.execute(data);
        }

    }

    public void onIcon(View vista) {
        datosvolatiles = datosvolatiles.toUpperCase();
        volatil.setText(datosvolatiles);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("volatil", datosvolatiles);
    }


}
