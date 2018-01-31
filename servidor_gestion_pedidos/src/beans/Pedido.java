package beans;

import java.time.LocalDateTime;

public class Pedido {
	private int idPedido;
	private String producto;
	private int unidades;
	private String ipCliente;
	private LocalDateTime fecha;
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
	public int getUnidades() {
		return unidades;
	}
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	public String getIpCliente() {
		return ipCliente;
	}
	public void setIpCliente(String ipCliente) {
		this.ipCliente = ipCliente;
	}
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	
	public Pedido(int idPedido, String producto, int unidades, String ipCliente, LocalDateTime fecha) {
		super();
		this.idPedido = idPedido;
		this.producto = producto;
		this.unidades = unidades;
		this.ipCliente = ipCliente;
		this.fecha = fecha;
	}
}
