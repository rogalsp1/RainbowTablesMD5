package rainbow;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface RainbowTables extends Remote {

    List<RainbowTableDTO> generateRainbowTables() throws RemoteException;


}
