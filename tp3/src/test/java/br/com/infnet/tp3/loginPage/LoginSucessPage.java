package br.com.infnet.tp3.loginPage;

import br.com.infnet.tp3.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginSucessPage extends BasePage {
    private final By titulo =  By.className("post-title");

    protected LoginSucessPage(WebDriver driver) {
        super(driver);
    }


    public WebElement getTitulo() {
        return $(titulo);
    }
}
