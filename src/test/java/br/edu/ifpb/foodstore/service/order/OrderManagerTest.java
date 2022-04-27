package br.edu.ifpb.foodstore.service.order;

import br.edu.ifpb.foodstore.domain.*;
import br.edu.ifpb.foodstore.service.log.LogService;
import br.edu.ifpb.foodstore.service.mail.MailNotification;
import br.edu.ifpb.foodstore.service.payment.PaymentService;
import br.edu.ifpb.foodstore.strategy.Billet;
import br.edu.ifpb.foodstore.strategy.CreditCard;
import br.edu.ifpb.foodstore.strategy.PaymentType;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
public class OrderManagerTest {

    @SpyBean
    private OrderManager orderManager;

    @MockBean
    private PaymentService paymentService;

    @MockBean
    private MailNotification mailNotification;

    @MockBean
    private LogService logService;

    private Order order;

    @BeforeEach
    public void init() {
        order = Order.builder()
                .id(1L)
                .status(OrderStatus.IN_PROGRESS)
                .customer(Customer.builder()
                        .email("testuser@test.com")
                        .name("Diego Pessoa")
                        .address("Super address")
                        .build())
                .creationDate(Calendar.getInstance())
                .items(Collections.singletonList(OrderItem.builder()
                        .product(Product.builder()
                                .name("Pastel de Vento")
                                .price(BigDecimal.valueOf(5.50))
                                .description("Pastel muito bom")
                                .build())
                        .build()))
                .build();
    }

    @SneakyThrows
    @Test
    void payOrderTest_success() {
        PaymentType paymentType = new CreditCard();
        orderManager.payOrder(order, paymentType);
        InOrder orderVerifier = Mockito.inOrder(paymentService, mailNotification, logService);
        orderVerifier.verify(paymentService).doPayment(paymentType);
        orderVerifier.verify(mailNotification).sendMailNotificationToAdmin("Order 1 completed successfully");
        orderVerifier.verify(mailNotification).sendMailNotificationToCustomer("Order 1 completed successfully", order.getCustomer());
        orderVerifier.verify(logService).info("payment finished");
    }

    @SneakyThrows
    @Test
    void payOrderTest_error() {
        PaymentType paymentType = new Billet();
        doThrow(new Exception(("unknown payment method"))).when(paymentService).doPayment(eq(paymentType));
        orderManager.payOrder(order, paymentType);
        InOrder orderVerifier = Mockito.inOrder(paymentService, mailNotification, logService);
        orderVerifier.verify(paymentService).doPayment(paymentType);
        orderVerifier.verify(logService).error("payment refused");
    }

    @SneakyThrows
    @Test
    void cancelOrderTest_inProgress() {
        orderManager.cancelOrder(order);
        assertThat(order.getStatus(), equalTo(OrderStatus.CANCELED));
        InOrder orderVerifier = Mockito.inOrder(logService, mailNotification);
        orderVerifier.verify(logService).info("Canceling in progress order");
        orderVerifier.verify(mailNotification).sendMailNotificationToAdmin("Order 1 canceled");
        orderVerifier.verify(mailNotification).sendMailNotificationToCustomer("Order 1 canceled", order.getCustomer());
        orderVerifier.verify(logService).debug("order 1 canceled");
    }

    @SneakyThrows
    @Test
    void cancelOrderTest_canceled() {
        order.setStatus(OrderStatus.CANCELED);
        assertThrows(OrderException.class, () -> orderManager.cancelOrder(order));
    }

    @SneakyThrows
    @Test
    void cancelOrderTest_paymentRefused() {
        order.setStatus(OrderStatus.PAYMENT_REFUSED);
        orderManager.cancelOrder(order);
        assertThat(order.getStatus(), equalTo(OrderStatus.CANCELED));
        InOrder orderVerifier = Mockito.inOrder(logService, mailNotification);
        orderVerifier.verify(logService).info("Canceling refused order");
        orderVerifier.verify(mailNotification).sendMailNotificationToAdmin("Order 1 canceled");
        orderVerifier.verify(mailNotification).sendMailNotificationToCustomer("Order 1 canceled", order.getCustomer());
        orderVerifier.verify(logService).debug("order 1 canceled");
    }

    @SneakyThrows
    @Test
    void cancelOrderTest_paymentSuccess() {
        order.setStatus(OrderStatus.PAYMENT_SUCCESS);
        orderManager.cancelOrder(order);
        assertThat(order.getStatus(), equalTo(OrderStatus.CANCELED));
        InOrder orderVerifier = Mockito.inOrder(logService, mailNotification);
        orderVerifier.verify(logService).info("Canceling already paid order");
        orderVerifier.verify(mailNotification).sendMailNotificationToAdmin("Order 1 canceled");
        orderVerifier.verify(mailNotification).sendMailNotificationToCustomer("Order 1 canceled", order.getCustomer());
        orderVerifier.verify(logService).debug("order 1 canceled");
    }
}
