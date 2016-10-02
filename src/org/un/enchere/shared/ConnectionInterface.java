package org.un.enchere.shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ConnectionInterface extends Remote {
	public void connect(String name) throws RemoteException;
	public void disconnect(String name) throws RemoteException;
	public boolean isConnected(String name) throws RemoteException;
}
