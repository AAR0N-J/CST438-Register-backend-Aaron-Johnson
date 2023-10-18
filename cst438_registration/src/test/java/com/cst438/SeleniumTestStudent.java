package com.cst438;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;

/*
 * This example shows how to use selenium testing using the web driver
 * with Chrome browser.
 *
 *  - Buttons, input, and anchor elements are located using XPATH expression.
 *  - onClick( ) method is used with buttons and anchor tags.
 *  - Input fields are located and sendKeys( ) method is used to enter test data.
 *  - Spring Boot JPA is used to initialize, verify and reset the database before
 *      and after testing.
 *
 *
 *    URL is the server on which Node.js is running.
 */

@SpringBootTest
public class SeleniumTestStudent {

	public static final String CHROME_DRIVER_FILE_LOCATION = "/Users/tiki/Documents/Software engineering/chromedriver-mac-x64/chromedriver";

	public static final String URL = "http://localhost:3000";

	public static final String TEST_USER_EMAIL = "test@csumb.edu";

	public static final String TEST_NAME = "test";

	public static final int SLEEP_DURATION = 1000; // 1 second.


    /*
     * add student 
     */
    @Test
    void addStudentTest() throws Exception {


		// set the driver location and start driver
		//@formatter:off
		// browser	property name 				Java Driver Class
		// edge 	webdriver.edge.driver 		EdgeDriver
		// FireFox 	webdriver.firefox.driver 	FirefoxDriver
		// IE 		webdriver.ie.driver 		InternetExplorerDriver
		//@formatter:on

		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_FILE_LOCATION);
		WebDriver driver = new ChromeDriver();
		// Puts an Implicit wait for 10 seconds before throwing exception
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		try {

			driver.get(URL);
			Thread.sleep(SLEEP_DURATION);

			driver.findElement(By.id("admin")).click();
			Thread.sleep(SLEEP_DURATION);

			driver.findElement(By.id("AddStudent")).click();
			Thread.sleep(SLEEP_DURATION);


			driver.findElement(By.id("StudentName")).sendKeys(TEST_NAME);
			driver.findElement(By.id("StudentEmail")).sendKeys(TEST_USER_EMAIL);
			driver.findElement(By.id("StudentStatus")).sendKeys("0");
			
			Thread.sleep(SLEEP_DURATION);
			
			driver.findElement(By.id("add")).click();
			Thread.sleep(SLEEP_DURATION);

			WebElement we = driver.findElement(By.xpath("//tr[td='"+TEST_NAME+"']"));
			assertNotNull(we, "Test student not found after successfully adding the student.");

		} catch (Exception ex) {
			throw ex;
		} finally {
			driver.quit();
		}

	}


	/*
	 * update student 
	 */
	@Test
	void EditStudentTest() throws Exception {
	
	
		// set the driver location and start driver
		//@formatter:off
		// browser	property name 				Java Driver Class
		// edge 	webdriver.edge.driver 		EdgeDriver
		// FireFox 	webdriver.firefox.driver 	FirefoxDriver
		// IE 		webdriver.ie.driver 		InternetExplorerDriver
		//@formatter:on
	
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_FILE_LOCATION);
		WebDriver driver = new ChromeDriver();
		// Puts an Implicit wait for 10 seconds before throwing exception
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
		try {
	
			driver.get(URL);
			Thread.sleep(SLEEP_DURATION);
			
			driver.findElement(By.id("admin")).click();
			Thread.sleep(SLEEP_DURATION);

	
			driver.findElement(By.id("editStudent")).click();
			Thread.sleep(SLEEP_DURATION);

	
			driver.findElement(By.id("name")).sendKeys(TEST_NAME);
			driver.findElement(By.id("update")).click();
			Thread.sleep(SLEEP_DURATION);
	
			WebElement we = driver.findElement(By.xpath("//tr[td='"+TEST_NAME+"']"));
			assertNotNull(we, "Test course title not found in schedule after successfully adding the course.");
	
	
		} catch (Exception ex) {
			throw ex;
		} finally {
			driver.quit();
		}
	
	}
	


	/*
	 * delete student 
	 */
	@Test
	void deleteStudentTest() throws Exception {
	
	
		// set the driver location and start driver
		//@formatter:off
		// browser	property name 				Java Driver Class
		// edge 	webdriver.edge.driver 		EdgeDriver
		// FireFox 	webdriver.firefox.driver 	FirefoxDriver
		// IE 		webdriver.ie.driver 		InternetExplorerDriver
		//@formatter:on
	
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_FILE_LOCATION);
		WebDriver driver = new ChromeDriver();
		// Puts an Implicit wait for 10 seconds before throwing exception
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
		try {
	
			driver.get(URL);
			Thread.sleep(SLEEP_DURATION);
			driver.findElement(By.id("admin")).click();
			Thread.sleep(SLEEP_DURATION);
	
			driver.findElement(By.id("deleteStudent")).click();
			Thread.sleep(SLEEP_DURATION);
	
	
		} catch (Exception ex) {
			throw ex;
		} finally {
			driver.quit();
		}
	
	}
}
	
