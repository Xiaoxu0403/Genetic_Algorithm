import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;


public class Population {

    private Individual[] individuals;                                

    public Population(int size, boolean activation) {
        individuals = new Individual[size];
        if (activation) {
            for (int i = 0; i < individuals.length; i++) {                      // generate genes for each individual
                Individual ind = new Individual();
                ind.generateGenes();
                individuals[i] = ind;
            }
        }
    }

    public void setIndividual(int index, Individual ind) {                    // set genes for individual
        individuals[index] = ind;
    }

    public Individual getIndividual(int index) {                             // get genes for corresponding individual
        return individuals[index];
    }

    public Individual getFittest(int index) {              //getFittest(0) is the strongest, getFittest(length-1) is the least
		PriorityQueue pq = new PriorityQueue<Individual>(new Comparator<Individual>() {
			@Override
			public int compare(Individual o1, Individual o2) {
				if(o1.getFitness()>o2.getFitness()) return -1;
				else  if(o1.getFitness()<o2.getFitness())return 1;
				else return 0; }
		});
		for(int i = 0; i < individuals.length; i++) {
			pq.add(individuals[i]);
		}
		for(int i = 0; i < individuals.length; i++) {
			individuals[i] = (Individual) pq.poll();
		}
		return individuals[index];                             // return the individual's fitness
	}
	
    public double getAverageFitness() {
		  double sum = 0;
	      for (int i = 0; i < size(); i++) {
	            sum += individuals[i].getFitness();
	      }
	      return sum/size();                                              // return the average fitness
	 }    

    public int size() {                                                
        return individuals.length;                                      // return individual's length
    }
    
	public void shuffle() {                                            // shuffle the population
		Random rand = new Random();
		for(int i=0;i<size();i++) {
			int index = rand.nextInt(i + 1); 
			Individual ind = individuals[index];
			individuals[index] = individuals[i];
			individuals[i] = ind;
		}
		
	}

    @Override
	 public String toString(){
        StringBuffer bf = new StringBuffer();
        bf.append("PopSize: " + size() + "\n");
        bf.append("Maximal Fitnenss: " + getFittest(0).getFitness() + "\n");
        bf.append("Minimal Fitness: " + getFittest(size()-1).getFitness() + "\n");
        bf.append("Average Fitness: " + getAverageFitness() + "\n");        
        return bf.toString();
 }

}
