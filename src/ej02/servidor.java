package ej02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class servidor {

    public static void main(String[] arg) throws IOException {

        int numeroPuerto = 6000;// Puerto
        ServerSocket servidor = new ServerSocket(numeroPuerto);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        //ver
        String mensajeEntrada;
        String mensajeSalida;

        System.out.println("Esperando conexion...");
        Socket clienteConectado = servidor.accept();
        System.out.println("Cliente conectado...");
        System.out.println("bienvenido al chat. "
                + "\n Para cerrar el Servidor envia '*'");

        // CREO FLUJO DE SALIDA AL CLIENTE
        PrintWriter fsalida = new PrintWriter(clienteConectado.getOutputStream(), true);
        // CREO FLUJO DE ENTRADA DEL CLIENTE
        BufferedReader fentrada = new BufferedReader(new InputStreamReader(clienteConectado.getInputStream()));

        while (true) {
            mensajeEntrada = fentrada.readLine();// != null
            //condicion Cliente cierre de chat
            if (mensajeEntrada.equals("*")) {
                break;
            }
            // recibo cad del cliente
            System.out.println("Cliente: " + mensajeEntrada);

            String[] cadenas = mensajeEntrada.split(";");

            System.out.println("cadena1: " + cadenas[0] + "\ncadena2: " + cadenas[1]);
            //USAMOS DOS FLUJOS DIFERENTES
            mensajeSalida = tejer(cadenas[0], cadenas[1]);;// lectura por teclado
            //condicion Servidor cierre de chat
            fsalida.println(mensajeSalida); // envio cadena al client
        }
        // CERRAR STREAMS Y SOCKETS
        System.out.println("Cerrando conexion...");
        fentrada.close();
        fsalida.close();
        clienteConectado.close();
        servidor.close();
    }

    public static String tejer(String c1, String c2) {
        String[] palabras1 = c1.split(" ");
        String[] palabras2 = c2.split(" ");

        int l1 = palabras1.length;
        int l2 = palabras2.length;
        int cont = 0;

        String finalStr = "";

        System.out.println(l1 + " " + l2);

        while (true) {

            if (l1 != cont && l2 != cont) {
                finalStr += palabras1[cont] + " " + palabras2[cont] + " ";
                cont += 1;
            } else if (l1 == cont) {
                for (int i = cont; i < palabras2.length; i ++) {
                    finalStr += palabras2[i] + " ";
                }
                break;
            } else {
                for (int i = cont; i < palabras1.length; i ++) {
                    finalStr += palabras1[i] + " ";
                }
                break;
            }
        }

        return finalStr;
    }
}