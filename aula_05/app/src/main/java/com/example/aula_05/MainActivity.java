package com.example.aula_05;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText varNome, varSobrenome;
    Button btConcatenar;
    TextView varResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        varNome = findViewById(R.id.editTextNome);
        varSobrenome = findViewById(R.id.editTextSobrenome);
        btConcatenar = findViewById(R.id.buttonConcatenar);
        varResultado = findViewById(R.id.textViewResultado);

        /*
         * Abaixo vamos acionar o evento do click do botão para processar os dados
         * */
        btConcatenar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // a linha abaixo atribui um valor TEXTO ao objeto, no caso o varResultado
                //varResultado.setText("Olá Mundo!!!");

                // alterando o tamanho do texto via código
                //varResultado.setTextColor(Color.argb(100,200,0,0));

                // recuperando o valor de um objeto, usamos o getText()
                varResultado.setText(varNome.getText() + " " + varSobrenome.getText());

                // posicionar o cursor no primeiro campo
                varNome.requestFocus();

                // vamos mostrar o resultado em um componente nativo do Android (TOAST)
                Toast.makeText(
                        MainActivity.this, varNome.getText() + " " + varSobrenome.getText()
                        , Toast.LENGTH_LONG).show();

                // conversão de texto em número (int, float ou double)
                double n1 = Double.parseDouble(varNome.getText().toString());
                int n2 = Integer.parseInt(varSobrenome.getText().toString());

                // convertendo um número para String
                varResultado.setText(String.valueOf(n1+n2));


                // apagar os valores digitados
                varNome.setText("");
                varSobrenome.setText("");

            }
        });

    }
}