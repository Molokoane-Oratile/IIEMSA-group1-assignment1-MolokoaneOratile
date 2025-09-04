/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package yarncalculatorapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import static org.junit.Assert.*;


public class YarnModelTest {
    
    private YarnModel yarnModel;
    
    @Before
    public void setUp(){
        yarnModel = new YarnModel();
    }
    
    @After
    public void tearDown(){
        yarnModel = null;
    }
    
    @Test
    public void testDefaultConstructor(){
       assertEquals("", yarnModel.getProjectType());
       assertEquals("", yarnModel.getYarnType());
       assertEquals("", yarnModel.getSize());
       assertEquals(0.0, yarnModel.getYardageNeeded(), 0.01);
       assertEquals(0, yarnModel.getSkeinCount()); 
    }
    
    @Test
    public void testScarfCalculationWorsted(){
        yarnModel.setProjectType("Scarf");
        yarnModel.setYarnType("Worsted");
        yarnModel.calculateYarnRequirements();
        
        assertEquals(300.0, yarnModel.getYardageNeeded(), 0.01);
        assertEquals(2, yarnModel.getSkeinCount());
    }
    
    @Test
    public void testScarfCalculationChunky(){
        yarnModel.setProjectType("Scarf");
        yarnModel.setYarnType("Chunky");
        yarnModel.calculateYarnRequirements();
        
        assertEquals(390.0, yarnModel.getYardageNeeded(), 0.01);
        assertEquals(4, yarnModel.getSkeinCount());
        
    }
     @Test
    public void testScarfCalculationLace() {
        yarnModel.setProjectType("Scarf");
        yarnModel.setYarnType("Lace");
        yarnModel.calculateYarnRequirements();
        
        assertEquals(210.0, yarnModel.getYardageNeeded(), 0.01);
        assertEquals(1, yarnModel.getSkeinCount());
    }
    
    // Test sweater calculations
    @Test
    public void testSweaterSmallWorsted() {
        yarnModel.setProjectType("Sweater");
        yarnModel.setYarnType("Worsted");
        yarnModel.setSize("Small");
        yarnModel.calculateYarnRequirements();
        
        assertEquals(1200.0, yarnModel.getYardageNeeded(), 0.01);
        assertEquals(6, yarnModel.getSkeinCount());
    }
    
    @Test
    public void testSweaterLargeChunky() {
        yarnModel.setProjectType("Sweater");
        yarnModel.setYarnType("Chunky");
        yarnModel.setSize("Large");
        yarnModel.calculateYarnRequirements();
        
        assertEquals(2080.0, yarnModel.getYardageNeeded(), 0.01); 
        assertEquals(20, yarnModel.getSkeinCount());
    }
    
    @Test
    public void testSweaterXLargeDK() {
        yarnModel.setProjectType("Sweater");
        yarnModel.setYarnType("DK");
        yarnModel.setSize("X-Large");
        yarnModel.calculateYarnRequirements();
        
        assertEquals(1620.0, yarnModel.getYardageNeeded(), 0.01); 
        assertEquals(7, yarnModel.getSkeinCount());
    }
 
    @Test
    public void testHatWorsted() {
        yarnModel.setProjectType("Hat");
        yarnModel.setYarnType("Worsted");
        yarnModel.calculateYarnRequirements();
        
        assertEquals(150.0, yarnModel.getYardageNeeded(), 0.01);
        assertEquals(1, yarnModel.getSkeinCount());
    }
    
   
    @Test
    public void testCaseInsensitiveProjectType() {
        yarnModel.setProjectType("SWEATER");
        yarnModel.setYarnType("worsted");
        yarnModel.setSize("MEDIUM");
        yarnModel.calculateYarnRequirements();
        
        assertEquals(1400.0, yarnModel.getYardageNeeded(), 0.01);
    }
    
    
    @Test
    public void testDefaultSizeHandling() {
        yarnModel.setProjectType("Sweater");
        yarnModel.setYarnType("Worsted");
        yarnModel.setSize(""); 
        yarnModel.calculateYarnRequirements();
        
        assertEquals(1400.0, yarnModel.getYardageNeeded(), 0.01); 
    }
    
    @Test
    public void testUnknownProjectType() {
        yarnModel.setProjectType("Unknown");
        yarnModel.setYarnType("Worsted");
        yarnModel.calculateYarnRequirements();
        
        assertEquals(200.0, yarnModel.getYardageNeeded(), 0.01); 
    }
    
    @Test
    public void testUnknownYarnType() {
        yarnModel.setProjectType("Hat");
        yarnModel.setYarnType("Unknown");
        yarnModel.calculateYarnRequirements();
        
        assertEquals(150.0, yarnModel.getYardageNeeded(), 0.01); 
        assertEquals(1, yarnModel.getSkeinCount()); 
    }
    
    @Test
    public void testReportGenerationWithoutSize() {
        yarnModel.setProjectType("Hat");
        yarnModel.setYarnType("DK");
        yarnModel.calculateYarnRequirements();
        
        String report = yarnModel.generateReport();
        
        assertTrue(report.contains("Project Type: Hat"));
        assertTrue(report.contains("Yarn Type: DK"));
        assertFalse(report.contains("Size:")); 
        assertTrue(report.contains("Estimated Yardage Needed: 135 yards"));
    }
    
     @Test
    public void testNullStringHandling() {
        
        yarnModel.setProjectType(null);
        yarnModel.setYarnType(null);
        yarnModel.setSize(null);
        
        try {
            yarnModel.calculateYarnRequirements();
            assertTrue(yarnModel.getYardageNeeded() >= 0);
            assertTrue(yarnModel.getSkeinCount() >= 0);
        } catch (NullPointerException e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void testYardageCalculationPrecision() {
        yarnModel.setProjectType("Blanket");
        yarnModel.setYarnType("Lace");
        yarnModel.setSize("Queen");
        yarnModel.calculateYarnRequirements();
        

        assertEquals(2450.0, yarnModel.getYardageNeeded(), 0.01); 
        assertEquals(4, yarnModel.getSkeinCount());
    }
    
    
    @Test(timeout = 1000) 
    public void testCalculationPerformance() {
        for (int i = 0; i < 1000; i++) {
            yarnModel.setProjectType("Sweater");
            yarnModel.setYarnType("Worsted");
            yarnModel.setSize("Medium");
            yarnModel.calculateYarnRequirements();
        }
       
        assertTrue(true);
    }
}


