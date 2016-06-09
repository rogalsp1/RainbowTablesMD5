
package rainbow;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class RainbowClient {

    public static void main(String[] args) {

        if(System.getSecurityManager()==null) {
            System.setSecurityManager(new SecurityManager());
        }

        try {

            String host = "localhost";
            String name = "Server";
            Registry registry = LocateRegistry.getRegistry(host);
            RainbowTables rainbowTables = (RainbowTables) registry.lookup(name);

            List<RainbowTableDTO> list = rainbowTables.generateRainbowTables("1111");
            list.stream().forEach(x -> System.out.println("PASSWORD: " + x.getPassword() + "HASH: " +x.getHash()));
        } catch (Exception e) {
            System.out.println("CalculateClient exception: "+ e.getMessage());
            e.printStackTrace();
        }


    }



}
