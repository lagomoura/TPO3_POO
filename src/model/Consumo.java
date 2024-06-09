package model;

public class Consumo {
    private int id;
    private int mes;
    private String nombre_establecimiento;
    private int ano;
    private float total;

    public Consumo( int id, int mes, String nombre_establecimiento, int ano, float total) {
        this.id = id;
        this.mes = mes;
        this.nombre_establecimiento = nombre_establecimiento;
        this.ano = ano;
        this.total = total;

    }

    public int getMes() {
        return mes;
    }

    public int getAno() {
        return ano;
    }

    public String getNombre_establecimiento() {
        return nombre_establecimiento;
    }

    public float getTotal() {
        return total;
    }

    public int getId() {
        return id;
    }
}
