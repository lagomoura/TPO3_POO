package controller;
import model.*;
import dto.*;
import java.util.ArrayList;
import java.util.Collection;

import static java.lang.Float.*;

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
        } else {
            Tarjeta_credito tarjeta = new Tarjeta_credito(cliente, new ArrayList<>(), num_tarjeta, interes);
            tarjetas.add(tarjeta);
            return true;
        }
    }

    public boolean altaTarjeta_debito(Cliente cliente, String num_tarjeta, float devolucion_iva) {
        if (clienteTiene_td(cliente)) {
            System.out.println("Cliente ya pose una tarjeta de credito");
            return false;
        } else {
            Tarjeta_debito tarjeta = new Tarjeta_debito(cliente, new ArrayList<>(), num_tarjeta, devolucion_iva);
            tarjetas.add(tarjeta);
            return true;
        }

    }

    public boolean clienteTiene_tc(Cliente cliente) {
        for (Tarjeta tarjeta : tarjetas) {
            if (tarjeta instanceof Tarjeta_credito && String.valueOf(tarjeta.getCliente().getDni()).equals(cliente.getDni())) {
                return true;
            }
        }
        return false;
    }

        public boolean clienteTiene_td(Cliente cliente) {
            for (Tarjeta tarjeta : tarjetas) {
                if (tarjeta instanceof Tarjeta_debito && String.valueOf(tarjeta.getCliente().getDni()).equals(cliente.getDni())) {
                    return true;
                }
            }
            return false;
        }

    public void registro_consumo(String numTarjeta, Consumo_DTO consumo_dto) {
        for (Tarjeta tarjeta : tarjetas) {
            if (tarjeta.getNum_tarjeta().equals(numTarjeta)) {
                Consumo consumo = new Consumo(
                        Integer.parseInt(consumo_dto.getNum_tarjeta()),
                        Integer.parseInt(consumo_dto.getMes()),
                        consumo_dto.getNombre_establecimiento(),
                        Integer.parseInt(consumo_dto.getAno()),
                        parseFloat(consumo_dto.getTotal()));

                tarjeta.getConsumo().add(consumo);
                System.out.println("Consumo registrado con suceso en tarjeta Nro: " + numTarjeta + " Valor: " + "$" + consumo_dto.getTotal() + " Mes: " + consumo_dto.getMes() + " Ano: " + consumo_dto.getAno() + " Establecimiento: " + consumo_dto.getNombre_establecimiento());
                return;
            }
        }
        System.out.println("No se encontro la tarjeta " + numTarjeta);
    }


    public float consumo_total_credito() {
        float total = 0;
        for (Tarjeta tarjeta : tarjetas) {
            if (tarjeta instanceof Tarjeta_credito) {
                Tarjeta_credito tarjeta_credito = (Tarjeta_credito) tarjeta;
                float interes = tarjeta_credito.getInteres();
                for (Consumo consumo : tarjeta_credito.getConsumo()) {
                    total += consumo.getTotal();
                }
                total += total * (1 + (interes / 100));
                System.out.println("Valor total de consumo, sumando el interes. Tarjeta: " + tarjeta.getNum_tarjeta() + " $" + total);
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
                    total += consumo.getTotal();
                }
                total += total - ((devolucion_iva / 100) * total);
                System.out.println("Valor total de consumo, restando devolucion de iva. Tarjeta: " + tarjeta.getNum_tarjeta()  + " $"  + total);
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

    public static Consumo_DTO toDTO(Consumo consumo) {
        return new Consumo_DTO(
                        String.valueOf(consumo.getId()),
                        String.valueOf(consumo.getMes()),
                        consumo.getNombre_establecimiento(),
                        String.valueOf(consumo.getAno()),
                        String.valueOf(consumo.getTotal())
        );
    }

    public static Consumo toModel(Consumo_DTO consumo_dto) {
        return new Consumo(
                Integer.parseInt(consumo_dto.getNum_tarjeta()),
                Integer.parseInt(consumo_dto.getMes()),
                consumo_dto.getNombre_establecimiento(),
                Integer.parseInt(consumo_dto.getAno()),
                Float.parseFloat(consumo_dto.getTotal())
        );
    }
}
