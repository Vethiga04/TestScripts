package maven;

import java.util.Scanner;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.web.pom.HomePage;

/**
 * @author Vethiga
 *
 * 
 */
public class Functions_HomePage {

	public static WebDriver driver;
	static String AutoCorrectedResult;
	static String ExpectedString;
	static ExtentReports extentReports;
	static ExtentHtmlReporter extenthtmlreporter;
	static ExtentTest ParentTestcase,ChildTestcase,ChildTestcase2;
	static String Text;
	static JavascriptExecutor javascript;

	@BeforeSuite
	public static void OpenBrowser() {
		driver=new ChromeDriver();
		driver.navigate().to("https://www.amazon.com/");

		// creating extent report
		extentReports=new ExtentReports();
		extenthtmlreporter=new ExtentHtmlReporter("HomePageFunctionReport.html");
		extentReports.attachReporter(extenthtmlreporter);

	}

	
	@Test(priority = 1)
	public static void VerifyScrollView() {
//		Initiating Test case's name in extent reports
		
		ChildTestcase=ParentTestcase.createNode("Verify whether scroll view is enable or not");
		
		javascript = (JavascriptExecutor) driver;
		//Check If horizontal scroll Is present or not.
		Boolean IsHorizontalScrollAvailable = (Boolean) javascript.executeScript("return document.documentElement.scrollWidth>document.documentElement.clientWidth;");
		// Check If vertical scroll Is present or not
		Boolean IsVerticalScrollAvailable = (Boolean) javascript.executeScript("return document.documentElement.scrollHeight>document.documentElement.clientHeight;");

		ChildTestcase.log(Status.INFO, "Verify the Horizontal and Vertical Scroll view");
		
		if(IsVerticalScrollAvailable&&IsHorizontalScrollAvailable) {
			
			ChildTestcase.log(Status.INFO, "Vertical Scroll View - "+IsVerticalScrollAvailable);
			ChildTestcase.log(Status.INFO,"Horizontal Scroll View - "+IsHorizontalScrollAvailable);
			ChildTestcase.log(Status.PASS, "Vertical and Horizontal Scroll Views is enable for home page");
			
		}
		else {
			
			if((IsVerticalScrollAvailable==false)&&(IsHorizontalScrollAvailable==false)) {

				ChildTestcase.log(Status.INFO, "Vertical Scroll View - "+IsVerticalScrollAvailable);
				ChildTestcase.log(Status.INFO,"Horizontal Scroll View - "+IsHorizontalScrollAvailable);
				ChildTestcase.log(Status.FAIL, "Vertical and Horizontal Scroll Views is not enable for home page");	
			}

			else if((IsVerticalScrollAvailable==true)&&(IsHorizontalScrollAvailable==false)) {
				
				ChildTestcase.log(Status.INFO, "Vertical Scroll View - "+IsVerticalScrollAvailable);
				ChildTestcase.log(Status.INFO,"Horizontal Scroll View - "+IsHorizontalScrollAvailable);
				ChildTestcase.log(Status.FAIL, "Vertical Scroll View is enable & Horizontal Scroll View is not enable");	
			}
			
			else if((IsVerticalScrollAvailable==false)&&(IsHorizontalScrollAvailable==true)) {

				ChildTestcase.log(Status.INFO, "Vertical Scroll View - "+IsVerticalScrollAvailable);
				ChildTestcase.log(Status.INFO,"Horizontal Scroll View - "+IsHorizontalScrollAvailable);
				ChildTestcase.log(Status.FAIL, "Vertical Scroll View is not enable & Horizontal Scroll View is enable");	
			}
		}
		
	}

	
	
	@Test(priority = 2)
	public static void VerifySearchElementMatch() {

		PageFactory.initElements(driver, HomePage.class);

		//		Verifying the generated result is matched with searched text

		//		Get input from user
		System.out.print("Type any product name which you wanted to search (Please make sure the spelling is correct) : ");
		Scanner sc=new Scanner(System.in);
		Text=sc.nextLine();
		sc.close();

		ChildTestcase2=ParentTestcase.createNode("Verify searched result match");
		
		HomePage.SearchField.sendKeys(Text);
		HomePage.SearchIcon.click();
		AutoCorrectedResult=HomePage.AutoResult.getText();
		ExpectedString="\""+Text+"\"";
		Assert.assertEquals(AutoCorrectedResult, ExpectedString);
		//Creating Test case in report
		ChildTestcase2=extentReports.createTest("Verifying the searched text is matched with generated result's text or not");
		ChildTestcase2.log(Status.INFO, "Expected Result : "+ExpectedString +"</br>" + "Actual Result : "+AutoCorrectedResult);

		if(AutoCorrectedResult.equalsIgnoreCase(ExpectedString)) {
			ChildTestcase2.log(Status.PASS,MarkupHelper.createLabel("Searched text's element is matched with generated result's text..", ExtentColor.GREEN) );
		}

	}


	
	@AfterSuite
	public static void CloseBrowser() {
		driver.close();
		extentReports.flush();
	}

}