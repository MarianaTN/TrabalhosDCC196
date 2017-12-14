package com.example.marit.trb3_dcc196;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EditarTarefa extends AppCompatActivity {
    private Button btnEditarTarefa;
    private Button btnDeletar;
    private EditText edtTitulo;
    private EditText edtDescricao;
    private Spinner spEstado;
    private ArrayList<String> estados = new ArrayList<>();
    private ArrayList<Integer> graus = new ArrayList<>();
    private String estado;
    private String grau;
    private Spinner spGraus;
    Cursor cursor;
    Integer codigo;
    TarefaDbHelper tarefaHelper;
    private ArrayList<Tag> tags = new ArrayList<>();
    private ListView lstTags;
    TagDbHelper tagHelper;
    Tag t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_tarefa);
        tarefaHelper = new TarefaDbHelper(getApplicationContext());
        tagHelper = new TagDbHelper(getApplicationContext());

        edtTitulo = (EditText) findViewById(R.id.edtTitulo);
        edtDescricao = (EditText) findViewById(R.id.edtDescricao);
        btnEditarTarefa = (Button) findViewById(R.id.btnEditarTarefa);
        spEstado = (Spinner) findViewById(R.id.spEstados);
        spGraus = (Spinner) findViewById(R.id.spGraus);
        btnDeletar = (Button) findViewById(R.id.btnDeletar);
        lstTags = (ListView) findViewById(R.id.lstTags);


        Intent intent = getIntent();
        Tarefa tarefa = (Tarefa) intent.getSerializableExtra("tarefa");
        tags = tarefa.getTags();

        codigo = tarefa.getId();
        cursor = tarefaHelper.carregaDadoById(codigo);
        if(cursor != null) {
            edtTitulo.setText(cursor.getString(cursor.getColumnIndexOrThrow(OrganizacaoContract.Tarefa.COLUMN_NAME_TITULO)));
            edtDescricao.setText(cursor.getString(cursor.getColumnIndexOrThrow(OrganizacaoContract.Tarefa.COLUMN_NAME_DESCRICAO)));
            grau = cursor.getString(cursor.getColumnIndexOrThrow(OrganizacaoContract.Tarefa.COLUMN_NAME_GRAU_DIFICULDADE));
            estado = cursor.getString(cursor.getColumnIndexOrThrow(OrganizacaoContract.Tarefa.COLUMN_NAME_ESTADO));
        }

        //tags = tagHelper.carregaDados();
        final ArrayAdapter<Tag> adaptadorT = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                tags
        );

        lstTags.setAdapter(adaptadorT);

       estados.add(estado);
       graus.add(Integer.parseInt(grau));
       //spEstado.setSelection(3);
       switch (estado) {
            case "A fazer":
                estados.add("Em execução");
                estados.add("Bloqueada");
                estados.add("Concluída");
                break;
            case "Em execução":
                estados.add("A fazer");
                estados.add("Bloqueada");
                estados.add("Concluída");
                break;
            case "Bloqueada":
                estados.add("A fazer");
                estados.add("Em execução");
                estados.add("Concluída");
                break;
            case "Concluída":
                estados.add("A fazer");
                estados.add("Em execução");
                estados.add("Bloqueada");
                break;
       }

        switch (grau) {
            case "1":
                for(int i =2; i<6;i++)
                    graus.add(i);
                break;
            case "2":
                graus.add(1);
                graus.add(3);
                graus.add(4);
                graus.add(5);
                break;
            case "3":
                graus.add(1);
                graus.add(2);
                graus.add(4);
                graus.add(5);
                break;
            case "4":
                graus.add(1);
                graus.add(2);
                graus.add(3);
                graus.add(5);
                break;
            case "5":
                for(int i =1; i<5;i++)
                    graus.add(i);
                break;
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

        btnEditarTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titulo = edtTitulo.getText().toString();
                String descricao = edtDescricao.getText().toString();
                tarefaHelper.alteraRegistro(codigo,titulo,descricao,Integer.parseInt(grau),estado);
                Toast.makeText(getApplicationContext(),"Tarefa editada com sucesso!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EditarTarefa.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tarefaHelper.deletaRegistro(codigo);
                Intent intent = new Intent(EditarTarefa.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
