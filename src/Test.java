/**
 * Created by camilopereira on 14-11-17.
 */

public class Test {

    public static void main(String args[]) throws Exception {
        for (int i = 0; i < SetCovering.getInstance().getCostSize(); i++)
            System.out.println(SetCovering.getInstance().getCost(i));
    }

}
