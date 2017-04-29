package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import Controller.Session;

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
		sessionName.setBounds(77, 13, 56, 16);
		contentPane.add(sessionName);
		

		
	}

}
