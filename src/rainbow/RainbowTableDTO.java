package rainbow;

import java.io.Serializable;

/**
 * Created by rogalsp1 on 09.06.16.
 */
public class RainbowTableDTO implements Serializable {
    private static final long serialVersionUID = -2708237917338857787L;
    private String password;
    private String hash;

    public RainbowTableDTO(String password, String hash) {
        this.password = password;
        this.hash = hash;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
