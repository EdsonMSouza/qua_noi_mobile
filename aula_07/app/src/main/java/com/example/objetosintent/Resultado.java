package com.example.objetosintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Resultado extends AppCompatActivity {
    TextView nome, sobrenome, full;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        nome = findViewById(R.id.textViewNome);
        sobrenome = findViewById(R.id.textViewSobrenome);
        full = findViewById(R.id.textViewFull);

        // recuperar o objeto enviado
        Intent it = getIntent();

        // acessar o objeto (Pessoa -> p)
        Pessoa p = (Pessoa) it.getSerializableExtra("pessoa");

        // atribuindo os valores recuperados para os objetos do layout
        nome.setText(p.getNome());
        sobrenome.setText(p.getSobrenome());
        full.setText(p.toString());

    }
}