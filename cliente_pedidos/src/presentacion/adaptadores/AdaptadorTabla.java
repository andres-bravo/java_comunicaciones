package presentacion.adaptadores;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import beans.Pedido;

public class AdaptadorTabla extends AbstractTableModel {
	
	List<Pedido> pedidos;
	public AdaptadorTabla(List<Pedido> pedidos) {
		this.pedidos=pedidos;
	}
	

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return pedidos.size();
	}

	@Override
	public Object getValueAt(int fila, int col) {
		String valor="";
		switch(col){
			case 0:
				valor=pedidos.get(fila).getProducto();
				break;
			case 1:
				valor=String.valueOf(pedidos.get(fila).getUnidades());
				break;
			case 2:
				valor=String.valueOf(pedidos.get(fila).getIpCliente());
				break;
			case 3:
				valor=String.valueOf(pedidos.get(fila).getFecha());
				break;			
		}
		
		
		return valor;
	}

	@Override
	public String getColumnName(int arg0) {
		String nombre="";
		switch(arg0) {
			case 0:
				nombre="Producto";
				break;
			case 1:
				nombre="Unidades";
				break;
			case 2:
				nombre="IpCliente";
				break;
			case 3:
				nombre="Fecha";
				break;
		}
		return nombre;
	}

	
	
}
