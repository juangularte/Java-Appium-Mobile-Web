package parking.tests;
import java.net.MalformedURLException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import parking.base.BaseMethods;

public class LoginTests extends BaseMethods{

	
	@Parameters({ "username", "password" })
	@Test(priority = 1)
	public void positiveLogin(String username, String password) throws MalformedURLException{

		By logo = (By.xpath("//div[@class='v-main-navigation']/nav/div//div[@aria-label='Parking.com']"));
		By menu_icon = (By.xpath("//div[@class='v-main-navigation']/nav/button/i"));
		By login_option = (By.xpath("//li//a[contains(text(), 'Log In')]"));
		By login_screen_description = (By.xpath("//p[contains(text(), 'reservations')]"));
		By email_field = (By.cssSelector("#email_field"));
		By password_field = (By.cssSelector("#password_field"));
		By login_button = (By.xpath("//button[text()='Log In']"));
		By sign_out_option = (By.cssSelector(".fa-sign-out"));
		

		Assert.assertTrue(waitForElementToBeVisible(logo).isDisplayed());
		waitForElementToBeClickable(menu_icon).click();
		waitForElementToBeClickable(login_option).click();
		Assert.assertEquals(waitForElementToBeVisible(login_screen_description).getText(), "Keep all your reservations, purchases, and vehicle information in one place for an easy checkout.");
		waitForElementToBeVisible(email_field).sendKeys(username);
		waitForElementToBeVisible(password_field).sendKeys(password);
		waitForElementToBeClickable(login_button).click();
		waitForElementToBeClickable(menu_icon).click();
		Assert.assertTrue(waitForElementToBeVisible(sign_out_option).isDisplayed());
	}
	
	@Parameters({ "username", "password" , "errorMsg"})
	@Test(priority = 2)
	public void negativeLoginWithInvalidCredentials(String username, String password, String errorMsg) throws MalformedURLException{
		
		By logo = (By.xpath("//div[@class='v-main-navigation']/nav/div//div[@aria-label='Parking.com']"));
		By menu_icon = (By.xpath("//div[@class='v-main-navigation']/nav/button/i"));
		By login_option = (By.xpath("//li//a[contains(text(), 'Log In')]"));
		By login_screen_description = (By.xpath("//p[contains(text(), 'reservations')]"));
		By email_field = (By.cssSelector("#email_field"));
		By password_field = (By.cssSelector("#password_field"));
		By login_button = (By.xpath("//button[text()='Log In']"));
		By error_message = (By.cssSelector(".message-box.message-box--error > span"));

		Assert.assertTrue(waitForElementToBeVisible(logo).isDisplayed());
		waitForElementToBeClickable(menu_icon).click();
		waitForElementToBeClickable(login_option).click();
		Assert.assertEquals(waitForElementToBeVisible(login_screen_description).getText(), "Keep all your reservations, purchases, and vehicle information in one place for an easy checkout.");
		waitForElementToBeVisible(email_field).sendKeys(username);
		waitForElementToBeVisible(password_field).sendKeys(password);
		waitForElementToBeClickable(login_button).click();
		Assert.assertEquals(waitForElementToBeVisible(error_message).getText(), errorMsg);
	}

	private WebDriverWait wait_for_element() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		return wait;
	}
	
	protected WebElement waitForElementToBeVisible(By locator) {
		
		return wait_for_element().until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	protected WebElement waitForElementToBeClickable(By locator) {
		
		return wait_for_element().until(ExpectedConditions.elementToBeClickable(locator));
	}
}