package Server;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.ResultSet;

import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import Common.*;

public class TourDeControle extends Thread {

    public static ArrayList<Avion> AnnuaireAvions = new ArrayList<>();
    public static ArrayList<Station> AnnuaireStations = new ArrayList<>();
    public static ArrayList<Vol> annuaireVols = new ArrayList<>();
    public static void main(String[] args) throws ClassNotFoundException {
        try {
            // Set Security property
            System.setProperty("java.security.policy", "C:/Users/Mazigh/OneDrive/Documents/fichier.policy");
            // Set Hostname for the server using javaProperty
            //Lancer Interface Server 
            TourDeControleGUI frame = new TourDeControleGUI();// Lancement de l'interface
           frame.setVisible(true);

            //Initialiser base de donnees 
            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airport_controller", "root", "");
            java.sql.Statement ps = con.createStatement();
            ResultSet rs;
            rs = ps.executeQuery("SELECT identifiant  FROM Avion  ");
            System.setProperty("java.rmi.server.hostname", "127.0.0.1");
            System.out.println("Server has started .......");
            //Initialisation des avions 
            while (rs.next()) {
                String lastName = rs.getString("identifiant");
                System.out.println(lastName);
                
                AnnuaireAvions.add(new Avion(lastName));
            }
            //Les Stations  
            rs = ps.executeQuery("SELECT *  FROM station  ");
           
            System.out.println("Server has started .......");
            while (rs.next()) {
                String station = rs.getString("Name");
                int pX = rs.getInt("positionX");
                int pY = rs.getInt("positionY");
                AnnuaireStations.add( new Station(station,pX,pY));
                System.out.println(station + pX + pY);
            }

            rs = ps.executeQuery("SELECT *  FROM vol  ");
           
            System.out.println("Server has started .......");
            while (rs.next()) {
                String AD = rs.getString("Aeroport de depart");
                String AA = rs.getString("Aeroport d'arrivée");
                String id = rs.getString("Numero vol");
                int Vt = rs.getInt("Vol_type");
                annuaireVols.add( new Vol(AD,AA,id,Vt));
            }

            for (int i=0; i<annuaireVols.size();i++){
            AffecterVol2(AnnuaireAvions.get(i).getName(),annuaireVols.get(i).numeroVol);
            System.out.println("Objects Created");
            }
            // ***********************************************************************************************************************
            // */

            Registry registry = LocateRegistry.createRegistry(9100);
            for (int w = 0; w < AnnuaireAvions.size(); w++) {
                AvionInterface stub1 = (AvionInterface) UnicastRemoteObject.exportObject(AnnuaireAvions.get(w), 0);
               
                registry.rebind(AnnuaireAvions.get(w).identifiant, stub1);
            }

            /************************************************************************************************************************* */

            System.out.println("Exporting and binding of objects has been completed .. ");

        } catch (Exception e) {
            System.out.println("Some SERVER eRROR" + e);
        }
        
    }

    public static  Station FindStation(String id) {
    	for(int i=0; i<AnnuaireStations.size(); i++) {
    		if (AnnuaireStations.get(i).name.equals(id)) {
    			return AnnuaireStations.get(i);
    		}
    	}
    	return null;
    }

    public static Avion FindAvion(String id) {
    	for(int i=0; i<AnnuaireAvions.size(); i++) {
    		if (AnnuaireAvions.get(i).identifiant.equals(id)) {
    			return AnnuaireAvions.get(i);
    		}
    	}
    	return null;
    }

    public static Vol FindVol(String id) {
    	for(int i=0; i<annuaireVols.size(); i++) {
    		if (annuaireVols.get(i).numeroVol.equals(id)) {
    			return annuaireVols.get(i);
    		}
    	}
    	return null;
    }

    public static void CreateVol(){
        
        Vol A = new Vol();
        annuaireVols.add(A);
    }

