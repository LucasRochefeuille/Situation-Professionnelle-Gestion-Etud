import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class ajoutEleve extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldINE;
	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	private JTextField textFieldDate;
	private JTextField textFieldAdresse;
	private JTextField textFieldTel;
	
	Connection cnx=null;
	PreparedStatement prepared= null;
	ResultSet resultat=null;
	private JTextField textFieldClasse;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ajoutEleve frame = new ajoutEleve();
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
	public ajoutEleve() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cnx= (Connection) ConnexionMysql.ConnexionDB();
		
		JLabel lblGestionDestudiants = new JLabel("Gestion des \u00E9tudiants");
		lblGestionDestudiants.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGestionDestudiants.setForeground(Color.WHITE);
		lblGestionDestudiants.setBounds(175, 31, 173, 14);
		contentPane.add(lblGestionDestudiants);
		
		JLabel lblCodeIne = new JLabel("Code INE");
		lblCodeIne.setForeground(Color.WHITE);
		lblCodeIne.setBounds(96, 93, 56, 14);
		contentPane.add(lblCodeIne);
		
		textFieldINE = new JTextField();
		textFieldINE.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				String sql="select nom_eleve, prenom_eleve from eleves where Code_INE= ?";
				try {
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1, textFieldINE.getText().toString());
					resultat=prepared.executeQuery();
					
					if(resultat.next())
					{
						String nom = resultat.getString("nom_eleve");
						String prenom = resultat.getString("prenom_eleve");
						
						
					
						
						textFieldNom.setText(nom);	
						textFieldPrenom.setText(prenom);
					
					}
					
					
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
			}
		});
		textFieldINE.setBounds(175, 90, 86, 20);
		contentPane.add(textFieldINE);
		textFieldINE.setColumns(10);
		
		JLabel lblNom = new JLabel("Nom :");
		lblNom.setForeground(Color.WHITE);
		lblNom.setBounds(113, 118, 46, 14);
		contentPane.add(lblNom);
		
		textFieldNom = new JTextField();
		textFieldNom.setBounds(175, 113, 86, 20);
		contentPane.add(textFieldNom);
		textFieldNom.setColumns(10);
		
		JLabel lblPrnom = new JLabel("Pr\u00E9nom :");
		lblPrnom.setForeground(Color.WHITE);
		lblPrnom.setBounds(96, 141, 63, 14);
		contentPane.add(lblPrnom);
		
		textFieldPrenom = new JTextField();
		textFieldPrenom.setBounds(175, 138, 86, 20);
		contentPane.add(textFieldPrenom);
		textFieldPrenom.setColumns(10);
		
		JLabel lblDateDeNaissance = new JLabel("Date Naissance");
		lblDateDeNaissance.setForeground(Color.WHITE);
		lblDateDeNaissance.setBounds(64, 166, 95, 14);
		contentPane.add(lblDateDeNaissance);
		
		textFieldDate = new JTextField();
		textFieldDate.setBounds(175, 163, 86, 20);
		contentPane.add(textFieldDate);
		textFieldDate.setColumns(10);
		
		JLabel lblJjmmaa = new JLabel("AAAA-JJ-MM");
		lblJjmmaa.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblJjmmaa.setForeground(Color.WHITE);
		lblJjmmaa.setBounds(276, 166, 72, 14);
		contentPane.add(lblJjmmaa);
		
		JLabel lblAdresse = new JLabel("Adresse");
		lblAdresse.setForeground(Color.WHITE);
		lblAdresse.setBounds(96, 191, 46, 14);
		contentPane.add(lblAdresse);
		
		textFieldAdresse = new JTextField();
		textFieldAdresse.setBounds(175, 191, 86, 20);
		contentPane.add(textFieldAdresse);
		textFieldAdresse.setColumns(10);
		
		JLabel lblTlphone = new JLabel("T\u00E9l\u00E9phone");
		lblTlphone.setForeground(Color.WHITE);
		lblTlphone.setBounds(82, 223, 60, 14);
		contentPane.add(lblTlphone);
		
		textFieldTel = new JTextField();
		textFieldTel.setBounds(175, 220, 86, 20);
		contentPane.add(textFieldTel);
		textFieldTel.setColumns(10);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sql="insert into eleves ( Code_INE, nom_eleve, prenom_eleve,date_naiss, adresse_eleve, tel_eleve, id_classe_eleve) values (?,?,?,?,?,?, ?)";
 				try {
					prepared=cnx.prepareStatement(sql);
					prepared.setString(1, textFieldINE.getText().toString());
					prepared.setString(2, textFieldNom.getText().toString());
					prepared.setString(3, textFieldPrenom.getText().toString());
					prepared.setString(4, textFieldDate.getText().toString());
					prepared.setString(5, textFieldAdresse.getText().toString());
					prepared.setString(6, textFieldTel.getText().toString());
					prepared.setString(7, textFieldClasse.getText().toString() );
					prepared.execute();
					
					JOptionPane.showMessageDialog(null, "Ajout Réussie");
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnAjouter.addMouseListener(new MouseAdapter() {
			
		});
		
		JLabel lblIdClasse = new JLabel("id classe");
		lblIdClasse.setForeground(Color.WHITE);
		lblIdClasse.setBounds(82, 248, 60, 14);
		contentPane.add(lblIdClasse);
		
		textFieldClasse = new JTextField();
		textFieldClasse.setBounds(175, 251, 86, 20);
		contentPane.add(textFieldClasse);
		textFieldClasse.setColumns(10);
		btnAjouter.setBounds(53, 309, 89, 23);
		contentPane.add(btnAjouter);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
String sql="Update eleves set nom_eleve=?, prenom_eleve=?, date_naiss=?, adresse_eleve=?, tel_eleve=?, id_classe_eleve=? where username=?";
				
				try {
					prepared = cnx.prepareStatement(sql);
					
					prepared.setString(1, textFieldINE.getText().toString());
					prepared.setString(2, textFieldNom.getText().toString());
					prepared.setString(3, textFieldPrenom.getText().toString());
					prepared.setString(4, textFieldDate.getText().toString());
					prepared.setString(5, textFieldAdresse.getText().toString());
					prepared.setString(6, textFieldTel.getText().toString());
					prepared.setString(7, textFieldClasse.getText().toString());
					prepared.executeQuery();
					JOptionPane.showMessageDialog(null, "Etudiant modifié");
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
			}
		});
		btnModifier.setBounds(172, 309, 89, 23);
		contentPane.add(btnModifier);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sql ="delete from eleves where Code_INE= ? ";
				try {
					prepared=cnx.prepareStatement(sql);
					prepared.setString(1,textFieldINE.getText().toString());
					prepared.execute();
					
					JOptionPane.showMessageDialog(null, "Affectation supprimé ");
					textFieldINE.setText("");
					textFieldNom.setText("");
					textFieldPrenom.setText("");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnSupprimer.setBounds(301, 309, 95, 23);
		contentPane.add(btnSupprimer);
		
		JButton btnRetourMenu = new JButton("retour Menu");
		btnRetourMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		btnRetourMenu.setBounds(361, 427, 113, 23);
		contentPane.add(btnRetourMenu);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\roche\\Desktop\\blue-wallpaper22.jpg"));
		lblNewLabel.setBounds(0, 0, 484, 461);
		contentPane.add(lblNewLabel);
		
	}
}
