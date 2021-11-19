package com.jmeza.prueba2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jmeza.prueba2.helper.BaseDeDatosHelper;

public class ActualizarSensorActivity extends AppCompatActivity {

    EditText tipoSensorInput, valorSensorInput, observacionSensorInput;
    String id, tipo, valor, observasion;
    Button actualizarSensorboton;
    BaseDeDatosHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_sensor);
        myDb = new BaseDeDatosHelper(ActualizarSensorActivity.this);
        tipoSensorInput = findViewById(R.id.tipo_sensor_input_update);
        valorSensorInput = findViewById(R.id.valor_sensor_input_update);
        observacionSensorInput = findViewById(R.id.observacion_sensor_input_update);
        actualizarSensorboton = findViewById(R.id.actualizar_sensor_button);

        actualizarSensorboton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                observacionSensorInput = findViewById(R.id.observacion_sensor_input_update);
                myDb.actualizarObservacionEnSensor(id, observacionSensorInput.getText().toString() );
            }
        });
        obtenerYMostrarInformacion();
    }

    void obtenerYMostrarInformacion(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("tipoSensor")
                && getIntent().hasExtra("valorSensor") && getIntent().hasExtra("observacion")){
            id = getIntent().getStringExtra("id");
            tipo = getIntent().getStringExtra("tipoSensor");
            valor = getIntent().getStringExtra("valorSensor");
            observasion = getIntent().getStringExtra("observacion");

            tipoSensorInput.setText(tipo);
            valorSensorInput.setText(valor);
            observacionSensorInput.setText(observasion);
        }else {
            Toast.makeText(this, "No se encontraron los datos", Toast.LENGTH_SHORT).show();
        }
    }
    public void eliminarRegistro(View view){
        AlertDialog.Builder alerta = new AlertDialog.Builder(ActualizarSensorActivity.this);
        alerta.setMessage("Esta seguro que quiere eleminar el registro?").setTitle("Eliminar");

        alerta.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                myDb.eliminarSensor(id);
            }
        });

        alerta.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog dialogo = alerta.create();
        dialogo.show();
    }
}