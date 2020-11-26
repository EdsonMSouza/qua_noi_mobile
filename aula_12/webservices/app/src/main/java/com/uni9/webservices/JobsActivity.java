package com.uni9.webservices;

import android.os.AsyncTask;
import android.util.Log;

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

public class JobsActivity extends AppCompatActivity {

    public static class JobsService extends AsyncTask<Void, Void, List<Jobs>> {
        private final String jsonInputString;

        public JobsService(Jobs job) {
            Gson gson = new Gson();
            jsonInputString = gson.toJson(job);
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected List<Jobs> doInBackground(Void... voids) {
            StringBuilder response = new StringBuilder();
            try {
                URL url = new URL("http://emsapi.esy.es/api_android/jobs.php");
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
                    // mostra no log o JSON retornado
                    //System.out.println(response.toString());
                }
                serv.disconnect();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            Type userType = new TypeToken<ArrayList<Jobs>>() {
            }.getType();
            List<Jobs> leituras = new Gson().fromJson(response.toString(), userType);
            return leituras;
        }

        protected void onPostExecute(final List<Jobs> result) {
            super.onPostExecute(result);
            try {
                if (result.size() > 0) {
                    for (Jobs j : result) {
                        if (j.getId() != 0) {
                            //Log.i("id", String.valueOf(j.getId()));
                            //Log.i("description", j.getDescription());
                            //Log.i("date", j.getDate());
                        } else {
                            Log.i("description", j.getType());
                        }
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
}