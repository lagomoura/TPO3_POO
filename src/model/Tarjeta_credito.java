package model;

import java.util.Collection;

public class Tarjeta_credito extends Tarjeta{
    private float interes;

    public Tarjeta_credito(Cliente cliente, Collection<Consumo> consumos, String num_tarjeta, float interes) {
        super(cliente, consumos, num_tarjeta);
        this.interes = interes;
    }

    public float getInteres() {
        return interes;
    }
}
