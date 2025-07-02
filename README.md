# Projeto Controle de Medicamentos

Este é um aplicativo Android desenvolvido em Java para auxiliar no controle de medicamentos. O app permite cadastrar medicamentos, visualizar, editar, excluir e marcar como tomados.

## Funcionalidades

1. **Cadastro de Medicamentos**

   * Nome
   * Dosagem
   * Horário de uso
2. **Visualização**

   * Lista todos os medicamentos cadastrados
   * Exibe nome, dosagem e horário
   * Opções de editar e excluir cada medicamento
3. **Marcar como Tomado**

   * Tela específica com checkboxes para marcar/desmarcar medicamentos
4. **Persistência Local**

   * Dados armazenados em `SharedPreferences` no formato JSON

## Tecnologias Utilizadas

* **Linguagem:** Java
* **IDE:** Android Studio
* **Framework:** Android SDK

## Estrutura de Pastas

```
app/
├── src/main/
│   ├── java/com/example/prova/
│   │   ├── MainActivity.java
│   │   ├── CadastrarMedicamentoActivity.java
│   │   ├── VisualizarMedicamentoActivity.java
│   │   ├── MarcarTomadoActivity.java
│   │   ├── Medicamento.java
│   │   ├── VisualizarMedicamentoAdapter.java
│   │   └── MarcarTomadoAdapter.java
│   └── res/
│       ├── layout/
│       │   ├── activity_main.xml
│       │   ├── activity_cadastrar_medicamento.xml
│       │   ├── activity_visualizar_medicamento.xml
│       │   ├── activity_marcar_tomado.xml
│       │   ├── item_visualizar_medicamento.xml
│       │   └── item_marcar_tomado.xml
│       └── values/
│           └── strings.xml
└── build.gradle
```

## Como Executar

1. Clone este repositório:

   ```bash
   git clone https://github.com/SEU_USUARIO/projeto-controle-medicamentos.git
   ```
2. Abra o Android Studio e selecione "Open an existing project".
3. Navegue até a pasta clonada e abra.
4. Inicie um emulador.
5. Clique em  "Run"  para instalar e iniciar o app.

