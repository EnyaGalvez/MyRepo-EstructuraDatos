package org.example;

public class Paciente implements Comparable<Paciente> {
    private String nombre;
    private String sintoma;
    private String codigo;

    public Paciente(String nombre, String sintoma, String codigo) {
        this.nombre = nombre;
        this.sintoma = sintoma;
        this.codigo = codigo;
    }

    public String getNombre() { return nombre; }
    public String getSintoma() { return sintoma; }
    public String getCodigo() { return codigo; }

    @Override
    public int compareTo(Paciente otro) {
        return this.codigo.compareTo(otro.codigo);
    }

    @Override
    public String toString() {
        return nombre + ", " + sintoma + ", " + codigo;
    }
}
