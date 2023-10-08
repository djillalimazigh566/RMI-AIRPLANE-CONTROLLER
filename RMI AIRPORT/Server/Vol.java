package Server;

import java.time.LocalDate;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;




public class Vol 
{
	public String numeroVol; 
	public LocalDate heureDepart;		
	public LocalDate heureArrivee;		
	public Avion AvionEnCharge;						
	
	public Station aeroportDepart;				
	public Station aeroportArrivee;			
	
	public int vol_type;
	

	public Etat etat;
	public int positionX;
	public int positionY;
	
	public int vitesseAvion = 0;
	public ArrayList<Station> Escales = new ArrayList<>();
	public int  NE = 0;				
    					
					
	public Vol() 
	{
		String IdD = JOptionPane.showInputDialog("Quel est l'aeroport de départ' ? ");
		aeroportDepart = TourDeControle.FindStation(IdD);
		String IdA = JOptionPane.showInputDialog("Quel est l'aeroport d'arrivée' ? ");
		aeroportArrivee = TourDeControle.FindStation(IdA);
		numeroVol =JOptionPane.showInputDialog("Numero du vol :  ");
		heureDepart = null;
		heureArrivee = null;
		this.positionX = aeroportDepart.coordX;
		this.positionY = aeroportDepart.coordY;
		int reply = JOptionPane.showConfirmDialog(null, "Est ce que c'est un vol direct", "Type de vol", JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
   				vol_type = 1;
			} else {
				vol_type = 0;
				int nbEsc =  Integer.parseInt(JOptionPane.showInputDialog("Donnez le nombre d'escales' ? "));
				for (int i=0;i<nbEsc;i++){
					System.out.print("----------------------------------*/*/*/*"+nbEsc);
					int Esc =  Integer.parseInt(JOptionPane.showInputDialog("Quel est l'aeroport de transition' ? "));
					if (TourDeControle.AnnuaireStations.get(Esc)!=null){
					Escales.add(TourDeControle.AnnuaireStations.get(Esc));
					}
				}
				Escales.add(aeroportArrivee);
				aeroportArrivee = Escales.get(0);
			}
	}

	public Vol (String Id, int depart, int arrivee) {
		this.aeroportDepart = TourDeControle.AnnuaireStations.get(depart);
		this.aeroportArrivee = TourDeControle.AnnuaireStations.get(arrivee);
		this.positionX = aeroportDepart.getCoordX();
		this.positionY = aeroportDepart.getCoordY();
		this.numeroVol = Id;
	}

	public Vol (String aD , String aA, String a , int b){
		
		this.aeroportDepart = TourDeControle.FindStation(aD);
		this.aeroportArrivee = TourDeControle.FindStation(aA);
		this.numeroVol = a;
		this.heureDepart = null;
		this.heureArrivee = null;
		this.positionX = aeroportDepart.coordX;
		this.positionY = aeroportDepart.coordY;
		vol_type = b;
			if (vol_type == 0) {
				int nbEsc = 2;
				for (int i=0;i<nbEsc;i++){
					int Esc =(int) Math.random() * 3;
					if (TourDeControle.AnnuaireStations.get(Esc)!=null){
					Escales.add(TourDeControle.AnnuaireStations.get(Esc));
					}
				}
				Escales.add(aeroportArrivee);
				aeroportArrivee = Escales.get(0);
			}
	}

	public String dataVol() 
	{
		String data = "";
		
		//Aeroports de depart et d'arrivee
		data = data+this.aeroportDepart.name;
		data = data+";"+this.aeroportArrivee.name + "\n";
		
		//Donnees de vol de l'avion 
		data =  data+ "\n" + "Position de l'avion " +";"+String.valueOf(this.positionX);
		data = data+";"+String.valueOf(this.positionY);
		
		data = data+"\n vitesse actuelle" +";"+String.valueOf(this.vitesseAvion);
		
		//Numero de vol
		data = data + "numero du vol";
		data = data+";"+this.numeroVol;
		
		return data;
	}


	public void changeState(int i) {
		//Avion A = TourDeControle.FindAvion(AvionEnCharge.identifiant);
    	if (i==0) {
    		this.etat = Etat.Normal ;
    		AvionEnCharge.state = new ImageIcon("ASSETS/vert.png");
    	}
    	if (i==1) {
    		this.etat = Etat.Danger ;
    		AvionEnCharge.state = new ImageIcon("ASSETS/jaune.png");
    	}
		if (i==2) {
    		this.etat = Etat.Catastrophe ;
    		AvionEnCharge.state = new ImageIcon("ASSETS/rouge.png");
    	}
    }

	
	public String getNumVol(){
		return this.numeroVol;
	}

	public int getPositionX() {
		return positionX;
	}
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}
	public int getPositionY() {
		return positionY;
	}
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}
	public void setAvionEnCharge(Avion avionEnCharge) {
		AvionEnCharge = avionEnCharge;
	}
	public Station getAeroportArrivee() {
		return aeroportArrivee;
	}

	public void setAeroportArrivee(Station aeroportArrivee) {
		this.aeroportArrivee = aeroportArrivee;
	}
	public int getVol_type() {
		return vol_type;
	}

	public void setVol_type(int vol_type) {
		this.vol_type = vol_type;
	}
}
