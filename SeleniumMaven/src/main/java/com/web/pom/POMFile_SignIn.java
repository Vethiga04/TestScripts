package com.web.pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Vethiga
 */

public class POMFile_SignIn {

	@FindBy(xpath = "//input[@type=\"email\"]")
	public static WebElement EmailInputField;

	@FindBy(xpath = "//input[@id=\"continue\"]")
	public static WebElement ContinueBtn_SignIn;

	@FindBy(xpath = "//input[@type=\"password\"]")
	public static WebElement PasswordInputField;

	@FindBy(xpath = "//input[@id=\"signInSubmit\"]")
	public static WebElement SignInBtn_ConfirmationPage;

	@FindBy(xpath = "//input[@type=\"checkbox\"]")
	public static WebElement Checkbox;

}
