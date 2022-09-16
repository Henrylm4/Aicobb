package com.example.proyectoembebidos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Crear extends AppCompatActivity {

    private List<String> listaubi=new ArrayList<String>();
    ArrayAdapter<String> arrayubi;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    //ListView listubi;
    //Spinner listubi;
    ListView listubi;
    private DatePickerDialog datePickerDialog;
    private Button enviar, fregistro;
    private EditText peso, cantidadp, nmuertos, nenfermos, proporcion, observacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear);
        initDatePicker();

        Calendar c= Calendar.getInstance();
        fregistro = findViewById(R.id.Edit_Fregistro);
        fregistro.setText(getTodaysDate());
        peso = findViewById(R.id.Edit_peso);
        cantidadp = findViewById(R.id.edit_cantidadp);
        nmuertos = findViewById(R.id.edit_nmuertos);
        nenfermos = findViewById(R.id. Edit_nenfermos);
        proporcion = findViewById(R.id.Edit_Proporcion);
        observacion = findViewById(R.id.Edit_Observacion);
        listubi=findViewById(R.id.ListaUbic);
        enviar = (Button) findViewById(R.id.button);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviardatos();
            }

        });
        InicializarFirebase();
        ListarDatos();

    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                fregistro.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
    }
    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "Ene";
        if(month == 2)
            return "Feb";
        if(month == 3)
            return "Mar";
        if(month == 4)
            return "Abr";
        if(month == 5)
            return "May";
        if(month == 6)
            return "Jun";
        if(month == 7)
            return "Jul";
        if(month == 8)
            return "Ago";
        if(month == 9)
            return "Sep";
        if(month == 10)
            return "Oct";
        if(month == 11)
            return "Nov";
        if(month == 12)
            return "Dic";

        //default should never happen
        return "Ene";
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }

    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void ListarDatos() {
        databaseReference.child("Registro").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaubi.clear();
                for(DataSnapshot objSnapshot : snapshot.getChildren()){
                    Object ubi= objSnapshot.getValue();
                    String puesto=objSnapshot.getKey();
                    listaubi.add(puesto);



                    arrayubi = new ArrayAdapter<String>(Crear.this, android.R.layout.simple_list_item_1,listaubi);
                    listubi.setAdapter(arrayubi);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void InicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
    }

    private void enviardatos() {
        String FregistroT= fregistro.getText().toString();
        String PesoT= peso.getText().toString();
        String CantidadPT= cantidadp.getText().toString();
        String NmuertosT= nmuertos.getText().toString();
        //String ubi=listubi.getSelectedItem().toString();
        String nenfermost = nenfermos.getText().toString();
        String proporciont = proporcion.getText().toString();
        String observaciont = observacion.getText().toString();
        if (FregistroT.equals("")||PesoT.equals("")||CantidadPT.equals("")||NmuertosT.equals("")||nenfermost.equals("")||proporciont.equals("")||observaciont.equals("")){
            validación();

        }
        else{
            RegistroDiario h = new RegistroDiario();
            Date date = new Date();
            //h.setUbicación(ubi);
            //h.setFregistro(fregistro.getText().toString());
            //h.setFechaf(sumarRestarDiasFecha(date,30));
            h.setNmuertos(NmuertosT);
            h.setNenfermos(nenfermost);
            h.setProporción(proporciont);
            h.setObservacion(observaciont);
            h.setNpollos(CantidadPT);
            h.setFregistro(FregistroT);
            //h.setOcupado(true);
            h.setPeso(PesoT);
            //h.setUid(UUID.randomUUID().toString());
            if (listaubi.contains(h.getFregistro().toString())){
                Toast.makeText(this,"Ya existe un registro asignado en la fecha seleccionada",Toast.LENGTH_LONG).show();
            }
            else{
                databaseReference.child("Registro").child(h.getFregistro().toString()).setValue(h);
                //databaseReference.child("PUESTOS").child(h.getUbicación()).setValue(1);
                Toast.makeText(this,"Agregado",Toast.LENGTH_LONG).show();
                limpiarcajas();
            }

        }
    }

    private void limpiarcajas() {
        fregistro.setText("");
        peso.setText("");
        cantidadp.setText("");
        nmuertos.setText("");
        nenfermos.setText("");
        cantidadp.setText("");
        observacion.setText("");
        proporcion.setText("");
    }

    private void validación() {
        String fregistrotext = fregistro.getText().toString();
        String pesotext= peso.getText().toString();
        String cantidadptext= cantidadp.getText().toString();
        String nmuertostext= nmuertos.getText().toString();
        String nenfermostext= nenfermos.getText().toString();
        String paguaraciontext= proporcion.getText().toString();
        if (fregistrotext == ""){
            fregistro.setError("Campo Requerido");
         }
        if (pesotext == ""){
            peso.setError("Campo Requerido");

        }
        if (cantidadptext == ""){
            cantidadp.setError("Campo Requerido");
        }
        if (nmuertostext == ""){
            nmuertos.setError("Campo Requerido");
        }
        if (nenfermostext == ""){
            nmuertos.setError("Campo Requerido");
        }
        if (paguaraciontext == ""){
            nmuertos.setError("Campo Requerido");
        }
    }
    public Date sumarRestarDiasFecha(Date fecha, int dias){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0
        return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos

    }
}