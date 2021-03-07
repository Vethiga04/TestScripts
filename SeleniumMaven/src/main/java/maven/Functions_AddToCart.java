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
	static boolean IsProceed;

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

		String[] ProductDetails=new String[5];
		String[] DetailTitle= {"Type Brand Name: ","Type OS Name: ","Type CPU Manufacturer Name: ","Type Screen Size: ","Type Computer Memory Size: "};

		Scanner scanner=new Scanner(System.in);
		int j = 0;
		for(int i=0;i<5;i++) {
			System.out.print(DetailTitle[j]);
			ProductDetails[i]= scanner.nextLine();
			j++;
		}
		
		
		scanner.close();

		for (WebElement Product : AddToCart.ProductsLink) {


			Actions actions=new Actions(driver);
			actions.keyDown(Keys.LEFT_CONTROL).click(Product).keyUp(Keys.LEFT_CONTROL).build().perform();

			List<String> tab=new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tab.get(1));
			driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);

			String[] DetailsTitleInPage= {AddToCart.BrandName.getText(),AddToCart.OSName.getText(),AddToCart.CPUManufacturerName.getText(),
					AddToCart.ScreenSize.getText(),AddToCart.ComputerMemorySize.getText()};
			int a=0;
			for(int h=0;h<5;h++) {
				if((ProductDetails[h].equalsIgnoreCase(DetailsTitleInPage[a]))){
					IsProceed=true;
				}
			a++;
			}

			if(IsProceed) {

				TestCase1.log(Status.INFO, "<span style=\"color:blue;font-weight:bold\">User's input</span>" + "</br> Brand Name: "+ProductDetails[0]+"</br> OS Name: "+ProductDetails[1]+"</br> CPU Manufacturer Name: "
						+ProductDetails[2]+"</br> Screen Size: "+ProductDetails[3]+"</br> Computer Memory Size: "+ProductDetails[4]);
				
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
