package com.example.marit.trb2_dcc196;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by marit on 01/12/2017.
 */

public class LivroDbHelper extends RegistroDbHelper {

    public LivroDbHelper(Context context) {
        super(context);
    }

    public void inserirLivro(Livro l) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(RegistroContract.Livro.COLUMN_NAME_TITULO, l.getTitulo());
            values.put(RegistroContract.Livro.COLUMN_NAME_EDITORA, l.getEditora());
            values.put(RegistroContract.Livro.COLUMN_NAME_ANO, l.getAno());
            db.insert(RegistroContract.Livro.TABLE_NAME, null, values);
            atualizar();
        } catch (Exception e) {
            Log.e("InserirLivro", e.getLocalizedMessage());
            Log.e("InserirLivro", e.getStackTrace().toString());
        }
    }

    public void atualizar() {
        try {
            SQLiteDatabase db = getReadableDatabase();
            String[] visao = {
                    RegistroContract.Livro._ID,
                    RegistroContract.Livro.COLUMN_NAME_TITULO,
                    RegistroContract.Livro.COLUMN_NAME_EDITORA,
                    RegistroContract.Livro.COLUMN_NAME_ANO,
            };
            String selecao = RegistroContract.Livro.COLUMN_NAME_TITULO + " > ?";
            String[] args = {"A"};
            String sort = RegistroContract.Livro._ID + " DESC";
            Cursor c = db.query(RegistroContract.Livro.TABLE_NAME, visao, selecao, args, null, null, sort);
            c.moveToFirst();

        } catch (Exception e) {
            Log.e("BIBLIO", e.getLocalizedMessage());
            Log.e("BIBLIO", e.getStackTrace().toString());
        }
    }

    public ArrayList<Livro> carregaDados() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(RegistroContract.Livro.SQL_SELECT_LIVRO, null);
        ArrayList<Livro> livros = new ArrayList<>();
        while (cursor.moveToNext()) {
            Livro livro = new Livro();
            livro.setId(cursor.getInt(cursor.getColumnIndex(RegistroContract.Livro._ID)));
            livro.setTitulo(cursor.getString(cursor.getColumnIndex(RegistroContract.Livro.COLUMN_NAME_TITULO)));
            livro.setEditora(cursor.getString(cursor.getColumnIndex(RegistroContract.Livro.COLUMN_NAME_EDITORA)));
            livro.setAno(cursor.getInt(cursor.getColumnIndex(RegistroContract.Livro.COLUMN_NAME_ANO)));
            livros.add(livro);
        }
        cursor.close();

        return livros;
    }
    public Cursor carregaDadoById(int id){
        Cursor cursor;
        String[] campos =  {RegistroContract.Livro._ID,
                RegistroContract.Livro.COLUMN_NAME_TITULO,
                RegistroContract.Livro.COLUMN_NAME_EDITORA,
                RegistroContract.Livro.COLUMN_NAME_ANO
        };
        String where = RegistroContract.Livro._ID + "=" + id;
        SQLiteDatabase db = getReadableDatabase();
        cursor = db.query(RegistroContract.Livro.TABLE_NAME,campos,where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
}
