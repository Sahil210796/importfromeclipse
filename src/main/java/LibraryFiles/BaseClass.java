package LibraryFiles;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseClass 
{
	public WebDriver driver;
	
	public void initializeBrowser(String browserName) throws IOException 
	{
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--disable-notifications");
		
		
		 if (browserName.equalsIgnoreCase("chrome")) 
		 {
			 driver=new ChromeDriver(op);
		 } 
		 else if(browserName.equalsIgnoreCase("fitefox"))
		 {
			 driver= new FirefoxDriver();
		 }
		 else if(browserName.equalsIgnoreCase("edge"))
		 {
			 driver= new EdgeDriver();
		 }		 
		 driver.get(UtiltyClass.getPFData("URL"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();		
	}

}
