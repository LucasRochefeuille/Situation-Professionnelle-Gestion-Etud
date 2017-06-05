import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPasswordField;






import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Authentification extends JFrame {

	private JFrame frame;
	private JTextField usernamefield;
	private JLabel lblPassword;
	private JButton btnConnexion;
	private JLabel lblGestionDestudiants;
	private JPasswordField passwordfield;
	
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
					Authentification window = new Authentification();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Authentification() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		cnx= (Connection) ConnexionMysql.ConnexionDB();
		
		usernamefield = new JTextField();
		usernamefield.setBounds(474, 141, 199, 20);
		frame.getContentPane().add(usernamefield);
		usernamefield.setColumns(10);
		
		JLabel lblUserNames = new JLabel("User Names :");
		lblUserNames.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblUserNames.setBounds(391, 141, 85, 20);
		frame.getContentPane().add(lblUserNames);
		
		lblPassword = new JLabel("Password :");
		lblPassword.setBounds(405, 172, 85, 20);
		frame.getContentPane().add(lblPassword);
		
		btnConnexion = new JButton("Se Connecter");
		btnConnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String username = usernamefield.getText().toString();
				String password = passwordfield.getText().toString();
				
				String sql = "select username, password from utilisateurs";
				try {
					prepared=cnx.prepareStatement(sql);
					resultat=prepared.executeQuery();
					int i=0;
					
					if(username.equals("")|| password.equals("") )
					{
						JOptionPane.showMessageDialog(null, "Remplissez les champs vides ! ");
					}
					else
					{
						while(resultat.next())
						{
							String username1=resultat.getString("username");
							String password1=resultat.getString("password");
							
							if(username1.equals(username)&& password1.equals(password) )
							{
								JOptionPane.showMessageDialog(null, "Connexion Réussite ");

								Menu obj = new Menu();
								obj.setVisible(true);
								obj.setLocationRelativeTo(null);
								i=1;
							}
						}
					}
				
					if(i==0)
					{
						JOptionPane.showMessageDialog(null, "Connexion Echouée ");
					}
					
				} catch (SQLException e) {
					
					
					e.printStackTrace();
				}
			}
		});
		btnConnexion.setBounds(473, 203, 200, 33);
		frame.getContentPane().add(btnConnexion);
		
		lblGestionDestudiants = new JLabel("Gestion des \u00E9tudiants");
		lblGestionDestudiants.setBackground(Color.WHITE);
		lblGestionDestudiants.setFont(new Font("Tekton Pro Ext", Font.PLAIN, 15));
		lblGestionDestudiants.setForeground(Color.WHITE);
		lblGestionDestudiants.setHorizontalAlignment(SwingConstants.CENTER);
		lblGestionDestudiants.setBounds(412, 11, 307, 49);
		frame.getContentPane().add(lblGestionDestudiants);
		
		passwordfield = new JPasswordField();
		passwordfield.setBounds(474, 168, 199, 20);
		frame.getContentPane().add(passwordfield);
		
		JLabel lblNewLabel_1 = new JLabel("Mot de passe oubli\u00E9");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				IndicationPass obj = new IndicationPass();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				
			}
		});
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel_1.setBounds(590, 188, 102, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBackground(new Color(240, 240, 240));
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\roche\\Desktop\\blue-wallpaper22.jpg"));
		lblNewLabel.setBounds(10, -48, 974, 461);
		frame.getContentPane().add(lblNewLabel);
	}
}
