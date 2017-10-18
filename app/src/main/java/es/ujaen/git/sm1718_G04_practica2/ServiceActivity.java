package es.ujaen.git.sm1718_G04_practica2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ServiceActivity extends AppCompatActivity {

    public static final String PARAM_USER = "param_user";
    public static final String PARAM_PASS = "param_pass";
    public static final String PARAM_IP = "param_ip";
    public static final String PARAM_PORT = "param_port";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        String user= getIntent().getStringExtra(PARAM_USER);
        String pass= getIntent().getStringExtra(PARAM_PASS);
        String ip= getIntent().getStringExtra(PARAM_IP);
        short port= getIntent().getShortExtra(PARAM_PORT,(short)6000);


        TextView title = (TextView) findViewById(R.id.textView);
        title.setText("Hola "+user);

    }
}
