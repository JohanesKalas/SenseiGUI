package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.Session;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import Controller.Session;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
public class HomePengusahaGUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	Session session;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePengusahaGUI frame = new HomePengusahaGUI(new Session("GAGAL"));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void setSession(Session session){
		this.session = session;
	}
	
	/**
	 * Create the frame.
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public HomePengusahaGUI(Session session) throws ClassNotFoundException, SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 676, 453);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.session = session;
		
		JLabel lblWelcome = new JLabel("Welcome, ");
		lblWelcome.setBounds(12, 13, 69, 16);
		contentPane.add(lblWelcome);
		
		JLabel lblPengusaha = new JLabel(session.username);
		lblPengusaha.setBounds(79, 13, 86, 16);
		contentPane.add(lblPengusaha);
		
		JLabel lblSenseicom = new JLabel("SENSEI.COM");
		lblSenseicom.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSenseicom.setBounds(12, 56, 118, 16);
		contentPane.add(lblSenseicom);
		
		Object[][] obj = new Object[20][5];
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = DriverManager.getConnection("jdbc:sqlserver://10.100.70.70\\PBD2017;user=i14039;password=i14039;database=i14039");
		Statement sta = conn.createStatement();
		
		int idPengusaha;
		String sql = "select * from pengusaha where nama = '"+session.username+"'";
		ResultSet rs;
		rs = sta.executeQuery(sql);
		rs.next();
		idPengusaha = Integer.parseInt(rs.getString("idPengusaha"));
		
		sql = "select * from proyek where idPengusaha = "+idPengusaha;
		
		rs = sta.executeQuery(sql);
		int i = 0;
		while(rs.next()){
			obj[i][0] = rs.getString("namaProyek");
			obj[i][1] = session.username;
			obj[i][2] = rs.getString("bayar");
			obj[i][3] = rs.getString("limitPekerja");
			obj[i][4] = rs.getString("waktuPengiklanan");
			i++;
		}
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			obj,
			new String[] {
				"Nama Proyek", "Owner", "Gaji", "Limit Pekerja", "Waktu Upload"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(245);
		table.getColumnModel().getColumn(1).setPreferredWidth(159);
		table.getColumnModel().getColumn(2).setPreferredWidth(151);
		table.getColumnModel().getColumn(3).setPreferredWidth(90);
		table.getColumnModel().getColumn(4).setPreferredWidth(146);
		table.setBounds(630, 310, 507, 254);
		contentPane.add(table);
		
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(12, 85, 470, 248);
		contentPane.add(scrollPane);
		
		JButton btnTambahProyek = new JButton("Tambah Proyek");
		btnTambahProyek.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					tambahProyek tp = new tambahProyek(session);
					tp.setVisible(true);
					HomePengusahaGUI.this.dispose();
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnTambahProyek.setBounds(494, 89, 137, 25);
		contentPane.add(btnTambahProyek);
		
		JButton btnEditProyek = new JButton("Edit Proyek");
		btnEditProyek.setBounds(494, 128, 137, 25);
		contentPane.add(btnEditProyek);
		
		
	}
}
