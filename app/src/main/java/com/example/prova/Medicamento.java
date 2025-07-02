package com.example.prova;

import org.json.JSONException;
import org.json.JSONObject;

public class Medicamento {
    private String nome;
    private String dosagem;
    private String horario;
    private boolean tomado;

    public Medicamento(String nome, String dosagem, String horario) {
        this.nome = nome;
        this.dosagem = dosagem;
        this.horario = horario;
        this.tomado = false;
    }

    // Getters e setters
    public String getNome()            { return nome; }
    public String getDosagem()         { return dosagem; }
    public String getHorario()         { return horario; }
    public boolean isTomado()          { return tomado; }
    public void setTomado(boolean t)   { this.tomado = t; }

    // Converte este objeto em JSONObject
    public JSONObject toJson() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("nome", nome);
        json.put("dosagem", dosagem);
        json.put("horario", horario);
        json.put("tomado", tomado);
        return json;
    }

    // Cria um Medicamento a partir de um JSONObject
    public static Medicamento fromJson(JSONObject json) throws JSONException {
        Medicamento m = new Medicamento(
                json.getString("nome"),
                json.getString("dosagem"),
                json.getString("horario")
        );
        m.setTomado(json.getBoolean("tomado"));
        return m;
    }
}
