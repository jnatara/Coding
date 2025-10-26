package testcases;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import pages.medicalRecord_page;
import testBase.AppTestBase;
import testBase.UserActions;
import testdata.LocatorsFactory;

public class medicalRecord_testcase extends AppTestBase {

	Map<String, String> configData;
	Map<String, String> loginCredentials;
	String expectedDataFilePath = testDataFilePath + "expected_data.xlsx";
	String loginFilePath = loginDataFilePath + "Login.xlsx";
	StartupPage startupPage;
	String randomInvoiceNumber;
	LocatorsFactory locatorsFactoryInstance;
	UserActions userActionsInstance;
	medicalRecord_page medicalRecord_pageInstance;

	@Parameters({ "browser", "environment" })
	@BeforeClass(alwaysRun = true)
	public void initBrowser(String browser, String environment) throws Exception {
		configData = new FileOperations().readExcelPOI(config_filePath, environment);
		configData.put("url", configData.get("url").replaceAll("[\\\\]", ""));
		configData.put("browser", browser);
		boolean isValidUrl = new ApiHelper().isValidUrl(configData.get("url"));
		Assert.assertTrue(isValidUrl,
				configData.get("url") + " might be Server down at this moment. Please try after sometime.");
		initialize(configData);
		startupPage = new StartupPage(driver);
	}

	@Test(priority = 1, groups = { "sanity" }, description = "1. Login in the healthapp application\r\n"
			+ "2. Scroll down menu till medicalRecord\r\n" + "3. Click on the medicalRecord")
	public void verifymedicalRecordModule() throws Exception {
		medicalRecord_pageInstance = new medicalRecord_page(driver);
		Map<String, String> medicalRecordExpectedData = new FileOperations().readExcelPOI(expectedDataFilePath,
				"medicalRecord");
		Map<String, String> loginData = new FileOperations().readExcelPOI(loginFilePath, "credentials");
		Assert.assertTrue(medicalRecord_pageInstance.loginToHealthAppByGivenValidCredetial(loginData),
				"Login failed, Invalid credentials ! Please check manually");
		medicalRecord_pageInstance.visitMedicalRecordTab();
		System.out.println("Verification Page url : " + medicalRecordExpectedData.get("URL"));
		Assert.assertEquals(medicalRecord_pageInstance.verifyMedicalRecordPageUrl(),
				medicalRecordExpectedData.get("URL"));
	}

	@Test(priority = 2, groups = { "sanity" }, description = "Pre condition: User should be logged in \r\n"
			+ "1. Click on the Medical Records Module drop-down arrow"
			+ "2. All sub-modules should be displayed correctly.\r\n"
			+ " Expected Sub modules are : MR Outpatient List, MR Inpatient List,  Birth List, Death List, Reports, Emergency Patient List  ")
	public void verifyMedicalRecordSubModules() throws Exception {
		medicalRecord_pageInstance = new medicalRecord_page(driver);
		locatorsFactoryInstance = new LocatorsFactory(driver);

		Assert.assertTrue(medicalRecord_pageInstance.highlightAndVerifyWhetherElementIsDisplayed(
				locatorsFactoryInstance.getPageBarFixedLocator("MR Outpatient List")));
		Assert.assertTrue(medicalRecord_pageInstance.highlightAndVerifyWhetherElementIsDisplayed(
				locatorsFactoryInstance.getPageBarFixedLocator("MR Inpatient List")));
		Assert.assertTrue(medicalRecord_pageInstance.highlightAndVerifyWhetherElementIsDisplayed(
				locatorsFactoryInstance.getPageBarFixedLocator("Birth List")));
		Assert.assertTrue(medicalRecord_pageInstance.highlightAndVerifyWhetherElementIsDisplayed(
				locatorsFactoryInstance.getPageBarFixedLocator("Death List")));
		Assert.assertTrue(medicalRecord_pageInstance.highlightAndVerifyWhetherElementIsDisplayed(
				locatorsFactoryInstance.getPageBarFixedLocator("Reports")));
		Assert.assertTrue(medicalRecord_pageInstance.highlightAndVerifyWhetherElementIsDisplayed(
				locatorsFactoryInstance.getPageBarFixedLocator("Emergency Patient List")));
	}

