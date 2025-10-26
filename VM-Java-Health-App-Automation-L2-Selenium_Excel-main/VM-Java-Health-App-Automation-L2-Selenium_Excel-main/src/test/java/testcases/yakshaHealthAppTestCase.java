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
import pages.yakshaHealthAppPages;
import testBase.AppTestBase;
import testdata.LocatorsFactory;

public class yakshaHealthAppTestCase extends AppTestBase
{
	Map<String, String> configData;
	Map<String, String> loginCredentials;
	String expectedDataFilePath = testDataFilePath + "expected_data.xlsx";
	String loginFilePath = loginDataFilePath + "Login.xlsx";
	StartupPage startupPage;
	yakshaHealthAppPages yakshaHealthAppInstance;
	LocatorsFactory locatorsFactoryInstance;


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

	@Test(priority = 1, groups = {"sanity"}, description="Verify the title and url of  the current page.")
	public void verifyTitleOfTheHomePage() throws Exception {

		yakshaHealthAppInstance = new yakshaHealthAppPages(driver);
		locatorsFactoryInstance = new LocatorsFactory(driver);

		Map<String, String> loginData = new FileOperations().readExcelPOI(loginFilePath, "credentials");
		Assert.assertTrue(yakshaHealthAppInstance.loginToHealthAppByGivenValidCredetial(loginData),"Login failed, Invalid credentials ! Please check manually");

		Map<String, String> expectedData = new FileOperations().readExcelPOI(expectedDataFilePath, "healthApp");
		Assert.assertEquals(yakshaHealthAppInstance.verifyTitleOfThePage(),expectedData.get("dasboardTitle")) ;
		Assert.assertEquals(yakshaHealthAppInstance.verifyURLOfThePage(),expectedData.get("pageUrl")) ;
		Assert.assertTrue(locatorsFactoryInstance.totalDoctorTextIsPresent(driver).isDisplayed(), "total doctors text is not present in the current page, Please check manually");
	}

	@Test(priority = 2, groups = {"sanity"}, description="Verify all fields and select counter pop up is present or not and proceed to the next page from the new 1 counter page.")
	public void verifySelectCounterPopupIsPresentAndNavigateToNextpage() throws Exception {
		yakshaHealthAppInstance = new yakshaHealthAppPages(driver);
		locatorsFactoryInstance = new LocatorsFactory(driver);

		Assert.assertTrue(yakshaHealthAppInstance.verifySelectCounterPopupIsPresentAndNavigateToNextpage(), "Any of the elememt is not present, please check manually");
		Assert.assertTrue(locatorsFactoryInstance.addNewButtonIsPresent(driver).isDisplayed(), "total doctors text is not present in the current page, Please check manually");
	}

	@Test(priority = 3, groups = {"sanity"}, description="Perform the keyboard operation to open the  popup and verify that the popup is displayed or not.")
	public void performKeyBoardOperationToOpenAddNewPatientPopup() throws Exception {
		yakshaHealthAppInstance = new yakshaHealthAppPages(driver);
		locatorsFactoryInstance = new LocatorsFactory(driver);

		Map<String, String> expectedData = new FileOperations().readExcelPOI(expectedDataFilePath, "healthApp");
		Assert.assertEquals(yakshaHealthAppInstance.performKeyBoardOperationToOpenAddNewPatientPopup(),expectedData.get("addNewpatientPopupTextValue"), "add New patient is not present, please check manually!") ;
		Assert.assertTrue(locatorsFactoryInstance.registerAndBillingButtonIsPresent(driver).isDisplayed(), "total doctors text is not present in the current page, Please check manually");
	}

	@Test(priority = 4, groups = {"sanity"}, description="Fill all the textfields which are present inside the Add New Patient popup and verify")
	public void fillTheTextFieldInAddNewPatientPopup() throws Exception {
		yakshaHealthAppInstance = new yakshaHealthAppPages(driver);
		locatorsFactoryInstance = new LocatorsFactory(driver);

		Map<String, String> expectedData = new FileOperations().readExcelPOI(expectedDataFilePath, "addNewPatientPopup");
		Assert.assertEquals(yakshaHealthAppInstance.fillTheTextFieldInAddNewPatientPopup(expectedData), expectedData.get("firstName"));
		Assert.assertEquals(locatorsFactoryInstance.verifyFirstNameTextValueIsPresent(), expectedData.get("firstName"));
	}

