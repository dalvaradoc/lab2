/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package punto1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Alejandro
 */
public class Menu {
    
    private Scanner sc;
    private Sistema sistema;

    public Menu() {
        sc = new Scanner(System.in);
        sistema = new Sistema();
        System.out.println("--------------");
        System.out.println("Para el corrector funcionamiento del programa, antes \n"
                + "de elegir una opcion dele click en el boton start. Se recomienda \n"
                + "sacar la pestaña \"Output\" de netbeans y ponerla encima de la \n"
                + "ventana de la simulacion para ver mejor el funcionamiento del \n"
                + "programa.");
        System.out.println("--------------");
        int opc1;
        String none = "";
        boolean ciclo1 = true;
        while (ciclo1){
            System.out.println("Seleccione una opcion.");
            System.out.println("1. Traer estante");
            System.out.println("2. Llenar el estante");
            System.out.println("3. Devolver estante");
            System.out.println("4. Hacer pedido.");
            System.out.println("5. Ver todos los productos disponibles");
            System.out.println("0. Cerrar el programa");
            opc1 = sc.nextInt();
            System.out.println("--------------");
            switch (opc1){
                case 1:
                    System.out.println("Escriba el numero del estante que desea traer: ");
                    int est = sc.nextInt();
                    sistema.traerEstante(est);
                    System.out.println("--------------");
                    break;
                case 2:
                    if (!sistema.isEstanteEnZona()){
                        System.out.println("No hay ningun estante en la zona de empleados");
                        break;
                    }
                    System.out.println("Escriba el numero de la caja (de 1 a 3):");
                    int caja = sc.nextInt();
                    boolean cicloAdd = true;
                    int opcAdd;
                    ArrayList<Producto> prodAdd = new ArrayList<>();
                    while (cicloAdd){
                        System.out.println("1. Elegir producto");
                        System.out.println("0. Añadir productos y volver");
                        opcAdd = sc.nextInt();
                        System.out.println("--------------");
                        switch (opcAdd){
                            case 1:
                                System.out.println("Escriba el nombre del producto a añadir:");
                                none = sc.nextLine();
                                String nombrePA = sc.nextLine();
                                System.out.println("Ingrese el precio del producto a añadir");
                                double precioPA = Double.parseDouble(sc.nextLine());
                                System.out.println("Ingrese la cantidad de ese producto a añadir");
                                int cantpa = sc.nextInt();
                                while (cantpa > 0){
                                    prodAdd.add(new Producto(nombrePA, precioPA));
                                    cantpa--;
                                }
                                break;
                            case 0:
                                sistema.llenarEstante(caja, prodAdd);
                                cicloAdd = false;
                                break;
                        }
                    }
                    break;
                case 3:
                    sistema.devolverEstante();
                    break;
                case 4:
                    HashMap<String,Integer> informacion = sistema.getInfoAllProductos();
                    System.out.println("Info productos: ");
                    for (String n : informacion.keySet()){
                        System.out.println(n + ": " + informacion.get(n));
                    }
                    HashMap<String,Integer> pedido = new HashMap<>();
                    int opcPedido;
                    boolean cicloPedido = true;
                    while (cicloPedido){
                        System.out.println("1. Añadir producto a la lista de pedidos");
                        System.out.println("2. Ver la lista de pedidos");
                        System.out.println("3. Traer los estantes donde estan los productos del pedido");
                        System.out.println("0. Cancelar");
                        opcPedido = sc.nextInt();
                        System.out.println("--------------");
                        switch (opcPedido){
                            case 1:
                                System.out.println("Ingrese el nombre del producto:");
                                none = sc.nextLine();
                                String nombrePP = sc.nextLine();
                                System.out.println("Ingrese la cantidad que quiere:");
                                int cant = sc.nextInt();
                                if (pedido.containsKey(nombrePP)){
                                    pedido.replace(nombrePP, pedido.get(nombrePP) + cant);
                                } else {
                                    pedido.put(nombrePP, cant);
                                }
                                break;
                            case 2:
                                for (String n : pedido.keySet()){
                                    System.out.println(n + ": " + pedido.get(n));
                                }
                                break;
                            case 3:
                                boolean cicloF = true;
                                if (!sistema.traerPedido(pedido)){
                                    cicloF = false;
                                }
                                ArrayList<Producto> prodsF = new ArrayList<>(); 
                                int opcF;
                                while (cicloF){
                                    System.out.println("1. Recolectar productos del estante");
                                    System.out.println("2. Devolver el estante");
                                    System.out.println("3. Llevar los productos a la zona de envio");
                                    opcF = sc.nextInt();
                                    System.out.println("--------------");
                                    switch (opcF){
                                        case 1:
                                            if (sistema.isEstanteEnZona()){
                                                prodsF.addAll(sistema.getProductos(pedido));
                                            }
                                            break;
                                        case 2:
                                            int cantP2 = 0;
                                            for (int c : pedido.values()){
                                                cantP2 += c;
                                            }
                                            if (cantP2 == 0){
                                                System.out.println("Ya tiene los productos necesarios, envie el pedido con el\n"
                                                        + "ultimo robot a la zona de envio.");
                                                break;
                                            }
                                            if (sistema.isEstanteEnZona()){
                                                sistema.devolverEstante();
                                            } else {
                                                System.out.println("No hay ningun estante en la zona de recoleccion");
                                            }
                                            break;
                                        case 3:
                                            int cantP = 0;
                                            for (int c : pedido.values()){
                                                cantP += c;
                                            }
                                            if (cantP == 0){
                                                sistema.llevarPedido (prodsF);
                                            } else {
                                                System.out.println("Aun no tiene todos los productos del envio");
                                            }
                                            cicloF = false;
                                            break;
                                    }
                                }
                                cicloPedido = false;
                                break;
                            case 0:
                                cicloPedido = false;
                                break;
                        }
                    }
                    break;
                case 5:
                    System.out.println("-----------");
                    HashMap<String,Integer> infoAllProds = sistema.getInfoAllProductos();
                    for (String n : infoAllProds.keySet()){
                        System.out.println(n + ": " + infoAllProds.get(n));
                    }
                    System.out.println("-----------");
                    break;
                case 0:
                    ciclo1 = false;
                    break;
            }
            System.out.println("--------------");
        }
        System.exit(0);
    }
    
}
