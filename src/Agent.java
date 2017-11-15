import java.util.Random;

public class Agent {

    private double inertialMass;
    private double gravitationalMass;
    private double position[];
    private double velocity[];
    private double aceleration[];
    private double fitness;
    private double force[];
    private double euler = 2.71828;
    private Random rnd = new Random();


    //constructor de la clase Agent
    public Agent(int dimention) {
        position = new double[dimention];
        velocity = new double[dimention];
        aceleration = new double[dimention];
        force = new double[dimention];
        for (int i = 0; i < dimention; i++) {
            position[i] = rnd.nextInt(2);
            velocity[i] = 0;
            aceleration[i] = 0;
            force[i] = 0;
        }
        inertialMass = rnd.nextDouble() * (100000 - 100) + 100000; //COMPROBARRRR ESTO
        fitness = 0;

    }

    //se calcula la fuerza de gravitación
    public void calculateForce(double gravitation, Agent agent_j) {
        for (int j = 0; j < this.position.length; j++) {
            force[j] = force[j] + (rnd.nextFloat() * forceOtherAgents(gravitation, agent_j, agent_j.position[j], this.position[j]));
        }
    }


    //se calcula la fuerza gravitacional que otros agentes ejercen sobre él
    public double forceOtherAgents(double gravitation, Agent agent_j, double posj, double posi) {
        double forceAct = 0;
        double epsilon = 0.1;

        forceAct = gravitation * ((this.inertialMass * agent_j.inertialMass) / (distance(this.position, agent_j.position) * epsilon)) * (posj - posi);

        return forceAct;
    }


    //se calcula la distancia euclidiana
    public double distance(double[] posAgentj, double[] posAgenti) {
        double r = 0;
        double sum = 0;

        for (int i = 0; i < this.position.length; i++) {
            sum = sum + Math.pow(posAgentj[i] - posAgenti[i], 2);
        }
        r = Math.pow(sum, 0.5);

        return r;
    }


    //se calcula el fitness de cada agente
    public double getFitness() {
        this.fitness=0;

        for(int i=0; i<this.position.length; i++){
            this.fitness = this.fitness + this.position[i]*SetCovering.getInstance().getCost(i);
        }

        return this.fitness;
    }


    //se calcula la masa gravitacional
    public double getGravitationalMass(double best, double worst) {
        gravitationalMass = (this.getFitness() - worst) / (best - worst);
        return gravitationalMass;
    }


    //se actualiza la masa inercial
    public void getInertialMass(double best, double worst, Agent[] agents) {
        double gravMassi = getGravitationalMass(best, worst);
        double sumGravMassj = 0;

        for (int i = 0; i < agents.length; i++) {
            sumGravMassj = sumGravMassj + agents[i].getGravitationalMass(best, worst);
        }
        this.inertialMass = gravMassi / sumGravMassj;

    }


    //se retorna la masa inercial del agente
    public double getMass() {
        return this.inertialMass;
    }


    //se actualiza la aceleración del agente
    public void calculateAceleration() {

        for (int i = 0; i < this.position.length; i++) {
            this.aceleration[i] = this.force[i] / this.inertialMass;
        }
    }


    //se actualiza la velocidad y posición del agente
    public void calculateVelocityandPosition() {
        for (int i = 0; i < this.position.length; i++) {
            this.velocity[i + 1] = rnd.nextFloat() * this.velocity[i] + this.aceleration[i];
            this.position[i + 1] = this.position[i] + this.velocity[i + 1];
        }

    }


    //función que retorna true si la solución es factible y 0 en caso de no serla
    public boolean isFactible() {
        int sum = 0;

        for (int j = 0; j <SetCovering.getInstance().getConstrainsRowSize(); j++) {
            for (int i = 0; i < this.position.length; i++) { //comprueba si cumple con una restricción
                sum = sum + this.position[i] * SetCovering.getInstance().getConstrain(j, i);
            }
            if (sum < 1) { //si no cumple con la restricción retorna false
                return false;
            }
            sum = 0;
        }
        return true; //si cumple con todas las restricciones retorna true
    }


    //repara una solución convirtiéndola en solución factible
    public void repair() {


    }


    //se binariza el vector posición del agente
    public void binarization() {
        double sigmoide;
        for (int i = 0; i < this.position.length; i++) {
            sigmoide = (1 / (1 + Math.pow(euler, -1 * this.position[i])));
            if (sigmoide + 0.5 > 1) {
                this.position[i] = 1;
            } else {
                this.position[i] = 0;
            }
        }
    }

    public void updateG(int[] gBest){
        for(int i=0; i<this.position[i]; i++){
            gBest[i]=(int)this.position[i];
        }

    }

}