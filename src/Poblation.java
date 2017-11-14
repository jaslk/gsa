import java.util.ArrayList;

public class Poblation {

    private double initialGravity = 100;
    private double alpha = 20;
    private int iterations = 500;
    private int totalAgents = 36;
    private double gconstant;
    //CREAR ATRIBUTO MEJOR SOLUCIÓN

    private Agent agents[];


    public void execute() {

        int t = 0; //iteración actual
        createAgents(agents, totalAgents);//se crea arreglo de agentes

        //FUNCIÓN INICIALIZAR AGENTES

        while (t <= iterations) {
            //CALCULAR FITNESS PARA CADA AGENTE

            //ACTUALIZAR CTE DE GRAVITACIÓN
            gconstant = get_gconstant(initialGravity, alpha, t, iterations); //actualización de G(t)

            //ACTUALIZAR MEJOR SOLUCIÓN
            updateBestSolution();

            //ACTUALIZAR PEOR SOLUCIÓN
            updateWorstSolution();


            //CALCULAR MASA PARA CADA AGENTE
            updateIntertialMass(agents);


            //ACTUALIZAR ACELERACIÓN PARA CADA AGENTE

            //ACTUALIZAR POSICIÓN Y VELOCIDAD

            t++;
        }

    }


    //función que actualiza la constante de gravitación
    public double get_gconstant(double initialGravity, double alpha, int t, int iterations) {
        double gt;
        gt = initialGravity * Math.exp(-alpha * (t / iterations));
        return gt;
    }


    //función que crea el arreglo de agentes
    public Agent[] createAgents(Agent[] agents, int totalAgents) {
        for (int i = 0; i < totalAgents; i++) {
            agents[i] = new Agent();
        }
        return agents;
    }

    //función que actualiza las fuerzas de cada agente
    public void updateForceAgents(Agent[] agents, double gconstant) {
        for (int i = 0; i < totalAgents; i++) {
            for (int j = 0; j < totalAgents; j++) {
                if (i != j) {
                    agents[i].calculateForce(gconstant, agents[j]);
                }
            }
        }


    }


    public double updateBestSolution() {
        double tmpBest;
        double fit;
        tmpBest = agents[0].getFitness(); //Se asigna primer fitness como el mínimo (mejor solución)

        for (int i = 1; i < totalAgents; i++) {
            fit = agents[i].getFitness();
            if (fit < tmpBest){ //si el fitness del agent i es menor al minimo actual, queda ese
                tmpBest = fit;
            }
        }

        return tmpBest;
    }


    public double updateWorstSolution() {
        double tmpWorst=0; //Se asigna 0 como primer fitness -> se busca el mayor (peor solución)
        double fit;

        for (int i = 0; i < totalAgents; i++) {
            fit = agents[i].getFitness();
            if (fit > tmpWorst){ //si el fitness del agent i es mayor al mayor actual, queda ese
                tmpWorst = fit;
            }
        }

        return tmpWorst;
    }


    public void updateIntertialMass(Agent[] agents) {
        for (int i = 0; i < totalAgents; i++) {

                agents[i].getInertialMass(updateBestSolution(), updateWorstSolution(), agents);
            }


    }
}