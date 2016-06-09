package rainbow;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RainbowServer implements RainbowTables {
    private static final String name = "Server";
    private static final int rainbowTableSize = 10000;
    private ReduceFunction reduceFunction;

    public RainbowServer() {
        reduceFunction = new Function();
    }

    public static void main(String[] args) {


        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());

        }

        try {
            RainbowServer server = new RainbowServer();

            RainbowTables stub = (RainbowTables) UnicastRemoteObject.exportObject(server, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, server);
            System.out.println("Server: is ready");
        } catch (Exception e) {
            System.out.println("RainbowServer exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<RainbowTableDTO> generateRainbowTables(String password) throws RemoteException {
        List<RainbowTableDTO> rainbowTables = new ArrayList<>();
        for(int i = 0; i<rainbowTableSize;i++) {
            byte[] bytes = MD5.computeMD5(password.getBytes());
            String hash = MD5.toHexString(bytes);
            RainbowTableDTO rainbowDTO = new RainbowTableDTO(password, hash);
            rainbowTables.add(rainbowDTO);
            password = reduceFunction.reduce(hash);
        }

        System.out.println("Server----------------------------------------------------------\n"+rainbowTables);
        return rainbowTables;

    }
}