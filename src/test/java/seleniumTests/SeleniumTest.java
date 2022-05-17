
package seleniumTests;

import interactions.Interactions;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



/**
 * Created by didier on 8/7/19.
 */
public class SeleniumTest {

    @Test
   // @Parameters("testParam")
    public void googleTestResults() throws MalformedURLException {
        String keyWord = "Webdriver";
        // Initialized the classes to access the browser
        Interactions browser = new Interactions();
        GooglePage googlePage = new GooglePage(browser);
        GoogleResultsPage googleResultsPage = new GoogleResultsPage(browser);
        boolean areTheKewordPresentInThePage = false;

        // Navigate to Google
        googlePage.goToGoogle();

        // Enter the given keyword - It was defined in testNG.xml file as parameter

        googlePage.enterkeyword("HJAHFADSJKHADSKJHFASDJKHDFASJKHAFSDJKHKJHFDASJKHFADSJKHASFDHJKHJKADFSJHKSADF");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        googlePage.enterkeyword(keyWord);

        // Search for the results
        googlePage.search();

        // Wait for page to be loaded
        googleResultsPage.waitForPageLoad();

        // Assert the page has results
      //  Assert.assertNotEquals(googleResultsPage.getTotalResults(), 0, "The page must display results");

        // Validate that each result contains the keyword we defined
        for (int i = 0; i < googleResultsPage.getTotalResults(); i++) {

            // Verify if the keyword is present on the title or in the description
            if (googleResultsPage.getResultsTextByIndex(i).toLowerCase().contains(keyWord.toLowerCase()) ||
                    googleResultsPage.getResultsTitleTextByIndex(i).toLowerCase().contains(keyWord.toLowerCase())) {
                areTheKewordPresentInThePage = true;
            }

            // Validate if the text is present on the results, either on the title or in the description for each result item
          //  Assert.assertTrue(areTheKewordPresentInThePage);

            // Set the flag to false to be used in the other item in the list
            areTheKewordPresentInThePage = false;
        }

        // Close the browser
        browser.quit();
    }
}
