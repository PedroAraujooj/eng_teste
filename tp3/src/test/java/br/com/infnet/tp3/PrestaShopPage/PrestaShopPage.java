package br.com.infnet.tp3.PrestaShopPage;

import br.com.infnet.tp3.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class PrestaShopPage extends BasePage {
    private static final String URL = "https://demo.prestashop.com/#/en/front";

    private final By produtos = By.cssSelector(".thumbnail.product-thumbnail");

    private final By frameLoja = By.id("framelive");
    private final By tituloModal = By.id("myModalLabel");
    private final By contactLink = By.id("contact-link");
    private final By subjectSelect = By.id("id_contact");
    private final By emailInput = By.id("email");
    private final By msgInput = By.id("contactform-message");
    private final By formSubmit = By.name("submitMessage");
    private final By adicionarCarrinhoBtn = By.cssSelector(".btn.btn-primary.add-to-cart");
    private final By alertaSucesso = By.cssSelector(".alert-success");

    public PrestaShopPage(WebDriver driver) {
        super(driver);
    }

    public PrestaShopPage abrir(){
        driver.get(URL);

        wait.until(ExpectedConditions
                .frameToBeAvailableAndSwitchToIt(frameLoja));

        wait.until(ExpectedConditions
                .visibilityOfElementLocated(produtos));

        return this;
    }

    public List<WebElement> getProdutos(){
        return $$(produtos);
    }

    public void adicionarCarrinho(){
        $(adicionarCarrinhoBtn).click();
    }

    public void irParaContato(){
        $(contactLink).click();
    }

    public String getTituloModal() {
        return $(tituloModal).getText();
    }

    public PrestaShopPage preencherForm(String select, String mail, String msg) throws InterruptedException {
        Select dropdown = new Select($(subjectSelect));
        dropdown.selectByVisibleText(select);
        type(emailInput, mail);
        type(msgInput, msg);
        return this;
    }

    public void submeterForm() {
        $(formSubmit).click();
    }

    public Boolean isFormBemSucedido() {
        return $(alertaSucesso).isDisplayed();
    }
}
