package ClienteView;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.ClientesController;
import Controller.MainController;
import Painel.Menu;
import Theme.DefaultTheme;
import View.Main;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Robot;

public class addCliente extends JFrame {
	
	public static MainController config = new MainController();

	private JPanel contentPane;
	private JTextField nomeIn;
	private JTextField enderecoIn;
	private JTextField bairroIn;
	private JTextField estadoIn;
	private JTextField cepIn;
	private JTextField telefoneIn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addCliente frame = new addCliente();
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
	public addCliente() {
		// seta tema FlatLaf
		new DefaultTheme();
		setResizable(false);
		setTitle(config.title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 812, 485);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(135, 206, 235));
		panel.setBounds(0, 0, 796, 446);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cadastrar clientes");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 11, 555, 25);
		panel.add(lblNewLabel);
		
		JLabel versionEl = new JLabel(config.title+" "+config.version);
		versionEl.setVerticalAlignment(SwingConstants.BOTTOM);
		versionEl.setHorizontalAlignment(SwingConstants.RIGHT);
		versionEl.setForeground(new Color(95, 158, 160));
		versionEl.setBounds(10, 421, 776, 14);
		panel.add(versionEl);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 50, 796, 302);
		panel.add(panel_1);
			
		JButton cancelBtn = new JButton("cancelar");
		cancelBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cancelBtn.setFont(new Font("Verdana", Font.PLAIN, 13));
		cancelBtn.setBounds(20, 256, 108, 35);
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu frame = new Menu("1");
				JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(cancelBtn);
				currentFrame.dispose();
				frame.setVisible(true);
			}
		});
		panel_1.setLayout(null);
		panel_1.add(cancelBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setOpaque(false);
		scrollPane.setBounds(10, 11, 776, 234);
		panel_1.add(scrollPane);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(null);
		panel_2.setOpaque(false);
		scrollPane.setViewportView(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Nome completo");
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setBounds(10, 22, 447, 14);
		panel_2.add(lblNewLabel_1);
		
		nomeIn = new JTextField();
		nomeIn.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 10) {
					// robô da tecla dinamica
					try {
						Robot robot = new Robot();
						robot.keyPress(KeyEvent.VK_TAB);
					}catch(AWTException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		nomeIn.setBounds(10, 39, 522, 25);
		panel_2.add(nomeIn);
		nomeIn.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Endere\u00E7o");
		lblNewLabel_2.setBounds(10, 82, 342, 14);
		panel_2.add(lblNewLabel_2);
		
		enderecoIn = new JTextField();
		enderecoIn.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 10) {
					// robô da tecla dinamica
					try {
						Robot robot = new Robot();
						robot.keyPress(KeyEvent.VK_TAB);
					}catch(AWTException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		enderecoIn.addActionListener(new ActionListener() {
			public void actionPerformed(KeyEvent e) {
				if(e.getKeyCode() == 10) {
					// robô da tecla dinamica
					try {
						Robot robot = new Robot();
						robot.keyPress(KeyEvent.VK_TAB);
					}catch(AWTException e1) {
						e1.printStackTrace();
					}
				}
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		enderecoIn.setColumns(10);
		enderecoIn.setBounds(10, 98, 754, 25);
		panel_2.add(enderecoIn);
		
		JLabel lblNewLabel_3 = new JLabel("Bairro");
		lblNewLabel_3.setBounds(10, 147, 46, 14);
		panel_2.add(lblNewLabel_3);
		
		bairroIn = new JTextField();
		bairroIn.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 10) {
					// robô da tecla dinamica
					try {
						Robot robot = new Robot();
						robot.keyPress(KeyEvent.VK_TAB);
					}catch(AWTException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		bairroIn.addActionListener(new ActionListener() {
			public void actionPerformed(KeyEvent e) {
				if(e.getKeyCode() == 10) {
					// robô da tecla dinamica
					try {
						Robot robot = new Robot();
						robot.keyPress(KeyEvent.VK_TAB);
					}catch(AWTException e1) {
						e1.printStackTrace();
					}
				}
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		bairroIn.setColumns(10);
		bairroIn.setBounds(10, 164, 332, 25);
		panel_2.add(bairroIn);
		
		JLabel lblNewLabel_3_1 = new JLabel("Cidade");
		lblNewLabel_3_1.setBounds(354, 147, 46, 14);
		panel_2.add(lblNewLabel_3_1);
		
		JTextField cidadeIn = new JTextField();
		cidadeIn.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 10) {
					// robô da tecla dinamica
					try {
						Robot robot = new Robot();
						robot.keyPress(KeyEvent.VK_TAB);
					}catch(AWTException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		cidadeIn.setColumns(10);
		cidadeIn.setBounds(354, 164, 178, 25);
		panel_2.add(cidadeIn);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("UF");
		lblNewLabel_3_1_1.setBounds(542, 147, 46, 14);
		panel_2.add(lblNewLabel_3_1_1);
		
		estadoIn = new JTextField();
		estadoIn.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 10) {
					// robô da tecla dinamica
					try {
						Robot robot = new Robot();
						robot.keyPress(KeyEvent.VK_TAB);
					}catch(AWTException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		estadoIn.setColumns(10);
		estadoIn.setBounds(542, 164, 46, 25);
		panel_2.add(estadoIn);
		
		JLabel lblNewLabel_3_1_1_1 = new JLabel("CEP");
		lblNewLabel_3_1_1_1.setBounds(598, 147, 46, 14);
		panel_2.add(lblNewLabel_3_1_1_1);
		
		JButton nextBtn = new JButton("cadastrar");

		
		cepIn = new JTextField();
		cepIn.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// envia formulario
				if(e.getKeyCode() == 10) {
					nextBtn.doClick();

				}
			}
		});
		cepIn.setColumns(10);
		cepIn.setBounds(598, 164, 166, 25);
		panel_2.add(cepIn);
		
		JLabel lblNewLabel_3_2 = new JLabel("Telefone");
		lblNewLabel_3_2.setBounds(542, 22, 121, 14);
		panel_2.add(lblNewLabel_3_2);
		
		telefoneIn = new JTextField();
		telefoneIn.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 10) {
					// robô da tecla dinamica
					try {
						Robot robot = new Robot();
						robot.keyPress(KeyEvent.VK_TAB);
					}catch(AWTException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		telefoneIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		telefoneIn.setColumns(10);
		telefoneIn.setBounds(542, 39, 222, 25);
		panel_2.add(telefoneIn);
		
		List<String> dados = new ArrayList<String>();

		nextBtn.setFont(new Font("Verdana", Font.PLAIN, 13));
		nextBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] options = {"Salvar", "Cancelar"};
				int msgDialog = JOptionPane.showOptionDialog(null, "Deseja salvar cliente?", "Salvar cliente",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
						null, options, options[0]);
				
				if(msgDialog == 0) {
					dados.add(nomeIn.getText());
					dados.add(telefoneIn.getText());
					dados.add(enderecoIn.getText());
					dados.add(bairroIn.getText());
					dados.add(cidadeIn.getText());
					dados.add(estadoIn.getText());
					dados.add(cepIn.getText());

					ClientesController clientesController = new ClientesController();				
					if(clientesController.cadastrarCliente(dados)) {
						JOptionPane.showMessageDialog(null, "Dados inseridos!", "Status", JOptionPane.INFORMATION_MESSAGE);
						JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(nextBtn);
						Menu menu = new Menu("1");
						currentFrame.dispose();
						menu.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Ocorreu um erro.", "Status", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
		nextBtn.setBounds(666, 256, 108, 35);
		panel_1.add(nextBtn);


	}
}
