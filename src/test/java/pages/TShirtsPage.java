package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class TShirtsPage {
	
	//Driver and logger
	WebDriver driver;
	
	
	public TShirtsPage(WebDriver driver) {
		this.driver = driver;	
	}
	
	//Locators
	private By firstItemInCategory = By.xpath("//div[@class='product-container']//h5/a[@class='product-name']");
	private By searchBar = By.id("search_query_top");
	private By searchButton = By.xpath("//button[@name='submit_search']");
	private By moreButton = By.xpath("//*[@class='button lnk_view btn btn-default']");
	private By addToWishListFirstItem = By.xpath("//*[@class='addToWishlist wishlistProd_1']");
	private By logedInErrorMsg = By.xpath("//p[contains(text(),'You must be logged in to manage your wishlist.')]");
	
	
	/*
	 * Get the text of the first item shown in a category
	 */
	public String getFirstCategoryItemText() {
		String firstItem = driver.findElement(firstItemInCategory).getText();
		return firstItem;
	}
	
	/*
	 * Search for an item in the search bar
	 */
	public void searchItem(String item) {
		driver.findElement(searchBar).sendKeys(item);
		driver.findElement(searchButton).click();
	}
	
	/*
	 * Hover first item
	 */
	public void hoverFirstCategoryItem() {
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(firstItemInCategory)).perform();
	}
	
	/*
	 *Click on the more button after hovering over the item
	 */
	public void clickOnMoreButton() {
		driver.findElement(moreButton).click();
	}
	
	/*
	 *Click on the add to wishlist button after hovering over the first item
	 */
	public void clickOnAddToWishlist() {
		driver.findElement(addToWishListFirstItem).click();
	}
	
	/*
	 *Validate that the message You must be logged in to manage your wishlist. is displayed when attempting to add an item to wishlist
	 */
	public void validateMustBeLoggedError() {
		Assert.assertTrue("Order was not placed", driver.findElement(logedInErrorMsg).isDisplayed());
		
	}

}
