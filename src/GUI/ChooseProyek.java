package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import Controller.Session;
public class ChooseProyek extends JFrame {

	private JPanel contentPane;
	Session session;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChooseProyek frame = new ChooseProyek(new Session("GAGAL"));
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
	public ChooseProyek(Session session) throws SQLException, ClassNotFoundException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 469, 465);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.session = session;
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = DriverManager.getConnection("jdbc:sqlserver://10.100.70.70\\PBD2017;user=i14039;password=i14039;database=i14039");
		Statement sta = conn.createStatement();
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChooseProyek.this.dispose();
			}
		});
		btnCancel.setBounds(34, 364, 97, 25);
		contentPane.add(btnCancel);
		
		JButton btnApplyJob = new JButton("Apply Job");
		btnApplyJob.setBounds(309, 364, 97, 25);
		contentPane.add(btnApplyJob);
		
		JPanel panel = new JPanel();
		panel.setBounds(34, 38, 372, 298);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNamaProyek = new JLabel("Nama Proyek");
		lblNamaProyek.setBounds(23, 39, 97, 16);
		panel.add(lblNamaProyek);
		
		JLabel lblOwner = new JLabel("Owner");
		lblOwner.setBounds(23, 86, 56, 16);
		panel.add(lblOwner);
		
		JLabel lblLimitPegawai = new JLabel("Limit Pegawai");
		lblLimitPegawai.setBounds(23, 131, 85, 16);
		panel.add(lblLimitPegawai);
		
		JLabel lblGaji = new JLabel("Gaji");
		lblGaji.setBounds(23, 180, 56, 16);
		panel.add(lblGaji);
		
		JLabel lblWaktuIklan = new JLabel("Waktu Iklan");
		lblWaktuIklan.setBounds(23, 230, 97, 16);
		panel.add(lblWaktuIklan);
		
		String sql = "select * from proyek where idProyek = "+session.username;
		ResultSet rs = sta.executeQuery(sql);
		
		rs.next();
		
		JLabel isiNamaProyek = new JLabel("");
		isiNamaProyek.setBounds(154, 39, 194, 16);
		panel.add(isiNamaProyek);
		isiNamaProyek.setText(rs.getString("namaProyek"));
		
		JLabel isiOwner = new JLabel("");
		isiOwner.setBounds(154, 86, 194, 16);
		panel.add(isiOwner);
		isiOwner.setText(rs.getString("idPengusaha"));
		
		JLabel isiLimitPegawai = new JLabel("");
		isiLimitPegawai.setBounds(154, 131, 196, 16);
		panel.add(isiLimitPegawai);
		isiLimitPegawai.setText(rs.getString("limitPekerja"));
		
		JLabel isiGaji = new JLabel("");
		isiGaji.setBounds(154, 180, 194, 16);
		panel.add(isiGaji);
		isiGaji.setText(rs.getString("bayar"));
		
		JLabel isiWaktuIklan = new JLabel("");
		isiWaktuIklan.setBounds(154, 230, 194, 16);
		panel.add(isiWaktuIklan);
		isiWaktuIklan.setText(rs.getString("waktuPengiklanan"));
	}
}
