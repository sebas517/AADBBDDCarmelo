package org.izv.aad.aadbbddcarmelo;

import android.provider.BaseColumns;

public class Contrato {

    private Contrato() {}

    public static class TablaContacto implements BaseColumns {
        public static final String TABLE_NAME = "contacto";
        public static final String COLUMN_NAME_NOMBRE = "nombre";
        public static final String COLUMN_NAME_APELLIDOS = "apellidos";
        public static final String COLUMN_NAME_TELEFONO = "telefono";

        public static final String SQL_CREATE_CONTACTO_v1 =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY," +
                        COLUMN_NAME_NOMBRE + " TEXT," +
                        COLUMN_NAME_TELEFONO + " TEXT)";

        public static final String SQL_CREATE_CONTACTO =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY," +
                        COLUMN_NAME_NOMBRE + " TEXT," +
                        COLUMN_NAME_APELLIDOS + " TEXT," +
                        COLUMN_NAME_TELEFONO + " TEXT," +
                        "unique("+COLUMN_NAME_NOMBRE+ ","+COLUMN_NAME_APELLIDOS+ "))";

        public static final String SQL_DROP_CONTACTO =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static class  tablaOtra implements BaseColumns{

    }
}
