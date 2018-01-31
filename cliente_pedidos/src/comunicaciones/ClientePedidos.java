package comunicaciones;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
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

public class ClientePedidos {
	public List<Pedido> recuperarTodos() {

		ArrayList<Pedido> pedidos=new ArrayList<>();
		//Datos del servidor
		//String ip="10.1.1.41";
		//Servidor del profesor
		//String ip="10.1.1.100";
		//Ip casa
		String ip="192.168.1.63";
		int puerto= 9000;
		try {
			//Conexion
			Socket sc= new Socket(ip,puerto);
			//Socket establecido ahora enviar y recibir getInputStream, getOutputStream
			InputStream is = sc.getInputStream();
			OutputStream os=sc.getOutputStream();
			
			try(PrintStream salida = new PrintStream(os);BufferedReader bf = new BufferedReader(new InputStreamReader(is));){
				//Envio de datos
				salida.println("Buscar Pedidos");
				//Cojo la respuesta directamente the InputStreamReader y la muestro
				//System.out.println(bf.readLine());
				JSONParser parser = new JSONParser();
				JSONArray array =(JSONArray)parser.parse(bf.readLine());
				//JSONArray array =(JSONArray)parser.parse(new InputStreamReader(is));
				//Recorremos el array JSON y mostramos los nombres de los contactos
				for(Object ob:array) {
					JSONObject jpedido=(JSONObject)ob;
					System.out.println(jpedido.get("producto").toString()+" | "+Integer.parseInt(jpedido.get("unidades").toString())+
							" | " + jpedido.get("ipCliente").toString() + " | " + LocalDateTime.parse(jpedido.get("fecha").toString()));
					/*
					Pedido p = new Pedido(0,jpedido.get("producto").toString(),Integer.parseInt(jpedido.get("unidades").toString()),
							jpedido.get("ipCliente").toString(), LocalDateTime.parse(jpedido.get("fecha").toString()));
					pedidos.add(p);
					*/
					
				}			
			}
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pedidos;
	}
	
	public void altaPedido(Pedido p) {
		String ip="192.168.1.63";
		int puerto= 8000;
		try {
			//Conexion
			Socket sc= new Socket(ip,puerto);
			//Socket establecido ahora enviar y recibir getInputStream, getOutputStream
			InputStream is = sc.getInputStream();
			OutputStream os=sc.getOutputStream();
			
			try(PrintStream salida = new PrintStream(os);BufferedReader bf = new BufferedReader(new InputStreamReader(is));){
				//Construyo JSONObject del Pedido a grabar
				JSONObject ob=new JSONObject();
				ob.put("idPedido", p.getIdPedido());
				ob.put("producto", p.getProducto());
				ob.put("unidades",p.getUnidades());
				ob.put("ipCliente", p.getIpCliente());
				ob.put("fecha", p.getFecha().toString());
				//Traza
				System.out.println(ob.toJSONString());
				//Envio de datos
				salida.println(ob.toJSONString());				
				}			
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		ClientePedidos cliped = new ClientePedidos();
		//Prueba Alta Pedido
		/*Pedido  ped = new Pedido(0, "articulo 002",20, "", LocalDateTime.now());
		cliped.altaPedido(ped);*/
		//Prueba Recuperar Pedidos
		cliped.recuperarTodos();		
	}
}