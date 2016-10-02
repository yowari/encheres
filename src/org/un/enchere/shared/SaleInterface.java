package org.un.enchere.shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

import org.un.enchere.model.Product;

public interface SaleInterface extends Remote {
	public Product getProductInSale() throws RemoteException;
	public void sell(Product product) throws RemoteException;
	public void outbid(String username, double price) throws RemoteException;
	public String getBuyer() throws RemoteException;
	public Object getPersonInBidLock() throws RemoteException;
}
