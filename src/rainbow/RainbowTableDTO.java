package rainbow;

import java.io.Serializable;

/**
 * Created by rogalsp1 on 09.06.16.
 */
public class RainbowTableDTO implements Serializable {
    private static final long serialVersionUID = -2708237917338857787L;
    private String password;
    private String hash;
    private int iteration;

    public RainbowTableDTO(String password, String hash, int iteration) {
        this.password = password;
        this.hash = hash;
        this.iteration = iteration;
    }

    public String getPassword() {
        return password;
    }

    public String getHash() {
        return hash;
    }

    public int getIteration() {
        return iteration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RainbowTableDTO that = (RainbowTableDTO) o;

        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        return hash != null ? hash.equals(that.hash) : that.hash == null;

    }

    @Override
    public int hashCode() {
        int result = password != null ? password.hashCode() : 0;
        result = 31 * result + (hash != null ? hash.hashCode() : 0);
        return result;
    }
}
