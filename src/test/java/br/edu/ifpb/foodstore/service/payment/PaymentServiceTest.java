package br.edu.ifpb.foodstore.service.payment;

import br.edu.ifpb.foodstore.service.log.LogService;
import br.edu.ifpb.foodstore.service.payment.strategy.BilletCardPaymentStrategy;
import br.edu.ifpb.foodstore.service.payment.strategy.CreditCardPaymentStrategy;
import br.edu.ifpb.foodstore.service.payment.strategy.DebitPaymentStrategy;
import br.edu.ifpb.foodstore.service.payment.strategy.PaypalPaymentStrategy;
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

    @SneakyThrows
    @Test
    void doPaymentTest() {
        paymentService.doPayment(new CreditCardPaymentStrategy(logService));
        InOrder orderVerifier = Mockito.inOrder(logService);
        orderVerifier.verify(logService).info("Do credit card payment!");

        paymentService.doPayment(new DebitPaymentStrategy(logService));
        orderVerifier = Mockito.inOrder(logService);
        orderVerifier.verify(logService).info("Do debit payment!");

        paymentService.doPayment(new PaypalPaymentStrategy(logService));
        orderVerifier = Mockito.inOrder(logService);
        orderVerifier.verify(logService).info("Do paypal payment!");

        paymentService.doPayment(new BilletCardPaymentStrategy(logService));
        orderVerifier = Mockito.inOrder(logService);
        orderVerifier.verify(logService).info("Do billet payment!");

    }

}
