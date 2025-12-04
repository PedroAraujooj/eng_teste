package br.com.infnet.tp3.test;

import br.com.infnet.tp3.PrestaShopPage.PrestaShopPage;
import br.com.infnet.tp3.core.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PrestaShopTest extends BaseTest {
    @Test
    void prestaShopTeste() throws InterruptedException {
        PrestaShopPage prestaShopPage = new PrestaShopPage(driver).abrir();
        List<WebElement> prods = prestaShopPage.getProdutos();
        prods.get(0).click();
        Thread.sleep(5000);
        prestaShopPage.adicionarCarrinho();
        String tituloModal = prestaShopPage.getTituloModal();
        assertTrue(tituloModal.contains("Product successfully added to your shopping cart"));

    }

    @Test
    void contacsUFormTest() throws InterruptedException {
        PrestaShopPage prestaShopPage = new PrestaShopPage(driver).abrir();
        prestaShopPage.irParaContato();
        prestaShopPage.preencherForm("Webmaster", "your@email.com", "Help me! I'm a junior developer")
                .submeterForm();
        Boolean bemSucedido = prestaShopPage.isFormBemSucedido();
        assertTrue(bemSucedido);

    }
}