    public static void AffecterVol() throws RemoteException{
        String IdAvion = JOptionPane.showInputDialog("A quel avion voulez vous affecter un vol' ? ");
        String IdVol = JOptionPane.showInputDialog("Quel vol voulez vous affecter a cet avion' ? ");
        Avion A = FindAvion(IdAvion);
        Vol B = FindVol(IdVol);
        A.setFlight(B);
        B.setAvionEnCharge(A);
       System.out.println( A.AeroplaneData());
    }


    public static void AffecterVol2(String IdAvion,String IdVol) throws RemoteException{
        Avion A = FindAvion(IdAvion);
        Vol B = FindVol(IdVol);
        A.setFlight(B);
        B.setAvionEnCharge(A);
       System.out.println( A.AeroplaneData());
    }

    static public double Distance(Avion a, Station b) {
        int x2 = b.coordX;
         int y2 = b.coordY;
         int x1 = a.flight.positionX;
         int y1 = a.flight.positionY;
         return Math.sqrt (Math.pow((y2 - y1),2) + Math.pow((x2 - x1),2));
     }

     static public double Distance(Avion a, Avion b) {

        int x2 = b.flight.positionX;
        int y2 = b.flight.positionY;
        int x1 = a.flight.positionX;
        int y1 = a.flight.positionY;

        return Math.sqrt(Math.pow(y2 - y1,2) + Math.pow(x2 - x1,2));
    }

    static public Station FindNearest(Avion a)
    {       
            float threshold = 75 ;
           
            Station nearest = null;
            int i = 0;
        
            if (a.getReservoirf()<50){
            while(  i<TourDeControle.AnnuaireStations.size()){
                if (Distance(a, TourDeControle.AnnuaireStations.get(i))<= threshold)
                {
                    
                    nearest = TourDeControle.AnnuaireStations.get(i);
                    System.out.println(nearest.name);
                    if (nearest != null){
                        a.flight.aeroportArrivee = nearest;
                    }
                }
                i++;
                
            }
        }
    
            return nearest;
    }


    static public void CrashDetect() throws RemoteException
    {

        
        for (int i=0; i< TourDeControle.AnnuaireAvions.size(); i++){

            if(String.valueOf( TourDeControle.AnnuaireAvions.get(i).etat).equals("Active"))
            {
                Avion Plane =  TourDeControle.AnnuaireAvions.get(i);

            for(int j = 0; j< TourDeControle.AnnuaireAvions.size(); j++)
                if (i!=j){

                    if(String.valueOf( TourDeControle.AnnuaireAvions.get(j).etat).equals("Active"))
                    {
                        Avion Plane2 =  TourDeControle.AnnuaireAvions.get(j);
                        System.out.println("check");
                        if (Distance(Plane,Plane2) < 10)
                            {
                                System.out.println("crash detected");
                                //if (Plane.flight.positionY == Plane2.flight.positionY)
                                  //  {
                                        Plane.flight.changeState(2);
                                        Plane.setStateBroken();
                                        Plane2.flight.changeState(2);
                                        Plane2.setStateBroken();
                                    //}
                            }
                    }
        }
    }
  }
}

public static void move(Avion a, int xx, int yy) {

    if (String.valueOf(a.etat).equals("Active")){
    if (a.flight.positionX > xx) {
        a.flight.positionX = a.flight.positionX - 5;
        a.setReservoir(a.getReservoirf() - 1);
    } else {
        if (a.flight.positionX < xx){
        a.flight.positionX = a.flight.positionX + 5;
        a.setReservoir(a.getReservoirf() - 1);
    }else {
        a.setReservoir(a.getReservoirf() - 1);
    }
    }
    if (a.flight.positionY > yy) {
        a.flight.positionY = a.flight.positionY - 5;
        a.setReservoir(a.getReservoirf() - 1);
    } else {
        if (a.flight.positionY < yy){
        a.flight.positionY = a.flight.positionY + 5;
        a.setReservoir(a.getReservoirf() - 1);
        }
        else{
            a.setReservoir(a.getReservoirf() - 1);
        }
    }
}
}

}