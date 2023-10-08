package Common;
 
import java.rmi.RemoteException;

import javax.swing.ImageIcon;

public interface AvionInterface extends java.rmi.Remote {

	public String AeroplaneData() throws java.rmi.RemoteException;

	public String DemandePermissionAtterissage() throws java.rmi.RemoteException;

	public String Demandedecollage() throws java.rmi.RemoteException;

	public String getName() throws java.rmi.RemoteException;

	public int getPosX() throws java.rmi.RemoteException;

	public int getPosY() throws java.rmi.RemoteException;

	public int getNumvol() throws java.rmi.RemoteException;

	public void setStateStandBy() throws java.rmi.RemoteException;

	public String getState() throws java.rmi.RemoteException;

	public String getReservoir() throws java.rmi.RemoteException;

	
	public float getReservoirf() throws java.rmi.RemoteException;


	public void setReservoir(float res) throws java.rmi.RemoteException;

	public String getFlightNum() throws java.rmi.RemoteException;

	public ImageIcon getImagestate() throws java.rmi.RemoteException;

	public void setPosX( int posx) throws RemoteException ;

	public void setPosY( int posy) throws RemoteException ;

	public int getFloatStationDX() throws RemoteException;

	public int getStationDY() throws RemoteException;

	public int getDepX() throws RemoteException;

	public int getDepY() throws RemoteException;
	


};
