package Client;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.Timer;

import Common.AvionInterface;

public class RadarClient extends JComponent implements ActionListener  {
	
    int tx = 25;
    int ty = 25;
    AvionInterface tiyara;
    RadarClient(AvionInterface tiyara)
    {
    	this.tiyara = tiyara;
        this.setSize(385, 460);
        Timer timer = new Timer(1000, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;

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
        for (int i=1; i<=nbCercles;i++) {
            int y = taille/2;
            g2d.drawOval(192-y, 230-y, taille, taille);
            taille = taille + 50;
        }
        //DRAW COMPONENT 
        try {
            
            if(String.valueOf(tiyara.getState()).equals("Active") ){
            g2d.drawLine(tiyara.getPosX()+10, tiyara.getPosY()+10, tiyara.getFloatStationDX()+10, tiyara.getStationDY() +10);    
            g2d.drawImage(tiyara.getImagestate().getImage(), tiyara.getPosX(),tiyara.getPosY(), null);
            }

            ImageIcon icone = new ImageIcon("ASSETS/Airpot.png");
            g2d.drawImage(icone.getImage(), tiyara.getDepX(),tiyara.getDepY(),null);
            g2d.drawImage(icone.getImage(), tiyara.getFloatStationDX(),tiyara.getStationDY(),null);
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    

    }

    public void move(AvionInterface a, int xx, int yy) throws RemoteException {
        if(tiyara.getState().equals("Active")){

        if (DistanceAeroport(a)>100){
            
        
		if (a.getPosX() > xx) {
			a.setPosX(a.getPosX() - 5);
			a.setReservoir(a.getReservoirf() - 1);
		} else {
			a.setPosX(a.getPosX() + 5);
			a.setReservoir(a.getReservoirf() - 1);
		}

		if (a.getPosY() > yy) {
			a.setPosY(a.getPosY() - 5);
			a.setReservoir(a.getReservoirf() - 1);
		} else {
			a.setPosY(a.getPosY() + 5);
			a.setReservoir(a.getReservoirf() - 1);
		}

        if (a.getReservoirf() < 75){
			/*a..changeState(1);
			
				Station urgence = TourDeControle.FindNearest(a);
				if(urgence != null){
				a.flight.setAeroportArrivee(urgence);
				}
			*/	
		}
    }
}
	}
  

    @Override
    public void actionPerformed(ActionEvent e) {
    		try {
                if (tiyara.getState().equals("Active")){
                    
                	//move(tiyara,tiyara.getFloatStationDX(),tiyara.getStationDY());
                    System.out.println(DistanceAeroport(tiyara));

                }
            } catch (RemoteException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            
            repaint();

    }
    public double DistanceAeroport ( AvionInterface a) throws RemoteException {

		int x1,x2,y1,y2;
        x2 = a.getStationDY();
        y2 = a.getStationDY();
        x1 = a.getPosX();
        y1 = a.getPosY();
        return Math.sqrt(Math.pow(y2 - y1, 2) + Math.pow(x2 - x1,2));
    }
}
