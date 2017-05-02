package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Session;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class EditProfileFreelancer extends JFrame {

	private JPanel contentPane;
	Session session;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditProfileFreelancer frame = new EditProfileFreelancer(new Session("GAGAL"));
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
	public EditProfileFreelancer(Session session) throws ClassNotFoundException, SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 682, 422);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.session = session;
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = DriverManager.getConnection("jdbc:sqlserver://10.100.70.70\\PBD2017;user=i14039;password=i14039;database=i14039");
		Statement sta = conn.createStatement();
		
		String sql = "select * from Freelancer where nama = "+session.username;
		ResultSet rs = sta.executeQuery(sql);
		rs.next();
		
		JLabel lblProfile = new JLabel("PROFILE");
		lblProfile.setBounds(317, 13, 56, 26);
		contentPane.add(lblProfile);
		
		JLabel lblNama = new JLabel("Nama");
		lblNama.setBounds(34, 52, 56, 16);
		contentPane.add(lblNama);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(34, 91, 90, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(34, 133, 56, 16);
		contentPane.add(lblEmail);
		
		JLabel lblAlamat = new JLabel("Alamat");
		lblAlamat.setBounds(34, 179, 56, 16);
		contentPane.add(lblAlamat);
		
		JLabel lblSkill = new JLabel("Skill");
		lblSkill.setBounds(34, 226, 56, 16);
		contentPane.add(lblSkill);
		
		JButton btnAddSkill = new JButton("Add Skill");
		btnAddSkill.setBounds(427, 337, 97, 25);
		contentPane.add(btnAddSkill);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EditProfileFreelancer.this.dispose();
			}
		});
		btnCancel.setBounds(212, 337, 97, 25);
		contentPane.add(btnCancel);
		
		JLabel isiNama = new JLabel("");
		isiNama.setBounds(184, 52, 423, 16);
		contentPane.add(isiNama);
		isiNama.setText(rs.getString("nama"));
		
		JLabel isiUsername = new JLabel("");
		isiUsername.setBounds(184, 91, 411, 16);
		contentPane.add(isiUsername);
		isiUsername.setText(rs.getString("username"));
		
		JLabel isiEmail = new JLabel("");
		isiEmail.setBounds(184, 133, 423, 16);
		contentPane.add(isiEmail);
		isiEmail.setText(rs.getString("email"));
		
		JLabel isiAlamat = new JLabel("");
		isiAlamat.setBounds(184, 179, 411, 16);
		contentPane.add(isiAlamat);
		isiAlamat.setText(rs.getString("alamat"));
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(184, 223, 115, 22);
		contentPane.add(comboBox);
		comboBox.addItem("No Skill");
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(317, 223, 128, 22);
		contentPane.add(comboBox_1);
		comboBox_1.addItem("No Skill");
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(468, 223, 133, 22);
		contentPane.add(comboBox_2);
		comboBox_2.addItem("No Skill");
		
		String sqlSkill = "select * from skill";
		ResultSet result = sta.executeQuery(sqlSkill);
		
		while(rs.next()){
			comboBox.addItem(result.getString("namaSkill"));
			comboBox_1.addItem(result.getString("namaSkill"));
			comboBox_2.addItem(result.getString("namaSkill"));
		}
	}
}
