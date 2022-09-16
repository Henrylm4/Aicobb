package com.example.proyectoembebidos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class Control_Actuadores extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ImageButton Guardar;
    Switch Motor, Luz;
    Button Horaencendido;
    int hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_actuadores);
        Motor = findViewById(R.id.Btn_motor);
        Luz = findViewById(R.id.Switch_Luz);
        Guardar = findViewById(R.id.Btn_Save2);
        Horaencendido = findViewById(R.id.Edit_Hora);
        Luz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviardatos();
            }

        });
        Motor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviardatos();
            }

        });
        Guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviardatos();
            }

        });
        InicializarFirebase();
        ListarDatos();

    }
    private void enviardatos() {
        Boolean ModoMotor= Motor.isChecked();
        Boolean ModoLuz = Luz.isChecked();
        Control_Manual controlm = new Control_Manual();
        controlm.setHora(hour);
        controlm.setMinuto(minute);
        controlm.setMotor(ModoMotor);
        controlm.setLuz(ModoLuz);

        databaseReference.child("ControlActuadores").setValue(controlm);
        Toast.makeText(this,"Agregado",Toast.LENGTH_LONG).show();


    }


    public void popTimePicker(View view)
    {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
            {
                hour = selectedHour;
                minute = selectedMinute;
                Horaencendido.setText(String.format(Locale.getDefault(), "%02d:%02d",hour, minute));
            }
        };

        int style = AlertDialog.THEME_HOLO_DARK;

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, /*style,*/ onTimeSetListener, hour, minute, true);

        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }
    private void InicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
    }
    private void ListarDatos() {
        databaseReference.child("ControlActuadores").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot objSnapshot = snapshot;
                Control_Manual control = objSnapshot.getValue(Control_Manual.class);
                hour = control.getHora();
                minute = control.getMinuto();
                Horaencendido.setText(String.format(Locale.getDefault(), "%02d:%02d",hour, minute));
                Luz.setChecked(control.getLuz());
                Motor.setChecked(control.getMotor());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}