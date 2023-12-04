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
import com.google.firebase.auth.FirebaseAuth;

public class Recuperar_contrasenia extends AppCompatActivity {
    private EditText etCorre;
    private Button btnEnviar;
    private FirebaseAuth mAuth;
    private String correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_contrasenia);
        mAuth=FirebaseAuth.getInstance();
        etCorre=findViewById(R.id.etCorreo);
        btnEnviar=findViewById(R.id.btnEnviar);

            btnEnviar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    correo=etCorre.getText().toString().trim();
                    mAuth.sendPasswordResetEmail(correo).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Intent i=new Intent(Recuperar_contrasenia.this,LoginActivity.class);
                                startActivity(i);
                            }
                        }
                    });

                }
            });
    }
}