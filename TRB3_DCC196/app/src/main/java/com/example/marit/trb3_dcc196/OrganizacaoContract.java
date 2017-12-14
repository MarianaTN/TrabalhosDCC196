package com.example.marit.trb3_dcc196;

import android.provider.BaseColumns;

/**
 * Created by marit on 03/12/2017.
 */

public class OrganizacaoContract {
    public static final String TEXT_TYPE = " VARCHAR";
    public static final String INT_TYPE = " INTEGER";
    public static final String SEP = ",";
    public static final String SQL_CREATE_TAREFA = "CREATE TABLE " + Tarefa.TABLE_NAME + " (" +
            Tarefa._ID + INT_TYPE +" PRIMARY KEY AUTOINCREMENT" + SEP +
            Tarefa.COLUMN_NAME_TITULO + TEXT_TYPE + SEP +
            Tarefa.COLUMN_NAME_DESCRICAO + TEXT_TYPE + SEP +
            Tarefa.COLUMN_NAME_GRAU_DIFICULDADE + INT_TYPE + SEP +
            Tarefa.COLUMN_NAME_ESTADO + TEXT_TYPE + " )";
    public static final String SQL_DROP_TAREFA = "DROP TABLE IF EXISTS " + Tarefa.TABLE_NAME;

    public static final String SQL_CREATE_TAG = "CREATE TABLE " + Tag.TABLE_NAME + " (" +
            Tag._ID + INT_TYPE +" PRIMARY KEY AUTOINCREMENT" + SEP +
            Tag.COLUMN_NAME_TEXTO + TEXT_TYPE + " )";
    public static final String SQL_DROP_TAG = "DROP TABLE IF EXISTS " + Tag.TABLE_NAME;

    public static final String SQL_CREATE_TAGTAREFA = "CREATE TABLE " + AuxTagTarefa.TABLE_NAME + " (" +
            AuxTagTarefa._ID + INT_TYPE +" PRIMARY KEY AUTOINCREMENT" + SEP +
            AuxTagTarefa.COLUMN_NAME_ID_TAREFA + INT_TYPE + SEP +
            AuxTagTarefa.COLUMN_NAME_ID_TAG + INT_TYPE + " )";
    public static final String SQL_DROP_TAGTAREFA = "DROP TABLE IF EXISTS " + AuxTagTarefa.TABLE_NAME;

    public OrganizacaoContract() {
    }

    public static final class Tarefa implements BaseColumns {
        public static final String TABLE_NAME = "tarefa";
        public static final String COLUMN_NAME_TITULO = "titulo";
        public static final String COLUMN_NAME_DESCRICAO = "descricao";
        public static final String COLUMN_NAME_GRAU_DIFICULDADE = "grauDificuldade";
        public static final String COLUMN_NAME_ESTADO = "estado";

        static final String SQL_SELECT_TAREFA = "SELECT " +
                Tarefa._ID + SEP +
                Tarefa.COLUMN_NAME_TITULO + SEP +
                Tarefa.COLUMN_NAME_DESCRICAO + SEP +
                Tarefa.COLUMN_NAME_GRAU_DIFICULDADE + SEP +
                Tarefa.COLUMN_NAME_ESTADO +
                " FROM "+ Tarefa.TABLE_NAME;

        static final String SQL_SELECT_ID = "SELECT " +
                Tarefa._ID + SEP +
                Tarefa.COLUMN_NAME_TITULO + SEP +
                Tarefa.COLUMN_NAME_DESCRICAO + SEP +
                Tarefa.COLUMN_NAME_GRAU_DIFICULDADE + SEP +
                Tarefa.COLUMN_NAME_ESTADO +
                " FROM "+ Tarefa.TABLE_NAME + " ORDER BY " + Tarefa._ID + " DESC";
    }

    public static final class Tag implements BaseColumns{
        public static final String TABLE_NAME = "tag";
        public static final String COLUMN_NAME_TEXTO = "texto";

        static final String SQL_SELECT_TAG = "SELECT " +
                Tag._ID + SEP +
                Tag.COLUMN_NAME_TEXTO +
                " FROM "+ Tag.TABLE_NAME;
    }

    public static final class AuxTagTarefa implements BaseColumns{
        public static final String TABLE_NAME = "tagtarefa";
        public static final String COLUMN_NAME_ID_TAREFA = "tarefa_id";
        public static final String COLUMN_NAME_ID_TAG = "tag_id";
    }

}
