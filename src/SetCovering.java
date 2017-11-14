public class SetCovering {

    private int N; //cantidad de columnas de la matriz binaria
    private int M; //cantidad de filas de la matriz binaria
    private int matriz[][];//matriz binaria de dimensiones MxN
    private int cost[]; //vector de N dimensiones que representa el costo de una columna j


    public SetCovering(int M, int N){
        matriz = new int[M][N];
        cost = new int[N];
    }

    public double calculateCost(int i){
        double totalCost=0;
        for(int j=0; j<N; j++){
            totalCost= totalCost + this.cost[j]*this.matriz[i][j];
        }
        return totalCost;
    }

    private static Vector<Vector<Float>> loadMatrixAsVector() throws Exception {
        String line = null;
        BufferedReader reader = new BufferedReader(new FileReader(
                "D:\\test.txt"));
        Vector<Vector<Float>> matrix = new Vector<Vector<Float>>();
        while ((line = reader.readLine()) != null) {
            Vector<Float> rowVector = new Vector<Float>();
            for (String value : line.split(" ")) {
                rowVector.add(Float.valueOf(value));
            }
            matrix.add(rowVector);
        }
        reader.close();

        System.out.println("Matrix : " + matrix);
        return matrix;
    }
}
