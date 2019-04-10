package gestionhotelera;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author JonathanLizama
 */
public class GestionHotelera {

      public static void main(String[] args) {
            final String habitaciones[] = new String[10];
            llenarHabitaciones(habitaciones);
            menu(habitaciones);

      }

      public static void menu(String habitaciones[]) {
            boolean salir = false;
            while (!salir) {
                  System.out.println("------------------------------------------------------------------------");
                  System.out.println("Bienvenido a la gestion hotelera  |DONDE CABEN 1 CABEN 2|");
                  System.out.println("------------------------------------------------------------------------");
                  mostrarOpcionesMenu();
                  int opcion = leerEntero();
                  switch (opcion) {
                        case 1: {
                              mostrarHabitaciones(habitaciones);
                              break;
                        }
                        case 2: {
                              System.out.println("¿cúal habitación quieres reservar?  |1|  |2|  |3| |4| |5| |6| |7| |8| |9| |10|");
                              reservarHabitacion(habitaciones, elegirHabitacion());

                              break;
                        }
                        case 3: {
                              System.out.println("¿en cúal habitación quieres Ingresar?  |1|  |2|  |3| |4| |5| |6| |7| |8| |9| |10|");
                              boolean resultado = IngresarPasajero(habitaciones, elegirHabitacion());
                              if (resultado) {

                                    Boleta(cantidadDias(), tipoDeComida());
                              }

                              break;
                        }
                        case 4: {
                              System.out.println("¿Cúal habitación quiere dejar disponible?  |1|  |2|  |3| |4| |5| |6| |7| |8| |9| |10|");
                              dejarHabitacionDisponible(habitaciones, elegirHabitacion());

                              break;
                        }
                        case 5: {
                              reiniciarHotel(habitaciones, verificarContraseña(leerContraseña()));
                              break;
                        }
                        case 6:
                              System.out.println("Saliste del programa");
                              salir = true;

                              break;
                        default:
                              System.out.println("Ingrese una opción correcta...");
                              break;
                  }
            }
      }

      // metodo para leer un número entero , aplicando excepciones.
      public static int leerEntero() {
            try {
                  Scanner leer = new Scanner(System.in);
                  int entero = leer.nextInt();
                  return entero;
            } catch (Exception e) {
                  return -1;
            }
      }

      // metodo para mostrar las opciones que tendra nuestro menu.
      public static void mostrarOpcionesMenu() {
            System.out.println("[1].- Consultar Habitaciones");
            System.out.println("[2].- Reservar Habitacion");
            System.out.println("[3].- Ingresar Pasajeros");
            System.out.println("[4].- Dejar Habitación Disponible");
            System.out.println("[5].- Reiniciar hotel completo.");
            System.out.println("[6].- Salir.");
      }

      // metodo para llenar las habitaciones del hotel al azar en 3 estados |Ocupado| |Disponible| |Reservado|
      public static void llenarHabitaciones(String habitaciones[]) {
            String estado[] = new String[]{"Ocupado", "Disponible", "Reservado"};
            Random azar = new Random();
            for (int i = 0; i < habitaciones.length; i++) {
                  habitaciones[i] = estado[azar.nextInt(estado.length)];
            }
      }

      //metodo para mostrar las 10 habitaciones de nuestro hotel.
      public static void mostrarHabitaciones(String habitaciones[]) {
            for (int i = 0; i < habitaciones.length; i++) {
                  System.out.println("habitación " + (i + 1) + ":" + "[" + habitaciones[i] + "]");
            }
            System.out.println("");

      }

      // metodo para RESERVAR una habitación, solo si la habitacion se encuentra disponible
      public static void reservarHabitacion(String habitaciones[], int eleccion) {

            if (eleccion >= 1 && eleccion <= 10) {
                  if (habitaciones[eleccion - 1].equals("Disponible")) {
                        habitaciones[eleccion - 1] = "Reservado";
                        System.out.println("Habitación " + eleccion + " reservada.");
                  } else {
                        System.out.println("No seleccionó bien la habitación, !!! SOLO SE PUEDEN RESERVAR HABITACIONES DISPONIBLES");
                        System.out.println("---------------------------------------------------------------------------------------");
                  }
            }

      }

