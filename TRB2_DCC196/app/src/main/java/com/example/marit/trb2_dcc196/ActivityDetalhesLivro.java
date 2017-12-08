package com.example.marit.trb2_dcc196;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityDetalhesLivro extends AppCompatActivity {
    private TextView txtTitulo;
    private TextView txtEditora;
    private TextView txtAno;
    Cursor cursor;
    Integer codigo;
    LivroDbHelper livroHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_livro);
        livroHelper = new LivroDbHelper(getApplicationContext());

        txtTitulo = (TextView) findViewById(R.id.txtTitulo);
        txtEditora = (TextView) findViewById(R.id.txtEditora);
        txtAno = (TextView) findViewById(R.id.txtAno);

        Intent intent = getIntent();
        Livro livro = (Livro) intent.getSerializableExtra("livro");

        codigo = livro.getId();
        cursor = livroHelper.carregaDadoById(codigo);
        if(cursor != null) {
            txtTitulo.setText(cursor.getString(cursor.getColumnIndexOrThrow(RegistroContract.Livro.COLUMN_NAME_TITULO)));
            txtEditora.setText(cursor.getString(cursor.getColumnIndexOrThrow(RegistroContract.Livro.COLUMN_NAME_EDITORA)));
            txtAno.setText(cursor.getString(cursor.getColumnIndexOrThrow(RegistroContract.Livro.COLUMN_NAME_ANO)));

        }
    }
}
