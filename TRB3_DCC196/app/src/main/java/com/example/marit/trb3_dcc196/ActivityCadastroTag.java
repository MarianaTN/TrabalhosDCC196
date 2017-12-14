package com.example.marit.trb3_dcc196;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityCadastroTag extends AppCompatActivity {
    private EditText edtTag;
    TagDbHelper tagHelper;
    private Button btnCadastroTag;
    private Button btnMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_tag);
        tagHelper = new TagDbHelper(getApplicationContext());

        edtTag = (EditText) findViewById(R.id.edtTag);
        btnCadastroTag = (Button) findViewById(R.id.btnCadastroTag);
        btnMain = (Button) findViewById(R.id.btnMain);

        btnCadastroTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tag = edtTag.getText().toString();
                Tag t = new Tag();
                t.setValor(tag);
                tagHelper.inserirTag(t);
                Toast.makeText(getApplicationContext(),"Tag cadastrada com sucesso!", Toast.LENGTH_SHORT).show();
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
