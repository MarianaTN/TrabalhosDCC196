package com.example.marit.trb2_dcc196;

/**
 * Created by marit on 01/12/2017.
 */
import android.provider.BaseColumns;

public class RegistroContract {
    public static final String TEXT_TYPE = " VARCHAR";
    public static final String INT_TYPE = " INTEGER";
    public static final String SEP = ",";
    public static final String SQL_CREATE_LIVRO = "CREATE TABLE " + Livro.TABLE_NAME + " (" +
            Livro._ID + INT_TYPE +" PRIMARY KEY AUTOINCREMENT" + SEP +
            Livro.COLUMN_NAME_TITULO + TEXT_TYPE + SEP +
            Livro.COLUMN_NAME_EDITORA + TEXT_TYPE + SEP +
            Livro.COLUMN_NAME_ANO + INT_TYPE + " )";
    public static final String SQL_DROP_LIVRO = "DROP TABLE IF EXISTS " + Livro.TABLE_NAME;

    public static final String SQL_CREATE_USUARIO = "CREATE TABLE " + Usuario.TABLE_NAME + " (" +
            Usuario._ID + INT_TYPE +" PRIMARY KEY AUTOINCREMENT" + SEP +
            Usuario.COLUMN_NAME_NOME + TEXT_TYPE + SEP +
            Usuario.COLUMN_NAME_EMAIL + TEXT_TYPE + SEP +
            Usuario.COLUMN_NAME_HORAENTRADA + TEXT_TYPE + SEP +
            Usuario.COLUMN_NAME_HORASAIDA + TEXT_TYPE + " )";
    public static final String SQL_DROP_USUARIO = "DROP TABLE IF EXISTS " + Usuario.TABLE_NAME;

    public RegistroContract() {
    }

    public static final class Livro implements BaseColumns {
        public static final String TABLE_NAME = "livro";
        public static final String COLUMN_NAME_TITULO = "titulo";
        public static final String COLUMN_NAME_EDITORA = "editora";
        public static final String COLUMN_NAME_ANO = "ano";

        static final String SQL_SELECT_LIVRO = "SELECT " +
                Livro._ID + SEP +
                Livro.COLUMN_NAME_TITULO + SEP +
                Livro.COLUMN_NAME_EDITORA + SEP +
                Livro.COLUMN_NAME_ANO +
                " FROM "+ Livro.TABLE_NAME;
    }

    public static final class Usuario implements BaseColumns{
        public static final String TABLE_NAME = "usuario";
        public static final String COLUMN_NAME_NOME = "nome";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_HORAENTRADA = "horarioEntrada";
        public static final String COLUMN_NAME_HORASAIDA = "horarioSaida";

        public static String SQL_SELECT_USER = "SELECT " +
                Usuario._ID + SEP +
                Usuario.COLUMN_NAME_NOME + SEP +
                Usuario.COLUMN_NAME_EMAIL + SEP +
                Usuario.COLUMN_NAME_HORAENTRADA + SEP +
                Usuario.COLUMN_NAME_HORASAIDA +
                " FROM "+ Usuario.TABLE_NAME;
    }
}