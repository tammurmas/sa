/**
 * 
 * Code downloaded from: http://www.theprojectspot.com/tutorial-post/simulated-annealing-algorithm-for-beginners/6
 * Author: Lee Jacobson
 * 
 * Edited by: Urmas T.
 * 
 * Manages all the cities in our TSP
 */

package sa;

import java.util.ArrayList;

public class TourManager {

    private static ArrayList destinationCities = new ArrayList();

    /**
     * Adds a destination city
     * @param city 
     */
    public static void addCity(City city) {
        destinationCities.add(city);
    }
    
    /**
     * Returns a city on the given index
     * @param index
     * @return 
     */
    public static City getCity(int index){
        return (City)destinationCities.get(index);
    }
    
    /**
     * Get the number of cities still to be visited
     * @return 
     */
    public static int numberOfCities(){
        return destinationCities.size();
    }
    
}