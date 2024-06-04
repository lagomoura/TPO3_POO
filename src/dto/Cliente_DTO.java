package dto;

public class Cliente_DTO {

    private final String nombreCliente;
    private final String dniCliente;


    public Cliente_DTO(String nombreCliente,
                       String dniCliente) {

        if (nombreCliente == null || nombreCliente.isEmpty()) {
            throw new IllegalArgumentException("Nombre no puede estar vacio");
        }

        if (dniCliente == null || dniCliente.isEmpty()) {
            throw new IllegalArgumentException("DNI no puede estar vacio");
        }

        this.nombreCliente =nombreCliente;
        this.dniCliente = dniCliente;

    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    @Override
    public String toString() {
        return "Cliente_DTO{" +
                "nombreCliente='" + nombreCliente + '\'' +
                ", dniCliente='" + dniCliente + '\'' +
                '}';
    }
}
