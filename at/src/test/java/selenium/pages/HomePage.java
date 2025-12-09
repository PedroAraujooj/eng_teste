package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium.core.BasePage;

public class HomePage extends BasePage {

    private final By logo = By.cssSelector("a > img");
    private final By signupLoginLink = By.xpath("//a[normalize-space()='Signup / Login']");
    private final By loggedInAs = By.xpath("//*[contains(text(),'Logged in as')]");
    private final By logoutLink = By.xpath("//a[normalize-space()='Logout']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("https://automationexercise.com/");
    }

    public boolean isHomeVisible() {
        return $(logo).isDisplayed();
    }

    public SignupLoginPage clickSignupLogin() {
        click(signupLoginLink);
        return new SignupLoginPage(driver);
    }

    public boolean isLoggedIn() {
        return !driver.findElements(loggedInAs).isEmpty();
    }

    public void logout() {
        click(logoutLink);
    }
}
