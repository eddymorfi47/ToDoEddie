package com.eddiemartnez.app.todoeddie.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.eddiemartnez.app.todoeddie.R;
import com.eddiemartnez.app.todoeddie.database.models.ToDoTable;

public class Formulario extends AppCompatActivity {

    private TextView lblNombre;
    private TextView lblActividad;

    private EditText txtNombre;
    private EditText txtActividad;
    private Button btnGuardar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        lblNombre = findViewById(R.id.lblNombre);
        lblActividad = findViewById(R.id.lblActividad);
        txtActividad = findViewById(R.id.txtActividad);
        txtNombre = findViewById(R.id.txtNombre);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar();
            }
        });
    }

    private boolean validación (){
        boolean send =true;
        if (txtNombre.getText().toString().isEmpty()){
            return send;
        }
        if (txtActividad.getText().toString().isEmpty()){
            send = false;
        }

        return  send;
    }
    private void guardar(){
        if (validación()){
            ToDoTable registro = new ToDoTable();
            registro.nombre = txtNombre.getText().toString();
            registro.actividad =txtActividad.getText().toString();
            registro.save();
            finish();
        }
        else{
            Toast.makeText(this,getResources().getString(R.string.error_valid),Toast.LENGTH_LONG).show();
        }
    }
}
