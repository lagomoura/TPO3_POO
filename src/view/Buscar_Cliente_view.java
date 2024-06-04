package view;

import controller.Cliente_controller;
import dto.Cliente_DTO;

import java.util.List;

public class Buscar_Cliente_view {
    private String dniTexto;
    private Cliente_controller cliente_controller;


    public Buscar_Cliente_view() {
        cliente_controller = Cliente_controller.getInstance();
    }

    public String buscarCliente(String dniTexto) {
        Cliente_DTO dto = cliente_controller.obtener_cliente_dni(dniTexto);
        if (dto != null) {
            return dto.toString();
        } else {
            return "Cliente no encontrado!";
        }
    }

    public String buscarTodosClientes() {
        String msj = "";
        List<Cliente_DTO> dtos = cliente_controller.buscar_todos_clientes();
        for (Cliente_DTO dto: dtos) {
            if (dto != null) {
                msj += dto.toString()+"\n";
            }
        }
        return msj;
    }
}
