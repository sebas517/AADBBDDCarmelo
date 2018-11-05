package org.izv.aad.aadbbddcarmelo;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder>{



    public interface OnItemClickListener{
        public void onClick(ViewHolder holder, long id);
    }

    private OnItemClickListener oyente;
    private Cursor cursor;

    public Adaptador(OnItemClickListener oyente, Cursor cursor) {
        this.oyente = oyente;
        this.cursor = cursor;
    }

    public void swapCursor(Cursor nuevoCursor){
        if (nuevoCursor !=null){
            cursor = nuevoCursor;
            notifyDataSetChanged();
        }
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        cursor.moveToPosition(i);

        Contacto c = GestorContacto.getContacto(cursor);
        viewHolder.tvNombre.setText(c.getNombre()+ " " + c.getApellidos());
        viewHolder.tvTelefono.setText(c.getTelefono());

    }

    @Override
    public int getItemCount() {
        int c = 0;
        if (cursor != null) {
            c = cursor.getCount();
        }
        return c;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Referencias UI
        public TextView tvNombre;
        public TextView tvTelefono;
        public ImageView ivFoto;

        public ViewHolder(View v) {
            super(v);
            tvNombre = v.findViewById(R.id.tvNombre);
            tvTelefono = v.findViewById(R.id.tvTelefono);
            ivFoto = v.findViewById(R.id.imageView);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            long id = 0;
            if (cursor != null) {
                if (cursor.moveToPosition(getAdapterPosition())) {
                    Contacto c = GestorContacto.getContacto(cursor);
                    id = c.getId();
                } else {
                    id = -1;
                }
            } else {
                id = -1;
            }
            oyente.onClick(this, id);
        }
    }
}
