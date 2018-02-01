package modelo;




import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import beans.Pedido;

public class GestionPedidos {
	
	public void registrarPedido(String producto,  int unidades) {
		try (Socket sc=new Socket("localhost",8000);
				PrintStream salida=new PrintStream(sc.getOutputStream());){
				JSONObject ob=new JSONObject();
				ob.put("producto", producto);
				ob.put("unidades", unidades);
				salida.println(ob.toJSONString());
		}
		catch(IOException ex) {
			ex.printStackTrace();
		} 
	}
	
	public List<Pedido> recuperarPedidos(){
		ArrayList<Pedido> pedidos=new ArrayList<>();
		try(Socket sc=new Socket("localhost",9000);
				BufferedReader bf=new BufferedReader(new InputStreamReader(sc.getInputStream()))){
			String cadJson=bf.readLine();
			JSONParser parser=new JSONParser();
			JSONArray jar=(JSONArray)parser.parse(cadJson);
			for(int i=0;i<jar.size();i++) {
				JSONObject ob=(JSONObject)jar.get(i);
				pedidos.add(new Pedido(0,
						ob.get("producto").toString(),
						ob.get("ipCliente").toString(),
						Integer.parseInt(ob.get("unidades").toString()),
						LocalDateTime.parse(ob.get("fecha").toString())
						));
			}
		}
		catch(IOException ex) {
			ex.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return pedidos;
		
	}
}
