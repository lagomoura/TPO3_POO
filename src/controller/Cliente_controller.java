package controller;
import model.Cliente;

import java.util.ArrayList;
import java.util.List;

import dto.Cliente_DTO;

public class Cliente_controller {
    private List<Cliente> clientes;
    private static Cliente_controller INSTANCE = null;


    private Cliente_controller(){
        clientes = new ArrayList<>();
    }


    /* Patron singleton, una solo instancia - El constrctuor es privado, nadie puede instanciarla    */
    public static Cliente_controller getInstance() {
        /* Me fuerza a mantener una solo instancia*/
        if (INSTANCE == null) {
            INSTANCE = new Cliente_controller();
        }
        return INSTANCE;
    }


    public List<Cliente_DTO> buscar_todos_clientes() {

        List <Cliente_DTO> dtos = new ArrayList<>();

        for (Cliente cliente : clientes) {
            dtos.add(toDTO(cliente));
        }
        return dtos;
    }


    public Cliente obtener_cliente_por_dni(String dni) {
        for (Cliente cliente : clientes) {
            if (dni.equals(String.valueOf(cliente.getDni()))) {
                return cliente;
            }
        }
        return null;
    }


    public boolean cliente_existe(String dni){
        for (Cliente cliente : clientes){
            if (dni.equals(String.valueOf(cliente.getDni()))){
                return true;
            }
        }
        return false;
    }

    public void crearCliente(Cliente_DTO dto) {
        if (!cliente_existe(dto.getDniCliente())) {
            Cliente cliente = new Cliente(dto.getNombreCliente(), Integer.parseInt(dto.getDniCliente()));
            clientes.add(toModel(dto));
        }
    }


    public static Cliente_DTO toDTO(Cliente cliente) {
        return new Cliente_DTO(cliente.getNombre(), String.valueOf(cliente.getDni()));
    }

    public static Cliente toModel(Cliente_DTO dto) {
        return new Cliente(dto.getNombreCliente(), Integer.parseInt(dto.getDniCliente()));
    }


}
