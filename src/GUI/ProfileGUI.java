package GUI;

import Controller.Session;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProfileGUI extends JFrame {

	private JPanel contentPane;
	Session session;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProfileGUI frame = new ProfileGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ProfileGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 439, 524);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNama = new JLabel("Nama");
		lblNama.setBounds(36, 33, 56, 16);
		contentPane.add(lblNama);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(175, 33, 211, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(36, 72, 56, 16);
		contentPane.add(lblEmail);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(175, 72, 211, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblSkill = new JLabel("Skill");
		lblSkill.setBounds(36, 113, 56, 16);
		contentPane.add(lblSkill);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(36, 153, 102, 22);
		contentPane.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(156, 153, 102, 22);
		contentPane.add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(284, 153, 102, 22);
		contentPane.add(comboBox_2);
		
		JLabel lblAlamat = new JLabel("Alamat");
		lblAlamat.setBounds(36, 206, 56, 16);
		contentPane.add(lblAlamat);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(175, 206, 211, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNegara = new JLabel("Negara");
		lblNegara.setBounds(36, 249, 56, 16);
		contentPane.add(lblNegara);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBounds(175, 249, 211, 16);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblKota = new JLabel("Kota");
		lblKota.setBounds(36, 293, 56, 16);
		contentPane.add(lblKota);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setBounds(175, 293, 211, 16);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblBudget = new JLabel("Budget");
		lblBudget.setBounds(36, 335, 56, 16);
		contentPane.add(lblBudget);
		
		JLabel lblRatePerHour = new JLabel("Rate per hour");
		lblRatePerHour.setBounds(36, 378, 102, 16);
		contentPane.add(lblRatePerHour);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setBounds(175, 335, 211, 16);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("New label");
		lblNewLabel_6.setBounds(175, 378, 211, 16);
		contentPane.add(lblNewLabel_6);
		
		JButton btnEdit = new JButton("Edit");
		JButton btnCancel = new JButton("Cancel");
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancel.setVisible(false);
				btnSave.setVisible(false);
				btnEdit.setVisible(true);
			}
		});
		
		btnSave.setBounds(81, 425, 97, 25);
		contentPane.add(btnSave);
		btnSave.setVisible(false);
		
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancel.setVisible(false);
				btnSave.setVisible(false);
				btnEdit.setVisible(true);
			}
		});
		btnCancel.setBounds(245, 425, 97, 25);
		contentPane.add(btnCancel);
		btnCancel.setVisible(false);
		
		
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSave.setVisible(true);
				btnCancel.setVisible(true);
				btnEdit.setVisible(false);
			}
		});
		btnEdit.setBounds(161, 425, 97, 25);
		contentPane.add(btnEdit);
		
		
	}
}
