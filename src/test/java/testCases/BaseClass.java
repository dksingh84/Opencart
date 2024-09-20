package testCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger;
	public Properties prop;
	
	@BeforeClass(groups = {"sanity","regression"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException {
		
		FileReader file= new FileReader("./src/test/resources/config.properties");
		prop=new Properties();
		prop.load(file);
		logger= LogManager.getLogger(this.getClass());
		
		if(prop.getProperty("execution_env").equalsIgnoreCase("remote")) {
			
			DesiredCapabilities capability= new DesiredCapabilities();
			if(os.equalsIgnoreCase("windows")) {
				capability.setPlatform(Platform.WIN11);
			}
			
			else if(os.equalsIgnoreCase("linux")) {
				capability.setPlatform(Platform.LINUX);
			}
			
			else if(os.equalsIgnoreCase("mac")) {
				capability.setPlatform(Platform.MAC);
			}
			
			else {
				System.out.println("No Matching os");
				return;
			}
			
			switch (br.toLowerCase()) {
			case "chrome":
				capability.setBrowserName("chrome");
				break;
			case "edge":
				capability.setBrowserName("MicrosoftEdge");
				break;
			case "firefox":
				capability.setBrowserName("firefox");
				break;
			default:
				System.out.println("No Matching Browser");
			}
			
			driver= new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
		}
		
		if (prop.getProperty("execution_env").equalsIgnoreCase("local")) {

			switch (br.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			default:
				System.out.println("Invalid browser name");
				return;
			}
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	@AfterClass(groups = {"sanity","regression"})
	public void tearDown() {
		driver.quit();
	}
	
	public String randomString() {
		String gereratedString= RandomStringUtils.randomAlphabetic(5);
		return gereratedString;
	}
	
	public String randomNumber() {
		String gereratedNumber= RandomStringUtils.randomNumeric(10);
		return gereratedNumber;
	}
	
	public String randomAlphaNumber() {
		String gereratedString= RandomStringUtils.randomAlphabetic(3);
		String gereratedNumber= RandomStringUtils.randomNumeric(3);
		return (gereratedString+"@"+gereratedNumber);
	}
	
	public String captureScreen(String tname) throws IOException{
		String timeStamp= new SimpleDateFormat("yyyyMMDDhhmmss").format(new Date());
		TakesScreenshot takesScreenshot= (TakesScreenshot) driver;
		File sourceFile=takesScreenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname +"_" +timeStamp;
		File targetFile= new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}
}
