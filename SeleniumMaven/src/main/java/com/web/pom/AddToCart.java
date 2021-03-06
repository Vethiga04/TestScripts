package com.web.pom;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Vethiga
 *
 * 
 */
public class AddToCart {

	@FindBy(xpath="(//div[@class=\"a-cardui-footer\"])[10]")
	public static WebElement ProductSectionLink;
	
	@FindBy(xpath="//a[@class=\"a-link-normal a-text-normal\"]/span[@class=\"a-size-base-plus a-color-base a-text-normal\"]")
	public static List<WebElement> ProductsLink;
	
	@FindBy(xpath="(//td[@class=\"a-span9\"]/span[@class=\"a-size-base\"])[1]")
	public static WebElement BrandName;
	
	@FindBy(xpath="(//td[@class=\"a-span9\"]/span[@class=\"a-size-base\"])[2]")
	public static WebElement OSName;
	
	@FindBy(xpath="(//td[@class=\"a-span9\"]/span[@class=\"a-size-base\"])[3]")
	public static WebElement CPUManufacturerName;
	
	@FindBy(xpath="(//td[@class=\"a-span9\"]/span[@class=\"a-size-base\"])[4]")
	public static WebElement ScreenSize;
	
	@FindBy(xpath="(//td[@class=\"a-span9\"]/span[@class=\"a-size-base\"])[5]")
	public static WebElement ComputerMemorySize;

	@FindBy(xpath="//span[@id=\"productTitle\"]")
	public static WebElement ProductDetailsPage_Title;
	
	@FindBy(xpath="//input[@id=\"add-to-cart-button\"]")
	public static WebElement AddToCartButton;
	
	@FindBy(xpath="//span[@id=\"attach-sidesheet-view-cart-button\"]/span/input[@class=\"a-button-input\"]")
	public static WebElement CartButton;

	@FindBy(xpath="(//a[@class=\"a-link-normal sc-product-link\"]/child::span)[6]")
	public static WebElement CartDetailsPage_ProductName;

}
