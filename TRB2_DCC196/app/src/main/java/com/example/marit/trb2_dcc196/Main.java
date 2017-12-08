package com.example.marit.trb2_dcc196;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Main extends AppCompatActivity {
    private ListView lstNomes;
    private Button btnCadastro;
    private Button btnLivro;
    private Button btnReserva;
    private Button btnListaLivros;
    private static final int PEDE_LIVRO = 0;
    private static final int PEDE_USER = 1;
    UsuarioDbHelper userHelper;
    LivroDbHelper livroHelper;
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<Livro> livros = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userHelper = new UsuarioDbHelper(getApplicationContext());
        livroHelper = new LivroDbHelper(getApplicationContext());

        lstNomes = (ListView) findViewById(R.id.lstLivros);
        btnCadastro = (Button) findViewById(R.id.btnCadastro);
        btnLivro = (Button) findViewById(R.id.btnLivro);
        btnReserva = (Button) findViewById(R.id.btnVoltar);
        btnListaLivros = (Button) findViewById(R.id.btnListaLivros);

        usuarios = userHelper.carregaDados();
        final ArrayAdapter<Usuario> adaptador = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                usuarios
        );
        lstNomes.setAdapter(adaptador);

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pedirUsuario = new Intent(Main.this, ActivityUsuario.class);
                startActivityForResult(pedirUsuario, PEDE_USER);
            }
        });
        btnLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pedirLivro = new Intent(Main.this, ActivityLivro.class);
                startActivityForResult(pedirLivro, PEDE_LIVRO);
            }
        });
        btnReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main.this, ActivityReserva.class);
                startActivity(intent);
            }
        });
        btnListaLivros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main.this, ActivityListaLivros.class);
                startActivity(intent);
            }
        });
        lstNomes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Usuario usuario = adaptador.getItem(i);
                if (usuario != null) {
                    Intent intent = new Intent(Main.this, ActivityDetalhesUsuario.class);
                    intent.putExtra("usuario", usuario);
                    startActivity(intent);
                }
            }
        });
        lstNomes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Usuario escolha = adaptador.getItem(i);
                if (escolha.getHorarioEntrada() == null) {
                    escolha.setHorarioEntrada(pegaDataHora());
                    userHelper.alteraRegistro(i,escolha.getNome(),escolha.getEmail(),escolha.getHorarioEntrada(),escolha.getHorarioSaida());
                    Toast.makeText(getApplicationContext(), "Horário de entrada registrado com sucesso!", Toast.LENGTH_SHORT).show();
                }else if(escolha.getHorarioSaida() == null){
                    escolha.setHorarioSaida(pegaDataHora());
                    userHelper.alteraRegistro(i,escolha.getNome(),escolha.getEmail(),escolha.getHorarioEntrada(),escolha.getHorarioSaida());
                    Toast.makeText(getApplicationContext(), "Horário de saida registrado com sucesso!", Toast.LENGTH_SHORT).show();
                }else if(escolha.getHorarioEntrada() != null && escolha.getHorarioSaida() != null){
                    escolha.setHorarioEntrada(null);
                    escolha.setHorarioSaida(null);
                    userHelper.alteraRegistro(i,escolha.getNome(),escolha.getEmail(),escolha.getHorarioEntrada(),escolha.getHorarioSaida());
                    Toast.makeText(getApplicationContext(), "Horário de entrada e saida excluidos com sucesso!", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK && data != null){
            switch (requestCode){
                case PEDE_LIVRO:
                    String titulo = data.getStringExtra("titulo");
                    String editora = data.getStringExtra("editora");
                    Integer ano = Integer.parseInt(data.getStringExtra("ano"));
                    Livro livro = new Livro(titulo,editora,ano);
                    livros.add(livro);
                    livroHelper.inserirLivro(livro);
                    Toast.makeText(getApplicationContext(), "Livro inserido no banco de dados com sucesso!", Toast.LENGTH_SHORT).show();
                    break;
                case PEDE_USER:
                    String nome = data.getStringExtra("nome");
                    String email = data.getStringExtra("email");
                    Usuario user = new Usuario(nome, email);
                    userHelper.inserirUsuario(user);
                    Toast.makeText(getApplicationContext(), "Usuario inserido no banco de dados com sucesso!", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
    public String pegaDataHora(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-HH:mm:ss");
        // OU
        SimpleDateFormat dateFormat_hora = new SimpleDateFormat("HH:mm:ss");

        Date data = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        Date data_atual = cal.getTime();

        String data_completa = dateFormat.format(data_atual);

        String hora_atual = dateFormat_hora.format(data_atual);

        Log.i("data_completa", data_completa);
        Log.i("data_atual", data_atual.toString());
        Log.i("hora_atual", hora_atual); // Esse é o que você quer

        return data_completa;
    }
}
