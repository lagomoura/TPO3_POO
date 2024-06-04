package view;

import controller.Cliente_controller;
import dto.Cliente_DTO; 

/* En la view, todos son strings */

public class Guardar_Cliente_view {
    private String nombreClienteTxt;
    private String dniClienteTxt;
    private Cliente_controller cliente_controller;


    public Guardar_Cliente_view(String nombreClienteTxt,
                                String dniClienteTxt) {
        this.nombreClienteTxt = nombreClienteTxt;
        this.dniClienteTxt = dniClienteTxt;

        /* Singleton */
        this.cliente_controller = Cliente_controller.getInstance();
    }

    public String guardar_cliente(){
        if (nombreClienteTxt != null && !nombreClienteTxt.isEmpty() && dniClienteTxt != null && !dniClienteTxt.isEmpty()) {
            Cliente_DTO cliente_dto = new Cliente_DTO(this.nombreClienteTxt, this.dniClienteTxt);

            cliente_controller.crearCliente(cliente_dto);

            boolean suceso = cliente_controller.crearCliente(cliente_dto);

            if (suceso) {
                System.out.println("Cliente " + nombreClienteTxt + " guardado con suceso");
                return "Operacion Finalizada";
            } else {
                return "Error: DNI ya registro en nuestra base de datos";
            }
        }
        return "Error: Nombre o DNI invalido";
    }
}
