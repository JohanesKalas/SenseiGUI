package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import Controller.Session;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class SenseiHomeGUI extends JFrame {

	private JPanel contentPane;
	Session session;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SenseiHomeGUI frame = new SenseiHomeGUI(new Session("GAGAL"));
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
	 */
	public SenseiHomeGUI(Session session) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblWelcome = new JLabel("Welcome, ");
		lblWelcome.setBounds(12, 13, 61, 16);
		contentPane.add(lblWelcome);
		
		this.session = session;
		
		JLabel sessionName = new JLabel(this.session.username);
		sessionName.setBounds(77, 13, 343, 16);
		contentPane.add(sessionName);
		
		JButton searchJob = new JButton("Search Job");
		searchJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchJobGUI sj;
				try {
					sj = new SearchJobGUI(session);
					sj.setVisible(true);
					SenseiHomeGUI.this.dispose();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		searchJob.setBounds(162, 92, 97, 25);
		contentPane.add(searchJob);
		
		JButton editProfile = new JButton("Edit Profile");
		editProfile.setBounds(162, 141, 97, 25);
		contentPane.add(editProfile);
		
		JLabel lblNewLabel = new JLabel("SENSEI.COM");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(12, 42, 111, 33);
		contentPane.add(lblNewLabel);
		

		
	}

}
