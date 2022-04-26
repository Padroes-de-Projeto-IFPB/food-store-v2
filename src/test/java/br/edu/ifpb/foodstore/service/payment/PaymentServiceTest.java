package br.edu.ifpb.foodstore.service.payment;

import br.edu.ifpb.foodstore.service.log.LogService;
import br.edu.ifpb.foodstore.service.payment.PaymentService;
import br.edu.ifpb.foodstore.service.payment.estrategias.*;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

@SpringBootTest
public class PaymentServiceTest {

    @SpyBean
    private PaymentService paymentService;

    @MockBean
    private LogService logService;

    private CreditCard creditCard = new CreditCard();
    private Debit debit = new Debit();
    private PayPal payPal = new PayPal();
    private Billet billet = new Billet();

    @SneakyThrows
    @Test
    void doPaymentTest() {
        paymentService.doPayment(creditCard);
        InOrder orderVerifier = Mockito.inOrder(logService);
        orderVerifier.verify(logService).info("Do credit card payment!");

        paymentService.doPayment(debit);
        orderVerifier = Mockito.inOrder(logService);
        orderVerifier.verify(logService).info("Do debit payment!");

        paymentService.doPayment(payPal);
        orderVerifier = Mockito.inOrder(logService);
        orderVerifier.verify(logService).info("Do paypal payment!");

        paymentService.doPayment(billet);
        orderVerifier = Mockito.inOrder(logService);
        orderVerifier.verify(logService).info("Do billet payment!");

    }

}
