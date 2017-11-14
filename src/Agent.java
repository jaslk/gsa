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
        inertialMass = 0;
        fitness = 0;

    }

   public void calculateForce(int i, double gravitation) {
       double tmpForce=0;
       Random rnd = new Random();
       for(int j=0; j<dimention; j++){
           if(j!=i){
               tmpForce = tmpForce + (rnd.nextFloat() * forceOtherAgents(gravitation));
           }
       }
   }


    public void calculateMass(int i){

    }


    //FALTA MODIFICARLA PARA QUE SEA PARA UNA ITERACIÓN EN ESPECÍFICO
    /*public double calculateAceleration(int iteration, int totalAgents){
        return this.aceleration = (calculateForce(iteration, totalAgents) / calculateMass(iteration));
    }
*/



    public double forceOtherAgents(double gravitation){
        double forceAct=0;



        return forceAct;
    }

}
