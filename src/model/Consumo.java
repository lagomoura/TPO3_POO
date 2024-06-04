package model;

public class Consumo {
    private int id;
    private int mes;
    private int ano;
    private String nombre_establecimiento;
    private float total;

    public Consumo(int id, int mes, int ano, String nombre_establecimiento) {
        this.id = id;
        this.mes = mes;
        this.ano = ano;
        this.nombre_establecimiento = nombre_establecimiento;
        this.total = total;
    }

    public int getId() {
        return id;
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
}
