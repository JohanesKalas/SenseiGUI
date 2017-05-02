package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Controller.Session;
public class tambahProyek extends JFrame {

	private JPanel contentPane;
	private JTextField namaProyek;
	private JTextField limitPekerja;
	private JTextField gaji;
	Session session;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tambahProyek frame = new tambahProyek(new Session("GAGAL"));
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
	public tambahProyek(Session session) throws ClassNotFoundException, SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 474, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTambahProyek = new JLabel("Tambah Proyek");
		lblTambahProyek.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTambahProyek.setBounds(12, 13, 157, 34);
		contentPane.add(lblTambahProyek);
		
		JLabel lblNamaProyek = new JLabel("Nama Proyek");
		lblNamaProyek.setBounds(12, 60, 90, 16);
		contentPane.add(lblNamaProyek);
		
		JLabel lblNewLabel = new JLabel("Limit Pekerja");
		lblNewLabel.setBounds(12, 89, 90, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblGaji = new JLabel("Gaji");
		lblGaji.setBounds(12, 118, 56, 16);
		contentPane.add(lblGaji);
		
		this.session = session;
		
		namaProyek = new JTextField();
		namaProyek.setBounds(114, 57, 332, 22);
		contentPane.add(namaProyek);
		namaProyek.setColumns(10);
		
		limitPekerja = new JTextField();
		limitPekerja.setBounds(114, 86, 56, 22);
		contentPane.add(limitPekerja);
		limitPekerja.setColumns(10);
		
		gaji = new JTextField();
		gaji.setBounds(142, 115, 132, 22);
		contentPane.add(gaji);
		gaji.setColumns(10);
		
		JLabel lblRp = new JLabel("Rp.");
		lblRp.setBounds(114, 118, 56, 16);
		contentPane.add(lblRp);
		
		JLabel lblSkillYangDi = new JLabel("Skill yang di butuhkan");
		lblSkillYangDi.setBounds(12, 162, 157, 16);
		contentPane.add(lblSkillYangDi);
		
		JComboBox skill1 = new JComboBox();
		skill1.setBounds(12, 191, 100, 22);
		contentPane.add(skill1);
		skill1.addItem("No Skill");
		
		JComboBox skill2 = new JComboBox();
		skill2.setBounds(178, 191, 100, 22);
		contentPane.add(skill2);
		skill2.addItem("No Skill");
		
		JComboBox skill3 = new JComboBox();
		skill3.setBounds(346, 191, 100, 22);
		contentPane.add(skill3);
		skill3.addItem("No Skill");
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = DriverManager.getConnection("jdbc:sqlserver://10.100.70.70\\PBD2017;user=i14039;password=i14039;database=i14039");
		Statement sta = conn.createStatement();
		int idPengusaha;
		String sql = "select * from skill";
		ResultSet rs = sta.executeQuery(sql);
		
		while(rs.next()){
			skill1.addItem(rs.getString("namaSkill"));
			skill2.addItem(rs.getString("namaSkill"));
			skill3.addItem(rs.getString("namaSkill"));
		}
		
		sql = "select * from pengusaha where nama = '"+session.username+"'";
		
		rs = sta.executeQuery(sql);
		rs.next();
		idPengusaha = Integer.parseInt(rs.getString("idPengusaha"));
		
		JButton btnApply = new JButton("Apply");
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String np = namaProyek.getText();
					int limit = Integer.parseInt(limitPekerja.getText());
					int ga = Integer.parseInt(gaji.getText());
					
					int s1 = skill1.getSelectedIndex();
					int s2 = skill2.getSelectedIndex();
					int s3 = skill3.getSelectedIndex();
					
					
					String sq = "exec insertProyek "+idPengusaha+", '"+np+"',"+limit+", "+ga;
					sta.execute(sq); 
					int idProyek;
					sq = "select * from proyek where namaProyek = '"+np+"'";
					
					ResultSet rs = sta.executeQuery(sq);
					rs.next();
					idProyek = Integer.parseInt(rs.getString("idProyek"));
					
					if(s1!=0){
						sq = "exec insertSkillProyek "+idProyek+","+s1;
						sta.execute(sq); 
					}
					if(s2!=0){
						sq = "exec insertSkillProyek "+idProyek+","+s2;
						sta.execute(sq); 
					}
					if(s3!=0){
						sq = "exec insertSkillProyek "+idProyek+","+s3;
						sta.execute(sq); 
					}
					
					JOptionPane.showMessageDialog(rootPane, "Tambah Proyek Berhasil!");
					HomePengusahaGUI hp = new HomePengusahaGUI(session);
					hp.setVisible(true);
					tambahProyek.this.dispose();
					
				} catch (Exception e){
					JOptionPane.showMessageDialog(rootPane, "Tambah Proyek Gagal!");
				}
			}
		});
		btnApply.setBounds(349, 269, 97, 25);
		contentPane.add(btnApply);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					HomePengusahaGUI hp = new HomePengusahaGUI(session);
					hp.setVisible(true);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				tambahProyek.this.dispose();
			}
		});
		btnCancel.setBounds(240, 269, 97, 25);
		contentPane.add(btnCancel);
	}

}