      // metodo para INGRESAR una persona al hotel, solo si la habitacion se encuentra disponible 
      public static boolean IngresarPasajero(String habitaciones[], int eleccion) {
            if (eleccion >= 1 && eleccion <= 10) {
                  if (habitaciones[eleccion - 1].equals("Disponible")) {
                        habitaciones[eleccion - 1] = "Ocupada";
                        System.out.println("Habitación " + eleccion + " ahora quedo ocupada.");
                        return true;
                  } else {
                        System.out.println("En esta habitación no puede ingresar , ¡¡¡SOLO EN HABITACIONES DISPONIBLES!!! ");
                        System.out.println("---------------------------------------------------------------");

                  }

            }
            return false;
      }

      //Metodo para dejar habitacion Disponible solo si esta ocupada o reservada.
      public static void dejarHabitacionDisponible(String habitaciones[], int eleccion) {
            if (eleccion >= 1 && eleccion <= 10) {
                  if (!habitaciones[eleccion - 1].equals("Disponible")) {
                        habitaciones[eleccion - 1] = "Disponible";
                        System.out.println("Habitación " + eleccion + " desocupada.");
                  } else {
                        System.out.println("Esta habitación ya está disponible");
                        System.out.println("---------------------------------------------------------------");
                  }
            }
      }

      // metodo para elegir una habitacion, falta validar cantidad de habitaciones 
      public static int elegirHabitacion() {
            Scanner leer = new Scanner(System.in);
            int eleccion;
            try {
                  eleccion = leer.nextInt();
                  return eleccion;
            } catch (InputMismatchException e) {
                  System.out.println(e);
                  System.out.println("Ingrese un número por favor");
                  return -1;
            }

      }

      // metodo que imprime el total gastado en el hotel
      public static void Boleta(int dias, int comida) {
            int soloCena = 30000;
            int desayunoYCena = 40000;
            int total = 0;
            if (comida == 1) {
                  total = soloCena * dias;

            } else if (comida == 2) {
                  total = desayunoYCena * dias;
            }
            System.out.println("total de dias que se quedo : " + dias);
            System.out.println("comida elegida: " + comida);
            System.out.println("Total a pagar es: $" + total + " pesos" + "\n");

      }

      // metodo que el usuario escoge el tipo de comida durante su estadia en el hotel.
      public static int tipoDeComida() {
            int tipoComida = 0;
            Scanner leer = new Scanner(System.in);
            do {
                  try {
                        System.out.println("Ingrese que tipo de habitacion desea  |1.- solo cena| |2.- desayuno + cena|");
                        tipoComida = leer.nextInt();

                  } catch (InputMismatchException e) {
                        System.out.println(e);
                        System.out.println("no se pueden ingresar letras");
                        break;
                  }
            } while (tipoComida != 1 && tipoComida != 2);
            return tipoComida;
      }

    // metodo para saber la cantidad de dias en el hotel  
      public static int cantidadDias() {
            Scanner leer = new Scanner(System.in);
            int dias = 0;
            do {
                  try {
                        System.out.println("Ingrese la cantidad de dias que estara en el hotel:");
                        dias = leer.nextInt();
                  } catch (InputMismatchException e) {
                        System.out.println(e);
                        System.out.println("no se pueden ingresar letras");
                        break;
                  }
            } while (dias < 0 || dias > 20);

            return dias;
      }
   
      // metodo que verifica que laa contraseña sea 1234.
      public static boolean verificarContraseña(int contraseña) {
            return contraseña == 1234;
      }
      //metodo para leer la contraseña
      public static int leerContraseña() {
            Scanner leer = new Scanner(System.in);
            int contraseña = 0;
            try {
                  System.out.println("Ingrese la contraseña para reiniciar el hotel:");
                  contraseña = leer.nextInt();
            } catch (InputMismatchException e) {
                  System.out.println("ingrese un dato valido");

            }
            return contraseña;

      }
      // metodo que reinicia el hotel y deja todo disponible
      public static void reiniciarHotel(String[] habitaciones, boolean autentificacion) {
            if (autentificacion) {
                  System.out.println("Acceso correcto , las habitaciones quedaron disponibles" + "\n");
                  for (int i = 0; i < habitaciones.length; i++) {
                        habitaciones[i] = "Disponible";
                  }
            } else {
                  System.out.println("Contraseña equivocada, acceso denegado.");
            }
      }

}
