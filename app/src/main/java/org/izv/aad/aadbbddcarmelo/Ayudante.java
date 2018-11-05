package org.izv.aad.aadbbddcarmelo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static org.izv.aad.aadbbddcarmelo.MainActivity.LOG;

public class Ayudante extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "contacto2.sqlite";
    private static final int VERSION = 3;

    public Ayudante(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        Log.v(LOG, "constructor ayudante");

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.v(LOG, "Oncreate BaseDatos");
        db.execSQL(Contrato.TablaContacto.SQL_CREATE_CONTACTO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.v(LOG, "Onupgrade BaseDatos");
        if (oldVersion == 1 && newVersion == 2){
            makeMigration1_2(db);
        }else if(oldVersion == 2 && newVersion == 3){
            makeMigration2_3(db);
        }
        db.execSQL(Contrato.TablaContacto.SQL_DROP_CONTACTO);
        onCreate(db);
    }

    private void makeMigration1_2(SQLiteDatabase db) {
        String sql;
        sql = "alter table " + Contrato.TablaContacto.TABLE_NAME + " rename to oldtable";
        db.execSQL(sql);
        onCreate(db);
        sql = "INSERT INTO " + Contrato.TablaContacto.TABLE_NAME + "(" +
                Contrato.TablaContacto._ID + "," +
                Contrato.TablaContacto.COLUMN_NAME_NOMBRE + "," +
                Contrato.TablaContacto.COLUMN_NAME_TELEFONO + ") " +
                "SELECT " + Contrato.TablaContacto._ID + "," +
                Contrato.TablaContacto.COLUMN_NAME_NOMBRE + "," +
                Contrato.TablaContacto.COLUMN_NAME_TELEFONO +
                " FROM oldtable;";
        db.execSQL(sql);
        //sql = "borrar datos";
        db.execSQL(sql);
    }
    private void makeMigration2_3(SQLiteDatabase db) {
        String sql;
        sql = "alter table " + Contrato.TablaContacto.TABLE_NAME + " rename to oldtable2";
        db.execSQL(sql);
        onCreate(db);
        sql = "INSERT INTO " + Contrato.TablaContacto.TABLE_NAME + "(" +
                Contrato.TablaContacto._ID + "," +
                Contrato.TablaContacto.COLUMN_NAME_NOMBRE + "," +
                Contrato.TablaContacto.COLUMN_NAME_TELEFONO + ") " +
                "SELECT " + Contrato.TablaContacto._ID + "," +
                Contrato.TablaContacto.COLUMN_NAME_NOMBRE + "," +
                Contrato.TablaContacto.COLUMN_NAME_TELEFONO +
                " FROM oldtable2;";
        db.execSQL(sql);
        db.execSQL("drop table oldtable2");
    }

}
