package Server;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.Timer;
import javax.swing.JLabel;
import java.awt.Font;

public class RadarPanel extends JComponent implements ActionListener {

	int x = 148;
	int y = 144;
	int tx = 25;
	int ty = 25;
	int r = 100;
	int i = 0;
	Timer timer = null;
	JLabel lblNewLabel = null;
	JLabel NameAirP = null;
	RadarPanel() {
		
		lblNewLabel = new JLabel();
		this.setSize(385, 460);
		timer = new Timer(1000, this);
		timer.start();

	}

	@Override
	public void paintComponent(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(6, 6, 162, 16);

		this.add(lblNewLabel);

		// BACKGROUND
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, 385, 460);

		// DRAW LINES
		g2d.setColor(Color.green);
		g2d.drawLine(0, 230, 385, 230);
		g2d.drawLine(192, 0, 192, 460);

		// DRAW CIRCLES
		int taille;
		taille = 50;
		int nbCercles = 15;
		for (int i = 1; i <= nbCercles; i++) {
			int y = taille / 2;
			g2d.drawOval(192 - y, 230 - y, taille, taille);
			taille = taille + 50;
		}
		// DRAW COMPONENT
		if (!TourDeControle.AnnuaireAvions.isEmpty()) {

			try {
				for (int v = 0; v < TourDeControle.AnnuaireAvions.size(); v++) {
					//System.out.println(String.valueOf(TourDeControle.AnnuaireAvions.get(v).etat));
					if (String.valueOf(TourDeControle.AnnuaireAvions.get(v).etat).equals("Active") || String.valueOf(TourDeControle.AnnuaireAvions.get(v).etat).equals("Broken")) {
						
						g2d.drawImage(TourDeControle.AnnuaireAvions.get(v).getImagestate().getImage(), TourDeControle.AnnuaireAvions.get(v).getPosX(),TourDeControle.AnnuaireAvions.get(v).getPosY(), null);
						
						if (TourDeControle.AnnuaireAvions.get(v).getReservoirf() < 50 && TourDeControle.AnnuaireAvions.get(v).getReservoirf() > 25 && String.valueOf(TourDeControle.AnnuaireAvions.get(v).etat).equals("Active") ){
							TourDeControle.AnnuaireAvions.get(v).flight.changeState(1);
								System.out.println("Orange");
								
								/*Station urgence = TourDeControle.FindNearest(TourDeControle.AnnuaireAvions.get(v));
									System.out.println(urgence.name);
								if(urgence != null){
									TourDeControle.AnnuaireAvions.get(v).flight.setAeroportArrivee(urgence);
									System.out.println(TourDeControle.AnnuaireAvions.get(v).flight.aeroportArrivee.name);
								}*/
								
						}
						if (TourDeControle.AnnuaireAvions.get(v).getReservoirf() < 25 ){
							TourDeControle.AnnuaireAvions.get(v).flight.changeState(2);
						}
						if (TourDeControle.AnnuaireAvions.get(v).getReservoirf()== 0){
							TourDeControle.AnnuaireAvions.get(v).etat = State.Broken;
						}
						
					}
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		g2d.setFont(new Font("Purisa", Font.PLAIN, 13));
		g2d.setColor(Color.WHITE);
		for (int v = 0; v < TourDeControle.AnnuaireStations.size(); v++) {
			g2d.drawImage(TourDeControle.AnnuaireStations.get(v).state.getImage(), TourDeControle.AnnuaireStations.get(v).getCoordX(),TourDeControle.AnnuaireStations.get(v).getCoordY(), null);	
			g2d.drawString(TourDeControle.AnnuaireStations.get(v).name, TourDeControle.AnnuaireStations.get(v).getCoordX(), TourDeControle.AnnuaireStations.get(v).getCoordY() + 60);
		}
	}


	/* 
	public void moveEsc(Avion a){
		int i=0;
		while((i<a.flight.Escales.size())   &&   (TourDeControle.Distance(a, a.flight.Escales.get(i))<7)){
			move(a,a.flight.Escales.get(i).coordX, a.flight.Escales.get(i).coordY);
		}
		i++;
	}
	*/
	@Override
	public void actionPerformed(ActionEvent e) {

		for (int i = 0; i < TourDeControle.AnnuaireAvions.size(); i++) {

			if(TourDeControle.AnnuaireAvions.get(i).getState().equals("Active")){
				TourDeControle.FindNearest(TourDeControle.AnnuaireAvions.get(i));
				TourDeControle.move(TourDeControle.AnnuaireAvions.get(i), TourDeControle.AnnuaireAvions.get(i).getFloatStationDX()  ,  TourDeControle.AnnuaireAvions.get(i).getStationDY() );
		}
		}
		try {
			TourDeControle.CrashDetect();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		repaint();
	}

}