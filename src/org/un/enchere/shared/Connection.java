package org.un.enchere.shared;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;

public class Connection extends UnicastRemoteObject implements ConnectionInterface {
	
	private static final long serialVersionUID = 1166918262114017278L;
	
	private LinkedList<String> clients;

	public Connection() throws RemoteException {
		super();
		clients = new LinkedList<String>();
	}

	@Override
	public void connect(String name) throws RemoteException {
		if (clients.contains(name)) {
			throw new RemoteException("Client deja connecte");
		}
		
		clients.add(name);
		System.out.println("Nouveau client connecte " + name);
	}
	
	@Override
	public void disconnect(String name) throws RemoteException {
		if (clients.remove(name)) {
			System.out.println(name + " disconnected");
		} else {
			System.out.println(name + " not connected to disconnect");
		}
	}

	@Override
	public boolean isConnected(String name) throws RemoteException {
		return clients.contains(name);
	}
}
