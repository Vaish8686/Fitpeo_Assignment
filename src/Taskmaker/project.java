package Taskmaker;
import java.io.FileInputStream;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;


public class project {
	public static String browser;
	WebDriver driver;
	public WebDriver invokebrowser(String browser) 
	{
		if(browser.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver","C:\\Users\\savul\\Desktop\\selenium\\Drivers\\chromedriver-win64\\chromedriver.exe");
	        driver= (WebDriver) new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("edge"))
		{
			System.setProperty("webdriver.edge.driver","C:\\Users\\savul\\Desktop\\selenium\\Drivers\\msedgedriver.exe");
		    driver = (WebDriver) new EdgeDriver();
		}
		else
		{
			System.out.println("no specified browser");
	    }
		return driver;
	}
	
	
	public void Homepage() throws InterruptedException {
		driver.findElement(By.xpath("//div[@class='satoshi MuiBox-root css-5ty6tm' and text()='Revenue Calculator']")).click();
		System.out.println("successfully moved to Revenue Calculator!");
		Thread.sleep(5000);
				
        //Scroll down the page until the revenue calculator slider is visible.
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)", "");
        System.out.println("Slider found and scrolled to successfully!");
		
    	//Adjust the Slider:
    	 WebElement slider = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/span[1]/span[3]"));
        Actions actions = new Actions(driver);
        actions.dragAndDropBy(slider,94,0).perform();//823
        Thread.sleep(2000);
        System.out.println("Slider moved to the value 820 successfully!");
        
        //Update the Text Field:
        actions.dragAndDropBy(slider,-150,0).perform();
        Thread.sleep(2000);
		WebElement text_field= driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/input[1]")); 
		text_field.sendKeys(Keys.BACK_SPACE);
    	text_field.sendKeys("560");//# Adjust ID as necessary
    	
    	//Validate Slider Value:
    	String fieldValue = text_field.getAttribute("value");
    	if (!fieldValue.equals("560")) {
    	    System.out.println("Test failed: Expected value '560', but got '" + fieldValue + "'");
    	} else {
    	    System.out.println("Test passed!");
    	}
	    Thread.sleep(5000);
        System.out.println("Slider moved to the value 560 successfully!");
        
        //Select CPT Codes:
        int length = text_field.getAttribute("value").length(); // Get current text length
        for (int i = 0; i < length; i++) {
          text_field.sendKeys(Keys.BACK_SPACE);
         };
        text_field.sendKeys("820");
        Thread.sleep(2000);
        js.executeScript("window.scrollBy(0,350)", "");
        Thread.sleep(4000);
        driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/div[1]/label[1]/span[1]/input[1]")).click();
        driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/div[2]/label[1]/span[1]/input[1]")).click();
        driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/div[3]/label[1]/span[1]/input[1]")).click();
        driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/div[8]/label[1]/span[1]/input[1]")).click();
        Thread.sleep(4000);
        System.out.println("CPT Codes are slected");
        Thread.sleep(4000);
        
        //Validate Total Recurring Reimbursement
        WebElement total_Reimubursement= driver.findElement(By.xpath("/html/body/div[2]/div[1]/header/div/p[4]/p"));
        String TotalValue = total_Reimubursement.getText();
    	if (!TotalValue.equals("$110700")) {
    	    System.out.println("Test failed: Expected value '$110700', but got '" + TotalValue + "'");
    	} else {
    	    System.out.println("Test passed! Value is:"+ TotalValue);
    	}       
	}
	
	 public void close(WebDriver driver)
	    {
	    	driver.close();
	    }
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		FileInputStream fis=new FileInputStream("C:\\Users\\savul\\eclipse-workspace\\LiveProject\\src\\main\\java\\Qualitybot\\config.properties");
		Properties p=new Properties();
		p.load(fis);
		String mybrowser=p.getProperty("browser");
		String myurl=p.getProperty("URL");
		System.out.println(mybrowser);
		project obj=new project();
	  	WebDriver dri=obj.invokebrowser(mybrowser);                  //open the browser
	  	dri.get(myurl);
	  	dri.manage().window().maximize();
	  	obj.Homepage();
	  	obj.close(dri); 
	}
		


}
