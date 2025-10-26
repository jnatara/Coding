package pages;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class pharmacy_Pages extends StartupPage {

	By usernameTextbox = By.xpath("//input[@id='username_id']");
	By passwordTextbox = By.xpath("//input[@id='password']");
	By signInButton = By.xpath("//button[@id='login']");
	By pharmacyTabXpath = By.xpath("//a[@href='#/Pharmacy']");
	By orderTabXpath = By.xpath("//ul[@class='page-breadcrumb']//a[@href='#/Pharmacy/Order']");
	By supplierTabXpath = By.xpath("//ul[@class=\"page-breadcrumb\"]//a[@href=\"#/Pharmacy/Supplier\"]");
	By reportTabXpath = By.xpath("//ul[@class='page-breadcrumb']//a[@href='#/Pharmacy/Report']");
	By settingTabXpath = By.xpath("//ul[@class='page-breadcrumb']//a[@href='#/Pharmacy/Setting']");
	By storeTabXpath = By.xpath("//ul[@class='page-breadcrumb']//a[@href='#/Pharmacy/Store']");
	By supplierLedgerTabXpath = By.xpath("//ul[@class='page-breadcrumb']//a[@href='#/Pharmacy/SupplierLedger']");
	By substoreRequestAndDispatchTabXpath = By
			.xpath("//ul[@class='page-breadcrumb']//a[@href='#/Pharmacy/SubstoreRequestAndDispatch']");
	By purchaseOrderSubTabXpath = By.xpath("//a[@href='#/Pharmacy/Order/PurchaseOrder']");
	By goodsReceiptSubTabXpath = By.xpath("//a[@href='#/Pharmacy/Order/GoodsReceiptList']");
	By agingDaysFromXpath = By.xpath("//input[@placeholder='From']");
	By agingDaysToXpath = By.xpath("//input[@placeholder='To']");
	public By searchBarId = By.id("quickFilterInput");
	public By supplierNameDropdownXpath = By.xpath("//input[@placeholder='select supplier']");
	public By showDetails = By.xpath("//button[contains(text(),'Show Details')]");
	By calendarFromDropdown = By.xpath("(//input[@id='date'])[1]");
	By calendarToDropdown = By.xpath("(//input[@id='date'])[2]");
	By okButtonXpath = By.xpath("//button[contains(text(),'OK')]");
	By filterDropdownXpath = By.xpath("//div[@class='dropdown']//span[@data-hover='dropdown']");
	By radiButtonCompleteXpath = By.xpath("//input[@value='complete']/../span");
	public By radiButtonCancelledXpath = By.xpath("//input[@value='cancelled']/../span");
	By radiButtonAllXpath = By.xpath("//input[@value='all']/../span");
	By homepageLogo = By.cssSelector("img[class=\"logo-default\"]");
	By printReceiptButtonId = By.id("saveGr");
	public By updateReceiptButtonId = By.id("updateGr");
	By itemNameId = By.id("txt_ItemName");
	By batchNumberId = By.id("txt_BatchNo");
	By quantityId = By.id("ItemQTy");
	By rateId = By.id("GRItemPrice");
	By saveButtonId = By.id("btn_Save");
	By mrpId = By.id("MRP");
	By supplierNameDropdownId = By.id("SupplierName");
	By invoiceNumberFieldId = By.id("InvoiceId");
	By paymentModeDropdownId = By.id("paymentMode");
	By closeModalButtonXpath = By.xpath("//a[@title='Cancel']");
	public By closeModalButtonXpath2 = By.xpath("//a[contains(@class,'history-del-btn')]");
	By expiryDateFieldId = By.id("ExpiryDate");
	By addNewItemModalXpath = By.xpath("(//div[@class='modelbox-div clearfix'])[2]");
	By modalXpath = By.xpath("//div[@class='modelbox-div clearfix']");
	By favouriteOrStarIcon = By.xpath("//i[contains(@class,'icon-favourite')]");
	By goodReceiptTableDataRow = By.cssSelector("div[ref=\"eCenterContainer\"] div[role=\"row\"]");
	public By editGRButton = By.cssSelector("button[id=\"editGR\"]");
	public By cancelGoodsReceiptSButton = By.id("cancelButtonContainer");
	public By cancelRemarks = By.id("CancelRemarks");
	public By proceedButton = By.xpath("//button[contains(text(),'Proceed')]");
	public By editGoodsRecordButton = By.id("editButton0");
	public By cancelledStamp = By.xpath("//b[text()='Cancelled']");
	By actualDatesOfGoodReceiptTableXpath = By
			.xpath("//div[@col-id=\"GoodReceiptDate\"]/span[not(@ref=\"cbSelectAll\")]");
	By gridXpath = By.xpath("//div[@class=\"aggrid-parent-wrapper\"]");
	By actualDatesOfPurchaseOrderTableXpath = By.xpath("//div[@col-id='PODate']/span[not(@ref='cbSelectAll')]");
	public By purchaseOrderXpath = By.xpath("//a[@href='#/Pharmacy/Order/PurchaseOrder']");
	public By patientTabXpath = By.xpath("//a[@href='#/Patient']");
	public By registerPatient = By.xpath("//a[contains(text(),'Register Patient')]");
	public By profilePicture = By.cssSelector("a[title=\"Profile Picture\"]");
	public By newPhoto = By.xpath("//button[contains(text(),'New Photo')]");
	public By chooseFileButton = By.cssSelector("label[for=\"fileFromLocalDisk\"]");
	public By doneButton = By.xpath("//button[text()='Done']");
	public By uploadedImage = By.cssSelector("img[class='ng-star-inserted']");
	public By uploadedProfilePhotoXpath = By.xpath("//button[contains(text(),'New Photo')]/../../img");
	public By exportButton = By.cssSelector("button[title='Export To Excel']");

	private By tabXpath;

	String pageName = this.getClass().getSimpleName();

	public pharmacy_Pages(WebDriver driver) {
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
			WebElement usernametextFieldWebElement = commonEvents.findElement(usernameTextbox);
			commonEvents.highlightElement(usernametextFieldWebElement);
			commonEvents.sendKeys(usernameTextbox, expectedData.get("username"));

			WebElement passwordtextFieldWebElement = commonEvents.findElement(passwordTextbox);
			commonEvents.highlightElement(passwordtextFieldWebElement);
			commonEvents.sendKeys(passwordTextbox, expectedData.get("password"));

			WebElement signinButtonWebElement = commonEvents.findElement(signInButton);
			commonEvents.highlightElement(signinButtonWebElement);
			commonEvents.click(signInButton);

			textIsDisplayed = true;
		} catch (Exception e) {
			throw e;
		}
		return textIsDisplayed;
	}

	/**
	 * @Test1.3 about this method verifyTitleOfThePage()
	 * 
	 * @param : null
	 * @description : it will navigate to the URL and validate the title of the
	 *              current page.
	 * @return : String
	 * @author : YAKSHA
	 */
	public String verifyTitleOfThePage() throws Exception {
		String pageTitle = "";
		try {
			pageTitle = commonEvents.getTitle();
			System.out.println("title of the page is  :" + pageTitle);
		} catch (Exception e) {
			throw e;
		}
		return pageTitle;
	}

	/**
	 * @Test1.4 about this method verifyURLOfThePage()
	 * 
	 * @param : null
	 * @description : it will navigate to the URL and validate the URL of the
	 *              current page.
	 * @return : String
	 * @author : YAKSHA
	 */
	public String verifyURLOfThePage() throws Exception {
		String urlofThepage = "";
		try {
			urlofThepage = commonEvents.getCurrentUrl();
			System.out.println("URL of the page is  :" + urlofThepage);
		} catch (Exception e) {
			throw e;
		}
		return urlofThepage;
	}

	/**
	 * @Test1.2 about this method clickOnHomePageLogo()
	 * 
	 * @param : null
	 * @description : This method finds the homepage logo on the screen. If the logo
	 *              is displayed, it highlights the logo and clicks on it.
	 * @return : void
	 * @author : YAKSHA
	 */
	public void clickOnHomePageLogo() {
		WebElement homePageLogo = commonEvents.findElement(homepageLogo);
		try {
			if (commonEvents.isDisplayed(homePageLogo)) {
				commonEvents.highlight(homePageLogo);
				commonEvents.click(homePageLogo);
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @Test2.1 about this method scrollDownAndClickPharmacyTab()
	 * 
	 * @param : null
	 * @description : verify the pharmacy tab, scroll to it, and click it
	 * @return : String
	 * @author : YAKSHA
	 */
	public void scrollDownAndClickPharmacyTab() throws Exception {
		try {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			WebElement pharmacyTab = commonEvents.findElement(pharmacyTabXpath);
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", pharmacyTab);
			commonEvents.highlight(pharmacyTab);
			commonEvents.click(pharmacyTab);

			// Wait for the URL to contain "Pharmacy/Dashboard"
			commonEvents.waitForUrlContains("Pharmacy/Dashboard", 10);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @Test2.2 about this method getPharmacyPageUrl()
	 * 
	 * @param : null
	 * @description : This method retrieves the current URL of the page to verify if
	 *              the user is on the Pharmacy page.
	 * @return : String - the current URL of the page
	 * @author : YAKSHA
	 */
	public String getPharmacyPageUrl() throws Exception {
		try {
			String titleToVerify = commonEvents.getCurrentUrl();
			return titleToVerify;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @Test3 about this method verifyAndHighlightTab()
	 * @param : String tabName - The name of the tab to verify and highlight
	 * @description : This method locates the tab specified by the tabName parameter
	 *              in the Pharmacy module, highlights it, and returns true if the
	 *              tab is displayed.
	 * @return : boolean - true if the tab is displayed, false otherwise
	 * @throws : Exception - if there is an issue locating or highlighting the tab
	 * @author : YAKSHA
	 */
	public boolean verifyAndHighlightTab(String tabName) throws Exception {
		try {
			tabXpath = By.xpath("//ul[@class='page-breadcrumb']//a[@href='#/Pharmacy/" + tabName + "']");
			WebElement tabToHighlight = commonEvents.findElement(tabXpath);
			commonEvents.highlight(tabToHighlight);
			return tabToHighlight.isDisplayed();
		} catch (Exception e) {
			throw e;
		}
	}

	private void setTabXpath(String tabName) {
		tabXpath = By.xpath("//ul[@class='page-breadcrumb']//a[@href='#/Pharmacy/" + tabName + "']");
	}

	/**
	 * @Test4.1, @Test5.1, @Test6.1 @Test7.1 @Test15.1 @Test15.2 and @Test17.1 about
	 * this method visitOrderTabUnderPharmacy()
	 * 
	 * @param : null
	 * @description : navigates the user to the order screen
	 * @return : void
	 * @author : YAKSHA
	 */
	public void visitTabUnderPharmacy(String tabName) throws Exception {
		try {
			setTabXpath(tabName);
			WebElement orderTab = commonEvents.findElement(tabXpath);
			commonEvents.highlight(orderTab);
			commonEvents.click(orderTab);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @Test4.2 about this method isOrderTabSelected()
	 * 
	 * @param : null
	 * @description : verify whether the order tab is the currently selected tab
	 * @return : boolean
	 * @author : YAKSHA
	 */
	public boolean isOrderTabSelected() throws Exception {
		try {
			WebElement orderTab = commonEvents.findElement(orderTabXpath);
			commonEvents.highlight(orderTab);
			boolean orderTabSelected = orderTab.getAttribute("class").contains("active");
			return orderTabSelected;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @Test4.3 about this method areSubTabsUnderOrderPresent()
	 * 
	 * @param : null
	 * @description : verify all sub tabs under order are present
	 * @return : boolean
	 * @author : YAKSHA
	 */
	public boolean areSubTabsUnderOrderPresent() throws Exception {
		try {
			boolean allSubTabsPresent = false;
			if (commonEvents.isDisplayed(purchaseOrderSubTabXpath) && commonEvents.isDisplayed(goodsReceiptSubTabXpath)) {
				allSubTabsPresent = true;
			}
			return allSubTabsPresent;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @Test4.4 about this method isButtonPresent()
	 * 
	 * @param : String
	 * @description : verify button presence through text
	 * @return : boolean
	 * @author : YAKSHA
	 */
	public boolean isButtonPresent(String buttonText) throws Exception {
		try {
			boolean buttonPresent = commonEvents
					.findElement(By.xpath("//button[contains(text(),'" + buttonText + "')]")).isDisplayed();
			return buttonPresent;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @Test4.5 about this method areAgingDaysFieldsPresent()
	 * 
	 * @param : null
	 * @description : This method verifies if the 'Aging Days From' and 'Aging Days
	 *              To' fields are present and highlighted.
	 * @return : boolean - true if both fields are displayed, false otherwise
	 * @throws : Exception - if there is an issue locating or highlighting the
	 *           fields
	 * @author : YAKSHA
	 */
	public boolean areAgingDaysFieldsPresent() throws Exception {
		try {
			boolean adingDaysFromFieldPresent = false;
			WebElement agingDaysFromField = commonEvents.findElement(agingDaysFromXpath);
			WebElement agingDaysToField = commonEvents.findElement(agingDaysToXpath);
			commonEvents.highlight(agingDaysFromField);
			commonEvents.highlight(agingDaysToField);
			if (agingDaysFromField.isDisplayed() && agingDaysToField.isDisplayed()) {
				adingDaysFromFieldPresent = true;
			}
			return adingDaysFromFieldPresent;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @Test4.6 about this method isSearchBarPresent()
	 * 
	 * @param : null
	 * @description : This method verifies if the search bar is present and
	 *              highlighted on the page.
	 * @return : boolean - true if the search bar is displayed, false otherwise
	 * @throws : Exception - if there is an issue locating or highlighting the
	 *           search bar
	 * @author : YAKSHA
	 */
	public boolean isSearchBarPresent() throws Exception {
		try {
			WebElement searchBar = commonEvents.findElement(searchBarId);
			commonEvents.highlight(searchBar);
			return searchBar.isDisplayed();
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @Test4.7 about this method areDateDropdownsPresent()
	 * 
	 * @param : null
	 * @description : This method verifies if the 'From' and 'To' date dropdowns are
	 *              present and highlighted on the page.
	 * @return : boolean - true if both date dropdowns are displayed, false
	 *         otherwise
	 * @throws : Exception - if there is an issue locating or highlighting the
	 *           dropdowns
	 * @author : YAKSHA
	 */
	public boolean areDateDropdownsPresent() throws Exception {
		try {
			boolean dateDropdownPresent = false;
			WebElement dateDropdownFrom = commonEvents.findElement(calendarFromDropdown);
			WebElement dateDropdownTo = commonEvents.findElement(calendarToDropdown);
			commonEvents.highlight(dateDropdownFrom);
			commonEvents.highlight(dateDropdownTo);
			if (dateDropdownFrom.isDisplayed() && dateDropdownTo.isDisplayed()) {
				dateDropdownPresent = true;
			}
			return dateDropdownPresent;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @Test4.8 about this method isSelectSupplierDropdownPresent()
	 * 
	 * @param : null
	 * @description : This method verifies if the 'Select Supplier' dropdown is
	 *              present and highlighted on the page.
	 * @return : boolean - true if the 'Select Supplier' dropdown is displayed,
	 *         false otherwise
	 * @throws : Exception - if there is an issue locating or highlighting the
	 *           dropdown
	 * @author : YAKSHA
	 */
	public boolean isSelectSupplierDropdownPresent() throws Exception {
		try {
			WebElement selectSupplierDropdown = commonEvents.findElement(supplierNameDropdownXpath);
			commonEvents.highlight(selectSupplierDropdown);
			return selectSupplierDropdown.isDisplayed();
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @Test4.9 about this method isFilterDropdownPresent()
	 * 
	 * @param : null
	 * @description : This method verifies if the filter dropdown is present and
	 *              highlighted on the page.
	 * @return : boolean - true if the filter dropdown is displayed, false otherwise
	 * @throws : Exception - if there is an issue locating or highlighting the
	 *           dropdown
	 * @author : YAKSHA
	 */
	public boolean isFilterDropdownPresent() throws Exception {
		try {
			WebElement filterDropdown = commonEvents.findElement(filterDropdownXpath);
			commonEvents.highlight(filterDropdown);
			return filterDropdown.isDisplayed();
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @Test4.10 about this method areFilterByStatusRadioButtonsPresent()
	 * 
	 * @param : null
	 * @description : This method verifies if the 'Completed', 'Cancelled', and
	 *              'All' radio buttons are present and highlighted on the page.
	 * @return : boolean - true if all three radio buttons are displayed, false
	 *         otherwise
	 * @throws : Exception - if there is an issue locating or highlighting the radio
	 *           buttons
	 * @author : YAKSHA
	 */
	public boolean areFilterByStatusRadioButtonsPresent() throws Exception {
		try {
			boolean radioButtonsPresent = false;
			WebElement radioButtonCompleted = commonEvents.findElement(radiButtonCompleteXpath);
			WebElement radioButtonCancelled = commonEvents.findElement(radiButtonCancelledXpath);
			WebElement radioButtonAll = commonEvents.findElement(radiButtonAllXpath);
			commonEvents.highlight(radioButtonCompleted);
			commonEvents.highlight(radioButtonCancelled);
			commonEvents.highlight(radioButtonAll);
			if (radioButtonCompleted.isDisplayed() && radioButtonCancelled.isDisplayed()
					&& radioButtonAll.isDisplayed()) {
				radioButtonsPresent = true;
			}
			return radioButtonsPresent;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @Test5.2 about this method verifySelectedTabIsActiveOrNot()
	 * 
	 * @param : null
	 * @description : This method verifies if the "Supplier" tab is displayed and
	 *              returns its "class" attribute value. This can be used to
	 *              determine if the tab is active or not based on its class
	 *              attributes.
	 * @return : String - the value of the "class" attribute of the "Supplier" tab
	 *         if it is displayed, an empty string otherwise
	 * @throws : Exception - if there is an issue locating or highlighting the tab,
	 *           or getting its attribute
	 * @author : YAKSHA
	 */
	public String verifySelectedTabIsActiveOrNot() throws Exception {
		String locatorAttributeValue = "";
		try {
			if (commonEvents.isDisplayed(supplierTabXpath)) {
				WebElement supplierTab = commonEvents.findElement(supplierTabXpath);
				commonEvents.highlight(supplierTab);
				locatorAttributeValue = commonEvents.getAttribute(supplierTabXpath, "class");
			}
		} catch (Exception e) {
			throw e;
		}
		return locatorAttributeValue;
	}

	/**
	 * @Test6.2, @Test7.2 and @Test7.3 about this method clickButtonByText()
	 * 
	 * @param buttonText : String - The text of the button to be clicked
	 * @description : This method locates a button using its text and performs a
	 *              click action on it. If the button is found and successfully
	 *              clicked, it highlights the button first. In case of failure
	 *              (e.g., button not found or click error), an error message is
	 *              printed and an exception is thrown.
	 * @return : void
	 * @throws : Exception - if there is an issue finding the button or performing
	 *           the click action
	 * @author : YAKSHA
	 */
	public void clickButtonByText(String buttonText) throws Exception {
		try {
			WebElement buttonToClick = commonEvents
					.findElement(By.xpath("//button[contains(text(),'" + buttonText + "')]"));
			commonEvents.highlight(buttonToClick).click(buttonToClick);
		} catch (Exception e) {
			System.out.println("Either the button not found or encountered error while clicking!");
			throw e;
		}
	}

	/**
	 * @Test6.3 about this method clickPrintReceipt()
	 * 
	 * @param : null
	 * @description : This method locates the "Print Receipt" button using its
	 *              identifier and performs a click action on it. The button is
	 *              highlighted before clicking to ensure visibility. If an
	 *              exception occurs during the finding or clicking of the button,
	 *              it is re-thrown.
	 * @return : void
	 * @throws : Exception - if there is an issue finding the button or performing
	 *           the click action
	 * @author : YAKSHA
	 */
	public void clickPrintReceipt() throws Exception {
		try {
			WebElement printReceiptButton = commonEvents.findElement(printReceiptButtonId);
			commonEvents.highlight(printReceiptButton).click(printReceiptButton);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @Test6.4, @Test7.6 @Test14.6 and @Test16.6 about this method
	 * verifyMessageByText()
	 * 
	 * @param messageText - the text to match within the message element
	 * @description : This method locates a message element on the page by searching
	 *              for a paragraph (`
	 *              <p>
	 *              `) that contains the specified text (`messageText`) or has a
	 *              class `main-message`. It highlights the message element and
	 *              returns its text content. If an exception occurs while finding
	 *              or retrieving the message, it logs an error and re-throws the
	 *              exception.
	 * @return : String - the text content of the located message element
	 * @throws : Exception - if there is an issue finding the message or retrieving
	 *           its text
	 * @author : YAKSHA
	 */
	public String verifyMessageByText(String messageText) throws Exception {
		try {
			WebElement message = commonEvents.findElement(
					By.xpath("//p[contains(@class,'main-message') or contains(text(),'" + messageText + "')]"));
			commonEvents.highlight(message);
			return message.getText();
		} catch (Exception e) {
			System.out.println("Error message didn't appear!");
			throw e;
		}
	}

	/**
	 * @Test6.5, @Test8.6 and @Test10.5 about this method closeAddGoodReceiptModal()
	 * 
	 * @param : null
	 * @description : This method locates the "Close" button of the "Add Good
	 *              Receipt" modal using the provided XPath
	 *              (`closeModalButtonXpath`). It highlights the button and then
	 *              clicks it to close the modal. If an exception occurs while
	 *              finding or interacting with the button, it logs an error and
	 *              re-throws the exception.
	 * @return : void
	 * @throws : Exception - if there is an issue finding or clicking the "Close"
	 *           button
	 * @author : YAKSHA
	 */
	public boolean closeAddGoodReceiptModal() throws Exception {
		boolean isModalClosed = false;
		try {
			WebElement closeAddGoodReceiptModalButton = commonEvents.findElement(closeModalButtonXpath);
			commonEvents.highlight(closeAddGoodReceiptModalButton).click(closeAddGoodReceiptModalButton);
			isModalClosed = true;
			return isModalClosed;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @Test7.4 and @Test14.5 about this method addGriItemWithMandatoryFieldsOnly()
	 * 
	 * @param itemName    - the name of the item to be added
	 * @param batchNumber - the batch number of the item
	 * @param quantity    - the quantity of the item
	 * @param rate        - the rate of the item
	 * @param mrp         - the MRP (Maximum Retail Price) of the item
	 * @description : This method adds an item to the GRI (Goods Receipt Inventory)
	 *              with mandatory fields only. It highlights and enters values for
	 *              item name, batch number, expiry date, quantity, rate, and MRP.
	 *              If any of these fields are not provided, they are skipped. The
	 *              method then clicks the save button. If the modal appears, it
	 *              waits for the modal to fully load.
	 * @return : void
	 * @throws : Exception - if there is any issue locating elements, entering data,
	 *           or clicking the save button
	 * @author : YAKSHA
	 */
	public void addGriItemWithMandatoryFieldsOnly(String itemName, String batchNumber, String quantity, String rate,
			String mrp) throws Exception {
		try {
			WebElement itemNameField = commonEvents.findElement(itemNameId);
			WebElement batchNumberField = commonEvents.findElement(batchNumberId);
			WebElement expiryDateField = commonEvents.findElement(expiryDateFieldId);
			WebElement quantityField = commonEvents.findElement(quantityId);
			WebElement rateField = commonEvents.findElement(rateId);
			WebElement mrpField = commonEvents.findElement(mrpId);
			WebElement saveButton = commonEvents.findElement(saveButtonId);

			// Highlight and enter itemName if it's not null or empty
			if (itemName != null && !itemName.isEmpty()) {
				commonEvents.highlight(itemNameField);
				commonEvents.sendKeys(itemNameField, itemName);
				commonEvents.sendKeys(itemNameField, Keys.ENTER);
			}

			// Highlight and enter batchNumber if it's not null or empty
			if (batchNumber != null && !batchNumber.isEmpty()) {
				commonEvents.highlight(batchNumberField);
				commonEvents.sendKeys(batchNumberField, batchNumber);
			}

			// Highlight and enter expiryDate (hardcoded) if it's not null or empty
			commonEvents.highlight(expiryDateField);
			commonEvents.sendKeys(expiryDateField, "August").sendKeys(expiryDateField, Keys.ARROW_RIGHT)
					.sendKeys(expiryDateField, "2024");

			// Highlight and enter quantity if it's not null or empty
			if (quantity != null && !quantity.isEmpty()) {
				commonEvents.highlight(quantityField);
				commonEvents.sendKeys(quantityField, quantity);
			}

			// Highlight and enter rate if it's not null or empty
			if (rate != null && !rate.isEmpty()) {
				commonEvents.highlight(rateField);
				commonEvents.sendKeys(rateField, rate);
			}

			if (mrp != null && !mrp.isEmpty()) {
				commonEvents.highlight(mrpField);
				commonEvents.sendKeys(mrpField, mrp);
			}

			// Highlight and click saveButton

			commonEvents.highlight(saveButton).click(saveButton);
			System.out.println("Clicked on Save Button!!");

			// Wait for the modal to appear
			if (commonEvents.isDisplayed(modalXpath)) {
				commonEvents.waitTillNumberOfElementsToBe(modalXpath, 5, 1);
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @Test7.5 about this method enterMandatoryDetailsToPrintGoodReceipt()
	 * 
	 * @param invoiceNumber - the invoice number to be entered in the field
	 * @description : This method enters mandatory details required to print a good
	 *              receipt. It locates and highlights the supplier dropdown,
	 *              invoice number field, and payment mode dropdown. It then enters
	 *              the provided invoice number and selects the necessary options
	 *              from the dropdowns before clicking the print receipt button.
	 * @return : void
	 * @throws : Exception - if there is any issue locating elements, entering data,
	 *           or clicking buttons
	 * @author : YAKSHA
	 */
	public void enterMandatoryDetailsToPrintGoodReceipt(String invoiceNumber) throws Exception {
		try {
			WebElement selectSupplierDropdown = commonEvents.findElement(supplierNameDropdownId);
			WebElement invoiceNumberField = commonEvents.findElement(invoiceNumberFieldId);
			WebElement paymentModeDropdown = commonEvents.findElement(paymentModeDropdownId);
			WebElement printReceiptButton = commonEvents.findElement(printReceiptButtonId);
			commonEvents.highlight(selectSupplierDropdown);
			commonEvents.click(selectSupplierDropdown).sendKeys(supplierNameDropdownId, Keys.ENTER);
			commonEvents.highlight(invoiceNumberField);
			commonEvents.click(invoiceNumberField).sendKeys(invoiceNumberField, invoiceNumber);
			commonEvents.highlight(paymentModeDropdown);
			commonEvents.click(paymentModeDropdown).sendKeys(paymentModeDropdownId, Keys.ENTER);
			commonEvents.highlight(selectSupplierDropdown).click(printReceiptButton);

		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @Test8.1 about this method closeModalBySubjectName()
	 * 
	 * @param : String
	 * @description : Closes the modal with the subject name
	 * @return : void
	 * @throws : Exception - if there is an issue finding or clicking the cross
	 *           button
	 * @author : YAKSHA
	 */
	public void closeModalBySubjectName(String modalTitle) throws Exception {
		try {
			WebElement closeModalButton = commonEvents
					.findElement(By.xpath("//h3/span[contains(text(),'" + modalTitle + "')]/../../a[@title='Cancel']"));
			commonEvents.highlight(closeModalButton).click(closeModalButton);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @Test8.4 @Test10.3 @Test14.3 @Test16.3 @Test16.9 about this method
	 * clickViewButtonWithInvoice()
	 * 
	 * @param : String
	 * @description : Clicks on the "view" button next to a particular invoice
	 *              number
	 * @return : void
	 * @throws : Exception - if there is an issue finding or clicking the "view"
	 *           button
	 * @author : YAKSHA
	 */
	public void clickViewButtonWithInvoice(String invoiceNumber) throws Exception {
		try {
			WebElement viewButton = commonEvents.findElement(By.xpath("//div[@col-id='InvoiceNo' and contains(text(),'"
					+ invoiceNumber + "')]/..//a[@danphe-grid-action='view']"));
			commonEvents.highlight(viewButton).click(viewButton);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @Test8.8 @Test10.4 about this method doesPrintContainsInvoiceNumber()
	 * 
	 * @param : String
	 * @description : Verify whether the printed report contains expected invoice
	 *              number
	 * @return : boolean
	 * @throws : Exception - if there is an issue finding or clicking the invoice
	 *           number text button
	 * @author : YAKSHA
	 */
	public boolean doesPrintContainsInvoiceNumber(String invoiceNumber) throws Exception {
		try {
			WebElement actualInvoiceNumber = commonEvents
					.findElement(By.xpath("//p[contains(text(),'Invoice No: ')]/b"));
			commonEvents.highlight(actualInvoiceNumber);
			String actualInvoiceNumberText = actualInvoiceNumber.getText();
			return actualInvoiceNumberText.contains(invoiceNumber);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @Test8.2 @Test10.1 @Test14.1 @Test16.1 about this method
	 * clickAndEnterValueInSearchField()
	 * 
	 * @param : String
	 * @description : Enters value in the search bar
	 * @return : boolean
	 * @throws : Exception - if there is an issue finding or typing the text
	 * @author : YAKSHA
	 */
	public boolean clickAndEnterValueInSearchField(String searchData) {
		boolean isSearchBarDisplayed = false;
		try {
			isSearchBarDisplayed = isSearchBarPresent();
			System.out.println("isSearchBarDisplayed + " + isSearchBarDisplayed);
			if (isSearchBarDisplayed) {
				WebElement searchField = commonEvents.findElement(searchBarId);
				commonEvents.clear(searchBarId);
				commonEvents.highlight(searchField).click(searchBarId).sendKeys(searchBarId, searchData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSearchBarDisplayed;
	}

	/**
	 * @Test12 about this method verifyToolTipText()
	 * @param : null
	 * @description : Verify the text of the tooltip
	 * @return : String
	 * @throws : Exception - if there is an issue finding the text
	 * @author : YAKSHA
	 */
	public String verifyToolTipText() {
		String toolTipValue = "";
		try {
			WebElement toolTip = commonEvents.findElement(favouriteOrStarIcon);
			toolTipValue = commonEvents.highlight(toolTip).getAttribute(toolTip, "title");
			System.out.println("Tool tip title : " + toolTipValue);
		} catch (Exception e) {
			throw e;
		}
		return toolTipValue;
	}

	/**
	 * @Test13 about this method
	 *         verifyGoodsReceiptTableDataIsPresentAfterEnteringSupplierName()
	 * 
	 * @param : null
	 * @description : Verify result appears after searching with supplier name
	 * @return : boolean
	 * @throws : Exception - if there is an issue finding the result
	 * @author : YAKSHA
	 */
	public boolean verifyGoodsReceiptTableDataIsPresentAfterEnteringSupplierName(
			Map<String, String> pharmacyExpectedData) {
		commonEvents.sendKeys(supplierNameDropdownXpath, pharmacyExpectedData.get("supplierName"))
				.sendKeys(supplierNameDropdownXpath, Keys.ENTER);
		List<WebElement> tableRows = commonEvents.getWebElements(goodReceiptTableDataRow);
		if (tableRows.size() >= 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @Test14.4 about this method clickOnEditGRButton()
	 * 
	 * @param : null
	 * @description : Clicks on the edit GR button
	 * @return : boolean
	 * @throws : Exception - if there is an issue clicking the edit GR button
	 * @author : YAKSHA
	 */
	public boolean clickOnEditGRButton() {
		boolean isEditGRButtonDisplayed = false;
		try {
			if (commonEvents.isDisplayed(editGRButton)) {
				WebElement editGrButton = commonEvents.getWebElement(editGRButton);
				isEditGRButtonDisplayed = true;
				commonEvents.highlight(editGrButton).click(editGrButton);
			}
		} catch (Exception e) {
			throw e;
		}
		return isEditGRButtonDisplayed;
	}

	/**
	 * @Test8.3 @Test9.2 @Test10.2 @Test14.2 @Test16.2 @Test16.4 @Test16.7 @Test16.8 @Test16.11
	 * about this method highlightAndClickOnButton()
	 * 
	 * @param : By, String
	 * @description : Highlights the provided element and clicks on it
	 * @return : boolean
	 * @throws : Exception - if there is an issue finding the element
	 * @author : YAKSHA
	 */
	public boolean highlightAndClickOnButton(By element, String buttonName) {
		boolean isButtonDisplayed = false;
		try {
			if (commonEvents.isDisplayed(element)) {
				WebElement webElement = commonEvents.getWebElement(element);
				commonEvents.highlight(webElement).click(webElement);
				System.out.println("Clicked on " + buttonName + " button");
				isButtonDisplayed = true;
			}
		} catch (Exception e) {
			throw e;
		}
		return isButtonDisplayed;
	}

	/**
	 * @Test11 about this method performScrollOperation()
	 * @param : null
	 * @description : Scrolls till Pharmacy tab, selects it, and clicks "Order" sub
	 *              tab
	 * @return : boolean
	 * @throws : Exception - if there is an issue finding the tab
	 * @author : YAKSHA
	 */
	public boolean performScrollOperation() {
		boolean performScrollOperation = false;
		try {
			this.scrollDownAndClickPharmacyTab();
			this.clickOnHomePageLogo();
			this.scrollDownAndClickPharmacyTab();
			commonEvents.click(orderTabXpath);
			performScrollOperation = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return performScrollOperation;
	}

	/**
	 * @Test9.1 @Test17.3 about this method applyDateFilter()
	 * 
	 * @param : String, String
	 * @description : Applies the date filter with date range
	 * @return : boolean
	 * @throws : Exception - if there is an issue finding or filling the date fields
	 * @author : YAKSHA
	 */
	public boolean applyDateFilter(String fromDate, String toDate) throws Exception {
		boolean isDateApplied = false;
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
			WebElement okButton = commonEvents.findElement(okButtonXpath);
			commonEvents.highlight(fromDateDropdown).sendKeys(fromDateDropdown, fromDay)
					.sendKeys(fromDateDropdown, fromMonth).sendKeys(fromDateDropdown, fromYear);
			commonEvents.highlight(toDateDropdown).sendKeys(toDateDropdown, toDay).sendKeys(toDateDropdown, toMonth)
					.sendKeys(toDateDropdown, toYear);
			commonEvents.highlight(okButton).click(okButton);

			isDateApplied = true;
			return isDateApplied;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @Test9.3 about this method verifyActualDatesAreWithinThisRange()
	 * 
	 * @param : String, String
	 * @description : Verify whether results are within the applied date range
	 * @return : boolean
	 * @throws : Exception - if there is an issue finding the actual dates within
	 *           the results
	 * @author : YAKSHA
	 */
	public boolean verifyActualDatesAreWithinThisRange(String fromDate, String toDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		List<WebElement> actualDatesAfterFilterApplied = commonEvents
				.getWebElements(actualDatesOfGoodReceiptTableXpath);
		LocalDate from = LocalDate.parse(fromDate, formatter);
		LocalDate to = LocalDate.parse(toDate, formatter);

		for (WebElement dateElement : actualDatesAfterFilterApplied) {
			String dateText = dateElement.getText();

			LocalDate date;
			LocalDate newDate;
			try {
				date = LocalDate.parse(dateText, inputFormatter);
				newDate = LocalDate.parse(date.format(formatter), formatter);

			} catch (Exception e) {
				System.out.println("Date parsing failed for: " + dateText);
				return false;
			}

			if (newDate.isBefore(from) || newDate.isAfter(to)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @Test16.5 about this method enterRemarksAndClickOnProceed()
	 * 
	 * @param : WebElement
	 * @description : Enters remarks and clicks on the "Proceed" button
	 * @return : void
	 * @throws : Exception - if there is an issue finding, filling, or clicking the
	 *           element
	 * @author : YAKSHA
	 */
	public void enterRemarksAndClickOnProceed(By element) {
		try {
			if (commonEvents.isDisplayed(cancelRemarks)) {
				WebElement cancelRemarksField = commonEvents.findElement(cancelRemarks);
				commonEvents.highlight(cancelRemarksField).sendKeys(cancelRemarks, "cancel");
			}
			this.highlightAndClickOnButton(element, "Proceed Button");
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @Test16.10 about this method verifyRecordStatusFromInvoce()
	 * 
	 * @param : WebElement
	 * @description : Verify the record status from invoice
	 * @return : boolean
	 * @throws : Exception - if there is an issue finding the element
	 * @author : YAKSHA
	 */
	public boolean verifyRecordStatusFromInvoce(By element) {
		boolean isStatusDisplayed = false;
		if (commonEvents.isDisplayed(element)) {
			isStatusDisplayed = true;
			System.out.println("isStatusDisplayed + " + isStatusDisplayed);
		}
		return isStatusDisplayed;
	}

	/**
	 * @Test15.3 about this method clickCompleteRadioButton()
	 * 
	 * @param : null
	 * @description : Clicks on the "Complete" radio button
	 * @return : void
	 * @throws : Exception - if there is an issue finding the radio button
	 * @author : YAKSHA
	 */
	public void clickCompleteRadioButton() throws Exception {
		try {
			WebElement radioButtonComplete = commonEvents.findElement(radiButtonCompleteXpath);
			commonEvents.highlight(radioButtonComplete).click(radioButtonComplete);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @Test15.4 about this method isCompleteRadioButtonSelectable()
	 * 
	 * @param : null
	 * @description : Verify whether the "Complete" radio button is selectable
	 * @return : boolean
	 * @throws : Exception - if there is an issue finding the radio button
	 * @author : YAKSHA
	 */
	public boolean isCompleteRadioButtonSelectable() throws Exception {
		try {
			System.out.println("Complete started");
			boolean radioButtonSelected = false;
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement radioButtonComplete = commonEvents.findElement(radiButtonCompleteXpath);

			String script = "return window.getComputedStyle(arguments[0], '::after').getPropertyValue('content');";
			String content = (String) js.executeScript(script, radioButtonComplete);
			if (!content.isEmpty()) {
				System.out.println("The span element contains the ::after pseudo-element. complete");
				radioButtonSelected = true;
			} else {
				System.out.println("The span element does not contain the ::after pseudo-element.");
				radioButtonSelected = false;
			}
			return radioButtonSelected;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @Test15.5 about this method clickCancelledRadioButton()
	 * 
	 * @param : null
	 * @description : Clicks the "Cancelled" radio button
	 * @return : void
	 * @throws : Exception - if there is an issue finding the radio button
	 * @author : YAKSHA
	 */
	public void clickCancelledRadioButton() throws Exception {
		try {
			WebElement radioButtonCancelled = commonEvents.findElement(radiButtonCancelledXpath);
			commonEvents.highlight(radioButtonCancelled).click(radioButtonCancelled);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @Test15.6 about this method isCancelledRadioButtonSelectable()
	 * 
	 * @param : null
	 * @description : Verify whether the "Cancelled" radio button is selectable
	 * @return : boolean
	 * @throws : Exception - if there is an issue finding the radio button
	 * @author : YAKSHA
	 */
	public boolean isCancelledRadioButtonSelectable() throws Exception {
		try {
			System.out.println("Cancelled started");
			boolean radioButtonSelected = false;
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0, 0);");
			WebElement radioButtonCancelled = commonEvents.findElement(radiButtonCancelledXpath);

			String script = "return window.getComputedStyle(arguments[0], '::after').getPropertyValue('content');";
			String content = (String) js.executeScript(script, radioButtonCancelled);
			if (!content.isEmpty()) {
				System.out.println("The span element contains the ::after pseudo-element. cancelled");
				radioButtonSelected = true;
			} else {
				System.out.println("The span element does not contain the ::after pseudo-element.");
				radioButtonSelected = false;
			}
			return radioButtonSelected;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @Test15.7 about this method clickAllRadioButton()
	 * 
	 * @param : null
	 * @description : Clicks the "All" radio button
	 * @return : void
	 * @throws : Exception - if there is an issue finding the radio button
	 * @author : YAKSHA
	 */
	public void clickAllRadioButton() throws Exception {
		try {
			WebElement radioButtonAll = commonEvents.findElement(radiButtonAllXpath);
			commonEvents.highlight(radioButtonAll).click(radioButtonAll);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @Test15.8 about this method isAllRadioButtonSelectable()
	 * 
	 * @param : null
	 * @description : Verify whether the "All" radio button is selectable
	 * @return : boolean
	 * @throws : Exception - if there is an issue finding the radio button
	 * @author : YAKSHA
	 */
	public boolean isAllRadioButtonSelectable() throws Exception {
		try {
			System.out.println("All started");
			boolean radioButtonSelected = false;
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0, 0);");
			WebElement radioButtonAll = commonEvents.findElement(radiButtonAllXpath);

			String script = "return window.getComputedStyle(arguments[0], '::after').getPropertyValue('content');";
			String content = (String) js.executeScript(script, radioButtonAll);
			if (!content.isEmpty()) {
				System.out.println("The span element contains the ::after pseudo-element. All");
				radioButtonSelected = true;
			} else {
				System.out.println("The span element does not contain the ::after pseudo-element.");
				radioButtonSelected = false;
			}
			return radioButtonSelected;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @Test17.4 about this method
	 * verifyActualDatesForPurchaseOrderAreWithinThisRange()
	 * 
	 * @param : String fromDate, String toDate
	 * @description : Verify if the actual dates for purchase orders are within the
	 *              specified date range
	 * @return : boolean
	 * @throws : Exception - if there is an issue parsing the dates
	 * @author : YAKSHA
	 */
	public boolean verifyActualDatesForPurchaseOrderAreWithinThisRange(String fromDate, String toDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		List<WebElement> actualDatesAfterFilterApplied = commonEvents
				.getWebElements(actualDatesOfPurchaseOrderTableXpath);
		LocalDate from = LocalDate.parse(fromDate, formatter);
		LocalDate to = LocalDate.parse(toDate, formatter);
		for (WebElement dateElement : actualDatesAfterFilterApplied) {
			String dateText = commonEvents.highlight(dateElement).getText(dateElement);
			LocalDate date;
			LocalDate newDate;
			try {
				date = LocalDate.parse(dateText, inputFormatter);
				newDate = LocalDate.parse(date.format(formatter), formatter);
			} catch (Exception e) {
				System.out.println("Date parsing failed for: " + dateText);
				return false;
			}
			if (newDate.isBefore(from) || newDate.isAfter(to)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @Test17.2 about this method clickPurchaseOrderTab()
	 * 
	 * @param : null
	 * @description : Clicks the "Purchase Order" tab
	 * @return : void
	 * @throws : Exception - if there is an issue finding the tab element
	 * @author : YAKSHA
	 */
	public void clickPurchaseOrderTab() throws Exception {
		try {
			WebElement purchaseOrderTab = commonEvents.findElement(purchaseOrderXpath);
			commonEvents.highlight(purchaseOrderTab).click(purchaseOrderTab);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @Test18 about this method verifyFileDownloaded()
	 * 
	 * @param : String partialFileName
	 * @description : Verify if a file with the specified partial name has been
	 *              downloaded
	 * @return : boolean
	 * @throws : InterruptedException - if the thread is interrupted while waiting
	 *           for the file to download
	 * @author : YAKSHA
	 */
	public boolean verifyFileDownloaded(String partialFileName) throws InterruptedException {
		commonEvents.click(orderTabXpath);
		commonEvents.click(exportButton);

		// Update to use the correct download directory
		String downloadPath = System.getProperty("user.dir") + "\\downloads";
		File dir = new File(downloadPath);
		boolean found = false;

		// Print debug info
		System.out.println("Checking download directory: " + downloadPath);

		for (int i = 0; i < 30; i++) { // Check for 30 seconds
			File[] files = dir.listFiles();
			if (files != null) {
				System.out.println("Files in download directory:");
				for (File file : files) {
					System.out.println(" - " + file.getName());
					if (file.getName().contains(partialFileName) && !file.getName().endsWith(".tmp")) {
						found = true;
						break;
					}
				}
			}
			if (found)
				break;
			Thread.sleep(1000);
		}

		return found;
	}

	/**
	 * @Test19.1 about this method clickPatientTab()
	 * 
	 * @param : String
	 * @description : Clicks on the "Patient" tab
	 * @return : boolean
	 * @throws : Exception - if there is an issue clicking the tab
	 * @author : YAKSHA
	 */
	public boolean clickPatientTab() throws Exception {
		boolean isUploaded = false;
		try {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			WebElement doctorTab = commonEvents.findElement(By.xpath("//a[@href=\"#/Doctors\"]"));
			WebElement patientTab = commonEvents.findElement(patientTabXpath);
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", doctorTab);
			commonEvents.highlight(patientTab);
			commonEvents.click(patientTab);

			commonEvents.waitForUrlContains("/Patient/SearchPatient", 10);
			isUploaded = true;
		} catch (Exception e) {
			throw e;
		}
		return isUploaded;
	}

	/**
	 * @Test19.2 about this method handleFileUpload()
	 * 
	 * @param : String pathOfTheFile - the file path of the file to be uploaded
	 * @description : This method handles the file upload process by interacting
	 *              with various elements on the page such as the register patient
	 *              button, profile picture, and file selection dialog. It waits for
	 *              the elements to be visible, clicks them, and uploads the
	 *              specified file.
	 * @return : boolean - true if the file is successfully uploaded, false
	 *         otherwise
	 * @throws : Exception - if there is an issue during the file upload process
	 * @author : YAKSHA
	 */
	public boolean handleFileUpload(String pathOfTheFile) throws Exception {
		boolean isUploaded = false;
		try {
			commonEvents.waitTillElementVisible(registerPatient, 10000);
			commonEvents.click(registerPatient);
			commonEvents.click(profilePicture);
			commonEvents.click(newPhoto);
			commonEvents.click(chooseFileButton);

			System.out.println("path of the file" + pathOfTheFile);
			Thread.sleep(3000); // wait for file to get uploaded
			commonEvents.fileUpload(pathOfTheFile);
			Thread.sleep(3000); // wait for file to get uploaded

			commonEvents.click(doneButton);
			System.out.println("isfile uploaded + " + isUploaded);
			isUploaded = true;
		} catch (Exception e) {
			throw e;
		}
		return isUploaded;
	}

	/**
	 * @Test20 about this method isProfilePictureUploaded()
	 * 
	 * @param : null
	 * @description : This method verifies if the profile picture has been uploaded
	 *              by navigating to the profile picture section and checking if an
	 *              element matching the uploaded profile photo XPath is present.
	 * @return : boolean - true if the profile picture is uploaded, false otherwise
	 * @throws : Exception - if there is an issue finding or clicking elements, or
	 *           verifying the upload
	 * @author : YAKSHA
	 */
	public boolean isProfilePictureUploaded() throws Exception {
		try {
			commonEvents.click(registerPatient);
			commonEvents.click(profilePicture);
			if (commonEvents.getWebElements(uploadedProfilePhotoXpath).size() > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @Test21 about this method verifyBrowserUrlAfterRefreshingThePage()
	 * @param : null
	 * @description : Verify the URL is same after refreshing the page
	 * @return : boolean
	 * @throws : Exception - if there is an issue reloading the page
	 * @author : YAKSHA
	 */
	public boolean verifyBrowserUrlAfterRefreshingThePage() {
		boolean areUrlsEqual = false;
		try {
			// Get the URL before the refresh
			String beforeRefresh = commonEvents.getCurrentUrl();
			System.out.println("Before Refresh Browser current URL > " + beforeRefresh);

			// Refresh the page
			commonEvents.refreshPage();

			// Get the URL after the refresh
			String afterRefresh = commonEvents.getCurrentUrl();
			System.out.println("After Refresh Browser current URL > " + afterRefresh);

			// Check if the URL before refresh contains the URL after refresh
			areUrlsEqual = beforeRefresh.contains(afterRefresh);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return areUrlsEqual;
	}

}
