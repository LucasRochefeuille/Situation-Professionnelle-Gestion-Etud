import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Font;


public class IndicationPass extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	
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
					IndicationPass frame = new IndicationPass();
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
	public IndicationPass() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 443, 154);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cnx= (Connection) ConnexionMysql.ConnexionDB();
		
		textField = new JTextField();
		textField.setBounds(74, 11, 143, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setBounds(10, 19, 73, 14);
		contentPane.add(lblUsername);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_1.setEditable(false);
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				
				
				
			}
			@Override
			public void keyReleased(KeyEvent e) {
				String username= textField.getText().toString();
				String sql ="select password from utilisateurs where username=?";
				try {
					
					prepared=cnx.prepareStatement(sql);
					prepared.setString(1, username);
					resultat=prepared.executeQuery();
					if (resultat.next())
					{
						
					String pass =resultat.getString("password");
					String pass1=pass.substring(0, 3);
					
					textField_1.setText("Les trois premières lettre  sont : "+ pass1 );
					
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		textField_1.setBounds(74, 67, 343, 39);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
	}
}
