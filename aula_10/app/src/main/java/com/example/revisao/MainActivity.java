package com.example.revisao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // declaração das variáveis para associar aos componentes do layout
    EditText nome, sobrenome;
    Button btOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // a linha abaixo faz o carregamento do layout correspondente
        setContentView(R.layout.activity_main);

        // associar os componentes às variáveis que foram criadas lá em cima
        nome = findViewById(R.id.editTextNome);
        sobrenome = findViewById(R.id.editTextSobrenome);
        btOk = findViewById(R.id.buttonOk);

        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // primeiro vamos mostrar o valores digitados em um aviso (Toast)
                Toast.makeText(
                        MainActivity.this,
                        nome.getText().toString() + " " + sobrenome.getText().toString(),
                        Toast.LENGTH_LONG).show();

                // passar valor para outra tela, devemos criar o ambiente (xml + java)
                Intent proxima = new Intent(MainActivity.this, Resultado.class);
                Bundle pacote = new Bundle();

                // coletar os dados digitados e colocar no pacote
                pacote.putString("nome", nome.getText().toString());
                pacote.putString("sobrenome", sobrenome.getText().toString());


                // coloca os dados dentro do pacote
                proxima.putExtras(pacote);

                // manda para a outra tela
                startActivity(proxima);

                // passando um objeto
                String a = new String();



            }
        });
    }
}