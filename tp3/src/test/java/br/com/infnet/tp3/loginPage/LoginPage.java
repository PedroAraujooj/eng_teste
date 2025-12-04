package br.com.infnet.tp3.loginPage;

import br.com.infnet.tp3.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    private static final String URL = "https://practicetestautomation.com/practice-test-login/";
    private final By loopContainer =  By.id("loop-container");

    private final By usernameInput =  By.id("username");
    private final By passwordInput =  By.id("password");
    private final By submitLoginBtn = By.id("submit");
    private final By logo = By.cssSelector(".custom-logo");


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage abrir(){
        driver.get(URL);
        return this;
    }

    public LoginPage preencherUsuario(String usuario) {
        type(usernameInput, usuario);
        return this;
    }

    public LoginPage preencherSenha(String senha) {
        type(passwordInput, senha);
        return this;
    }

    public LoginPage irParaTelaPrincipal() {
        $(logo).click();
        return this;
    }

    public LoginSucessPage submeterLoginComSucesso(){
        click(submitLoginBtn);
        wait.until(ExpectedConditions.visibilityOfElementLocated(loopContainer));
        return new LoginSucessPage(driver);
    }

}
