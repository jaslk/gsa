import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Vector;

public class SetCovering {

    private int N; //cantidad de columnas de la matriz binaria
    private int M; //cantidad de filas de la matriz binaria
    private Vector<Vector<Integer>> matrix = new Vector<>();//matriz binaria de dimensiones MxN
    private Vector<Float> costs = new Vector<>(); //vector de N dimensiones que representa el costo de una columna j

    // Minimo
    private Vector<Integer> miniumBin = new Vector<>();//matriz binaria de dimensiones MxN

    //Singleton
    private static SetCovering instance;

    private SetCovering() {
        try {
            loadCost();
            loadMatrix();
            resetNextMinium();
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

    public int getConstrain(int i, int j) {
        return matrix.get(i).get(j);
    }

    public Float getCost(int i) {
        return costs.get(i);
    }

    public int getConstrainsRowSize() {
        return matrix.size();
    }

    public int getConstrainsColumnSize() {
        return matrix.firstElement().size();
    }

    public int getCostSize() {
        return costs.size();
    }

    private void setZeroNumBin() {
        for (int i = 0; i < getCostSize(); i++) {
            miniumBin.add(1);
        }
    }

    public int getNextMiniumIndex() {
        return getIndexMinium();
    }

    public void resetNextMinium() {
        setZeroNumBin();
    }

    private int getIndexMinium() {
        // se elige como minimo el primer numero valido
        int j = 0;
        while (miniumBin.get(j) == 0)
            j++;
        // Se asgina dichos valores minimos iniciales
        float minium = costs.get(j);
        int final_index = j;
        // Se encuentra el numevo minimo
        for (int i = 0; i < getCostSize(); i++) {
            if ((costs.get(i) < minium) && miniumBin.get(i) == 1) {
                minium = costs.get(i);
                final_index = i;
            }
        }
        // Se merca el minimo como utilizado
        miniumBin.set(final_index, 0); // Colocar en 0
        return final_index;
    }
}
