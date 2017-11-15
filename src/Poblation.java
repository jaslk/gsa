import java.util.ArrayList;

public class Poblation {

    private double initialGravity = 100;
    private double alpha = 20;
    private int iterations = 1000;
    private int totalAgents = 40;
    private double gconstant;
    private double gSolution[];
    private int dimention = 36;

    private Agent agents[];


    public void execute() {

        int t = 0; //iteración actual
        createAgents();//se crea arreglo de agentes
        gSolution = new double[totalAgents];





        while (t <= iterations) {
            binarization();
            factandRepair();
            //CALCULAR FITNESS PARA CADA AGENTE

            //ACTUALIZAR CTE DE GRAVITACIÓN
            gconstant = get_gconstant(initialGravity, alpha, t, iterations); //actualización de G(t)


            //CALCULAR MASA PARA CADA AGENTE
            updateIntertialMass();


            //ACTUALIZAR ACELERACIÓN PARA CADA AGENTE
            updateAceleration();

            //ACTUALIZAR POSICIÓN Y VELOCIDAD
            updateVelandPos();

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
    public Agent[] createAgents() {
        for (int i = 0; i < totalAgents; i++) {
            agents[i] = new Agent(dimention);
        }
        return agents;
    }

    //función que actualiza las fuerzas de cada agente ---> NO SE DONDE OCUPO ESTA HUEA, REVISA BIEN FERNANDITA.
    public void updateForceAgents(double gconstant) {
        for (int i = 0; i < agents.length; i++) {
            for (int j = 0; j < agents.length; j++) {
                if (i != j) {
                    agents[i].calculateForce(gconstant, agents[j]);
                }
            }
        }


    }


    //actualiza la mejor solución
    public double updateBestSolution() {
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

    //actualiza la peor solución
    public double updateWorstSolution() {
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
                agents[i].getInertialMass(updateBestSolution(), updateWorstSolution(), agents);
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
    public void factandRepair(){
        for (int i=0; i<agents.length; i++){
            if(!agents[i].isFactible()){
                agents[i].repair();
            }
        }
    }

    //binariza cada agente
    public void binarization(){
        for(int i=0; i<agents.length; i++){
            agents[i].binarization();
        }

    }

}