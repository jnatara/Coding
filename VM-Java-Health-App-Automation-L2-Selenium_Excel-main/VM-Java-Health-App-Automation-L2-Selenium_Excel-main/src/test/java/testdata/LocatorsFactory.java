package testdata;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



public class LocatorsFactory {

	//please write all the locators for Money Control page only 

	// element of health application

	By totalDoctortextElement = By.xpath("//p[contains(text(), 'Total Doctors')]");
	By selectCounterPopupElement = By.xpath("//h3//span[contains(text(), 'Select Counter')]");
	By new1TextElement = By.xpath("//h5[contains(text(), 'New-1')]");
	By new2TextElement = By.xpath("//h5[contains(text(), 'New-2')]");
	By new3TextElement = By.xpath("//h5[contains(text(), 'New-3')]");
	By old1TextElement = By.xpath("//h5[contains(text(), 'Old-1')]"); 
	By opdCounterTextElement = By.xpath("//h5[contains(text(), 'OPD-Counter')]");

	By addNewPatientButtonElement = By.xpath("//button[contains(text(), 'Add New Patient')]");
	By registerAndBillingButtonElement = By.xpath("//button[contains(text(), 'Register & Billing')]");
	By registerOnlyButtonElement = By.xpath("//button[contains(text(), 'Register Only')]");
	By registerOnlyButtonToHighlight = By.xpath("(//button[@class=\"btn green btn-success margin-7-hr\"])[2]");
	By firstNameTextFieldElement = By.xpath("//input[@id='newPatFirstName']");
	By countryDropdownByElement = By.xpath("//select[@id='ddlCountry']");
	By errorMessageOfRegisterBillingByElement = By.xpath("//p[contains(text(), 'Some of the inputs are invalid. Please check and try again. !')]");
	By closeButtonOfAddNewpatientpage = By.xpath("//button[contains(text(), 'Close')]");

	By errorMessageCloseButton = By.xpath("//a[@class='close-btn']//i[@class='fa fa-times-circle']");
	By dispensaryToggle = By.xpath("//span[@data-target='#Dispensary']");
	By prescriptionSubMenu = By.xpath("//ul[@id='Dispensary']//li//a//span[contains(text(),'Prescription')]");
	By saleSubMenu = By.xpath("//ul[@id='Dispensary']//li//a//span[contains(text(),'Sale')]");
	By stockSubMenu = By.xpath("//ul[@id='Dispensary']//li//a//span[contains(text(),'Stock')]");
	By counterSubMenu = By.xpath("//ul[@id='Dispensary']//li//a//span[contains(text(),'Counter')]");
	By reportsSubMenu = By.xpath("//ul[@id='Dispensary']//li//a//span[contains(text(),'Reports')]");
	By patientConsumptionSubMenu = By.xpath("//ul[@id='Dispensary']//li//a//span[contains(text(),'Patient Consumption')]");
	
//	By dispensaryLeftNavigationMenu = By.xpath("//a[@class='left-nav-active']//span[contains(text(), 'Dispensary')]");
	By dispensaryLeftNavigationMenu = By.xpath("//a[@href='#/Dispensary']//span[contains(text(), 'Dispensary')]");
	By settingLeftNavigationMenu = By.xpath("//a[@href='#/Settings']//span[contains(text(), 'Settings')]");

	//l2 locators

	By checkBox=By.xpath("(//label[@class='mt-checkbox mt-checkbox-outline'])[1]//span");
	By addNewButtonElement = By.xpath("//button[contains(text(), 'Add New Patient')]");
	By headerNotificationBar = By.id("header_notification_bar");
	By admittingDocField = By.xpath("//b[.='Admitting Doc:']");
	By newItemButton = By.xpath("//button[.=' New Item ']");
	By myFavoritesButton = By.xpath("//a[.=' My Favorites']");
	By manageRoleTab = By.xpath("//a[.='Manage Role']");
	By tooltipText = By.xpath("//li[contains(text(),'+')]");

	//public CommonEvents commonEvents;
	public WebDriver driver;
	public LocatorsFactory(WebDriver driver) {
		
		this.driver = driver;
		//commonEvents = new CommonEvents(driver);
	}
	