	@Test(priority = 3, groups = {
			"sanity" }, description = "Pre condition: User should be logged in and it is on medicalRecord module\r\n"
					+ "1. Click on the medicalRecords module drop-down arrow \r\n"
					+ "2. Click on MR Outpatient section")

	public void verifyMROutpatientComponents() throws Exception {
		medicalRecord_pageInstance = new medicalRecord_page(driver);
		
		medicalRecord_pageInstance.clickAnchorButtonByText("MR Outpatient List");
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String toDate = currentDate.format(formatter);
		Thread.sleep(3000);
		medicalRecord_pageInstance.applyDateFilter("01-01-2023", toDate);

		Assert.assertTrue(medicalRecord_pageInstance.verifyIfInputFieldsDropdownsAndCheckboxesAreVisibleOrNot());
	}

	@Test(priority = 4, groups = {
			"sanity" }, description = "Pre condition: User should be logged in and it is on Medical Records module\r\n"
					+ "1. Click on the MR Outpatient  \r\n" + "2. Click on the Reports\r\n"
					+ "3. Click on the Birth List\r\n" + "4. Click on the  Death List\r\n"
					+ "5. Click on the Emergency Patient List  \r\n" + "6. Clcik on the MR Inpatient List"
					+ "6. User should navigate to the all section from the MR Outpatient  section and should navigate back to MR Outpatient section ")
	public void verifyNavigationBetweenSubmodules() throws Exception {
		medicalRecord_pageInstance = new medicalRecord_page(driver);
		Assert.assertTrue(medicalRecord_pageInstance.verifyUrlContains("MR Outpatient List", "OutpatientList"));
		Assert.assertTrue(medicalRecord_pageInstance.verifyUrlContains("Reports", "ReportList"));
		Assert.assertTrue(medicalRecord_pageInstance.verifyUrlContains("Birth List", "BirthList"));
		Assert.assertTrue(medicalRecord_pageInstance.verifyUrlContains("Death List", "DeathList"));
		Assert.assertTrue(
				medicalRecord_pageInstance.verifyUrlContains("Emergency Patient List", "EmergencyPatientList"));
		Assert.assertTrue(medicalRecord_pageInstance.verifyUrlContains("MR Inpatient List", "InpatientList"));
	}

	

	@Test(priority = 5, groups = {
			"sanity" }, description = "Pre condition: User should be logged in and it is on MR Outpatient section \r\n"
					+ "1. Click on the \"From\" date\r\n" + "2. Select the \"Jan 2020\" date\r\n"
					+ "3. Click on the \"To\" date\r\n" + "4. Select \"July 2024\" date\r\n"
					+ "5. Click on \"OK\" button")
	public void verifyResultWithinSpecifiedDateRange() throws Exception {
		medicalRecord_pageInstance = new medicalRecord_page(driver);
		
		Assert.assertTrue(medicalRecord_pageInstance.verifyUrlContains("MR Outpatient List", "OutpatientList"));
		Assert.assertTrue(medicalRecord_pageInstance.applyDateFilter("01-01-2020", "01-01-2024"));
		Assert.assertTrue(
				medicalRecord_pageInstance.verifyResultsAppointmentDateFallsWithin("01-01-2020", "01-01-2024"));
	}


	@Test(priority = 6, groups = {
			"sanity" }, description = "Pre condition: User should be logged in and it is on MR Outpatient section\r\n"
					+ "1. select \"Cardiology\"  in select disease category drop down \r\n" + "")
	public void verifyDepartmentFilterFunctionality() throws Exception {
		medicalRecord_pageInstance = new medicalRecord_page(driver);

		Assert.assertTrue(medicalRecord_pageInstance.applyDepartmentFilterAndVerifyResults("Cardiology"));
	}
	


