/**
 * 
 * Code downloaded from: http://www.theprojectspot.com/tutorial-post/simulated-annealing-algorithm-for-beginners/6
 * Author: Lee Jacobson
 * 
 * Edited by: Urmas T.
 * 
 * Defines a city object based on its coordinates and index number
 */

package sa;

public class City {
    int x;
    int y;
    int i;
    
    /**
     * City constructor using its coordinates and index number
     * 
     * @param x
     * @param y
     * @param i 
     */
    public City(int x, int y, int i){
        this.x = x;
        this.y = y;
        this.i = i;
    }
    
    /**
     * Returns city's x coordinate
     * @return 
     */
    public int getX(){
        return this.x;
    }
    
    /**
     * Returns city's y coordinate
     * @return 
     */
    public int getY(){
        return this.y;
    }
    
    /**
     * Calculates distance between the city object and the given city
     * @param city
     * @return 
     */
    public double distanceTo(City city){
        int xDistance = Math.abs(getX() - city.getX());
        int yDistance = Math.abs(getY() - city.getY());
        double distance = Math.sqrt( (xDistance*xDistance) + (yDistance*yDistance) );
        
        return distance;
    }
    
    @Override
    public String toString(){
        return "(p"+i+")";
    }
}