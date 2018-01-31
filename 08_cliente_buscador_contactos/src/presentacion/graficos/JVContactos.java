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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import beans.Contacto;
import comunicaciones.ClienteBuscadorContactos;
import presentacion.adaptadores.AdaptadorTabla;

public class JVContactos extends JFrame {

	private JPanel contentPane;
	private JTable tbContactos;
	private JTextField textContacto;

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
		

		
		textContacto = new JTextField();
		textContacto.setBounds(10, 11, 86, 20);
		contentPane.add(textContacto);
		textContacto.setColumns(10);
		
		JButton btnBuscarContactos = new JButton("Buscar Contactos");
		btnBuscarContactos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//carga del JTable
				//GestionAgenda agenda=new GestionAgenda();
				ClienteBuscadorContactos agenda = new ClienteBuscadorContactos();
				String cad=textContacto.getText();
				List<Contacto> contactos=agenda.recuperarTodos(cad);
				//creamos objeto adaptador
				AdaptadorTabla adp=new AdaptadorTabla(contactos);
				tbContactos.setModel(adp);
			}
		});
		btnBuscarContactos.setBounds(102, 10, 127, 23);
		contentPane.add(btnBuscarContactos);
		
		this.setVisible(true);
	}
}