	@Test(priority = 7, groups = {
		"sanity" }, description = "Pre condition: User should be logged in and it is on MR Outpatient section \r\n"
				+ "1. Click on the data range button\r\n" + "2. select \"one week\" option from the drop down\r\n"
				+ "3. Click on \"OK\" button")
	public void verifyResultWithOneWeekDateRange() throws Exception {
	medicalRecord_pageInstance = new medicalRecord_page(driver);
		Assert.assertTrue(medicalRecord_pageInstance.verifyUrlContains("MR Outpatient List", "OutpatientList"));
		Assert.assertTrue(medicalRecord_pageInstance.clickDateRangeDropdownAndSelect("Last 1 Week"));
		LocalDate currentDate = LocalDate.now();
		LocalDate date7DaysAgo = currentDate.minusDays(7);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String toDate = currentDate.format(formatter);
		String fromDate = date7DaysAgo.format(formatter);
		Thread.sleep(3000);
		Assert.assertTrue(medicalRecord_pageInstance.verifyResultsAppointmentDateFallsWithin(fromDate, toDate));
	}


	@Test(priority = 8, groups = { "sanity" }, description = "1. Login in the healthapp application\r\n"
		+ "2. Scroll down menu till medicalRecords\r\n" + "3. Navigate to the Medical record  module\r\n"
		+ "4. Click on MR Outpatient sub module\r\n" + "5.Select \"Jan 2024\" from the \"From\" field\r\n"
		+ "6. Select \"August 2024\" from the \"To\" field" + "7. Scroll  all the way to the botton of the page\r\n"
		+ "8. Click on \"Next\" button\r\n")
	
	public void verifyTheFunctionalityOfNextPageNavigator() throws Exception {
		medicalRecord_pageInstance = new medicalRecord_page(driver);
		locatorsFactoryInstance = new LocatorsFactory(driver);
		 
		userActionsInstance = new UserActions(driver);
		
		userActionsInstance.click(locatorsFactoryInstance.getAnchorTagLocatorByText("MR Inpatient List"));
		userActionsInstance.click(locatorsFactoryInstance.getAnchorTagLocatorByText("MR Outpatient List"));
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String toDate = currentDate.format(formatter);
		Assert.assertTrue(medicalRecord_pageInstance.applyDateFilter("01-01-2023", toDate));
		Assert.assertTrue(medicalRecord_pageInstance.scrollAllTheWayDown());
		Thread.sleep(1000);
		Assert.assertTrue(medicalRecord_pageInstance.verifyCurrentPageIs("1"));
		Assert.assertTrue(medicalRecord_pageInstance.clickButtonByText("Next"));
		Assert.assertTrue(medicalRecord_pageInstance.verifyCurrentPageIs("2"));
		Assert.assertTrue(medicalRecord_pageInstance.clickButtonByText("Previous"));
		
	}

	@Test(priority = 9, groups = {
	"sanity" }, description = "Pre condition: User should be logged in and it is on MR Outpatient section\r\n"
			+ "1. Enter the \"2B72\" in the \" select disease\" field \r\n"
			+ "2. select \"Malignant neoplasms of stomach\"  in select disease category drop down "
			+ "3. Data should be present as per the selected Diagnosis from the dropdown\r\n"
			+ "Ensure that the \"ICD Code\" column is correctly updated based on changes in the selected diagnosis.\r\n"
			+ "ICD CODE -> \"2B72\"")
	
	public void verifyDataisFilteredAccordingToSelectedDiagnosis() throws Exception {
		medicalRecord_pageInstance = new medicalRecord_page(driver);
		
		Map<String, String> medicalRecordExpectedData = new FileOperations().readExcelPOI(expectedDataFilePath,
			"medicalRecord");
		
		Assert.assertTrue(medicalRecord_pageInstance.verifyDataIsFilteredAccordingToSelectedDiagnosis(
			medicalRecordExpectedData.get("diagnosisCode"), medicalRecordExpectedData.get("finalDiagValue")));
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
