package Painel;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Controller.ClientesController;
import Controller.MainController;
import Database.Connect;
import Models.Clientes;
import View.Main;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class Menu extends JFrame {
	
	public static MainController config = new MainController();
	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private int num_clientes;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
//					frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Menu() {		
		setResizable(false);
		
		setBackground(new Color(135, 206, 235));
		setTitle(config.title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 812, 485);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 235));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel versionEl = new JLabel(String.format("%s - %s", config.title, config.version));
		versionEl.setForeground(new Color(95, 158, 160));
		versionEl.setBounds(10, 421, 776, 14);
		versionEl.setVerticalAlignment(SwingConstants.BOTTOM);
		versionEl.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(versionEl);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(new Color(0, 0, 0));
		tabbedPane.setBackground(new Color(192, 192, 192));
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 17));
		tabbedPane.setBounds(0, 0, 796, 446);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Inicio", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblInicio = new JLabel("Inicio");
		lblInicio.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblInicio.setBounds(10, 11, 107, 22);
		panel.add(lblInicio);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 44, 788, 267);
		scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.add(scrollPane);

		
		JTextPane descriptionEl = new JTextPane();
		descriptionEl.setEditable(false);
		scrollPane.setViewportView(descriptionEl);
		descriptionEl.setText(config.description);
		descriptionEl.setHighlighter(null); // remove selection
		descriptionEl.setCaretPosition(0); // scroll to top
		descriptionEl.setFont(new Font("Arial", Font.PLAIN, 14));
		descriptionEl.setOpaque(false);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Gerencia clientes", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblClientes = new JLabel("Clientes");
		lblClientes.setBounds(10, 11, 781, 22);
		lblClientes.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_1.add(lblClientes);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 44, 791, 232);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(0, 0, 791, 232);
		panel_3.add(scrollPane_1);
		
		String clientes_col[] = {"ID", "Nome", "Endereço", "Bairro", "Cidade", "Estado", "CEP", "Telefone", "Detalhes"};
		DefaultTableModel modelo_1 = new DefaultTableModel();
		modelo_1.setColumnIdentifiers(clientes_col);
		
		table_1 = new JTable();
		table_1.setEnabled(false);
		table_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table_1.setModel(modelo_1);
		
		// carrega modelo tabela
		ClientesController clientes = new ClientesController();
		clientes.loadClientes(modelo_1);

		table_1.setModel(modelo_1);
		num_clientes = table_1.getRowCount();
		
		scrollPane_1.setViewportView(table_1);
		
		JLabel totalEl = new JLabel("Total: "+String.valueOf(num_clientes) + " clientes.");
		totalEl.setHorizontalAlignment(SwingConstants.RIGHT);
		totalEl.setBounds(83, 287, 698, 14);
		panel_1.add(totalEl);
		
		JButton refreshBtn = new JButton("atualizar");
		refreshBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(refreshBtn);
				modelo_1.setRowCount(0);
				clientes.loadClientes(modelo_1);
				JOptionPane.showMessageDialog(null, "Dados atualizados!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
//				currentFrame.dispose();
//				currentFrame.setVisible(true);
			}
		});
		refreshBtn.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\src\\Images\\refresh-icon.png"));
		refreshBtn.setHorizontalAlignment(SwingConstants.RIGHT);
		refreshBtn.setBounds(664, 10, 117, 23);
		panel_1.add(refreshBtn);
		
			
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Gerencia produtos", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblProdutos = new JLabel("Produtos");
		lblProdutos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblProdutos.setBounds(10, 11, 107, 22);
		panel_2.add(lblProdutos);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(72, 60, 382, 157);
		panel_2.add(panel_4);
		
		JLabel lblNewLabel = new JLabel("<html><table><tr><td>aaa</td></tr><tr><td>aaa</td><td>aaa</td></tr></table></html>");
		panel_4.add(lblNewLabel);
		
		JPanel panel_6 = new JPanel();
		tabbedPane.addTab("Gerenciar categorias", null, panel_6, null);
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("Administrar vendas", null, panel_5, null);
	}
}
