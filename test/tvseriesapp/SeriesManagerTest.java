/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package tvseriesapp;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Baackup
 */
public class SeriesManagerTest {
    
    private SeriesManager manager;
    private SeriesModel testSeries;
    
    @Before
    public void setUp(){
        manager = new SeriesManager();
        testSeries = new SeriesModel("S001", "Breaking Bad", "18", "62");
    }
    
    @Test
    public void TestSearchSeries(){
        manager.getSeriesList().add(testSeries);
        
        SeriesModel result = manager.searchSeriesById("S001");
        
        assertNotNull("Series should be found", result);
        assertEquals("Series ID should match", "S001", result.getSeriesId());
        assertEquals("Series name should match", "Breaking Bad", result.getSeriesName());
        assertEquals("Series age should match", "18", result.getSeriesAge());
        assertEquals("Series episodes should match", "62", result.getSeriesNumberOfEpisodes()); 
        
    }
    
    @Test 
    public void TestSearchSeries_SeriesNotFound(){
        manager.getSeriesList().add(testSeries);
        
        SeriesModel result = manager.searchSeriesById("S999");
        
        assertNull("Series should not be found", result);
    }
    
    @Test
    public void TestUpdateSeries(){
        manager.getSeriesList().add(testSeries);
        String newName = "Better Call Saul";
        String newAge = "16";
        String newEpisodes = "63";
        
        boolean updateResult = manager.updateSeries("S001", newName, newAge, newEpisodes);
        
        assertTrue("Update should be successful", updateResult);
        
        SeriesModel updateSeries = manager.searchSeriesById("S001");
        assertNotNull("Updated series shoukd exist", updateSeries);
        assertEquals("Series name should be updated", newName, updateSeries.getSeriesName());
        assertEquals("Series age should be updated", newAge, updateSeries.getSeriesAge());
        assertEquals("Series episodes should be updated", newEpisodes, updateSeries.getSeriesNumberOfEpisodes());
      
    }
    
    @Test
    public void TestDeleteSeries(){
        manager.getSeriesList().add(testSeries);
        int initialSize = manager.getSeriesList().size();
        
        boolean deleteResult = manager.deleteSeries("S001");
        
        assertTrue("Delete should be successful", deleteResult);
        assertEquals("Series list size should decrease by 1", initialSize - 1, manager.getSeriesList().size());
        
        SeriesModel deleteSeries = manager.searchSeriesById("S001");
        assertNull("Deleted series should not be found", deleteSeries);
    }
    
    
    @Test
    public void TestDeleteSeries_SeriesNotFound(){
        manager.getSeriesList().add(testSeries);
        int initialSize = manager.getSeriesList().size();
        
        boolean deleteResult = manager.deleteSeries("S999");
        
        assertFalse("Delete should fail for non-existent series", deleteResult);
        assertEquals("Series List size should remain unchanged", initialSize, manager.getSeriesList().size());
    }
    
    @Test 
    public void TestSeriesAgeRestriction_AgeValid(){
        assertTrue("PG should be valid", manager.validateSeriesAge("PG"));
        assertTrue("13 should be valid", manager.validateSeriesAge("13"));
        assertTrue("16 should be valid", manager.validateSeriesAge("16"));
        assertTrue("18 should be valid", manager.validateSeriesAge("18"));
        
    }
    
    @Test
    public void TestSeriesAgeRestriction_SeriesAgeInValid(){
        assertFalse("21 should be invalid", manager.validateSeriesAge("21"));
        assertFalse("G should be invalid", manager.validateSeriesAge("G"));
        assertFalse("12A should be invalid", manager.validateSeriesAge("12A"));
        assertFalse("Empty string should be invalid", manager.validateSeriesAge(""));
        assertFalse("Null should be invalid", manager.validateSeriesAge(null));
    }
    
    @Test
    public void TestSeriesModelConstructorAndAccessors(){
        SeriesModel series = new SeriesModel("S002", "Game of Thrones", "18", "73");
        assertEquals("S002", series.getSeriesId());
        assertEquals("Game of Thrones", series.getSeriesName());
        assertEquals("18", series.getSeriesAge());
        assertEquals("73", series.getSeriesNumberOfEpisodes());
        
        // Test default constructor
        SeriesModel defaultSeries = new SeriesModel();
        assertEquals("", defaultSeries.getSeriesId());
        assertEquals("", defaultSeries.getSeriesName());
        assertEquals("", defaultSeries.getSeriesAge());
        assertEquals("", defaultSeries.getSeriesNumberOfEpisodes());
        
        // Test setters
        defaultSeries.setSeriesId("S003");
        defaultSeries.setSeriesName("The Office");
        defaultSeries.setSeriesAge("PG");
        defaultSeries.setSeriesNumberOfEpisodes("201");
        
        assertEquals("S003", defaultSeries.getSeriesId());
        assertEquals("The Office", defaultSeries.getSeriesName());
        assertEquals("PG", defaultSeries.getSeriesAge());
        assertEquals("201", defaultSeries.getSeriesNumberOfEpisodes());
        
    }
    
    @Test
    public void TestUpdateSeries_SeriesNotFound(){
        boolean updateResult = manager.updateSeries("S999", "New Name", "PG", "50");
        
        assertFalse("Update should fail for non-existent series", updateResult);
    }
    
    @Test
    public void TestSeriesManagerInitialization(){
        SeriesManager newManager = new SeriesManager();
        assertNotNull("Series list should be initialized", newManager.getSeriesList());
        assertEquals("Series list should be empty initially", 0, newManager.getSeriesList().size());
    }
    
    @Test
    public void TestMultipleSeriesOperations(){
        SeriesModel series1 = new SeriesModel("S001", "Breaking Bad", "18", "62");
        SeriesModel series2 = new SeriesModel("S002", "Friends", "PG", "236");
        SeriesModel series3 = new SeriesModel("S003", "Stranger Things", "16", "42");
        
        manager.getSeriesList().add(series1);
        manager.getSeriesList().add(series2);
        manager.getSeriesList().add(series3);
        
        // Verify all series are added
        assertEquals("Should have 3 series", 3, manager.getSeriesList().size());
        
        // Test searching for each series
        assertNotNull("Should find S001", manager.searchSeriesById("S001"));
        assertNotNull("Should find S002", manager.searchSeriesById("S002"));
        assertNotNull("Should find S003", manager.searchSeriesById("S003"));
        
        // Delete middle series
        assertTrue("Should delete S002", manager.deleteSeries("S002"));
        assertEquals("Should have 2 series after deletion", 2, manager.getSeriesList().size());
        assertNull("S002 should no longer exist", manager.searchSeriesById("S002"));
        
        // Verify other series still exist
        assertNotNull("S001 should still exist", manager.searchSeriesById("S001"));
        assertNotNull("S003 should still exist", manager.searchSeriesById("S003"));
    }
    }
    
    
   