import java.util.ArrayList;

public class Poblation {

    private double initialGravity = 100;
    private double initialAceleration = 20;
    private int iterations = 100;
    private int totalAgents = 36;
    private double gconstant;
    //CREAR ATRIBUTO MEJOR SOLUCIÓN

    private Agent agents[];


    public void execute(){

        int t=0; //iteración actual
        createAgents(agents,totalAgents);//se crea arreglo de agentes

        //FUNCIÓN INICIALIZAR AGENTES

        while(t<=iterations) {
            //CALCULAR FITNESS PARA CADA AGENTE

            //ACTUALIZAR CTE DE GRAVITACIÓN

            //ACTUALIZAR MEJOR SOLUCIÓN

            //ACTUALIZAR PEOR SOLUCIÓN

            //CALCULAR MASA PARA CADA AGENTE

            //ACTUALIZAR ACELERACIÓN PARA CADA AGENTE

            //ACTUALIZAR POSICIÓN Y VELOCIDAD
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
