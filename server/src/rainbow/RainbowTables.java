package rainbow;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface RainbowTables extends Remote {


    String add(int a,int b) throws RemoteException;


}
