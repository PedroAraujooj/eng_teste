import org.example.e3.OrderService;
import org.example.e3.PaymentProcessor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private PaymentProcessor paymentProcessor;

    @InjectMocks
    private OrderService orderService;

    @Test
    void deveConfirmarPedidoQuandoPagamentoAprovado() {
        double amount = 100.0;
        when(paymentProcessor.processPayment(amount)).thenReturn(true);

        boolean result = orderService.processOrder(amount);

        assertTrue(result, "Quando o pagamento é aprovado, o pedido deve ser confirmado");
        verify(paymentProcessor, times(1)).processPayment(amount);
    }

    @Test
    void deveRecusarPedidoQuandoPagamentoReprovado() {
        double amount = 200.0;
        when(paymentProcessor.processPayment(amount)).thenReturn(false);

        boolean result = orderService.processOrder(amount);

        assertFalse(result, "Quando o pagamento é recusado, o pedido não deve ser confirmado");
        verify(paymentProcessor, times(1)).processPayment(amount);
    }
}