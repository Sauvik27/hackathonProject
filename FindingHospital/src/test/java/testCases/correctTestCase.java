package testCases;

import java.io.IOException;

import org.testng.annotations.Test;

import baseClass.pageBaseClass;
import pageClasses.corporateWellnessPage;
import pageClasses.diagnosticsPage;
import pageClasses.homePage;
import pageClasses.searchPage;

public class correctTestCase extends pageBaseClass{
	
	homePage homePage;
	searchPage searchPage;
	diagnosticsPage diagnosticsPage;
	corporateWellnessPage corporateWellnessPage;
	
	@Test
	public void submitForm() throws IOException {
		logger = report.createTest("Search for hospitals");
		pageBaseClass pageBaseClass = new pageBaseClass();
		pageBaseClass.invokeBrowser("chrome");
		homePage = pageBaseClass.OpenApplication();
		searchPage = homePage.seachHospitals();
		diagnosticsPage = searchPage.hosptialFilters();
		corporateWellnessPage = diagnosticsPage.majorCitiesSelection();
		corporateWellnessPage.submitForm();
	}
}
