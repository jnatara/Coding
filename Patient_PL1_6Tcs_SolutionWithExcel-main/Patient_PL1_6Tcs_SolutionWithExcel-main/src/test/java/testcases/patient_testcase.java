package testcases;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import coreUtilities.testutils.ApiHelper;
import coreUtilities.utils.FileOperations;
import pages.StartupPage;
import pages.patient_Pages;
import testBase.AppTestBase;
import testdata.LocatorsFactory;

public class patient_testcase extends AppTestBase
{
	Map<String, String> configData;
	Map<String, String> loginCredentials;
	String expectedDataFilePath = testDataFilePath + "expected_data.xlsx";
	String loginFilePath = loginDataFilePath + "Login.xlsx";
	StartupPage startupPage;
	LocatorsFactory locatorsFactoryInstance;
	patient_Pages patient_PagesInstance;


	@Parameters({"browser", "environment"})
	@BeforeClass(alwaysRun = true)
	public void initBrowser(String browser, String environment) throws Exception {
		configData = new FileOperations().readExcelPOI(config_filePath, environment);
		configData.put("url", configData.get("url").replaceAll("[\\\\]", ""));
		configData.put("browser", browser);

		boolean isValidUrl = new ApiHelper().isValidUrl(configData.get("url"));
		Assert.assertTrue(isValidUrl, configData.get("url")+" might be Server down at this moment. Please try after sometime.");
		initialize(configData);
		startupPage = new StartupPage(driver);
	}

	@Test(priority = 1, groups = {"sanity"}, description="* Navigate to the URL.\r\n"
			+ "* Retrieve Title and URL of the current page.\r\n"
			+ "* Verify Title & URL: Check if the title  & URL matches the expected title.")
	public void verifyTitleAndURLOfTheHomePage() throws Exception {
		patient_PagesInstance = new patient_Pages(driver);
		locatorsFactoryInstance = new LocatorsFactory(driver);

		Map<String, String> loginData = new FileOperations().readExcelPOI(loginFilePath, "credentials");
		Assert.assertTrue(patient_PagesInstance.loginToHealthAppByGivenValidCredetial(loginData),"Login failed, Invalid credentials ! Please check manually");

		Map<String, String> expectedData = new FileOperations().readExcelPOI(expectedDataFilePath, "healthApp");
		Assert.assertEquals(patient_PagesInstance.verifyTitleOfThePage(),expectedData.get("dasboardTitle")) ;
		Assert.assertEquals(patient_PagesInstance.verifyURLOfThePage(),expectedData.get("homePageUrl")) ;
		Assert.assertTrue(locatorsFactoryInstance.verifyPatientModuleIsPresent(driver).isDisplayed(), "Patient Module is not present in the current page, Please check manually");
	}

	@Test(priority = 2, groups = {"sanity"}, description="Verify that Patient module is present or not ?\r\n"
			+ "If Present, then expand the Patient module\r\n"
			+ "and verify all presence of sub mudules under the patient module.")
	public void verifyAllPresenceOfFieldIfPatientModuleIsPresent() throws Exception {
		patient_PagesInstance = new patient_Pages(driver);
		locatorsFactoryInstance = new LocatorsFactory(driver);

		Assert.assertTrue(patient_PagesInstance.verifyAllPresenceOfFieldIfPatientModuleIsPresent(), "Any of the elememt is not present in pages class, please check manually");
		Assert.assertTrue(locatorsFactoryInstance.verifyPrintIsPresent(driver).isDisplayed(), "Print Button is not present in the Current(Locators) page, Please check manually");
	}

	@Test(priority = 3, groups = {"sanity"}, description="On the \"Patient\" Module's \"Search Patient\" page,\r\n"
			+ "verify & clicking on the \"Search (Minimum 3 Character)\" textbox\r\n"
			+ "and get the Placeholder name of \"Search (Minimum 3 Character)\" textbox.\r\n"
			+ "Then verify the Placeholder name is \"Search (Minimum 3 Character)\".")
	public void verifyPlaceholderNameOfTexbox() throws Exception {
		patient_PagesInstance = new patient_Pages(driver);
		locatorsFactoryInstance = new LocatorsFactory(driver);

		Map<String, String> expectedData = new FileOperations().readExcelPOI(expectedDataFilePath, "patientModule");
		Assert.assertEquals(patient_PagesInstance.verifyPlaceholderNameOfTexbox(),expectedData.get("placeholderNameOfSearchTextbox"), "" 
				+ "placeholder is not matching with expected result, Error in Pages Classes, Please check manualy!");
		Assert.assertTrue(locatorsFactoryInstance.verifySearchPatientTextboxIsPresent(driver).isDisplayed(), "" +
				"Search patient" + "Textbox is not present in the current page , Error in locators Classes Please check manually");
	}

