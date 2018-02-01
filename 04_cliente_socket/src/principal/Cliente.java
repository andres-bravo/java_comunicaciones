package principal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class Cliente {

	public static void main(String[] args) {
		//Datos del servidor
		String ip="10.1.1.41";
		//Servidor del profesor
		//String ip="10.1.1.100";
		int puerto= 9000;
		try {
			//Conexión
			Socket sc= new Socket(ip,puerto);
			//Socket establecido ahora enviar y recibir getInputStream, getOutputStream
			InputStream is = sc.getInputStream();
			OutputStream os=sc.getOutputStream();
			
			try(PrintStream salida = new PrintStream(os);BufferedReader bf = new BufferedReader(new InputStreamReader(is));){
				//Envío de datos
				salida.println("profe");
				//Leo Respuesta
				String cad=bf.readLine();
				System.out.println(cad);
			}
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		
				
	}

}
