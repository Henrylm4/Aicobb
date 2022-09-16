package com.example.proyectoembebidos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText user, pass;
    private Button login, register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user= (EditText) findViewById(R.id.PlainUser);
        pass= (EditText) findViewById(R.id.PlainContra);
        login= (Button) findViewById(R.id.Btn_Iniciarsesion);
        register= (Button) findViewById(R.id.Btn_Registrarse);
        user.setText("JulianB");
        pass.setText("123456");
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user.getText().toString().equals("JulianB") && pass.getText().toString().equals("123456")){

                    Intent intent= new Intent(MainActivity.this, MenuP.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this, "Usuario o Contraseña Incorrecta", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}