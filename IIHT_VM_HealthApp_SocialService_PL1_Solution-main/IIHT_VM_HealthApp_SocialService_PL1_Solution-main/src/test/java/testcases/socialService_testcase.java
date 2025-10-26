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
import pages.socialService_Pages;
import testBase.AppTestBase;
import testdata.LocatorsFactory;

public class socialService_testcase extends AppTestBase
{
	Map<String, String> configData;
	Map<String, String> loginCredentials;
	String expectedDataFilePath = testDataFilePath + "expected_data.xlsx";
	String loginFilePath = loginDataFilePath + "Login.xlsx";
	StartupPage startupPage;
	socialService_Pages socialService_PagesInstance;
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
	public void verifyTitleAndURLOfTheHomePage() throws Exception {

		socialService_PagesInstance = new socialService_Pages(driver);
		locatorsFactoryInstance = new LocatorsFactory(driver);

		Map<String, String> loginData = new FileOperations().readExcelPOI(loginFilePath, "credentials");
		Assert.assertTrue(socialService_PagesInstance.loginToHealthAppByGivenValidCredetial(loginData),"Login failed, Invalid credentials ! Please check manually");

		Map<String, String> expectedData = new FileOperations().readExcelPOI(expectedDataFilePath, "healthApp");
		Assert.assertEquals(socialService_PagesInstance.verifyTitleOfThePage(),expectedData.get("dasboardTitle")) ;
		Assert.assertEquals(socialService_PagesInstance.verifyURLOfThePage(),expectedData.get("pageUrl")) ;
		Assert.assertTrue(locatorsFactoryInstance.totalDoctorTextIsPresent(driver).isDisplayed(), "total doctors text is not present in the current page, Please check manually");
	}
	
	@Test(priority = 2, groups = {"sanity"}, description="Verify that SocialService module is present or not ?                                                      \r\n"
			+ "If Social Service Module is present then Then go to SocialService Page.")
	public void verifySocialServiceModuleIsPresentAndGoToSocialServiceTab() throws Exception {
		socialService_PagesInstance = new socialService_Pages(driver);
		locatorsFactoryInstance = new LocatorsFactory(driver);
		Assert.assertTrue(socialService_PagesInstance.verifySocialServiceModuleisPresentAndGoToSocialServiceTab(), "SocialService Module is not present, please check manually");
		Assert.assertTrue(locatorsFactoryInstance.editInformationOfTextFieldIsPresent(driver).isDisplayed(), "Edit Information Of TextField is not present in the current page, Please check manually");
	}
	
	@Test(priority = 3, groups = {"sanity"}, description="Verify that Register New SSU Patient button is present inside the SocialService page\r\n"
			+ "and when we click on \"Register New SSU Patient\" button, it popup \"New SSU Patient Registration\" form.")
	public void verifyRegisterNewSSUPatientButtonisPresentAndValidateFormName() throws Exception {
		socialService_PagesInstance = new socialService_Pages(driver);
		locatorsFactoryInstance = new LocatorsFactory(driver);
		Map<String, String> expectedData = new FileOperations().readExcelPOI(expectedDataFilePath, "socialServiceModuleStringValue");
		Assert.assertEquals(socialService_PagesInstance.verifyRegisterNewSSUPatientButtonisPresentAndValidateFormName(),expectedData.get("newSSUPatientRegistration"), "New SSU Patient Registration Form is not present, please check manually") ;
		Assert.assertTrue(locatorsFactoryInstance.verifyPatientInformationTabIsPresent(driver).isDisplayed(), "Patient Information Tab is not present in the current page, Please check manually");
	}
	
	@Test(priority = 4, groups = {"sanity"}, description="On the \"New SSU Patient Registration\" form,\r\n"
			+ "Validate the error message in Last Name textfield\r\n"
			+ "without filling any data and clicking on \"Register\" Button")
	public void validateErrorMessageInLastNameTextfield() throws Exception {
		socialService_PagesInstance = new socialService_Pages(driver);
		locatorsFactoryInstance = new LocatorsFactory(driver);
		Map<String, String> expectedData = new FileOperations().readExcelPOI(expectedDataFilePath, "healthAppErrorMessages");
		Assert.assertEquals(socialService_PagesInstance.validateErrorMessageInLastNameTextfield(),expectedData.get("LastNameFieldErrorMessage"),"Error message is not present in the current page, Please check manually") ;
		Assert.assertTrue(locatorsFactoryInstance.verifyErrorMessageIsPresentInGenderDropdown(driver).isDisplayed(), "Error message is not present in the current page, Please check manually");
	}
	
