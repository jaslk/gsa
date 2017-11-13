import java.util.ArrayList;

public class Poblation {

    private double initialGravity = 100;
    private double alpha = 20;
    private int iterations = 100;
    private int totalAgents = 36;
    private double gconstant;

    ArrayList<Agent> agents = new ArrayList<>();

    public void execute(){

        SetCovering scp = new SetCovering(36,36); // M = numero de restricciones - N = cantidad de regiones
        //se inicializa la población
        generatePob(scp);


        //AGREGAR FÓRMULA PARA INICIALIZAR LAS POSICIONES DE LOS AGENTES
        for ( int i = 0; i<= totalAgents; i++ ){
                agents.add(new Agent());
        }

        for (int i =0; i<iterations; i++){
            for(Agent a: agents){
                a.calculateForce();
             }
        }

    }


    //Función para calcular la constante de gravitación
    public double get_gconstant(double initialGravity, double initialAceleration, int i, int iterations){
        gconstant = initialGravity * Math.exp(-alpha * (i/iterations));

        return gconstant;
    }


    //Se genera la población de manera random
    public void generatePob(SetCovering scp){

    }
}
