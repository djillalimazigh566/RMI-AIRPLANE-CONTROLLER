package Server;

import javax.swing.ImageIcon;

class Station{
	public String Id; 
	public String name;	
	public int cap_carb;
	public int cap_Avion;                                                        
	public int coordX;								
	public int coordY;								
	ImageIcon state = null;
	public boolean PisteAD;
	public int NbPiste = 1;	
	
	//constructeur1
	public Station(String idt, String nom, int capa, int capr, int x,int y ){
	this.Id = idt;
	this.name = nom;
	this.cap_carb = capr;
	this.cap_Avion = capa;
	this.coordX= x;
	this.coordY = y;
	this.PisteAD = true;
	}
	//constructeur2
	public Station(String name, int x, int y ) {
		this.name = name;
		this.coordX = x;
		this.coordY = y;
		PisteAD = true;
		this.cap_Avion = 10;
		this.state = new ImageIcon("ASSETS/Airpot.png");
	}
	
	public Station(){
	
	}

	public int getCoordX() {
		return coordX;
	}
	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}
	public int getCoordY() {
		return coordY;
	}
	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}
}
