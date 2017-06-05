import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Menu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 501);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnGestionlves = new JButton("Gestion \u00E9l\u00E8ves");
		btnGestionlves.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				ajoutEleve obj = new ajoutEleve();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				
			}
		});
		btnGestionlves.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnGestionlves.setBounds(160, 59, 159, 45);
		contentPane.add(btnGestionlves);
		
		JButton btnGestionProfesseurs = new JButton("Gestion professeurs");
		btnGestionProfesseurs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GestionProfesseur obj = new GestionProfesseur();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
			
			}
		});
		btnGestionProfesseurs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnGestionProfesseurs.setBounds(160, 134, 159, 45);
		contentPane.add(btnGestionProfesseurs);
		
		JButton btnGestionMatires = new JButton("Gestion mati\u00E8res");
		btnGestionMatires.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				GestionMatiere obj = new GestionMatiere();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
			}
		});
		btnGestionMatires.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnGestionMatires.setBounds(160, 209, 159, 45);
		contentPane.add(btnGestionMatires);
		
		JButton btnGestionClasse = new JButton("Gestion Classe");
		btnGestionClasse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				GestionClasse obj = new GestionClasse();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
			}
		});
		btnGestionClasse.setBounds(160, 354, 159, 45);
		contentPane.add(btnGestionClasse);
		
		JButton btnNewButton = new JButton("Gestion utilisateur");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				AjoutUtilisateur obj = new AjoutUtilisateur();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(160, 285, 159, 41);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\roche\\Desktop\\blue-wallpaper22.jpg"));
		lblNewLabel.setBounds(0, 0, 484, 462);
		contentPane.add(lblNewLabel);
	}

}
