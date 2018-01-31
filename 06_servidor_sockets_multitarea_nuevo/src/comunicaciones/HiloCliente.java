package comunicaciones;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class HiloCliente implements Runnable {
	private Socket sc;
	public HiloCliente(Socket sc) {
		this.sc=sc;
	}
	@Override
	public void run() {
		try {
			InputStream is = sc.getInputStream();
			OutputStream os=sc.getOutputStream();
			
			try(PrintStream salida = new PrintStream(os);BufferedReader bf = new BufferedReader(new InputStreamReader(is));){
				//Leo Respuesta
				String cad=bf.readLine();
				System.out.println(cad);
				//Envío de datos
				salida.println("Respuesta Servidor");
			}
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		finally {
			//Tengo que hacerlo con finally ya que no puedo utilizar el try con recursos ya que el 
			//Socket viene del constructor y no se crea en este metodo.
			try {
				sc.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
