import java.util.ArrayList;

public class Poblation {

    private double initialGravity = 100; //Go
    private double alpha = 20; // cte alpga
    private int iterations = 1000; //cantidad total de iteraciones
    private int totalAgents = 40; //cantidad total de agentes (soluciones)
    private double gconstant; //G(t)
    private int dimention = 36; //dimensión de las posiciones de los agentes

    private Agent agents[]; //arreglo de agentes
    private int gSolution[]; //arreglo que contiene la mejor solución € [0,1]

    public void execute() {

        int t = 0; //iteración actual
        createAgents();//se crea arreglo de agentes

        for(int i=0; i<dimention; i++) {
            gSolution[i] = 1;
        }

        while (t <= iterations) {
            binarization(); //proceso de binarización de las posiciones --> LISTO

            if(factandRepair()) { //proceso de factibilidad y reparación de soluciones -->

                getFitness(); //se calcula el fitness para cada agente --> LISTO

                updateGBest(); //se actualiza gBest --> LISTO

                gconstant = get_gconstant(initialGravity, alpha, t, iterations); //actualización de G(t) --> LISTO

                updateIntertialMass(); //actualización de la masa inercial --> LISTO

                updateForceAgents(gconstant); //actualización de las fuerzas de gravitación --> LISTO

                updateAceleration(); //actualización de las aceleraciones de cada agente --> LISTO

                updateVelandPos(); // --> LISTO

                t++; //avanza a la siguiente iteración
            }
        }

        /*
            System.out.print("La mejor solución tiene un costo de: " + getBestSolution());
         */

    }


    //función que actualiza la constante de gravitación
    public double get_gconstant(double initialGravity, double alpha, int t, int iterations) {
        double gt;
        gt = initialGravity * Math.exp(-alpha * (t / iterations));
        return gt;
    }


    //función que crea el arreglo de agentes
    public Agent[] createAgents() {
        for (int i = 0; i < totalAgents; i++) {
            agents[i] = new Agent(dimention);
        }
        return agents;
    }


    //función que calcula el fitness para cada agente
    public void getFitness(){
        for (int i=0; i<totalAgents; i++){
            agents[i].getFitness();
        }
    }

    //función que actualiza gBest
    public void updateGBest(){
        Agent bAgent=agents[0];
        double fit;
        double tmpBest = agents[0].getFitness(); //Se asigna primer fitness como el mínimo (mejor solución)

        for (int i = 1; i < agents.length; i++) {
            fit = agents[i].getFitness();
            if (fit < tmpBest){ //si el fitness del agent i es menor al minimo actual, queda ese
                tmpBest = fit;
                bAgent=agents[i];
            }
        }

        bAgent.updateG(gSolution);


    }

    //función que actualiza las fuerzas de cada agente
    public void updateForceAgents(double gconstant) {
        for (int i = 0; i < agents.length; i++) {
            for (int j = 0; j < agents.length; j++) {
                if (i != j) {
                    agents[i].calculateForce(gconstant, agents[j]);
                }
            }
        }
    }


    //retorna la mejor solución
    public double getBestSolution() {
        double tmpBest;
        double fit;
        tmpBest = agents[0].getFitness(); //Se asigna primer fitness como el mínimo (mejor solución)

        for (int i = 1; i < agents.length; i++) {
            fit = agents[i].getFitness();
            if (fit < tmpBest){ //si el fitness del agent i es menor al minimo actual, queda ese
                tmpBest = fit;
            }
        }

        return tmpBest;
    }

    //retorna la peor solución
    public double getWorstSolution() {
        double tmpWorst=0; //Se asigna 0 como primer fitness -> se busca el mayor (peor solución)
        double fit;

        for (int i = 0; i < agents.length; i++) {
            fit = agents[i].getFitness();
            if (fit > tmpWorst){ //si el fitness del agent i es mayor al mayor actual, queda ese
                tmpWorst = fit;
            }
        }
        return tmpWorst;
    }


    //actualiza la masa inercial de cada agente
    public void updateIntertialMass() {
        for (int i = 0; i < agents.length; i++) {
                agents[i].getInertialMass(getBestSolution(), getWorstSolution(), agents);
            }

    }

    //actualiza aceleración de cada agente
    public void updateAceleration(){
        for (int i = 0; i < agents.length; i++) {
            agents[i].calculateAceleration();
        }
    }


    //actualiza velocidad y posición de cada agente
    public void updateVelandPos(){
        for(int i=0; i<agents.length; i++){
            agents[i].calculateVelocityandPosition();
        }
    }

    //comprueba si es factible un agente, en caso de no serlo lo repara
    public boolean factandRepair(){
        for (int i=0; i<agents.length; i++){
            while(!agents[i].isFactible()){
                agents[i].repair();
            }
        }
        return true;
    }

    //binariza cada agente
    public void binarization(){
        for(int i=0; i<agents.length; i++){
            agents[i].binarization();
        }
    }

}