	@Test(priority = 5, groups = {"sanity"}, description="On the \"New SSU Patient Registration\" form's validate that First Name,\r\n"
			+ "Middle Name & Last Name textbox are present or not ?\r\n"
			+ "If present then fill the below textbox :\r\n"
			+ "First Name textbox\r\n"
			+ "Middle Name textbox\r\n"
			+ "Last Name textbox \r\n"
			+ "Check the data which we are entered are present in \"First Name\"First Name textbox or not ?")
	public void fillDataInTextfieldsAndVerifyEnteredDataInFirstName() throws Exception {
		socialService_PagesInstance = new socialService_Pages(driver);
		locatorsFactoryInstance = new LocatorsFactory(driver);
		Map<String, String> expectedData = new FileOperations().readExcelPOI(expectedDataFilePath, "NewSSUPatientRegistrationForm");
		Assert.assertEquals(socialService_PagesInstance.fillDataInTextfieldsAndVerifyEnteredDataInFirstName(expectedData), expectedData.get("firstName"),"Any of the field is not present in the current page, Please check manually");
		Assert.assertEquals(locatorsFactoryInstance.verifyValueIsPresentInLastNameTextbox(),expectedData.get("lastName"),"lastName field Text is not present in the current page, Please check manually");
	}
	
	@Test(priority = 6, groups = {"sanity"}, description="scroll to the bottom of the \"New SSU Patient Registration\" form\r\n"
			+ "and verify that \"Register\" button  and \"Close\" button are peresent or not?")
	public void scrollToButtomOfThePageAndVerifyRegisterAndCloseButtonIsPresent() throws Exception {
		socialService_PagesInstance = new socialService_Pages(driver);
		locatorsFactoryInstance = new LocatorsFactory(driver);
		Assert.assertTrue(socialService_PagesInstance.scrollToButtomOfThePageAndVerifyRegisterAndCloseButtonIsPresent(), "Any of the elememt is not present, please check manually");
		Assert.assertTrue(locatorsFactoryInstance.verifyRegisterButtonIsPresent(driver).isDisplayed(), "Print Invoice Button Element is not present in the current page, Please check manually");
	}
	
	@Test(priority = 7, groups = {"sanity"}, description="Validate the warnning message for the membership dropdown.")
	public void validateWarnningMessageOfSSUInformationSection() throws Exception {
		socialService_PagesInstance = new socialService_Pages(driver);
		locatorsFactoryInstance = new LocatorsFactory(driver);
		Map<String, String> expectedData = new FileOperations().readExcelPOI(expectedDataFilePath, "NewSSUPatientRegistrationPopup");
		Assert.assertEquals(socialService_PagesInstance.validateWarnningMessageOfSSUInformationSection(expectedData), expectedData.get("warnningMessage"),"Warning message is not present, please check manually");
		Assert.assertTrue(locatorsFactoryInstance.warningMessageIsPresent(driver).isDisplayed(), "Warning message is not present in the current page, Please check manually");
	}
	
	@Test(priority = 8, groups = {"sanity"}, description="select Yes from the Has target group certificate? dropdown and verify that Target group certificate type and certificate no textfield is present or not ")
	public void verifyTextboxIsPresentBySelectingYesFromHasTargetGroupCertificateDropdown() throws Exception {
		socialService_PagesInstance = new socialService_Pages(driver);
		locatorsFactoryInstance = new LocatorsFactory(driver);
		Map<String, String> expectedData = new FileOperations().readExcelPOI(expectedDataFilePath, "NewSSUPatientRegistrationForm");
		Assert.assertEquals(socialService_PagesInstance.verifyTextboxIsPresentBySelectingYesFromHasTargetGroupCertificateDropdown(expectedData), expectedData.get("HasTargetGroupCertificate?Yes"), " Yes option is not present in that dropdown, please check manually");
		Assert.assertTrue(locatorsFactoryInstance.verifyCertificateNoTextFieldIsPresent(driver).isDisplayed(), "hasTG_certificate Dropdown is not present in the current page, Please check manually");
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
