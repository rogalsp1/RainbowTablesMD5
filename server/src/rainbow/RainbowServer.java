package rainbow;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class RainbowServer implements RainbowTables {


    public static void main(String[] args) {


        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());

        }

        String name = args[0];
        try {
            RainbowServer server = new RainbowServer();

            RainbowTables stub = (RainbowTables) UnicastRemoteObject.exportObject(server, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, server);
            System.out.println("CalculateServer: is ready");
        } catch (Exception e) {
            System.out.println("CalculateServer exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public String add(int a, int b) throws RemoteException {
        System.out.println("Server wykonal dzialanie: "+a+" + "+b+" = "+(a+b));
        return "Wynik dodawania to : " + (a + b);
    }
}
