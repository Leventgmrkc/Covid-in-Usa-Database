package com.levent.demo;
import java.awt.image.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class Main {

	private JFrame frame;
	private JTextField username;
	private JPasswordField password;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 139, 139));
		frame.setBounds(100, 100, 686, 548);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		username = new JTextField();
		username.setBounds(267, 152, 153, 29);
		frame.getContentPane().add(username);
		username.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(267, 222, 153, 29);
		frame.getContentPane().add(password);
		
		JLabel lblNewLabel = new JLabel("Username :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(155, 151, 86, 29);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(155, 224, 90, 22);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Login");
		Image ok_img = new ImageIcon(this.getClass().getResource("/okk.png")).getImage();
		btnNewButton.setIcon(new ImageIcon(ok_img));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 	String pw = password.getText();
				 	String usern= username.getText();
				
				if(pw.contains("admin") && usern.contains("admin") ) {
					password.setText(null);
					username.setText(null);
					
					ShowTable show=new ShowTable();
					show.setVisible(true);
					frame.setVisible(false);
					//Menu.main(null);
				}
				else {
					JOptionPane.showMessageDialog(null,"Invalid Login Information","Login Error", JOptionPane.ERROR_MESSAGE);
					password.setText(null);
					username.setText(null);
				}
				
				
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(152, 322, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Clear");
		Image clear_img = new ImageIcon(this.getClass().getResource("/clear.png")).getImage();
		btnNewButton_1.setIcon(new ImageIcon(clear_img));
		
		
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				password.setText(null);
				username.setText(null);
				
			}
		});
		btnNewButton_1.setBounds(282, 323, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Exit");
		Image exit_img = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		btnNewButton_2.setIcon(new ImageIcon(exit_img));
		
		
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null,"Confirm if you want to exit","Login System", JOptionPane.YES_NO_CANCEL_OPTION)==JOptionPane.YES_NO_OPTION){
					System.exit(0);
				}
				
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_2.setBounds(412, 323, 89, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setIconTextGap(10);
		btnNewButton_3.setIcon(new ImageIcon("C:\\Users\\leven\\Desktop\\master\\4th\\database\\project\\step3\\Covid-19.png"));
		btnNewButton_3.setBounds(430, 30, 214, 197);
		frame.getContentPane().add(btnNewButton_3);
		
		
		JLabel login_image = new JLabel("\r\n");
		Image login_img = new ImageIcon(this.getClass().getResource("/login.png")).getImage();
		login_image.setIcon(new ImageIcon(login_img));
		login_image.setBounds(0, -19, 212, 200);
		frame.getContentPane().add(login_image);
		
		JLabel lblNewLabel_2 = new JLabel("Welcome to Vaccinvid");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(243, 11, 177, 39);
		frame.getContentPane().add(lblNewLabel_2);
	}
}
