

import static org.junit.Assert.assertEquals;

@SuppressWarnings("ALL")
public class Test {

	@org.junit.jupiter.api.Test
    void generateGenesntest(){  
    		byte[] genes = {0,1,2,3,4,5,6};
    		Individual testgenes = new Individual();
    		testgenes.setGene(0, genes[0]);
    		testgenes.setGene(1, genes[1]);
    		testgenes.setGene(2, genes[2]);
    		testgenes.setGene(3, genes[3]);
    		testgenes.setGene(4, genes[4]);
    		testgenes.setGene(5, genes[5]);
    		testgenes.setGene(6, genes[6]);
        assertEquals(testgenes.getGene(0), 0);
		assertEquals(testgenes.getGene(1), 1);
		assertEquals(testgenes.getGene(2), 2);
		assertEquals(testgenes.getGene(3), 3);
		assertEquals(testgenes.getGene(4), 4);
		assertEquals(testgenes.getGene(5), 5);
		assertEquals(testgenes.getGene(6), 6);
	}
	
	@org.junit.jupiter.api.Test
	 void Populationtest(){  
		Population testpopulation = new Population(8, true);
		assertEquals(testpopulation.size(), 8);
		assertEquals(testpopulation.getFittest(0),testpopulation.getIndividual(0));
		assertEquals(testpopulation.getFittest(1),testpopulation.getIndividual(1));
		assertEquals(testpopulation.getFittest(2),testpopulation.getIndividual(2));
		assertEquals(testpopulation.getFittest(3),testpopulation.getIndividual(3));
		assertEquals(testpopulation.getFittest(4),testpopulation.getIndividual(4));
		assertEquals(testpopulation.getFittest(5),testpopulation.getIndividual(5));
		assertEquals(testpopulation.getFittest(6),testpopulation.getIndividual(6));
		assertEquals(testpopulation.getFittest(7),testpopulation.getIndividual(7));	
	}
	
	@org.junit.jupiter.api.Test
	void tournamentSelectiontest() {
		Algorithm algor = new Algorithm(0,0,2);
		Population population = new Population(2,true);
		assertEquals(Algorithm.tournamentSelection(population),population.getFittest(0));
	}
	
	@org.junit.jupiter.api.Test
	void crossovertest() {
		Individual indiv1 = new Individual();
		Individual indiv2 = new Individual();
		Algorithm algor1 = new Algorithm(0,-1,2);
		Population population1 = new Population(2,true);
		for(int i =0; i < indiv2.getLength(); i++) {
			assertEquals(Algorithm.crossover(indiv1, indiv2).getGene(i),indiv2.getGene(i));
		}
		Algorithm algor2 = new Algorithm(0,1,2);
		Population population2 = new Population(2,true);
		for(int i =0; i < indiv2.getLength(); i++) {
			assertEquals(Algorithm.crossover(indiv1, indiv2).getGene(i),indiv1.getGene(i));
		}
	}
	
	
	@org.junit.jupiter.api.Test
	void mutatePopulationtetest() { 
	    Algorithm algor3 = new Algorithm(-1,0,0);
		Population population3 = new Population(10,true);
		
		for(int i=0;i<population3.size();i++) {
			assertEquals(algor3.mutatePopulation(population3).getIndividual(i),population3.getIndividual(i));}
		
	}
		

}
    

    

   

   


