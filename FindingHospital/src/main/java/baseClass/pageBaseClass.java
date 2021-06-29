package baseClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.utils.ExtentReportManager;

import pageClasses.homePage;


public class pageBaseClass {
	
	public WebDriver driver;
	public static Properties prop;
	public ExtentReports report = ExtentReportManager.getReportInstance();
	public static ExtentTest logger;

	/*********** Invoke Browser 
	 * @throws IOException ********/
	public void invokeBrowser(String BrowserName) throws IOException{

		try {
			if (BrowserName.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
				driver = new ChromeDriver();
			} else {
				System.setProperty("webdriver.firefox.driver",
						System.getProperty("user.dir") + "\\drivers\\geckodriver.exe");
				driver = new FirefoxDriver();
			}
		} catch (Exception e) {
			// TODO: handle exception
			//reportFail(e.getMessage());
			System.out.println(e.getMessage());
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		
		if (prop == null) {
			prop = new Properties();
			try {
				FileInputStream file = new FileInputStream(System.getProperty("user.dir")
						+ "\\propertiesFile\\projectConfig.properties");
				prop.load(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				//reportFail(e.getMessage());
			}
		}
	}
	

	public homePage OpenApplication() {
			driver.get(prop.getProperty("websiteURL"));
			reportPass("Website is opened Successfully");
			return PageFactory.initElements(driver, homePage.class);
	}
	
	/***************Get Page Title**************/
	public void getTitle(String expectedTitle) {
		Assert.assertEquals(driver.getTitle(), expectedTitle);
	}
	
	
	
	
	
	/******************** Reporting Functions ******************/
	public void reportFail(String reportString) {
		logger.log(Status.FAIL, reportString);
		screenshot();
		Assert.fail(reportString);
	}

	public void reportPass(String reportString) {
		logger.log(Status.PASS, reportString);
	}

	public void screenshot() {

	}
	
	@AfterMethod
	public void flushReport() {
		report.flush();
		//driver.close();
	}
}
