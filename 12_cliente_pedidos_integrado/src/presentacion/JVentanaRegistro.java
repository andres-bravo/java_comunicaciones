package presentacion;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import modelo.GestionPedidos;

public class JVentanaRegistro extends JFrame {

	private JPanel contentPane;
	private JTextField jtfProducto;
	private JTextField jtfUnidades;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JVentanaRegistro frame = new JVentanaRegistro();
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
	public JVentanaRegistro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProducto = new JLabel("Producto:");
		lblProducto.setBounds(34, 37, 68, 14);
		contentPane.add(lblProducto);
		
		jtfProducto = new JTextField();
		jtfProducto.setBounds(113, 34, 86, 20);
		contentPane.add(jtfProducto);
		jtfProducto.setColumns(10);
		
		JLabel lblUnidades = new JLabel("Unidades:");
		lblUnidades.setBounds(34, 87, 68, 14);
		contentPane.add(lblUnidades);
		
		jtfUnidades = new JTextField();
		jtfUnidades.setBounds(113, 84, 86, 20);
		contentPane.add(jtfUnidades);
		jtfUnidades.setColumns(10);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionPedidos gpedidos=new GestionPedidos();
				gpedidos.registrarPedido(jtfProducto.getText(), Integer.parseInt(jtfUnidades.getText()));
			}
		});
		btnEnviar.setBounds(150, 158, 89, 23);
		contentPane.add(btnEnviar);
	}
}
