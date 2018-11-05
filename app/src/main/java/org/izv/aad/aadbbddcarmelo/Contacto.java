package org.izv.aad.aadbbddcarmelo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

public class Contacto implements Parcelable{
    private long id;
    private String nombre;
    private String apellidos;
    private String Telefono;

    public Contacto(){
        this(0, null, null, null);
    }

    public Contacto(long id, String nombre, String apellidos,String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        Telefono = telefono;
    }

    public Contacto(String nombre, String apellidos,String telefono) {
        this(0,nombre,apellidos,telefono);
    }

    protected Contacto(Parcel in) {
        id = in.readLong();
        nombre = in.readString();
        apellidos = in.readString();
        Telefono = in.readString();
    }

    public static final Creator<Contacto> CREATOR = new Creator<Contacto>() {
        @Override
        public Contacto createFromParcel(Parcel in) {
            return new Contacto(in);
        }

        @Override
        public Contacto[] newArray(int size) {
            return new Contacto[size];
        }
    };

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public long getId() {
        return id;
    }

    public Contacto setId(long id) {
        this.id = id;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public Contacto setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public String getTelefono() {
        return Telefono;
    }

    public Contacto setTelefono(String telefono) {
        Telefono = telefono;
        return this;
    }

    @Override
    public String toString() {
        return "Contacto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", Telefono='" + Telefono + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(nombre);
        dest.writeString(apellidos);
        dest.writeString(Telefono);
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("nombre", nombre);
        result.put("apellidos", apellidos);
        result.put("telefono", Telefono);
        return result;
    }
}
