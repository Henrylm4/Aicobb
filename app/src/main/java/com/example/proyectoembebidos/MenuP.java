package com.example.proyectoembebidos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuP extends AppCompatActivity {
    private Button control, deteccion, registro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_p);

        control = (Button) findViewById(R.id.Btn_Control);
        deteccion = (Button) findViewById(R.id.Btn_Deteccion);
        registro = (Button) findViewById(R.id.Btn_Registro);

       control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuP.this, Control_y_Monitoreo.class);
                startActivity(intent);
            }
        });
       deteccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuP.this, Deteccion_enfermedades.class);
                startActivity(intent);
            }
        });
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuP.this, Crear.class);
                startActivity(intent);
            }
        });
    }
}