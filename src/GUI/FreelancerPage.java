package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import Controller.Session;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Panel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Button;

import Controller.*;

public class FreelancerPage extends JFrame {

	private JPanel contentPane;
	private JTextField userField;
	private JTextField passwordField;
	private JTextField namaField;
	private JTextField usernameField;
	private JTextField passField;
	private JTextField emailField;
	private JTextField alamatField;
	Session session;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FreelancerPage frame = new FreelancerPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public FreelancerPage() throws SQLException, ClassNotFoundException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 579, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Panel panel = new Panel();
		panel.setBounds(289, 11, 266, 340);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Silahkan daftar jika belum punya akun");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(26, 40, 217, 14);
		panel.add(lblNewLabel);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setBounds(98, 287, 81, 23);
		panel.add(btnRegister);
		
		JLabel lblNama = new JLabel("Nama");
		lblNama.setBounds(26, 86, 46, 14);
		panel.add(lblNama);
		
		JLabel lblUsername_1 = new JLabel("Username");
		lblUsername_1.setBounds(26, 111, 63, 14);
		panel.add(lblUsername_1);
		
		JLabel lblPassword_1 = new JLabel("Password");
		lblPassword_1.setBounds(26, 139, 46, 14);
		panel.add(lblPassword_1);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(26, 164, 46, 14);
		panel.add(lblEmail);
		
		JLabel lblAlamat = new JLabel("Alamat");
		lblAlamat.setBounds(26, 189, 46, 14);
		panel.add(lblAlamat);
		
		namaField = new JTextField();
		namaField.setBounds(98, 83, 145, 20);
		panel.add(namaField);
		namaField.setColumns(10);
		
		usernameField = new JTextField();
		usernameField.setBounds(98, 108, 145, 20);
		panel.add(usernameField);
		usernameField.setColumns(10);
		
		passField = new JPasswordField();
		passField.setBounds(98, 136, 145, 20);
		panel.add(passField);
		passField.setColumns(10);
		
		emailField = new JTextField();
		emailField.setBounds(98, 161, 145, 20);
		panel.add(emailField);
		emailField.setColumns(10);
		
		alamatField = new JTextField();
		alamatField.setBounds(98, 187, 145, 20);
		panel.add(alamatField);
		alamatField.setColumns(10);
		
		JLabel lblNegara = new JLabel("Negara");
		lblNegara.setBounds(26, 214, 46, 14);
		panel.add(lblNegara);
		
		JLabel lblKota = new JLabel("Kota");
		lblKota.setBounds(26, 239, 46, 14);
		panel.add(lblKota);
		
		JComboBox kota = new JComboBox();
		kota.setBounds(98, 236, 145, 20);
		panel.add(kota);
		
		JComboBox negara = new JComboBox();
		negara.setBounds(98, 211, 145, 20);
		panel.add(negara);
		
		Panel panel_1 = new Panel();
		panel_1.setBounds(10, 11, 273, 340);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(28, 94, 81, 14);
		panel_1.add(lblUsername);
		
		userField = new JTextField();
		userField.setBounds(119, 91, 131, 20);
		panel_1.add(userField);
		userField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(28, 125, 81, 14);
		panel_1.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(119, 122, 131, 20);
		panel_1.add(passwordField);
		passwordField.setColumns(10);
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = DriverManager.getConnection("jdbc:sqlserver://10.100.70.70\\PBD2017;user=i14039;password=i14039;database=i14039");
		Statement sta = conn.createStatement();
		
		String sql = "select * from city";
		ResultSet rs = sta.executeQuery(sql);
		while(rs.next()){
			kota.addItem(rs.getString("nama"));
		}
		
		sql = "select * from country";
		rs = sta.executeQuery(sql);
		while(rs.next()){
			negara.addItem(rs.getString("nama"));
		}
		
		btnRegister.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					String sql1 = "exec registerFreelancer '"+usernameField.getText()+"','"+passField.getText()+"','"+namaField.getText()+"','"+emailField.getText()+"','"+alamatField.getText()+"',"+(kota.getSelectedIndex()+1)+","+(negara.getSelectedIndex()+1);
					if(sta.execute(sql1)){
						JOptionPane.showMessageDialog(rootPane, "Registrasi Berhasil!");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = userField.getText();
				String password = passwordField.getText();
				
				String sql = "exec loginFreelancer '"+username+"','"+password+"'";
				ResultSet rs;
				try {
					rs = sta.executeQuery(sql);
					rs.next();
					if(rs.getString("nama")!=null){
						JOptionPane.showMessageDialog(rootPane, "Success!");
						session = new Session(rs.getString("nama"));
						SenseiHomeGUI home = new SenseiHomeGUI(session);
						home.setSession(session);
						home.setVisible(true);
					}
					else{
						
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(rootPane, "Username atau Password salah!");
				}
			}
		});
		btnLogin.setBounds(94, 196, 81, 23);
		panel_1.add(btnLogin);
		
		JLabel lblSelamatDatang = new JLabel("Selamat mencari pekerjaan");
		lblSelamatDatang.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSelamatDatang.setBounds(59, 35, 167, 22);
		panel_1.add(lblSelamatDatang);

	}
}
