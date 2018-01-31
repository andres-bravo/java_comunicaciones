package comunicacion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import beans.Contacto;
import modelo.GestionAgenda;

public class HiloCliente implements Runnable {
	private Socket sc;
	public HiloCliente(Socket sc) {
		this.sc=sc;
	}
	@Override
	public void run() {
		try {
			//crear canales entrada/salida
			InputStream is=sc.getInputStream();
			OutputStream os=sc.getOutputStream();
			try(PrintStream salida=new PrintStream(os);BufferedReader bf=new BufferedReader(new InputStreamReader(is));){
				//recuperamos la cadena de búsqueda
				String cad=bf.readLine();
				//recogemos contactos
				GestionAgenda agenda=new GestionAgenda();
				List<Contacto> contactos=agenda.recuperar(cad);
				//transformamos la lista de contactos en una cadena JSON
				String respuesta=formatearJSON(contactos);
				//enviamos cadena al cliente
				salida.println(respuesta);
				System.out.println(respuesta);
			}
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		finally {
			try {
				sc.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private String formatearJSON(List<Contacto> contacto) {
		JSONArray array=new JSONArray();
		contacto.forEach(c->{
			JSONObject ob=new JSONObject();
			ob.put("nombre", c.getNombre());
			ob.put("email", c.getEmail());
			ob.put("telefono", c.getTelefono());
			array.add(ob);
		});
		return array.toJSONString();
	}
	
}
