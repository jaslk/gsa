import java.util.Random;

public class Agent {

    private double inertialMass;
    private double position[];
    private double velocity[];
    private double aceleration[];
    private double fitness;
    private double force[];
    private int dimention;



    public Agent(){
        position = new double[dimention];
        velocity = new double[dimention];
        aceleration = new double[dimention];
        force = new double[dimention];

        for(int i=0; i<dimention; i++){
            position[i]=0;
            velocity[i]=0;
            aceleration[i]=0;
            force[i]=0;
        }

        inertialMass = 0;
        fitness = 0;

    }

    public void calculateForce(double gravitation, Agent agent_j) {
        Random rnd = new Random();
        for(int j=0; j<dimention; j++){
            force[j] = force[j] + (rnd.nextFloat() * forceOtherAgents(gravitation,agent_j,agent_j.position[j],this.position[j]));
        }
    }


    public void calculateMass(int i){

    }


    //FALTA MODIFICARLA PARA QUE SEA PARA UNA ITERACIÓN EN ESPECÍFICO
    /*public double calculateAceleration(int iteration, int totalAgents){
        return this.aceleration = (calculateForce(iteration, totalAgents) / calculateMass(iteration));
    }
*/



    public double forceOtherAgents(double gravitation, Agent agent_j,double posj, double posi){
        double forceAct=0;
        double epsilon=0.1;

        forceAct = gravitation * ((this.inertialMass * agent_j.inertialMass)/(distance(this.position,agent_j.position)*epsilon))*(posj-posi);

        return forceAct;
    }

    public double distance(double[] posAgentj,double[] posAgenti){
        double r=0;
        double sum=0;

        for(int i=0; i<dimention; i++){
            sum = sum + Math.pow(posAgentj[i]-posAgenti[i],2);
        }
        r = Math.pow(sum,0.5);

        return r;
    }
}