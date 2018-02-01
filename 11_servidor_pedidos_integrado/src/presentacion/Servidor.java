package presentacion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Servidor {
	public static void main(String[] args) {
		try{
			ServerSocket server=new ServerSocket(8000);
			while(true) {
				System.out.println("Esperando llamadas en servidor ...");
				//espera llamadas desde el exterior
				Socket sc=server.accept();
				String op=recuperarOperacion(sc);
				System.out.println("Llamada recibida...");
				switch(op) {
				case "registro":
					//procesar llamada
					new HiloClienteRegistro(sc).start();
					break;
				case "recuperacion":
					//procesar llamada
					new HiloClienteRecuperacion(sc).start();
					break;
				}
				
			}
			
			
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	private static String recuperarOperacion(Socket sc) {
		String op="";
		try {
			BufferedReader bf=new BufferedReader(new InputStreamReader(sc.getInputStream()));
			JSONParser parse=new JSONParser();
			JSONObject ob=(JSONObject)parse.parse(bf.readLine());
			op=ob.get("operacion").toString();
		}
		catch(IOException ex) {
			ex.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return op;
	}
}
