package presentacion;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import beans.Pedido;

public class AdaptadorTabla extends AbstractTableModel {
	private List<Pedido> pedidos;
	

	public AdaptadorTabla(List<Pedido> pedidos) {
		super();
		this.pedidos = pedidos;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return pedidos.size();
	}

	@Override
	public Object getValueAt(int fila, int col) {
		String res="";
		switch(col) {
			case 0:
				res= pedidos.get(fila).getProducto();
				break;
			case 1:
				res= pedidos.get(fila).getIpCliente();
				break;
			case 2:
				res= pedidos.get(fila).getUnidades()+"";
				break;
			case 3:
				res=pedidos.get(fila).getFecha().toString();
		}
		return res;
		
	}

	@Override
	public String getColumnName(int arg0) {
		String res="";
		switch(arg0) {
			case 0:
				res= "Producto";
				break;
			case 1:
				res= "IP Cliente";
				break;
			case 2:
				res= "Unidades";
				break;
			case 3:
				res="Fecha";
				break;
		}
		return res;
	}
	
	
	

}
