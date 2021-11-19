package com.jmeza.prueba2.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.style.UpdateAppearance;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.jmeza.prueba2.ActualizarSensorActivity;
import com.jmeza.prueba2.R;
import com.jmeza.prueba2.dto.SensorDTO;

import java.util.ArrayList;

public class AdaptadorSensor extends RecyclerView.Adapter<AdaptadorSensor.MyViewHolder> {

    Context context;
    ArrayList<SensorDTO> sensores;
    Activity activity;

    public AdaptadorSensor(Activity activity, Context context, ArrayList sensores) {
        this.context = context;
        this.sensores = sensores;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_sensor, parent, false);
        return new MyViewHolder(view);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SensorDTO sensor = sensores.get(position);
        holder.sensor_id_txt.setText(String.valueOf(sensor.getId()));
        holder.sensor_tipo_txt.setText(sensor.getTipoSensor());
        holder.sensor_valor_txt.setText(sensor.getValor());
        holder.cardLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ActualizarSensorActivity.class);
                intent.putExtra("id",String.valueOf(sensor.getId()));
                intent.putExtra("tipoSensor", sensor.getTipoSensor());
                intent.putExtra("valorSensor", sensor.getValor());
                intent.putExtra("observacion", sensor.getObservacion());
                activity.startActivityForResult(intent,1);
            }
        });
    }


    @Override
    public int getItemCount() {
        return sensores.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView sensor_id_txt, sensor_tipo_txt, sensor_valor_txt;
        ConstraintLayout cardLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            sensor_id_txt = itemView.findViewById(R.id.sensor_id_txt);
            sensor_tipo_txt = itemView.findViewById(R.id.sensor_tipo_txt);
            sensor_valor_txt = itemView.findViewById(R.id.sensor_valor_txt);
            cardLayout = itemView.findViewById(R.id.card_sensor_layout);
        }
    }
}
