package org.example.e3;

public class OrderService {
    private final PaymentProcessor paymentProcessor;
    public OrderService(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    public boolean processOrder(double amount) {
        if (paymentProcessor.processPayment(amount)) {
            System.out.println("Pedido confirmado.");
            return true;
        } else {
            System.out.println("Pagamento recusado.");
            return false;
        }
    }
}
