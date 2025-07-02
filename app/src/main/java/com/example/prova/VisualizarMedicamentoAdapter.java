package com.example.prova;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VisualizarMedicamentoAdapter
        extends RecyclerView.Adapter<VisualizarMedicamentoAdapter.ViewHolder> {

    public interface OnItemAction {
        void onEdit(int position);
        void onDelete(int position);
    }

    private final List<Medicamento> lista;
    private final OnItemAction listener;

    public VisualizarMedicamentoAdapter(List<Medicamento> lista, OnItemAction listener) {
        this.lista = lista;
        this.listener = listener;
    }

    @NonNull @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_visualizar_medicamento, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Medicamento m = lista.get(position);
        holder.tvNome.setText(m.getNome());
        holder.tvDosagem.setText("Dosagem: " + m.getDosagem());
        holder.tvHorario.setText("Horário: " + m.getHorario());

        holder.btnEditar.setOnClickListener(v -> listener.onEdit(position));
        holder.btnExcluir.setOnClickListener(v -> listener.onDelete(position));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNome, tvDosagem, tvHorario;
        ImageButton btnEditar, btnExcluir;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNome    = itemView.findViewById(R.id.tvItemNome);
            tvDosagem = itemView.findViewById(R.id.tvItemDosagem);
            tvHorario = itemView.findViewById(R.id.tvItemHorario);
            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnExcluir= itemView.findViewById(R.id.btnExcluir);
        }
    }
}
