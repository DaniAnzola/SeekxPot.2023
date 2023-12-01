package com.project.seekxpot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.seekxpot.Pojo.Persona;

public class RegisterActivity extends AppCompatActivity {
    private EditText etNombre, etApellido, etCorreoR, etEdad, etContraseniaR;
    private FirebaseAuth mAuth;

    private DatabaseReference mRef;
    private FirebaseUser fbUser;
    private Button btnRegistrar;
    private String nombre, correo, contrasenia, apellido;
    private int edad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        mAuth = FirebaseAuth.getInstance();
        mRef = FirebaseDatabase.getInstance().getReference().child("Usuarios");

        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etCorreoR = findViewById(R.id.etCorreoR);
        etEdad = findViewById(R.id.etEdad);
        etContraseniaR = findViewById(R.id.etContraseniaR);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre = etNombre.getText().toString().trim();
                apellido = etApellido.getText().toString().trim();
                edad = Integer.parseInt(etEdad.getText().toString().trim());
                correo = etCorreoR.getText().toString().trim();
                contrasenia = etContraseniaR.getText().toString().trim();


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
                                        Intent i= new Intent(RegisterActivity.this,LoginActivity.class);
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
}