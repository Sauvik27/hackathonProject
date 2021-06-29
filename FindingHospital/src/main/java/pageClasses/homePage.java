package pageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import baseClass.pageBaseClass;

public class homePage extends pageBaseClass{

	public WebDriver driver;
	
	//Constructor
	public homePage(WebDriver driver) {
		this.driver = driver;
	}
	
	
	
	public searchPage seachHospitals() {
		
		
		
		WebElement locElement = driver.findElement(By.xpath(prop.getProperty("locationSearchBox_xpath")));
		locElement.clear();
		locElement.sendKeys("Bangalore");

		for(int i=0;i<15;i++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(driver.findElement(By.xpath(prop.getProperty("locationClick_xpath"))).getText().equalsIgnoreCase("Bangalore")) {
				driver.findElement(By.xpath(prop.getProperty("locationClick_xpath"))).click();
				break;
			}
			if(i==5 || i==10) {
				locElement.clear();
				locElement.sendKeys("Bangalore");
			}
		}
		reportPass("Found Bangalore City in Location List");
		
		WebElement hospitalType = driver.findElement(By.xpath(prop.getProperty("hospitalSearchBox_xpath")));
		hospitalType.sendKeys("Hospital");

		for(int i=0;i<15;i++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}if(driver.findElement(By.xpath(prop.getProperty("hospitalBoxClick_xpath"))).getText().equalsIgnoreCase("Hospital")) {
				driver.findElement(By.xpath(prop.getProperty("hospitalTextClick"))).click();
				break;
			}
			if(i==5 || i==10) {
				hospitalType.clear();
				hospitalType.sendKeys("Hospital");
			}
		}
		
		

		
		
		reportPass("Hospital keyword found on category list");
		reportPass("Hospital search executed");
		return PageFactory.initElements(driver, searchPage.class);
	}
}
