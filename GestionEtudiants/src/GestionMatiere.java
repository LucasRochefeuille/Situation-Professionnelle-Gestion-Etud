import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;


public class GestionMatiere extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNomMat;
	Connection cnx=null;
	PreparedStatement prepared= null;
	ResultSet resultat=null;
	private JTextField textFieldId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionMatiere frame = new GestionMatiere();
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
	public GestionMatiere() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cnx= (Connection) ConnexionMysql.ConnexionDB();
		
		JLabel lblGestionDesMatires = new JLabel("Gestion des mati\u00E8res");
		lblGestionDesMatires.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGestionDesMatires.setForeground(Color.WHITE);
		lblGestionDesMatires.setBounds(154, 31, 155, 14);
		contentPane.add(lblGestionDesMatires);
		
		JLabel lblEntrerMatire = new JLabel("Entrer mati\u00E8re :");
		lblEntrerMatire.setForeground(Color.WHITE);
		lblEntrerMatire.setBounds(57, 104, 94, 14);
		contentPane.add(lblEntrerMatire);
		
		textFieldNomMat = new JTextField();
		textFieldNomMat.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				String sql="select id_matiere, libelle_matiere from matieres where libelle_matiere= ?";
				try {
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1, textFieldNomMat.getText().toString());
					resultat=prepared.executeQuery();
					
					if(resultat.next())
					{
						String id = resultat.getString("id_matiere");
						
						
						
					
						
						textFieldId.setText(id);	
						
					
					}
					
					
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
			}
		});
		textFieldNomMat.setBounds(140, 101, 86, 20);
		contentPane.add(textFieldNomMat);
		textFieldNomMat.setColumns(10);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sql="insert into matieres ( libelle_matiere) values (?)";
 				try {
					prepared=cnx.prepareStatement(sql);
					prepared.setString(1, textFieldNomMat.getText().toString());
					
					prepared.execute();
					
					JOptionPane.showMessageDialog(null, "Matière ajoutée");
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnAjouter.setBounds(178, 167, 89, 23);
		contentPane.add(btnAjouter);
		
		JLabel lblId = new JLabel("ID :");
		lblId.setForeground(Color.WHITE);
		lblId.setBounds(300, 104, 46, 14);
		contentPane.add(lblId);
		
		textFieldId = new JTextField();
		textFieldId.setBounds(329, 101, 69, 20);
		contentPane.add(textFieldId);
		textFieldId.setColumns(10);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql ="delete from matieres where libelle_matiere= ? ";
				try {
					prepared=cnx.prepareStatement(sql);
					prepared.setString(1,textFieldNomMat.getText().toString());
					prepared.execute();
					
					JOptionPane.showMessageDialog(null, "Matière supprimée ");
					textFieldId.setText("");
					textFieldNomMat.setText("");
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnSupprimer.setBounds(178, 219, 89, 23);
		contentPane.add(btnSupprimer);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\roche\\Desktop\\blue-wallpaper22.jpg"));
		lblNewLabel.setBounds(0, 0, 484, 461);
		contentPane.add(lblNewLabel);
	}
}
