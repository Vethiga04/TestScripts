package maven;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.web.pom.POMFile_VerifyCredentials;

public class VerifyCredentialsInAmazon {

	static WebDriver driver;
	static String a;
	static List<String> ResultReason = new ArrayList<String>();
	static Row rowvalue;
	static Iterator<Cell> CellIterator;
	static Cell cellvalue;
	static Iterator<Row> RowIterator;

	static List<String> Email = new ArrayList<String>();
	static List<String> Username = new ArrayList<String>();
	static List<String> Password = new ArrayList<String>();
	static List<String> Re_Password = new ArrayList<String>();
	static List<String> OutputResult = new ArrayList<String>();
	static List[] str = { Username, Email, Password, Re_Password };
	static int k, j, i, x = 0;
	static int CellTotalCount, RowTotalCount;
	static List[] TestResultArray = { OutputResult, ResultReason };

	static ExtentReports extentReports;
	static ExtentHtmlReporter htmlReporter;
	static ExtentTest ParentTestcase, ChildTestcase;

  
	public static void CredentialsIntegration() throws IOException {
		for (int b = 0; b <= RowTotalCount; b++) {
			x++;
			CreateAccount(Username.get(b), Email.get(b), (Password).get(b), (Re_Password).get(b));

		}
	}

	
	public static void main(String[] args) throws IOException {
		extentReports = new ExtentReports();
		htmlReporter = new ExtentHtmlReporter("ExcelSheet_ExtentReport.html");
		extentReports.attachReporter(htmlReporter);
		ParentTestcase = extentReports.createTest("Verify credentials for signup");

		
		ExcelReadData();
		System.out.println("Username values: " + Username);
		System.out.println("Email values: " + Email);
		System.out.println("Password values: " + Password);
		System.out.println("Password values: " + Re_Password);
		CredentialsIntegration();
		System.out.println(OutputResult);
		System.out.println(ResultReason);
		System.out.println(RowTotalCount);
		InputExcelData();

	}

	public static void CreateAccount(String username, String email, String password, String re_password) throws IOException {

		driver = new ChromeDriver();
		driver.get("https://www.amazon.com/");

		PageFactory.initElements(driver, POMFile_VerifyCredentials.class);


		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click()", POMFile_VerifyCredentials.SigninBtn_MainPage);

		POMFile_VerifyCredentials.CreateAccountBtn.click();

		POMFile_VerifyCredentials.YourNamefield.sendKeys(username);
		POMFile_VerifyCredentials.Emailfield.sendKeys(email);
		POMFile_VerifyCredentials.Passwordfield.sendKeys(password);
		POMFile_VerifyCredentials.Re_Passwordfield.sendKeys(re_password);

		POMFile_VerifyCredentials.CreatingAmazonAccountBtn.click();

		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);

		ChildTestcase = ParentTestcase.createNode("Test Data : " + x);
		ChildTestcase.log(Status.INFO,
				"Verify following credentials whether they are valid or not to proceed the Signup<br/>Username is: "
						+ username + "<br/>Email is: " + email + "<br/>Password is: " + password);

		if (driver.getTitle().equals("Authentication required")) {
			ResultReason.add("Credentials are valid to Signup");
			OutputResult.add("PASS");
			ChildTestcase.log(Status.PASS,
					MarkupHelper.createLabel("Credentials are valid to SignUp", ExtentColor.GREEN));

		}
		else if (POMFile_VerifyCredentials.PasswordMismatchError.isDisplayed()) {
			ResultReason.add("Passwords are not matched");
			OutputResult.add("FAIL");
			ChildTestcase.log(Status.FAIL,
					MarkupHelper.createLabel("Passwords doesn't match to proceed", ExtentColor.RED));
		}

		else if (POMFile_VerifyCredentials.SamePageErrorMsg.isDisplayed()) {
				String Error_ReasonInternal = POMFile_VerifyCredentials.SamePageErrorMsg.getText();
				ResultReason.add(Error_ReasonInternal + "-Internal Error");
				OutputResult.add("FAIL");
				ChildTestcase.log(Status.FAIL,
						MarkupHelper.createLabel("Credentials are not valid to Signup", ExtentColor.RED));

		}

		else if (!POMFile_VerifyCredentials.SamePageErrorMsg.isDisplayed()) {

			if (POMFile_VerifyCredentials.ErrorMsgHeader.isDisplayed()) {
				String Error_Reason = POMFile_VerifyCredentials.ErrorMsgHeader.getText();
				ResultReason.add(Error_Reason);
				OutputResult.add("FAIL");
				ChildTestcase.log(Status.FAIL, MarkupHelper.createLabel(
						"Email address is already in use,so Credentials are not valid to proceed", ExtentColor.RED));
			}
		}

		driver.quit();

	}

	public static void InputExcelData() throws IOException {
		int b = 0;
		for (int a = 0; a <= RowTotalCount; a++) {
			for (int c = 1; c <= TestResultArray.length; c++) {
				FileInputStream fileInputStream1 = new FileInputStream(
						"G:\\Learning_V\\Automation\\DataDriven\\Apache_POI\\AccountCredentials.xlsx");
				XSSFWorkbook workbook1 = new XSSFWorkbook(fileInputStream1);
				XSSFSheet sheet1 = workbook1.getSheetAt(0);
				String Val = (String) TestResultArray[c - 1].get(b);
				sheet1.getRow(a).createCell(3 + c).setCellValue(Val);
				workbook1.close();

			}
			b++;
		}
		extentReports.flush();
	}

	public static void ExcelReadData() throws IOException {
		// Navigating to File location
		FileInputStream fileInputStream = new FileInputStream(
				"G:\\Learning_V\\Automation\\DataDriven\\Apache_POI\\AccountCredentials.xlsx");

		// Navigating to work book
		XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

		// Navigating to excelsheet
		XSSFSheet sheet = workbook.getSheetAt(0);

		// Iterating the rows in the sheet
		Iterator<Row> RowIterator = sheet.iterator();

		// verify whether next row is having value or not
		while (RowIterator.hasNext()) {

			// Get the row value
			Row rowvalue = RowIterator.next();

			// Iterating the cells
			Iterator<Cell> CellIterator = rowvalue.iterator();

			// Check whether cells are having values on the next or not
			while (CellIterator.hasNext()) {

				// Get the cell value
				Cell cellvalue = CellIterator.next();

				// Get the Total column count
				CellTotalCount = cellvalue.getColumnIndex();

				// Get the total row count
				RowTotalCount = rowvalue.getRowNum();

				
				if (CellTotalCount >= 4) {
					cellvalue.setBlank();
					// rowvalue.removeCell(cellvalue);
				}

			}
		}

		System.out.println(CellTotalCount);
		System.out.println(RowTotalCount);

		for (int j = 0; j <= CellTotalCount; j++) {
			for (int i = 0; i <= RowTotalCount; i++) {

				// getting all the cell values
				XSSFCell CellValue = sheet.getRow(i).getCell(j);

				// Identifying Cellvalue's type
				switch (CellValue.getCellType()) {

				// if cell value is string, get string value of the cell
				case STRING:
					str[k].add(CellValue.getStringCellValue());
					break;
				case NUMERIC:
					str[k].add(CellValue.getNumericCellValue());
					break;
				case BOOLEAN:
					str[k].add(CellValue.getBooleanCellValue());
					break;

				}
			}
			// increasing id value of str array[0,1,2..]
			k++;
		}

	}
}
