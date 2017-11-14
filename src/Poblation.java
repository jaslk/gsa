import java.util.ArrayList;

public class Poblation {

    private double initialGravity = 100;
    private double initialAceleration = 20;
    private int iterations = 100;
    private int totalAgents = 36;
    private double gconstant;
    private Agent agents[];


    public void execute(){

        createAgents(agents,totalAgents);

        //se inicializa la población



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


    public double get_gconstant(double initialGravity, double initialAceleration, int i, int iterations){
        gconstant = initialGravity * Math.exp(-initialAceleration * (i/iterations));

        return gconstant;
    }


    public Agent[] createAgents(Agent[] agents,int totalAgents){
        for(int i=0; i<totalAgents; i++){
            agents[i] = new Agent();
        }
        return agents;
    }
}
