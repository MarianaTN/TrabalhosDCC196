package com.example.marit.trb2_dcc196;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ActivityListaLivros extends AppCompatActivity {
    private ListView lstLivros;
    private Button btnVoltar;
    LivroDbHelper livroHelper;
    private ArrayList<Livro> livrosCadastrados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_livros);
        livroHelper = new LivroDbHelper(getApplicationContext());

        lstLivros = (ListView) findViewById(R.id.lstLivros);
        btnVoltar = (Button) findViewById(R.id.btnVoltar);

        livrosCadastrados = livroHelper.carregaDados();
        final ArrayAdapter<Livro> adaptadorL = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                livrosCadastrados
        );
        lstLivros.setAdapter(adaptadorL);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        lstLivros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Livro livro = adaptadorL.getItem(i);
                if (livro != null) {
                    Intent intent = new Intent(ActivityListaLivros.this, ActivityDetalhesLivro.class);
                    intent.putExtra("livro", livro);
                    startActivity(intent);
                }
            }
        });
    }
}
