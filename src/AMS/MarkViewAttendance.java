package AMS;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
 
@RunWith(Parallelized.class)
public class MarkViewAttendance {
     public String username = "ramitdlambdatest";
    public String accesskey = "O0xKXBte1IdZMdmGqavn7dUMdRDylD26dFV0RLnWB10P44kbVh";
    public String gridURL = "@hub.lambdatest.com/wd/hub";
 
     public String platform;
     public String browserName;
     public String browserVersion;
     public String resolution;
 
 
    public RemoteWebDriver driver = null;
 
     boolean status = false;
  
        @Parameterized.Parameters
     public static LinkedList<String[]> getEnvironments() throws Exception {
        LinkedList<String[]> env = new LinkedList<String[]>();
        env.add(new String[]{"WIN10", "Chrome", "70.0","1920x1080"});
        
        env.add(new String[]{"macOS High Sierra","Chrome","61.0", "1920x1080"});
        
        //env.add(new String[]{"WIN7","firefox","63.0", "1920x1080"});
        //env.add(new String[]{"WIN7","firefox","64.0", "1920x1080"});
        //env.add(new String[]{"WIN7","firefox","65.0", "1920x1080"});
        //env.add(new String[]{"WIN7","firefox","66.0", "1920x1080"});
        
        return env;
    }
 
 
   public MarkViewAttendance(String platform, String browserName, String browserVersion, String resolution) {
        this.platform = platform;
        this.browserName = browserName;
        this.browserVersion = browserVersion;
        this.resolution = resolution;
     
     }
 
    @Before
    public void setUp() throws Exception {
       DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("version", browserVersion);
        capabilities.setCapability("platform", platform); // If this cap isn't specified, it will just get the any available one
        capabilities.setCapability("build", "Attendance_Management_System.");
        capabilities.setCapability("name", "AMSTest-1");
        capabilities.setCapability("resolution",resolution);
        capabilities.setCapability("network", true); // To enable network logs
        capabilities.setCapability("visual", true); // To enable step by step screenshot
        capabilities.setCapability("video", true); // To enable video recording
        capabilities.setCapability("console", true); // To capture console logs
        capabilities.setCapability("tunnel",true);
        try {
            driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), capabilities);
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
  
    @Test
    public void testParallel() throws Exception {
       try {
    		
    	       driver.get("http://localhost.lambdatest.com/attendance/index.php?signIn=1");
    	       
    	       // Student account login
    	       driver.findElement(By.xpath("//*[@id=\'username\']")).sendKeys("Sagar");  //username
    	       driver.findElement(By.xpath("//*[@id=\'password\']")).sendKeys("sagar07");  //password
    	       
    	       Thread.sleep(2000);
    	       driver.findElement(By.cssSelector("#submit")).click();  // Sign in
    	       Thread.sleep(2000);
    	      
    	       //Marking Attendance
    	       
    	        driver.findElement(By.cssSelector("#attendance-tile > div > div > a")).click();
    	        Thread.sleep(2000);  //Attendance record
    	        driver.findElement(By.id("addNew")).click();
    	        Thread.sleep(2000);  //Add New record
    	        
    	        driver.findElement(By.id("s2id_student-container")).click();
    	        driver.findElement(By.xpath("//*[@id=\'select2-drop\']/div/input")).sendKeys("Sagar Kapoor");  // Student
    	        Actions keyDown = new Actions(driver);
     		    keyDown.sendKeys(Keys.chord(Keys.DOWN, Keys.RETURN)).perform();  
     		    
     		    driver.findElement(By.id("s2id_week")).click();
     		    driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/div/input")).sendKeys("3");  //Week
     		    keyDown.sendKeys(Keys.chord(Keys.RETURN)).perform();
     		    
     		    driver.findElement(By.id("s2id_unit-container")).click();
     		    driver.findElement(By.xpath("//*[@id=\'select2-drop\']/div/input")).sendKeys("C++");  //Unit
    		    keyDown.sendKeys(Keys.chord(Keys.DOWN, Keys.RETURN)).perform();
    		    
    		    driver.findElement(By.id("attended")).click();  //Check Attended
    		    
    		    Thread.sleep(2000);
    		    driver.findElement(By.cssSelector("#insert")).click();  //Save
    		    
    		    Thread.sleep(2000);
    		    driver.findElement(By.xpath("/html/body/div[1]/nav/div[2]/ul[2]/a")).click();  //Sign Out
     		    

     	       // Lecturer account login
     	       driver.findElement(By.xpath("//*[@id=\'username\']")).sendKeys("ramit");  //username
     	       driver.findElement(By.xpath("//*[@id=\'password\']")).sendKeys("ramit07");  //password
     	       
     	       Thread.sleep(2000);
     	       driver.findElement(By.cssSelector("#submit")).click();  // Sign in
     	       Thread.sleep(2000);
    		    
     	      //View Attendance
    	       
   	          driver.findElement(By.cssSelector("#attendance-tile > div > div > a")).click();
   	          Thread.sleep(2000);  //Attendance record
   	          
   	          driver.findElement(By.xpath("//*[@id=\'Filter\']")).click(); //Filter
   	          Thread.sleep(2000);
   	          
   	          WebElement selectMyElement = driver.findElement((By.xpath("//*[@id=\'s2id_FilterField_1_\']"))); 
		      selectMyElement.click();  //Filtered Field 

		   
		   keyDown.sendKeys(Keys.chord(Keys.DOWN ,Keys.DOWN ,Keys.DOWN ,Keys.DOWN, Keys.DOWN, Keys.RETURN)).perform();
		   Thread.sleep(2000);  // Selecting Unit
		   
		   driver.findElement(By.xpath("/html/body/div[1]/div[4]/div/form/div[3]/div[7]/input")).sendKeys("C++");
		   Thread.sleep(2000);  // Enter Unit
		   driver.findElement(By.cssSelector("#applyFilters")).click();
		   Thread.sleep(3000);  // Apply Filter
		   
		   driver.findElement(By.xpath("/html/body/div[1]/nav/div[2]/ul[2]/a")).click();  //Sign Out
		    
		   
     		    
    	           		   
    	   
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
  
    @After
    public void tearDown() throws Exception {
       if (driver != null) {
            ((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
            driver.quit();
        }
    }
}