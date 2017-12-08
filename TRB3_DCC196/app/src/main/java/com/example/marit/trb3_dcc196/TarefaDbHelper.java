package com.example.marit.trb3_dcc196;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by marit on 03/12/2017.
 */

public class TarefaDbHelper extends OrganizacaoDbHelper {
    public TarefaDbHelper(Context context) {
        super(context);
    }

    public int inserirTarefa(Tarefa u) {
        int id = 0;
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(OrganizacaoContract.Tarefa.COLUMN_NAME_TITULO, u.getTitulo());
            values.put(OrganizacaoContract.Tarefa.COLUMN_NAME_DESCRICAO, u.getDescricao());
            values.put(OrganizacaoContract.Tarefa.COLUMN_NAME_GRAU_DIFICULDADE, u.getGrauDificuldade());
            values.put(OrganizacaoContract.Tarefa.COLUMN_NAME_ESTADO, u.getEstado());
            db.insert(OrganizacaoContract.Tarefa.TABLE_NAME, null, values);
            atualizar();
            Cursor cursor = db.rawQuery(OrganizacaoContract.Tarefa.SQL_SELECT_TAREFA, null);
            id = cursor.getInt(cursor.getColumnIndex(OrganizacaoContract.Tarefa._ID));
        } catch (Exception e) {
            Log.e("InserirTarefa", e.getLocalizedMessage());
            Log.e("InserirTarefa", e.getStackTrace().toString());
        }
        return id;
    }
    public ArrayList<Tarefa> carregaDados(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(OrganizacaoContract.Tarefa.SQL_SELECT_TAREFA, null);
        ArrayList<Tarefa> usuarios = new ArrayList<>();
        while (cursor.moveToNext()) {
            Tarefa user = new Tarefa();
            user.setId(cursor.getInt(cursor.getColumnIndex(OrganizacaoContract.Tarefa._ID)));
            user.setTitulo(cursor.getString(cursor.getColumnIndex(OrganizacaoContract.Tarefa.COLUMN_NAME_TITULO)));
            user.setDescricao(cursor.getString(cursor.getColumnIndex(OrganizacaoContract.Tarefa.COLUMN_NAME_DESCRICAO)));
            user.setGrauDificuldade(cursor.getInt(cursor.getColumnIndex(OrganizacaoContract.Tarefa.COLUMN_NAME_GRAU_DIFICULDADE)));
            user.setEstado(cursor.getString(cursor.getColumnIndex(OrganizacaoContract.Tarefa.COLUMN_NAME_ESTADO)));
            usuarios.add(user);
        }
        cursor.close();

        return usuarios;
    }
    public void atualizar() {
        try {
            SQLiteDatabase db = getReadableDatabase();
            String[] visao = {
                    OrganizacaoContract.Tarefa._ID,
                    OrganizacaoContract.Tarefa.COLUMN_NAME_TITULO,
                    OrganizacaoContract.Tarefa.COLUMN_NAME_DESCRICAO,
                    OrganizacaoContract.Tarefa.COLUMN_NAME_GRAU_DIFICULDADE,
                    OrganizacaoContract.Tarefa.COLUMN_NAME_ESTADO,
            };
            String selecao = OrganizacaoContract.Tarefa.COLUMN_NAME_GRAU_DIFICULDADE + " > ?";
            String[] args = {"1"};
            String sort = OrganizacaoContract.Tarefa.COLUMN_NAME_ESTADO + " DESC";
            Cursor c = db.query(OrganizacaoContract.Tarefa.TABLE_NAME, visao, selecao, args, null, null, sort);
            c.moveToFirst();

        } catch (Exception e) {
            Log.e("BIBLIO", e.getLocalizedMessage());
            Log.e("BIBLIO", e.getStackTrace().toString());
        }
    }
    public Cursor carregaDadoById(int id){
        Cursor cursor;
        String[] campos =  {OrganizacaoContract.Tarefa._ID,OrganizacaoContract.Tarefa.COLUMN_NAME_TITULO,
                OrganizacaoContract.Tarefa.COLUMN_NAME_DESCRICAO,
                OrganizacaoContract.Tarefa.COLUMN_NAME_GRAU_DIFICULDADE,
                OrganizacaoContract.Tarefa.COLUMN_NAME_ESTADO,
        };
        String where = OrganizacaoContract.Tarefa._ID + "=" + id;
        SQLiteDatabase db = getReadableDatabase();
        cursor = db.query(OrganizacaoContract.Tarefa.TABLE_NAME,campos,where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public void alteraRegistro(int id, String titulo, String descricao, int grau, String estado){
        ContentValues valores;
        SQLiteDatabase db ;
        String where;
        db = getWritableDatabase();
        where = OrganizacaoContract.Tarefa._ID + "=" + id;

        valores = new ContentValues();
        valores.put(OrganizacaoContract.Tarefa.COLUMN_NAME_TITULO, titulo);
        valores.put(OrganizacaoContract.Tarefa.COLUMN_NAME_DESCRICAO, descricao);
        valores.put(OrganizacaoContract.Tarefa.COLUMN_NAME_GRAU_DIFICULDADE, grau);
        valores.put(OrganizacaoContract.Tarefa.COLUMN_NAME_ESTADO, estado);

        db.update(OrganizacaoContract.Tarefa.TABLE_NAME,valores,where,null);
        db.close();
    }

    public void deletaRegistro(int id){
        String where = OrganizacaoContract.Tarefa._ID + "=" + id;
        SQLiteDatabase db = getReadableDatabase();
        db.delete(OrganizacaoContract.Tarefa.TABLE_NAME,where,null);
        db.close();
    }

    public Tarefa retornarUltimo(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(OrganizacaoContract.Tarefa.SQL_SELECT_ID, null);
        if(cursor.moveToFirst()){
            Tarefa user = new Tarefa();
            user.setId(cursor.getInt(cursor.getColumnIndex(OrganizacaoContract.Tarefa._ID)));
            user.setTitulo(cursor.getString(cursor.getColumnIndex(OrganizacaoContract.Tarefa.COLUMN_NAME_TITULO)));
            user.setDescricao(cursor.getString(cursor.getColumnIndex(OrganizacaoContract.Tarefa.COLUMN_NAME_DESCRICAO)));
            user.setGrauDificuldade(cursor.getInt(cursor.getColumnIndex(OrganizacaoContract.Tarefa.COLUMN_NAME_GRAU_DIFICULDADE)));
            user.setEstado(cursor.getString(cursor.getColumnIndex(OrganizacaoContract.Tarefa.COLUMN_NAME_ESTADO)));
            cursor.close();
            return user;
        }
        return null;

    }

}

