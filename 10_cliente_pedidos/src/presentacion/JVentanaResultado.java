package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import modelo.GestionPedidos;

public class JVentanaResultado extends JFrame {

	private JPanel contentPane;
	private JTable jTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JVentanaResultado frame = new JVentanaResultado();
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
	public JVentanaResultado() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane jScroll = new JScrollPane();
		jScroll.setBounds(48, 65, 386, 135);
		contentPane.add(jScroll);
		
		jTable = new JTable();
		jScroll.setViewportView(jTable);
		GestionPedidos gpedidos=new GestionPedidos();
		jTable.setModel(new AdaptadorTabla(gpedidos.recuperarPedidos()));
		
		//Hilo que refresca la tabla
		Runnable r=()->{
			while(true) {
				jTable.setModel(new AdaptadorTabla(gpedidos.recuperarPedidos()));
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		new Thread(r).start(); //ponemos hilo en ejecución
	}
}
