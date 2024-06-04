package model;

import java.util.Collection;

public abstract class Tarjeta {
    private Cliente cliente;
    private Collection<Consumo> consumo;
    private String num_tarjeta;

    public Tarjeta(Cliente cliente, Collection<Consumo> consumos, String num_tarjeta) {
        this.cliente = cliente;
        this.consumo = consumos;
        this.num_tarjeta = num_tarjeta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Collection<Consumo> getConsumo() {
        return consumo;
    }

    public String getNum_tarjeta() {
        return num_tarjeta;
    }
}
