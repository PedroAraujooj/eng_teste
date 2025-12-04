package br.com.infnet.tp3.PrestaShopPage;

import br.com.infnet.tp3.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Objects;

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
    private final By closeBnt = By.cssSelector("#blockcart-modal button.close");
    private final By carrinhoCount = By.cssSelector(".cart-products-count");

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

    public void fecharModal() {
        Objects.requireNonNull(wait.until(ExpectedConditions.elementToBeClickable(closeBnt))).click();
    }

    public Integer getQuantidadeCarrinho(){
        String stringCount = $(carrinhoCount).getText();
        return Integer.parseInt(stringCount.replace("(", "")
                .replace(")", ""));
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
