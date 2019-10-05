package mx.com.petlove.petlove;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtUser, txtPass;
    private Button btnLogin, btnRegistro;
    private Intent intent;
    private String user, pass, resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegistro = (Button) findViewById(R.id.btnRegistro);

        txtUser = (EditText) findViewById(R.id.txtUser);
        txtPass = (EditText) findViewById(R.id.txtPass);

        btnLogin.setOnClickListener(this);
        btnRegistro.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {



        switch (v.getId()){
            case R.id.btnLogin:
                Toast.makeText(getApplicationContext(), "Espera....", Toast.LENGTH_SHORT).show();
                Thread tr = new Thread(){
                    @Override
                    public void run() {
                        user = txtUser.getText().toString();
                        pass = txtPass.getText().toString();

                        resultado = enviarDatosGET(user, pass);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int res = obtenerDatosJason(resultado);
                                if (res > 0){
                                    intent = new Intent(getApplicationContext(), ActivityTab.class);
                                    intent.putExtra("USUARIO", user);
                                    startActivity(intent);
                                    Toast.makeText(getApplicationContext(), "Succes!", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(getApplicationContext(), "Usuario o Contraseña incorrectos", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                };

                tr.start();

                break;
            case R.id.btnRegistro:
                //Accion al darle click al boton registrar
                intent = new Intent(this, ActivityRegistro.class);
                startActivity(intent);
                break;
        }
    }

    public String enviarDatosGET(String usuario, String pass){
        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder resul = null;

        try {

            url = new URL("http://petlove.hol.es/valida.php?user="+usuario+"&pass="+pass);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            respuesta = connection.getResponseCode();

            resul = new StringBuilder();

            if (respuesta == HttpURLConnection.HTTP_OK){

                InputStream in = new BufferedInputStream(connection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader((in)));

                while ((linea = reader.readLine()) != null){
                    resul.append(linea);
                }
            }

        }catch (Exception ex){
            Toast.makeText(getApplicationContext(), "Error: "+ex, Toast.LENGTH_SHORT).show();
        }

        return resul.toString();
    }

    public int obtenerDatosJason(String respuesta){
        int res = 0;
        try {
            JSONArray json = new JSONArray(respuesta);
            if (json.length() > 0){
                res = 1;
            }
        }catch (Exception ex){
            Toast.makeText(getApplicationContext(), "Error: "+ex, Toast.LENGTH_SHORT).show();
        }
        return res;
    }
}