	@Test(priority = 5, groups = {"sanity"}, description="Check the data which we are entered are present in all the textfields.")
	public void verifyEnteredValueIsPresent() throws Exception {
		yakshaHealthAppInstance = new yakshaHealthAppPages(driver);
		locatorsFactoryInstance = new LocatorsFactory(driver);

		Map<String, String> expectedData = new FileOperations().readExcelPOI(expectedDataFilePath, "addNewPatientPopup");

		Assert.assertEquals(yakshaHealthAppInstance.verifyFirstNameIsPresent(), expectedData.get("firstName"));
		Assert.assertEquals(yakshaHealthAppInstance.verifyMiddleNameIsPresent(), expectedData.get("middleName"));
		Assert.assertEquals(yakshaHealthAppInstance.verifyLastNameIsPresent(), expectedData.get("lastName"));
		Assert.assertEquals(yakshaHealthAppInstance.verifyAgeValueIsPresent(), expectedData.get("age"));
		Assert.assertEquals(yakshaHealthAppInstance.verifyContactNumberIsPresent(), expectedData.get("contact"));

		Assert.assertEquals(locatorsFactoryInstance.verifyRegisterOnlyButtonIsPresent(), expectedData.get("registerOnlyButtonValue"));
	}
	
	@Test(priority = 6, groups = {"sanity"}, description="Simply select India  from the country dropdown menu and validate India is selected or not.")
	public void verifyIndiaIsSelectedFromCountryDropdown() throws Exception {
		yakshaHealthAppInstance = new yakshaHealthAppPages(driver);
		locatorsFactoryInstance = new LocatorsFactory(driver); 

		Map<String, String> expectedData = new FileOperations().readExcelPOI(expectedDataFilePath, "addNewPatientPopup");
		Assert.assertEquals(yakshaHealthAppInstance.verifyIndiaIsSelectedFromCountryDropdown(expectedData), expectedData.get("selectedCountryName"), "selected country is not matching with expected, please check manually!");
		Assert.assertEquals(locatorsFactoryInstance.verifyIndiaIsPresent(), expectedData.get("selectedCountryName"), "selected country is not matching with expected, please check manually!");
	}
	
	@Test(priority = 7, groups = {"sanity"}, description="Verify the error message, when I click on register and billing button without fill any data.")
	public void validateErrorMessageWhenClickOnRegisterBillingButton() throws Exception {
		yakshaHealthAppInstance = new yakshaHealthAppPages(driver);
		locatorsFactoryInstance = new LocatorsFactory(driver);

		Map<String, String> expectedData = new FileOperations().readExcelPOI(expectedDataFilePath, "addNewPatientPopup");
		Assert.assertEquals(yakshaHealthAppInstance.validateErrorMessageWhenClickOnRegisterBillingButton(), expectedData.get("errorMessageOfRegisterBilling"), "Error Message is not matching with expected, please check manually!");
		Assert.assertEquals(locatorsFactoryInstance.verifyErrorMessageIsPresent(), expectedData.get("errorMessageOfRegisterBilling"), "Error Message is not matching with expected, please check manually!");
	}
	
	@Test(priority = 8, groups = {"sanity"}, description="Verify that the dispensary arrow is expanding")
	public void verifyDispensaryArrowIsExpanding() throws Exception {
		yakshaHealthAppInstance = new yakshaHealthAppPages(driver);
		locatorsFactoryInstance = new LocatorsFactory(driver);
		
		Assert.assertTrue(yakshaHealthAppInstance.verifyDispensaryArrowIsExpanding(), "Any of the elememt is not present, please check manually");
		Assert.assertTrue(locatorsFactoryInstance.highLightPresenceOfAllFieldInDispensaryMenu(), "Any of the elememt is not present, please check manually");
	}
	
