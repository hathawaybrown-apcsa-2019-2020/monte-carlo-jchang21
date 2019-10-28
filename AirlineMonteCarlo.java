/**
 * AirlineMonteCarlo takes inputs of tickets sold, number of seats, and no show probability. It runs a certain numer of simulations and
 * calculates average seats filled and number of times overbooked
 *
 * @jessicachang
 * @2019-10-24
 */
public class AirlineMonteCarlo
{
    private final int NUM_SIMULATIONS = 100000; // number of times the simulation is run
    private int ticketsSold; // number of tickets sold
    private int seats; // number of seats available on airplane
    private double noShowProbability; // probability for each passenger that they won't show up
    private double averageSeatsFilled; // average number of seats filled on a flight
    private int overBooked = 0; // number of times the airline is overbooked
    private double percentage;
     
     /**
     * Constructor for objects of class AirlineMonteCarlo
     */
    public AirlineMonteCarlo(int tickets, int seats, double probability)
    {
        this.ticketsSold = tickets;
        this.seats = seats;
        this.noShowProbability = probability;
    }

    
    /**
     * Simulates one flight
     * @param numShow   number of people who show up
     * @return number of people show up
     */
    public int simulateOneFlight()
     {
       int numShow = 0;
       for (int i = 1; i <= ticketsSold; i++)
        {
            if (Math.random() > noShowProbability)
            {
                numShow = numShow + 1;
            }
        }   
       return numShow;
    }
    
    /**
     * Runs the simulateOneFlight methoed for a certain number of simulations
     * Calculates number of times flights are overbooked
     * Calculates the average number of seats filled on a flight
     * Calculates the percentage of flights that are overbooked
     * @param runningTotal  total number of seats filled over all the simulations
     */
    public void runSimulation()
    {
        int runningTotal = 0;
        for (int i = 1; i <= NUM_SIMULATIONS; i++)
        {
            int numShow = simulateOneFlight();
            runningTotal = runningTotal + numShow;
            if (numShow > seats)
            {
                overBooked = overBooked + 1;
            }
        }
        averageSeatsFilled = (double)(runningTotal)/NUM_SIMULATIONS;
        percentage = (double)(overBooked) * 100 / NUM_SIMULATIONS;
    }
    
    /**
     * Reports results of all the simulations: average seats filled, number of times overbooked, percentage of flights overbooked
     */
    public void reportResults()
    {
        System.out.println("Simulation: " + ticketsSold + " sold for " + seats + " seats; no-show probability = " + noShowProbability);
        System.out.println("Based on " + NUM_SIMULATIONS + " simulations:");
        System.out.println("Average seats filled: " + averageSeatsFilled);
        System.out.println("Number of times overbooked: " + overBooked + " (" + percentage + " percent)");
    }
    
    /**
     *  Runs the code based on the three inputs
     */
    public static void main (String[] args)
    {
        AirlineMonteCarlo mySim = new AirlineMonteCarlo(148,140,0.08);
        mySim.runSimulation();
        mySim.reportResults();
    }
}
