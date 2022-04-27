package br.edu.ifpb.foodstore.service.payment;

import br.edu.ifpb.foodstore.service.log.LogService;
import br.edu.ifpb.foodstore.service.payment.PaymentService;
import br.edu.ifpb.foodstore.strategy.Billet;
import br.edu.ifpb.foodstore.strategy.CreditCard;
import br.edu.ifpb.foodstore.strategy.Debit;
import br.edu.ifpb.foodstore.strategy.Paypal;
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
        paymentService.doPayment(new CreditCard());
        InOrder orderVerifier = Mockito.inOrder(logService);
        orderVerifier.verify(logService).info("Do credit card payment!");

        paymentService.doPayment(new Debit());
        orderVerifier = Mockito.inOrder(logService);
        orderVerifier.verify(logService).info("Do debit payment!");

        paymentService.doPayment(new Paypal());
        orderVerifier = Mockito.inOrder(logService);
        orderVerifier.verify(logService).info("Do paypal payment!");

        paymentService.doPayment(new Billet());
        orderVerifier = Mockito.inOrder(logService);
        orderVerifier.verify(logService).info("Do billet payment!");

    }

}
