package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage {
	//Driver and logger
		WebDriver driver;
		
		
		public SearchPage(WebDriver driver) {
			this.driver = driver;	
		}
		
		//Locators
		private By firstItemInSearch = By.xpath("//div[@class='product-container']//h5/a[@class='product-name']");
		
		/*
		 * Get the text of the first item shown in a category
		 */
		public String getFirstSearchItemText() {
			String firstItem = driver.findElement(firstItemInSearch).getText();
			return firstItem;
		}
		
		/*
		 * Validate that same product is displayed on searched page with same details which were displayed on T-Shirt's page.
		 */
		public void validateItemFromSearch(String txt) {
			Assert.assertEquals("Item did not match", txt, getFirstSearchItemText());
		}

}
