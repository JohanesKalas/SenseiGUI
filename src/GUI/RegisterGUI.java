package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class RegisterGUI extends JFrame {

	private JPanel contentPane;
	private JTextField namaField;
	private JTextField usernameField;
	private JTextField emailField;
	private JTextField alamatField;
	private JPasswordField passwordField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() throws SQLException, ClassNotFoundException {
		setTitle("Register Form");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 418, 511);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNama = new JLabel("Nama");
		lblNama.setBounds(32, 29, 56, 16);
		contentPane.add(lblNama);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(32, 71, 89, 16);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(32, 112, 56, 16);
		contentPane.add(lblPassword);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(32, 154, 56, 16);
		contentPane.add(lblEmail);
		
		JLabel lblAlamat = new JLabel("Alamat");
		lblAlamat.setBounds(32, 197, 56, 16);
		contentPane.add(lblAlamat);
		
		JLabel lblNegara = new JLabel("Negara");
		lblNegara.setBounds(32, 241, 56, 16);
		contentPane.add(lblNegara);
		
		JLabel lblKota = new JLabel("Kota");
		lblKota.setBounds(32, 284, 56, 16);
		contentPane.add(lblKota);
		
		namaField = new JTextField();
		namaField.setBounds(150, 26, 219, 22);
		contentPane.add(namaField);
		namaField.setColumns(10);
		
		usernameField = new JTextField();
		usernameField.setBounds(150, 68, 219, 22);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		emailField = new JTextField();
		emailField.setBounds(150, 151, 219, 22);
		contentPane.add(emailField);
		emailField.setColumns(10);
		
		alamatField = new JTextField();
		alamatField.setBounds(150, 194, 219, 22);
		contentPane.add(alamatField);
		alamatField.setColumns(10);
		
		JComboBox negaraComboBox = new JComboBox();
		negaraComboBox.setBounds(150, 238, 219, 22);
		contentPane.add(negaraComboBox);
		
		JComboBox kotaComboBox = new JComboBox();
		kotaComboBox.setBounds(150, 281, 219, 22);
		contentPane.add(kotaComboBox);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(89, 363, 97, 25);
		contentPane.add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI.this.dispose();
			}
		});
		btnCancel.setBounds(223, 363, 97, 25);
		contentPane.add(btnCancel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(150, 109, 219, 22);
		contentPane.add(passwordField);
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = DriverManager.getConnection("jdbc:sqlserver://10.100.70.70\\PBD2017;user=i14039;password=i14039;database=i14039");
		Statement sta = conn.createStatement();
		//String Sql = "exec registerFreelancer "+usernameField.getText()+","+passwordField.getText()+","+namaField.getText()+","+emailField.getText();
		String sql = "select * from city";
		ResultSet rs = sta.executeQuery(sql);
		while(rs.next()){
			kotaComboBox.addItem(rs.getString("nama"));
		}
		
		sql = "select * from country";
		rs = sta.executeQuery(sql);
		while(rs.next()){
			negaraComboBox.addItem(rs.getString("nama"));
		}
		btnSubmit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					String sql1 = "exec registerFreelancer '"+usernameField.getText()+"','"+passwordField.getText()+"','"+namaField.getText()+"','"+emailField.getText()+"','"+alamatField.getText()+"',"+(kotaComboBox.getSelectedIndex()+1)+","+(negaraComboBox.getSelectedIndex()+1);
					if(sta.execute(sql1)){
						JOptionPane.showMessageDialog(rootPane, "Registrasi Berhasil!");
						RegisterGUI.this.dispose();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

}
