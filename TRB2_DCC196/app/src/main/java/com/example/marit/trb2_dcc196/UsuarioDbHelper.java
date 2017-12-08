package com.example.marit.trb2_dcc196;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by marit on 06/12/2017.
 */

public class UsuarioDbHelper extends RegistroDbHelper {
    public UsuarioDbHelper(Context context) {
        super(context);
    }

    public void inserirUsuario(Usuario u) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(RegistroContract.Usuario.COLUMN_NAME_NOME, u.getNome());
            values.put(RegistroContract.Usuario.COLUMN_NAME_EMAIL, u.getEmail());
            values.put(RegistroContract.Usuario.COLUMN_NAME_HORAENTRADA, u.getHorarioEntrada());
            values.put(RegistroContract.Usuario.COLUMN_NAME_HORASAIDA, u.getHorarioSaida());
            db.insert(RegistroContract.Usuario.TABLE_NAME, null, values);
            atualizar();
        } catch (Exception e) {
            Log.e("InserirUser", e.getLocalizedMessage());
            Log.e("InserirUser", e.getStackTrace().toString());
        }
    }

    public void atualizar() {
        try {
            SQLiteDatabase db = getReadableDatabase();
            String[] visao = {
                    RegistroContract.Usuario._ID,
                    RegistroContract.Usuario.COLUMN_NAME_NOME,
                    RegistroContract.Usuario.COLUMN_NAME_EMAIL,
                    RegistroContract.Usuario.COLUMN_NAME_HORAENTRADA,
                    RegistroContract.Usuario.COLUMN_NAME_HORASAIDA,
            };
            String selecao = RegistroContract.Usuario.COLUMN_NAME_NOME + " > ?";
            String[] args = {"A"};
            String sort = RegistroContract.Usuario._ID + " DESC";
            Cursor c = db.query(RegistroContract.Usuario.TABLE_NAME, visao, selecao, args, null, null, sort);
            c.moveToFirst();

        } catch (Exception e) {
            Log.e("BIBLIO", e.getLocalizedMessage());
            Log.e("BIBLIO", e.getStackTrace().toString());
        }
    }

    public ArrayList<Usuario> carregaDados() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(RegistroContract.Usuario.SQL_SELECT_USER, null);
        ArrayList<Usuario> usuarios = new ArrayList<>();
        while (cursor.moveToNext()) {
            Usuario user = new Usuario();
            user.setId(cursor.getInt(cursor.getColumnIndex(RegistroContract.Usuario._ID)));
            user.setNome(cursor.getString(cursor.getColumnIndex(RegistroContract.Usuario.COLUMN_NAME_NOME)));
            user.setEmail(cursor.getString(cursor.getColumnIndex(RegistroContract.Usuario.COLUMN_NAME_EMAIL)));
            user.setHorarioEntrada(cursor.getString(cursor.getColumnIndex(RegistroContract.Usuario.COLUMN_NAME_HORAENTRADA)));
            user.setHorarioSaida(cursor.getString(cursor.getColumnIndex(RegistroContract.Usuario.COLUMN_NAME_HORASAIDA)));
            usuarios.add(user);
        }
        cursor.close();

        return usuarios;
    }
    public void alteraRegistro(int id, String nome, String email, String horaEntrada,String horaSaida){
        ContentValues valores;
        String where;

        SQLiteDatabase db = getWritableDatabase();

        where = RegistroContract.Usuario._ID + "=" + id;

        valores = new ContentValues();
        valores.put(RegistroContract.Usuario.COLUMN_NAME_NOME, nome);
        valores.put(RegistroContract.Usuario.COLUMN_NAME_EMAIL, email);
        valores.put(RegistroContract.Usuario.COLUMN_NAME_HORAENTRADA, horaEntrada);
        valores.put(RegistroContract.Usuario.COLUMN_NAME_HORASAIDA, horaSaida);

        db.update(RegistroContract.Usuario.TABLE_NAME,valores,where,null);
        db.close();
    }

    public Cursor carregaDadoById(int id){
        Cursor cursor;
        String[] campos =  {RegistroContract.Usuario._ID,
                RegistroContract.Usuario.COLUMN_NAME_NOME,
                RegistroContract.Usuario.COLUMN_NAME_EMAIL,
                RegistroContract.Usuario.COLUMN_NAME_HORAENTRADA,
                RegistroContract.Usuario.COLUMN_NAME_HORASAIDA
        };
        String where = RegistroContract.Usuario._ID + "=" + id;
        SQLiteDatabase db = getReadableDatabase();
        cursor = db.query(RegistroContract.Usuario.TABLE_NAME,campos,where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
}