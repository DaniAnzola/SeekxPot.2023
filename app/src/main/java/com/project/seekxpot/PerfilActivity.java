package com.project.seekxpot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.seekxpot.Pojo.Persona;

public class PerfilActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private DatabaseReference mRef;
    private FirebaseUser fbUser;
    private TextView etNombrePerfil, etApellidoPerfil, etCorreoPerfil;
    private String nombre, correo;
    private Button botonEditar, botonGuardar, btnGuardados;
    private Persona persona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        etNombrePerfil = findViewById(R.id.etNombrePerfil);
        etApellidoPerfil = findViewById(R.id.etApellidoPerfil);
        etCorreoPerfil = findViewById(R.id.etCorreoPerfil);
        botonEditar = findViewById(R.id.botonEditar);
        botonGuardar = findViewById(R.id.botonGuardar);
        btnGuardados =findViewById(R.id.btnGuardados);

        mAuth = FirebaseAuth.getInstance();
        mRef = FirebaseDatabase.getInstance().getReference().child("usuario");
        fbUser = mAuth.getCurrentUser();// esto siempre sera despues de la isntancia de mAuth

        String uId = fbUser.getUid();
        mRef.child(uId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Persona p = snapshot.getValue(Persona.class);
                etNombrePerfil.setText(p.getNombre());
                etApellidoPerfil.setText(p.getApellido());
                etCorreoPerfil.setText(p.getCorreo());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        botonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                habilitar();
            }
        });

        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            guardarCambios();

            }
        });
        btnGuardados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO INTENT AL ACTIVITY DONDE VAN A QUEDAR LOS GARITOS QUE EL USUSRARIO GUARDE COMO FAVORITOS.
            }
        });


    }
//Metodo de deshabilitar
    private void desahibilitar() {
        etNombrePerfil.setEnabled(false);
        etApellidoPerfil.setEnabled(false);
        etCorreoPerfil.setEnabled(false);
        botonGuardar.setEnabled(false);

        botonEditar.setEnabled(true);
    }
//Metodo de Habilitar
    private void habilitar() {
        etNombrePerfil.setEnabled(true);
        etApellidoPerfil.setEnabled(true);
        etCorreoPerfil.setEnabled(false);
        botonGuardar.setEnabled(true);


        botonEditar.setEnabled(false);


    }

    private void guardarCambios() {
        // Aquí se obtiene los nuevos datos ingresados por el usuario
        String nuevoNombre = etNombrePerfil.getText().toString();
        String nuevoApellido = etApellidoPerfil.getText().toString();
        String nuevoCorreo = etCorreoPerfil.getText().toString();

        // Verificación que los campos no estén vacíos antes de guardar
        if (!nuevoNombre.isEmpty() && !nuevoApellido.isEmpty() && !nuevoCorreo.isEmpty()) {
            // Actualizar los datos en la base de datos
            String uId = fbUser.getUid();
            DatabaseReference usuarioRef = mRef.child(uId);
            usuarioRef.child("nombre").setValue(nuevoNombre);
            usuarioRef.child("apellido").setValue(nuevoApellido);
            usuarioRef.child("correo").setValue(nuevoCorreo);

            //la edición se desahabilita después de guardar
            desahibilitar();
        }else {
            // Toast diciendo que todos los campos deben estar llenos
            Toast.makeText(PerfilActivity.this, "Todos los campos deben estar llenos", Toast.LENGTH_SHORT).show();
        }


    }
}