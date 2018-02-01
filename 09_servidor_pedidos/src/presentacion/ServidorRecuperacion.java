package presentacion;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorRecuperacion {
	public static void main(String[] args) {
		try{
			ServerSocket server=new ServerSocket(9000);
			while(true) {
				System.out.println("Esperando llamadas en servidor Recuperacion...");
				//espera llamadas desde el exterior
				Socket sc=server.accept();
				System.out.println("Llamada recibida...");
				//procesar llamada
				new HiloClienteRecuperacion(sc).start();
			}
			
			
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}
}
