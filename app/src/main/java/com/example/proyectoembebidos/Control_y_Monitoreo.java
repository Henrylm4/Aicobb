package com.example.proyectoembebidos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Control_y_Monitoreo extends AppCompatActivity {


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private Button Visualizar, Controlar;
    private ImageButton Save;
    private EditText dias,peso,cantidad;
    private Switch modo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_ymonitoreo);

        Visualizar = findViewById(R.id.Btn_Visualizar);
        Controlar = findViewById(R.id.Btn_Controlar);
        Save = findViewById(R.id.Btn_Save);
        dias= findViewById(R.id.Edit_dias);
        peso= findViewById(R.id.Edit_Peso);
        cantidad= findViewById(R.id.Edit_Cantidad);
        modo=findViewById(R.id.Switch_Modo);

        Visualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Control_y_Monitoreo.this, Visualizar_Sensores.class);
                startActivity(intent);
            }
        });
        Controlar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Control_y_Monitoreo.this, Control_Actuadores.class);
                startActivity(intent);
            }
        });
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviardatos();
            }

        });
        InicializarFirebase();
        ListarDatos();
    }

    private void InicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
    }

    private void enviardatos() {
            String DiasT= dias.getText().toString();
            String PesoT= peso.getText().toString();
            String CantidadT= cantidad.getText().toString();
            Boolean ModoB= modo.isChecked();

            if (DiasT.equals("")||PesoT.equals("")||PesoT.equals("")||CantidadT.equals("")||ModoB.equals("")){
                validación();

            }
            else{
                Control c = new Control();
                c.setCantidad(CantidadT);
                c.setPeso(PesoT);
                c.setDias(DiasT);
                c.setModo(ModoB);
                databaseReference.child("Modo").setValue(c);
                Toast.makeText(this,"Agregado",Toast.LENGTH_LONG).show();

            }
    }
    private void ListarDatos() {
        databaseReference.child("Modo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot objSnapshot = snapshot;
                Control control = objSnapshot.getValue(Control.class);
                dias.setText(control.getDias());
                peso.setText(control.getPeso());
                cantidad.setText(control.getCantidad());
                modo.setChecked(control.getModo());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void validación() {
        String DiasT= dias.getText().toString();
        String PesoT= peso.getText().toString();
        String CantidadT= cantidad.getText().toString();

        if (DiasT == ""){
            dias.setError("Campo Requerido");
        }
        if (PesoT == ""){
            peso.setError("Campo Requerido");
        }
        if (CantidadT == ""){
            cantidad.setError("Campo Requerido");
        }
    }


}