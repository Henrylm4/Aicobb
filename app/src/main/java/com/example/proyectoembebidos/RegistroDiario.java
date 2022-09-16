package com.example.proyectoembebidos;

import java.util.Date;

public class RegistroDiario {
    private String uid;
    private String Fregistro;
    private String Peso;
    private String Npollos;
    private String Nmuertos;
    private String Nenfermos;
    private String Proporción;
    private String Observacion;
    private Date fechai;
    private Date fechaf;
    private String ubicación;
    private boolean ocupado;
    public RegistroDiario(){

    }

    public String getNmuertos() {
        return Nmuertos;
    }

    public String getNpollos() {
        return Npollos;
    }

    public String getFregistro() {
        return Fregistro;
    }

    public String getPeso() {
        return Peso;
    }

    public String getUid() {
        return uid;
    }

    public void setNmuertos(String nmuertos) {
        Nmuertos = nmuertos;
    }

    public void setNpollos(String npollos) {
        Npollos = npollos;
    }

    public void setFregistro(String fregistro) {
        Fregistro = fregistro;
    }

    public void setPeso(String peso) {
        this.Peso = peso;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public Date getFechaf() {
        return fechaf;
    }

    public Date getFechai() {
        return fechai;
    }

    public String getUbicación() {
        return ubicación;
    }

    public void setFechaf(Date fechaf) {
        this.fechaf = fechaf;
    }

    public void setFechai(Date fechai) {
        this.fechai = fechai;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public void setUbicación(String ubicación) {
        this.ubicación = ubicación;
    }

    public String getNenfermos() {
        return Nenfermos;
    }

    public void setNenfermos(String nenfermos) {
        Nenfermos = nenfermos;
    }

    public String getProporción() {
        return Proporción;
    }

    public void setProporción(String proporción) {
        Proporción = proporción;
    }

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String observacion) {
        Observacion = observacion;
    }
}
