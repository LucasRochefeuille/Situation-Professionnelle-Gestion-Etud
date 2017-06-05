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


public class Enseigner extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldCode;
	private JTextField textFieldIdMat;
	private JTextField textFieldNom;
	private JTextField textFieldlib;
	
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
					Enseigner frame = new Enseigner();
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
	public Enseigner() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cnx= (Connection) ConnexionMysql.ConnexionDB();
		
		JLabel lblAffecterUnProf = new JLabel("Affecter un prof \u00E0 une mati\u00E8re");
		lblAffecterUnProf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAffecterUnProf.setForeground(Color.WHITE);
		lblAffecterUnProf.setBounds(107, 43, 243, 29);
		contentPane.add(lblAffecterUnProf);
		
		JLabel lblCodeProf = new JLabel("Code prof :");
		lblCodeProf.setForeground(Color.WHITE);
		lblCodeProf.setBounds(32, 113, 95, 14);
		contentPane.add(lblCodeProf);
		
		textFieldCode = new JTextField();
		textFieldCode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				String sql="select id_matiere from enseigner  where id_prof= ?";
				try {
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1, textFieldCode.getText().toString());
					resultat=prepared.executeQuery();
					
					if(resultat.next())
					{
						String id_mat = resultat.getString("id_matiere");
						
						
						textFieldIdMat.setText(id_mat);
							
					}
					
					
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		textFieldCode.setBounds(107, 113, 98, 14);
		contentPane.add(textFieldCode);
		textFieldCode.setColumns(10);
		
		JLabel lblIdmatiere = new JLabel("id_matiere :");
		lblIdmatiere.setForeground(Color.WHITE);
		lblIdmatiere.setBounds(32, 139, 67, 14);
		contentPane.add(lblIdmatiere);
		
		textFieldIdMat = new JTextField();
		textFieldIdMat.setColumns(10);
		textFieldIdMat.setBounds(107, 139, 98, 14);
		contentPane.add(textFieldIdMat);
		
		textFieldNom = new JTextField();
		textFieldNom.setColumns(10);
		textFieldNom.setBounds(303, 113, 98, 14);
		contentPane.add(textFieldNom);
		
		textFieldlib = new JTextField();
		textFieldlib.setColumns(10);
		textFieldlib.setBounds(303, 139, 98, 14);
		contentPane.add(textFieldlib);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sql="insert into enseigner ( id_prof, id_matiere) values (?,?)";
 				try {
					prepared=cnx.prepareStatement(sql);
					prepared.setString(1, textFieldCode.getText().toString());
					prepared.setString(2, textFieldIdMat.getText().toString());
					
					prepared.execute();
					
					JOptionPane.showMessageDialog(null, "Enseignement ajouté");
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
		btnAjouter.setBounds(107, 219, 89, 23);
		contentPane.add(btnAjouter);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sql ="delete from enseigner where id_prof= ? and id_matiere=?";
				try {
					prepared=cnx.prepareStatement(sql);
					prepared.setString(1,textFieldCode.getText().toString());
					prepared.setString(2,textFieldIdMat.getText().toString());
				
					prepared.execute();
					
					JOptionPane.showMessageDialog(null, "Enseignement supprimé ");
					textFieldCode.setText("");
					textFieldIdMat.setText("");
				
					
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				
			}
		});
		btnSupprimer.setBounds(218, 219, 104, 23);
		contentPane.add(btnSupprimer);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\roche\\Desktop\\blue-wallpaper22.jpg"));
		lblNewLabel.setBounds(0, 0, 484, 461);
		contentPane.add(lblNewLabel);
	}

}
