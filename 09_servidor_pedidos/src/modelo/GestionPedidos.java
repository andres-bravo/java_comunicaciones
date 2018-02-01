package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import beans.Pedido;

public class GestionPedidos {
	String cadena="jdbc:mysql://localhost:3306/tiendas";
	String user="root";
	String pwd="root";
	public void registrarPedido(String producto, String ipCliente, int unidades) {
		/*try (Connection cn=DriverManager.getConnection(cadena, user, pwd);){							
			//definimos la instrucción SQL y la enviamos a través del objeto Statement
			String sql="Insert into pedidos(producto,ipCliente,unidades,fecha) values(?,?,?,?)";
			PreparedStatement st=cn.prepareStatement(sql);
			st.setString(1, producto);
			st.setString(2, ipCliente);
			st.setInt(3, unidades);
			Timestamp t=Timestamp.valueOf(LocalDateTime.now());
			st.setTimestamp(4, t);
			st.execute();										
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}*/
		registrarPedido(new Pedido(0,producto,ipCliente,unidades,LocalDateTime.now()));
	}
	public void registrarPedido(Pedido p) {
		try (Connection cn=DriverManager.getConnection(cadena, user, pwd);){							
			//definimos la instrucción SQL y la enviamos a través del objeto Statement
			String sql="Insert into pedidos(producto,ipCliente,unidades,fecha) values(?,?,?,?)";
			PreparedStatement st=cn.prepareStatement(sql);
			st.setString(1, p.getProducto());
			st.setString(2, p.getIpCliente());
			st.setInt(3, p.getUnidades());
			Timestamp t=Timestamp.valueOf(p.getFecha());
			st.setTimestamp(4, t);
			st.execute();										
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	public List<Pedido> recuperarPedidos(){
		ArrayList<Pedido> pedidos=new ArrayList<>();
		//creamos la conexión contra la base de datos
				try (Connection cn=DriverManager.getConnection(cadena, user, pwd);){							
					//definimos la instrucción SQL y la enviamos a través del objeto Statement
					String sql="Select * from pedidos ";
					Statement st=cn.createStatement();
					ResultSet rs=st.executeQuery(sql);
					while(rs.next()) {
						pedidos.add(new Pedido(rs.getInt("idPedido"),
								rs.getString("producto"),
								rs.getString("ipCliente"),								
								rs.getInt("unidades"),
								rs.getTimestamp("fecha").toLocalDateTime()));
					}										
				}
				catch(SQLException ex) {
					ex.printStackTrace();
				}
				return pedidos;
	}
}
