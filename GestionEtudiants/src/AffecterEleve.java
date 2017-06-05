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

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class AffecterEleve extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldINE;
	private JTextField textFieldIdClasse;
	
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
					AffecterEleve frame = new AffecterEleve();
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
	public AffecterEleve() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cnx= (Connection) ConnexionMysql.ConnexionDB();
		
		JLabel lblAffectationEleve = new JLabel("Affectation Eleve");
		lblAffectationEleve.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAffectationEleve.setForeground(Color.WHITE);
		lblAffectationEleve.setBounds(192, 28, 153, 14);
		contentPane.add(lblAffectationEleve);
		
		JLabel lblIdEleve = new JLabel("Code INE :");
		lblIdEleve.setForeground(Color.WHITE);
		lblIdEleve.setBounds(39, 129, 88, 14);
		contentPane.add(lblIdEleve);
		
		textFieldINE = new JTextField();
		textFieldINE.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				String sql="select id_classe from contient where id_eleve= ?";
				try {
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1, textFieldINE.getText().toString());
					resultat=prepared.executeQuery();
					
					if(resultat.next())
					{
						String id_classe = resultat.getString("id_classe");
						textFieldIdClasse.setText(id_classe);	
					}
					
					
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				
			}
		});
		textFieldINE.setBounds(98, 126, 106, 20);
		contentPane.add(textFieldINE);
		textFieldINE.setColumns(10);
		
		JLabel lblIdclasse = new JLabel("id_Classe:");
		lblIdclasse.setForeground(Color.WHITE);
		lblIdclasse.setBounds(42, 157, 85, 14);
		contentPane.add(lblIdclasse);
		
		textFieldIdClasse = new JTextField();
		textFieldIdClasse.setColumns(10);
		textFieldIdClasse.setBounds(98, 152, 106, 20);
		contentPane.add(textFieldIdClasse);
		
		JButton btnAffecter = new JButton("Affecter");
		btnAffecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sql="insert into contient ( id_classe, id_eleve) values (?,?)";
 				try {
					prepared=cnx.prepareStatement(sql);
					prepared.setString(1, textFieldIdClasse.getText().toString());
					prepared.setString(2, textFieldINE.getText().toString());
					prepared.execute();
					
					JOptionPane.showMessageDialog(null, "Affectation réussit");
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
		btnAffecter.setBounds(102, 258, 89, 23);
		contentPane.add(btnAffecter);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sql ="delete from contient where id_classe= ? and id_eleve = ?";
				try {
					prepared=cnx.prepareStatement(sql);
					prepared.setString(1,textFieldIdClasse.getText().toString());
					prepared.setString(2,textFieldINE.getText().toString());
					prepared.execute();
					JOptionPane.showMessageDialog(null, "Affectation supprimé ");
					textFieldIdClasse.setText("");
					textFieldINE.setText("");
				} catch (SQLException e4) {
					// TODO Auto-generated catch block
					e4.printStackTrace();
				}
				
			}
		});
		btnSupprimer.setBounds(218, 258, 89, 23);
		contentPane.add(btnSupprimer);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\roche\\Desktop\\blue-wallpaper22.jpg"));
		lblNewLabel.setBounds(0, 0, 484, 461);
		contentPane.add(lblNewLabel);
	}

}
