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

    public void inserirTagTarefa(int id, List<Integer> tagsIds) {
        int i =0;
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            while(!tagsIds.isEmpty()) {
                values.put(OrganizacaoContract.AuxTagTarefa.COLUMN_NAME_ID_TAREFA, id);
                values.put(OrganizacaoContract.AuxTagTarefa.COLUMN_NAME_ID_TAG, tagsIds.get(i).toString());
                db.insert(OrganizacaoContract.AuxTagTarefa.TABLE_NAME, null, values);
                i++;
            }
        } catch (Exception e) {
            Log.e("InserirUser", e.getLocalizedMessage());
            Log.e("InserirUser", e.getStackTrace().toString());
        }
    }
}
