package stepDefinition;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;

public class LoginFlipkart {
	
	WebDriver driver=null;
	 Properties prop;
	
	@Before
	public void setup() throws IOException
	{
		System.setProperty("webdriver.chrome.driver", "/Users/rahultiwari/Documents/Driver_for_selenium/chromedriver");
		FileReader reader =new FileReader("src/test/java/util/locators.properties");
		 driver = new ChromeDriver();
		  prop =new Properties();
		  prop.load(reader);
		  
		 
		
	}
	
	@After
	public void TearDown()
	{
		driver.close();
		
	}
	
	
	@Given("^I am on Flipkart home Page \"([^\"]*)\"$")
	public void i_am_on_Flipkart_home_Page(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		
		driver.get(arg1);
	    
	}

	@When("^i submit credentials \"([^\"]*)\" and \"([^\"]*)\"$")
	public void i_submit_credentials_and(String arg1, String arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		
		driver.findElement(By.xpath(prop.getProperty("EmailonLogin"))).sendKeys(arg1);
		driver.findElement(By.xpath(prop.getProperty("password"))).sendKeys(arg2);
		try
		{
		driver.findElement(By.xpath(prop.getProperty("login"))).click();
		}
		catch(Exception e)
		{
			System.out.println("Handled login button non-interactable issue");
			Thread.sleep(2000);
			boolean displayed=driver.findElement(By.xpath(prop.getProperty("login"))).isDisplayed();
			System.out.println(displayed+ "displayed");
			driver.findElement(By.xpath(prop.getProperty("login"))).click();
		}
	    
	}

	@Then("^i should be able to login$")
	public void i_should_be_able_to_login() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		
		String actual = driver.getTitle();
		
		String Expected="Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!";
		Assert.assertEquals(Expected, actual);
	    
	}
	
	@Then("^compare the logo$")
	public void compare_the_logo() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		WebElement element=driver.findElement(By.xpath("//img[@class='_1e_EAo']"));
		
		
		
		
		Screenshot s = new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(driver, element);
		ImageIO.write(s.getImage(), "PNG", new File("drivers/actual.png"));
		BufferedImage actualImage=s.getImage();
		
		BufferedImage expected=ImageIO.read(new File("drivers/flipkart_expected.png"));
		
		ImageDiffer imdiff = new ImageDiffer();
		ImageDiff imagediff=imdiff.makeDiff(expected, actualImage);
		boolean diffboolean=imagediff.hasDiff();
		
		if(diffboolean==true)
		{
			Assert.assertFalse("Image has differecne", diffboolean);
		}else
		{
			Assert.assertTrue("Image has no differecne", true);
		}
		
		
	   
	
	}

}
