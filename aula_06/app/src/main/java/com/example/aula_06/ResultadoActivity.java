package com.example.aula_06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultadoActivity extends AppCompatActivity {
    TextView varPeso, varAltura;
    Button btVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        varPeso = findViewById(R.id.textViewPeso);
        varAltura = findViewById(R.id.textViewAltura);
        btVoltar = findViewById(R.id.buttonVoltar);

        // vamos criar um correio para receber o envelope
        Intent correio = getIntent();

        // abrir o envelope
        Bundle envelope = correio.getExtras();

        // retirar os dados de dentro do envelope (ler os dados)
        //varPeso.setText(envelope.getString("keyPeso"));
        //varAltura.setText(envelope.getString("keyAltura"));

        double peso = Double.parseDouble(envelope.getString("keyPeso"));
        double altura = Double.parseDouble(envelope.getString("keyAltura"));

        double imc = peso / (altura * altura);
        varAltura.setText(String.valueOf(imc));

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}