	public void waitTillElementVisible(WebElement element, long seconds) {
		new WebDriverWait(driver, Duration.ofSeconds(seconds))
		.until(ExpectedConditions.visibilityOf(element));
		
	}
	
	public void waitTillElementVisible(By by, long seconds) {
		new WebDriverWait(driver, Duration.ofSeconds(seconds))
		.until(ExpectedConditions.visibilityOf(getWebElement(by)));
	}
	
	public boolean isDisplayed(By by)
	{
		boolean flag = false;
		try
		{
			waitTillElementVisible(by, 60);
			if(getWebElement(by).isDisplayed())
				flag = true;
		}catch(Exception e)
		{
			flag = false;
		}

		return flag;
	}
	
	public boolean isDisplayed(By by, int seconds)
	{
		boolean isDisplayed = false;
		try
		{
			waitTillElementVisible(by, seconds);
			isDisplayed = true;
		}
		catch(Exception e)
		{
			isDisplayed = false;
		}
		return isDisplayed;
	}
	
	public boolean isDisplayed(WebElement element, int seconds)
	{
		boolean isDisplayed = false;
		try
		{
			waitTillElementVisible(element, seconds);
			isDisplayed = true;
		}
		catch(Exception e)
		{
			isDisplayed = false;
		}
		return isDisplayed;
	}
	
	public void waitTillPageLoad(WebElement element, int seconds, String pageName) throws Exception
	{
		int count = 1;
		boolean isDisplayed = false;
		boolean noActive = false;
		boolean ajaxIsComplete = false;

		while(count<=seconds) 
		{
			ajaxIsComplete = (boolean) ((JavascriptExecutor) driver).executeScript("return document.readyState")
					.toString()
					.equals("complete");
			if(ajaxIsComplete) 
			{
				try
				{
					noActive = (boolean)((JavascriptExecutor) driver).executeScript("return jQuery.active==0;");
					isDisplayed = isDisplayed(element, 1);
				}
				catch(Exception e)
				{
					isDisplayed = isDisplayed(element, 1);
				}


				if (noActive || isDisplayed)
					break;
				else
					count++;
			}
			else
				count++;
		}
		if(count>seconds)
			throw new Exception("The ajax calls for "+pageName+" could not be completed in "+seconds+" seconds");
		
	}
	
	public void waitTillPageLoad(By by, int seconds, String pageName) throws Exception
	{
		int count = 1;
		boolean isDisplayed = false;
		boolean noActive = false;
		boolean ajaxIsComplete = false;

		while(count<=seconds) 
		{
			ajaxIsComplete = (boolean) ((JavascriptExecutor) driver).executeScript("return document.readyState")
					.toString()
					.equals("complete");
			if(ajaxIsComplete) 
			{
				try
				{
					noActive = (boolean)((JavascriptExecutor) driver).executeScript("return jQuery.active==0;");
					isDisplayed = isDisplayed(by, 1);
				}
				catch(Exception e)
				{
					isDisplayed = isDisplayed(by, 1);
				}


				if (noActive || isDisplayed)
					break;
				else
					count++;
			}
			else
				count++;
		}
		if(count>seconds)
			throw new Exception("The ajax calls for "+pageName+" could not be completed in "+seconds+" seconds");

	}
	
	public WebElement getWebElement(By by)
	{
		return driver.findElement(by);
	}
	
	public void jsClick(By by)
	{
		((JavascriptExecutor) driver)
		.executeScript("arguments[0].click()", getWebElement(by));
	}


	
	
	
	public void highlightElement(WebElement element)
	{
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		// Add the highlight
		jse.executeScript("arguments[0].setAttribute('style', 'border: solid 5px green');", element);

		try {
			// Wait for a specified time (e.g., 500 milliseconds)
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Remove the highlight
		jse.executeScript("arguments[0].setAttribute('style', '');", element);

		
	}
	
	
	
	public void highlightElementAfterAction(WebElement element)
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].setAttribute('style','border: solid 5px green');", element);

		try {
			// Wait for a specified time (e.g., 500 milliseconds)
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Remove the highlight
		jse.executeScript("arguments[0].setAttribute('style', '');", element);

	}
	
	public String getAttribute(By by, String attributeName)
	{
		return getWebElement(by).getAttribute(attributeName);
	}	
	
	public String getText(By by)
	{
		return getWebElement(by).getText();
	}
	
