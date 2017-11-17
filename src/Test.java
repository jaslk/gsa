public class Test {

    public static void main(String args[]) throws Exception {
        for (int i = 1; i < 10; i++) {
            System.out.println("\nPrueba Nº: " + i + "\n");
            Poblation poblation = new Poblation();
            poblation.execute();
        }
    }


}
