package com.example.prova;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MarcarTomadoAdapter
        extends RecyclerView.Adapter<MarcarTomadoAdapter.ViewHolder> {

    public interface OnToggle {
        void onToggle(int position, boolean isChecked);
    }

    private final List<Medicamento> lista;
    private final OnToggle listener;

    public MarcarTomadoAdapter(List<Medicamento> lista, OnToggle listener) {
        this.lista = lista;
        this.listener = listener;
    }

    @NonNull @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {

        // Inflamando o layout correto: item_marcar_tomado
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_marcar_tomado, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder, int position) {

        Medicamento m = lista.get(position);
        holder.cb.setText(
                m.getNome() + " — " +
                        m.getDosagem() + " — " +
                        m.getHorario()
        );

        holder.cb.setOnCheckedChangeListener(null);
        holder.cb.setChecked(m.isTomado());
        holder.cb.setOnCheckedChangeListener((button, isChecked) ->
                listener.onToggle(position, isChecked)
        );
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox cb;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            cb = itemView.findViewById(R.id.cbItemTomado);
        }
    }
}
