package getLastSMS;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDeviceActionShortcuts;
import io.appium.java_client.android.AndroidKeyCode;

import org.openqa.selenium.By;

public class Util_GetSMS extends ReadOTP{

	private String Code;

	public String getSMSTextFromNotification(AppiumDriver<?> driver) {

		//===============================Read message from Notification method==============================//
		/* this method will open the notifcation of the settings and get text attribute from the message element*/

		String deviceOS = (String) driver.executeScript("seetest:client.getProperty(\"device.os\")");

		if (deviceOS.contains("ANDROID")) {

			driver.executeScript("seetest:client.swipe(\"Up\", 0, 500)");
			if(driver.findElement(By.xpath("//*[contains(@text,'JPMC')]")).isDisplayed()) // This should be an identifier to uniquely identify the text of your message
			{ 
				if(driver.findElement(By.xpath("//*[contains(@text,'JPMC')]")).isDisplayed()){
					//driver.findElement(By.xpath("//*[@id='from' and @text='JA']")).click();

					String MessageContent = driver.findElement(By.xpath("//*[contains(@text,'Your') and parent::*/preceding::*/*[contains(@text,'IT-JPMC')]]")).getAttribute("text");

					System.out.println("EntireMessage is "+MessageContent);				
					
					//Assuming the message will be in the format "Your OTP is: 123456."

					String[] SplitLeft = MessageContent.split(":");
					String[] SplitRight = SplitLeft[1] .split("\\.");
					Code = SplitRight[0].trim(); 
					System.out.println(Code);

					((AndroidDeviceActionShortcuts) driver).pressKeyCode(AndroidKeyCode.BACK);
					
					//We can add command here to click on clear button if needed
					//driver.findElement(By.xpath("//*[@text='Clear']")).click();
				}
			}

		} else if (deviceOS.contains("IOS")) {

			driver.executeScript("seetest:client.swipe(\"Up\", 0, 500)");


			try{
				if(driver.findElement(By.xpath("//*[contains(@text,'MESSAGES')]")).isDisplayed()){
				}
			}catch (Exception e) {
				System.out.println("We are not in correct page, hence will swipe right");
				driver.executeScript("seetest:client.swipe(\"Right\", 0, 500)");

			}

			if(driver.findElement(By.xpath("//*[contains(@text,'MESSAGES')]")).isDisplayed()){

				String MessageContent = driver.findElement(By.xpath("//*[contains(@text,'OTP')]")).getAttribute("text");

				System.out.println("EntireMessage is "+MessageContent);					

				//Assuming the message will be in the format "Your OTP is: 123456."
				
				String[] SplitLeft = MessageContent.split(":");
				String[] SplitRight = SplitLeft[1] .split("\\.");
				Code = SplitRight[0].trim(); 
				System.out.println(Code);

				driver.executeScript("seetest:client.swipe(\"Down\", 0, 500)");

				//We can add command here to click on clear button if needed
				//driver.findElement(By.xpath("//*[@text='Clear notifications']")).click();
			}

		}
		return Code;
	}

}