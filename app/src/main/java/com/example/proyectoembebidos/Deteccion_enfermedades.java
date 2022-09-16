package com.example.proyectoembebidos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class Deteccion_enfermedades extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private StorageReference mStorageReference;
    private FirebaseAuth mAuth;


    //, mStorageReference1, mStorageReference2, mStorageReference3, mStorageReference4, mStorageReference5, mStorageReference6, mStorageReference7;
    ImageView Imagen, Imagen1,Imagen2,Imagen3,Imagen4,Imagen5,Imagen6,Imagen7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_deteccion_enfermedades);
        //mStorageReference = FirebaseStorage.getInstance().getReference().child("sick_1.jpg");
        /*mStorageReference1 = FirebaseStorage.getInstance().getReference().child("sick_2.jpg");
        mStorageReference2 = FirebaseStorage.getInstance().getReference().child("sick_3.jpg");
        mStorageReference3 = FirebaseStorage.getInstance().getReference().child("sick_4.jpg");
        mStorageReference4 = FirebaseStorage.getInstance().getReference().child("sick_5.jpg");
        mStorageReference5 = FirebaseStorage.getInstance().getReference().child("sick_6.jpg");
        mStorageReference6 = FirebaseStorage.getInstance().getReference().child("sick_7.jpg");
        mStorageReference7 = FirebaseStorage.getInstance().getReference().child("sick_8.jpg");*/

        Imagen = findViewById(R.id.imageView8);
/*        Imagen1 = findViewById(R.id.imageView9);
        Imagen2 = findViewById(R.id.imageView10);
        Imagen3 = findViewById(R.id.imageView11);
        Imagen4 = findViewById(R.id.imageView12);
        Imagen5 = findViewById(R.id.imageView13);
        Imagen6 = findViewById(R.id.imageView14);
        Imagen7 = findViewById(R.id.imageView15);*/
        /*try {
            final File localFile= File.createTempFile("sick_1","jpg");
            /*final File localFile1= File.createTempFile("sick_2","jpg");
            final File localFile2= File.createTempFile("sick_3","jpg");
            final File localFile3= File.createTempFile("sick_4","jpg");
            final File localFile4= File.createTempFile("sick_5","jpg");
            final File localFile5= File.createTempFile("sick_6","jpg");
            final File localFile6= File.createTempFile("sick_7","jpg");
            final File localFile7= File.createTempFile("sick_8","jpg");
            mStorageReference.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(Deteccion_enfermedades.this,"Pollo Enfermo detectado",Toast.LENGTH_SHORT).show();
                            Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            Imagen.setImageBitmap(bitmap);
                           /* Bitmap bitmap2 = BitmapFactory.decodeFile(localFile1.getAbsolutePath());
                            Imagen1.setImageBitmap(bitmap2);
                            Bitmap bitmap3 = BitmapFactory.decodeFile(localFile2.getAbsolutePath());
                            Imagen.setImageBitmap(bitmap3);
                            Bitmap bitmap4 = BitmapFactory.decodeFile(localFile3.getAbsolutePath());
                            Imagen.setImageBitmap(bitmap4);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Deteccion_enfermedades.this,"error",Toast.LENGTH_SHORT).show();
                        }
                    });

        }
        catch (IOException e) {
            e.printStackTrace();
        }

*/

    }
  /*  @Override
    public void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            // do your stuff
        } else {
            signInAnonymously();
        }

    }*/

    private void signInAnonymously() {
        mAuth.signInAnonymously().addOnSuccessListener(this, new  OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        // do your stuff
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {

                    }
                });
    }
}