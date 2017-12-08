package com.example.marit.trb2_dcc196;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityDetalhesUsuario extends AppCompatActivity {
    private TextView txtNome;
    private TextView txtEmail;
    private TextView txtEntrada;
    private TextView txtSaida;
    Cursor cursor;
    Integer codigo;
    UsuarioDbHelper userHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_usuario);
        userHelper = new UsuarioDbHelper(getApplicationContext());

        txtNome = (TextView) findViewById(R.id.txtTitulo);
        txtEmail = (TextView) findViewById(R.id.txtEditora);
        txtEntrada = (TextView) findViewById(R.id.txtAno);
        txtSaida = (TextView) findViewById(R.id.txtSaida);

        Intent intent = getIntent();
        Usuario user = (Usuario) intent.getSerializableExtra("usuario");

        codigo = user.getId();
        cursor = userHelper.carregaDadoById(codigo);
        if(cursor != null) {
            txtNome.setText(cursor.getString(cursor.getColumnIndexOrThrow(RegistroContract.Usuario.COLUMN_NAME_NOME)));
            txtEmail.setText(cursor.getString(cursor.getColumnIndexOrThrow(RegistroContract.Usuario.COLUMN_NAME_EMAIL)));
            txtEntrada.setText(cursor.getString(cursor.getColumnIndexOrThrow(RegistroContract.Usuario.COLUMN_NAME_HORAENTRADA)));
            txtSaida.setText(cursor.getString(cursor.getColumnIndexOrThrow(RegistroContract.Usuario.COLUMN_NAME_HORASAIDA)));
        }
    }
}
