package utilities;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.github.bonigarcia.wdm.WebDriverManager;


public class DriverSetup {
	/* Driver set up for chrome and firefox
	 * @Author:AlfonsoZ
	 */
	private WebDriver driver = null;
	private static final Logger LOG = LoggerFactory.getLogger(DriverSetup.class);

	public WebDriver setUpDriver(String browser) {
		switch (browser) {
			case "chrome": {
				LOG.info("Chrome Driver selected");
				WebDriverManager.chromedriver().setup();
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.setCapability("acceptInsecureCerts", true);
				driver = new ChromeDriver(chromeOptions);
				driver.manage().window().maximize();
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
				break;
			}

			default: {
				LOG.info("Chrome Driver selected by default");
				WebDriverManager.chromedriver().setup();
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.setCapability("acceptInsecureCerts", true);
				driver = new ChromeDriver(chromeOptions);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
				break;
			}

		}
		
		return driver;
	}

}
