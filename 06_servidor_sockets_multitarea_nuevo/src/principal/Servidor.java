package principal;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import comunicaciones.HiloCliente;

public class Servidor {

	public static void main(String[] args) {
		try (ServerSocket serv = new ServerSocket(9000);){
			ExecutorService es= Executors.newFixedThreadPool(10);
			//Quedamos a la espera de llamada y devolvemos un socket
			//Se podría poner una variable en el 
			while(true) {
				System.out.println("Esperando llamadas...");
				Socket sc = serv.accept();
				//Sacamos datos del cliente llamante con getInetAddress()
				
				System.out.println("Llamada recibida del cliente: " + sc.getInetAddress().getCanonicalHostName());
				es.submit(new HiloCliente(sc));
			}		
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}
}
	
