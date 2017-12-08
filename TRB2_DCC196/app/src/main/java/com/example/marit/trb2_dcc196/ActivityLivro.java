package com.example.marit.trb2_dcc196;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityLivro extends AppCompatActivity {
    private EditText edtTitulo;
    private EditText edtEditora;
    private EditText edtAno;
    private Button btnCadastroLivro;
    private Button btnListaLivro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livro);

        edtTitulo = (EditText) findViewById(R.id.edtTitulo);
        edtAno = (EditText) findViewById(R.id.edtAno);
        edtEditora = (EditText) findViewById(R.id.edtEditora);
        btnCadastroLivro = (Button) findViewById(R.id.btnCadastroLivro);
        btnListaLivro = (Button) findViewById(R.id.btnListaLivros);


        btnCadastroLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titulo = edtTitulo.getText().toString();
                String editora = edtEditora.getText().toString();
                String ano = edtAno.getText().toString();
                Toast.makeText(getApplicationContext(),"Livro cadastrado com sucesso!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent();
                intent.putExtra("titulo",titulo);
                intent.putExtra("editora",editora);
                intent.putExtra("ano",ano);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        btnListaLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityLivro.this, ActivityListaLivros.class);
                startActivity(intent);
            }
        });
    }
}