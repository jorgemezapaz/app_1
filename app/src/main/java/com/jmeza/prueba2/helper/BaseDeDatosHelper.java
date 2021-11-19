package com.jmeza.prueba2.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.jmeza.prueba2.dto.SensorDTO;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseDeDatosHelper extends SQLiteOpenHelper {

    public static final String DD_M_YYYY_HH_MM_SS = "dd-M-yyyy hh:mm:ss";
    private Context context;
    private static final String DATABASE_NAME = "Prueba2.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "sensor";
    private static final String COLUMN_ID = "_id";
    private static final String TIPO = "tipo";
    private static final String VALOR = "valor";
    private static final String FECHA = "fecha";
    private static final String HORA = "hora";
    private static final String OBSERVACION = "observacion";

    public BaseDeDatosHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    String query = "CREATE TABLE sensor ( _id  INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "tipo TEXT," +
            "valor TEXT," +
            "fecha TEXT," +
            "hora TEXT," +
            "observacion TEXT);";
    db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS sensor;");
        onCreate(db);
    }

    public void agregarSensor(String tipo, String valor, String observacion){
        SQLiteDatabase db = this.getWritableDatabase();
        String dateTime = generarFechaYHora();
        String fecha = dateTime.split(" ")[0];
        String hora = dateTime.split(" ")[1];

        ContentValues cv = new ContentValues();
        cv.put(TIPO, tipo);
        cv.put(VALOR, valor);
        cv.put(FECHA, fecha);
        cv.put(HORA, hora);
        cv.put(OBSERVACION, observacion);

        Long result = db.insert(TABLE_NAME, null, cv);
        if (result == 1){
            Toast.makeText(context, "Fallo al guardar!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Registro guardado exitosamente", Toast.LENGTH_SHORT).show();
        }
    }

    public void actualizarObservacionEnSensor(String id, String observacion){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(OBSERVACION, observacion);
        int result = db.update(TABLE_NAME, cv, "_id= ?", new String[]{id});
        if (result == -1){
            Toast.makeText(context, "Fallo al actualizar!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Registro actualizado exitosamente", Toast.LENGTH_SHORT).show();
        }
    }

    public void eliminarSensor(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{id});
        if (result == -1){
            Toast.makeText(context, "Fallo al eliminar el registro!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Registro eliminado exitosamente", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor getSensores(){
        String query = "SELECT * FROM "+ TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return  cursor;
    }

    private String generarFechaYHora(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(DD_M_YYYY_HH_MM_SS);
        return formatter.format(date);
    }
}
