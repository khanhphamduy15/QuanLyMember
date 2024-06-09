package ql;

import java.awt.EventQueue;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;
import objects.MemberObject;
import sms.member.MemberFunction;
import sms.member.MemberFunctionImpl;
import util.ConnectionPool;
import util.ConnectionPoolImpl;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class table_demo {

	private JFrame frame;
	private JTextField txtID;
	private JLabel lblNewLabel_1;
	private JTextField txtName;
	private JLabel lblNewLabel_2;
	private JTextField txtAddress;
	private JLabel lblNewLabel_3;
	private JTextField txtPhone;
	private JLabel lblNewLabel_4;
	private JTextField txtEmail;
	private JTable table;
	DefaultTableModel model;
	private JPanel panel;
	private JLabel lblNewLabel_5;
	private JPanel panel_1;
	private JTextField txtSearch;
	private JLabel lblNewLabel_6;
	private JButton btnClear;

	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					table_demo window = new table_demo();
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
	public table_demo() {
		initialize();
		table_load();
	}
	
	
	PreparedStatement pst;
	ResultSet rs;
	
	public void table_load() {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlymem", "root", "root");
			pst = con.prepareStatement("SELECT * from members");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(119, 136, 153));
		frame.setBounds(100, 100, 1006, 584);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(24, 83, 313, 297);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(30, 32, 73, 43);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		txtID = new JTextField();
		txtID.setBounds(98, 43, 175, 26);
		panel.add(txtID);
		txtID.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(30, 85, 73, 43);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		txtName = new JTextField();
		txtName.setBounds(98, 96, 175, 26);
		panel.add(txtName);
		txtName.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Address");
		lblNewLabel_2.setBounds(30, 138, 73, 43);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		txtAddress = new JTextField();
		txtAddress.setBounds(98, 149, 175, 26);
		panel.add(txtAddress);
		txtAddress.setColumns(10);
		
		lblNewLabel_3 = new JLabel("Phone");
		lblNewLabel_3.setBounds(30, 191, 73, 43);
		panel.add(lblNewLabel_3);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		txtPhone = new JTextField();
		txtPhone.setBounds(98, 202, 175, 26);
		panel.add(txtPhone);
		txtPhone.setColumns(10);
		
		lblNewLabel_4 = new JLabel("Email");
		lblNewLabel_4.setBounds(30, 244, 73, 43);
		panel.add(lblNewLabel_4);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		txtEmail = new JTextField();
		txtEmail.setBounds(98, 255, 175, 26);
		panel.add(txtEmail);
		txtEmail.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConnectionPool cp = new ConnectionPoolImpl();
				MemberFunction mf = new MemberFunctionImpl(cp);
				MemberObject mo = new MemberObject();	
				if (txtID.getText().equals("") || txtName.getText().equals("") || txtAddress.getText().equals("") || txtPhone.getText().equals("") || txtEmail.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Must input all field");
				} else {
				mo.setMember_id(Integer.parseInt(txtID.getText()));
				mo.setMember_name(txtName.getText());
				mo.setMember_address(txtAddress.getText());
				mo.setMember_phone(txtPhone.getText());
				mo.setMember_email(txtEmail.getText());
				mf.addMember(mo);		
				JOptionPane.showMessageDialog(null, "Member Added");
				table_load();
				txtID.setText("");
				txtName.setText("");
				txtAddress.setText("");
				txtPhone.setText("");
				txtEmail.setText("");
				txtSearch.setText("");
				txtID.requestFocus();
				}
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAdd.setBounds(24, 405, 85, 43);
		frame.getContentPane().add(btnAdd);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					ConnectionPool cp = new ConnectionPoolImpl();
					MemberFunction mf = new MemberFunctionImpl(cp);
					MemberObject mu = new MemberObject();	
					mu.setMember_id(Integer.parseInt(txtID.getText()));
					mu.setMember_name(txtName.getText());
					mu.setMember_address(txtAddress.getText());
					mu.setMember_phone(txtPhone.getText());
					mu.setMember_email(txtEmail.getText());
					mf.delMember(mu);		
					JOptionPane.showMessageDialog(null, "Member Updated");
					table_load();
					txtID.setText("");
					txtName.setText("");
					txtAddress.setText("");
					txtPhone.setText("");
					txtEmail.setText("");
					txtSearch.setText("");
					txtID.requestFocus();
				}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDelete.setBounds(135, 405, 85, 43);
		frame.getContentPane().add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(441, 10, 548, 520);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		model =new DefaultTableModel();
		Object[] column = {"ID","Name","Address","Phone","Email"};
		Object[] row = new Object[0];
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		lblNewLabel_5 = new JLabel("Member Management");
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblNewLabel_5.setBounds(24, 12, 407, 54);
		frame.getContentPane().add(lblNewLabel_5);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(119, 136, 153));
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(23, 458, 377, 76);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlymem", "root", "root");
					String member_id = txtSearch.getText();
					
					pst = con.prepareStatement("SELECT member_name,member_address,member_phone,member_email from members where member_id=?");
					pst.setString(1, member_id);
					rs = pst.executeQuery();
					
					if(rs.next()==true) {
						String name = rs.getString(1);
						String address = rs.getString(2);
						String phone = rs.getString(3);
						String email = rs.getString(4);
						
						txtID.setText(member_id);
						txtName.setText(name);
						txtAddress.setText(address);
						txtPhone.setText(phone);
						txtEmail.setText(email);
					}
					else 
					{
						txtID.setText("");
						txtName.setText("");
						txtAddress.setText("");
						txtPhone.setText("");
						txtEmail.setText("");
					}
				} catch(SQLException ex) {
					
				}
				
			}
		});
		txtSearch.setBounds(47, 30, 161, 28);
		txtSearch.setColumns(10);
		panel_1.add(txtSearch);
		
		lblNewLabel_6 = new JLabel("ID");
		lblNewLabel_6.setBounds(10, 27, 27, 28);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel_1.add(lblNewLabel_6);
		
		JButton btnNewButton = new JButton("Update");
		btnNewButton.setBounds(262, 21, 85, 43);
		panel_1.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConnectionPool cp = new ConnectionPoolImpl();
				MemberFunction mf = new MemberFunctionImpl(cp);
				MemberObject mu = new MemberObject();	
				mu.setMember_id(Integer.parseInt(txtID.getText()));
				mu.setMember_name(txtName.getText());
				mu.setMember_address(txtAddress.getText());
				mu.setMember_phone(txtPhone.getText());
				mu.setMember_email(txtEmail.getText());
				mf.editMember(mu);		
				JOptionPane.showMessageDialog(null, "Member Updated");
				table_load();
				txtID.setText("");
				txtName.setText("");
				txtAddress.setText("");
				txtPhone.setText("");
				txtEmail.setText("");
				txtSearch.setText("");
				txtID.requestFocus();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtID.setText("");
				txtName.setText("");
				txtAddress.setText("");
				txtPhone.setText("");
				txtEmail.setText("");
				txtID.requestFocus();
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnClear.setBounds(252, 405, 85, 43);
		frame.getContentPane().add(btnClear);
	}
}
