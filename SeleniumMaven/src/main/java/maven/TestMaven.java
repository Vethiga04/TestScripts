package maven;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class TestMaven {
	WebDriver driver;
	ExtentReports extentReports;
	ExtentHtmlReporter HtmlReporter;
	ExtentTest Testcase;

	@BeforeSuite
	public void LaunchBrowser() {
		extentReports = new ExtentReports();
		HtmlReporter = new ExtentHtmlReporter("ExtentReport.html");
		extentReports.attachReporter(HtmlReporter);
		driver = new ChromeDriver();
		driver.navigate().to("https://www.amazon.com/");
	}

	@Test
	public void Test() {
		Testcase = extentReports.createTest("Verifying Title");
		Testcase.log(Status.INFO, "Verify Browser Title containing text");
		String Title = driver.getTitle();
		Testcase.log(Status.INFO, "Expected Browser's Title containing text is Amazon");
		Testcase.log(Status.INFO, "Actual Browser's Title is : " + Title);
		if (Title.contains("Amazon")) {
			Testcase.log(Status.PASS, "Expected and Actual results are matched");
		} else {
			Testcase.log(Status.FAIL, "Expected and Actual results are not matched");
		}
	}

	@AfterSuite
	public void CloseBrowser() {
		driver.quit();
		extentReports.flush();
	}

}
