

public class Individual {
	
     /* Pacman can observe 5 positions:up,down,left,right and current grids,
      and each grid has four states: empty,wall, bean and bomb, so it can**
      have 4^5 = 1024 states  ********************************************/
  
    private static int geneLength = 1024; 
    /* Pacman has eight actions:  get up, get down, get right, get left,
      keep still, randomly move, eat bombs and eat beans **************/
 
    private static byte actionNum = 7;
    private byte genes[] = null;
    private int fitness = Integer.MIN_VALUE;

    public Individual() {
        genes = new byte[geneLength];       
    }

    public void generateGenes(){                  //generate individual genes randomly for each gene-position
        for (int i = 0; i < geneLength; i++) {
            byte gene = (byte) Math.floor(Math.random() * actionNum);
            genes[i] = gene;
        }
    }

    public int getFitness() {                   // calculate the fitness for each individual
        if (fitness == Integer.MIN_VALUE) {
            fitness = FitnessCalc.getFitnessPall(this);
        }
        return fitness;
    }


    public int getLength() {                     //return geneLength
        return geneLength;
    }
    
    public int getActionNum() {                 // return actionNum
    	return actionNum;
    }

    public byte getGene(int index) {            //return gene value for corresponding index
        return genes[index];
    }

    public void setGene(int index, byte gene) {  // set genes to byte gene array
        this.genes[index] = gene;
        fitness = Integer.MIN_VALUE;
    }

    
    public byte getActionCode(State state) {       // return specific genes for corresponding states 
        int stateCode = (int) (state.getMiddle() * Math.pow(3, 4) + state.getUp() * Math.pow(3, 3) + state.getRight() * Math.pow(3, 2) + state.getDown() * 3 + state.getLeft());
        return genes[stateCode];
    }

    @Override
    public String toString() {                    // save genes to a string buffer
        StringBuffer bf = new StringBuffer();
        for (int i = 0; i < geneLength; i++) {
            bf.append(genes[i]);
        }
        return bf.toString();
    }


}



