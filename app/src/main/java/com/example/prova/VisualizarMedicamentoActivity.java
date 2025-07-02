package com.example.prova;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class VisualizarMedicamentoActivity extends AppCompatActivity {

    private List<Medicamento> listaMed;
    private VisualizarMedicamentoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_medicamento);

        // Configura o RecyclerView
        RecyclerView rv = findViewById(R.id.rvVisualizarMedicamentos);
        rv.setLayoutManager(new LinearLayoutManager(this));

        // Carrega os medicamentos salvos
        listaMed = loadMedicamentos();

        // Cria o adapter com listener de editar e excluir
        adapter = new VisualizarMedicamentoAdapter(
                listaMed,
                new VisualizarMedicamentoAdapter.OnItemAction() {
                    @Override
                    public void onEdit(int position) {
                        Medicamento m = listaMed.get(position);
                        Intent it = new Intent(
                                VisualizarMedicamentoActivity.this,
                                CadastrarMedicamentoActivity.class
                        );
                        // Passa índice e dados para edição
                        it.putExtra("edit_index", position);
                        it.putExtra("med_nome",    m.getNome());
                        it.putExtra("med_dosagem", m.getDosagem());
                        it.putExtra("med_horario", m.getHorario());
                        startActivity(it);
                    }

                    @Override
                    public void onDelete(int position) {
                        listaMed.remove(position);
                        adapter.notifyItemRemoved(position);
                        saveMedicamentos();
                    }
                }
        );
        rv.setAdapter(adapter);


    }

    @Override
    protected void onResume() {
        super.onResume();
        // Recarrega lista após edição ou exclusão
        listaMed.clear();
        listaMed.addAll(loadMedicamentos());
        adapter.notifyDataSetChanged();
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
