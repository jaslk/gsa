

public class Agent {

    private double inertialMass;
    private double activeMass;
    private double pasiveMass;
    private double position;
    private double velocity;
    private double aceleration;
    private double fitness;
    private double force;


    public Agent(){



    }

   public double calculateForce(int iteration, int totalAgents){
        double totalForce=0;

        for(int i=0; i<totalAgents; i++){
            if (i!=iteration) {

            }
        }

        return totalForce;
   }


    public double calculateMass(int iteration){
        this.inertialMass =

    }


    //FALTA MODIFICARLA PARA QUE SEA PARA UNA ITERACIÓN EN ESPECÍFICO
    public double calculateAceleration(int iteration, int totalAgents){
        return this.aceleration = (calculateForce(iteration, totalAgents) / calculateMass(iteration));
    }

}
