package com.example.marit.trb3_dcc196;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button btnCadastroTarefa;
    private ArrayList<Tarefa> tarefas = new ArrayList<>();
    private ListView lstTarefas;
    private static final int PEDE_TAREFA = 1;
    TarefaDbHelper tarefaHelper;
    Cursor cursor;
    ArrayAdapter<Tarefa> adaptador;
    ArrayList<Tag> tags = new ArrayList<Tag>();
    private Button btnTag;
    private Button btnAssociar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tarefaHelper = new TarefaDbHelper(getApplicationContext());

        btnCadastroTarefa = (Button) findViewById(R.id.btnCadastroTarefa);
        lstTarefas = (ListView) findViewById(R.id.lstTarefas);
        btnTag = (Button) findViewById(R.id.btnTag);
        btnAssociar = (Button) findViewById(R.id.btnAssociar);

        tarefas = tarefaHelper.carregaDados();
       // Tarefa t = tarefaHelper.retornarUltimo();
        //tarefas.add(t);

        // Recebe o ArrayList de tags
        tags = (ArrayList<Tag>) getIntent().getSerializableExtra("tags");

        final ArrayAdapter<Tarefa> adaptador = new ArrayAdapter<>(
                getBaseContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                tarefas
        );

        lstTarefas.setAdapter(adaptador);

        lstTarefas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Tarefa tarefa = adaptador.getItem(i);
                if (tarefa != null) {
                    Intent intent = new Intent(MainActivity.this, EditarTarefa.class);
                    intent.putExtra("tarefa", tarefa);
                   // intent.putExtra("tags", tags);
                    startActivity(intent);
                }
            }
        });
        btnCadastroTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pedirtarefa = new Intent(MainActivity.this, CadastroTarefa.class);
                startActivity(pedirtarefa);
            }
        });
        btnTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityCadastroTag.class);
                startActivity(intent);
            }
        });
        btnAssociar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AssociarTagTarefa.class);
                startActivity(intent);
            }
        });
    }

}
