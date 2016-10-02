package org.un.enchere;

import java.rmi.Naming;
import java.rmi.RemoteException;

import org.un.enchere.model.Product;
import org.un.enchere.shared.ConnectionInterface;
import org.un.enchere.shared.SaleInterface;

public class Client {
	
	private static String username = "Meeeee";
	
	public static void main(String[] args) {
		try {
			ConnectionInterface connection = (ConnectionInterface)Naming.lookup("connection");
			SaleInterface sale = (SaleInterface)Naming.lookup("sale");
			
			connection.connect(username);
			
			Product frommage = new Product("Frommage", 12.0, username);
			sale.sell(frommage);
			
			//sold(sale.getPersonInBidLock());
			
			connection.disconnect(username);
		} catch (Exception e) {
			System.out.println("Impossible de lancer le client "  + e);
		}
	}
	
	private static void sold(Object personInBidLock) throws RemoteException {
		synchronized (personInBidLock) {
			try {
				personInBidLock.wait();
				System.out.println("Sold!");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
