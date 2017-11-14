import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Vector;

public class SetCovering {

    private int N; //cantidad de columnas de la matriz binaria
    private int M; //cantidad de filas de la matriz binaria
    private int matriz[][];//matriz binaria de dimensiones MxN
    private int cost[]; //vector de N dimensiones que representa el costo de una columna j


    public SetCovering(int M, int N) {
        matriz = new int[M][N];
        cost = new int[N];
    }

    public double calculateCost(int i) {
        double totalCost = 0;
        for (int j = 0; j < N; j++) {
            totalCost = totalCost + this.cost[j] * this.matriz[i][j];
        }
        return totalCost;
    }

    public static Vector<Vector<Float>> loadMatrix() throws Exception {
        String line = null;
        BufferedReader reader = new BufferedReader(new FileReader("test.txt"));
        Vector<Vector<Float>> matrix = new Vector<>();
        //Se lee linea por linea del archivo hasta llegar a nill
        while ((line = reader.readLine()) != null) {
            //Se crea un nuevo vector para almacenar los valores de la linea
            Vector<Float> rowVector = new Vector<>();
            for (String value : line.split(",")) { // Se separa por comas
                rowVector.add(Float.valueOf(value));
            }
            matrix.add(rowVector);
        }
        reader.close();

        System.out.println("Matrix : " + matrix);
        return matrix;
    }
}
