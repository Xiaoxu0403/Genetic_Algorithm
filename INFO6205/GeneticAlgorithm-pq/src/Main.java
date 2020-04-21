
public class Main {
    public static void main(String[] args) {
        Algorithm geneticAlgorithm = new Algorithm(0.001, 0.5, 2);         //initialize the mutation rate, crossover rate and tournament size
        Population population = new Population(1000,true);                 // initialize the population size
        
        int generationsCount = 1;
        final int maxGenerations = 2000;                                  //set the maximal generation
        while (geneticAlgorithm.isTerminationConditionMet(generationsCount,maxGenerations)==false){     // termination condition                           
           Population newPopulation = Algorithm.evolve(population);
            if (generationsCount % 10 == 0) {                                                                   
                System.out.println("The " + generationsCount + "'s generation:");                      //print the fitness
                System.out.println(newPopulation);  
            }
            population = newPopulation;
            generationsCount++;            
        }
    }  
        
}
