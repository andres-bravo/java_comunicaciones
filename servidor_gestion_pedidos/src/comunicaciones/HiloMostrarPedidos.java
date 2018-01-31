package comunicaciones;

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

import bbdd.GestionPedidos;
import beans.Pedido;

public class HiloMostrarPedidos implements Runnable {
	private Socket sc;
	public HiloMostrarPedidos(Socket sc) {
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
				GestionPedidos pedidos=new GestionPedidos();
				List<Pedido> lped=pedidos.recuperarPedidos();
				//transformamos la lista de contactos en una cadena JSON
				String respuesta=formatearJSON(lped);
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
	private String formatearJSON(List<Pedido> lped) {
		JSONArray array=new JSONArray();
		lped.forEach(p->{
			JSONObject ob=new JSONObject();
			ob.put("idPedido", p.getIdPedido());
			ob.put("producto", p.getProducto());
			ob.put("unidades",p.getUnidades());
			ob.put("ipCliente", p.getIpCliente());
			ob.put("fecha", p.getFecha());
			array.add(ob);
		});
		return array.toJSONString();
	}

}
