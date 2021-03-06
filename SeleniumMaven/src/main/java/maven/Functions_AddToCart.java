package maven;
/**
 * @author Vethiga
 *
 * 
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.web.pom.AddToCart;

public class Functions_AddToCart {

	static WebDriver driver;
	static ExtentReports extentreports;
	static ExtentHtmlReporter extentHtmlreporter;
	static ExtentTest TestCase1,TestCase2;

	@BeforeSuite
	public static void OpenWebBrowser() {

		driver=new ChromeDriver();
		driver.navigate().to("https://www.amazon.com/");

		extentreports=new ExtentReports();
		extentHtmlreporter=new ExtentHtmlReporter("ExtentReport-AddToCartFeature.html");
		extentreports.attachReporter(extentHtmlreporter);

	}


	@Test
	public static void VerifyProductNavigation() {

		PageFactory.initElements(driver, AddToCart.class);

		AddToCart.ProductSectionLink.click();

		TestCase1=extentreports.createTest("Verify whether customer searched product(from user's input) is listed in product's page or not");

		System.out.print("Type Brand Name: ");
		Scanner scanner=new Scanner(System.in);
		String BrandName=scanner.nextLine();

		System.out.print("Type OS Name: ");
		String OSName=scanner.nextLine();

		System.out.print("Type CPU Manufacturer Name: ");
		String CPUManufacturerName=scanner.nextLine();

		System.out.print("Type Screen Size: ");
		String ScreenSize=scanner.nextLine();

		System.out.print("Type Computer Memory Size: ");
		String ComputerMemorySize=scanner.nextLine();

		scanner.close();

		for (WebElement Product : AddToCart.ProductsLink) {


			Actions actions=new Actions(driver);
			actions.keyDown(Keys.LEFT_CONTROL).click(Product).keyUp(Keys.LEFT_CONTROL).build().perform();

			List<String> tab=new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tab.get(1));
			driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);


			if(BrandName.equals(AddToCart.BrandName.getText()) && OSName.equals(AddToCart.OSName.getText()) 
					&& CPUManufacturerName.equals(AddToCart.CPUManufacturerName.getText()) && ScreenSize.equals(AddToCart.ScreenSize.getText())
					&& ComputerMemorySize.equals(AddToCart.ComputerMemorySize.getText())) {

				TestCase1.log(Status.INFO, "<h4 style=\"background-color:DodgerBlue;\">User's input</h4>" + "</br> Brand Name: "+BrandName+"</br> OS Name: "+OSName+"</br> CPU Manufacturer Name: "
						+CPUManufacturerName+"</br> Screen Size: "+ScreenSize+"</br> Computer Memory Size: "+ComputerMemorySize);
				
				TestCase1.log(Status.PASS, "Product's details are matched with user's input");
				System.out.println("Yayyy!!! Product got found now");

				TestCase2=extentreports.createTest("Verify whether the product is added to cart successfully or not");
				String ProductNameInProductDetailsPage=AddToCart.ProductDetailsPage_Title.getText();

				AddToCart.AddToCartButton.click();
				driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
				AddToCart.CartButton.click();

				String ProductNameInCartPage=AddToCart.CartDetailsPage_ProductName.getText();

				if(ProductNameInCartPage.equals(ProductNameInProductDetailsPage)) {
					
					TestCase2.log(Status.PASS,"Product is added to cart successfully");
					System.out.println("Your Product is added to Cart Successfully..");
					
				}
				else {
					
					TestCase2.log(Status.FAIL, "Product is failed to add into cart");
					System.out.println("Ooops Sorry...Your Product is failed to add into Cart..");
				}

				driver.close();
				driver.switchTo().window(tab.get(0));
				break;

			}
			else {
				System.out.println("Sorry... Product is not found");
			}


			driver.close();
			driver.switchTo().window(tab.get(0));


		}


	}


	@AfterSuite
	public static void CloseBrowser() {

		driver.close();
		extentreports.flush();

	}


	//	ASUS
	//	Windows 10 S
	//	Intel
	//	15.6 Inches
	//	8 GB


}
