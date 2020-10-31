package com.example.revisao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Resultado extends AppCompatActivity {
    TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        resultado = findViewById(R.id.textViewResultado);

        Intent it = getIntent();
        Bundle pacote = it.getExtras();

        resultado.setText(pacote.getString("nome") + " " + pacote.getString("sobrenome"));


    }
}