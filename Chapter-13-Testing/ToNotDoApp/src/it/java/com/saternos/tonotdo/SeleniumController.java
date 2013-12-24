package com.saternos.tonotdo;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;


public class SeleniumController {
	
	/**
	 * There are a bunch of Drivers available, but which one(s) will work based varies based on Selenium 
	 * version, browser version, OS etc.  Chrome requires some additional setup to use.  Safari on OSX
	 * currently sees the login tests as a potential phishing attach and prompts the user to continue.
	 * Recent versions of Firefox hang while loading.  Real world development tends to lack ease and 
	 * elegance ;).
	 */
	
    //WebDriver driver = new HtmlUnitDriver();  
    WebDriver driver = new SafariDriver(); // Set security settings in Safari or Phishing notice for user/password in URL 
    //WebDriver driver = new FirefoxDriver();
    // For ChromeDriver see https://code.google.com/p/selenium/wiki/ChromeDriver

    @Before
    public void initSelenium() throws Exception {
    }

    @After
    public void destroySelenium() {
        driver.close();
        driver.quit();
    }

    public void clickAndWait(String selector) {
        WebElement element = driver.findElement(By.id(selector));
        element.click();
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
    }

    public void get(String location) {
        driver.get(location);
    }

    public boolean contains(String text) {
        WebElement content = driver.findElement(By.tagName("body"));
        return content.getText().contains(text);
    }
}