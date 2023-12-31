package com.project.seekxpot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.seekxpot.Pojo.Garito;
import com.project.seekxpot.Pojo.Persona;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    private EditText etNombre, etApellido, etCorreoR, etEdad, etContraseniaR, etContrasenia2;
    private FirebaseAuth mAuth;
    private TextView tvpolitica;
    private DatabaseReference mRef;
    private FirebaseUser fbUser;
    private Button btnRegistrar;
    private String nombre, correo, contrasenia, contrasenia2, apellido;

    private static final String EMAIL_PATTERN ="^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    private ProgressDialog progressDialog;
    private int edad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        mAuth = FirebaseAuth.getInstance();
        mRef = FirebaseDatabase.getInstance().getReference().child("Usuarios");
        tvpolitica= findViewById(R.id.tvpolitica);
        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etCorreoR = findViewById(R.id.etCorreoR);
        etEdad = findViewById(R.id.etEdad);
        etContraseniaR = findViewById(R.id.etContraseniaR);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        etContrasenia2 = findViewById(R.id.etContrasenia2);

        progressDialog = new ProgressDialog(RegisterActivity.this);

        tvpolitica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirpdf(R.raw.aviso_legal);


            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Guardando Datos . . .");
                progressDialog.show();
                nombre = etNombre.getText().toString().trim();
                apellido = etApellido.getText().toString().trim();
                edad = Integer.parseInt(etEdad.getText().toString().trim());
                correo = etCorreoR.getText().toString().trim();
                contrasenia = etContraseniaR.getText().toString().trim();
                contrasenia2 = etContrasenia2.getText().toString().trim();

                //TODO VALIDAR QUE ME INTRODUCE LOS DATOS QUE ME GUSTAN

                if(contrasenia.equals(contrasenia2)){// VALIDACION CONTRASEÑA
                    if(pattern.matcher(correo).matches()){//VALIDACION DE CORREO


                    }else{
                        Toast.makeText(RegisterActivity.this, "Introduce un Correo valido", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(RegisterActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
                Persona u = new Persona(nombre, apellido, edad, correo);

                mAuth.createUserWithEmailAndPassword(correo, contrasenia).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            fbUser = mAuth.getCurrentUser();
                            String uid = fbUser.getUid();


                            mRef.child(uid).setValue(u).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        progressDialog.dismiss();
                                        Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                                        startActivity(i);
                                    }

                                }
                            });

                            }


                    }
                });

            }
        });


    }

    private void abrirpdf(int avisoLegal) {

        try {
            InputStream inputStream = getResources().openRawResource(avisoLegal);
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            String pdfFileName="aviso_legal.pdf";
            File tempFile= new File(getFilesDir(),pdfFileName);
            FileOutputStream outputStream= new FileOutputStream(tempFile);
            outputStream.write(buffer);
            outputStream.close();
            Uri fileUri= FileProvider.getUriForFile(this,getApplicationContext().getPackageName()+".fileprovider",tempFile);
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setDataAndType(fileUri,"application/pdf");
            i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            try{
                startActivity(i);
            }catch (ActivityNotFoundException e ){
                Toast.makeText(this, "No hay lector de pdf en su Dispositivo", Toast.LENGTH_SHORT).show();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}


