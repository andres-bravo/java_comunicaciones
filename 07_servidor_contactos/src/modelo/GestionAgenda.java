package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Contacto;

public class GestionAgenda {
	
	
	public List<Contacto> recuperar(String n) {
		ArrayList<Contacto> contactos=new ArrayList<>();
		try (Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda", "root", "root");){
			String sql="select * from contactos where nombre like '%"+n+"%'";
			Statement st=cn.createStatement();
			ResultSet rs=st.executeQuery(sql);			
			while(rs.next()) {
				Contacto c=new Contacto(rs.getString("nombre"),rs.getString("email"),rs.getInt("telefono"));
				contactos.add(c);
			}
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		return contactos;
	}

}
