package br.com.infnet.tp3.test;

import br.com.infnet.tp3.core.BaseTest;
import br.com.infnet.tp3.loginPage.LoginPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest extends BaseTest {
    @Test
    void fazerLogin() {
        String tituloPagina = new LoginPage(driver).abrir().preencherUsuario("student").
                preencherSenha("Password123").submeterLoginComSucesso().getTitulo().getText();
        assertEquals("Logged In Successfully", tituloPagina);
    }

    @Test
    void testScrollDowmAndPrint() {
        assertDoesNotThrow(() -> {
            LoginPage lgnPg = new LoginPage(driver).abrir();
            lgnPg.irParaTelaPrincipal();
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Path destino = Path.of("resources", "screenshots", "home-bottom.png");
            Files.createDirectories(destino.getParent());
            Files.copy(src.toPath(), destino, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Screenshot salvo em: " + destino.toAbsolutePath());
        });

    }
}
