package com.example.prova;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CadastrarMedicamentoActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "med_prefs";
    private static final String KEY_LIST   = "medicamentos";

    private EditText etNomeMedicamento;
    private EditText etDosagem;
    private EditText etHorario;
    private Button btnSalvarMedicamento;
    private Button buttonVoltar;

    private JSONArray arrMedicamentos;
    private int editIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_medicamento);

        etNomeMedicamento    = findViewById(R.id.etNomeMedicamento);
        etDosagem            = findViewById(R.id.etDosagem);
        etHorario            = findViewById(R.id.etHorario);
        btnSalvarMedicamento = findViewById(R.id.btnSalvarMedicamento);
        buttonVoltar         = findViewById(R.id.buttonVoltar);

        // 1) Carrega o JSONArray do SharedPreferences
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String jsonList = prefs.getString(KEY_LIST, "[]");
        try {
            arrMedicamentos = new JSONArray(jsonList);
        } catch (JSONException e) {
            arrMedicamentos = new JSONArray();
        }

        // 2) Verifica se veio em modo edição
        Intent it = getIntent();
        if (it.hasExtra("edit_index")) {
            editIndex = it.getIntExtra("edit_index", -1);
            if (editIndex >= 0 && editIndex < arrMedicamentos.length()) {
                try {
                    JSONObject obj = arrMedicamentos.getJSONObject(editIndex);
                    etNomeMedicamento.setText(obj.getString("nome"));
                    etDosagem.setText(obj.getString("dosagem"));
                    etHorario.setText(obj.getString("horario"));
                    btnSalvarMedicamento.setText("Atualizar");
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            }
        }

        // 3) Ao clicar em Salvar/Atualizar
        btnSalvarMedicamento.setOnClickListener(v -> {
            String nome    = etNomeMedicamento.getText().toString().trim();
            String dosagem = etDosagem.getText().toString().trim();
            String horario = etHorario.getText().toString().trim();

            if (nome.isEmpty() || dosagem.isEmpty() || horario.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                // Cria o objeto JSON
                Medicamento m = new Medicamento(nome, dosagem, horario);
                JSONObject novoObj = m.toJson();

                if (editIndex >= 0 && editIndex < arrMedicamentos.length()) {
                    // atualiza o item existente
                    arrMedicamentos.put(editIndex, novoObj);
                } else {
                    // adiciona novo item
                    arrMedicamentos.put(novoObj);
                }

                // salva de volta no SharedPreferences
                prefs.edit()
                        .putString(KEY_LIST, arrMedicamentos.toString())
                        .apply();

                Toast.makeText(
                        this,
                        editIndex >= 0 ? "Medicamento atualizado!" : "Medicamento salvo!",
                        Toast.LENGTH_SHORT
                ).show();
                finish();
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(this, "Erro ao salvar", Toast.LENGTH_SHORT).show();
            }
        });

        // 4) Botão Voltar
        buttonVoltar.setOnClickListener(v -> finish());
    }
}
