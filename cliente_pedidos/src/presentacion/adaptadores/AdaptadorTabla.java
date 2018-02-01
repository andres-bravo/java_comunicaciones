package presentacion.adaptadores;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import beans.Contacto;

public class AdaptadorTabla extends AbstractTableModel {
	
	List<Contacto> contactos;
	public AdaptadorTabla(List<Contacto> contactos) {
		this.contactos=contactos;
	}
	

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return contactos.size();
	}

	@Override
	public Object getValueAt(int fila, int col) {
		String valor="";
		switch(col){
			case 0:
				valor=contactos.get(fila).getNombre();
				break;
			case 1:
				valor=contactos.get(fila).getEmail();
				break;
			case 2:
				valor=String.valueOf(contactos.get(fila).getTelefono());
				break;
		}
		
		
		return valor;
	}

	@Override
	public String getColumnName(int arg0) {
		String nombre="";
		switch(arg0) {
			case 0:
				nombre="Nombre";
				break;
			case 1:
				nombre="Email";
				break;
			case 2:
				nombre="Teléfono";
				break;
		}
		return nombre;
	}

	
	
}