	@Test(priority = 4, groups = {"sanity"}, description="On the \"Patient\" Module's,\r\n"
			+ "clicking on \"Register Patient\" sub module\r\n"
			+ "and verify that the \"+ New Photo\" button is present or not ?\r\n"
			+ "After validation of \"+ New Photo\" button,\r\n"
			+ "then click on it.\r\n"
			+ "Then verify \"Take A Snapshot\" button is present or not?")
	public void verifyTakeASnapshotButtonIsPresent() throws Exception {
		patient_PagesInstance = new patient_Pages(driver);
		locatorsFactoryInstance = new LocatorsFactory(driver);

		Assert.assertTrue(patient_PagesInstance.verifyTakeASnapshotButtonIsPresent(), "button is not present in current page," + 
				"Error in Pages Classes, Please check manualy!");
		Assert.assertTrue(locatorsFactoryInstance.verifyNewPhotoButtonIsPresent(driver).isDisplayed(), "New Photo Button is not present in the Current page, " + 
				"Error in locators Classes Please check manually");
	}

	@Test(priority = 5, groups = {"sanity"}, description="On the \"Patient\" Module's \"Register Patient\" sub module,\r\n"
			+ "directly clicking the \"Register Patient\" button\r\n"
			+ "from the \"Basic Information\" form\r\n"
			+ "without filling any information\r\n"
			+ "and validate the error message\r\n"
			+ "from \"Phone Number\" textbox from the Besic information form.\r\n"
			+ "Error Message : Primary Phone is required")
	public void verifyErrorMessage() throws Exception {
		patient_PagesInstance = new patient_Pages(driver);
		locatorsFactoryInstance = new LocatorsFactory(driver);

		Map<String, String> expectedData = new FileOperations().readExcelPOI(expectedDataFilePath, "patientModule");
		Assert.assertEquals(patient_PagesInstance.verifyErrorMessage(), expectedData.get("errorMessageOfPhonoNumberTextbox"), "" + 
				"Error Message is not present in current page" + "Error in Pages Classes, Please check manualy!") ;
		Assert.assertTrue(locatorsFactoryInstance.verifyErrorMessageOfPhoneNumberTextbox(driver).isDisplayed(), "" + 
				"Error Message is not present in the current page, Error in locators Classes Please check manually");
	}
	
	@Test(priority = 6, groups = {"sanity"}, description="On the \"Basic Information\" form of  \"Register Patient\" sub module,\r\n"
			+ "Fill the following textbox which are present inside the  \"Basic Information\" form.\r\n"
			+ "Validate the entered values.\r\n"
			+ "Following textboxes are :\r\n"
			+ "-> First Name  Textbox\r\n"
			+ "-> Middle Name Textbox\r\n"
			+ "-> Last Name Textbox\r\n"
			+ "-> Age Textbox\r\n"
			+ "-> Phone Number Textbox\r\n"
			+ "Note : read & write the data from the JSON ")
	public void verifyTextboxIsPresentAndValidateEnteredValue() throws Exception {
		patient_PagesInstance = new patient_Pages(driver);
		locatorsFactoryInstance = new LocatorsFactory(driver);
		Map<String, String> expectedData = new FileOperations().readExcelPOI(expectedDataFilePath, "patientModule");

		Assert.assertEquals(patient_PagesInstance.verifyTextboxIsPresentAndValidateEnteredValue(expectedData),expectedData.get("firstNameValue"), "" +
				"Element is not present in current page" + "Error in Pages Classes, Please check manualy!") ;
		Assert.assertEquals(locatorsFactoryInstance.verifyValueIsPresentInFirstNameTextbox(),expectedData.get("firstNameValue"), "" +
				"Element is not present in the current page, Error in locators Classes Please check manually") ;
	}
	
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		System.out.println("before closing the browser");
		browserTearDown();
	}

	@AfterMethod
	public void retryIfTestFails() throws Exception {
		startupPage.navigateToUrl(configData.get("url"));
	}
}
