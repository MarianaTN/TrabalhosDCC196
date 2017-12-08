package com.example.marit.trb2_dcc196;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class ActivityUsuario extends AppCompatActivity {
    private Button btnCadastroAux;
    private EditText txtNome;
    private EditText txtEmail;
    private ArrayList<Usuario> usuarios = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        btnCadastroAux = (Button) findViewById(R.id.btnCadastroAux);
        txtNome = (EditText) findViewById(R.id.txtTitulo);
        txtEmail = (EditText) findViewById(R.id.txtEditora);

        btnCadastroAux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = txtNome.getText().toString();
                String email = txtEmail.getText().toString();
                Toast.makeText(getApplicationContext(),"Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra("nome",nome);
                intent.putExtra("email",email);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

}