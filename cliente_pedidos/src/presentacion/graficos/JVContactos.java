package presentacion.graficos;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import beans.Pedido;
import comunicaciones.ClientePedidos;
import presentacion.adaptadores.AdaptadorTabla;

public class JVContactos extends JFrame {

	private JPanel contentPane;
	private JTable tbContactos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JVContactos frame = new JVContactos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JVContactos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JVContactos.this.dispose();
			}
		});
		btnSalir.setBounds(170, 227, 89, 23);
		contentPane.add(btnSalir);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 58, 414, 114);
		contentPane.add(scrollPane);
		
		tbContactos = new JTable();
		scrollPane.setViewportView(tbContactos);
		
		JButton btnBuscarPedidos = new JButton("Buscar Pedidos");
		btnBuscarPedidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//carga del JTable
				//GestionAgenda agenda=new GestionAgenda();
				ClientePedidos gestpedidos = new ClientePedidos();
				List<Pedido> pedidos=gestpedidos.recuperarTodos();
				//creamos objeto adaptador
				AdaptadorTabla adp=new AdaptadorTabla(pedidos);
				tbContactos.setModel(adp);
			}
		});
		btnBuscarPedidos.setBounds(10, 11, 127, 23);
		contentPane.add(btnBuscarPedidos);
		
		this.setVisible(true);
	}
}
