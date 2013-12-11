/**
 * 
 * Code downloaded from: http://www.theprojectspot.com/tutorial-post/simulated-annealing-algorithm-for-beginners/6
 * Author: Lee Jacobson
 * 
 * Edited by: Urmas T.
 * 
 * Calculating optimal solution for the TSP using the simulated annealing algorithm
 */

package sa;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SimulatedAnnealing {

    private static final String fileName = "TSP_100.txt";

    public static void main(String[] args) throws IOException {
        
        BufferedReader inputStream = null;

        String line;
        
        try {
            inputStream = new BufferedReader(new FileReader(fileName));
            
            int i = 0;
            
            while( (line = inputStream.readLine()) != null)
            {
                //skip first line
                if(i > 0)
                {
                    String[] lineParts = line.split("\\s+");//any number of whitespace characters as delimiter
                
                    int x = Integer.parseInt(lineParts[0]);
                    int y = Integer.parseInt(lineParts[1]);
                    
                    City city = new City(x,y, i);

                    TourManager.addCity(city);
                            
                }
                i++;
            }
        }
        catch (IOException e){}
        finally{
             if (inputStream != null) {
                inputStream.close();
            }
        }
        
        // Set initial temp
        int tempVal = 1000;

        while (tempVal <= 1000000000)
        {
            double temp = tempVal;
            // Cooling rate
            double coolingRate = 1/temp;

            // Initialize intial solution
            Tour currentSolution = new Tour();
            currentSolution.generateIndividual();

            System.out.println("Initial solution distance: " + currentSolution.getDistance());

            // Set as current best
            Tour best = new Tour(currentSolution.getTour());

            // Loop until system has cooled
            while (temp > 1) {
                // Create new neighbour tour
                Tour newSolution = new Tour(currentSolution.getTour());

                // Get a random positions in the tour
                int tourPos1 = (int) (newSolution.tourSize() * Math.random());
                int tourPos2 = (int) (newSolution.tourSize() * Math.random());

                // Get the cities at selected positions in the tour
                City citySwap1 = newSolution.getCity(tourPos1);
                City citySwap2 = newSolution.getCity(tourPos2);

                // Swap them
                newSolution.setCity(tourPos2, citySwap1);
                newSolution.setCity(tourPos1, citySwap2);

                // Get energy of solutions
                int currentEngery = currentSolution.getDistance();
                int neighbourEngery = newSolution.getDistance();

                // Decide if we should accept the neighbour
                if (acceptanceProbability(currentEngery, neighbourEngery, temp) > Math.random()) {
                    currentSolution = new Tour(newSolution.getTour());
                }

                // Keep track of the best solution found
                if (currentSolution.getDistance() < best.getDistance()) {
                    best = new Tour(currentSolution.getTour());
                }

                // Cool system
                temp *= 1-coolingRate;
            }

            System.out.println("Final solution distance: " + best.getDistance());
            System.out.println("Starting temperatur: "+tempVal);
            System.out.println("Tour: \n" + best);
            
            tempVal *= 10;
        }
    }
    
    /**
     * Function to determine whether or not accept the newly generated solution
     * @param engery
     * @param newEngery
     * @param temperature
     * @return 
     */
    public static double acceptanceProbability(int engery, int newEngery, double temperature) {
        // If the new solution is better, accept it
        if (newEngery < engery) {
            return 1.0;
        }
        // If the new solution is worse, calculate an acceptance probability
        return Math.exp((engery - newEngery) / temperature);
    }
}