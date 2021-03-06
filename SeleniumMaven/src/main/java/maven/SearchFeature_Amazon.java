package maven;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.web.pom.POMFile_SearchFeature;

/**
 * @author Vethiga
 */

public class SearchFeature_Amazon {
	static WebDriver driver;
	static int PriceSpanCount, CountColorOption;
	static List<WebElement> ProductsColor;
	static String DriverTitle, ClearText, MinPriceValue, MaxPriceValue, ProductNameInDetailView,
			ProductPriceInDetailView, ProductColorInDetailView;
	static boolean IsNameDisplay, IsPriceDisplay, IsColorDisplay;
	static WebElement ProductName, ProductPrice, ProductColor;
	static WebDriverWait wait;
	static JavascriptExecutor jsExecutor;
	static ExtentReports extentReport;
	static ExtentHtmlReporter extentHtmlReport;
	static ExtentTest ParentTestcase, ChildTestcase;

	@BeforeSuite
	public static void OpenBrowser() {
		// Open browser and navigate to amazon
		driver = new ChromeDriver();
		driver.navigate().to("https://www.amazon.com/");

		extentReport = new ExtentReports();
		extentHtmlReport = new ExtentHtmlReporter("ExtentReport-SearchFeature.html");
		extentReport.attachReporter(extentHtmlReport);
	}

	
	@Test
	public static void SearchTestImplementation() {

		ParentTestcase = extentReport.createTest("Verify Searching functionality is working or not");

		// Factory Class for Page Object usage
		PageFactory.initElements(driver, POMFile_SearchFeature.class);

		// Selecting an option from DropDown
		Select select = new Select(POMFile_SearchFeature.DropDownIcon);
		select.selectByVisibleText("Home & Kitchen");
		POMFile_SearchFeature.SearchIcon.click();

		// Verifying Header of selected option from drop down
		Assert.assertEquals(POMFile_SearchFeature.HomeAndKitchen_HeaderVerify.getText(), "Home and Kitchen");
		POMFile_SearchFeature.HomeDecorSection.click(); // Selecting Section
		Assert.assertEquals(POMFile_SearchFeature.HomeDecorText_Verify.getText(), "Home Décor"); // Verifying Home Decor
		// text in side pane

		POMFile_SearchFeature.DecorativePillowsDepartment.click(); // Selecting Department from section
		Assert.assertEquals(POMFile_SearchFeature.decorativePillowsText_Verify.getText(), "Decorative Pillows");

		POMFile_SearchFeature.ThrowPillowCoversSelection.click(); // Selecting Throw Pillow Covers Option
		Assert.assertEquals(POMFile_SearchFeature.ThrowPillowCoversText_Verify.getText(), "Throw Pillow Covers");

		// Verifying color is selected or not (using "Clear" text and displaying color
		// count)
		POMFile_SearchFeature.ColorSelection.click();// Click on the color selection
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
		CountColorOption = POMFile_SearchFeature.ColorSelection_Verify.size();

		for (WebElement Color : POMFile_SearchFeature.ColorSelection_Verify) {
			if (Color.getAttribute("title").equals("Red")) {
				Assert.assertEquals(Color.getAttribute("title"), "Red");
			}
		}
		
		ClearText = POMFile_SearchFeature.ClearTextColorSelection_Verify.getText();
		Assert.assertEquals(CountColorOption, 2); // Verify 2 colors are displaying(after selection)
		Assert.assertEquals("Clear", ClearText); // Verify the Clear text

		POMFile_SearchFeature.PriceSelection.click(); // Click on the price selection
		MinPriceValue = POMFile_SearchFeature.PriceMin_Verify.getAttribute("value");
		MaxPriceValue = POMFile_SearchFeature.PriceMax_Verify.getAttribute("value");
		Assert.assertEquals(MinPriceValue, "25"); // Verifying the minimum filled value
		Assert.assertEquals(MaxPriceValue, "50"); // Verifying the maximum filled value

		for (WebElement Product : POMFile_SearchFeature.ProductClickLink) {

			Actions actions = new Actions(driver); // actions class (performing complex user
			// web interactions like double-click, right-click, etc.)
			actions.keyDown(Keys.LEFT_CONTROL).click(Product).keyUp(Keys.LEFT_CONTROL).build().perform();

			List<String> tab = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tab.get(1));
			driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);


			if (POMFile_SearchFeature.ProductDetailedNameInDetailedView.isDisplayed() == true) {
				try {

					ProductName = driver.findElement(By.xpath("//h1[@id=\"title\"]"));
					System.out.println(ProductName.getText());

				}

				catch (org.openqa.selenium.NoSuchElementException e) {
					System.out.println("Name is not visible");

				}
			}

			if (POMFile_SearchFeature.ProductPriceInDetailedView.isDisplayed() == true) {
				try {

					ProductPrice = driver.findElement(By.xpath("(//td[@class=\"a-span12\"]/span)[1]"));
					System.out.println("         Price: " + ProductPrice.getText());

				}

				catch (org.openqa.selenium.NoSuchElementException e) {
					System.out.println("Price is not visible");

				}
			}

//			if (POMFile_SearchFeature.ProductColorInDetailedView.isDisplayed() == true) {
//
//			}
			

				try {

					ProductColor = driver.findElement(By.xpath(
							"(//label[contains(text(),\"Color:\")]/following-sibling::span[@class=\"selection\"])[1]"));
					System.out.println("         Color: " + ProductColor.getText());

				}

				catch (org.openqa.selenium.NoSuchElementException e) {
					System.out.println("Color is not visible");

				}
				

			driver.close();
			driver.switchTo().window(tab.get(0));

		}

		// Retrieving the price tag count in 1st page
		PriceSpanCount = POMFile_SearchFeature.ProductClickLink.size();
		System.out.println(PriceSpanCount);

	}

	@AfterSuite
	public static void CloseBrowser() {
		driver.quit();
		extentReport.flush();
	}

}
