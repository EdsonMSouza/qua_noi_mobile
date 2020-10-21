package com.example.projetolistview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    EditText nome, sobrenome, curso;
    Button btAdicionar;
    ListView listViewPessoas;
    ArrayList<Pessoa> listaPessoas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome = findViewById(R.id.editTextNome);
        sobrenome = findViewById(R.id.editTextSobrenome);
        curso = findViewById(R.id.editTextCurso);
        btAdicionar = findViewById(R.id.buttonAdicionar);
        listViewPessoas = findViewById(R.id.listViewPessoas);

        // Configurando a ListView (Lista de Objetos)
        final ArrayAdapter<Pessoa> adapter =
                new ArrayAdapter<>(
                        getApplicationContext(),
                        android.R.layout.simple_list_item_1,
                        listaPessoas);

        // ligando o adaptador com a ListView
        listViewPessoas.setAdapter(adapter);

        btAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listaPessoas.add(
                        new Pessoa(
                                nome.getText().toString(),
                                sobrenome.getText().toString(),
                                curso.getText().toString())
                );

                // avisa ao adaptador que os dados foram alterados
                adapter.notifyDataSetChanged();

                // Ordenando a listView dinamicamente
                Collections.sort(listaPessoas, new OrdenaPorNome());

                nome.setText("");
                sobrenome.setText("");
                curso.setText("");
                nome.requestFocus();
            }
        });

        // clicando na lista e visualizando os dados com o Toast
        listViewPessoas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // recupera o objeto que foi clicado na lista
                Pessoa p = (Pessoa) listViewPessoas.getItemAtPosition(position);

                Intent it = new Intent(getApplicationContext(), Resultado.class);
                it.putExtra("pessoa", p);
                startActivity(it);

                /* Mostra os dados com o Toast
                Toast.makeText(
                        getApplicationContext(),
                        p.getDados(),
                        Toast.LENGTH_LONG).show();
                */
            }
        });

        // excluir um elemento da lista
        // vamos excluir ele também do ArrayList<Pessoa> (listaPessoas)
        listViewPessoas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Pessoa p = (Pessoa) listViewPessoas.getItemAtPosition(position);

                // remove o objeto da lista
                listaPessoas.remove(p);

                // atualizar o adaptador
                adapter.notifyDataSetChanged();

                return false;
            }
        });
    }
}