package com.jmeza.prueba2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import com.jmeza.prueba2.adapter.AdaptadorSensor;
import com.jmeza.prueba2.dto.SensorDTO;
import com.jmeza.prueba2.helper.BaseDeDatosHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    BaseDeDatosHelper myDb;
    ArrayList<SensorDTO> sensores;

    AdaptadorSensor adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        myDb = new BaseDeDatosHelper(MainActivity.this);
        sensores = new ArrayList<>();
        cargarDatos();

        adapter = new AdaptadorSensor(MainActivity.this, MainActivity.this, sensores);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    public void agregarSensor(View view){
        Intent intent = new Intent(MainActivity.this, AgregarSensorActivity.class);
        startActivity(intent);
    }

    public void mapaView(View view){
        Intent intent = new Intent(MainActivity.this, MapaActivity.class);
        startActivity(intent);
    }

    private void cargarDatos(){
        Cursor cursor = myDb.getSensores();
        if (cursor == null){
            Toast.makeText(MainActivity.this, "No hay Datos.", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                sensores.add(
                        new SensorDTO(cursor.getInt(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(5))
                );
            }
        }
    }
}