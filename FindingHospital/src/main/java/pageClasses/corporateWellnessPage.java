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
		driver.findElement(By.id(prop.getProperty("phone_id"))).sendKeys("9874563210");
		
		Select select = new Select(driver.findElement(By.id(prop.getProperty("size_id"))));
		select.selectByVisibleText("5001-10000");
		
		reportPass("Form filled");
		
		driver.findElement(By.xpath(prop.getProperty("submit_xpath"))).click();
		WebDriverWait wait = new WebDriverWait(driver, 60);
		if(wait.until(ExpectedConditions.alertIsPresent())!=null) {
			System.out.println("****************************Alert********************************");
			System.out.println(driver.switchTo().alert().getText());
		}
	}
}
