package com.example.proyectoembebidos;

public class Control {
    private String Dias;
    private String Cantidad;
    private String Peso;
    private Boolean Modo;
    public Control(){

    }

    public String getDias() {
        return Dias;
    }

    public void setDias(String dias) {
        Dias = dias;
    }

    public String getCantidad() {
        return Cantidad;
    }

    public void setCantidad(String cantidad) {
        Cantidad = cantidad;
    }

    public String getPeso() {
        return Peso;
    }

    public void setPeso(String peso) {
        Peso = peso;
    }

    public Boolean getModo() {
        return Modo;
    }

    public void setModo(Boolean modo) {
        Modo = modo;
    }
}
