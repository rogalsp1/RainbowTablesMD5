package rainbow;

/**
 * Created by rogalsp1 on 09.06.16.
 */
public class Function implements ReduceFunction {
    @Override
    public String reduce(String hash) {
       return hash.replaceAll("\\D+","").substring(0,4);
    }
}