	public WebElement findElement(By locator) {
		return driver.findElement(locator);
	}
	
	public String getFirstSelectedOptionFromDropdown(By by, String elementName, String pageName) throws Exception
	{
		if(elementName==null)
			throw new Exception("Element name should not be null");
		if(pageName==null)
			throw new Exception("Page name should not be null");
		String desiredValue="";
		try
		{
			waitTillPageLoad(by, 30, pageName);
			Select select = new Select(getWebElement(by));
			desiredValue = select.getFirstSelectedOption().getText();
		}
		catch(Exception e)
		{
			throw new Exception(elementName+" is not displayed on "+pageName);
		}
		return desiredValue;
	}

// here above to  it
	public WebElement totalDoctorTextIsPresent(WebDriver driver) {
		WebElement totalDoctorTextWebElement = driver.findElement(totalDoctortextElement);
		highlightElement(totalDoctorTextWebElement);
		return totalDoctorTextWebElement; 
	}

	public Boolean verifyAllFieldIsPresent() throws Exception {
		Boolean allFieldIsPresent = false;
		try {
			if(isDisplayed(selectCounterPopupElement)&&
					isDisplayed(new1TextElement)&&
					isDisplayed(new2TextElement)&&
					isDisplayed(new3TextElement)&&
					isDisplayed(old1TextElement)&&
					isDisplayed(opdCounterTextElement)) {

				allFieldIsPresent = true;
			}	
		}catch(Exception e) {
			throw e;	
		}
		return allFieldIsPresent;
	}

	public WebElement addNewButtonIsPresent(WebDriver driver) {
		WebElement addNewPatientButtonWebElement = driver.findElement(addNewPatientButtonElement);
		highlightElementAfterAction(addNewPatientButtonWebElement);
		return addNewPatientButtonWebElement; 
	}

	public WebElement registerAndBillingButtonIsPresent(WebDriver driver) {
		WebElement registerAndBillingButtonWebElement = driver.findElement(registerAndBillingButtonElement);
		return registerAndBillingButtonWebElement; 
	}

	public String verifyFirstNameTextValueIsPresent() throws Exception {
		String firstNameTextfieldValue = "";
		try {
			if(isDisplayed(firstNameTextFieldElement))
			{
				firstNameTextfieldValue = getAttribute(firstNameTextFieldElement, "value");
				System.out.println("firstName Text value is  : " + firstNameTextfieldValue);
			}
		}catch(Exception e) {
			throw e;
		}
		return firstNameTextfieldValue;
	}

	public String verifyRegisterOnlyButtonIsPresent() throws Exception {
		String registerOnlyButtonIsPresent = "";
		try {
			if(isDisplayed(registerOnlyButtonElement))
			{
				registerOnlyButtonIsPresent = getText(registerOnlyButtonElement);
				System.out.println("text value of register Only Button : " + registerOnlyButtonIsPresent);
				WebElement registerOnlyButtonWebElement = findElement(registerOnlyButtonToHighlight);
				highlightElementAfterAction(registerOnlyButtonWebElement);
			}
		}catch(Exception e) {
			throw e;
		}
		return registerOnlyButtonIsPresent;
	}

	public String verifyIndiaIsPresent() throws Exception {
		String selectedCountryValue = "";
		try {
			if(isDisplayed(countryDropdownByElement))
			{
				selectedCountryValue = getFirstSelectedOptionFromDropdown(countryDropdownByElement, "elementName", "pageName");
				System.out.println("selected dropdown value is  : " + selectedCountryValue);
			}
		}catch(Exception e) {
			throw e;
		}
		return selectedCountryValue;
	}


	public String verifyErrorMessageIsPresent() throws Exception {
		String errorMessageValue = "";
		try {
			if(isDisplayed(errorMessageOfRegisterBillingByElement))
			{
				errorMessageValue =  getText(errorMessageOfRegisterBillingByElement);
				System.out.println("error message validation by Locators factory class : " + errorMessageValue);
				jsClick(errorMessageCloseButton);
				jsClick(closeButtonOfAddNewpatientpage);
			}
		}catch(Exception e) {
			throw e;
		}
		return errorMessageValue;
	}

