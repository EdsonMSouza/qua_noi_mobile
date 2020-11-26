package com.uni9.webservices;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    String jsonInputString = null;

    public class UserService extends AsyncTask<Void, Void, List<User>> {
        public UserService(User user) {
            Gson gson = new Gson();
            jsonInputString = gson.toJson(user);
            System.out.println(jsonInputString);
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected List<User> doInBackground(Void... voids) {
            StringBuilder response = new StringBuilder();
            try {
                URL url = new URL("http://www.emsapi.esy.es/api_android/user.php");
                HttpURLConnection serv = (HttpURLConnection) url.openConnection();
                serv.setRequestMethod("POST");
                serv.setRequestProperty("Content-Type", "application/json; utf-8");
                serv.setRequestProperty("Accept", "application/json");
                serv.setConnectTimeout(5000);
                serv.setReadTimeout(5000);
                serv.setDoInput(true);
                serv.setDoOutput(true);

                try (OutputStream os = serv.getOutputStream()) {
                    byte[] input = jsonInputString.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }

                // leitura dos dados de resposta
                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(serv.getInputStream(), "utf-8"))) {

                    String responseLine = null;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    // mostra no log o JSON retornado (dados enviados e retornados)
                    System.out.println(response.toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            Type alunoType = new TypeToken<ArrayList<User>>() {
            }.getType();
            List<User> leituras = new Gson().fromJson(response.toString(), alunoType);
            return leituras;
        }

        protected void onPostExecute(final List<User> result) {
            super.onPostExecute(result);

            try {
                if (result.size() > 0) {
                    for (User u : result) {
                        Log.i("id", String.valueOf(u.getId()));
                        Log.i("name", u.getName());
                        Log.i("user", u.getUser());
                    }
                } else {
                    Log.i("message", "Erro na requisição");
                }
            } catch (Exception ex) {
                Log.i("erro", ex.getMessage());
            }
        }

        protected void onProgressUpdate(Integer... values) {
            // TODO
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText usuario, senha, userId;
        Button btVisualizar, btLogcat;
        TextView saida;

        usuario = findViewById(R.id.editTextUsuario);
        senha = findViewById(R.id.editTextSenha);
        btLogcat = findViewById(R.id.btLogcat);

        userId = findViewById(R.id.editTextUserId);
        btVisualizar = findViewById(R.id.btVisualizar);
        saida = findViewById(R.id.textViewJobs);

        btLogcat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setType("login");
                //user.setName("Estou testando via Android");
                user.setUser(usuario.getText().toString()); //"edson.melo"
                user.setPassword(senha.getText().toString()); //"edson123"
                new UserService(user).execute();
            }
        });

        btVisualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Jobs job = new Jobs();
                job.setType("jobs");
                job.setUser_id(Integer.parseInt(userId.getText().toString()));

                try {
                    ArrayList<Jobs> jobs = (ArrayList<Jobs>) new JobsActivity.JobsService(job).execute().get();
                    String s = new String();
                    for (Jobs j : jobs) {
                        s = s + j.getDescription() + "\n";
                        saida.setText(s);
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}