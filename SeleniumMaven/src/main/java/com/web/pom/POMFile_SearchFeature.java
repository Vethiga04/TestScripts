package com.web.pom;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

/**
* @author Vethiga
*/

public class POMFile_SearchFeature {

	@FindBy(xpath="//select[@aria-describedby=\"searchDropdownDescription\"]")
	public static WebElement DropDownIcon;
	
	@FindBy(xpath="//h1/b")
	public static WebElement HomeAndKitchen_HeaderVerify;
	
	@FindBy(xpath="//input[@id=\"nav-search-submit-button\"]")
	public static WebElement SearchIcon;
	
	@FindBy(xpath="//img[@alt=\"Home Décor\"]")
	public static WebElement HomeDecorSection;
	
	@FindBy(xpath="//span/span[@class=\"a-size-base a-color-base a-text-bold\"]")
	public static WebElement HomeDecorText_Verify;
	
	@FindBy(xpath="//span[contains(text(),\"Decorative Pillows\")]")
	public static WebElement DecorativePillowsDepartment;
	
	@FindBy(xpath="//span/span[@class=\"a-size-base a-color-base a-text-bold\"]")
	public static WebElement decorativePillowsText_Verify;
	
	@FindBy(xpath="//a[@data-routing=\"off\"]/span[contains(text(),\"Throw Pillow Covers\")]")
	public static WebElement ThrowPillowCoversSelection;
	
	@FindBy(xpath="//span/span[@class=\"a-size-base a-color-base a-text-bold\"]")
	public static WebElement ThrowPillowCoversText_Verify;
	
	@FindBy(xpath="//a[@title=\"Red\"]/span/div[@class=\"colorsprite aok-float-left\"]")
	public static WebElement ColorSelection;
	
	@FindBy(xpath="//li[@class=\"s-sprite-grid aok-align-top aok-float-left\"]/span[@class=\"a-list-item\"]/child::a[@class=\"a-link-normal s-navigation-item\"]")
	public static List<WebElement> ColorSelection_Verify;
	
	@FindBy(xpath="//span[contains(text(),\"Clear\")]")
	public static WebElement ClearTextColorSelection_Verify;
	
	@FindBy(xpath="//span[@class=\"a-list-item\"]/a/span[contains(text(),\"$25 to $50\")]")
	public static WebElement PriceSelection;

	@FindBy(xpath="//input[@placeholder=\"Min\"]")
	public static WebElement PriceMin_Verify;
	
	@FindBy(xpath="//input[@placeholder=\"Max\"]")
	public static WebElement PriceMax_Verify;
	
	@FindBy(xpath="//span[@class=\"a-price-whole\"]")
	public static WebElement PriceSpanOne;
	
	@FindBy(xpath="//section[@aria-label=\"4 Stars & Up\"]")
	public static WebElement Rating;
	
	@FindBy(xpath="//div/img[@data-image-latency=\"s-product-image\"]")
	public static List<WebElement> ProductClickLink;
	
	@FindBy(xpath="(//td[@class=\"a-span12\"]/span)[1]")
	public static WebElement ProductPriceInDetailedView;
	
	@FindBy(xpath="//h1[@id=\"title\"]")
	public static WebElement ProductDetailedNameInDetailedView;
	
	@FindBy(xpath="(//label[contains(text(),\"Color:\")]/following-sibling::span[@class=\"selection\"])[1]")
	public static WebElement ProductColorInDetailedView;
}
