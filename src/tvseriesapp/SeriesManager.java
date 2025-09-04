/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tvseriesapp;

/**
 *
 * @author Baackup
 */


import javax.swing.JOptionPane;
import java.util.ArrayList;


public class SeriesManager {
    // ArrayList to store all series in memory
    private ArrayList<SeriesModel> seriesList;
    
    // Constructor
    public SeriesManager() {
        seriesList = new ArrayList<SeriesModel>();
    }
    // Method to capture a new TV series
    public void captureSeries() {
        try {
            // Get input from user using JOptionPane
            String id = JOptionPane.showInputDialog("Enter Series ID:");
            if (id == null || id.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Series ID cannot be empty!");
                return;
            }
            
            // Check if ID already exists
            if (searchSeriesById(id) != null) {
                JOptionPane.showMessageDialog(null, "Series ID already exists!");
                return;
            }
            
            String name = JOptionPane.showInputDialog("Enter Series Name:");
            if (name == null || name.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Series Name cannot be empty!");
                return;
            }
            
            String age = JOptionPane.showInputDialog("Enter Series Age Restriction (e.g., PG, 13, 16, 18):");
            if (age == null || age.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Age restriction cannot be empty!");
                return;
            }
            
            String episodes = JOptionPane.showInputDialog("Enter Number of Episodes:");
            if (episodes == null || episodes.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Number of episodes cannot be empty!");
                return;
            }
            
            // Create new series and add to list
            SeriesModel newSeries = new SeriesModel(id, name, age, episodes);
            seriesList.add(newSeries);
            
            JOptionPane.showMessageDialog(null, "Series added successfully!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error capturing series: " + e.getMessage());
        }
    }
    
    // Method to search for a series by ID
    public SeriesModel searchSeriesById(String seriesId) {
        // Loop through the array list to find the series
        for (int i = 0; i < seriesList.size(); i++) {
            SeriesModel series = seriesList.get(i);
            if (series.getSeriesId().equals(seriesId)) {
                return series; // Series found
            }
        }
        return null; // Series not found
    }
    
