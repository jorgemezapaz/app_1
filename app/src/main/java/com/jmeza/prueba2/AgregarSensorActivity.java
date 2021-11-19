package com.jmeza.prueba2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.jmeza.prueba2.helper.BaseDeDatosHelper;

import java.util.ArrayList;
import java.util.List;

public class AgregarSensorActivity extends AppCompatActivity {

    public static final String SELECCIONE_UN_SENSOR = "Seleccione un sensor";
    List<String> sensoresDispositivo;
    List<Sensor> deviceSensorDTOS;
    EditText tipoSensorInput, valorSensorInput, observacionSensorInput;
    Button guardarSensorboton;
    BaseDeDatosHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_sensor);
        tipoSensorInput = findViewById(R.id.tipo_sensor_input);
        valorSensorInput = findViewById(R.id.valor_sensor_input);
        observacionSensorInput = findViewById(R.id.observacion_sensor_input);
        guardarSensorboton = findViewById(R.id.guardar_sensor_button);
        myDb = new BaseDeDatosHelper(AgregarSensorActivity.this);


        //Spinner
        cargarSensoresDispositivo();
        Spinner sp = (Spinner) findViewById(R.id.spinner_sensores);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,sensoresDispositivo);
        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i > 0){
                    Sensor sensor = deviceSensorDTOS.get(i-1);
                    tipoSensorInput.setText(sensor.getName());
                    valorSensorInput.setText(String.valueOf(sensor.getPower()));
                }else{
                    tipoSensorInput.setText("");
                    valorSensorInput.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
        //Button
        guardarSensorboton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDb.agregarSensor(
                        tipoSensorInput.getText().toString(),
                        valorSensorInput.getText().toString(),
                        observacionSensorInput.getText().toString());
            }
        });
    }

    private void cargarSensoresDispositivo(){
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        deviceSensorDTOS = sensorManager.getSensorList(Sensor.TYPE_ALL);
        sensoresDispositivo = new ArrayList<>();
        sensoresDispositivo.add(SELECCIONE_UN_SENSOR);
        for (Sensor s: deviceSensorDTOS) {
            sensoresDispositivo.add(s.getName());
        }
        Log.i("Lista de sensores -> ",sensoresDispositivo.toString());
    }

}