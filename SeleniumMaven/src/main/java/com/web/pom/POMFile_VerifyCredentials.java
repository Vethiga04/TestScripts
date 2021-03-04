package com.web.pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class POMFile_VerifyCredentials {
		
		@FindBy(xpath="//a/span[2]/span[@class=\"nav-icon nav-arrow null\"]")
		public static WebElement SigninBtn_MainPage;

		@FindBy(xpath="//a[@id=\"createAccountSubmit\"]")
		public static WebElement CreateAccountBtn;
		
		@FindBy(xpath="//input[@id=\"ap_customer_name\"]")
		public static WebElement YourNamefield;
		
		@FindBy(xpath="//input[@id=\"ap_email\"]")
		public static WebElement Emailfield;
		
		@FindBy(xpath="//input[@id=\"ap_password\"]")
		public static WebElement Passwordfield;
		
		@FindBy(xpath="//input[@id=\"ap_password_check\"]")
		public static WebElement Re_Passwordfield;

		@FindBy(xpath="//input[@id=\"continue\"]")
		public static WebElement SubmitBtn;
		
		@FindBy(xpath="//input[@id=\"continue\"]")
		public static WebElement CreatingAmazonAccountBtn;
		
		@FindBy(xpath="//div[@class=\"a-section auth-pagelet-container\"]//h4[@class=\"a-alert-heading\"]")
		public static WebElement ErrorMsgHeader;
		
		@FindBy(xpath="//h4[@class=\"a-alert-heading\"]")
		public static WebElement SamePageErrorMsg;
		
		@FindBy(xpath="//label[@class=\"a-form-label\"]")
		public static WebElement SuccessPage;
		
		
		@FindBy(xpath="//input[@name=\"cvf_captcha_captcha_action\"]")
		public static WebElement SuccessPageNavigation;
		
		
		@FindBy(xpath="//div[@class=\"a-alert-content\" and contains(text(),'  Passwords must match')]")
		public static WebElement PasswordMismatchError;
		
}