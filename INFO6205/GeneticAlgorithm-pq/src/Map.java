
import java.awt.Point;



/* The map has 100 grids, and the grid could be empty,a bomb or a bean.
   If the grid has a bean and then eat the bean, the sorce will add 10 
   points. If it eat a bomb, the sorce will minus -3 points. Due to the
   maximal number of beans are 40, so the ideal value is 400 points.However,
   due to the existence of bombs, it is very difficult to get this point. In 
   our tests, the max points are almost just a little over 300 points*/

public class Map implements Cloneable{

    private int x = -1;
    private int y = -1;
    private int total = -1;
    private byte[][] mapGrid = null;
    private static final int stateNum = 3;           //bomb,beans and empty

    public Map(int x, int y) {
        this.x = x;  
        this.y = y;
        mapGrid = new byte[x][y];
        total = x * y;
    }
    
    

    public void addElements(int num) {              // add items to elements
        //check num 
        if (num > total) {
            num = total;
        }
        int count[] = {50, 40, 10};                 // take empty as an element, and set its number to 50
        for (int i = 0; i < num; i++) {
        	//int rand = (int) Math.floor((Math.random() * total));
        	int xp = i/ y;
        	int yp = i % y;
        
           int state = (int) Math.floor((Math.random() * stateNum)); //generate the number between 0  and (stateNUm - 1)        
            if(count[state] > 0) {
            	switch(state) {
            	case 0: mapGrid[xp][yp] = 0; break; //empty  50
            	case 1: mapGrid[xp][yp] = 1; break; //bean 40
            	case 2: mapGrid[xp][yp] = 2; break; //bomb  10
            
            	}
            	count[state]--;
            } else {
            	i--;
            	continue;
            	}
            }
    }

    public boolean isInMap(int x, int y) {           // judge the location of the pacman, mainly for judging whether it rushes the wall
        if (x < 0 || x >= this.x) return false;
        if (y < 0 || y >= this.y) return false;     
        return true;
    }
    
    /*a list of actions that pacman can perform*/
    public boolean hasBean(int x, int y) {               
        boolean ret = mapGrid[x][y] == 1 ? true : false;
        return ret;
    }

    public boolean eatBean(int x, int y) {
        if(hasBean(x, y)) {
            mapGrid[x][y] = 0;
            return true;
        }
        return false;
    }
    public boolean hasBomb(int x, int y) {
        boolean ret = mapGrid[x][y] == 2 ? true : false;
        return ret;
    }

    public boolean eatBomb(int x, int y) {
        if(hasBomb(x, y)) {
            mapGrid[x][y] = 0;
            return true;
        }
        return false;
    }
    
    /*initialize the starting point*/
    public Point getStartPoint() {              
        int x = (int) Math.floor(Math.random() * this.x);
        int y = (int) Math.floor(Math.random() * this.y);       
        return new Point(x, y);
    }
   /*return a value for each action for different states*/
    public State getState(Point p) {        
        byte middle = stateOfPoint(p);
        byte up = stateOfPoint(new Point(p.x, p.y - 1));
        byte right = stateOfPoint(new Point(p.x + 1, p.y));
        byte down = stateOfPoint(new Point(p.x, p.y + 1));
        byte left = stateOfPoint(new Point(p.x - 1, p.y));
        return new State(middle, up, right, down, left);
    }

     /* set each state a value */
    private byte stateOfPoint(Point p) {
        byte val;
        if (!isInMap(p.x, p.y)) val = 0;            
        else if (mapGrid[p.x][p.y] == 0) val =  3;
        else if (mapGrid[p.x][p.y]==1)  val = 1;
        else val = 2;

        return val;
    }
    
    @Override
    public Map clone() throws CloneNotSupportedException {
        Map m = (Map) super.clone();
        byte[][] mapGrid = new byte[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                mapGrid[i][j] = this.mapGrid[i][j];
            }
        }
        m.mapGrid = mapGrid;
        return m;       
    }


    public void print() {                      //print the map
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                System.out.print(mapGrid[j][i]);
            }
            System.out.println();
        }
    }



}

