package com.example.prova;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Ajusta padding considerando as barras de sistema
        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main),
                (view, insets) -> {
                    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    view.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );
                    return insets;
                }
        );

        // Botão para abrir a tela de Cadastro
        Button btnCadastrar = findViewById(R.id.buttonCadastrarMedicamento);
        btnCadastrar.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, CadastrarMedicamentoActivity.class))
        );

        // Botão para abrir a tela de Visualização
        Button btnVisualizar = findViewById(R.id.buttonVisualizarMedicamentos);
        btnVisualizar.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, VisualizarMedicamentoActivity.class))
        );
        findViewById(R.id.buttonMarcarComoTomado)
                .setOnClickListener(v ->
                        startActivity(new Intent(this, MarcarTomadoActivity.class))
                );
    }
}
