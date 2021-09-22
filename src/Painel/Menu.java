package Painel;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Database.*;
import Controller.MainController;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Menu extends JFrame {
	
	public static MainController config = new MainController();

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		
		setBackground(new Color(135, 206, 235));
		setTitle(config.title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 235));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel versionEl = new JLabel(String.format("%s - %s", config.title, config.version));
		versionEl.setHorizontalAlignment(SwingConstants.RIGHT);
		versionEl.setForeground(SystemColor.windowBorder);
		contentPane.add(versionEl, BorderLayout.SOUTH);
	}

}
