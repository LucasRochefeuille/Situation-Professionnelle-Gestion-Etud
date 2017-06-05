import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Font;
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


public class AjoutClasse extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldClasse;
	Connection cnx=null;
	PreparedStatement prepared= null;
	ResultSet resultat=null;
	private JTextField textFieldIdClasse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AjoutClasse frame = new AjoutClasse();
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
	public AjoutClasse() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cnx= (Connection) ConnexionMysql.ConnexionDB();
		
		JLabel lblAjouterUneClasse = new JLabel("Ajouter une classe");
		lblAjouterUneClasse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAjouterUneClasse.setForeground(Color.WHITE);
		lblAjouterUneClasse.setBounds(167, 37, 176, 36);
		contentPane.add(lblAjouterUneClasse);
		
		JLabel lblNomDeLa = new JLabel("Nom de la classe :");
		lblNomDeLa.setForeground(Color.WHITE);
		lblNomDeLa.setBounds(51, 127, 94, 14);
		contentPane.add(lblNomDeLa);
		
		textFieldClasse = new JTextField();
		textFieldClasse.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				String sql="select id_classe from classes where libelle_classe= ?";
				try {
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1, textFieldClasse.getText().toString());
					resultat=prepared.executeQuery();
					
					if(resultat.next())
					{
						String id = resultat.getString("id_classe");
						textFieldIdClasse.setText(id);	
					}
					
					
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
			}
		});
		textFieldClasse.setBounds(155, 124, 86, 20);
		contentPane.add(textFieldClasse);
		textFieldClasse.setColumns(10);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sql="insert into classes ( id_classe, libelle_classe) values (?,?)";
 				try {
					prepared=cnx.prepareStatement(sql);
					prepared.setString(1, textFieldIdClasse.getText().toString());
					prepared.setString(2, textFieldClasse.getText().toString());
				
					prepared.execute();
					
					JOptionPane.showMessageDialog(null, "Classe ajouté");
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		
		JLabel lblIdClasse = new JLabel("Id Classe");
		lblIdClasse.setForeground(Color.WHITE);
		lblIdClasse.setBounds(51, 157, 46, 14);
		contentPane.add(lblIdClasse);
		
		textFieldIdClasse = new JTextField();
		textFieldIdClasse.setColumns(10);
		textFieldIdClasse.setBounds(155, 161, 86, 20);
		contentPane.add(textFieldIdClasse);
		btnAjouter.setBounds(56, 248, 89, 23);
		contentPane.add(btnAjouter);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.setBounds(167, 248, 89, 23);
		contentPane.add(btnModifier);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sql ="delete from classes where id_classe= ? and libelle_classe = ?";
				try {
					prepared=cnx.prepareStatement(sql);
					prepared.setString(1,textFieldIdClasse.getText().toString());
					prepared.setString(2,textFieldClasse.getText().toString());
					prepared.execute();
					JOptionPane.showMessageDialog(null, "Classe supprimé ");
					textFieldClasse.setText("");
					textFieldIdClasse.setText("");
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				
			}
		});
		btnSupprimer.setBounds(279, 248, 89, 23);
		contentPane.add(btnSupprimer);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\roche\\Desktop\\blue-wallpaper22.jpg"));
		lblNewLabel.setBounds(0, 0, 484, 461);
		contentPane.add(lblNewLabel);
	}

}
