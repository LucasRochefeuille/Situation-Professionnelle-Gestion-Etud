import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPasswordField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JEditorPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



public class AjoutUtilisateur extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	

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
					AjoutUtilisateur frame = new AjoutUtilisateur();
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
	public AjoutUtilisateur() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 555, 414);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cnx= (Connection) ConnexionMysql.ConnexionDB();
		
		JLabel lblUtilisateur = new JLabel("Utilisateur :");
		lblUtilisateur.setForeground(Color.WHITE);
		lblUtilisateur.setBounds(45, 25, 88, 14);
		contentPane.add(lblUtilisateur);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe :");
		lblMotDePasse.setForeground(Color.WHITE);
		lblMotDePasse.setBounds(30, 50, 71, 14);
		contentPane.add(lblMotDePasse);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				String sql="select password from utilisateurs where username= ?";
				try {
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1, textField.getText().toString());
					resultat=prepared.executeQuery();
					
					if(resultat.next())
					{
						String password = resultat.getString("password");
						passwordField.setText(password);	
					}
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		textField.setBounds(115, 22, 121, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnAjouterUtilisateur = new JButton("Ajouter utilisateur");
		btnAjouterUtilisateur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
 				String sql="insert into utilisateurs ( username, password) values (?,?)";
 				try {
					prepared=cnx.prepareStatement(sql);
					prepared.setString(1, textField.getText().toString());
					prepared.setString(2, passwordField.getText().toString());
					prepared.execute();
					
					JOptionPane.showMessageDialog(null, "Ajout Réussie");
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
 				
			}
		});
		
		btnAjouterUtilisateur.setBounds(21, 106, 144, 23);
		contentPane.add(btnAjouterUtilisateur);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(115, 50, 121, 20);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("Supprimer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sql ="delete from utilisateurs where username= ? and password = ?";
				try {
					prepared=cnx.prepareStatement(sql);
					prepared.setString(1,textField.getText().toString());
					prepared.setString(2,passwordField.getText().toString());
					prepared.execute();
					JOptionPane.showMessageDialog(null, "Utilisateur supprimé ");
					textField.setText("");
					passwordField.setText("");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(21, 140, 144, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Modifier");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				String sql="Update utilisateurs set password=? where username=?";
				
				try {
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1, passwordField.getText().toString());
					prepared.setString(2, textField.getText().toString());
					prepared.execute();
					JOptionPane.showMessageDialog(null, "Utilisateur modifié");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnNewButton_1.setBounds(21, 174, 144, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\roche\\Desktop\\blue-wallpaper22.jpg"));
		lblNewLabel.setBounds(0, 0, 549, 375);
		contentPane.add(lblNewLabel);
	}
}
