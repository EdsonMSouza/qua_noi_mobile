package com.example.objetosintent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText nome, sobrenome;
    Button btProxima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nome = findViewById(R.id.editTextNome);
        sobrenome = findViewById(R.id.editTextSobrenome);
        btProxima = findViewById(R.id.buttonProxima);

        btProxima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), Resultado.class);

                // cria o objeto Pessoa com o construtor vazio
                Pessoa p = new Pessoa(nome.getText().toString(), sobrenome.getText().toString());

                // colocando o objeto na Intent
                it.putExtra("pessoa", p);

                // enviar o objeto
                startActivity(it);
            }
        });
    }
}