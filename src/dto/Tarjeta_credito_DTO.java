package dto;

public class Tarjeta_credito_DTO extends Tarjeta_DTO {
    private String interes;

    public Tarjeta_credito_DTO(Cliente_DTO cliente, String num_tarjeta, String interes) {
        super(cliente, num_tarjeta);
        this.interes = interes;
    }

    public String getInteres() {
        return interes;
    }

    @Override
    public String toString() {
        return "Tarjeta_credito_DTO{" +
                "interes=" + interes +
                '}';
    }
}