    // Method to search and display series
    public void searchSeries() {
        try {
            String searchId = JOptionPane.showInputDialog("Enter Series ID to search:");
            if (searchId == null || searchId.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a valid Series ID!");
                return;
            }
            
            SeriesModel foundSeries = searchSeriesById(searchId);
            
            if (foundSeries != null) {
                String result = "Series Found:\n" +
                              "ID: " + foundSeries.getSeriesId() + "\n" +
                              "Name: " + foundSeries.getSeriesName() + "\n" +
                              "Age Restriction: " + foundSeries.getSeriesAge() + "\n" +
                              "Episodes: " + foundSeries.getSeriesNumberOfEpisodes();
                JOptionPane.showMessageDialog(null, result);
            } else {
                JOptionPane.showMessageDialog(null, "Series not found!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error searching series: " + e.getMessage());
        }
    }
    
    // Method to update an existing series
    public boolean updateSeries(String seriesId, String newName, String newAge, String newEpisodes) {
        SeriesModel seriesToUpdate = searchSeriesById(seriesId);
        
        if (seriesToUpdate != null) {
            // Update the series information
            seriesToUpdate.setSeriesName(newName);
            seriesToUpdate.setSeriesAge(newAge);
            seriesToUpdate.setSeriesNumberOfEpisodes(newEpisodes);
            return true; // Update successful
        }
        return false; // Series not found, update failed
    }
    
    // Method to update series (with user interface)
    public void updateSeriesUI() {
        try {
            String updateId = JOptionPane.showInputDialog("Enter Series ID to update:");
            if (updateId == null || updateId.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a valid Series ID!");
                return;
            }
            
            SeriesModel existingSeries = searchSeriesById(updateId);
            if (existingSeries == null) {
                JOptionPane.showMessageDialog(null, "Series not found!");
                return;
            }
            
            // Show current information
            String currentInfo = "Current Series Information:\n" +
                               "Name: " + existingSeries.getSeriesName() + "\n" +
                               "Age: " + existingSeries.getSeriesAge() + "\n" +
                               "Episodes: " + existingSeries.getSeriesNumberOfEpisodes();
            JOptionPane.showMessageDialog(null, currentInfo);
            
            // Get new information
            String newName = JOptionPane.showInputDialog("Enter new Series Name:");
            String newAge = JOptionPane.showInputDialog("Enter new Age Restriction:");
            String newEpisodes = JOptionPane.showInputDialog("Enter new Number of Episodes:");
            
            if (newName != null && newAge != null && newEpisodes != null) {
                boolean success = updateSeries(updateId, newName, newAge, newEpisodes);
                if (success) {
                    JOptionPane.showMessageDialog(null, "Series updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update series!");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error updating series: " + e.getMessage());
        }
    }
    
    // Method to delete a series
    public boolean deleteSeries(String seriesId) {
        // Loop through the list to find and remove the series
        for (int i = 0; i < seriesList.size(); i++) {
            SeriesModel series = seriesList.get(i);
            if (series.getSeriesId().equals(seriesId)) {
                seriesList.remove(i);
                return true; // Series deleted successfully
            }
        }
        return false; // Series not found
    }
    
    // Method to delete series (with user interface)
    public void deleteSeriesUI() {
        try {
            String deleteId = JOptionPane.showInputDialog("Enter Series ID to delete:");
            if (deleteId == null || deleteId.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a valid Series ID!");
                return;
            }
            
            boolean deleted = deleteSeries(deleteId);
            if (deleted) {
                JOptionPane.showMessageDialog(null, "Series deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Series not found!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error deleting series: " + e.getMessage());
        }
    }
    
    // Method to validate series age restriction
    public boolean validateSeriesAge(String ageRestriction) {
        // Valid age restrictions: PG, 13, 16, 18
        String[] validAges = {"PG", "13", "16", "18"};
        
        for (int i = 0; i < validAges.length; i++) {
            if (validAges[i].equals(ageRestriction)) {
                return true; // Valid age restriction
            }
        }
        return false; // Invalid age restriction
    }
    
    // Method to generate and display series report
    public void generateSeriesReport() {
        if (seriesList.size() == 0) {
            System.out.println("No series available to display.");
            JOptionPane.showMessageDialog(null, "No series available to display.");
            return;
        }
        
        // Create report header
        System.out.println("\n========== TV SERIES REPORT ==========");
        System.out.println("Total Series: " + seriesList.size());
        System.out.println("=====================================");
        
        // Display each series
        for (int i = 0; i < seriesList.size(); i++) {
            SeriesModel series = seriesList.get(i);
            System.out.println("Series " + (i + 1) + ":");
            System.out.println("ID: " + series.getSeriesId());
            System.out.println("Name: " + series.getSeriesName());
            System.out.println("Age Restriction: " + series.getSeriesAge());
            System.out.println("Episodes: " + series.getSeriesNumberOfEpisodes());
            System.out.println("-------------------------------------");
        }
        
        // Also show in JOptionPane for better display
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("TV SERIES REPORT\n");
        reportBuilder.append("Total Series: ").append(seriesList.size()).append("\n\n");
        
        for (int i = 0; i < seriesList.size(); i++) {
            SeriesModel series = seriesList.get(i);
            reportBuilder.append("Series ").append(i + 1).append(":\n");
            reportBuilder.append("ID: ").append(series.getSeriesId()).append("\n");
            reportBuilder.append("Name: ").append(series.getSeriesName()).append("\n");
            reportBuilder.append("Age: ").append(series.getSeriesAge()).append("\n");
            reportBuilder.append("Episodes: ").append(series.getSeriesNumberOfEpisodes()).append("\n\n");
        }
        
        JOptionPane.showMessageDialog(null, reportBuilder.toString());
    }
    
    // Getter for seriesList (needed for unit testing)
    public ArrayList<SeriesModel> getSeriesList() {
        return seriesList;
    } 
}
