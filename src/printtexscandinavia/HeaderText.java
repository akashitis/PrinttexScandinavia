package printtexscandinavia;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HeaderText {

	WebDriver driver;

@BeforeSuite
public void setUp() {
	WebDriverManager.firefoxdriver().setup();
	 driver=new FirefoxDriver();	
}

@Test(priority = 1)
public void openUrl() throws IOException {
	FileInputStream f=new FileInputStream("../PrinttexScandinavia/property file/data.properties");
	
	Properties p=new Properties();
	
	p.load(f);
	
	String url = p.getProperty("url1");
	
	driver.get(url);
	
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
}

//verify head text compare to customer text
@Test(priority=2)
public void HeadText() throws InterruptedException {
	Thread.sleep(5000);
	WebElement headtext = driver.findElement(By.tagName("h1"));
	String acttext = headtext.getText();
	String exptext="PRINTTEX SCANDINAVIA";
	assertEquals(acttext, exptext);
}

//check left side logo size
@Test(priority=3)
public void leftLogo() throws InterruptedException {
	Thread.sleep(3000);
	WebElement leftlogo = driver.findElement(By.xpath(".//img[@class='logoimg']"));
	boolean l1 = leftlogo.isDisplayed();
	if (l1) {
		System.out.println("LeftLogo is Displayed");
	} else {
        System.out.println("Left Logo is Not Displayed");
	}
	Dimension leftlogosize = leftlogo.getSize();
	int h1 = leftlogosize.getHeight();
	int w1 = leftlogosize.getWidth();
	
    System.out.println(h1+" "+w1);
}

@Test(priority = 4)
public void slideshowImageCount() throws InterruptedException {
	Thread.sleep(3000);
	List<WebElement> slimg = driver.findElements(By.xpath(".//div[@class='slideshow-container']//img"));
	int count=0;
	
	for (int i = 0; i < slimg.size(); i++) {
		 boolean img1 = slimg.get(i).isDisplayed();
		 if (img1) {
			System.out.println("img is displayed");
		 }
		 
		 count++;	
	}
	System.out.println(count);
	
}

@AfterSuite
public void tearDown() {
	driver.quit();
	
}
	
}
