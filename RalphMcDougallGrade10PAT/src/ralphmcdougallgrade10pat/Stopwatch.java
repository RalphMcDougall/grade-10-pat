/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ralphmcdougallgrade10pat;

/**
 *
 * @author Ralph 4/11/2016
 */
public class Stopwatch extends Thread {

    /**
     * This class is a thread for keeping track of the time that has passed
     * for the game.
     */
    
    private boolean EXIT = false;
    
    private long START_TIME = 0;
    private long CURRENT_TIME = 0;
    private long LAST_UPDATE = 0;
    
    private GUIQuestion GUIQ;
    
    public Stopwatch(GUIQuestion _GUIQ)
    {
        START_TIME = System.currentTimeMillis(); // Get the starting time
        LAST_UPDATE = START_TIME; // Set the last update to be now
        GUIQ = _GUIQ; // The GUIQuestion that this is keeping time for
        System.out.println("Stopwatch thread initialised.");
    }
    
    @Override
    public void run() {
        System.out.println("Stopwatch starting.");
        System.out.println("");
        while (!EXIT) {
            CURRENT_TIME = System.currentTimeMillis(); // Update current time
            
            GUIQ.setStopwatchTime((int) (CURRENT_TIME - START_TIME)); // Update the GUIQuestion stopwatch time
            if (CURRENT_TIME - LAST_UPDATE >= 1000) // A second has passed
            {
                LAST_UPDATE = CURRENT_TIME; // Set the last update to the current time
                updateStopwatchLabel(); // Update the GUIQuestion stopwatch label
            }

        }
        System.out.println("Stopwatch stopped.");
    }
    public void close()
    {
        // The program can now exit from the loop and terminate properly
        EXIT = true;
    }
    private void updateStopwatchLabel()
    {
        // Update the label of the stopwatch in the GUIQuestion
        GUIQ.updateStopwatchDisplay();
    }
    
}
