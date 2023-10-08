package Client;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.Timer;

import javax.swing.border.TitledBorder;

import Common.AvionInterface;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.event.ActionEvent;

public class InterfaceClient extends JFrame implements ActionListener {
	JButton CreationVol = null;
	JRadioButton Autorisation = null;
	JPanel Console = null;
	JPanel panel = null;
	JLabel LabelOk = null;
	JLabel Message = null;
	JLabel Reservoir = null;
	JLabel PosX = null;
	JLabel PosY = null;
	JLabel IDAvion = null;
	JButton btnDemanderDecollage = null;
	JButton btnDemanderAtterissage = null;
	RadarClient Radar = null;
	Timer timer = null;
	int randomNum = ThreadLocalRandom.current().nextInt(100000, 999999 + 1);

	
	JLabel lblNewLabel_2_2 = null;
	AvionInterface tiyara;
	
	public InterfaceClient(AvionInterface tiyara1) throws RemoteException {

		
		
		tiyara = tiyara1;
		timer = new Timer(1000, this);
		timer.start();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1080, 720);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Tableau de bord", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(6, 27, 1068, 125);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel Title = new JLabel("Air Traffic Controller");
		Title.setBounds(435, 16, 171, 39);
		Title.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		panel.add(Title);

		JLabel lblNewLabel = new JLabel("Interface du client");
		lblNewLabel.setBounds(457, 52, 201, 16);
		panel.add(lblNewLabel);

		IDAvion = new JLabel();
		IDAvion.setBounds(234, 28, 116, 14);
		panel.add(IDAvion);

		JLabel IDavion = new JLabel("Identifiant du vol : ");
		IDavion.setBounds(10, 27, 152, 14);
		panel.add(IDavion);
		IDavion.setFont(new Font("Tahoma", Font.BOLD, 12));

		 JLabel lblNewLabel_8_1 = new JLabel(tiyara.getFlightNum());
		lblNewLabel_8_1.setBounds(234, 27, 152, 14);
		panel.add(lblNewLabel_8_1);

		JLabel lblNewLabel_1_1 = new JLabel("Nom de l'avion : ");
		lblNewLabel_1_1.setBounds(10, 53, 152, 14);
		panel.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));

		
		  JLabel lblNewLabel_2_1 = new JLabel(tiyara.getName());
		  lblNewLabel_2_1.setBounds(234, 54, 116, 14);
		  panel.add(lblNewLabel_2_1);
		 

		JLabel lblNewLabel_1_2_1 = new JLabel("Capacité réservoir : ");
		lblNewLabel_1_2_1.setBounds(10, 81, 152, 14);
		panel.add(lblNewLabel_1_2_1);
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 12));

		Reservoir = new JLabel(tiyara.getReservoir());
		Reservoir.setBounds(234, 82, 116, 14);
		panel.add(Reservoir);

		JLabel lblNewLabel_1_3 = new JLabel("Position de l'avion : ");
		lblNewLabel_1_3.setBounds(719, 28, 152, 14);
		panel.add(lblNewLabel_1_3);
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 12));

		PosX = new JLabel(String.valueOf(tiyara.getPosX()));
		PosX.setBounds(923, 29, 46, 14);
		panel.add(PosX);

		PosY = new JLabel(String.valueOf(tiyara.getPosY()));
		PosY.setBounds(995, 29, 46, 14);
		panel.add(PosY);

		JLabel lblNewLabel_1_2 = new JLabel("Etat de l'avion : ");
		lblNewLabel_1_2.setBounds(719, 80, 152, 14);
		panel.add(lblNewLabel_1_2);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 12));

		
		lblNewLabel_2_2 = new JLabel("" + tiyara.getState() + "");
		 lblNewLabel_2_2.setBounds(943, 81, 116, 14);
		 panel.add(lblNewLabel_2_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Contacter tour de contrôle", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		panel_1.setBounds(590, 163, 464, 115);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		btnDemanderDecollage = new JButton("Demander décollage");
		btnDemanderDecollage.addActionListener(this);
		btnDemanderDecollage.setBounds(10, 46, 154, 29);
		panel_1.add(btnDemanderDecollage);

		btnDemanderAtterissage = new JButton("Demander atterissage ");
		btnDemanderAtterissage.setBounds(274, 46, 154, 29);
		btnDemanderAtterissage.addActionListener(this);
		panel_1.add(btnDemanderAtterissage);

		

		Console = new JPanel();
		Console.setBackground(new Color(0, 0, 0));
		Console.setBounds(590, 289, 464, 376);
		getContentPane().add(Console);
		Console.setLayout(null);
		Message = new JLabel();
		Message.setBounds(10, 11, 443, 24);
		Console.add(Message);
		Message.setForeground(Color.GREEN);

		LabelOk = new JLabel();
		LabelOk.setFont(new Font("Tahoma", Font.PLAIN, 13));
		LabelOk.setForeground(new Color(0, 255, 0));
		LabelOk.setBounds(10, 46, 55, 24);
		Console.add(LabelOk);
		this.setVisible(true);

		
		 Radar = new RadarClient(tiyara);
		
		 
		 Radar.setBounds(16, 164, 385, 460);
		 Radar.setVisible(true);
		 this.getContentPane().add(Radar);
		 

	}

	
	  public void actionPerformed(ActionEvent e) {
	  if (e.getSource() == btnDemanderAtterissage) {
	  
	  try {
		String m = tiyara.DemandePermissionAtterissage();	
		Message.setText(m);
		
	} catch (RemoteException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	  Message.setBounds(10, 11, 289, 24);
	  Message.setForeground(Color.GREEN);
	  Console.add(Message);
	  
	  }
	  
	  if (e.getSource() == btnDemanderDecollage) {
	  
	  try {
		Message.setText(tiyara.Demandedecollage());
	} catch (RemoteException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	  Message.setBounds(10, 11, 289, 24);
	  Message.setForeground(Color.GREEN);
	  Console.add(Message);
	 
	  }
	  
	  try {
		PosX.setText("" + String.valueOf(tiyara.getPosX()) + "");
		Reservoir.setText(String.valueOf(tiyara.getReservoirf()));
		lblNewLabel_2_2.setText(tiyara.getState()); 
	} catch (RemoteException e1) {
		
		e1.printStackTrace();
	}
	  
	  try {
		PosY.setText("" + String.valueOf(tiyara.getPosY()) + "");
	} catch (RemoteException e1) {
		
		e1.printStackTrace();
	}
	  repaint();
	  }
	 
}