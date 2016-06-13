package rainbow;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by rogalsp1 on 13.06.16.
 */
public class HashCracker {
    public static void main(String[] args ) {
        Path path = Paths.get("app.log");
        String searchedHash = args[0];
        String hash = searchedHash;
        String findedPassword = "";
        String password = "";
        ReduceFunction function = new Function();
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            List<RainbowTableDTO> lines = reader.lines().map(x -> {
                String[] line = x.split(";");
                return new RainbowTableDTO(line[0],line[1],Integer.parseInt(line[2]));
            }).collect(Collectors.toList());
            for(int i = 0 ; i < 20; i++) {
                for (RainbowTableDTO e : lines) {
                    if (e.getHash().equals(hash)) {
                        findedPassword = e.getPassword();
                        System.out.println("FINDED:" + findedPassword);
                        break;
                    }
                }
                if (findedPassword.equals("")) {
                    password = function.reduce(hash);
                    hash = MD5.toHexString(MD5.computeMD5(password.getBytes()));
                    System.out.println(password + " " + hash);
                } else
                    break;
            }
            System.out.print(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
