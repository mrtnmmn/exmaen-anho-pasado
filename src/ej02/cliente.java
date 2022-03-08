package ej02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

//10.101.19.124
public class cliente {

    public static void main(String[] args) throws IOException {

        String Host = "localhost";
        int Puerto = 6000;// puerto remoto
        Socket Cliente = new Socket(Host, Puerto);

        // CREO FLUJO DE SALIDA AL SERVIDOR
        PrintWriter fsalida = new PrintWriter(Cliente.getOutputStream(), true);
        // CREO FLUJO DE ENTRADA AL SERVIDOR
        BufferedReader fentrada = new BufferedReader(new InputStreamReader(Cliente.getInputStream()));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String cadena, cadena2, eco = "";
        System.out.println(
                "bienvenido al chat. Para cerrar el Servidor envia '*'" + "\n para cerrar el Cliente envia '*' ");

        while (true) {
            System.out.print("Cadena1: ");
            cadena = in.readLine();// lectura por teclado
            System.out.print("Cadena2: ");
            cadena2 = in.readLine();

            if (cadena.equals("*")) {
                break;
            }
            fsalida.println(cadena + ";" + cadena2); // envio cadena al servidor
            eco = fentrada.readLine(); // recibo cadena del servidor
            System.out.println("Servidor: " + eco);
        }

        fsalida.close();
        fentrada.close();
        System.out.println("Fin del envio... ");
        in.close();
        Cliente.close();
    }

}