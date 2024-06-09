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

    public boolean buscarCliente(String dniTexto) {
        return cliente_controller.cliente_existe(dniTexto);
    }
}
