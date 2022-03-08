package ej03;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

//* corta el cliente
// 10.101.19.124
public class cliente {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket = new DatagramSocket();//socket cliente

        byte[] enviados = new byte[1024];
        byte[] recibidos;

        InetAddress IPServidor = InetAddress.getLocalHost();// localhost
        int puerto = 9876;

        while (true) {
            //INTRODUCIR DATOS POR TECLADO
            System.out.print("Introduce año: ");
            String cadena = in.readLine();
            //Para terminar
            if (cadena.trim().equals("*")) break;

            enviados = cadena.getBytes();

            DatagramPacket envio = new DatagramPacket(enviados, enviados.length, IPServidor, puerto);
            clientSocket.send(envio);

            System.out.println("Esperando datagrama .......... ");
            recibidos = new byte[1024];

            DatagramPacket recibo = new DatagramPacket(recibidos, recibidos.length);
            clientSocket.receive(recibo);
            //siempre disponemos de una instancia nueva
            String datos =  new String(recibo.getData());
            System.out.println("\tMensaje recibido: " + datos.trim());

        }
    }

}