package dto;

public class Tarjeta_DTO {
    private String num_tarjeta;
    private Cliente_DTO cliente;

    public Tarjeta_DTO(Cliente_DTO cliente, String num_tarjeta) {
        this.num_tarjeta = num_tarjeta;
        this.cliente = cliente;
    }

    public Cliente_DTO getCliente() {
        return cliente;
    }

    public String getNum_tarjeta() {
        return num_tarjeta;
    }

    @Override
    public String toString() {
        return "Tarjeta_DTO{" +
                "num_tarjeta='" + num_tarjeta + '\'' +
                ", cliente=" + cliente +
                '}';
    }
}
