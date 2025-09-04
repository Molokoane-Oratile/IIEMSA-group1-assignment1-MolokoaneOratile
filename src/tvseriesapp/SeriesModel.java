/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tvseriesapp;

/**
 *
 * @author Baackup
 */
public class SeriesModel {
   private String seriesId;
    private String seriesName;
    private String seriesAge;
    private String seriesNumberOfEpisodes;
    
    // Constructor
    public SeriesModel(String seriesId, String seriesName, String seriesAge, String seriesNumberOfEpisodes) {
        this.seriesId = seriesId;
        this.seriesName = seriesName;
        this.seriesAge = seriesAge;
        this.seriesNumberOfEpisodes = seriesNumberOfEpisodes;
    }
    
    // Default constructor
    public SeriesModel() {
        this.seriesId = "";
        this.seriesName = "";
        this.seriesAge = "";
        this.seriesNumberOfEpisodes = "";
    }
    
    // Getters and Setters
    public String getSeriesId() {
        return seriesId;
    }
    
    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }
    
    public String getSeriesName() {
        return seriesName;
    }
    
    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }
    
    public String getSeriesAge() {
        return seriesAge;
    }
    
    public void setSeriesAge(String seriesAge) {
        this.seriesAge = seriesAge;
    }
    
    public String getSeriesNumberOfEpisodes() {
        return seriesNumberOfEpisodes;
    }
    
    public void setSeriesNumberOfEpisodes(String seriesNumberOfEpisodes) {
        this.seriesNumberOfEpisodes = seriesNumberOfEpisodes;
    }  
}
