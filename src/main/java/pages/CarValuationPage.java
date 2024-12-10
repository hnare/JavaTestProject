package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CarValuationPage {

    private WebDriver driver;

    private By carRegInput = By.id("vehicleReg");
    private By carMileageInput = By.id("Mileage");
    private By carValuationButton = By.id("btn-go");

    public CarValuationPage(WebDriver driver){
        this.driver = driver;
    }

    public void enterCarReg(String registration){
        driver.findElement(carRegInput).sendKeys(registration);
    }

    public void enterCarMileage(String milleage){
        driver.findElement(carMileageInput).sendKeys(milleage);
    }

    public void clickGetCarValuationButton(){
        driver.findElement(carValuationButton).click();
    }

}