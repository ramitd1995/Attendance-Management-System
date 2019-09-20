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
public class CreatingRegisteringNewAccount {
     public String username = "ramitdlambdatest";
    public String accesskey = "O0xKXBte1IdZMdmGqavn7dUMdRDylD26dFV0RLnWB10P44kbVh";
    public String gridURL = "@stage-hub.lambdatest.com/wd/hub";
 
     public String platform;
     public String browserName;
     public String browserVersion;
     public String resolution;
 
 
    public RemoteWebDriver driver = null;
 
     boolean status = false;
  
        @Parameterized.Parameters
     public static LinkedList<String[]> getEnvironments() throws Exception {
        LinkedList<String[]> env = new LinkedList<String[]>();
        env.add(new String[]{"macOS Sierra", "chrome", "72.0","1920x1080"});
        env.add(new String[]{"macOS Sierra","firefox","66.0", "1920x1080"});
        
        
       // env.add(new String[]{"WIN8","firefox","66.0", "1920x1080"});
        //env.add(new String[]{"WIN7","firefox","63.0", "1920x1080"});
        //env.add(new String[]{"WIN7","firefox","64.0", "1920x1080"});
        //env.add(new String[]{"WIN7","firefox","65.0", "1920x1080"});
        //env.add(new String[]{"WIN7","firefox","66.0", "1920x1080"});
        
        return env;
    }
 
 
   public CreatingRegisteringNewAccount(String platform, String browserName, String browserVersion, String resolution) {
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
        capabilities.setCapability("name", "AMSTests");
        capabilities.setCapability("resolution",resolution);
        capabilities.setCapability("network", true); // To enable network logs
        capabilities.setCapability("visual", true); // To enable step by step screenshot
        capabilities.setCapability("video", true); // To enable video recording
        capabilities.setCapability("console", true); // To capture console logs
        capabilities.setCapability("tunnel",true);
        capabilities.setCapability("fixedIP","10.92.181.177");  // 10.26.33.13,14,15
        capabilities.setCapability("fixedPort","43000");  WIN8
        // 43000, 43001
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
    	       
    	       // admin account login
    	       driver.findElement(By.xpath("//*[@id=\'username\']")).sendKeys("admin");  //username
    	       driver.findElement(By.xpath("//*[@id=\'password\']")).sendKeys("admin07");  //password
    	       
    	       Thread.sleep(2000);
    	       driver.findElement(By.cssSelector("#submit")).click();  // Sign in
    	      
    	       // Creating an account for new user: Admin or Lecturer or Student
    	       
    	       Thread.sleep(2000);
    	       driver.findElement(By.xpath("/html/body/div[1]/nav/div[2]/ul[2]/a[1]")).click(); //Admin Area
    	          
    	       WebElement AddMember = driver.findElement(By.xpath("/html/body/div[1]/nav/div[2]/ul/li[2]/a")); // Dropdown menu for Members
    		   Actions actions = new Actions(driver);
    		   actions.moveToElement(AddMember);
    		   actions.click();
    		   actions.build().perform();
    		   driver.findElement(By.xpath("/html/body/div[1]/nav/div[2]/ul/li[2]/ul/li[2]/a")).click();  //Select Add Member
    		   Thread.sleep(2000);
    		   
    		   driver.findElement(By.id("memberID")).sendKeys("Sagar");  //Member Username
    		   driver.findElement(By.xpath("//*[@id=\'password\']")).sendKeys("sagar07"); //password
    		   driver.findElement(By.xpath("//*[@id=\'confirmPassword\']")).sendKeys("sagar07"); //confirm password
    		   driver.findElement(By.id("email")).sendKeys("sagar@gmail.com"); //email Id
    		 
    		   
    		   Select group = new Select(driver.findElement(By.xpath("//*[@id=\'groupID\']"))); // Group
    		   group.selectByValue("4"); // Select Student
    		   
    		   driver.findElement(By.xpath("//*[@id=\'custom1\']")).sendKeys("Sagar Kapoor"); //Full Name
    		   driver.findElement(By.xpath("//*[@id=\'custom2\']")).sendKeys("Hari Nagar"); //Address
    		   driver.findElement(By.xpath("//*[@id=\'custom3\']")).sendKeys("Delhi"); //City
    		   driver.findElement(By.xpath("//*[@id=\'custom4\']")).sendKeys("New Delhi");  // State
    		   driver.findElement(By.xpath("//*[@id=\'comments\']")).sendKeys("Added to Student Group");  //Comment
    		   
    		   Thread.sleep(2000);
    		   driver.findElement(By.cssSelector("#saveChanges")).click();	// Save Changes
    		   Thread.sleep(2000);
    		   
    		   //Registering user to mark attendance
    		   
    		   driver.findElement(By.xpath("/html/body/div/nav/div[2]/div/a[1]")).click();
    		   Thread.sleep(2000);  // Users area button
    		   
    		   driver.findElement(By.id("students_add_new")).click();
    		   Thread.sleep(2000);  // add new student
    		   
    		   WebElement iFrame= driver.findElement(By.tagName("iframe"));
    		   driver.switchTo().frame(iFrame);  //Switching to a new frame
    		   
    		   
    		   driver.findElement(By.cssSelector("#regno")).sendKeys("BCA/4417/18");   //Reg No.
    		   driver.findElement(By.xpath("//*[@id=\'name\']")).sendKeys("Sagar Kapoor");  //Name
    		   
    		   
    		   WebElement selectMyElement = driver.findElement((By.xpath("//*[@id=\'s2id_course-container\']"))); 
    		   selectMyElement.click(); //Course

    		   Actions keyDown = new Actions(driver);
    		   keyDown.sendKeys(Keys.chord(Keys.DOWN, Keys.DOWN, Keys.DOWN, Keys.RETURN)).perform();
    		   Thread.sleep(3000);
    		   driver.findElement(By.id("insert")).click();
    		     // Save
    		   
    		   driver.switchTo().defaultContent();
    		   driver.findElement(By.className(("close"))).click();  //Close frame
    		   
    		   driver.findElement(By.xpath("/html/body/div[1]/nav/div[2]/ul[3]/a")).click();  // Sign Out
    		   
    		  // Login in with new credentials
    		   
    		   driver.findElement(By.xpath("//*[@id=\'username\']")).sendKeys("Sagar");  //new username
    	       driver.findElement(By.xpath("//*[@id=\'password\']")).sendKeys("sagar07");  //new password
    	       
    	       Thread.sleep(2000);
    	       driver.findElement(By.cssSelector("#submit")).click();  // Sign in
    	       
    		   
    	   
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