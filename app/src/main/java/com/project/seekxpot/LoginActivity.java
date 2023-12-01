package com.project.seekxpot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private FirebaseUser fbUser;

    private TextView tvlogin;

    private EditText etcorreo, etcontrasenia;

    private Button btniniciar,btnRegister,btnGuest;
    private String correo, contrasenia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        btn=findViewById(R.id.btn);

        tvlogin = (TextView) findViewById(R.id.tvLogin);
        etcorreo = (EditText) findViewById(R.id.etCorreo);
        etcontrasenia = (EditText) findViewById(R.id.etContrasenia);
        btniniciar = (Button) findViewById(R.id.btniniciar);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnGuest = (Button) findViewById(R.id.btnGuest);


        btniniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                correo = etcorreo.getText().toString().trim();
                contrasenia = etcontrasenia.getText().toString().trim();

                mAuth.signInWithEmailAndPassword(correo, contrasenia).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                           // Intent i = new Intent(LoginActivity.this, MainActivity.class);
                            //startActivity(i);
                        } else {
                            Toast.makeText(LoginActivity.this, "Contraseña o Correo Incorrecto", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(p);
            }
        });


    }
}
