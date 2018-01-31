package comunicaciones;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import bbdd.GestionPedidos;
import beans.Pedido;

public class HiloAltaPedidos implements Runnable{
	private Socket sc;
	public HiloAltaPedidos(Socket sc) {
		this.sc=sc;
	}
	@Override
	public void run() {
		try {
			//crear canales entrada/salida
			InputStream is=sc.getInputStream();
			OutputStream os=sc.getOutputStream();
			try(PrintStream salida=new PrintStream(os);BufferedReader bf=new BufferedReader(new InputStreamReader(is));){
				//Recuperamos el objeto JSON
				JSONParser parser = new JSONParser();
				JSONObject jpedido = (JSONObject)parser.parse(new InputStreamReader(is));
				//recogemos contacto
				Pedido p = new Pedido(0,jpedido.get("producto").toString(),Integer.parseInt(jpedido.get("unidades").toString()),
						sc.getInetAddress().getHostAddress(), LocalDateTime.parse(jpedido.get("fecha").toString()));
				GestionPedidos pedidos=new GestionPedidos();
			
				pedidos.grabarPedido(p);
			}
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
