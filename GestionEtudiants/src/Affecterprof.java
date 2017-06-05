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


public class Affecterprof extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldIdClasse;
	private JTextField textFieldCode;

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
					Affecterprof frame = new Affecterprof();
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
	public Affecterprof() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cnx= (Connection) ConnexionMysql.ConnexionDB();

		
		JLabel lblAffecterProfesseur = new JLabel("Affecter professeur \u00E0 une classe");
		lblAffecterProfesseur.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAffecterProfesseur.setForeground(Color.WHITE);
		lblAffecterProfesseur.setBounds(161, 33, 218, 14);
		contentPane.add(lblAffecterProfesseur);
		
		JLabel lblNom = new JLabel("id classe");
		lblNom.setForeground(Color.WHITE);
		lblNom.setBounds(66, 135, 46, 14);
		contentPane.add(lblNom);
		
		textFieldIdClasse = new JTextField();
		textFieldIdClasse.setBounds(122, 132, 86, 20);
		contentPane.add(textFieldIdClasse);
		textFieldIdClasse.setColumns(10);
		
		JLabel lblClasse = new JLabel("code prof");
		lblClasse.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblClasse.setForeground(Color.WHITE);
		lblClasse.setBounds(66, 98, 46, 14);
		contentPane.add(lblClasse);
		
		textFieldCode = new JTextField();
		textFieldCode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				String sql="select id_classe, Code_Prof from affecter where Code_Prof= ?";
				try {
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1, textFieldCode.getText().toString());
					resultat=prepared.executeQuery();
					
					if(resultat.next())
					{
						String classe = resultat.getString("id_classe");
					
						
						
					
						
						textFieldIdClasse.setText(classe);	
						
					
					}
					
					
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
			}
		});
		textFieldCode.setColumns(10);
		textFieldCode.setBounds(122, 95, 86, 20);
		contentPane.add(textFieldCode);
		
		JButton btnAjouter = new JButton("Affecter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql="insert into Affecter ( id_classe, Code_Prof) values (?,?) ";
 				try {
					prepared=cnx.prepareStatement(sql);
					prepared.setString(1, textFieldIdClasse.getText().toString());
					prepared.setString(2, textFieldCode.getText().toString());
					
					prepared.execute();
					
					JOptionPane.showMessageDialog(null, "Affectation Réussie");
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnAjouter.setBounds(23, 249, 89, 23);
		contentPane.add(btnAjouter);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
String sql="Update affectation set id_classe=? where Code_Prof=?";
				
				try {
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1, textFieldIdClasse.getText().toString());
					prepared.setString(2, textFieldCode.getText().toString());
					prepared.execute();
					JOptionPane.showMessageDialog(null, "Affectation modifié");
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		btnModifier.setBounds(161, 249, 89, 23);
		contentPane.add(btnModifier);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql ="delete from affecter where Id_Classe= ? and Code_Prof = ?";
				try {
					prepared=cnx.prepareStatement(sql);
					prepared.setString(1,textFieldIdClasse.getText().toString());
					prepared.setString(2,textFieldCode.getText().toString());
					prepared.execute();
					JOptionPane.showMessageDialog(null, "Affectation supprimé ");
					textFieldIdClasse.setText("");
					textFieldCode.setText("");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnSupprimer.setBounds(290, 249, 89, 23);
		contentPane.add(btnSupprimer);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\roche\\Desktop\\blue-wallpaper22.jpg"));
		lblNewLabel.setBounds(0, 0, 484, 461);
		contentPane.add(lblNewLabel);
	}

}
