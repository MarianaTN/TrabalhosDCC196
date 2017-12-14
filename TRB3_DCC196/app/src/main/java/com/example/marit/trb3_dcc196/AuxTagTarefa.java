package com.example.marit.trb3_dcc196;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marit on 07/12/2017.
 */

public class AuxTagTarefa extends OrganizacaoDbHelper {
    public AuxTagTarefa(Context context) {
        super(context);
    }

    public void inserirTagTarefa(Tarefa tarefa,Tag tag) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(OrganizacaoContract.AuxTagTarefa.COLUMN_NAME_ID_TAREFA, tarefa.getId());
            values.put(OrganizacaoContract.AuxTagTarefa.COLUMN_NAME_ID_TAG, tag.getId());
            db.insert(OrganizacaoContract.AuxTagTarefa.TABLE_NAME, null, values);
        } catch (Exception e) {
            Log.e("InserirTagTarefa", e.getLocalizedMessage());
            Log.e("InserirTagTarefa", e.getStackTrace().toString());
        }
    }
}
