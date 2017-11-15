import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Vector;

public class SetCovering {

    //Singleton
    private static SetCovering instance;

    private SetCovering() {
        try {
            loadCost();
            loadMatrix();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized SetCovering getInstance() {
        if (instance == null) {
            instance = new SetCovering();
        }
        return instance;
    }

    private int N; //cantidad de columnas de la matriz binaria
    private int M; //cantidad de filas de la matriz binaria


    private Vector<Vector<Integer>> matrix = new Vector<>();//matriz binaria de dimensiones MxN
    private Vector<Float> costs = new Vector<>(); //vector de N dimensiones que representa el costo de una columna j


    public double calculateCost(int i) {
        double totalCost = 0;
        for (int j = 0; j < N; j++) {
            totalCost = totalCost + this.costs.get(i) * this.matrix.get(i).get(j);
        }
        return totalCost;
    }

    private void loadMatrix() throws Exception {
        String line = null;
        BufferedReader reader = new BufferedReader(new FileReader("test.txt"));
        //Se lee linea por linea del archivo hasta llegar a nill
        while ((line = reader.readLine()) != null) {
            //Se crea un nuevo vector para almacenar los valores de la linea
            Vector<Integer> rowVector = new Vector<>();
            for (String value : line.split(",")) { // Se separa por comas
                rowVector.add(Integer.valueOf(value));
            }
            this.matrix.add(rowVector);
        }
        reader.close();
    }

    private void loadCost() throws Exception {
        String line = null;
        BufferedReader reader = new BufferedReader(new FileReader("costos.txt"));
        while ((line = reader.readLine()) != null) {
            for (String value : line.split(",")) { // Se separa por comas
                costs.add(Float.valueOf(value));
            }
        }
    }

    public int getContrain(int i, int j) {
        return matrix.get(i).get(j);
    }

    public Float getCost(int i) {
        return costs.get(i);
    }

    public int getContrainsRowSize() {
        return matrix.size();
    }

    public int getContrainsColumnSize() {
        return matrix.firstElement().size();
    }

    public int getCostSize() {
        return costs.size();
    }


}
