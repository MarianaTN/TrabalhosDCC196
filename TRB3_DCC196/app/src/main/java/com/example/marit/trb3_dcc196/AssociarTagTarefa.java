package com.example.marit.trb3_dcc196;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AssociarTagTarefa extends AppCompatActivity {
    private ListView lstTarefa;
    private ListView lstTag;
    TarefaDbHelper tarefaHelper;
    TagDbHelper tagHelper;
    private Button btnMain;
    private List<Tarefa> tarefasCadastradas = new ArrayList<>();
    private List<Tag> tagsCadastradas = new ArrayList<>();
    private Tarefa escolhaTarefa = new Tarefa();
    private Tag escolhaTag = new Tag();
    private Button btnAssocia;
    AuxTagTarefa aux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_associar_tag_tarefa);
        tarefaHelper = new TarefaDbHelper(getApplicationContext());
        tagHelper = new TagDbHelper(getApplicationContext());
        aux = new AuxTagTarefa(getApplicationContext());

        btnMain = (Button) findViewById(R.id.btnMain);
        lstTarefa = (ListView) findViewById(R.id.lstTarefa);
        lstTag = (ListView) findViewById(R.id.lstTag);
        btnAssocia = (Button) findViewById(R.id.btnAssocia);

        tarefasCadastradas = tarefaHelper.carregaDados();
        tagsCadastradas = tagHelper.carregaDados();

        final ArrayAdapter<Tarefa> adaptadorT = new ArrayAdapter<Tarefa>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                tarefasCadastradas
        );
        lstTarefa.setAdapter(adaptadorT);

        final ArrayAdapter<Tag> adaptadorTag = new ArrayAdapter<Tag>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                tagsCadastradas
        );
        lstTag.setAdapter(adaptadorTag);

        lstTarefa.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                escolhaTarefa = adaptadorT.getItem(i);
                Toast.makeText(getApplicationContext(), "Tarefa selecionada com sucesso!", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        lstTag.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                escolhaTag = adaptadorTag.getItem(i);
                    Toast.makeText(getApplicationContext(), "Tag selecionada com sucesso!", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnAssocia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aux.inserirTagTarefa(escolhaTarefa,escolhaTag);
                Toast.makeText(getApplicationContext(), "Tag e Tarefa associados com sucesso!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
