
import java.awt.Point;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;



public class FitnessCalc {
	
	  /*Each individual can walk 200 steps.For each step,it can choose an action to perform.
    There is a counter for the fitness. If it choose to eat beans, the counter adds 10 points; if 
    it choose to eat bombs, the counter will minus points; if it rushes the wall, the counter will
    minus 5 points; and if it eat empty, the counter will minus 1 point  */
 
    private static int DefaultSimTimes = 1000;              // get the individual fitness by looping 1000 times
   
    private static int simSteps = 200;
    private static int cores = 8;

    public static int getFitness(Individual ind) {           // get the individual fitness by looping 1000 times
        return getFitness(ind, DefaultSimTimes);
    }

    public static int getFitness(Individual ind, int simTimes) {     //override the get fitness method
        int fitness = 0;        
        MapMgr mgr = MapMgr.getInstance();                            //  call the constructor function for map controller and generate 100 kinds of maps
        for (int i = 0; i < simTimes; i++) {        
            Map map = mgr.getMap(i);                                  // assign each simulation with a different map
            Point point = map.getStartPoint();                        // initial the starting points of pacman
            for (int j = 0; j < simSteps; j++) {
                State state = map.getState(point);                    // get the state of current point
                byte actionCode = ind.getActionCode(state);           // get the current gene for current state
                fitness += action(point, map, actionCode);            // calculate the fitness for current steps and sum it
                //map.print();
                //System.out.println("===============");
            }                               
        }       
        return fitness / simTimes;
    }

    public static int getFitnessPall(Individual ind) {                // parallel calculating the fitness
        int fitness = 0;        
        if (DefaultSimTimes < 100) {                                  //  divide the DefaultSimTimes by recursion until it is less than 100
            fitness = getFitness(ind);
        } else {                            
            @SuppressWarnings("unchecked")
			FutureTask<Integer>[] tasks = new FutureTask[cores];      //  assign each thread with a task       
            for (int i = 0; i < cores; i++) {
                FitnessPall pall = null;
                if (i == 0) {                                         //Continually divide the DefaultTimes
                    pall = new FitnessPall(ind, (DefaultSimTimes / cores) + DefaultSimTimes % cores);
                } else {
                    pall = new FitnessPall(ind, DefaultSimTimes / cores);   
                }               
                tasks[i] = new FutureTask<Integer>(pall); 
               // ExecutorService executor = Executors.newCachedThreadPool();
               // executor.submit(tasks[i]);
                //executor.shutdown();
                Thread thread = new Thread(tasks[i]);                  // start the thread
                thread.start();
            }       
            for (int i = 0; i < cores; i++) {
                try {
                    fitness += tasks[i].get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
            fitness = fitness / cores;
        }
        return fitness;
    }


    /* set the action with corresponding values, and judging the next actions*/
    private static int action(Point point, Map map, int actionCode) {
        int sorce = 0;
        switch (actionCode) {
        case 0:
            if (map.isInMap(point.x, point.y - 1)) {
                sorce = 0;
                point.y = point.y - 1;
            } else {
                sorce = -5;
            }           
            break;
        case 1:
            if (map.isInMap(point.x - 1, point.y)) {
                sorce = 0;
                point.x = point.x - 1;
            } else {
                sorce = -5;
            }
            break;
        case 2:
            if (map.isInMap(point.x, point.y + 1)) {
                sorce = 0;
                point.y = point.y + 1;
            } else {
                sorce = -5;
            }
            break;
        case 3: 
            if (map.isInMap(point.x + 1, point.y)) {
                sorce = 0;
                point.x = point.x + 1;
            } else {
                sorce = -5;
            }
            break;
        case 4:
            int randomCode = (int) Math.floor(Math.random() * 4);
            sorce = action(point, map, randomCode);         
            break;
            
        case 5:
            if (map.eatBean(point.x, point.y)) {
                sorce = 10;             
            } else if (map.eatBomb(point.x, point.y)){
                sorce = -3;
            } else {
            	sorce = -1;
            }
            break;
        case 6: 
            sorce = 0;
            break;
        }
        return sorce;
    }


}

class FitnessPall implements Callable<Integer> {
    private int simTimes;
    private Individual ind;
    public FitnessPall(Individual ind, int simTimes) {    //set the simTimes to the current DefaultSimTimes
        this.ind = ind;
        this.simTimes = simTimes;       
    }

    @Override
    public Integer call() throws Exception {
        return FitnessCalc.getFitness(ind, simTimes);       
    }   
}
