package com.web.pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Vethiga
 *
 * 
 */
public class HomePage {
	
	@FindBy(xpath="//input[@id=\"twotabsearchtextbox\"]")
	public static WebElement SearchField;
	
	@FindBy(xpath="//input[@id=\"nav-search-submit-button\"]")
	public static WebElement SearchIcon;
	
	@FindBy(xpath="//span[@class=\"a-color-state a-text-bold\"]")
	public static WebElement AutoResult;
}