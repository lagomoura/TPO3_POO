package controller;


import model.*;
import dto.*;
import java.util.ArrayList;
import java.util.Collection;

public class Tarjeta_controller {
    private Collection<Tarjeta> tarjetas;

    private static Tarjeta_controller INSTANCE = null;

    private Tarjeta_controller() {
        tarjetas = new ArrayList<>();
    }

    public static Tarjeta_controller getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Tarjeta_controller();
            INSTANCE.tarjetas = new ArrayList<>();
        }
        return INSTANCE;
    }

    public boolean altaTarjeta_credito(Cliente cliente, String num_tarjeta, float interes) {
        if (clienteTiene_tc(cliente)) {
            System.out.println("Cliente ya pose una tarjeta de credito");
            return false;
        }
        Tarjeta_credito tarjeta = new Tarjeta_credito(cliente, new ArrayList<>(), num_tarjeta, interes);
        tarjetas.add(tarjeta);
        System.out.println("Tarjeta de credito creada con suceso!");
        return true;
    }

    public boolean altaTarjeta_debito(Cliente cliente, String num_tarjeta, float devolucion_iva) {
        if (clienteTiene_td(cliente)) {
            System.out.println("Cliente ya pose una tarjeta de debito");
            return false;
        }
        Tarjeta_debito tarjeta = new Tarjeta_debito(cliente, new ArrayList<>(), num_tarjeta, devolucion_iva);
        return true;
    }

    public boolean clienteTiene_tc(Cliente cliente) {
        for (Tarjeta tarjeta : tarjetas) {
            if (tarjeta instanceof Tarjeta_credito && tarjeta.getCliente().getDni() == cliente.getDni()) {
                return true;
            }
        }
        return false;
    }

    public boolean clienteTiene_td(Cliente cliente) {
        for (Tarjeta tarjeta : tarjetas) {
            if (tarjeta instanceof Tarjeta_debito && tarjeta.getCliente().getDni() == cliente.getDni()) {
                return true;
            }
        }
        return false;
    }

    public void registro_consumo(Tarjeta tarjeta, Consumo consumo) {
        tarjeta.getConsumo().add(consumo);
    }


    public float consumo_total_credito() {
        float total = 0;
        for (Tarjeta tarjeta : tarjetas) {
            if (tarjeta instanceof Tarjeta_credito) {
                Tarjeta_credito tarjeta_credito = (Tarjeta_credito) tarjeta;
                float interes = tarjeta_credito.getInteres();
                for (Consumo consumo : tarjeta_credito.getConsumo()) {
                    total += consumo.getTotal() * (1 + (interes / 100));
                }
            }
        }
        return total;
    }

    public float consumo_total_debito() {
        float total = 0;
        for (Tarjeta tarjeta : tarjetas) {
            if (tarjeta instanceof Tarjeta_debito) {
                Tarjeta_debito tarjeta_debito = (Tarjeta_debito) tarjeta;
                float devolucion_iva = tarjeta_debito.getDevolucion_iva();
                for (Consumo consumo : tarjeta_debito.getConsumo()) {
                    total += consumo.getTotal() - ((devolucion_iva / 100) * total);
                }
            }
        }
        return total;
    }

    public static Tarjeta_DTO toDTO(Tarjeta tarjeta) {
        if (tarjeta instanceof Tarjeta_credito) {
            Tarjeta_credito tarjeta_credito = (Tarjeta_credito) tarjeta;
            return new Tarjeta_credito_DTO(Cliente_controller.toDTO(tarjeta_credito.getCliente()), tarjeta_credito.getNum_tarjeta(), String.valueOf(tarjeta_credito.getInteres()));
        } else if (tarjeta instanceof  Tarjeta_debito) {
            Tarjeta_debito tarjeta_debito = (Tarjeta_debito) tarjeta;
            return new Tarjeta_debito_DTO(Cliente_controller.toDTO(tarjeta_debito.getCliente()), tarjeta_debito.getNum_tarjeta(), String.valueOf(tarjeta_debito.getDevolucion_iva()));
        }
        return null;
    }


    public static Tarjeta toModel(Tarjeta_DTO dto) {
        Cliente cliente = Cliente_controller.toModel(dto.getCliente());

        if (dto instanceof Tarjeta_credito_DTO) {
            Tarjeta_credito_DTO tarjeta_credito_dto = (Tarjeta_credito_DTO) dto;
            return new Tarjeta_credito(cliente, new ArrayList<>(), tarjeta_credito_dto.getNum_tarjeta(), Integer.parseInt(tarjeta_credito_dto.getInteres()));

            } else if (dto instanceof Tarjeta_debito_DTO) {
                Tarjeta_debito_DTO tarjeta_debito_dto = (Tarjeta_debito_DTO) dto;
                return new Tarjeta_debito(cliente, new ArrayList<>(), tarjeta_debito_dto.getNum_tarjeta(), Integer.parseInt((tarjeta_debito_dto.getDevolucion_iva())));
        }
            return null;
    }
}