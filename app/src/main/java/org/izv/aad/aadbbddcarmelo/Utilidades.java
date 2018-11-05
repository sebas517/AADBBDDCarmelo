package org.izv.aad.aadbbddcarmelo;

import android.content.ContentValues;

public class Utilidades {

    public static ContentValues contentValues(Contacto contacto){
        ContentValues contentValues = new ContentValues();
        //contentValues.put(Contrato.TablaContacto._ID, contacto.getId());
        contentValues.put(Contrato.TablaContacto.COLUMN_NAME_NOMBRE, contacto.getNombre());
        contentValues.put(Contrato.TablaContacto.COLUMN_NAME_APELLIDOS, contacto.getApellidos());
        contentValues.put(Contrato.TablaContacto.COLUMN_NAME_TELEFONO, contacto.getTelefono());
        return contentValues;
    }

}
