/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tvseriesapp;

/**
 *
 * @author Baackup
 */

import javax.swing.JOptionPane;

public class TVSeriesApp {

    /**
     * @param args the command line arguments
     */
   
    private static SeriesManager manager = new SeriesManager();
    
    public static void main(String[] args) {
        // Welcome message
        System.out.println("Welcome to TV Series Management System!");
        
        // Main application loop
        boolean running = true;
        
        while (running) {
            // Display menu
            String menu = "TV SERIES MANAGEMENT SYSTEM\n\n" +
                         "1. Capture a new TV Series\n" +
                         "2. Search for a TV Series\n" +
                         "3. Update TV Series details\n" +
                         "4. Delete a TV Series\n" +
                         "5. Display TV Series Report\n" +
                         "6. Exit Application\n\n" +
                         "Please select an option (1-6):";
            
            String choice = JOptionPane.showInputDialog(menu);
            
            // Handle user choice
            if (choice == null) {
                // User clicked cancel or X
                running = false;
            } else {
                switch (choice) {
                    case "1":
                        manager.captureSeries();
                        break;
                    case "2":
                        manager.searchSeries();
                        break;
                    case "3":
                        manager.updateSeriesUI();
                        break;
                    case "4":
                        manager.deleteSeriesUI();
                        break;
                    case "5":
                        manager.generateSeriesReport();
                        break;
                    case "6":
                        running = false;
                        JOptionPane.showMessageDialog(null, "Thank you for using TV Series Management System!");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid option! Please select 1-6.");
                        break;
                }
            }
        }
        
        // Exit the application
        System.exit(0);
    } 
        
        
    }
    

