package com.levent.demo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ShowTable extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField question1;
	private JTextField question2;
	private JTextField question3;
	private JTextField question4;
	private JTextField min;
	private JTextField max;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowTable frame = new ShowTable();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
    public void display(String query) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vaccination","root","6161");
			 Statement st =con.createStatement();
			 //query="Select * from states";
			 ResultSet rs =st.executeQuery(query);
			 ResultSetMetaData rsdm=rs.getMetaData();
			 DefaultTableModel model=(DefaultTableModel) table.getModel();
			 int cols = rsdm.getColumnCount();
			 String[] colName=new String[cols];
			 for(int i =0;i<cols;i++) {
				 colName[i]=rsdm.getColumnName(i+1);
			 }
			 model.setColumnIdentifiers(colName);
			 String st_code,st_name,st_region;
			 while(rs.next()) {
				 st_code=rs.getString(1);
				 st_name=rs.getString(2);
				 st_region=rs.getString(3);
				 String[] row = {st_code,st_name,st_region};
				 model.addRow(row);
			 }
			 st.close();
			 con.close();
			 
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    }
	/**
	 * Create the frame.
	 * 
	 * 
	 */
	public ShowTable() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1298, 917);
		contentPane = new JPanel();
		contentPane.setForeground(SystemColor.text);
		contentPane.setBackground(new Color(0, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		JButton btnNewButton = new JButton("0. Exit");
		btnNewButton.setBackground(new Color(165, 42, 42));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(JOptionPane.showConfirmDialog(null,"Confirm if you want to exit","Menu System", JOptionPane.YES_NO_CANCEL_OPTION)==JOptionPane.YES_NO_OPTION){
					System.exit(0);
				}
			}
		});
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setBounds(58, 134, 104, 28);
		contentPane.add(btnNewButton);
		
		JButton btnhowManyPeople = new JButton("1.How many people got covid last 7 days at given State Code");
		btnhowManyPeople.setBackground(SystemColor.windowBorder);
		btnhowManyPeople.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String q1=question1.getText();
				String query1="select cases_last_7_days\r\n"
						+ "from get_sick\r\n"
						+ "where state_code ='" +q1+"'";

				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vaccination","root","6161");
					 Statement st =con.createStatement();
					 //query="Select * from states";
					 ResultSet rs =st.executeQuery(query1);
					 ResultSetMetaData rsdm=rs.getMetaData();
					 DefaultTableModel model=(DefaultTableModel) table.getModel();
					 int cols = rsdm.getColumnCount();
					 String[] colName=new String[cols];
					 for(int i =0;i<cols;i++) {
						 colName[i]=rsdm.getColumnName(i+1);
					 }
					 model.setColumnIdentifiers(colName);
					 String st_code,st_name,st_region;
					 while(rs.next()) {
						 st_code=rs.getString(1);
						 String[] row = {st_code};
						 model.addRow(row);
					 }
					 st.close();
					 con.close();
					 
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				question1.setText(null);
				
			}
		});
		btnhowManyPeople.setHorizontalAlignment(SwingConstants.LEFT);
		btnhowManyPeople.setBounds(58, 173, 403, 28);
		contentPane.add(btnhowManyPeople);
		
		JButton btnDisplayThe = new JButton("2. Display state code and the total death where the death rate per 100000 is greater then given number");
		btnDisplayThe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String q2=question2.getText();
				String query2="select st_code_died,total_death\r\n"
						+ "from death,death_rate,died_in\r\n"
						+ "where death_state=state_death AND death_state=died_state AND death_rate_per_100000 > '" +q2+"'";
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vaccination","root","6161");
					 Statement st =con.createStatement();
					 //query="Select * from states";
					 ResultSet rs =st.executeQuery(query2);
					 ResultSetMetaData rsdm=rs.getMetaData();
					 DefaultTableModel model=(DefaultTableModel) table.getModel();
					 int cols = rsdm.getColumnCount();
					 String[] colName=new String[cols];
					 for(int i =0;i<cols;i++) {
						 colName[i]=rsdm.getColumnName(i+1);
					 }
					 model.setColumnIdentifiers(colName);
					 String st_code,st_name,st_region;
					 while(rs.next()) {
						 st_code=rs.getString(1);
						 st_name=rs.getString(2);
						 String[] row = {st_code,st_name};
						 model.addRow(row);
					 }
					 st.close();
					 con.close();
					 
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				question2.setText(null);
				
			}
		});
		btnDisplayThe.setBackground(SystemColor.windowBorder);
		btnDisplayThe.setHorizontalAlignment(SwingConstants.LEFT);
		btnDisplayThe.setBounds(58, 212, 630, 28);
		contentPane.add(btnDisplayThe);
		
		JButton btnDisplayThe_1 = new JButton("3. Display the state region number where the cases last 7 days are greater then given number");
		btnDisplayThe_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String q3 = question3.getText();
				String query3 =" 	Select st_region,count(st_region) as number_of_states \r\n"
						+ " 	From states\r\n"
						+ " 	Where st_name IN ( Select sta_name\r\n"
						+ " 			        From get_sick\r\n"
						+ "					Where   cases_last_7_days>'" +q3+"')"
				        + "       group by st_region; ";
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vaccination","root","6161");
					 Statement st =con.createStatement();
					 //query="Select * from states";
					 ResultSet rs =st.executeQuery(query3);
					 ResultSetMetaData rsdm=rs.getMetaData();
					 DefaultTableModel model=(DefaultTableModel) table.getModel();
					 int cols = rsdm.getColumnCount();
					 String[] colName=new String[cols];
					 for(int i =0;i<cols;i++) {
						 colName[i]=rsdm.getColumnName(i+1);
					 }
					 model.setColumnIdentifiers(colName);
					 String st_code,st_name,st_region;
					 while(rs.next()) {
						 st_code=rs.getString(1);
						 st_name=rs.getString(2);
						 String[] row = {st_code,st_name};
						 model.addRow(row);
					 }
					 st.close();
					 con.close();
					 
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				question3.setText(null);
				
			}
		});
		btnDisplayThe_1.setBackground(SystemColor.windowBorder);
		btnDisplayThe_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnDisplayThe_1.setBounds(58, 250, 565, 28);
		contentPane.add(btnDisplayThe_1);
		
		JButton btnListThe = new JButton("4. List the state names and total deaths \twhere the confirmed death number is greater then given number");
		btnListThe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String q4=question4.getText();
				String query4 = "select death_state,total_death\r\n"
						+ "from death\r\n"
						+ "where confirmed_death >'" +q4+"'";
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vaccination","root","6161");
					 Statement st =con.createStatement();
					 //query="Select * from states";
					 ResultSet rs =st.executeQuery(query4);
					 ResultSetMetaData rsdm=rs.getMetaData();
					 DefaultTableModel model=(DefaultTableModel) table.getModel();
					 int cols = rsdm.getColumnCount();
					 String[] colName=new String[cols];
					 for(int i =0;i<cols;i++) {
						 colName[i]=rsdm.getColumnName(i+1);
					 }
					 model.setColumnIdentifiers(colName);
					 String st_code,st_name,st_region;
					 while(rs.next()) {
						 st_code=rs.getString(1);
						 st_name=rs.getString(2);
						
						 String[] row = {st_code,st_name};
						 model.addRow(row);
					 }
					 st.close();
					 con.close();
					 
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				question4.setText(null);
				
			}
		});
		btnListThe.setBackground(SystemColor.windowBorder);
		btnListThe.setHorizontalAlignment(SwingConstants.LEFT);
		btnListThe.setBounds(58, 289, 630, 28);
		contentPane.add(btnListThe);
		
		JButton btnFindThe = new JButton("5. List the death number and state names");
		btnFindThe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query5="select st_name,total_death\r\n"
						+ "from states,died_in,death\r\n"
						+ "where st_code=st_code_died AND state_death=death_state;";
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vaccination","root","6161");
					 Statement st =con.createStatement();
					 //query="Select * from states";
					 ResultSet rs =st.executeQuery(query5);
					 ResultSetMetaData rsdm=rs.getMetaData();
					 DefaultTableModel model=(DefaultTableModel) table.getModel();
					 int cols = rsdm.getColumnCount();
					 String[] colName=new String[cols];
					 for(int i =0;i<cols;i++) {
						 colName[i]=rsdm.getColumnName(i+1);
					 }
					 model.setColumnIdentifiers(colName);
					 String st_code,st_name,st_region;
					 while(rs.next()) {
						 st_code=rs.getString(1);
						 st_name=rs.getString(2);
						 String[] row = {st_code,st_name};
						 model.addRow(row);
					 }
					 st.close();
					 con.close();
					 
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnFindThe.setBackground(SystemColor.windowBorder);
		btnFindThe.setHorizontalAlignment(SwingConstants.LEFT);
		btnFindThe.setBounds(58, 328, 269, 28);
		contentPane.add(btnFindThe);
		
		JButton btnNewButton_6 = new JButton("6.List the state codes and state names where the case rate per 100000 is more then 8000");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query6="Select st_code , st_name\r\n"
						+ "from states,case_rate\r\n"
						+ "where st_name=case_state AND case_rate_per_100000>8000;";
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vaccination","root","6161");
					 Statement st =con.createStatement();
					 //query="Select * from states";
					 ResultSet rs =st.executeQuery(query6);
					 ResultSetMetaData rsdm=rs.getMetaData();
					 DefaultTableModel model=(DefaultTableModel) table.getModel();
					 int cols = rsdm.getColumnCount();
					 String[] colName=new String[cols];
					 for(int i =0;i<cols;i++) {
						 colName[i]=rsdm.getColumnName(i+1);
					 }
					 model.setColumnIdentifiers(colName);
					 String st_code,st_name,st_region;
					 while(rs.next()) {
						 st_code=rs.getString(1);
						 st_name=rs.getString(2);
						 String[] row = {st_code,st_name};
						 model.addRow(row);
					 }
					 st.close();
					 con.close();
					 
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
		});
		btnNewButton_6.setBackground(SystemColor.windowBorder);
		btnNewButton_6.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_6.setBounds(58, 365, 558, 28);
		contentPane.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("7. List the state names and total death numbers where the probable case is greater then 100000");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query7="select death_state,total_death\r\n"
						+ "from death\r\n"
						+ "where death_state IN ( select state_name\r\n"
						+ "from case_states\r\n"
						+ "where confirmed_case >100000\r\n"
						+ ");";
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vaccination","root","6161");
					 Statement st =con.createStatement();
					 //query="Select * from states";
					 ResultSet rs =st.executeQuery(query7);
					 ResultSetMetaData rsdm=rs.getMetaData();
					 DefaultTableModel model=(DefaultTableModel) table.getModel();
					 int cols = rsdm.getColumnCount();
					 String[] colName=new String[cols];
					 for(int i =0;i<cols;i++) {
						 colName[i]=rsdm.getColumnName(i+1);
					 }
					 model.setColumnIdentifiers(colName);
					 String st_code,st_name,st_region;
					 while(rs.next()) {
						 st_code=rs.getString(1);
						 st_name=rs.getString(2);
						 String[] row = {st_code,st_name};
						 model.addRow(row);
					 }
					 st.close();
					 con.close();
					 
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
		});
		btnNewButton_7.setBackground(SystemColor.windowBorder);
		btnNewButton_7.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_7.setBounds(58, 404, 600, 28);
		contentPane.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("8. List the state number of west regions where the death rate per 100000 is in given range");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String region = "West";
				String minimum = min.getText();
				String maximum = max.getText();
				
				String query8="select count(st_name) as state_number\r\n"
						+ "from states\r\n"
						+ "where st_region ='" +region+"' AND st_name IN(\r\n"
						+ "select died_state\r\n"
						+ "from death_rate\r\n"
						+ "where death_rate_per_100000 > '" +minimum+"' AND death_rate_per_100000 < '" +maximum+"')";
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vaccination","root","6161");
					 Statement st =con.createStatement();
					 //query="Select * from states";
					 ResultSet rs =st.executeQuery(query8);
					 ResultSetMetaData rsdm=rs.getMetaData();
					 DefaultTableModel model=(DefaultTableModel) table.getModel();
					 int cols = rsdm.getColumnCount();
					 String[] colName=new String[cols];
					 for(int i =0;i<cols;i++) {
						 colName[i]=rsdm.getColumnName(i+1);
					 }
					 model.setColumnIdentifiers(colName);
					 String st_code,st_name,st_region;
					 while(rs.next()) {
						 st_code=rs.getString(1);
						
						 String[] row = {st_code};
						 model.addRow(row);
					 }
					 st.close();
					 con.close();
					 
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				min.setText(null);
				max.setText(null);
				
			}
		});
		btnNewButton_8.setBackground(SystemColor.windowBorder);
		btnNewButton_8.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_8.setBounds(58, 443, 545, 28);
		contentPane.add(btnNewButton_8);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(192, 192, 192));
		scrollPane.setBounds(751, 140, 508, 467);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBackground(new Color(222, 184, 135));
		scrollPane.setViewportView(table);
		
		question1 = new JTextField();
		question1.setBounds(483, 177, 50, 20);
		contentPane.add(question1);
		question1.setColumns(10);
		
		question2 = new JTextField();
		question2.setBounds(711, 216, 30, 20);
		contentPane.add(question2);
		question2.setColumns(10);
		
		question3 = new JTextField();
		question3.setBounds(633, 254, 30, 20);
		contentPane.add(question3);
		question3.setColumns(10);
		
		question4 = new JTextField();
		question4.setBounds(698, 293, 30, 20);
		contentPane.add(question4);
		question4.setColumns(10);
		
		min = new JTextField();
		min.setBounds(633, 447, 30, 20);
		contentPane.add(min);
		min.setColumns(10);
		
		max = new JTextField();
		max.setBounds(698, 447, 30, 20);
		contentPane.add(max);
		max.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Min:");
		lblNewLabel.setBounds(613, 450, 50, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Max :");
		lblNewLabel_1.setBounds(673, 450, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Display Menu");
		Image menu_img = new ImageIcon(this.getClass().getResource("/menu.png")).getImage();
		lblNewLabel_2.setIcon(new ImageIcon(menu_img));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(262, 32, 199, 59);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("Clear");
		Image clear2_img = new ImageIcon(this.getClass().getResource("/clear2.png")).getImage();
		btnNewButton_1.setIcon(new ImageIcon(clear2_img));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setModel(new DefaultTableModel());
			}
		});
		btnNewButton_1.setBackground(Color.CYAN);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(806, 630, 132, 44);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_3 = new JLabel("Display Table");
		Image dispaly = new ImageIcon(this.getClass().getResource("/display.png")).getImage();
		lblNewLabel_3.setIcon(new ImageIcon(dispaly));
		
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3.setBounds(946, 89, 163, 40);
		contentPane.add(lblNewLabel_3);
		
		JButton btnNewButton_2 = new JButton("Sign Out");
		Image sign_img = new ImageIcon(this.getClass().getResource("/signout.png")).getImage();
		btnNewButton_2.setIcon(new ImageIcon(sign_img));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					Main window=new Main();
					window.main(null);
					contentPane.setVisible(false);
				
			}
		});
		btnNewButton_2.setBackground(Color.PINK);
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_2.setBounds(586, 764, 142, 49);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Save to Txt File");
		Image save_img = new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		btnNewButton_3.setIcon(new ImageIcon(save_img));
		
		btnNewButton_3.setBackground(new Color(0, 255, 255));
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String filePath="C:\\Users\\leven\\Desktop\\master\\4th\\database\\project\\step3\\project_data.txt";
				File file = new File(filePath);
				try {
					FileWriter fw = new FileWriter(file);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.newLine();
					bw.newLine();
					for(int i=0;i<table.getRowCount();i++) {
						for(int j =0;j<table.getColumnCount();j++) {
							bw.write(table.getValueAt(i, j).toString()+" ");
						}
						bw.newLine();
					}
					bw.close();
					fw.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_3.setBounds(1047, 631, 174, 44);
		contentPane.add(btnNewButton_3);
		
		JPanel panel = new JPanel();
		panel.setBounds(199, 496, 424, 148);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("You can send the vaccines to certain states based on the ");
		lblNewLabel_5.setBounds(10, 65, 392, 14);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("results you get here.");
		lblNewLabel_6.setBounds(10, 90, 119, 14);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_4 = new JLabel("This program is a database system of US covid cases. ");
		lblNewLabel_4.setBounds(10, 19, 392, 35);
		panel.add(lblNewLabel_4);
		
		JButton btnNewButton_4 = new JButton("About");
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				panel.setVisible(false);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panel.setVisible(true);
			}
		});
		Image about_img = new ImageIcon(this.getClass().getResource("/about.png")).getImage();
		btnNewButton_4.setIcon(new ImageIcon(about_img));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_4.setBounds(48, 593, 114, 45);
		contentPane.add(btnNewButton_4);
		
		JPanel panel_1 = new JPanel();
		panel_1.setVisible(false);
		panel_1.setBounds(199, 630, 424, 103);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton_5 = new JButton("How To Use");
		btnNewButton_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_1.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_1.setVisible(false);
			}
		});
		Image guide_img = new ImageIcon(this.getClass().getResource("/guide.png")).getImage();
		btnNewButton_5.setIcon(new ImageIcon(guide_img));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_5.setBounds(48, 662, 142, 44);
		contentPane.add(btnNewButton_5);

		
		JLabel lblNewLabel_7 = new JLabel("Before you choose the option, you should enterreleated info into ");
		lblNewLabel_7.setBounds(10, 11, 404, 14);
		panel_1.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("parallel box if it is necessary. Then click the option.");
		lblNewLabel_8.setBounds(10, 28, 404, 14);
		panel_1.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("After displaying datas, you can save them in txt file. ");
		lblNewLabel_9.setBounds(10, 45, 404, 14);
		panel_1.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Either way before choosing another option you should");
		lblNewLabel_10.setBounds(10, 62, 404, 14);
		panel_1.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel(" clear the display table.");
		lblNewLabel_11.setBounds(10, 78, 404, 14);
		panel_1.add(lblNewLabel_11);
		panel.setVisible(false);
	}
}
