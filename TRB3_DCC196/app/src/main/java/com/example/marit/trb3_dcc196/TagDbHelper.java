package com.example.marit.trb3_dcc196;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marit on 07/12/2017.
 */

public class TagDbHelper extends OrganizacaoDbHelper {
    long id;

    public TagDbHelper(Context context) {
        super(context);
    }
    public long inserirTag(Tag u) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(OrganizacaoContract.Tag.COLUMN_NAME_TEXTO, u.getValor());
            id = db.insert(OrganizacaoContract.Tag.TABLE_NAME, null, values);
            atualizar();
        } catch (Exception e) {
            Log.e("InserirTag", e.getLocalizedMessage());
            Log.e("InserirTag", e.getStackTrace().toString());
        }
        return id;
    }

    public void atualizar() {
        try {
            SQLiteDatabase db = getReadableDatabase();
            String[] visao = {
                    OrganizacaoContract.Tag._ID,
                    OrganizacaoContract.Tag.COLUMN_NAME_TEXTO
            };
            String selecao = OrganizacaoContract.Tag.COLUMN_NAME_TEXTO + " > ?";
            String[] args = {"A"};
            String sort = OrganizacaoContract.Tag._ID + " DESC";
            Cursor c = db.query(OrganizacaoContract.Tag.TABLE_NAME, visao, selecao, args, null, null, sort);
            c.moveToFirst();

        } catch (Exception e) {
            Log.e("BIBLIO", e.getLocalizedMessage());
            Log.e("BIBLIO", e.getStackTrace().toString());
        }
    }
    public ArrayList<Tag> carregaDados(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(OrganizacaoContract.Tag.SQL_SELECT_TAG, null);
        ArrayList<Tag> usuarios = new ArrayList<>();
        while (cursor.moveToNext()) {
            Tag user = new Tag();
            user.setId(cursor.getInt(cursor.getColumnIndex(OrganizacaoContract.Tag._ID)));
            user.setValor(cursor.getString(cursor.getColumnIndex(OrganizacaoContract.Tag.COLUMN_NAME_TEXTO)));
            usuarios.add(user);
        }
        cursor.close();

        return usuarios;
    }
}
