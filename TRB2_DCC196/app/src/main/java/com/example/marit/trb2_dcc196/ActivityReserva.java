package com.example.marit.trb2_dcc196;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ActivityReserva extends AppCompatActivity {
    private static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    private Button btnMain;
    private ListView lstLivro;
    private ListView lstUsuarios;
    private Livro escolhaLivro;
    private ArrayList<Livro> livrosCadastrados = new ArrayList<Livro>();
    private ArrayList<Usuario> usuariosCadastrados = new ArrayList<Usuario>();
    LivroDbHelper livroHelper;
    UsuarioDbHelper userhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);
        userhelper = new UsuarioDbHelper(getApplicationContext());
        livroHelper = new LivroDbHelper(getApplicationContext());

        btnMain = (Button) findViewById(R.id.btnMain);
        lstLivro = (ListView) findViewById(R.id.lstLivro);
        lstUsuarios = (ListView) findViewById(R.id.lstUsuarios);

        usuariosCadastrados = userhelper.carregaDados();
        livrosCadastrados = livroHelper.carregaDados();

        final ArrayAdapter<Livro> adaptadorP = new ArrayAdapter<Livro>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                livrosCadastrados
        );
        lstLivro.setAdapter(adaptadorP);

        final ArrayAdapter<Usuario> adaptadorU = new ArrayAdapter<Usuario>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                usuariosCadastrados
        );
        lstUsuarios.setAdapter(adaptadorU);

        lstLivro.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                escolhaLivro = adaptadorP.getItem(i);
                Toast.makeText(getApplicationContext(), "Livros selecionado com sucesso!", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        lstUsuarios.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Usuario escolhaUsuario = adaptadorU.getItem(i);
                if(escolhaUsuario.getHorarioEntrada() != null) {
                    escolhaUsuario.adicionaLivro(escolhaLivro);
                    Toast.makeText(getApplicationContext(), "Livros associado com sucesso!", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getApplicationContext(), "Usuário não registrou entrada.", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
