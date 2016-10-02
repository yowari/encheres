package org.un.enchere.shared;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Timer;
import java.util.TimerTask;

import org.un.enchere.model.Product;

public class Sale extends UnicastRemoteObject implements SaleInterface {
	
	private static final long serialVersionUID = -4528104732486194393L;
	
	private ConnectionInterface connection;
	private Product product = null;
	private String buyer = null;
	private Timer timer = new Timer("CurrentSale");
	private long delay = 5 * 1000;
	
	private final Object sellerLock = new Object();
	private final Object buyerLock = new Object();
	private final Object personInBidLock = new Object();

	public Sale(ConnectionInterface connection) throws RemoteException {
		super();
		this.connection = connection;
	}
	
	@Override
	public Product getProductInSale() throws RemoteException {
		return product;
	}

	@Override
	public void sell(Product newProduct) throws RemoteException {
		synchronized (sellerLock) {
			/*if (!connection.isConnected(product.getSeller())) {
				throw new RemoteException("Client non connecte");
			}*/
			
			if (product != null) {
				try {
					sellerLock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			product = newProduct;
			Sale sale = this;
			
			timer.schedule(new TimerTask() {
	
				@Override
				public void run() {
					synchronized (sellerLock) {
						System.out.println("Temps de vente ecoule");
						sellerLock.notify();
						
						//personInBidLock.notifyAll();
						
						product = null;
					}
				}
				
			}, delay);
		}
	}
	
	@Override
	public void outbid(String username, double price) throws RemoteException {
		synchronized (buyerLock) {
			if (price <= this.product.getPrice()) {
				throw new RemoteException("New price must be superior");
			}
			
			if (!connection.isConnected(username)) {
				throw new RemoteException("Client non connecte");
			}
			
			this.product.setPrice(price);
			this.buyer = username;
		}
	}
	
	@Override
	public String getBuyer() throws RemoteException {
		return buyer;
	}
	
	@Override
	public Object getPersonInBidLock() throws RemoteException {
		return personInBidLock;
	}

}
