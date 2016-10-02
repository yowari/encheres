package org.un.enchere;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import org.un.enchere.shared.Connection;
import org.un.enchere.shared.Sale;

public class Server {
	
	public static void main(String[] args) {
		try {
			Connection connection = new Connection();
			Sale sale = new Sale(connection);
			
			LocateRegistry.createRegistry(1099);
			Naming.rebind("connection", connection);
			Naming.rebind("sale", sale);
			
			System.out.println("Serveur pret");
			
		} catch (Exception e) {
			System.err.println("Impossible de lancer le serveur: "  + e);
		}
	}

}
