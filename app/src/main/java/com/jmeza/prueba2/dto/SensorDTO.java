package com.jmeza.prueba2.dto;

public class SensorDTO {

    private int id;
    private String tipoSensor;
    private String valor;
    private String fecha;
    private String hora;
    private String observacion;

    public SensorDTO() { }

    public SensorDTO(int id, String tipoSensor, String valor, String observacion) {
        this.id = id;
        this.tipoSensor = tipoSensor;
        this.valor = valor;
        this.observacion = observacion;
    }

    public SensorDTO(int id, String tipoSensor, String valor, String fecha, String hora, String observacion) {
        this.id = id;
        this.tipoSensor = tipoSensor;
        this.valor = valor;
        this.fecha = fecha;
        this.hora = hora;
        this.observacion = observacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoSensor() {
        return tipoSensor;
    }

    public void setTipoSensor(String tipoSensor) {
        this.tipoSensor = tipoSensor;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}