	@Test(priority = 9, groups = {"sanity"}, description="Verify that the dispensary arrow is minimizing.")
	public void verifyDispensaryArrowIsMinimizing() throws Exception {
		yakshaHealthAppInstance = new yakshaHealthAppPages(driver);
		locatorsFactoryInstance = new LocatorsFactory(driver);
		
		Assert.assertTrue(yakshaHealthAppInstance.verifyDispensaryArrowIsMinimizing(), "Dispensary Arrow element is not present, please check manually");
		Assert.assertTrue(locatorsFactoryInstance.verifyFieldIsNotPresentInDispensaryMenu(), "Any of the elememt is not present, please check manually");
	}
	
	@Test(priority = 10, groups = {"sanity"}, description="Verify that the left navigation menu scroll bar is scrolling from Dispensary module to settings module.")
	public void verifyControlScrollingFromDispensaryToSettingModule() throws Exception {
		yakshaHealthAppInstance = new yakshaHealthAppPages(driver);
		locatorsFactoryInstance = new LocatorsFactory(driver);
		
		Assert.assertTrue(yakshaHealthAppInstance.verifyControlIsScrollingFromDispensaryToSettingModule(), "Any of the elememt is not present, please check manually");
		Assert.assertTrue(locatorsFactoryInstance.settingModuleIsPresent(), "Any of the elememt is not present, please check manually");
	}
	@Test(priority = 11, groups = {"sanity"}, description="Search for a patient name and view the details of the selected patient")
	public void searchForApatientAndViewDetailsOfSelectedPatient() throws Exception {
		yakshaHealthAppInstance = new yakshaHealthAppPages(driver);
		locatorsFactoryInstance = new LocatorsFactory(driver);
		Map<String, String> expectedData = new FileOperations().readExcelPOI(expectedDataFilePath, "YakshaHealthApp");
		Assert.assertTrue(yakshaHealthAppInstance.searchForPatientAndViewDetailsOfSelectedPatient(expectedData), "User is not able to view the details of the patient, please check manually");
		Assert.assertTrue(locatorsFactoryInstance.admittingDocFieldIsPresent(driver).isDisplayed(), "AdmittingDoc field is not present in the current page, Please check manually");
	}
	
	
	@Test(priority = 12, groups = {"sanity"}, description="Take screenshot of Doctor page")
	public void  takeScreenshotOfDoctorPage() throws Exception {
		yakshaHealthAppInstance = new yakshaHealthAppPages(driver);
		locatorsFactoryInstance = new LocatorsFactory(driver);
		Assert.assertTrue(yakshaHealthAppInstance.takeScreenshotOfTheDoctorPage(), "Unable to take the Screenshot , please check manually");	
	    Assert.assertTrue(locatorsFactoryInstance.myFavoritesButtonIsPresent(driver).isDisplayed(), "My Favorites Button is not present in the current page, Please check manually");
	}
	
	@Test(priority = 13, groups = {"sanity"}, description="Deactivate a user or employee by managing the alert popup")
	public void  deactivateUserByManagingTheAlertPopup() throws Exception {
		yakshaHealthAppInstance = new yakshaHealthAppPages(driver);
		locatorsFactoryInstance = new LocatorsFactory(driver);
		Assert.assertTrue(yakshaHealthAppInstance.deactivateUserByManagingAlertPopup(), "Unable to handle the alert popup, please check manually");	
	    Assert.assertTrue(locatorsFactoryInstance.manageRoleTabIsPresent(driver).isDisplayed(), "Manage Role Tab is not present in the current page, Please check manually");
	}
	
	@Test(priority = 14, groups = {"sanity"}, description="Verify tooltip of an element which is present inside the Appointment page.")
	public void  verifyTooltipOfAnElement() throws Exception {
		yakshaHealthAppInstance = new yakshaHealthAppPages(driver);
		locatorsFactoryInstance = new LocatorsFactory(driver);
		Map<String, String> expectedData = new FileOperations().readExcelPOI(expectedDataFilePath, "KeyBoardTooltip");
		Assert.assertEquals(yakshaHealthAppInstance.verifyToolTipOfAnElement(), expectedData.get("TooltipValue"),"Verification failed, please check manually");
	    Assert.assertTrue(locatorsFactoryInstance.tooltipTextIsPresent(driver).isDisplayed(), "Tooltip is not present in the current page, Please check manually");
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
