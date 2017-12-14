package com.example.marit.trb3_dcc196;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CadastroTarefa extends AppCompatActivity {
    private Button btnCadastrarTarefa;
    private EditText edtTitulo;
    private EditText edtDescricao;
    private Spinner spEstado;
    private ArrayList<String> estados = new ArrayList<>();
    private ArrayList<Integer> graus = new ArrayList<>();
    private String estado;
    private String grau;
    private Spinner spGraus;
    private EditText edtTag1;
    private EditText edtTag2;
    private EditText edtTag3;
    private EditText edtTag4;
    private List<Integer> tagsID = new ArrayList<>();
    TagDbHelper tagHelper;
    private Button btnTag;
    private Button btnMain;
    TarefaDbHelper tarefaHelper;
    AuxTagTarefa auxHelper;
    int id;
    Tag tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_tarefa);
        tagHelper = new TagDbHelper(getApplicationContext());
        tarefaHelper = new TarefaDbHelper(getApplicationContext());
        auxHelper = new AuxTagTarefa(getApplicationContext());

        edtTitulo = (EditText) findViewById(R.id.edtTitulo);
        edtDescricao = (EditText) findViewById(R.id.edtDescricao);
        btnCadastrarTarefa = (Button) findViewById(R.id.btnCadastrarTarefa);
        spEstado = (Spinner) findViewById(R.id.spEstados);
        spGraus = (Spinner) findViewById(R.id.spGraus);
        btnTag = (Button) findViewById(R.id.btnTag);
        btnMain = (Button) findViewById(R.id.btnMain);

        estados.add("A fazer");
        estados.add("Em execução");
        estados.add("Bloqueada");
        estados.add("Concluída");

        Intent data = new Intent();
        String t = data.getStringExtra("tag");
        tag = new Tag(t);
        //id = tagHelper.inserirTag(tag);
        //tagsID.add(id);

        for(int i = 1; i < 6;i++){
            graus.add(i);
        }

        final ArrayAdapter<String> adaptador = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                estados
        );
        spEstado.setAdapter(adaptador);

        final ArrayAdapter<Integer> adaptadorGrau = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                graus
        );
        spGraus.setAdapter(adaptadorGrau);

        //Método do Spinner para capturar o item selecionado
        spEstado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id) {
                //pega estado pela posição
                estado = parent.getItemAtPosition(posicao).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spGraus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id) {
                //pega grau pela posição
                grau = parent.getItemAtPosition(posicao).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnCadastrarTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titulo = edtTitulo.getText().toString();
                String descricao = edtDescricao.getText().toString();
                Toast.makeText(getApplicationContext(),"Tarefa cadastrada com sucesso!", Toast.LENGTH_SHORT).show();

                Tarefa t = new Tarefa();
                t.setTitulo(titulo);
                t.setDescricao(descricao);
                t.setGrauDificuldade(Integer.valueOf(grau));
                t.setEstado(estado);
                t.insereTag(tag);
                tag.insereTarefa(t);
                int id = tarefaHelper.inserirTarefa(t);
                finish();
            }
        });
        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
