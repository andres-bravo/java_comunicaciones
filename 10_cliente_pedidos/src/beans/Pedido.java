package beans;

import java.time.LocalDateTime;

public class Pedido {
	private int idPedido;
	private String producto;
	private String ipCliente;
	private int unidades;
	private LocalDateTime fecha;
	public Pedido(int idPedido, String producto, String ipCliente, int unidades, LocalDateTime fecha) {
		super();
		this.idPedido = idPedido;
		this.producto = producto;
		this.ipCliente = ipCliente;
		this.unidades = unidades;
		this.fecha = fecha;
	}
	public int getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	public String getIpCliente() {
		return ipCliente;
	}
	public void setIpCliente(String ipCliente) {
		this.ipCliente = ipCliente;
	}
	public int getUnidades() {
		return unidades;
	}
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	
}
