package pages;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class medicalRecord_page extends StartupPage {

//	TC-1 Locators
	public By getUsernameTextfieldLocator = By.id("username_id");
	public By getPasswordTextboxLocator = By.xpath("//input[@id='password']");
	public By getSignInButtonLocator = By.xpath("//button[@id='login']");
	public By getMedicalRecordLocator = By.xpath("//a[@href='#/Medical-records']");
//	TC-2 Locators
//	Write the required locators here
//	TC-3 Locators	
	public By getAnchorTagLocatorMROutpatientList = By.xpath("//a[contains(text(),'" + "MR Outpatient List" + "')]");
	public By calendarFromDropdown = By.xpath("(//input[@id='date'])[1]");
	public By calendarToDropdown = By.xpath("(//input[@id='date'])[2]");
	public By searchBarId = By.id("quickFilterInput");
	public By getButtonLocatorsFirst = By.xpath("//button[contains(text(),'" + "First" + "')]");
	public By getButtonLocatorsPrevious = By.xpath("//button[contains(text(),'" + "Previous" + "')]");
	public By getButtonLocatorsNext = By.xpath("//button[contains(text(),'" + "Next" + "')]");
	public By getButtonLocatorsLast = By.xpath("//button[contains(text(),'" + "Last" + "')]");
//	TC-4 Locators
	public By getAnchorTagLocatorReports = By.xpath("//a[contains(text(),'" + "Reports" + "')]");
	public By getAnchorTagLocatorBirthList = By.xpath("//a[contains(text(),'" + "Birth List" + "')]");
	public By getAnchorTagLocatorDeathList = By.xpath("//a[contains(text(),'" + "Death List" + "')]");
	public By getAnchorTagLocatorEmergencyPatientList = By.xpath("//a[contains(text(),'" + "Emergency Patient List" + "')]");
	public By getAnchorTagLocatorMRInpatientList = By.xpath("//a[contains(text(),'" + "MR Inpatient List" + "')]");
//	Write the required locators here
//	TC-5 Locators
	public By getRowsOfResult = By.xpath("//div[not(contains(@class,'hidden'))]/div[@row-id]");
//	TC-6 Locators
	public By getActualAppointmentDates = By.xpath("//div[@role='gridcell' and @col-id='VisitDate']");
	public By getDepartmentFilterDropdown = By.cssSelector("select#departmentlist");	
	public By getActualDepartmentsInResult = By.xpath("//div[@role='gridcell' and @col-id='DepartmentName']");
	public By getDateRangeButton = By.cssSelector("td [data-hover='dropdown']");
//TC-7 Locators
	public By getAnchorTagLocatorLast1Week = By.xpath("//a[contains(text(),'" + "Last 1 Week" + "')]");	
//	TC-8 Locators
	public By getCurrentPage = By.xpath("//span[@ref='lbCurrent']");
//	TC-9 Locators	
	public By getDiagnosisDropdownLocator = By.cssSelector("input[placeholder='Select ICD-11(s)']");
	public By getButtonLocatorOK = By.xpath("//button[contains(text(),'" + "OK" + "')]");
	public By getFinalDiagTextLocator = By.xpath("//div[contains(@col-id,'FinalDiagnosis_1')]");
	


	public medicalRecord_page(WebDriver driver) {
		super(driver);
	}

	/**
	 * @Test1.1 about this method loginTohealthAppByGivenValidCredetial()
	 * 
	 * @param : Map<String, String>
	 * @description : fill usernameTextbox & passwordTextbox and click on sign in
	 *              button
	 * @return : Boolean
	 * @author : Yaksha
	 */
	public boolean loginToHealthAppByGivenValidCredetial(Map<String, String> expectedData) throws Exception {
		Boolean textIsDisplayed = false;
		try {
			WebElement usernametextFieldWebElement = commonEvents.findElement(getUsernameTextfieldLocator);
			commonEvents.highlightElement(usernametextFieldWebElement);
			commonEvents.sendKeys(getUsernameTextfieldLocator, expectedData.get("username"));

			WebElement passwordtextFieldWebElement = commonEvents.findElement(getPasswordTextboxLocator);
			commonEvents.highlightElement(passwordtextFieldWebElement);
			commonEvents.sendKeys(getPasswordTextboxLocator, expectedData.get("password"));

			WebElement signinButtonWebElement = commonEvents.findElement(getPasswordTextboxLocator);
			commonEvents.highlightElement(signinButtonWebElement);
			commonEvents.click(getSignInButtonLocator);
			textIsDisplayed = true;
		} catch (Exception e) {
			throw e;
		}
		return textIsDisplayed;
	}
	
	/**
	 * @Test1.2 about this method visitMedicalRecordTab()
	 * 
	 * @param : null
	 * @description : verify the medicalRecord tab and click it
	 * @return : String
	 * @author : YAKSHA
	 */
	public void visitMedicalRecordTab() throws Exception {
		try {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			WebElement medicalRecordTab = commonEvents.findElement(getMedicalRecordLocator);
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", medicalRecordTab);
			jsExecutor.executeScript("window.scrollBy(0, -50)");
			commonEvents.highlight(medicalRecordTab);
			commonEvents.click(medicalRecordTab);

			// Wait for the URL to contain "Medical-records/InpatientList"
			commonEvents.waitForUrlContains("Medical-records/InpatientList", 10);
		} catch (Exception e) {
			throw e;
		}
	}
	

	/**
	 * @Test1.3 about this method verifyMedicalRecordPageUrl()
	 * 
	 * @param : null
	 * @description : verify medicalRecord page url
	 * @return : String
	 * @author : YAKSHA
	 */
	public String verifyMedicalRecordPageUrl() throws Exception {
		try {
			String URLToVerify = commonEvents.getCurrentUrl();
			return URLToVerify;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @Test2 about this method highlightAndVerifyWhetherElementIsDisplayed
	 * 
	 * @param element : By - Locator of the element to be highlighted and verified
	 * @description : This method verifies whether an element is displayed on the
	 *              page, highlights it if displayed, and returns true if displayed.
	 * @return : boolean - true if the element is displayed, otherwise false
	 * @author : YAKSHA
	 */
	public boolean highlightAndVerifyWhetherElementIsDisplayed(By element) {
		boolean isElementDisplayed = false;
		try {
			if (commonEvents.isDisplayed(element)) {
				WebElement elementToHighlight = commonEvents.findElement(element);
				commonEvents.highlight(elementToHighlight);
				isElementDisplayed = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isElementDisplayed;
	}



	/**
	 * @Test3.1 about this method clickAnchorButtonByText()
	 * 
	 * @param : null
	 * @description : Clicks Anchor button through its text
	 * @return : Boolean
	 * @author : YAKSHA
	 */
	public boolean clickAnchorButtonByText(String textOfAnchorButton) throws Exception {
		try {
			WebElement buttonToClick = commonEvents.findElement(getAnchorTagLocatorMROutpatientList);
			commonEvents.highlight(buttonToClick).click(buttonToClick);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**@test8.4 , @Test8.6
	 * about this method clickButtonByText()
	 * 
	 * @param buttonText : String - The text of the button to be clicked
	 * @description : This method locates a button using its text and performs a
	 *              click action on it. If the button is found, it is highlighted
	 *              before the click action. In case of failure (e.g., button not
	 *              found or click error), an error message is printed and an
	 *              exception is thrown.
	 * @return : boolean - Returns true if the button is successfully clicked.
	 * @throws : Exception - if there is an issue finding the button or performing
	 *           the click action.
	 * @author : YAKSHA
	 */
	public boolean clickButtonByText(String buttonText) throws Exception {
		try {
			// Locate the button using its text
			WebElement buttonToClick = commonEvents
					.findElement(By.xpath("//button[contains(text(),'" + buttonText + "')]"));

			// Highlight and click the button
			commonEvents.highlight(buttonToClick).click(buttonToClick);

			// Return true if the button is successfully clicked
			return true;
		} catch (Exception e) {
			// Print error message and rethrow exception
			System.out.println("Either the button was not found or encountered an error while clicking!");
			throw e;
		}
	}
	
	/**
	 * @Test3.2, @test5.2, @test8.1 about this method
	 * applyDateFilter()
	 * 
	 * @param : String, String
	 * @description : Applies the date filter with date range
	 * @return : void
	 * @throws : Exception - if there is an issue finding or filling the date fields
	 * @author : YAKSHA
	 */
	public boolean applyDateFilter(String fromDate, String toDate) throws Exception {
		try {
			String fromDay, fromMonth, fromYear, toDay, toMonth, toYear;
			fromDay = fromDate.split("-")[0];
			fromMonth = fromDate.split("-")[1];
			fromYear = fromDate.split("-")[2];
			toDay = toDate.split("-")[0];
			toMonth = toDate.split("-")[1];
			toYear = toDate.split("-")[2];
			WebElement fromDateDropdown = commonEvents.findElement(calendarFromDropdown);
			WebElement toDateDropdown = commonEvents.findElement(calendarToDropdown);
			commonEvents.highlight(fromDateDropdown).sendKeys(fromDateDropdown, fromDay)
					.sendKeys(fromDateDropdown, fromMonth).sendKeys(fromDateDropdown, fromYear);
			commonEvents.highlight(toDateDropdown).sendKeys(toDateDropdown, toDay).sendKeys(toDateDropdown, toMonth)
					.sendKeys(toDateDropdown, toYear);
			clickButtonByText("OK");
		} catch (Exception e) {
			throw e;
		}
		return true;
	}
	
	/**
	 * @Test3.3 about this method
	 * verifyIfInputFieldsDropdownsAndCheckboxesAreVisibleOrNot()
	 * 
	 * @param : null
	 * @description : This method verifies the visibility of various UI components
	 *              on the page, including buttons, input fields, dropdowns, and
	 *              checkboxes.
	 * @return : boolean - Returns true if all specified UI components are
	 *         displayed, otherwise false.
	 * @throws : Exception - if there is an issue finding any of the UI components.
	 * @author : YAKSHA
	 */
	public boolean verifyIfInputFieldsDropdownsAndCheckboxesAreVisibleOrNot() throws Exception {
		boolean areAllFieldsDisplayed = false;
		try {
			// Check the visibility of all required UI components
			if (commonEvents.isDisplayed(getButtonLocatorsFirst)
					&& commonEvents.isDisplayed(getButtonLocatorsPrevious)
					&& commonEvents.isDisplayed(getButtonLocatorsNext)
					&& commonEvents.isDisplayed(getButtonLocatorsLast)
					&& commonEvents.isDisplayed(searchBarId)) {

				// If all components are displayed, set the flag to true
				areAllFieldsDisplayed = true;
			}
		} catch (Exception e) {
			// Throw an exception with a meaningful message if any UI component is not found
			throw new Exception("Failed to verify if all fields are displayed!", e);
		}
		// Return the result of the visibility check
		return areAllFieldsDisplayed;
	}





	/**
	 * @Test4, @test5.1, @Test7.1 about this method
	 *        verifyUrlContains()
	 * 
	 * @param buttonName      : String - The name of the button that will be clicked
	 *                        to navigate to a different URL.
	 * @param urlTextToVerify : String - The partial URL text to verify after
	 *                        clicking the button.
	 * @description : This method locates a button by its text, clicks on it, and
	 *              then checks if the resulting URL contains the specified text. It
	 *              ensures that the correct navigation has occurred.
	 * @return : boolean - Returns true if the URL contains the specified text,
	 *         otherwise false.
	 * @throws : Exception - if there is an issue finding the button, clicking it,
	 *           or verifying the URL.
	 * @author : YAKSHA
	 */
	public boolean verifyUrlContains(String buttonName, String urlTextToVerify) throws Exception {
		try {
			
			if (buttonName == "MR Outpatient List") {
			WebElement buttonToMROutpatientList = commonEvents.findElement(getAnchorTagLocatorMROutpatientList);
			commonEvents.highlight(buttonToMROutpatientList).click(buttonToMROutpatientList);
			commonEvents.waitForUrlContains("OutpatientList", 0);
			return true;
			}
			if (buttonName == "Reports") {
			WebElement buttonToReports = commonEvents.findElement(getAnchorTagLocatorReports);
			commonEvents.highlight(buttonToReports).click(buttonToReports);
			commonEvents.waitForUrlContains("ReportList", 0);
			return true;
			}
			else if (buttonName == "Birth List") {
			WebElement buttonToBirthList = commonEvents.findElement(getAnchorTagLocatorBirthList);
			commonEvents.highlight(buttonToBirthList).click(buttonToBirthList);
			commonEvents.waitForUrlContains("BirthList", 0);
			return true;
			}
			else if (buttonName == "Death List") {
			WebElement buttonToDeathList = commonEvents.findElement(getAnchorTagLocatorDeathList);
			commonEvents.highlight(buttonToDeathList).click(buttonToDeathList);
			commonEvents.waitForUrlContains("DeathList", 0);
			return true;
			}
			else if (buttonName == "Emergency Patient List") {
			WebElement buttonToEmergencyPatientList = commonEvents.findElement(getAnchorTagLocatorEmergencyPatientList);
			commonEvents.highlight(buttonToEmergencyPatientList).click(buttonToEmergencyPatientList);
			commonEvents.waitForUrlContains("EmergencyPatientList", 0);
			return true;
			}
			else if (buttonName == "MR Inpatient List") {
			WebElement buttonToInpatientList = commonEvents.findElement(getAnchorTagLocatorMRInpatientList);
			commonEvents.highlight(buttonToInpatientList).click(buttonToInpatientList);
			commonEvents.waitForUrlContains("InpatientList", 0);
			return true;
			}
			else {
				return false;
			}
		
		} catch (Exception e) {
			// Throw the exception with context if an error occurs
			throw new Exception("Failed to verify that the URL contains the specified text: " + urlTextToVerify, e);
		}
	}



	/**
	 * @Test5.3, @Test7.3 about this method verifyResultsAppointmentDateFallsWithin()
	 * 
	 * @param fromDate : String - The starting date of the range in "dd-MM-yyyy"
	 *                 format.
	 * @param toDate   : String - The ending date of the range in "dd-MM-yyyy"
	 *                 format.
	 * @description : This method verifies that all appointment dates in the result
	 *              fall within the specified date range.
	 * @return : boolean - true if all appointment dates are within the specified
	 *         range, otherwise false.
	 * @throws : Exception - if there is an issue parsing the dates or verifying the
	 *           results.
	 * @author : YAKSHA
	 */
	public boolean verifyResultsAppointmentDateFallsWithin(String fromDate, String toDate) throws Exception {
		try {
			// Define the formatters for input and output date formats
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			// Parse the fromDate and toDate strings to LocalDate objects
			LocalDate from = LocalDate.parse(fromDate, formatter);
			LocalDate to = LocalDate.parse(toDate, formatter);

			// Get the list of appointment dates from the filtered results
			List<WebElement> actualDatesAfterFilterApplied = commonEvents.getWebElements(getActualAppointmentDates);

			// Iterate through each date element to verify it falls within the specified
			// range
			for (WebElement dateElement : actualDatesAfterFilterApplied) {
				commonEvents.highlight(dateElement);
				String dateText = dateElement.getText();

				try {
					// Parse the date from the element text
					LocalDate date = LocalDate.parse(dateText, inputFormatter);
					LocalDate formattedDate = LocalDate.parse(date.format(formatter), formatter);

					// Check if the date is outside the specified range
					if (formattedDate.isBefore(from) || formattedDate.isAfter(to)) {
						return false; // Return false if any date is out of range
					}
				} catch (Exception e) {
					// Log the date parsing error and return false
					System.out.println("Date parsing failed for: " + dateText);
					return false;
				}
			}

			// Return true if all dates are within the range
			return true;

		} catch (Exception e) {
			// Throw a new exception with context if an error occurs
			throw new Exception("Failed to verify appointment dates within the range " + fromDate + " to " + toDate, e);
		}
	}



	/**
	 * @Test6 about this method applyDepartmentFilterAndVerifyResults()
	 * 
	 * @param departmentName - The name of the department to filter by.
	 * @description : This method applies a department filter by department name and
	 *              verifies if the results contain only the selected department.
	 * @return boolean - true if all results contain the selected department name,
	 *         otherwise false.
	 * @throws Exception - if there is an issue finding the dropdown, selecting its
	 *                   values, or verifying the results.
	 * @author : YAKSHA
	 */
	public boolean applyDepartmentFilterAndVerifyResults(String departmentName) throws Exception {
		boolean resultContainsSameDepartment = false;
		try {
			// Navigate to MR Inpatient List and MR Outpatient List
			commonEvents.click(getAnchorTagLocatorMRInpatientList);
			commonEvents.click(getAnchorTagLocatorMROutpatientList);

			// Verify that the URL contains "OutpatientList" to ensure navigation is correct
			Assert.assertTrue(commonEvents.getCurrentUrl().contains("OutpatientList"));

			// Locate and highlight the department filter dropdown
			WebElement departmentFilter = commonEvents.findElement(getDepartmentFilterDropdown);
			commonEvents.highlight(departmentFilter);
			commonEvents.click(departmentFilter);

			// Select the department from the dropdown using the visible text
			Select dropdown = new Select(departmentFilter);
			dropdown.selectByVisibleText(departmentName);

			// Retrieve all department names in the filtered results and verify they match
			// the selected department
			List<WebElement> departmentNamesInResult = commonEvents.getWebElements(getActualDepartmentsInResult);
			for (WebElement departmentNameElement : departmentNamesInResult) {
				commonEvents.highlight(departmentNameElement);
				String departmentNameInCurrentRow = departmentNameElement.getText();

				// If any department name doesn't match the selected department, return false
				if (!departmentNameInCurrentRow.equals(departmentName)) {
					return false;
				}
				resultContainsSameDepartment = true;
			}

			// Return true if all department names match the selected department
			return resultContainsSameDepartment;
		} catch (Exception e) {
			// Throw a new exception with a meaningful message if any error occurs
			throw new Exception("Failed to apply department filter and verify results", e);
		}
	}
	
	/**
	 * @Test7.2 about this method clickDateRangeDropdownAndSelect()
	 * 
	 * @param valueToSelect : String - The text of the value to select from the
	 *                      dropdown.
	 * @description : This method clicks on the date range button, selects a value
	 *              by its text, and verifies if the selection was successful.
	 * @return : boolean - true if the intended value is successfully selected,
	 *         otherwise false.
	 * @throws : Exception - if there is an issue finding the dropdown or its
	 *           values.
	 * @author : YAKSHA
	 */
	public boolean clickDateRangeDropdownAndSelect(String valueToSelect) throws Exception {
		try {
			// Locate the date range dropdown button
			WebElement dateRangeButton = commonEvents.findElement(getDateRangeButton);
			// Highlight and click the date range button to open the dropdown
			commonEvents.highlight(dateRangeButton).click(dateRangeButton);

			// Locate the element with the text value to select
			WebElement valueToSelectElement = commonEvents.findElement(getAnchorTagLocatorLast1Week);
			// Highlight and click the value to select it from the dropdown
			commonEvents.highlight(valueToSelectElement).click(valueToSelectElement);

			// Verify if the value is successfully selected by checking if it has a specific
			// class (e.g., "selected-range")
			boolean isValueSelected = commonEvents.getAttribute(valueToSelectElement, "class")
					.contains("selected-range");

			return isValueSelected;
		} catch (Exception e) {
			// Throw an exception with a meaningful message if an error occurs
			throw new Exception("Failed to select value '" + valueToSelect + "' from date range dropdown", e);
		}
	}
	
	
	
	/**
	 * @Test8.2 about this method scrollAllTheWayDown()
	 * 
	 * @param : null
	 * @description : This method vertically scrolls the screen to the bottom
	 * @return : boolean - true if successfully scrolled down and false if not
	 *         scrolled
	 * @throws : Exception - if there is an issue while scrolling
	 * @author : YAKSHA
	 */
	public boolean scrollAllTheWayDown() throws Exception {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
			return true;

		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * @Test8.3 @Test8.5 about this method verifyCurrentPageIs()
	 * 
	 * @param expectedCurrentPage : String - The expected text that should be
	 *                            present on the current page to verify its
	 *                            correctness.
	 * @description : This method verifies whether the current page matches the
	 *              expected page by checking the text of a specific element. If the
	 *              text matches the expected value, the method returns true,
	 *              otherwise it returns false.
	 * @return : boolean - true if the current page matches the expected page, false
	 *         otherwise.
	 * @throws : Exception - if there is an issue finding the element or performing
	 *           the text comparison.
	 * @author : YAKSHA
	 */
	public boolean verifyCurrentPageIs(String expectedCurrentPage) throws Exception {
		try {
			// Locate the element that holds the current page text
			WebElement currentPageElement = commonEvents.findElement(getCurrentPage);

			// Retrieve the text from the element
			String elementText = commonEvents.getText(currentPageElement);

			// Return true if the text contains the expected page name
			return elementText.contains(expectedCurrentPage);
		} catch (Exception e) {
			// Rethrow any encountered exception
			throw e;
		}
	}
	/**
	 * @Test9 about this method verifyDataIsFilteredAccordingToSelectedDiagnosis()
	 * 
	 * @param diagCode               - The diagnosis code to filter the data.
	 * @param expectedFinalDiagvalue - The expected final diagnosis value to verify
	 *                               against.
	 * @description : This method verifies that the data is filtered according to
	 *              the selected diagnosis code.
	 * @return : boolean - Returns true if the data is correctly filtered, otherwise
	 *         false.
	 * @throws : Exception - If there is an issue finding or interacting with
	 *           elements, a meaningful error message is thrown.
	 * @author : YAKSHA
	 */
	public boolean verifyDataIsFilteredAccordingToSelectedDiagnosis(String diagCode, String expectedFinalDiagvalue) {
		boolean isDataFiltered = false;
		try {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			WebElement inPatientListTab = commonEvents.findElement(getAnchorTagLocatorMRInpatientList);
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", inPatientListTab);
			jsExecutor.executeScript("window.scrollBy(0, -50)");

			// Navigate to MR Inpatient List and MR Outpatient List
			commonEvents.click(getAnchorTagLocatorMRInpatientList);
			commonEvents.click(getAnchorTagLocatorMROutpatientList);

			WebElement selectDiagnosis = commonEvents.findElement(getDiagnosisDropdownLocator);
			commonEvents.highlight(selectDiagnosis).click(selectDiagnosis).sendKeys(selectDiagnosis, diagCode);
			commonEvents.sendKeys(selectDiagnosis, Keys.TAB);

			commonEvents.click(getButtonLocatorOK);

			Thread.sleep(1000);

			List<WebElement> finalDiagElement = commonEvents.getWebElements(getFinalDiagTextLocator);
			for (int i = 1; i < finalDiagElement.size(); i++) {
				String actualFinalDiagvalue = finalDiagElement.get(i).getText();
				actualFinalDiagvalue.equals(expectedFinalDiagvalue);
			}

			isDataFiltered = true;

		} catch (Exception e) {

		}
		return isDataFiltered;
	}

		
}