package presentacion;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import beans.Pedido;
import modelo.GestionPedidos;



public class HiloClienteRecuperacion extends Thread{
	Socket sc;
	public HiloClienteRecuperacion(Socket sc) {
		this.sc=sc;
	}
	public void run() {
		GestionPedidos gpedidos=new GestionPedidos();
		List<Pedido> pedidos=gpedidos.recuperarPedidos();
		//creamos PrintStream para enviar los pedidos
		try(PrintStream salida=new PrintStream(sc.getOutputStream())){
			JSONArray array=new JSONArray();
			pedidos.forEach(t-> {
				//creamos objeto JSONObject y lo guardamos
				//en el array
				JSONObject ob=new JSONObject();
				ob.put("producto", t.getProducto());
				ob.put("ipCliente", t.getIpCliente());
				ob.put("unidades", t.getUnidades());
				ob.put("fecha", t.getFecha().toString());
				array.add(ob);//añadimos los objetos al array
			});
			salida.println(array.toJSONString());
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
}
