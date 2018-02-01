package presentacion;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import comunicaciones.HiloMostrarPedidos;

public class SrvMostrarPedidos {

	public static void main(String[] args) {
		try(ServerSocket serv=new ServerSocket(9000)){
			ExecutorService es=Executors.newFixedThreadPool(10);
			//quedamos a la espera de una llamada
			while(true) {
				System.out.println("Servicio Recuperar Pedidos Esperando llamadas...");
				Socket sc=serv.accept();
				System.out.println("Llamada recibida..."+sc.getInetAddress().getHostAddress());
				es.submit(new HiloMostrarPedidos(sc));			
			}
			
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}

	}

}
