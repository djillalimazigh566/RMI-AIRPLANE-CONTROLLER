package Client;

import java.io.*;

import java.util.*;


import javax.swing.JOptionPane;

import Common.AvionInterface;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

class Client {
	public static int capacite_reservoir = 100;
	public static String numAeroplane;

	public static String coulor;
	public static ArrayList<String> ListClients = new ArrayList<>() ;

	public static boolean departure;
	private static Random randomInt = new Random();

	public static int getRandomInt(int min, int max) {
		int range = (max - min) + 1;
		return randomInt.nextInt(range) + min;
	}

	
	public static void main(String[] args) throws IOException {

		try {

			System.setProperty("java.security.policy", "C:/Users/Mazigh/OneDrive/Documents/fichier.policy");
			System.out.println("Security polivy accepted");
			Registry registry = LocateRegistry.getRegistry("127.0.0.1", 9100);
			
			String Identifiant = new String();
			Identifiant = JOptionPane.showInputDialog("Quel est le nom de l'avion ? ");
			AvionInterface avion = (AvionInterface) registry.lookup(Identifiant);
			InterfaceClient frame1 = new InterfaceClient(avion);
			frame1.setVisible(true);
		} catch (Exception e) {
			System.out.println("Erreur d'acces a un objet distant");
			System.out.println(e.toString());
		}

	}

}
