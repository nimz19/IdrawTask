package test;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IDrawTest {
	private static RemoteWebDriver driver;
	Actions action = new Actions(driver);

	@BeforeClass
	public static void init() {
		// Set up the path to the ChromeDriver executable
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\User\\IdeaProjects\\SeleniumCats\\src\\test\\resources\\webdrivers\\chromedriver.exe");

		// Set ChromeOptions for any specific Chrome configurations
		ChromeOptions cOptions = new ChromeOptions();
		cOptions.setHeadless(false);  // Change to true if headless mode is preferred

		// Initialize the ChromeDriver with ChromeOptions
		driver = new ChromeDriver(cOptions);

		// Set the window size
		driver.manage().window().setSize(new Dimension(1366, 768));
	}

	@Before
	public void setup() {
		// Navigate directly to the drawing URL
		driver.get("https://www.youidraw.com/apps/painter/");
	}

	@AfterClass
	public static void tearDown() {
		driver.quit();
	}

	@Test
	public void drawInital() throws InterruptedException {
		// Find the drawing canvas
		WebElement canvas = driver.findElement(By.cssSelector("#catch"));

		// Start drawing by clicking on the canvas
		canvas.click();

		// Drawing the letter "I"
		action.moveToElement(canvas)
				.clickAndHold()                       // Start at the top of "I"
				.moveByOffset(0, 200)                 // Draw vertical line down
				.release()                            // Finish vertical line
				.perform();

		action.moveByOffset(-50, -200)               // Move to the top-left for the top horizontal line
				.clickAndHold()
				.moveByOffset(100, 0)                 // Draw top horizontal line
				.release()
				.perform();

		action.moveByOffset(-100, 200)                // Move to the bottom-left for the bottom horizontal line
				.clickAndHold()
				.moveByOffset(100, 0)                 // Draw bottom horizontal line
				.release()
				.perform();

		Thread.sleep(2000);  // Short pause before drawing the next letter

		// Drawing the letter "Z"
		action.moveByOffset(200, -200)               // Move to starting position for "Z"
				.clickAndHold()
				.moveByOffset(100, 0)                 // Draw top horizontal line
				.moveByOffset(-100, 200)              // Draw diagonal line down
				.moveByOffset(100, 0)                 // Draw bottom horizontal line
				.release()
				.perform();

		Thread.sleep(10000);  // Wait to observe the drawing, if needed
	}
}
