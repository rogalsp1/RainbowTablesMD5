
package rainbow;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RainbowClient {

    public static void main(String[] args) {

        if(System.getSecurityManager()==null) {
            System.setSecurityManager(new SecurityManager());
        }

        try {

            String host = args[0];
            String name = args[1];
            Registry registry = LocateRegistry.getRegistry(host);
            RainbowTables rainbowTables = (RainbowTables) registry.lookup(name);

            int a = Integer.parseInt(args[2]);
            int b = Integer.parseInt(args[3]);
            System.out.println(rainbowTables.add(a, b));


        } catch (Exception e) {
            System.out.println("CalculateClient exception: "+ e.getMessage());
            e.printStackTrace();
        }


    }



}
