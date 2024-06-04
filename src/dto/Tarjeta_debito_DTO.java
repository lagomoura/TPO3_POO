package dto;

public class Tarjeta_debito_DTO extends Tarjeta_DTO{
    private String devolucion_iva;

    public Tarjeta_debito_DTO(Cliente_DTO cliente, String num_tarjeta, String devolucion_iva) {
        super(cliente, num_tarjeta);
        this.devolucion_iva = devolucion_iva;
    }

    public String getDevolucion_iva(){
        return devolucion_iva;
    }

    @Override
    public String toString() {
        return "Tarjeta_debito_DTO{" +
                "devolucion_iva=" + devolucion_iva +
                '}';
    }
}
