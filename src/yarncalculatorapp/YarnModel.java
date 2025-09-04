/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yarncalculatorapp;

/**
 *
 * @author Baackup
 */
public class YarnModel {
     private String projectType;
    private String yarnType;
    private String size;
    private double yardageNeeded;
    private int skeinCount;
    
    // Constructor
    public YarnModel() {
        this.projectType = "";
        this.yarnType = "";
        this.size = "";
        this.yardageNeeded = 0.0;
        this.skeinCount = 0;
    }
    
    // Getters and Setters
    public String getProjectType() {
        return projectType;
    }
    
    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }
    
    public String getYarnType() {
        return yarnType;
    }
    
    public void setYarnType(String yarnType) {
        this.yarnType = yarnType;
    }
    
    public String getSize() {
        return size;
    }
    
    public void setSize(String size) {
        this.size = size;
    }
    
    public double getYardageNeeded() {
        return yardageNeeded;
    }
    
    public void setYardageNeeded(double yardageNeeded) {
        this.yardageNeeded = yardageNeeded;
    }
    
    public int getSkeinCount() {
        return skeinCount;
    }
    
    public void setSkeinCount(int skeinCount) {
        this.skeinCount = skeinCount;
    }
    
    // Method to calculate yarn requirements
    public void calculateYarnRequirements() {
        double baseYardage = 0;
        
        // Calculate base yardage based on project type
        switch (projectType.toLowerCase()) {
            case "scarf":
                baseYardage = calculateScarfYardage();
                break;
            case "sweater":
                baseYardage = calculateSweaterYardage();
                break;
            case "blanket":
                baseYardage = calculateBlanketYardage();
                break;
            case "hat":
                baseYardage = calculateHatYardage();
                break;
            default:
                baseYardage = 200; // Default estimate
        }
        
        // Adjust for yarn type
        baseYardage = adjustForYarnType(baseYardage);
        
        this.yardageNeeded = baseYardage;
        this.skeinCount = calculateSkeinCount(baseYardage);
    }
    
    // Calculate yardage for scarves
    private double calculateScarfYardage() {
        // Standard scarf: 6 feet long, 8 inches wide
        return 300; // Base estimate for medium weight yarn
    }
    
    // Calculate yardage for sweaters based on size
    private double calculateSweaterYardage() {
        switch (size.toLowerCase()) {
            case "small":
                return 1200;
            case "medium":
                return 1400;
            case "large":
                return 1600;
            case "x-large":
                return 1800;
            default:
                return 1400; // Default to medium
        }
    }
    
    // Calculate yardage for blankets based on size
    private double calculateBlanketYardage() {
        switch (size.toLowerCase()) {
            case "baby":
                return 800;
            case "lap":
                return 1500;
            case "twin":
                return 2500;
            case "queen":
                return 3500;
            default:
                return 1500; // Default to lap size
        }
    }
    
    // Calculate yardage for hats
    private double calculateHatYardage() {
        return 150; // Standard adult hat
    }
    
    // Adjust yardage based on yarn weight/type
    private double adjustForYarnType(double baseYardage) {
        switch (yarnType.toLowerCase()) {
            case "lace":
                return baseYardage * 0.7; // Lighter weight needs less
            case "dk":
                return baseYardage * 0.9;
            case "worsted":
                return baseYardage; // Base calculation
            case "chunky":
                return baseYardage * 1.3;
            case "super chunky":
                return baseYardage * 1.6;
            default:
                return baseYardage;
        }
    }
    
    // Calculate number of skeins needed
    private int calculateSkeinCount(double totalYardage) {
        int yardsPerSkein;
        
        // Estimate yards per skein based on yarn type
        switch (yarnType.toLowerCase()) {
            case "lace":
                yardsPerSkein = 800;
                break;
            case "dk":
                yardsPerSkein = 280;
                break;
            case "worsted":
                yardsPerSkein = 220;
                break;
            case "chunky":
                yardsPerSkein = 120;
                break;
            case "super chunky":
                yardsPerSkein = 80;
                break;
            default:
                yardsPerSkein = 220; // Default to worsted
        }
        
        // Add 10% extra for safety
        totalYardage *= 1.1;
        
        return (int) Math.ceil(totalYardage / yardsPerSkein);
    }
    
    // Method to generate report
    public String generateReport() {
        StringBuilder report = new StringBuilder();
        report.append("=== YARN CALCULATION REPORT ===\n\n");
        report.append("Project Type: ").append(projectType).append("\n");
        report.append("Yarn Type: ").append(yarnType).append("\n");
        if (!size.isEmpty()) {
            report.append("Size: ").append(size).append("\n");
        }
        report.append("Estimated Yardage Needed: ").append(String.format("%.0f", yardageNeeded)).append(" yards\n");
        report.append("Number of Skeins: ").append(skeinCount).append("\n\n");
        report.append("Note: This includes 10% extra yarn for safety.\n");
        report.append("Actual requirements may vary based on your tension and pattern.");
        
        return report.toString();
    }
}
