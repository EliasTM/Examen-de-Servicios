package com.socket.entidad;

import java.io.Serializable;

public class Supervisor implements Serializable {

  private int codigo;
  private double sueldo;
  private boolean condicion;

  public Supervisor(int codigo, double sueldo, boolean condicion) {
    super();
    this.codigo = codigo;
    this.sueldo = sueldo;
    this.condicion = condicion;
  }

  public int getCodigo() {
    return codigo;
  }

  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }

  public double getSueldo() {
    return sueldo;
  }

  public void setSueldo(double sueldo) {
    this.sueldo = sueldo;
  }

  public boolean isCondicion() {
    return condicion;
  }

  public void setCondicion(boolean condicion) {
    this.condicion = condicion;
  }

  public String validacion() {
    if (condicion) {
      return "Estable";
    } else {
      return "Por campa?a";
    }

  }

  public Supervisor() {
    super();
  }

  public Supervisor(boolean condicion) {
    super();
    this.condicion = condicion;
  }

}
