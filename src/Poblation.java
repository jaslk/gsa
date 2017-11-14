import java.util.ArrayList;

public class Poblation {

    private double initialGravity = 100; //constante de gravitación inicial Go
    private double alpha = 20; //Alpha inicial
    private int t_max = 100; //número máximo de iteraciones
    private int totalAgents = 36; //cantidad total de agentes
    private double gconstant;

    ArrayList<Agent> agents = new ArrayList<>();

    public void execute(){

        SetCovering scp = new SetCovering(36,totalAgents); // M = numero de restricciones - N = cantidad de regiones

        generatePob(scp);  //se genera la población inicial

        //AGREGAR FÓRMULA PARA INICIALIZAR LAS POSICIONES DE LOS AGENTES
       /* for ( int i = 0; i<= totalAgents; i++ ){
                agents.add(new Agent());
        }

        for (int i =0; i<iterations; i++){
            for(Agent a: agents){
                a.calculateForce();
             }
        }*/

    }

    //Función para calcular la constante de gravitación
    public double get_gconstant(double initialGravity, double alpha, int t, int t_max){
        gconstant = initialGravity * Math.exp(-alpha * (t/t_max));

        return gconstant;
    }



    //Se genera la población de manera random
    public void generatePob(SetCovering scp){

    }
}
