package org.izv.aad.aadbbddcarmelo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static org.izv.aad.aadbbddcarmelo.MainActivity.LOG;

public class GestorContacto {
    private Ayudante abd;
    private SQLiteDatabase bd;

    public GestorContacto(Context c){
        this(c, true);
    }

    public GestorContacto(Context c, boolean write){
        Log.v(LOG, "constructor gestor");
        this.abd = new Ayudante(c);
        if (write){
            bd = abd.getWritableDatabase();
        }else{
            bd = abd.getReadableDatabase();
        }
    }

    public void cerrar(){
     abd.close();
    }

    public long insertar(Contacto c){
        return bd.insert(Contrato.TablaContacto.TABLE_NAME, null,
                Utilidades.contentValues(c));
    }

    public int eliminar(long id){
        String condicion = Contrato.TablaContacto._ID + " = ?";
        String[] argumentos = {id + ""};
        return bd.delete(Contrato.TablaContacto.TABLE_NAME, condicion, argumentos);

    }

    public int eliminar(Contacto c){
        //String condicion = Contrato.TablaContacto._ID + " = ?";
        //String[] argumentos = {c.getId() + ""};
        //return bd.delete(Contrato.TablaContacto.TABLE_NAME, condicion, argumentos);
        return eliminar(c.getId());
    }

    public int eliminar (String nombre){
        String condicion = Contrato.TablaContacto.COLUMN_NAME_NOMBRE + " = ?";
        String[] argumentos = {nombre};
        return bd.delete(Contrato.TablaContacto.TABLE_NAME, condicion, argumentos);
    }

    public int editar(Contacto c){
        return bd.update(Contrato.TablaContacto.TABLE_NAME,
                Utilidades.contentValues(c),
                Contrato.TablaContacto._ID + "= ?",
                new String[]{c.getId() + ""});
    }
    // no recomendado
    public int editar2(Contacto c){
        return bd.update(Contrato.TablaContacto.TABLE_NAME,
                Utilidades.contentValues(c),
                Contrato.TablaContacto._ID + "= " + c.getId(),
                null);
    }

    public Cursor getCursor(String condicion, String[] argumentos) {
        return bd.query(Contrato.TablaContacto.TABLE_NAME,
                null,
                condicion,
                argumentos,
                null,
                null,
                Contrato.TablaContacto.COLUMN_NAME_NOMBRE + " DESC");

    }

    public Cursor getCursor(){
        return getCursor(null, null);
    }

    public static Contacto getContacto(Cursor c){
        Contacto contacto = new Contacto();
        contacto.setId(c.getLong(c.getColumnIndex(Contrato.TablaContacto._ID)));
        contacto.setNombre(c.getString(c.getColumnIndex(Contrato.TablaContacto.COLUMN_NAME_NOMBRE)));
        contacto.setApellidos(c.getString(c.getColumnIndex(Contrato.TablaContacto.COLUMN_NAME_APELLIDOS)));
        contacto.setTelefono(c.getString(c.getColumnIndex(Contrato.TablaContacto.COLUMN_NAME_TELEFONO)));
        return contacto;
    }

    public Contacto getContacto(long id){
        Contacto c = null;
        List<Contacto> contactos = getContactos(Contrato.TablaContacto._ID + " = ?", new String[]{id + ""});
        if(contactos.size()>0){
            c = contactos.get(0);
        }
        return c;
    }

    public List<Contacto> getContactos(String condicion, String[] argumentos){
        List<Contacto> todos = new ArrayList<>();
        Cursor cursor = getCursor(condicion, argumentos);
        while(cursor.moveToNext()){
            todos.add(getContacto(cursor));
        }
        cursor.close();
        return todos;
    }

    public List<Contacto> getContactos(String condicion){
        List<Contacto> todos = new ArrayList<>();
        Cursor cursor = getCursor(condicion, null);
        cursor.moveToFirst();
        while(cursor.moveToNext()){
            todos.add(GestorContacto.getContacto(cursor));
        }
        cursor.close();
        return todos;
    }

    public List<Contacto> getContactos(){
        return getContactos(null, null);
    }
}
