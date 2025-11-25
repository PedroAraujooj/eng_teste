import org.example.e4.Customer;
import org.example.e4.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerServiceTest {

    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        customerService = new CustomerService();
    }

    // ==============================
    // Teste de Cadastro com Idade Válida e Inválida
    // ==============================
    @Test
    void deveFalharCadastroQuandoIdadeMenorQueMinimo() {
        Customer c = new Customer(1, "João", "joao@test.com", 17, true);

        boolean result = customerService.registerCustomer(c);

        assertFalse(result);
    }

    @Test
    void devePermitirCadastroQuandoIdadeIgualAoMinimo() {
        Customer c = new Customer(2, "Maria", "maria@test.com", 18, true);

        boolean result = customerService.registerCustomer(c);

        assertTrue(result);
    }

    @Test
    void devePermitirCadastroQuandoIdadeIgualAoMaximo() {
        Customer c = new Customer(3, "Ana", "ana@test.com", 99, true);

        boolean result = customerService.registerCustomer(c);

        assertTrue(result);
    }

    @Test
    void deveFalharCadastroQuandoIdadeMaiorQueMaximo() {
        Customer c = new Customer(4, "Carlos", "carlos@test.com", 100, true);

        boolean result = customerService.registerCustomer(c);

        assertFalse(result);
    }

    // ==============================
    // Teste de Atualização de Cliente Ativo e Inativo
    // ==============================

    @Test
    void deveAtualizarClienteAtivo() {
        Customer c = new Customer(5, "João", "joao@test.com", 30, true);

        boolean result = customerService.updateCustomer(c, "João Silva", "joao.silva@test.com", 31);

        assertTrue(result);
        assertEquals("João Silva", c.getName());
        assertEquals("joao.silva@test.com", c.getEmail());
        assertEquals(31, c.getAge());
    }

    @Test
    void naoDeveAtualizarClienteInativo() {
        Customer c = new Customer(6, "João", "joao@test.com", 30, false);

        boolean result = customerService.updateCustomer(c, "João Silva", "joao.silva@test.com", 31);

        assertFalse(result);
        // garantir que não alterou os dados
        assertEquals("João", c.getName());
        assertEquals("joao@test.com", c.getEmail());
        assertEquals(30, c.getAge());
    }

    // ==============================
    // Teste de Exclusão de Cliente Ativo e Inativo
    // ==============================

    @Test
    void deveExcluirClienteAtivo() {
        Customer c = new Customer(7, "Maria", "maria@test.com", 25, true);

        boolean result = customerService.deleteCustomer(c);

        assertTrue(result);
    }

    @Test
    void naoDeveExcluirClienteInativo() {
        Customer c = new Customer(8, "Maria", "maria@test.com", 25, false);

        boolean result = customerService.deleteCustomer(c);

        assertFalse(result);
    }

    // ==============================
    // Teste de Validação de E-mail
    // ==============================

    @Test
    void deveAceitarEmailValido() {
        Customer c = new Customer(9, "Ana", "ana.valid@test.com", 30, true);

        boolean result = customerService.registerCustomer(c);

        assertTrue(result);
    }

    @Test
    void deveRejeitarEmailSemArroba() {
        Customer c = new Customer(10, "Ana", "ana.test.com", 30, true);
        // sem '@'
        boolean result = customerService.registerCustomer(c);

        assertFalse(result);
    }

    @Test
    void deveRejeitarEmailSemDominioCorreto() {
        Customer c = new Customer(11, "Ana", "ana@test", 30, true);
        // sem .com  .br
        boolean result = customerService.registerCustomer(c);

        assertFalse(result);
    }

    // ==============================
    // Teste de Cadastro Completo
    // ==============================

    @Test
    void cadastroCompletoComDadosValidosDeveSerBemSucedido() {
        Customer c = new Customer(12, "Pedro", "pedro@test.com", 35, true);

        boolean result = customerService.registerCustomer(c);

        assertTrue(result);
    }
}