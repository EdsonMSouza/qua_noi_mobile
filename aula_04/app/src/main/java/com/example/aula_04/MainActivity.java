package com.example.aula_04;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // Variáveis declaradas aqui, antes do @Override, são globais (para a classe)
    // Então, aqui vamos declarar todas as variáveis que se referem aos componentes
    // do layout.
    EditText varNome, varSobrenome;
    Button btConcatenar;
    TextView varResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
         * CTRL+ALT+L -> organiza o código (identação)
         * Nas linhas abaixo, vamos associar as variáveis locais com os
         * componentes que criamos no layout (activity_main.xml)
         * */
        varNome = findViewById(R.id.editTextNome);
        varSobrenome = findViewById(R.id.editTextSobrenome);
        btConcatenar = findViewById(R.id.buttonConcatenar);
        varResultado = findViewById(R.id.textViewResultado);

    }
}