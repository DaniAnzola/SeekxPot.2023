package com.project.seekxpot;

import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class HomeF extends Fragment {
    private FirebaseAuth mAuth;
    private Button btnCerrar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mAuth = FirebaseAuth.getInstance();
        btnCerrar=view.findViewById(R.id.btnCerrar);
        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();

                SharedPreferences gp= getActivity().getBaseContext().getSharedPreferences("shared", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=gp.edit();
                editor.putString("user",null);
                editor.apply();
                Intent i=new Intent(getContext(),LoginActivity.class);
                startActivity(i);

            }
        });




        return view;
    }
}