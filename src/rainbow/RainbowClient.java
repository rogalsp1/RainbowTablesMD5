package rainbow;

import java.io.BufferedWriter;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class RainbowClient implements Serializable{
    private static final String host = "localhost";
    private static final String name = "Server";
    private static final String firstPassword = "1234";
    private static int iterations = 2000000;

    public static void main(String[] args) {
        Path logFile = Paths.get("app.log");
        if(System.getSecurityManager()==null) {
            System.setSecurityManager(new SecurityManager());
        }
        if(args.length == 2)
            iterations = Integer.parseInt(args[1]);
        try (BufferedWriter writer = Files.newBufferedWriter(logFile,
                StandardCharsets.UTF_8, StandardOpenOption.WRITE)) {
            Registry registry = LocateRegistry.getRegistry(host);
            RainbowTables rainbowTables = (RainbowTables) registry.lookup(name);
            List<RainbowTableDTO> list = rainbowTables.generateRainbowTables();
            for(RainbowTableDTO e : list)
                writer.write(e.getPassword()+";"+e.getHash()+";"+ e.getIteration()+"\n");
            writer.close();
        } catch (Exception e) {
            System.out.println(" "+ e.getMessage());
            e.printStackTrace();
        }
    }



}
