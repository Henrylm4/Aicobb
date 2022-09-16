package com.example.proyectoembebidos;

public class Control_Manual {
    private int hora;
    private int minuto;
    private Boolean Motor;
    private Boolean Luz;
    public Control_Manual(){

    }


    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    public Boolean getMotor() {
        return Motor;
    }

    public void setMotor(Boolean motor) {
        Motor = motor;
    }

    public Boolean getLuz() {
        return Luz;
    }

    public void setLuz(Boolean luz) {
        Luz = luz;
    }
}
