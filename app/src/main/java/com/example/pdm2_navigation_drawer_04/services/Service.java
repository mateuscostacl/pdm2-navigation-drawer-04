package com.example.pdm2_navigation_drawer_04.services;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pdm2_navigation_drawer_04.pojo.Categoria;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Service extends AppCompatActivity {


    private String TAG = Service.class.getSimpleName();
    private static String URL = "https://my-json-server.typicode.com/mateuscostacl/pdm2-navigation-drawer-04/";

    List<Categoria> categoriaList = new ArrayList<>();

    public List<Categoria> pegarPorCategoria(String cat) {
        List<Categoria> cList = null;
        try {
            return new PegarPorCategoriaService().execute(this.URL + cat).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return cList;
    }

    private class PegarPorCategoriaService extends AsyncTask<String, Void, List<Categoria>> {

        @Override
        protected List<Categoria> doInBackground(String... params) {

            HttpHandler sh = new HttpHandler();

            String jsonStr = sh.usarServico(params[0]);

            Log.e(TAG, "Endereço da URL: " + params[0]);
            Log.e(TAG, "Resposta da URL: " + jsonStr);


            if (jsonStr != null) {
                try {

                    JSONArray jsonArray = new JSONArray(jsonStr);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject c = jsonArray.getJSONObject(i);

                        Categoria categoria = new Categoria();
                        categoria.setNome(c.getString("nome"));

                        JSONArray jsonDicas = c.getJSONArray("dicas");
                        for (int j = 0; j < jsonDicas.length(); j++) {
                            String dica = jsonDicas.get(j).toString();

                            categoria.getDicas().add(dica);
                        }

                        categoriaList.add(categoria);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "JSON erro: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Service.this,
                                    "JSON erro: " + e.getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
            return categoriaList;
        }
    }
}
