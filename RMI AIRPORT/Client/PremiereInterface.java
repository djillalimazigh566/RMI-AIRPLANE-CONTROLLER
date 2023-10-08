package Client;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class PremiereInterface {

	public String checkVar = new String();
	JFrame frame;
	private JTextField textField;
	

	
	public PremiereInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 600, 343);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 584, 304);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Air Traffic Controller");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel.setBounds(215, 52, 150, 14);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Bienvenu dans ");
		lblNewLabel_1.setBounds(236, 37, 118, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Veuillez introduire le nom de l'avion : ");
		lblNewLabel_2.setBounds(29, 102, 243, 14);
		panel.add(lblNewLabel_2);

		textField = new JTextField();
		textField.setBounds(282, 99, 150, 20);
		panel.add(textField);
		textField.setColumns(10);


		JButton btnNewButton = new JButton("Valider");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String Name = textField.getText(); 
				
			
			}
		});
		btnNewButton.setBounds(246, 243, 89, 23);
		panel.add(btnNewButton);
	}
}
