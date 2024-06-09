

import controller.Cliente_controller;
import controller.Tarjeta_controller;
import dto.Consumo_DTO;
import dto.Tarjeta_debito_DTO;
import model.Cliente;
import model.Consumo;
import model.Tarjeta_credito;
import model.Tarjeta_debito;
import view.Guardar_Cliente_view;

import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        Guardar_Cliente_view guardarClienteView = new Guardar_Cliente_view("Rosa", "147258");
        guardarClienteView.guardar_cliente();


        Cliente_controller cliente_controller = Cliente_controller.getInstance();

        Cliente cliente = cliente_controller.obtener_cliente_por_dni("147258");
        if (cliente == null) {
            System.out.println("Cliente no encontrado");
        } else {
            Tarjeta_controller tarjeta_controller = Tarjeta_controller.getInstance();
            boolean tarjeta_debito_creada = Tarjeta_controller.getInstance().altaTarjeta_debito(cliente, "222", 21);
            boolean tarjeta_credito_creada = Tarjeta_controller.getInstance().altaTarjeta_credito(cliente, "237", 10);


            if (tarjeta_debito_creada) {
                System.out.println("Tarjeta debito creada");
            } else {
                System.out.println("Error en crear tarjeta debito");
                }
            if (tarjeta_credito_creada) {
                System.out.println("Tarjeta credito creada");
            } else {
                System.out.println("Error en crear tarjeta credito");
            }

            Consumo_DTO consumo_dto_debito = new Consumo_DTO("222", "100", "2", "2024", "UADE");
            Consumo_DTO consumo_dto_credito = new Consumo_DTO("237", "500", "1", "2024", "Mostaza");

            Tarjeta_controller.getInstance().registro_consumo(consumo_dto_debito.getNum_tarjeta(), consumo_dto_debito);
            Tarjeta_controller.getInstance().registro_consumo(consumo_dto_credito.getNum_tarjeta(), consumo_dto_credito);

            float total_consumo_credito = tarjeta_controller.consumo_total_credito();
            float total_consumo_debito = tarjeta_controller.consumo_total_debito();

            }
        }
    }