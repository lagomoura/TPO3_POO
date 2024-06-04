package model;

import java.util.Collection;

public class Tarjeta_debito extends Tarjeta {
    private float devolucion_iva;

    public Tarjeta_debito(Cliente cliente, Collection<Consumo> consumos, String num_tarjeta, float devolucion_iva) {
        super(cliente, consumos, num_tarjeta);
        this.devolucion_iva = devolucion_iva;
    }

    public float getDevolucion_iva() {
        return devolucion_iva;
    }
}
