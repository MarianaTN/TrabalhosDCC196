package com.example.marit.trb3_dcc196;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityCadastroTag extends AppCompatActivity {
    private Button btnCadastroTag;
    private EditText edtTag;
    TagDbHelper tagHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_tag);
        tagHelper = new TagDbHelper(getApplicationContext());

        btnCadastroTag = (Button) findViewById(R.id.btnCadastroTag);
        edtTag = (EditText) findViewById(R.id.edtTag);

        btnCadastroTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tag = edtTag.getText().toString();
                Tag t = new Tag(tag);
                int id = tagHelper.inserirTag(t);
                Toast.makeText(getApplicationContext(),"Tag cadastrada com sucesso!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra("tag", tag);
                finish();
            }
        });
    }
}
