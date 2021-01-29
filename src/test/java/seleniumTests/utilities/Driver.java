package seleniumTests.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This handle the way the driver is created based on the OS
 */
public class Driver {

    /**
     * Returns Webdriver instance based on the OS where it is being executed
     *
     * @return Webdriver based on the OS
     */
    public static WebDriver getChromeDriver() {

        /** Set the utilities folder */
        String driversPath = System.getProperty("user.dir") + File.separator + "resources" + File.separator;

        /** If the tests are executed in MAC OS returns Mac driver */
        if (System.getProperty("os.name").toLowerCase().contains("mac"))
            System.setProperty("webdriver.chrome.driver", driversPath + "chromedriver");

        /** If the tests are executed in Windows OS returns Mac driver */
        if (System.getProperty("os.name").toLowerCase().contains("win"))
            System.setProperty("webdriver.chrome.driver", driversPath + "chromedriver-binary.exe");

        // Note: Not other OS where included in this code.

        WebDriver driver = new ChromeDriver();
        return driver;
    }

    /**
     * Returns Webdriver instance based on the OS where it is being executed
     *
     * @return Webdriver based on the OS
     */
    public static WebDriver getGridDriver(String browserName) throws MalformedURLException {
        WebDriver driver;
        String baseURL, nodeURL;
        nodeURL = "http://localhost:4444/wd/hub";
        DesiredCapabilities capability = DesiredCapabilities.chrome();
        capability.setBrowserName(browserName);
        driver = new RemoteWebDriver(new URL(nodeURL), capability);

      // driver = new ChromeDriver();
        return driver;
    }
}
