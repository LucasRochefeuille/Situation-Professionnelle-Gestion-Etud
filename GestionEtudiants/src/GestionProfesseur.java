import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class GestionProfesseur extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionProfesseur frame = new GestionProfesseur();
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
	public GestionProfesseur() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 499, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGestionDesProfesseurs = new JLabel("Gestion des professeurs");
		lblGestionDesProfesseurs.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGestionDesProfesseurs.setForeground(new Color(255, 255, 255));
		lblGestionDesProfesseurs.setBounds(166, 45, 169, 14);
		contentPane.add(lblGestionDesProfesseurs);
		
		JButton btnAjouterUnProfesseur = new JButton("Ajouter un professeur");
		btnAjouterUnProfesseur.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				AjoutProf obj = new AjoutProf();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
			}
		});
		btnAjouterUnProfesseur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAjouterUnProfesseur.setBounds(166, 115, 180, 36);
		contentPane.add(btnAjouterUnProfesseur);
		
		JButton btnAffecterUnProf = new JButton("Affecter un prof \u00E0 une classe");
		btnAffecterUnProf.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Affecterprof obj = new Affecterprof();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
			}
		});
		btnAffecterUnProf.setBounds(166, 188, 180, 36);
		contentPane.add(btnAffecterUnProf);
		
		JButton btnAffecterUnProf_1 = new JButton("Affecter un prof \u00E0 une mati\u00E8re");
		btnAffecterUnProf_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Enseigner obj = new Enseigner();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
			}
		});
		btnAffecterUnProf_1.setBounds(166, 269, 180, 36);
		contentPane.add(btnAffecterUnProf_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\roche\\Desktop\\blue-wallpaper22.jpg"));
		lblNewLabel.setBounds(0, 0, 483, 461);
		contentPane.add(lblNewLabel);
	}

}
