package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;

import Controller.MainController;
import Painel.Menu;

import java.awt.Color;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JFrame {
	
	public static MainController config = new MainController();
	public static String title;
	public static String version;

	private JPanel contentPane;
	private JTextField userEl;
	private JPasswordField senhaEl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setResizable(false);
		// import config title
		title = config.title;
		version = config.version;
		
		setTitle(this.title+" - "+this.version);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 567, 355);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(135, 206, 235));
		panel.setBounds(0, 0, 551, 316);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usu\u00E1rio");
		lblNewLabel.setBounds(196, 75, 62, 18);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		panel.add(lblNewLabel);
		
		JLabel titleEl = new JLabel();
		titleEl.setText(title);
		titleEl.setBounds(0, 21, 551, 43);
		titleEl.setForeground(new Color(0, 0, 0));
		titleEl.setFont(new Font("Arial", Font.PLAIN, 18));
		titleEl.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(titleEl);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(196, 145, 52, 18);
		lblSenha.setFont(new Font("Arial", Font.PLAIN, 18));
		panel.add(lblSenha);
		
		senhaEl = new JPasswordField();
		senhaEl.setHorizontalAlignment(SwingConstants.CENTER);
		userEl = new JTextField();
		userEl.setHorizontalAlignment(SwingConstants.CENTER);

		
		JButton loginBtn = new JButton("Acessar");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(userEl.getText().equals(config.login) && String.valueOf(senhaEl.getPassword()).equals(config.password)) {
					dispose();
					Menu menu = new Menu();
					menu.setVisible(true);
//					menu.setExtendedState(menu.getExtendedState() | JFrame.MAXIMIZED_BOTH);
				}else {
					new JOptionPane().showMessageDialog(null, "Usuário e Senha não correspondem!", "Acesso Negado", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		senhaEl.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 10) {
					loginBtn.doClick();
				}
			}
		});
		senhaEl.setBounds(196, 174, 163, 32);
		senhaEl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(senhaEl);
		
		userEl.setBounds(196, 99, 163, 32);
		userEl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(userEl);
		userEl.setColumns(10);
		
		loginBtn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		loginBtn.setBounds(233, 248, 83, 32);
		panel.add(loginBtn);
		
		JLabel versionEl = new JLabel(version);
		versionEl.setBounds(455, 291, 86, 14);
		versionEl.setForeground(new Color(105, 105, 105));
		versionEl.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(versionEl);
	}
}
