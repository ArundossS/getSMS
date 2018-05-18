package getLastSMS;


import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.net.URL;
import java.net.MalformedURLException;
import java.util.logging.Level;

public class ReadOTP{

	protected AppiumDriver<WebElement> driver = null;

	DesiredCapabilities dc = new DesiredCapabilities();

	@BeforeMethod
	public void setUp() throws MalformedURLException {

		/*dc.setCapability(MobileCapabilityType.UDID, ""); //use your android device's UDID here
		dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.experitest.ExperiBank");
		dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".LoginActivity");
		dc.setCapability("noReset","true");
		dc.setCapability("dontGoHomeOnQuit", true);
		driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
*/
		dc.setCapability(MobileCapabilityType.UDID, ""); ////use your iOS device's UDID here
        dc.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.experitest.ExperiBank");
        dc.setCapability("noReset","true");
        dc.setCapability("dontGoHomeOnQuit", true);
        driver = new IOSDriver<>(new URL("http://localhost:4723/wd/hub"), dc);

        driver.setLogLevel(Level.INFO);
		
	}

	@Test
	
	//In this specific scenario, we are launching Eribank and logging into it. After logging in, the flow will open notifications and check if there will be any notifications and gets it.
	//And, you will have to replace this code with your relevant capabilities and application flow.
	
	public void AppLoginWithOTP() throws InterruptedException {
		driver.context("NATIVE_APP");
		driver.findElement(By.xpath("//*[@id='usernameTextField']|//*[@accessibilityLabel='usernameTextField']")).sendKeys("company");
		driver.findElement(By.xpath("//*[@id='passwordTextField']|//*[@accessibilityLabel='passwordTextField']")).sendKeys("company");
		driver.findElement(By.xpath("//*[@text='Login']|//*[@text='loginButton']")).click();
		Thread.sleep(1500);
		String Code = new Util_GetSMS().getSMSTextFromNotification(driver);
		System.out.println("Code ::::::: " + Code);
		Thread.sleep(3000);

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}