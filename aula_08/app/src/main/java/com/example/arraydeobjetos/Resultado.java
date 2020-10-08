package com.example.arraydeobjetos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Resultado extends AppCompatActivity {
    TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        resultado = findViewById(R.id.textViewResultado);

        // recuperar a lista de pessoas
        Intent it = getIntent();

        // sempre que recebermos um valor na Intent, deve-se fazer o CAST ou conversão de tipo
        ArrayList<Pessoa> listaPessoas = (ArrayList) it.getSerializableExtra("pessoas");

        // E agora? What I do?
        // Vamos iterar (percorrer ou varrer) a lista
        // Para isso vamos usar um laço de repetição

        String todos = new String();

        for(Pessoa p : listaPessoas){
            todos += p.toString() + "\n";
        }

        resultado.setText(todos);
    }
}