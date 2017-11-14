import java.util.ArrayList;

public class Poblation {

    private double initialGravity = 100;
    private double alpha = 20;
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
            gconstant = get_gconstant(initialGravity,alpha,t,iterations);

            //ACTUALIZAR MEJOR SOLUCIÓN

            //ACTUALIZAR PEOR SOLUCIÓN

            //CALCULAR MASA PARA CADA AGENTE

            //ACTUALIZAR ACELERACIÓN PARA CADA AGENTE

            //ACTUALIZAR POSICIÓN Y VELOCIDAD

            t++;
        }

    }


    public double get_gconstant(double initialGravity, double alpha, int t, int iterations){
        double gt;
        gt = initialGravity * Math.exp(-alpha * (t/iterations));
        return gt;
    }


    public Agent[] createAgents(Agent[] agents,int totalAgents){
        for(int i=0; i<totalAgents; i++){
            agents[i] = new Agent();
        }
        return agents;
    }


}
