

public class Algorithm {
	
	   private static   int tournamentSize;
	   private static  double uniformRate ;
	   private static  double mutationRate ;
	   
		public Algorithm( double mutationRate, double uniformRate, int tournamentSize) {
	   
			this.mutationRate = mutationRate;
			this.uniformRate = uniformRate;
			this.tournamentSize = tournamentSize;
		} 
	   
	   public static Population evolve(Population population)           // evolve the population by crossover and  mutation
		{
			return mutatePopulation(crossoverPopulation(population));
		}
	   
	   public boolean isTerminationConditionMet(int generationsCount, int maxGenerations) {    //termination condition
			return (generationsCount > maxGenerations);
		}

	   
	   public static  Population crossoverPopulation(Population population) {             //crossover the population
		   Population crossoverPopulation = new Population(population.size(),true);
		   for(int i=0;i<population.size();i++) {                                  
			   Individual indiv1 = tournamentSelection(population);                      // select parents by tournamentSelection
			   Individual indiv2 = tournamentSelection(population);
			   Individual newIndiv = crossover(indiv1,indiv2);
			   crossoverPopulation.setIndividual(i, newIndiv);                          //save the individual to population
		   }
		   
		   return crossoverPopulation;
		}
	   public static  Population mutatePopulation(Population population) {            // mutate the population
		   Population mutatePopulation = new Population(population.size(),true);
		   mutatePopulation = population;
		   for(int i=0;i<population.size();i++) {
			     mutate(mutatePopulation.getIndividual(i));
		   }
		         return mutatePopulation;
	   }


	   public static Individual tournamentSelection(Population pop) {              // tournament seletion
	    
	       Population tournamentPop = new Population(tournamentSize,true);
	       // tournamentPop.shuffle();                                               //  shuffle the population for randomly selecting individuals from it
	     
	       for (int i = 0; i < tournamentSize; i++) {
	           int randomId = (int) (Math.random() * pop.size());
	           tournamentPop.setIndividual(i, pop.getIndividual(randomId));
	       }
	      
	       Individual fittest = tournamentPop.getFittest(0);                      // return the best solution from tournament population
	       return fittest;
	   }


	   public static Individual crossover(Individual indiv1, Individual indiv2) {   // uniform crossover
	       Individual newIndiv = new Individual();
	     
	       for (int i = 0; i < indiv1.getLength(); i++) {
	           if (Math.random() <= uniformRate) {
	               newIndiv.setGene(i, indiv1.getGene(i));
	           } else {
	               newIndiv.setGene(i, indiv2.getGene(i));
	           }
	       }
	       return newIndiv;
	   }

	  
	   private static void mutate(Individual indiv) {                              // mutate genes for corresponding position 
	       for (int i = 0; i < indiv.getLength(); i++) {
	           if (Math.random() <= mutationRate) {
	               byte gene = (byte) Math.floor(Math.random() * indiv.getActionNum());    // the range of gene types(from 0 to 7)
	               indiv.setGene(i, gene);
	           }
	       }
	   }

	}

