/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package yarncalculatorapp;

/**
 *
 * @author Baackup
 */

import javax.swing.JOptionPane;
import java.util.ArrayList;

public class YarnCalculatorApp {
private ArrayList<YarnModel> calculations;
    
   
    public YarnCalculatorApp() {
        calculations = new ArrayList<>();
    }
    

    public void showMainMenu() {
        while (true) {
            String[] menuOptions = {
                "Calculate Yarn for New Project",
                "View Previous Calculations", 
                "Search Previous Calculation",
                "Update Previous Calculation",
                "Delete Previous Calculation",
                "Generate Report",
                "Exit"
            };
            
            String choice = (String) JOptionPane.showInputDialog(
                null,
                "=== YARN CALCULATOR FOR CROCHET PROJECTS ===\n\n" +
                "Select an option:",
                "Yarn Calculator Main Menu",
                JOptionPane.QUESTION_MESSAGE,
                null,
                menuOptions,
                menuOptions[0]
            );
            
            if (choice == null || choice.equals("Exit")) {
                JOptionPane.showMessageDialog(null, "Thank you for using the Yarn Calculator!\nHappy crocheting!");
                break;
            }
            
            switch (choice) {
                case "Calculate Yarn for New Project":
                    calculateNewProject();
                    break;
                case "View Previous Calculations":
                    viewPreviousCalculations();
                    break;
                case "Search Previous Calculation":
                    searchCalculation();
                    break;
                case "Update Previous Calculation":
                    updateCalculation();
                    break;
                case "Delete Previous Calculation":
                    deleteCalculation();
                    break;
                case "Generate Report":
                    generateCompleteReport();
                    break;
            }
        }
    }
    
    
    private void calculateNewProject() {
        YarnModel newProject = new YarnModel();
        
        
        String[] projectTypes = {"Scarf", "Sweater", "Blanket", "Hat"};
        String projectType = (String) JOptionPane.showInputDialog(
            null,
            "What type of project are you making?",
            "Project Type Selection",
            JOptionPane.QUESTION_MESSAGE,
            null,
            projectTypes,
            projectTypes[0]
        );
        
        if (projectType == null) return;
        newProject.setProjectType(projectType);
        
       
        String[] yarnTypes = {"Lace", "DK", "Worsted", "Chunky", "Super Chunky"};
        String yarnType = (String) JOptionPane.showInputDialog(
            null,
            "What type of yarn will you use?",
            "Yarn Type Selection",
            JOptionPane.QUESTION_MESSAGE,
            null,
            yarnTypes,
            yarnTypes[2] 
        );
        
        if (yarnType == null) return;
        newProject.setYarnType(yarnType);
        
        
        if (projectType.equals("Sweater")) {
            String[] sweaterSizes = {"Small", "Medium", "Large", "X-Large"};
            String size = (String) JOptionPane.showInputDialog(
                null,
                "What size sweater?",
                "Sweater Size Selection",
                JOptionPane.QUESTION_MESSAGE,
                null,
                sweaterSizes,
                sweaterSizes[1] // Default to Medium
            );
            if (size != null) {
                newProject.setSize(size);
            }
        } else if (projectType.equals("Blanket")) {
            String[] blanketSizes = {"Baby", "Lap", "Twin", "Queen"};
            String size = (String) JOptionPane.showInputDialog(
                null,
                "What size blanket?",
                "Blanket Size Selection",
                JOptionPane.QUESTION_MESSAGE,
                null,
                blanketSizes,
                blanketSizes[1] 
            );
            if (size != null) {
                newProject.setSize(size);
            }
        }
        
      
        newProject.calculateYarnRequirements();
        
       
        calculations.add(newProject);
        
        
        String result = newProject.generateReport();
        JOptionPane.showMessageDialog(
            null,
            result,
            "Yarn Calculation Results",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    
   
    private void viewPreviousCalculations() {
        if (calculations.isEmpty()) {
            JOptionPane.showMessageDialog(
                null,
                "No previous calculations found.\nCalculate a project first!",
                "No Data",
                JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }
        
        StringBuilder allCalcs = new StringBuilder();
        allCalcs.append("=== ALL PREVIOUS CALCULATIONS ===\n\n");
        
        for (int i = 0; i < calculations.size(); i++) {
            YarnModel calc = calculations.get(i);
            allCalcs.append("Calculation #").append(i + 1).append(":\n");
            allCalcs.append("Project: ").append(calc.getProjectType());
            if (!calc.getSize().isEmpty()) {
                allCalcs.append(" (").append(calc.getSize()).append(")");
            }
            allCalcs.append("\nYarn: ").append(calc.getYarnType());
            allCalcs.append("\nYardage: ").append(String.format("%.0f", calc.getYardageNeeded())).append(" yards");
            allCalcs.append("\nSkeins: ").append(calc.getSkeinCount()).append("\n\n");
        }
        
        JOptionPane.showMessageDialog(
            null,
            allCalcs.toString(),
            "Previous Calculations",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    
  
    private void searchCalculation() {
        if (calculations.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No calculations to search.");
            return;
        }
        
        String searchTerm = JOptionPane.showInputDialog(
            null,
            "Enter project type to search for:",
            "Search Calculations"
        );
        
        if (searchTerm == null || searchTerm.trim().isEmpty()) return;
        
        StringBuilder results = new StringBuilder();
        results.append("=== SEARCH RESULTS ===\n\n");
        boolean found = false;
        
        for (int i = 0; i < calculations.size(); i++) {
            YarnModel calc = calculations.get(i);
            if (calc.getProjectType().toLowerCase().contains(searchTerm.toLowerCase())) {
                results.append("Calculation #").append(i + 1).append(":\n");
                results.append(calc.generateReport()).append("\n\n");
                found = true;
            }
        }
        
        if (!found) {
            results.append("No calculations found for: ").append(searchTerm);
        }
        
        JOptionPane.showMessageDialog(
            null,
            results.toString(),
            "Search Results",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    
    private void updateCalculation() {
        if (calculations.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No calculations to update.");
            return;
        }
        
      
        String[] calcOptions = new String[calculations.size()];
        for (int i = 0; i < calculations.size(); i++) {
            YarnModel calc = calculations.get(i);
            calcOptions[i] = "Calc #" + (i + 1) + ": " + calc.getProjectType() + " (" + calc.getYarnType() + ")";
        }
        
        String choice = (String) JOptionPane.showInputDialog(
            null,
            "Which calculation would you like to update?",
            "Update Calculation",
            JOptionPane.QUESTION_MESSAGE,
            null,
            calcOptions,
            calcOptions[0]
        );
        
        if (choice == null) return;
                String numberPart = choice.substring(choice.indexOf("#") + 1, choice.indexOf(":"));
                int index = Integer.parseInt(numberPart.trim()) - 1;
                YarnModel calcToUpdate = calculations.get(index);
        
        
        String[] yarnTypes = {"Lace", "DK", "Worsted", "Chunky", "Super Chunky"};
        String newYarnType = (String) JOptionPane.showInputDialog(
            null,
            "Current yarn type: " + calcToUpdate.getYarnType() + "\nSelect new yarn type:",
            "Update Yarn Type",
            JOptionPane.QUESTION_MESSAGE,
            null,
            yarnTypes,
            calcToUpdate.getYarnType()
        );
        
        if (newYarnType != null) {
            calcToUpdate.setYarnType(newYarnType);
            calcToUpdate.calculateYarnRequirements();
            
            JOptionPane.showMessageDialog(
                null,
                "Calculation updated successfully!\n\n" + calcToUpdate.generateReport(),
                "Update Complete",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
    
  
    private void deleteCalculation() {
        if (calculations.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No calculations to delete.");
            return;
        }
        
       
        String[] calcOptions = new String[calculations.size()];
        for (int i = 0; i < calculations.size(); i++) {
            YarnModel calc = calculations.get(i);
            calcOptions[i] = "Calc #" + (i + 1) + ": " + calc.getProjectType() + " (" + calc.getYarnType() + ")";
        }
        
        String choice = (String) JOptionPane.showInputDialog(
            null,
            "Which calculation would you like to delete?",
            "Delete Calculation",
            JOptionPane.QUESTION_MESSAGE,
            null,
            calcOptions,
            calcOptions[0]
        );
        
        if (choice == null) return;
        
        
        int confirm = JOptionPane.showConfirmDialog(
            null,
            "Are you sure you want to delete this calculation?\n" + choice,
            "Confirm Deletion",
            JOptionPane.YES_NO_OPTION
        );
        
        if (confirm == JOptionPane.YES_OPTION) {
            
            String numberPart = choice.substring(choice.indexOf("#") + 1, choice.indexOf(":"));
            int index = Integer.parseInt(numberPart.trim()) - 1;
            
            calculations.remove(index);
            JOptionPane.showMessageDialog(null, "Calculation deleted successfully!");
        }
    }
    
    
    private void generateCompleteReport() {
        if (calculations.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No calculations to report.");
            return;
        }
        
        StringBuilder fullReport = new StringBuilder();
        fullReport.append("=== COMPLETE YARN CALCULATION REPORT ===\n\n");
        fullReport.append("Total Projects Calculated: ").append(calculations.size()).append("\n\n");
        

        int scarves = 0, sweaters = 0, blankets = 0, hats = 0;
        double totalYardage = 0;
        
        for (YarnModel calc : calculations) {
            switch (calc.getProjectType().toLowerCase()) {
                case "scarf": scarves++; break;
                case "sweater": sweaters++; break;
                case "blanket": blankets++; break;
                case "hat": hats++; break;
            }
            totalYardage += calc.getYardageNeeded();
        }
        
        fullReport.append("Project Summary:\n");
        fullReport.append("- Scarves: ").append(scarves).append("\n");
        fullReport.append("- Sweaters: ").append(sweaters).append("\n");
        fullReport.append("- Blankets: ").append(blankets).append("\n");
        fullReport.append("- Hats: ").append(hats).append("\n\n");
        fullReport.append("Total Estimated Yardage: ").append(String.format("%.0f", totalYardage)).append(" yards\n\n");
        
        
        fullReport.append("=== INDIVIDUAL CALCULATIONS ===\n\n");
        for (int i = 0; i < calculations.size(); i++) {
            fullReport.append("Calculation #").append(i + 1).append(":\n");
            fullReport.append(calculations.get(i).generateReport()).append("\n\n");
        }
        
        JOptionPane.showMessageDialog(
            null,
            fullReport.toString(),
            "Complete Report",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    
    public static void main(String[] args) {

        JOptionPane.showMessageDialog(
            null,
            "Welcome to the Yarn Calculator for Crochet Projects!\n\n" +
            "This application will help you estimate how much yarn\n" +
            "you need for your crochet projects including:\n" +
            "- Scarves\n- Sweaters (with sizing options)\n- Blankets (with sizing options)\n- Hats\n\n" +
            "Let's get started!",
            "Welcome to Yarn Calculator",
            JOptionPane.INFORMATION_MESSAGE
        );
        
        
        YarnCalculatorApp app = new YarnCalculatorApp();
        app.showMainMenu();
    }
    }
    

