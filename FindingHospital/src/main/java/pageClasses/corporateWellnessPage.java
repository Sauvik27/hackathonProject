package pageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import baseClass.pageBaseClass;

public class corporateWellnessPage extends pageBaseClass{
	
	public WebDriver driver;
	
	//Constructor
	public corporateWellnessPage(WebDriver driver) {
		this.driver = driver;
	}
	

	
	public void submitForm() {
		
		driver.findElement(By.id(prop.getProperty("name_id"))).sendKeys("Ram");
		driver.findElement(By.id(prop.getProperty("organisation_id"))).sendKeys("Cognizant");
		driver.findElement(By.id(prop.getProperty("email_id"))).sendKeys("1234@gmail.com");
		driver.findElement(By.id(prop.getProperty("phone_id"))).sendKeys("98745632101");
		
		Select select = new Select(driver.findElement(By.id(prop.getProperty("size_id"))));
		select.selectByVisibleText("5001-10000");
		
		reportPass("Form filled");
		
		
		driver.findElement(By.xpath(prop.getProperty("submit_xpath"))).click();
		
		//Switching iframe
		driver.switchTo().frame(2);
		System.out.println("frame changed");
		//Explicit wait of 1 second
		WebDriverWait wait = new WebDriverWait(driver, 1);
		while(true) {
			//Handling alert
			try {
				if(wait.until(ExpectedConditions.alertIsPresent())!=null) {
					reportFail(driver.switchTo().alert().getText());
					System.out.println("****************************Alert********************************");
					System.out.println(driver.switchTo().alert().getText());
					driver.switchTo().alert().accept();
					break;
				}
			} catch (org.openqa.selenium.TimeoutException ex) {
				
				
			}
			//switch back iframe to default 
			driver.switchTo().defaultContent();
			if(driver.findElement(By.id("thankyou-section")).isDisplayed()) {
				reportPass("Form Submitted");
				System.out.println("Test Passed");
				break;
			}
			//switch to iframe
			driver.switchTo().frame(2);
		}
		
		
//		if(wait.until(ExpectedConditions.alertIsPresent())!=null) {
//			System.out.println("****************************Alert********************************");
//			System.out.println(driver.switchTo().alert().getText());
//			reportFail(driver.switchTo().alert().getText());
//		}
//		else {
//			reportPass("Form Submitted");
//		}
		
	}
}
