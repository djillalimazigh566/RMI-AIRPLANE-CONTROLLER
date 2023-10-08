package Server;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class TourDeControleGUI extends JFrame implements ActionListener {
	
	JButton CreationVol = null;
	RadarPanel Radar = null;
	JButton AffectationVol = null;
	JButton btnAfficherLesVols = null;
	public TourDeControleGUI() throws RemoteException {
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1080, 720);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 27, 1068, 125);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel Title = new JLabel("Air Traffic Controller");
		Title.setBounds(435, 16, 171, 39);
		Title.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		panel.add(Title);
		
		JLabel lblNewLabel = new JLabel("Interface de la tour de contrôle");
		lblNewLabel.setBounds(431, 51, 201, 16);
		panel.add(lblNewLabel);
		
		JPanel AnnuairesPanel = new JPanel();
		AnnuairesPanel.setBorder(new TitledBorder(null, "Annuaires", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		AnnuairesPanel.setBounds(637, 167, 437, 519);
		getContentPane().add(AnnuairesPanel);
		AnnuairesPanel.setLayout(null);
		
		JLabel AnnuaireAvionLabel = new JLabel("Annuaire des avions existants :");
		AnnuaireAvionLabel.setBounds(6, 51, 202, 16);
		AnnuairesPanel.add(AnnuaireAvionLabel);
		
		JLabel AnnuaireAvionLabel_1 = new JLabel("Annuaire des stations existantes :");
		AnnuaireAvionLabel_1.setBounds(6, 209, 240, 16);
		AnnuairesPanel.add(AnnuaireAvionLabel_1);
		


		

		JEditorPane AnnuaireAvions = new JEditorPane();
		AnnuaireAvions.setBounds(6, 79, 425, 118);
		AnnuairesPanel.add(AnnuaireAvions);
		
		


		JEditorPane AnnuaireStations = new JEditorPane();
		AnnuaireStations.setBounds(6, 237, 425, 118);
		AnnuairesPanel.add(AnnuaireStations);
		
		JLabel AnnuaireAvionLabel_1_1 = new JLabel("Annuaire des vols :");
		AnnuaireAvionLabel_1_1.setBounds(6, 367, 240, 16);
		AnnuairesPanel.add(AnnuaireAvionLabel_1_1);
		
		JEditorPane AnnuaireVols = new JEditorPane();
		AnnuaireVols.setBounds(6, 395, 425, 118);
		AnnuairesPanel.add(AnnuaireVols);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(416, 79, 15, 118);
		AnnuairesPanel.add(scrollBar);
		
		JScrollBar scrollBar_1 = new JScrollBar();
		scrollBar_1.setBounds(416, 237, 15, 118);
		AnnuairesPanel.add(scrollBar_1);
		
		JScrollBar scrollBar_2 = new JScrollBar();
		scrollBar_2.setBounds(416, 395, 15, 118);
		AnnuairesPanel.add(scrollBar_2);
		
		JPanel FunctionsPanel = new JPanel();
		FunctionsPanel.setBorder(new TitledBorder(null, "Fonctionnalit\u00E9s", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		FunctionsPanel.setBounds(413, 167, 212, 519);
		getContentPane().add(FunctionsPanel);
		FunctionsPanel.setLayout(null);
		
		JButton AffectationVol = new JButton("Affectation vol");
		AffectationVol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.print("test");
			try {
				TourDeControle.AffecterVol();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		});
		AffectationVol.setBounds(6, 455, 200, 29);
		FunctionsPanel.add(AffectationVol);
		
		JButton LancementVol = new JButton("Lancement de vol");
		LancementVol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		LancementVol.setBounds(6, 487, 200, 29);
		FunctionsPanel.add(LancementVol);
		
		CreationVol = new JButton("Créer un vol");
		CreationVol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		CreationVol.setBounds(6, 21, 200, 29);
		CreationVol.addActionListener(this);
		FunctionsPanel.add(CreationVol);
		
		JButton CreationAeroport = new JButton("Créer Aeroport");
		CreationAeroport.setBounds(6, 53, 200, 29);
		FunctionsPanel.add(CreationAeroport);
		
		JButton AffectationVol_1 = new JButton("Afficher les Avions");
		AffectationVol_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String AVIONS  = "";
				for(int w =0;w<TourDeControle.AnnuaireAvions.size();w++){
				String AnnuaireAv = TourDeControle.AnnuaireAvions.get(w).identifiant + "" + TourDeControle.AnnuaireAvions.get(w).etat ;
				AVIONS = AVIONS + AnnuaireAv + "\n";
				
				
				}
				AnnuaireAvions.setText(AVIONS);
			}
		});
		AffectationVol_1.setBounds(6, 86, 200, 29);
		FunctionsPanel.add(AffectationVol_1);
		
		JButton btnAfficherLesStations = new JButton("Afficher les Stations");
		btnAfficherLesStations.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				String STATIONS  = "",AnnuaireSt= "";
				for(int w =0;w<TourDeControle.AnnuaireStations.size();w++){
				 AnnuaireSt = TourDeControle.AnnuaireStations.get(w).name + ""  ;
				STATIONS = STATIONS + AnnuaireSt + "\n";
				
				System.out.println(AnnuaireSt);
				}
				AnnuaireStations.setText(STATIONS);
			}
		});
		btnAfficherLesStations.setBounds(6, 115, 200, 29);
		FunctionsPanel.add(btnAfficherLesStations);
		
		JButton btnAfficherLesVols = new JButton("Afficher les vols ");
		btnAfficherLesVols.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					String VOLS  = "";
					String AnnuaireSt= "";
					for(int w =0;w<TourDeControle.annuaireVols.size();w++){
					AnnuaireSt = TourDeControle.annuaireVols.get(w).numeroVol + ""  ;
					VOLS = VOLS + AnnuaireSt + "\n";
					}
					AnnuaireVols.setText(VOLS);
					System.out.println(VOLS);
				}
			});
		btnAfficherLesVols.setBounds(6, 146, 200, 29);
		FunctionsPanel.add(btnAfficherLesVols);
		
	
		
		Radar = new RadarPanel();
		Radar.setBounds(16, 164, 385, 460);
		Radar.setVisible(true);
		this.getContentPane().add(Radar);
		
		JButton RefreshButton = new JButton("Refresh");
		RefreshButton.setBounds(26, 639, 117, 29);
		RefreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Radar.repaint();
				}
			});
		getContentPane().add(RefreshButton);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == CreationVol ) {
				TourDeControle.CreateVol();
		}
	}
}
