import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CarValuationPage;
import pages.YourDetailsPage;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static Utils.FileHandler.readInputFile;
import static Utils.FileHandler.readOutputFileAsArray;
import static Utils.RegexHandler.extractCarReg;


public class CarValuationTest extends BasePage {

    @Test
    public void compareCarValuation() throws IOException, InterruptedException {

        List<String> inputLines = readInputFile("src/main/java/files/car_input.txt");
        Map<String, String[]> expectedOutput = readOutputFileAsArray("src/main/java/files/car_output.txt");

        String inputText = String.join(" ", inputLines);
        List<String> registrations = extractCarReg(inputText);

        if (registrations.isEmpty()) {
            throw new RuntimeException("No registrations found in the input file.");
        }

        SoftAssert softAssert = new SoftAssert();

        CarValuationPage carPage = new CarValuationPage(driver);
        YourDetailsPage detailsPage = new YourDetailsPage(driver);
        System.out.println("**********************"+registrations);

        for (String reg : registrations) {

            WebDriverWait wait = new WebDriverWait(driver, 20);
                driver.get("https://www.webuyanycar.com");
                try {
                    WebElement acceptCookiesButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("onetrust-accept-btn-handler")));
                    acceptCookiesButton.click();
                } catch (Exception e) {
                    System.out.println("No cookie consent banner found or already accepted.");
                }
                carPage.enterCarReg(reg);
                carPage.enterCarMileage("50000");
                carPage.clickGetCarValuationButton();
                Thread.sleep(20000);

                String[] actualResult = detailsPage.getResultArray();
                String[] expectedResult = expectedOutput.get(reg);

            softAssert.assertNotNull(expectedResult, "No expected output found for registration: " + reg);
            softAssert.assertEqualsNoOrder(actualResult, expectedResult, "Mismatch for registration: " + reg);


        }
        softAssert.assertAll();
    }
}