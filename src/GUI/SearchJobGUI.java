package GUI;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.ScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import Controller.Session;
public class SearchJobGUI extends JFrame {

	private JPanel contentPane;
	private JTextField searchTF;
	private JTable table;
	Session session;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchJobGUI frame = new SearchJobGUI(new Session("GAGAL"));
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
	public SearchJobGUI(Session session) throws SQLException, ClassNotFoundException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 798, 496);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblJob = new JLabel("Job");
		lblJob.setBounds(27, 24, 56, 16);
		contentPane.add(lblJob);
		
		searchTF = new JTextField();
		searchTF.setBounds(95, 21, 556, 22);
		contentPane.add(searchTF);
		searchTF.setColumns(10);
		
		JLabel lblFilterBySkill = new JLabel("Filter by skill");
		lblFilterBySkill.setBounds(663, 79, 97, 16);
		contentPane.add(lblFilterBySkill);
		
		JComboBox filterComboBox = new JComboBox();
		filterComboBox.setBounds(663, 108, 97, 22);
		contentPane.add(filterComboBox);
		
		filterComboBox.addItem("All Skill");
		
		
		this.session = session;
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = DriverManager.getConnection("jdbc:sqlserver://10.100.70.70\\PBD2017;user=i14039;password=i14039;database=i14039");
		Statement sta = conn.createStatement();
		Statement sta1 = conn.createStatement();
		String sql = "select * from proyek";

		String[][] obj = new String[100][5];
		
		filterComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(filterComboBox.getSelectedIndex()==0){
					try {
						String sql1 = "select * from proyek";
						ResultSet rs = sta.executeQuery(sql1);
						
						int i = 0;
						while(rs.next()){
							int id = Integer.parseInt(rs.getString("idPengusaha"));
							System.out.println(id);
							sql1 = "select * from pengusaha where idPengusaha = "+id;
							ResultSet rs1 = sta1.executeQuery(sql1);
							rs1.next();
							String name = rs1.getString("nama");
							obj[i][0] = rs.getString("namaProyek");
							obj[i][1] = name;
							obj[i][2] = rs.getString("bayar");
							obj[i][3] = rs.getString("limitPekerja");
							obj[i][4] = rs.getString("waktuPengiklanan");
							i++;
							
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
							
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					try {
						int index = filterComboBox.getSelectedIndex();
						String sql1 = "exec filterBySkill " + index;
						System.out.println(sql1);
						ResultSet rs = sta.executeQuery(sql1);
						
						int i = 0;
						while(rs.next()){
							int id = Integer.parseInt(rs.getString("idPengusaha"));
							sql1 = "select * from pengusaha where idPengusaha = "+id;
							ResultSet rs1 = sta1.executeQuery(sql1);
							rs1.next();
							String name = rs1.getString("nama");
							obj[i][0] = rs.getString("namaProyek");
							obj[i][1] = name;
							obj[i][2] = rs.getString("bayar");
							obj[i][3] = rs.getString("limitPekerja");
							obj[i][4] = rs.getString("waktuPengiklanan");
							i++;
							
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
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		ResultSet rs = sta.executeQuery(sql);
		int i = 0;
		while(rs.next()){
			int id = Integer.parseInt(rs.getString("idPengusaha"));
			System.out.println(id);
			sql = "select * from pengusaha where idPengusaha = "+id;
			ResultSet rs1 = sta1.executeQuery(sql);
			rs1.next();
			String name = rs1.getString("nama");
			obj[i][0] = rs.getString("namaProyek");
			obj[i][1] = name;
			obj[i][2] = rs.getString("bayar");
			obj[i][3] = rs.getString("limitPekerja");
			obj[i][4] = rs.getString("waktuPengiklanan");
			i++;
		}
		
		sql = "select * from skill";
		rs = sta.executeQuery(sql);
		
		while(rs.next()){
			filterComboBox.addItem(rs.getString("namaSkill"));
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
		scrollPane.setBounds(27, 71, 624, 341);
		contentPane.add(scrollPane);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String search = searchTF.getText();
				String sql = "select * from proyek where namaProyek LIKE '%"+search+"%'";
				System.out.println(sql);
				String o[][] = new String[100][5];
				try {
					ResultSet rs = sta.executeQuery(sql);
					int i = 0;
					while(rs.next()){
						int id = Integer.parseInt(rs.getString("idPengusaha"));
						String sql1 = "select * from pengusaha where idPengusaha = "+id;
						ResultSet rs1 = sta1.executeQuery(sql1);
						rs1.next();
						String name = rs1.getString("nama");
						o[i][0] = rs.getString("namaProyek");
						o[i][1] = name;
						o[i][2] = rs.getString("bayar");
						o[i][3] = rs.getString("limitPekerja");
						o[i][4] = rs.getString("waktuPengiklanan");
						
						System.out.println(o[i][0]);
						System.out.println(o[i][1]);
						System.out.println(o[i][2]);
						System.out.println(o[i][3]);
						System.out.println(o[i][4]);
						i++;
					}
					
					
					table = new JTable();
					table.setModel(new DefaultTableModel(
						o,
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
					scrollPane.setBounds(27, 71, 624, 341);
					contentPane.add(scrollPane);
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnSearch.setBounds(663, 20, 97, 25);
		contentPane.add(btnSearch);
		
		JButton btnPilihProyek = new JButton("Pilih Proyek");
		btnPilihProyek.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println(table.getSelectedRow()+" BARIS");
				String nProyek = (String)table.getValueAt(table.getSelectedRow(), 0);
				String sql3 = "select * from proyek where namaProyek = '"+nProyek+"'";
				try {
					ResultSet r = sta.executeQuery(sql3);
					r.next();
					ChooseProyek cp;
					try {
						cp = new ChooseProyek(new Session(r.getString("idProyek")));
						cp.setVisible(true);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		});
		btnPilihProyek.setBounds(663, 387, 97, 25);
		contentPane.add(btnPilihProyek);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setModel(new DefaultTableModel(null,
					new String[]{
					"Nama Proyek", "Owner", "Gaji", "Limit Pekerja", "Waktu Upload"
				}));
			}
		});
		btnClear.setBounds(663, 178, 97, 25);
		contentPane.add(btnClear);
		
	}
}
