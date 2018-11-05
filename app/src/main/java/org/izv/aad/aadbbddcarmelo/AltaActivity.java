package org.izv.aad.aadbbddcarmelo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AltaActivity extends AppCompatActivity {

    private android.widget.EditText etNombre;
    private android.widget.EditText EtApellido;
    private android.widget.EditText etTelefono;
    private android.widget.Button btAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta);
        this.btAdd = (Button) findViewById(R.id.btAdd);
        this.etTelefono = (EditText) findViewById(R.id.etTelefono);
        this.EtApellido = (EditText) findViewById(R.id.EtApellido);
        this.etNombre = (EditText) findViewById(R.id.etNombre);

        init();
    }

    private void init(){
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = etNombre.getText().toString();
                String apellidos = EtApellido.getText().toString();
                String telefono = etTelefono.getText().toString();

                if (nombre.isEmpty() || apellidos.isEmpty() || telefono.isEmpty()){
                    return;
                }else{
                    Contacto nuevoContacto = new Contacto(nombre, apellidos, telefono);
                    Intent i = new Intent();
                    i.putExtra(getString(R.string.nuevo_contacto), nuevoContacto);
                    setResult(RESULT_OK, i);
                    finish();
                }
            }
        });
    }
}
