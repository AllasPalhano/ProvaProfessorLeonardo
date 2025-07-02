package com.example.prova;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MarcarTomadoActivity extends AppCompatActivity {

    private List<Medicamento> listaMed;
    private MarcarTomadoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marcar_tomado);

        // Aqui usei o ID do RecyclerView, não do CheckBox
        RecyclerView rv = findViewById(R.id.rvMarcarTomado);
        rv.setLayoutManager(new LinearLayoutManager(this));

        // 1) Carrega lista
        listaMed = loadMedicamentos();

        // 2) Adapter com listener de toggle
        adapter = new MarcarTomadoAdapter(listaMed, (position, isChecked) -> {
            listaMed.get(position).setTomado(isChecked);
            saveMedicamentos();
        });

        rv.setAdapter(adapter);
    }

    private List<Medicamento> loadMedicamentos() {
        SharedPreferences prefs = getSharedPreferences("med_prefs", MODE_PRIVATE);
        String json = prefs.getString("medicamentos", "[]");
        List<Medicamento> out = new ArrayList<>();
        try {
            JSONArray arr = new JSONArray(json);
            for (int i = 0; i < arr.length(); i++) {
                out.add(Medicamento.fromJson(arr.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return out;
    }

    private void saveMedicamentos() {
        SharedPreferences prefs = getSharedPreferences("med_prefs", MODE_PRIVATE);
        JSONArray arr = new JSONArray();
        for (Medicamento m : listaMed) {
            try {
                arr.put(m.toJson());
            } catch (JSONException ignored) {}
        }
        prefs.edit()
                .putString("medicamentos", arr.toString())
                .apply();
    }
}
