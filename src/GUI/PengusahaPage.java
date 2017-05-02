package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import Controller.Session;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;

public class PengusahaPage extends JFrame {

	private JPanel contentPane;
	private JTextField userLoginTF;
	private JPasswordField passwordLoginField;
	private JTextField namaRegisterTF;
	private JTextField usernameRegTF;
	private JPasswordField passwordRegisterField;
	Session session;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PengusahaPage frame = new PengusahaPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public PengusahaPage() throws ClassNotFoundException, SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 579, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Panel panel = new Panel();
		panel.setBounds(10, 10, 262, 333);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblSelamatDatang = new JLabel("Selamat datang");
		lblSelamatDatang.setBounds(76, 40, 196, 16);
		panel.add(lblSelamatDatang);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(12, 85, 85, 16);
		panel.add(lblUsername);
		
		userLoginTF = new JTextField();
		userLoginTF.setBounds(103, 82, 147, 22);
		panel.add(userLoginTF);
		userLoginTF.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(12, 123, 56, 16);
		panel.add(lblPassword);
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = DriverManager.getConnection("jdbc:sqlserver://10.100.70.70\\PBD2017;user=i14039;password=i14039;database=i14039");
		Statement sta = conn.createStatement();
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = userLoginTF.getText();
				@SuppressWarnings("deprecation")
				String password = passwordLoginField.getText();
				
				String sql = "exec loginPengusaha '"+username+"','"+password+"'";
				ResultSet rs;
				try {
					rs = sta.executeQuery(sql);
					rs.next();
					if(rs.getString("nama")!=null){
						JOptionPane.showMessageDialog(rootPane, "Success!");
						session = new Session(rs.getString("nama"));
						HomePengusahaGUI home = new HomePengusahaGUI(session);
						home.setSession(session);
						home.setVisible(true);
					}
					else{
						
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(rootPane, "Username atau Password salah!");
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnLogin.setBounds(76, 177, 97, 25);
		panel.add(btnLogin);
		
		passwordLoginField = new JPasswordField();
		passwordLoginField.setBounds(103, 120, 147, 22);
		panel.add(passwordLoginField);
		
		Panel panel_1 = new Panel();
		panel_1.setBounds(286, 10, 265, 333);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblSilahkanDaftar = new JLabel("Silahkan daftar jika belum punya akun");
		lblSilahkanDaftar.setBounds(25, 41, 228, 16);
		panel_1.add(lblSilahkanDaftar);
		
		JLabel lblNewLabel = new JLabel("Nama");
		lblNewLabel.setBounds(12, 84, 56, 16);
		panel_1.add(lblNewLabel);
		
		JLabel lblUsername_1 = new JLabel("Username");
		lblUsername_1.setBounds(12, 118, 70, 16);
		panel_1.add(lblUsername_1);
		
		JLabel lblPassword_1 = new JLabel("Password");
		lblPassword_1.setBounds(12, 157, 56, 16);
		panel_1.add(lblPassword_1);
		
		namaRegisterTF = new JTextField();
		namaRegisterTF.setBounds(78, 81, 175, 22);
		panel_1.add(namaRegisterTF);
		namaRegisterTF.setColumns(10);
		
		usernameRegTF = new JTextField();
		usernameRegTF.setBounds(78, 115, 175, 22);
		panel_1.add(usernameRegTF);
		usernameRegTF.setColumns(10);
		
		passwordRegisterField = new JPasswordField();
		passwordRegisterField.setBounds(80, 154, 173, 22);
		panel_1.add(passwordRegisterField);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					String sql1 = "exec registerPengusaha '"+namaRegisterTF.getText()+"','"+usernameRegTF.getText()+"','"+passwordRegisterField.getText()+"'";
					if(!sta.execute(sql1)){
						JOptionPane.showMessageDialog(rootPane, "Registrasi Berhasil!");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(rootPane, "Registrasi Gagal!");
					e1.printStackTrace();
				}
			}
		});
		btnRegister.setBounds(91, 209, 97, 25);
		panel_1.add(btnRegister);
		
	}
}
