package es.rgmf;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    int opcionUsuario, opcionMaquina, partidas, victoria, marcador1 = 0, marcador2 = 0;
        Scanner entrada = new Scanner(System.in);

        System.out.println("PIEDRA, PAPEL, TIJERA");
        partidas = pedirNumeroEnRango(entrada, 1, 5,"¿Cuántas partidas quieres jugar? [De 1 a 5]: ");
        System.out.println();

        for (int i = 0; i < partidas; i++) {
            System.out.println("Partida " + (i + 1) + ":");
            System.out.println("======================================");

            opcionUsuario = pedirOpcionUsuario(entrada);
            opcionMaquina = generarOpcionMaquina();

            victoria = calcularVictoria(opcionUsuario, opcionMaquina);
            if (victoria == 1) {
                marcador1++;
            } else if (victoria == 2) {
                marcador2++;
            }

            System.out.println();

            mostrarResultado(victoria, "Usuario", opcionUsuario, "Máquina", opcionMaquina);

            System.out.println();
            System.out.println("Intro para continuar...");
            entrada.nextLine();
        }

        victoriaPartida("Usuario", marcador1, "Máquina", marcador2);

        System.out.println("=====¡PARTIDA TERMINADA!=====");
        entrada.close();
    }

    /**
     * Pide un número entero y devuelve lo que escriba el usuario tras limpiar la entrada.
     *
     * @param entrada objeto Scanner para leer de la entrada estándar.
     * @param min número menor del rango.
     * @param max número mayor del rango.
     * @param mensaje objecto String con la información necesaria para que el usuario sepa lo que tiene que escribir.
     * @return el número entero escrito por el usuario.
     */
    // TODO escribe el método pedirNumeroEnRango para que haga lo que se indica en el comentario
    private static int pedirNumeroEnRango(Scanner entrada, int min, int max, String mensaje) {
        int numero;

        do {
            System.out.print(mensaje);
            numero = entrada.nextInt();
        } while (numero < min || numero > max);

        entrada.nextLine();

        return numero;
    }

    /**
     * Muestra un menú de opciones al usuario:
     * 1.- Piedra
     * 2.- Papel
     * 3.- Tijera
     *
     * Garantiza que el usuario ha elegido una opción entre 1 y 3.
     *
     * @param entrada objeto Scanner para leer por teclado.
     * @return número entero: 1 (piedra), 2 (papel), 3 (tijera).
     */
    // TODO escribe el método pedirOpcionUsuario para que haga lo que se indica en el comentario
    private static int pedirOpcionUsuario(Scanner entrada) {
        int opcion;
        do {
            System.out.println("1.-Piedra");
            System.out.println("2.-Papel");
            System.out.println("3.-Tijera");
            System.out.print("Elige una opción: ");
            opcion = entrada.nextInt();
            entrada.nextLine();
            System.out.println();
        } while (opcion < 1 || opcion > 3);


        return opcion;
    }

    /**
     * Genera un número aleatorio entre 1 y 3 que represente la opción de la máquina.
     * @return número entero: 1 (piedra), 2 (papel), 3 (tijera).
     */
    // TODO escribe el método generarOpcionMaquina para que haga lo que se indica en el comentario
    private static int generarOpcionMaquina(){
        int opcionMaquina = (int) (Math.random() * 3 + 1);
        return opcionMaquina;
    }

    /**
     * Calcula quién ha ganado una partida a piedra, papel o tijera.
     *
     * Considera que: 1 es piedra, 2 es papel y 3 es tijera.
     *
     * Esta función asume que tanto jugador1 como jugador2 tiene un valor
     * correcto, es decir, un valor entero entre 1 y 3.
     *
     * Si los valores de jugador1 y jugador2 están mal, el resultado
     * será inesperado.
     *
     * @param jugador1 la opción del jugador 1.
     * @param jugador2 la opción del jugador 2.
     * @return devuelve 1 si ha ganado el jugador 1.
     *         devuelve 2 si ha ganado el jugador 2.
     *         devuelve 0 si han empatado (misma opción).
     */
    // TODO escribe el método calcularVictoria para que haga lo que se indica en el comentario
    private static int calcularVictoria(int jugador1, int jugador2) {
        if (jugador1 == jugador2) {
            return 0;
        }
        // 1 piedra, 2 papel, 3 tijera

        switch (jugador1) {
            case 1:
                if (jugador2 == 2) {
                    return 2;
                } else {
                    return 1;
                }
            case 2:
                if (jugador2 == 1) {
                    return 1;
                } else {
                    return 2;
                }
            case 3:
                if (jugador2 == 1) {
                    return 2;
                } else {
                    return 1;
                }
        }
        return 0;
    }


    /**
     * Muestra por pantalla el resultado de una partida.
     *
     * @param victoria indica quién ha ganado: 0 (empate), 1 (jugador1) o 2 (jugador2).
     * @param nombre1 el nombre del jugador 1.
     * @param opcion1 la opción elegida por el jugador 1.
     * @param nombre2 el nombre del jugador 2.
     * @param opcion2 la opción elegida por el jugador 2.
     */
    // TODO escribe el método mostrarResultado para que haga lo que se indica en el comentario
    private static void mostrarResultado(int victoria, String nombre1, int opcion1, String nombre2, int opcion2){
        // Usuario ha elegido tijera y Máquina papel
        // Usuario gana a Máquina
        if (victoria == 0) {
            System.out.println(nombre1 + " y " + nombre2 + " han elegido " + nombreOpcion(opcion1));
            System.out.println("Han empatado");
        } else if (victoria == 1) {
            System.out.println(nombre1 + " ha elegido " + nombreOpcion(opcion1) + " y " + nombre2 + " ha elegido " + nombreOpcion(opcion2) );
            System.out.println("Ha ganado " + nombre1);
        } else if (victoria == 2) {
            System.out.println(nombre1 + " ha elegido " + nombreOpcion(opcion1) + " y " + nombre2 + " ha elegido " + nombreOpcion(opcion2) );
            System.out.println("Ha ganado " + nombre2);
        }
    }
    /**
     * Asume que el parámetro opción es 1, 2 o 3, de manera que si se pasa otro número
     * el resultado será inesperado.
     *
     * @param opcion número de la opción: 1, 2 o 3.
     * @return el nombre de la opción elegida: 1 = Piedra, 2 = Papel, 3 = Tijera
     */
    // TODO escribe el método nombreOpcion para que haga lo que se indica en el comentario
    private static String nombreOpcion(int opcion) {
        if (opcion == 1) {
            return "Piedra";
        } else if (opcion == 2) {
            return "Papel";
        } else {
            return "Tijera";
        }
    }
    //  TODO muestra quién ha ganado en general.
    private static void victoriaPartida(String nombre1, int marcador1, String nombre2, int marcador2){
       if (marcador1 == marcador2) {
           System.out.println("Se ha producido un empate.");
       } else if (marcador1 > marcador2){
           System.out.println("Ha ganado el Usuario.");
        }else {
           System.out.println("Ha ganado la Máquina.");
       }

    }
}
