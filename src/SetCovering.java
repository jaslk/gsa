public class SetCovering {

    private int N; //cantidad de columnas de la matriz binaria
    private int M; //cantidad de filas de la matriz binaria
    private int matriz[][];//matriz binaria de dimensiones MxN
    private int coste[]; //vector de N dimensiones que representa el costo de una columna j


    public SetCovering(int N, int M){
        matriz = new int[N][M];
        coste = new int[N];
    }
}