	public Boolean highLightPresenceOfAllFieldInDispensaryMenu() throws Exception {
		Boolean highlightPresenceOfElement=false;
		try {
			if(isDisplayed(prescriptionSubMenu)&&
					isDisplayed(saleSubMenu)&&
					isDisplayed(stockSubMenu)&&
					isDisplayed(counterSubMenu)&&
					isDisplayed(reportsSubMenu)&&
					isDisplayed(patientConsumptionSubMenu)) {

				WebElement prescriptionSubMenuWebElement = driver.findElement(prescriptionSubMenu);
				highlightElementAfterAction(prescriptionSubMenuWebElement);
				WebElement saleSubMenuWebElement = driver.findElement(saleSubMenu);
				highlightElementAfterAction(saleSubMenuWebElement);
				WebElement stockSubMenuWebElement = driver.findElement(stockSubMenu);
				highlightElementAfterAction(stockSubMenuWebElement);
				WebElement counterSubMenuWebElement = driver.findElement(counterSubMenu);
				highlightElementAfterAction(counterSubMenuWebElement);
				WebElement reportsSubMenuWebElement = driver.findElement(reportsSubMenu);
				highlightElementAfterAction(reportsSubMenuWebElement);
				WebElement patientConsumptionSubMenuWebElement = driver.findElement(patientConsumptionSubMenu);
				highlightElementAfterAction(patientConsumptionSubMenuWebElement);

				highlightPresenceOfElement= true;
			}	

		}catch(Exception e) {
			throw e;	
		}
		return highlightPresenceOfElement;
	}
	
	public Boolean verifyFieldIsNotPresentInDispensaryMenu() throws Exception {
		Boolean highlightDispensaryNavigationMenu=false;
		try {
			if(!(isDisplayed(prescriptionSubMenu)&&
					isDisplayed(saleSubMenu))) {

				WebElement dispensaryNavigationMenuWebElement = driver.findElement(dispensaryLeftNavigationMenu);
				highlightElementAfterAction(dispensaryNavigationMenuWebElement);

				highlightDispensaryNavigationMenu = true;
			}	

		}catch(Exception e) {
			throw e;	
		}
		return highlightDispensaryNavigationMenu;
	}
	
	public Boolean settingModuleIsPresent() throws Exception {
		Boolean settingModuleIsPresent = false;
		try {
			if(isDisplayed(settingLeftNavigationMenu)) {
				
				WebElement settingLeftNavigationMenuWebElement = driver.findElement(settingLeftNavigationMenu);
				highlightElementAfterAction(settingLeftNavigationMenuWebElement);
				
				settingModuleIsPresent = true;
			}	
			
		}catch(Exception e) {
			throw e;	
		}
		return settingModuleIsPresent;
	}

	// l2 scenarios

	public WebElement headerNotificationBarIsPresent(WebDriver driver) {
		WebElement headerNotificationBarWebElement = driver.findElement(headerNotificationBar);
		return headerNotificationBarWebElement; 
	}

	public WebElement admittingDocFieldIsPresent(WebDriver driver) {
		WebElement admittingDocFieldWebElement = driver.findElement(admittingDocField);
		return admittingDocFieldWebElement; 
	}

	public WebElement checkBoxIsPresent(WebDriver driver) {
		WebElement checkBoxWebElement = driver.findElement(checkBox);
		return checkBoxWebElement; 
	}

	public WebElement newItemButtonPresent(WebDriver driver) {
		WebElement newItemButtonWebElement = driver.findElement(newItemButton);
		return newItemButtonWebElement; 
	}

	public WebElement myFavoritesButtonIsPresent(WebDriver driver) {
		WebElement myFavoritesButtonWebElement = driver.findElement(myFavoritesButton);
		return myFavoritesButtonWebElement; 
	}

	public WebElement manageRoleTabIsPresent(WebDriver driver) {
		WebElement manageRoleTabWebElement = driver.findElement(manageRoleTab);
		return manageRoleTabWebElement; 
	}
	
	public WebElement newItemButtonIsPresent(WebDriver driver) {
		WebElement newItemButtonWebElement = driver.findElement(newItemButton);
		return newItemButtonWebElement;
	}
	
	public WebElement tooltipTextIsPresent(WebDriver driver) {
		WebElement tooltipTextWebElement = driver.findElement(tooltipText);
		return tooltipTextWebElement;
	}
}
