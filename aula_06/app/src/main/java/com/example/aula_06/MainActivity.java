package com.example.aula_06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {
    EditText varPeso, varAltura;
    Button btCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        varPeso = findViewById(R.id.editTextPeso);
        varAltura = findViewById(R.id.editTextAltura);
        btCalcular = findViewById(R.id.buttonCalcular);

        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // coletar os dados digitados pelo usuário
                String valorPeso = varPeso.getText().toString();
                String valorAltura = varAltura.getText().toString();

                // vamos criar o envelope para colocarmos os dados
                Bundle envelope = new Bundle(); // Bundle significa Pacote

                // vamos colocar os dados dentro do envelope
                envelope.putString("keyPeso", valorPeso);
                envelope.putString("keyAltura", valorAltura);

                // vamos criar o correio para postar o envelope
                Intent correio = new
                        Intent(getApplicationContext(),
                        ResultadoActivity.class);

                // estamos colocando o envelope no correio
                correio.putExtras(envelope);

                // vamos enviar o envelope (postagem)
                startActivity(correio);

            }
        });

    }
}