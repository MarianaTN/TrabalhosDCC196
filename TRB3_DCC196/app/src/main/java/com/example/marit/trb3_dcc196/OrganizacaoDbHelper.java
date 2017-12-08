package com.example.marit.trb3_dcc196;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by marit on 03/12/2017.
 */

public class OrganizacaoDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "Oraganizacao.db";

    public OrganizacaoDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(OrganizacaoContract.SQL_CREATE_TAREFA);
        sqLiteDatabase.execSQL(OrganizacaoContract.SQL_CREATE_TAG);
        sqLiteDatabase.execSQL(OrganizacaoContract.SQL_CREATE_TAGTAREFA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(OrganizacaoContract.SQL_DROP_TAREFA);
        sqLiteDatabase.execSQL(OrganizacaoContract.SQL_DROP_TAG);
        sqLiteDatabase.execSQL(OrganizacaoContract.SQL_DROP_TAGTAREFA);
        onCreate(sqLiteDatabase);
    }
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion,newVersion);
    }
}
