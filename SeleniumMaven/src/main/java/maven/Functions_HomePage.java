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
	static ExtentTest Testcase1,Testcase2,ChildTestcase;
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
		
		Testcase1=extentReports.createTest("Verify whether scroll view is enable or not");
//		ChildTestcase=Testcase1.createNode("Verify whether scroll view is enable or not");
		
		javascript = (JavascriptExecutor) driver;
		//Check If horizontal scroll Is present or not.
		Boolean IsHorizontalScrollAvailable = (Boolean) javascript.executeScript("return document.documentElement.scrollWidth>document.documentElement.clientWidth;");
		// Check If vertical scroll Is present or not
		Boolean IsVerticalScrollAvailable = (Boolean) javascript.executeScript("return document.documentElement.scrollHeight>document.documentElement.clientHeight;");

//		ChildTestcase.log(Status.INFO, "Verify the Horizontal and Vertical Scroll view");
		
		if(IsVerticalScrollAvailable&&IsHorizontalScrollAvailable) {
			
			Testcase1.log(Status.INFO, "Vertical Scroll View - "+IsVerticalScrollAvailable);
			Testcase1.log(Status.INFO,"Horizontal Scroll View - "+IsHorizontalScrollAvailable);
			Testcase1.log(Status.PASS, "Vertical and Horizontal Scroll Views is enable for home page");
			
		}
		else {
			
			if((IsVerticalScrollAvailable==false)&&(IsHorizontalScrollAvailable==false)) {

				Testcase1.log(Status.INFO, "Vertical Scroll View - "+IsVerticalScrollAvailable);
				Testcase1.log(Status.INFO,"Horizontal Scroll View - "+IsHorizontalScrollAvailable);
				Testcase1.log(Status.FAIL, "Vertical and Horizontal Scroll Views is not enable for home page");	
			}

			else if((IsVerticalScrollAvailable==true)&&(IsHorizontalScrollAvailable==false)) {
				
				Testcase1.log(Status.INFO, "Vertical Scroll View - "+IsVerticalScrollAvailable);
				Testcase1.log(Status.INFO,"Horizontal Scroll View - "+IsHorizontalScrollAvailable);
				Testcase1.log(Status.FAIL, "Vertical Scroll View is enable But Horizontal Scroll View is not enable");	
			}
			
			else if((IsVerticalScrollAvailable==false)&&(IsHorizontalScrollAvailable==true)) {

				Testcase1.log(Status.INFO, "Vertical Scroll View - "+IsVerticalScrollAvailable);
				Testcase1.log(Status.INFO,"Horizontal Scroll View - "+IsHorizontalScrollAvailable);
				Testcase1.log(Status.FAIL, "Vertical Scroll View is not enable But Horizontal Scroll View is enable");	
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

		
		HomePage.SearchField.sendKeys(Text);
		HomePage.SearchIcon.click();
		AutoCorrectedResult=HomePage.AutoResult.getText();
		ExpectedString="\""+Text+"\"";
		Assert.assertEquals(AutoCorrectedResult, ExpectedString);
		//Creating Test case in report
		Testcase2=extentReports.createTest("Verifying the searched text is matched with generated result's text or not");
		Testcase2.log(Status.INFO, "Expected Result : "+ExpectedString +"</br>" + "Actual Result : "+AutoCorrectedResult);

		if(AutoCorrectedResult.equalsIgnoreCase(ExpectedString)) {
			Testcase2.log(Status.PASS,MarkupHelper.createLabel("Searched text's element is matched with generated result's text..", ExtentColor.GREEN) );
		}

	}


	
	@AfterSuite
	public static void CloseBrowser() {
		driver.close();
		extentReports.flush();
	}

}