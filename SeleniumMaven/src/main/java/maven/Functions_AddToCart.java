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

import com.web.pom.AddToCart;

public class Functions_AddToCart {

	static WebDriver driver;
	
	
	@BeforeSuite
	public static void OpenWebBrowser() {
		
		driver=new ChromeDriver();
		driver.navigate().to("https://www.amazon.com/");
		
	}
	
	
	@Test
	public static void VerifyProductNavigation() {
		
		PageFactory.initElements(driver, AddToCart.class);
		
		AddToCart.ProductSectionLink.click();
		

		System.out.println("Type Brand Name: ");
		Scanner scanner=new Scanner(System.in);
		String BrandName=scanner.nextLine();
		
		System.out.println("Type OS Name: ");
		String OSName=scanner.nextLine();
		
		System.out.println("Type CPU Manufacturer Name: ");
		String CPUManufacturerName=scanner.nextLine();
		
		System.out.println("Type Screen Size: ");
		String ScreenSize=scanner.nextLine();
		
		System.out.println("Type Computer Memory Size: ");
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
				
				System.out.println("Yayyy!!! Product got found now");
				
				String ProductNameInProductDetailsPage=AddToCart.ProductDetailsPage_Title.getText();

				AddToCart.AddToCartButton.click();
				driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
				AddToCart.CartButton.click();
				
				String ProductNameInCartPage=AddToCart.CartDetailsPage_ProductName.getText();
				
				if(ProductNameInCartPage.equals(ProductNameInProductDetailsPage)) {
					System.out.println("Your Product is added to Cart Successfully..");
				}
				else {
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
		
	}
	
	
//	ASUS
//	Windows 10 S
//	Intel
//	15.6 Inches
//	8 GB

	
}
