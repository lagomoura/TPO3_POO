package controller;
import model.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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


    public Cliente_DTO obtener_cliente_dni(String dni){
        for (Cliente cliente : clientes){
            if (dni != null && !dni.equals(String.valueOf(cliente.getDni()))){
                return toDTO(cliente);
            }
        };
        System.out.println("Cliente ya registrado");
        return null;
    }

    public boolean crearCliente(Cliente_DTO dto) {
        if (dto != null && dto.getDniCliente() != null) {
            for (Cliente cliente : clientes) {
                if (cliente.getDni() == Integer.parseInt(dto.getDniCliente())) {
                    System.out.println("Cliente " + cliente.getNombre() + " ya registrado");
                    return false;
                }
            }
            clientes.add(toModel(dto));
            return true;
        }
        return false;
    }

    public static Cliente_DTO toDTO(Cliente cliente) {
        return new Cliente_DTO(cliente.getNombre(), String.valueOf(cliente.getDni()));
    }

    public static Cliente toModel(Cliente_DTO dto) {
        return new Cliente(dto.getNombreCliente(), Integer.parseInt(dto.getDniCliente()));
    }
}
