package selenium;

import org.junit.jupiter.api.Test;
import selenium.core.BaseTest;
import selenium.pages.AccountCreatedPage;
import selenium.pages.AccountInformationPage;
import selenium.pages.HomePage;
import selenium.pages.SignupLoginPage;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class AuthenticationTest extends BaseTest {

    private String randomEmail() {
        return "qa_" + UUID.randomUUID() + "@teste.com";
    }

    @Test
    void deveCadastrarNovoUsuarioELogarComSucesso() {
        String testName = "deveCadastrarNovoUsuarioELogarComSucesso";
        String email = randomEmail();
        String password = "SenhaForte123";

        try {
            HomePage home = new HomePage(driver);
            home.open();
            assertTrue(home.isHomeVisible(), "Home não carregou corretamente");

            SignupLoginPage signupLogin = home.clickSignupLogin();
            assertTrue(signupLogin.isSignupFormVisible(), "'New User Signup!' não está visível");

            // Cadastro
            AccountInformationPage accountInfo =
                    signupLogin.startSignup("QA Automation", email);

            AccountCreatedPage createdPage =
                    accountInfo.fillFormAndSubmit(password);

            assertTrue(createdPage.isAccountCreatedVisible(),
                    "Mensagem 'ACCOUNT CREATED!' não visível");

            HomePage homeAfterSignup = createdPage.clickContinue();
            assertTrue(homeAfterSignup.isLoggedIn(),
                    "'Logged in as' não está visível após cadastro");

            // Logout
            homeAfterSignup.logout();

            // Login com as mesmas credenciais
            SignupLoginPage loginPage = homeAfterSignup.clickSignupLogin();
            loginPage.login(email, password);

            HomePage homeAfterLogin = new HomePage(driver);
            assertTrue(homeAfterLogin.isLoggedIn(),
                    "Usuário está logado após login com credenciais válidas");

        } catch (Exception e) {
            takeScreenshot(testName);
            throw e;
        }
    }

    @Test
    void deveMostrarErroAoFazerLoginComCredenciaisInvalidas() {
        String testName = "deveMostrarErroAoFazerLoginComCredenciaisInvalidas";

        try {
            HomePage home = new HomePage(driver);
            home.open();
            assertTrue(home.isHomeVisible(), "Home não carregou corretamente");

            SignupLoginPage loginPage = home.clickSignupLogin();

            // Email/senha inválidos
            loginPage.login("usuario_inexistente@teste.com", "senhaErrada123");

            String error = loginPage.getLoginErrorMessage();
            assertEquals("Your email or password is incorrect!", error);

        } catch (Exception e) {
            takeScreenshot(testName);
            throw e;
        }
    }
}
