package org.izv.aad.aadbbddcarmelo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference reference;
    private static final int REQUEST_CODE = 1;
    public static String LOG = "ZZTOP";
    private GestorContacto gestor;
    private Adaptador adaptador;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case REQUEST_CODE:
                if (resultCode == RESULT_CANCELED){

                }else if (resultCode == RESULT_OK){
                    Contacto nuevoContacto = data.getParcelableExtra(getString(R.string.nuevo_contacto));
                    Long r = gestor.insertar(nuevoContacto);
                    if (r > 0){
                        nuevoContacto.setId(r);
                        adaptador.swapCursor(gestor.getCursor());
                        guardarContactoFirebase(nuevoContacto);
                    }
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        //long id = gestor.insertar(c);
        //Log.v(LOG, id + "");

        //c = gestor.getContacto(2);
        //Log.v(LOG, c.toString());



        //List<Contacto> contactos = gestor.getContactos(null, null);

        //List<Contacto> contactos = gestor.getContactos
         //       (Contrato.TablaContacto.COLUMN_NAME_NOMBRE + " like 'p%'", new String[]{"p%"});

        //for (Contacto ct: contactos) {
         //   Log.v(LOG, ct.toString());
       // }

        //c.setTelefono("612345231");
        //int r = gestor.editar(c);
        //Log.v(LOG, r +"");

        //int r = gestor.eliminar(c);
        //Log.v(LOG,r + "");

        //gestor.cerrar();


    }

    private void addActivity() {
        Intent i = new Intent(MainActivity.this, AltaActivity.class);
        startActivityForResult(i, REQUEST_CODE);
    }

    private void init(){
        Button bt = findViewById(R.id.btAdd);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addActivity();

            }
        });

        gestor = new GestorContacto(this);

        Adaptador.OnItemClickListener oyente = new Adaptador.OnItemClickListener() {
            @Override
            public void onClick(Adaptador.ViewHolder holder, long id) {
                Log.v(LOG, id + "");
                /*gestor.eliminar(id);
                adaptador.swapCursor(gestor.getCursor());*/
            }
        };

        RecyclerView rv = findViewById(R.id.rvlista);
        adaptador = new Adaptador(oyente, gestor.getCursor());
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adaptador);

        reference = FirebaseDatabase.getInstance().getReference();


    }
    private void guardarContactoFirebase(Contacto nuevoContacto){ // guarda un item en el directorio indicado
        Map<String, Object> saveItem = new HashMap<>();
        String key = reference.child("contacto").push().getKey();
        saveItem.put("/key/" + key + "/", nuevoContacto.toMap());
        reference.updateChildren(saveItem).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Log.v(LOG, "onComplete");
                }else{
                    Log.v(LOG, task.getException().toString());
                }
            }
        });
        saveItem.put("/Contacto/" + nuevoContacto.getId() + "/", nuevoContacto.toMap());
        reference.updateChildren(saveItem).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Log.v(LOG, "onComplete");
                }else{
                    Log.v(LOG, task.getException().toString());
                }
            }
        });
    }
}
