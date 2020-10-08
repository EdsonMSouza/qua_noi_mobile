package com.example.arraydeobjetos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.ResultSet;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText nome, sobrenome;
    Button btAdicionar, btProxima;

    // Vamos criar uma estrutura denominada ArrayList
    // Esse sinal "<>" é chamado de Diamond
    // Todo ArrayList precisa de um tipo "classe" dentro do diamond
    ArrayList<Pessoa> listaPessoas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nome = findViewById(R.id.editTextNome);
        sobrenome = findViewById(R.id.editTextSobrenome);
        btAdicionar = findViewById(R.id.buttonAdicionar);
        btProxima = findViewById(R.id.buttonProxima);

        btAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ADICIONAR ELEMENTOS NA LISTA
                // Primeiro vamos entender a dinâmica
                // o comando abaixo "add" insere um "elemento" na lista
                listaPessoas.add(
                        new Pessoa(
                                nome.getText().toString(),
                                sobrenome.getText().toString()
                        )
                );
                nome.setText("");
                sobrenome.setText("");
                nome.requestFocus();
            }
        });

        btProxima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ENVIAR PARA OUTRA ACTIVITY
                Intent proxima =
                        new Intent(
                                getApplicationContext(),
                                Resultado.class
                        );

                // colocando a listaPessoas na Intent
                proxima.putExtra("pessoas", listaPessoas);

                // chama a activity
                startActivity(proxima);
            }

        });
    }
}