package parking.base;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.logging.LogManager;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class BaseMethods {
	
	
	protected AndroidDriver driver;
	protected static Logger log;

	@BeforeTest(alwaysRun = true)
	public void setCapabilities() throws MalformedURLException {

		//LOCAL CONNECTION
		// DesiredCapabilities caps = new DesiredCapabilities();
		// caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Samsung S22 API 31");
		// caps.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");
		// caps.setCapability(MobileCapabilityType.BROWSER_NAME,"chrome");
		// caps.setCapability("chromedriverExecutable",System.getProperty("user.dir")+"\\src\\main\\resources\\chromedriver.exe");
		
		// URL localUrl = new URL("http://127.0.0.1:4723/wd/hub");
		// driver = new AndroidDriver(localUrl, caps);

		
		//SAUCE LABS CONNECTION
		MutableCapabilities caps = new MutableCapabilities();
		caps.setCapability("platformName","Android");
		caps.setCapability("appium:deviceName","Samsung Galaxy Tab S6 GoogleAPI Emulator");
		caps.setCapability("appium:deviceOrientation", "portrait");
		caps.setCapability("browserName","chrome");
		caps.setCapability("appium:platformVersion","12.0");
		// caps.setCapability("appium:app", "storage:filename=app-qa-release.apk");
		MutableCapabilities sauceOptions = new MutableCapabilities();
		sauceOptions.setCapability("name", "Parking - Test");
		caps.setCapability("sauce:options", sauceOptions);

		URL sauceLabsUrl = new URL("https://jcgularte:636020b5-5aea-420c-8d29-01d7b96233db@ondemand.us-west-1.saucelabs.com:443/wd/hub");
		driver = new AndroidDriver(sauceLabsUrl, caps);

		System.out.println("App is up and running.");
		driver.get("https://uat.parking.com");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}
	
	@AfterTest(alwaysRun = true)
	public void shutDown() {
		driver.quit();
	}
	
}
