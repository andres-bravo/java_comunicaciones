package comunicaciones;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import beans.Contacto;

public class ClienteBuscadorContactos {
	public List<Contacto> recuperarTodos(String cad) {
	//public void recuperarTodos() {
		ArrayList<Contacto> contactos=new ArrayList<>();
		//Datos del servidor
		//String ip="10.1.1.41";
		//Servidor del profesor
		String ip="10.1.1.100";
		int puerto= 9000;
		try {
			//Conexión
			Socket sc= new Socket(ip,puerto);
			//Socket establecido ahora enviar y recibir getInputStream, getOutputStream
			InputStream is = sc.getInputStream();
			OutputStream os=sc.getOutputStream();
			
			try(PrintStream salida = new PrintStream(os);BufferedReader bf = new BufferedReader(new InputStreamReader(is));){
				//Envío de datos
				salida.println(cad);
				//Leo Respuesta
				//String cad=bf.readLine();
				//System.out.println(cad);				
				JSONParser parser = new JSONParser();
				JSONArray array =(JSONArray)parser.parse(new InputStreamReader(is));
				//Recorremos el array JSON y mostramos los nombres de los contactos
				for(Object ob:array) {
					JSONObject data=(JSONObject)ob;
					Contacto c = new Contacto(String.valueOf(data.get("nombre")),
							String.valueOf(data.get("email")),
							Integer.parseInt(String.valueOf((data.get("telefono")))));
					contactos.add(c);					
					System.out.println(data.get("nombre")+","+data.get("email")+" "+data.get("telefono"));
				}			
			}
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contactos;
	}
	public static void main(String[] args) {
		ClienteBuscadorContactos cbc = new ClienteBuscadorContactos();
		cbc.recuperarTodos("n");
	}
}