import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class GestionClasse extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionClasse frame = new GestionClasse();
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
	public GestionClasse() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGestionClasse = new JLabel("Gestion Classe");
		lblGestionClasse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGestionClasse.setForeground(Color.WHITE);
		lblGestionClasse.setBounds(201, 30, 151, 14);
		contentPane.add(lblGestionClasse);
		
		JButton btnAjouterUneClasse = new JButton("Ajouter une classe");
		btnAjouterUneClasse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAjouterUneClasse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AjoutClasse obj = new AjoutClasse();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
			}
		});
		btnAjouterUneClasse.setBounds(166, 119, 186, 59);
		contentPane.add(btnAjouterUneClasse);
		
		JButton btnAjouterEleveDans = new JButton("Ajouter Eleve dans une classe");
		btnAjouterEleveDans.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				AffecterEleve obj = new AffecterEleve();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				
			}
		});
		btnAjouterEleveDans.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAjouterEleveDans.setBounds(166, 249, 194, 59);
		contentPane.add(btnAjouterEleveDans);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\roche\\Desktop\\blue-wallpaper22.jpg"));
		lblNewLabel.setBounds(0, 0, 484, 461);
		contentPane.add(lblNewLabel);
	}

}
