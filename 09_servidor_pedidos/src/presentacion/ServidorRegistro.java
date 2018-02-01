package presentacion;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorRegistro {
	public static void main(String[] args) {
		try{
			ServerSocket server=new ServerSocket(8000);
			while(true) {
				System.out.println("Esperando llamadas en servidor registro...");
				//espera llamadas desde el exterior
				Socket sc=server.accept();
				System.out.println("Llamada recibida...");
				//procesar llamada
				new HiloClienteRegistro(sc).start();
			}
			
			
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}
}
