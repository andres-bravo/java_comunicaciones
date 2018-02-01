package bbdd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import beans.Pedido;

public class GestionPedidos {
	private String cadena,user,pwd;
	public GestionPedidos() {
		//Ruta clase
		//String fichero = "D:\\manana\\AndresBravo\\comunicaciones\\servidor_gestion_pedidos\\src\\config\\BBDDConnection.txt";
		//Ruta casa
		String fichero = "/Users/abravo/git/java_comunicaciones/servidor_gestion_pedidos/src/config/BBDDConnection.txt";
		try(FileReader fr = new FileReader(fichero);
				BufferedReader bf = new BufferedReader(fr)) {
			//Manera Cl�sica
			String s;
			System.out.println("Entro en constructor.");
			while ((s=bf.readLine())!=null){
				//System.out.println(s);
				if (s.contains("user")) {
					user = s.substring(s.indexOf("|")+1);
				}
				else if (s.contains("pwd")){
					pwd = s.substring(s.indexOf("|")+1);
				}
				else if (s.contains("conexion")) {
					cadena = s.substring(s.indexOf("|")+1);;
				}
			}

		}	
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void grabarPedido(Pedido p) {
		//graba Pedido en BBDD
		//Conexion la BBDD
		try(Connection cn= DriverManager.getConnection(cadena,user,pwd)){ 
			String sql="insert into pedidos(producto,unidades,ipcliente,fecha) values(?,?,?,?)";
			PreparedStatement ps= cn.prepareStatement(sql);
			ps.setString(1, p.getProducto());
			ps.setInt(2, p.getUnidades());
			ps.setString(3, p.getIpCliente());
			ps.setTimestamp(4, Timestamp.valueOf(p.getFecha()));
			//Trazas
			System.out.println("grabarPedido parametros query");
			System.out.println(p.getProducto() + p.getUnidades() + p.getIpCliente() + Timestamp.valueOf(p.getFecha()));
			ps.execute();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	public List<Pedido> recuperarPedidos(){
		ArrayList<Pedido> lped = new ArrayList<>();
		//Conexion a la BBDD
		try(Connection cn= DriverManager.getConnection(cadena,user,pwd)){
			String sql="select * from pedidos order by idPedido";
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);			
			while (rs.next()){			

				Pedido p = new Pedido(rs.getInt("idPedido"),rs.getString("producto"),rs.getInt("unidades"),rs.getString("ipCliente"),
						rs.getTimestamp("fecha").toLocalDateTime());			
				lped.add(p);
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lped;		
	}

}
