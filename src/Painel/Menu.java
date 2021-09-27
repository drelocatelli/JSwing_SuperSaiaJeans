package Painel;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Robot;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
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
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;

import ClienteView.addCliente;
import ClienteView.editCliente;
import Controller.ClientesController;
import Controller.MainController;
import Database.Connect;
import Theme.DefaultTheme;
import View.Main;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.SystemColor;
import java.awt.Window;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;



public class Menu extends JFrame {
	
	public static MainController config = new MainController();
	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private int num_clientes;
	private JTextField searchClienteIn;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu("0");
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
	
	// param optional
	public Menu(String tab) {
		// seta tema FlatLaf
		new DefaultTheme();
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
		tabbedPane.setBackground(UIManager.getColor("InternalFrame.inactiveTitleBackground"));
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 17));
		tabbedPane.setBounds(0, 0, 796, 446);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Inicio", null, panel, null);
		panel.setLayout(null);
		
		JButton logoutBtn = new JButton("Sair");
		logoutBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		logoutBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		logoutBtn.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				Main frame = new Main();
				JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(logoutBtn);
				currentFrame.dispose();
				frame.show();
//				frame.setVisible(true);
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
		DefaultTableModel modelo_1 = new DefaultTableModel() {
				@Override
				public boolean isCellEditable(int row, int column){
					return true;
				}
			};
		modelo_1.setColumnIdentifiers(clientes_col);
		
		table_1 = new JTable();
		table_1.setBackground(UIManager.getColor("InternalFrame.borderColor"));
		table_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table_1.setModel(modelo_1);
		
		// carrega modelo tabela
		ClientesController clientes = new ClientesController();
		clientes.loadClientes(modelo_1);

		table_1.setModel(modelo_1);
		num_clientes = table_1.getRowCount();
		
		scrollPane_1.setViewportView(table_1);
		
		JButton refreshBtn = new JButton("<html>&nbsp;&nbsp;recarregar</html>");
		refreshBtn.setFont(new Font("Verdana", Font.PLAIN, 14));
		
		refreshBtn.setIcon(new ImageIcon(getClass().getClassLoader().getResource("refresh-icon.png")));
		refreshBtn.setHorizontalAlignment(SwingConstants.RIGHT);
		refreshBtn.setBounds(646, 9, 135, 29);
		panel_1.add(refreshBtn);
		
		JButton insertCBtn = new JButton("<html>&nbsp;&nbsp;cadastrar</html>");
		insertCBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		insertCBtn.setFont(new Font("Verdana", Font.PLAIN, 14));
		insertCBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(refreshBtn);
				addCliente addClienteFrame = new addCliente();

				currentFrame.dispose();
				addClienteFrame.setVisible(true);
			}
		});
		insertCBtn.setIcon(new ImageIcon(getClass().getClassLoader().getResource("insert-icon.png")));
		insertCBtn.setHorizontalAlignment(SwingConstants.RIGHT);
		insertCBtn.setBounds(355, 9, 135, 29);
		panel_1.add(insertCBtn);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(211, 211, 211));
		
		searchClienteIn = new JTextField();
		searchClienteIn.setBackground(new Color(254, 255, 222));
		JLabel searchClienteLatest = new JLabel("");
		searchClienteIn.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// limpa tabela
				try {
					while (table_1.getModel().getRowCount()>0){
						((DefaultTableModel) table_1.getModel()).removeRow(0);
			        }
				}catch(Exception e1) {
				}
				
				clientes.loadClientesNome(modelo_1, searchClienteIn.getText());
				
				if(!searchClienteIn.getText().equals("")) {
					searchClienteLatest.setText("Última busca: "+searchClienteIn.getText());
				}
			}
		});
		searchClienteIn.setFont(new Font("Verdana", Font.PLAIN, 14));
		String buscarCliente = "Buscar pelo nome";

		panel_7.setOpaque(true);
		panel_7.setBorder(null);
		panel_7.setBounds(0, 276, 791, 135);
		panel_1.add(panel_7);
		panel_7.setLayout(null);
		
		searchClienteIn.setText("Filtrar...");

		searchClienteIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				searchClienteIn.selectAll();
			}
		});
		searchClienteIn.setBounds(51, 45, 683, 36);
		panel_7.add(searchClienteIn);
		searchClienteIn.setColumns(10);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.GRAY);
		panel_8.setBounds(683, -1, 108, 21);
		panel_7.add(panel_8);
		
		JLabel totalEl = new JLabel("Total: "+String.valueOf(num_clientes) + " clientes.");
		panel_8.add(totalEl);
		totalEl.setForeground(Color.WHITE);
		totalEl.setHorizontalAlignment(SwingConstants.RIGHT);
		
		searchClienteLatest.setFont(new Font("Trebuchet MS", Font.PLAIN, 11));
		searchClienteLatest.setBounds(51, 90, 469, 14);
		panel_7.add(searchClienteLatest);
		clientes.loadClientesNome(modelo_1, searchClienteIn.getText());
		
		if(!searchClienteIn.getText().equals("")) {
			searchClienteLatest.setText("Última busca: "+searchClienteIn.getText());
		}
		JButton editClienteBtn = new JButton("<html>&nbsp;&nbsp;editar tabela</html>");
		
		JLabel lblClientes = new JLabel("Clientes");
		lblClientes.setBounds(10, 11, 135, 22);
		lblClientes.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_1.add(lblClientes);
		
		JButton removerBtn = new JButton("<html>&nbsp;&nbsp;remover</html>");	
		removerBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		editClienteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editClienteBtn.setEnabled(false);
				
				table_1.setEnabled(true);
				JOptionPane.showMessageDialog(null, "Tabela desbloqueada para edição!", "Ação", JOptionPane.WARNING_MESSAGE);
				
				// modo sele�ao tabela
				table_1.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent ev) {
						
						// modo edi�ao da tabela
						removerBtn.setEnabled(true);
						
						table_1.getModel().addTableModelListener(new TableModelListener() {
							
							@Override
							public void tableChanged(TableModelEvent e) {
//								System.out.println("Editou coluna: "+table_1.getSelectedColumn()+" Linha:" + table_1.getSelectedRow());
//								System.out.println("Colunas: "+table_1.getColumnCount());
								
								String clientesData[] = new String[table_1.getColumnCount()];
								
								for(int count = 0; count < table_1.getColumnCount(); count++) {
									// dados da tabela
									String tbClientesData = table_1.getModel().getValueAt(table_1.getSelectedRow(), count).toString();
									clientesData[count] = tbClientesData;
								}
								
								// altera dados no banco
								if(clientes.editarCliente(clientesData)) {
									table_1.setEnabled(false);
									editClienteBtn.setEnabled(true);
								}else {
									JOptionPane.showMessageDialog(null, "Ocorreu algum erro e não foi possível salvar!", "Status", JOptionPane.ERROR_MESSAGE);
								}
							}

						});
						
					}
				});
				
			}
		});
		
		removerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// remove linha
				if(clientes.deleteTbClientes(Integer.valueOf((String) table_1.getModel().getValueAt(table_1.getSelectedRow(), 0)))) {
//					table_1.setRowSelectionInterval(table_1.getSelectedRow() - 1, 0);
					removerBtn.setEnabled(false);
					refreshBtn.doClick();
//					((DefaultTableModel) table_1.getModel()).removeRow(table_1.getSelectedRow());
				}
				
				
			}
		});
		
		editClienteBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		editClienteBtn.setIcon(new ImageIcon(getClass().getClassLoader().getResource("edit-icon.png")));
		editClienteBtn.setHorizontalAlignment(SwingConstants.RIGHT);
		editClienteBtn.setFont(new Font("Verdana", Font.PLAIN, 14));
		editClienteBtn.setBounds(170, 9, 175, 29);
		panel_1.add(editClienteBtn);
		
		removerBtn.setEnabled(false);
		removerBtn.setHorizontalAlignment(SwingConstants.RIGHT);
		removerBtn.setIcon(new ImageIcon(getClass().getClassLoader().getResource("trash.png")));
		removerBtn.setFont(new Font("Verdana", Font.PLAIN, 14));
		removerBtn.setBounds(501, 9, 135, 29);
		panel_1.add(removerBtn);
		
			
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Gerencia produtos", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblProdutos = new JLabel("Produtos");
		lblProdutos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblProdutos.setBounds(10, 11, 107, 22);
		panel_2.add(lblProdutos);
		
		JButton editProdutosBtn = new JButton("<html>&nbsp;&nbsp;gerenciar produto</html>");
		editProdutosBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		editProdutosBtn.setIcon(new ImageIcon(getClass().getClassLoader().getResource("edit-icon.png")));
		editProdutosBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		editProdutosBtn.setHorizontalAlignment(SwingConstants.RIGHT);
		editProdutosBtn.setFont(new Font("Verdana", Font.PLAIN, 14));
		editProdutosBtn.setBounds(141, 11, 204, 29);
		panel_2.add(editProdutosBtn);
		
		JButton insertCBtn_1 = new JButton("<html>&nbsp;&nbsp;cadastrar</html>");
		insertCBtn_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		insertCBtn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		insertCBtn_1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("insert-icon.png")));
		insertCBtn_1.setHorizontalAlignment(SwingConstants.RIGHT);
		insertCBtn_1.setFont(new Font("Verdana", Font.PLAIN, 14));
		insertCBtn_1.setBounds(355, 11, 135, 29);
		panel_2.add(insertCBtn_1);
		
		JButton removerBtn_1 = new JButton("<html>&nbsp;&nbsp;remover</html>");
		removerBtn_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		removerBtn_1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("trash.png")));
		removerBtn_1.setHorizontalAlignment(SwingConstants.RIGHT);
		removerBtn_1.setFont(new Font("Verdana", Font.PLAIN, 14));
		removerBtn_1.setEnabled(false);
		removerBtn_1.setBounds(501, 11, 135, 29);
		panel_2.add(removerBtn_1);
		
		JButton refreshBtn_1 = new JButton("<html>&nbsp;&nbsp;recarregar</html>");
		refreshBtn_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		refreshBtn_1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("refresh-icon.png")));
		refreshBtn_1.setHorizontalAlignment(SwingConstants.RIGHT);
		refreshBtn_1.setFont(new Font("Verdana", Font.PLAIN, 14));
		refreshBtn_1.setBounds(646, 11, 135, 29);
		panel_2.add(refreshBtn_1);
		
		JPanel panel_6 = new JPanel();
		tabbedPane.addTab("Departamento", null, panel_6, null);
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("Vendas", null, panel_5, null);
		
		if(Integer.parseInt(tab) > 0) {
			tabbedPane.setSelectedIndex(Integer.parseInt(tab));
		}
	
		
		refreshBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		refreshBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// limpa tabela
				try {
					while (table_1.getModel().getRowCount()>0){
						((DefaultTableModel) table_1.getModel()).removeRow(0);
			        }
					
					JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(logoutBtn);
					currentFrame.dispose();
					Menu frame = new Menu("1");
					frame.setVisible(true);
					JOptionPane.showMessageDialog(null, "Dados atualizados!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
				}catch(Exception e1) {
				}
			}
		});
		
	}
}