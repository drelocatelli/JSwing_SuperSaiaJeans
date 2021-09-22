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
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



public class Menu extends JFrame {
	
	public static MainController config = new MainController();
	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private int num_clientes;
	private JTextField textField;
	

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
		
		JButton logoutBtn = new JButton("Sair");
		logoutBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main frame = new Main();
				JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(logoutBtn);
				currentFrame.dispose();
				frame.setVisible(true);
			}
		});
		logoutBtn.setForeground(Color.WHITE);
		logoutBtn.setBackground(Color.RED);
		logoutBtn.setBounds(10, 364, 95, 36);
		panel.add(logoutBtn);
		
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
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 44, 791, 232);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
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
		
		JButton refreshBtn = new JButton("<html>&nbsp;&nbsp;atualizar</html>");
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
		refreshBtn.setIcon(new ImageIcon(getClass().getClassLoader().getResource("refresh-icon.png")));
		refreshBtn.setHorizontalAlignment(SwingConstants.RIGHT);
		refreshBtn.setBounds(664, 10, 117, 23);
		panel_1.add(refreshBtn);
		
		JButton insertCBtn = new JButton("<html>&nbsp;&nbsp;adicionar</html>");
		insertCBtn.setIcon(new ImageIcon(getClass().getClassLoader().getResource("insert-icon.png")));
		insertCBtn.setHorizontalAlignment(SwingConstants.RIGHT);
		insertCBtn.setBounds(530, 10, 117, 23);
		panel_1.add(insertCBtn);
		
		JPanel panel_7 = new JPanel();
		textField = new JTextField();
		textField.setFont(new Font("Verdana", Font.PLAIN, 14));
		String buscarCliente = "Buscar pelo nome";

		panel_7.setOpaque(true);
		panel_7.setBorder(null);
		panel_7.setBounds(10, 291, 668, 92);
		panel_1.add(panel_7);
		panel_7.setLayout(null);
		
		textField.setText(buscarCliente);

		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				textField.selectAll();
			}
		});
		textField.setBounds(97, 25, 325, 43);
		panel_7.add(textField);
		textField.setColumns(10);
		
		JButton insertCBtn_1 = new JButton("<html>&nbsp;&nbsp;&nbsp;&nbsp;filtrar</html>");
		insertCBtn_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		insertCBtn_1.setBounds(432, 25, 117, 43);
		panel_7.add(insertCBtn_1);
		insertCBtn_1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("filter-icon.png")));
		insertCBtn_1.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblClientes = new JLabel("Clientes");
		lblClientes.setBounds(10, 11, 781, 22);
		lblClientes.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_1.add(lblClientes);
		
			
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
		tabbedPane.addTab("Departamento", null, panel_6, null);
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("Vendas", null, panel_5, null);
	}
}
