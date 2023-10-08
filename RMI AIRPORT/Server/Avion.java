package Server;
import java.rmi.RemoteException;
import javax.swing.ImageIcon;
import Common.AvionInterface;



public class Avion implements AvionInterface {

	public String identifiant;
	public State etat = State.New;
	public float reservoir = 100;
	ImageIcon state = null;
	
	public Vol flight = null;

	public Avion(String Name) throws java.rmi.RemoteException {
		this.identifiant = Name;
		state = new ImageIcon("ASSETS/vert.png");
	}

	public int getDepX(){
		return this.flight.aeroportDepart.coordX;
	}

	public int getDepY(){
		return this.flight.aeroportDepart.coordY;
	}

	@Override
	public String AeroplaneData() throws RemoteException {
		System.out.println("Donnees du vol");
		System.out.println(this.flight.dataVol() + "Donees de l'avion:\n");
		System.out.println(this.identifiant + "Etat du reservoir : " + this.reservoir + "\n qui a l'etat :" + this.etat
				+ "avec l'identifiant" + this.identifiant);

		return this.identifiant + "Etat du reservoir : " + this.reservoir + "\n qui a l'etat :" + this.etat
				+ "avec l'identifiant" + this.identifiant;
	}

	@Override
	public String DemandePermissionAtterissage() throws RemoteException {
		System.out.println("L'avion " + this.identifiant + "Demande la permission d'atterir");
		if (TourDeControle.Distance(this, this.flight.aeroportArrivee)<100){
		  if(this.flight.aeroportArrivee.cap_Avion > 0 &&
		  String.valueOf(this.flight.aeroportArrivee.PisteAD).equals("true")){
		  this.etat = State.Idle;
		  if (this.flight.vol_type == 0){
			this.reservoir = 85;
			this.flight.changeState(0);
			this.flight.aeroportDepart = this.flight.aeroportArrivee;
			this.flight.NE = this.flight.NE+1;
			this.flight.aeroportArrivee = this.flight.Escales.get(this.flight.NE);
			this.flight.aeroportDepart.cap_carb = this.flight.aeroportDepart.cap_carb - 85;
		  }
		  else {
			this.reservoir = 85;
			this.flight.changeState(0);
			this.flight.aeroportDepart = this.flight.aeroportArrivee;
			this.flight.aeroportArrivee = null;
			this.flight.aeroportDepart.cap_carb = this.flight.aeroportDepart.cap_carb - 85;
		  }
		  
		  this.flight.aeroportDepart.NbPiste = this.flight.aeroportDepart.NbPiste-1;
		  if (this.flight.aeroportDepart.NbPiste == 0){
			this.flight.aeroportDepart.PisteAD = false ;
		  }
		  try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.flight.aeroportDepart.NbPiste = this.flight.aeroportDepart.NbPiste +1;
		this.flight.aeroportDepart.PisteAD = true;
		  return "permission d'aterissage  Accordee";
		  }else{
			return "Permission d'aterrissage non accordee";
			   }
		  }else {
			return "vous etes trop loins de la piste d'atterissage";
				}
}

	@Override
	public String Demandedecollage() throws RemoteException {
		if (TourDeControle.Distance(this, this.flight.aeroportDepart)<100){
		System.out.println("L'avion " + this.identifiant + "Demande la permission de decoller");		
		
		if(String.valueOf(this.flight.aeroportDepart.PisteAD).equals("true")){
			
			this.flight.aeroportDepart.cap_carb = this.flight.aeroportDepart.cap_carb - 85;
			this.flight.changeState(0);
		this.etat = State.Active;
		this.flight.aeroportDepart.NbPiste = this.flight.aeroportDepart.NbPiste-1;
		  if (this.flight.aeroportDepart.NbPiste == 0){
			this.flight.aeroportDepart.PisteAD = false ;
		  }
		  try {
			Thread.sleep(2000);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.flight.aeroportDepart.NbPiste = this.flight.aeroportDepart.NbPiste +1;
		this.flight.aeroportDepart.PisteAD = true;
		return "permission De decollage Accordee";
		}
		else{
		this.etat = State.Standby;
		return "Permission de decollage  non accordee";
	    }
	}else {
		return "vous etes trop loins de la piste de decollage";
	}
}

	
	public String GetName() {
		return this.identifiant;
	}

	public void setName(String name) {
		this.identifiant = name;
	}

	@Override
	public String getName() throws RemoteException {
		// TODO Auto-generated method stub
		return this.identifiant;
	}

	public void setStateStandBy() throws RemoteException {
		this.etat = State.Standby;
	}

	@Override
	public int getPosX() throws RemoteException {
		// TODO Auto-generated method stub
		return this.flight.positionX;
	}

	public void setPosX( int posx) throws RemoteException {
		// TODO Auto-generated method stub
		 this.flight.positionX = posx;
	}
 
	@Override
	public int getPosY() throws RemoteException {
		// TODO Auto-generated method stub
		return this.flight.positionY;
	}

	public void setPosY( int posy) throws RemoteException {
		// TODO Auto-generated method stub
		 this.flight.positionY = posy;
	}

	@Override
	public int getNumvol() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getState(){
		return String.valueOf(this.etat);
	}
	
	public String getReservoir() {
		return String.valueOf(reservoir);
	}


	public float getReservoirf(){
		return this.reservoir;
	}

	public String getFlightNum(){
		return this.flight.numeroVol;
	}

	public ImageIcon getImagestate(){
		return this.state;
	}

	public void setReservoir(float reservoir) {
		this.reservoir = reservoir;
	}

	public int getFloatStationDX(){
		
		
		return this.flight.aeroportArrivee.getCoordX(); 
	}

	public int getStationDY(){
		return this.flight.aeroportArrivee.getCoordY(); 
	}
	
	public void setFlight(Vol flight) {
		this.flight = flight;
	}

	public void setStateBroken() throws RemoteException {
		this.etat = State.Broken;
	}
	
	
}
