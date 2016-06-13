package rainbow;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RainbowServer implements RainbowTables {
    private static final String name = "Server";
    private ReduceFunction reduceFunction;
    private static int maxIteration = 20;
    private static int tableSize = 1000;

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
            System.out.println("RainbowServer: is ready");
        } catch (Exception e) {
            System.out.println("RainbowServer exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<RainbowTableDTO> generateRainbowTables() throws RemoteException {
        List<RainbowTableDTO> rainbowTables = new ArrayList<>();
        for (int i = 0; i< tableSize; i++) {
            String password = ""+((int)(Math.random()*9000)+1000);
            String reducedPassword = password;
            String hash = "";
            for(int j = 0; j< maxIteration; j++) {
                byte[] bytes = MD5.computeMD5(reducedPassword.getBytes());
                hash = MD5.toHexString(bytes);
                reducedPassword = reduceFunction.reduce(hash);
            }
            RainbowTableDTO dto = new RainbowTableDTO(password,hash, maxIteration);
            rainbowTables.add(dto);
        }
        return rainbowTables;

    }
}