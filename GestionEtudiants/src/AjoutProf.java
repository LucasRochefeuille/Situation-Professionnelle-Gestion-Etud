import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class AjoutProf extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldCode;
	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	
	Connection cnx=null;
	PreparedStatement prepared= null;
	ResultSet resultat=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AjoutProf frame = new AjoutProf();
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
	public AjoutProf() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cnx= (Connection) ConnexionMysql.ConnexionDB();
		
		JLabel lblAjoutDunProfesseur = new JLabel("Ajout d'un Professeur");
		lblAjoutDunProfesseur.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAjoutDunProfesseur.setForeground(Color.WHITE);
		lblAjoutDunProfesseur.setBounds(150, 41, 152, 14);
		contentPane.add(lblAjoutDunProfesseur);
		
		JLabel lblCodeProf = new JLabel("Code prof :");
		lblCodeProf.setForeground(Color.WHITE);
		lblCodeProf.setBounds(47, 109, 72, 14);
		contentPane.add(lblCodeProf);
		
		textFieldCode = new JTextField();
		textFieldCode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String sql="select Code_Prof, nom_prof, prenom_prof from professeurs where Code_Prof= ?";
				try {
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1, textFieldCode.getText().toString());
					resultat=prepared.executeQuery();
					
					if(resultat.next())
					{
						String nom = resultat.getString("nom_prof");
						String prenom = resultat.getString("prenom_prof");
						
						textFieldNom.setText(nom);
						textFieldPrenom.setText(prenom);	
						
					}
					
					
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			
			}
		});
		textFieldCode.setBounds(113, 109, 89, 14);
		contentPane.add(textFieldCode);
		textFieldCode.setColumns(10);
		
		JLabel lblNom = new JLabel("Nom :");
		lblNom.setForeground(Color.WHITE);
		lblNom.setBounds(47, 134, 46, 14);
		contentPane.add(lblNom);
		
		textFieldNom = new JTextField();
		textFieldNom.setColumns(10);
		textFieldNom.setBounds(113, 134, 89, 14);
		contentPane.add(textFieldNom);
		
		JLabel lblPrenom = new JLabel("Prenom : ");
		lblPrenom.setForeground(Color.WHITE);
		lblPrenom.setBounds(47, 164, 46, 14);
		contentPane.add(lblPrenom);
		
		textFieldPrenom = new JTextField();
		textFieldPrenom.setColumns(10);
		textFieldPrenom.setBounds(113, 164, 89, 14);
		contentPane.add(textFieldPrenom);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql="insert into professeurs ( Code_Prof, nom_prof, prenom_prof) values (?,?,?)";
 				try {
					prepared=cnx.prepareStatement(sql);
					prepared.setString(1, textFieldCode.getText().toString());
					prepared.setString(2, textFieldNom.getText().toString());
					prepared.setString(3, textFieldPrenom.getText().toString());
					prepared.execute();
					
					JOptionPane.showMessageDialog(null, "Professeur ajouté");
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
		btnAjouter.setBounds(47, 219, 89, 23);
		contentPane.add(btnAjouter);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.setBounds(178, 219, 89, 23);
		contentPane.add(btnModifier);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sql ="delete from professeurs where Code_Prof= ?";
				try {
					prepared=cnx.prepareStatement(sql);
					prepared.setString(1,textFieldCode.getText().toString());
				
					prepared.execute();
					
					JOptionPane.showMessageDialog(null, "Professeur supprimé ");
					textFieldCode.setText("");
					textFieldNom.setText("");
					textFieldPrenom.setText("");
					
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
			}
		});
		btnSupprimer.setBounds(298, 219, 89, 23);
		contentPane.add(btnSupprimer);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\roche\\Desktop\\blue-wallpaper22.jpg"));
		lblNewLabel.setBounds(0, 0, 484, 461);
		contentPane.add(lblNewLabel);
	}
}
