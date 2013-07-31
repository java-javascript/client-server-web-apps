import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;  //Headless: java based implementation. For any other language binding Selenium Server required to use this driver.
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumTest1  {
    public static void main(String[] args) {
        
       // WebDriver driver = new FirefoxDriver();
//		  WebDriver driver = new SafariDriver();
		WebDriver driver = new HtmlUnitDriver();  //HtmlUnitDriver(true) blows up though

		
// Additional work is required for Chrome...		
//  http://code.google.com/p/selenium/wiki/ChromeDriver
//  import org.openqa.selenium.chrome.ChromeDriver;
//  WebDriver driver = new ChromeDriver(); 

        driver.get("http://www.google.com");  //or driver.navigate().to("http://www.google.com");
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("Cheese!");
		element.submit();  //  WebDriver finds the form from the element

        // Check the title of the page
        System.out.println("Page title is: " + driver.getTitle());
        
        // Google's search is rendered dynamically with JavaScript.
        // Wait for the page to load, timeout after 10 seconds
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("cheese!");
            }
        });

        System.out.println("Page title is: " + driver.getTitle());
        driver.quit();
    }
}