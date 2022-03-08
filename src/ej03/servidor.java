package ej03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class servidor {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket serverSocket = new DatagramSocket(9876);

        byte[] recibidos;
        byte[] enviados = new byte[1024];
        String cadena;
        //corta servidor *

        while (true) {
            // RECIBO EL DATAGRAMA
            System.out.println("Esperando datagrama...");
            recibidos = new byte[1024];

            DatagramPacket pagRecibido = new DatagramPacket(recibidos, recibidos.length);
            serverSocket.receive(pagRecibido);
            //instancia de cadena nueva
            cadena = new String(pagRecibido.getData());
            System.out.println("\tAño recibido: " + cadena.trim());

            System.out.println("hi");

            if (cadena.trim().equals("*"))
                break;

            // DIRECCION ORIGEN
            InetAddress IPOrigen = pagRecibido.getAddress();
            int puerto = pagRecibido.getPort();

            // INTRODUCIR DATOS POR TECLADO
            int anho = Integer.parseInt(cadena.trim());

            if ((checkMultiplo(anho, 4) && !checkMultiplo(anho, 100)) || checkMultiplo(anho, 400)) {
                enviados = ("bisiesto").getBytes();
            } else {
                enviados = ("no bisiesto").getBytes();
            }

            // ENVIO DATAGRAMA AL CLIENTE
            DatagramPacket pagEnviado = new DatagramPacket(enviados, enviados.length, IPOrigen, puerto);
            serverSocket.send(pagEnviado);

        }
        serverSocket.close();
        System.out.println("Socket cerrado...");
    }

    public static boolean checkMultiplo(int n1, int n2) {
        if ( n1%n2 == 0 ) { return true; }
        else { return false; }
    }
}