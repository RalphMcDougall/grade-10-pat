/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ralphmcdougallgrade10pat;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Ralph McDougall 4/11/2016
 */
public class RecordsFileHandler {
    /**
     * This class handles all of the file I/O.
     * It automatically adds times into the correct place in the list.
     * It allows you to retrieve the name and time of the person at any position
     */
    
    private String FILE_NAME = "src/records/"; // Default file extension

    private int NUM_RECORDS = 8; // Number of records saved
    private int[] TIMES = {0, 0, 0, 0, 0, 0, 0, 0}; // Array of times (milliseconds)
    private String[] NAMES = {"", "", "", "", "", "", "", ""}; // Array of names

    public RecordsFileHandler(int difficulty, int operator, int target) throws IOException {
        // Construct the FILE_NAME based on the operator, difficulty and target
        FILE_NAME += operator; // 0 = Addition
        FILE_NAME += " " + target; // 10 = 10
        FILE_NAME += " " + difficulty; // 1 = Easy
        FILE_NAME += ".txt";

        System.out.println("FILE NAME: " + this.FILE_NAME);
        System.out.println("");

        // Get the data from the file
        this.getData();
    }

    public void getData() throws FileNotFoundException, IOException {
        // Gets the data from the file
        
        // Initialisation of the string containing all data in the file
        String allData = "";

        // Open file for input
        BufferedReader br = new BufferedReader(new FileReader(this.FILE_NAME));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine(); // Read the first line

            // While there are still lines to read
            while (line != null) {
                sb.append(line); // Add each line onto the StringBuilder
                sb.append("\n"); // Newline
                line = br.readLine(); // Get the next line
            }
            allData = sb.toString(); // The data gets set to the text from the file
        } finally {
            br.close();
        }
        
        int last_pos = 0; // Lower bound of the part of the string that needs to be split next
        int num_found = 0; // The number of entries found

        String[] info = {"", "", "", "", "", "", "", ""}; // The strings containing the time and name of the people

        // Look through every character of the data
        for (int i = 0; i < allData.length() && num_found != 8; ++i) {
            if (allData.charAt(i) == '\n') {
                // If a newline was found add the section from there to the last newline into the info
                info[num_found] = allData.substring(last_pos, i);
                num_found++; // One more entry found
                last_pos = i + 1; // The new cut-off position
            }
        }

        // Get the data for each person
        for (int i = 0; i < 8; ++i) {
            int breakPos = 0;
            // Go through each character of the info for each person
            for (int j = 0; j < info[i].length(); ++j) {
                if (info[i].charAt(j) == ' ') {
                    // If a SPACE was found, we have found where the time will be
                    breakPos = j;
                    break;
                }
            }
            // The time is the string upto the first SPACE
            TIMES[i] = Integer.parseInt(info[i].substring(0, breakPos));
            NAMES[i] = info[i].substring(breakPos + 1); // The name is the rest of the String
        }

    }

    public int getTime(int pos) {
        return TIMES[pos]; // Return the time in position pos
    }

    public String getName(int pos) {
        return NAMES[pos]; // Return the name in position pos
    }

    public void insert(int time, String name) {
        // Try to insert time into the list

        boolean inserted = false; // The time has not been inserted yet

        int lastTime = 0; // The previous time
        String lastName = ""; // The previous name

        for (int i = 0; i < 8; ++i) {
            if (inserted) {
                // If the time has already been inserted shift the current name and time down
                int tempT = TIMES[i];
                String tempN = NAMES[i];
                TIMES[i] = lastTime;
                NAMES[i] = lastName;
                lastTime = tempT;
                lastName = tempN;

            } else {
                lastTime = TIMES[i]; // Update the last name and time
                lastName = NAMES[i];
                if (time <= TIMES[i])
                {
                    // The time must be inserted here
                    inserted = true;
                    TIMES[i] = time; // Set the time and name to the desired time and name
                    NAMES[i] = name;
                }
            }
        }
    }
    
    public void writeData() throws FileNotFoundException
    {
        // Write the names and times to the file again
        
        // Initialise the String containing all of the data
        String allData = "";
        for (int i = 0; i < 8; ++i)
        {
            allData += TIMES[i]; // The time comes first
            allData += " "; // Space indicates the end of the time
            allData += NAMES[i]; // The name is the rest of the line
            allData += "\n"; // Newline
        }
        
        System.out.println("Written Data: \n" + allData + "\n");
        
        // Open the file for writing
        PrintWriter out = new PrintWriter(this.FILE_NAME);
        out.write(allData); //  Write the data to the file
        out.close(); // Close the file
    }

}
