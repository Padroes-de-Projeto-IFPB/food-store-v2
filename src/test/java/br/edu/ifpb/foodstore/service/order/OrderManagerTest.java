package br.edu.ifpb.foodstore.service.order;

import br.edu.ifpb.foodstore.domain.Customer;
import br.edu.ifpb.foodstore.domain.Order;
import br.edu.ifpb.foodstore.domain.OrderItem;
import br.edu.ifpb.foodstore.domain.Product;
import br.edu.ifpb.foodstore.service.log.LogService;
import br.edu.ifpb.foodstore.service.mail.MailNotification;
import br.edu.ifpb.foodstore.service.mail.observer.AdminEmailNotification;
import br.edu.ifpb.foodstore.service.mail.observer.UserEmailNotification;
import br.edu.ifpb.foodstore.service.order.state.OrderInCanceledState;
import br.edu.ifpb.foodstore.service.order.state.OrderInPaymentRefusedState;
import br.edu.ifpb.foodstore.service.order.state.OrderInPaymentSuccessState;
import br.edu.ifpb.foodstore.service.order.state.OrderInProgressState;
import br.edu.ifpb.foodstore.service.payment.strategy.BilletCardPaymentStrategy;
import br.edu.ifpb.foodstore.service.payment.strategy.CreditCardPaymentStrategy;
import br.edu.ifpb.foodstore.service.payment.PaymentService;
import br.edu.ifpb.foodstore.service.payment.strategy.PaymentStrategy;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
import static org.mockito.Mockito.mock;

@SpringBootTest
public class OrderManagerTest {

    @SpyBean
    private OrderManager orderManager;

    @MockBean
    private LogService logService;

    private Order order;

    @BeforeEach
    public void init() {
        order = Order.builder()
                .id(1L)
                .status(Order.OrderStatus.IN_PROGRESS)
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
        PaymentStrategy paymentStrategy = new CreditCardPaymentStrategy(logService);
        orderManager.setOrderState(new OrderInProgressState(logService));
        orderManager.payOrder(order, paymentStrategy);
        InOrder orderVerifier = Mockito.inOrder(logService);
        orderVerifier.verify(logService).info("payment finished");
    }

    @SneakyThrows
    @Test
    void payOrderTest_error() {
        PaymentStrategy paymentStrategy = mock(BilletCardPaymentStrategy.class);
        orderManager.setOrderState(new OrderInProgressState(logService));
        doThrow(new Exception(("unknown payment method"))).when(paymentStrategy).pay();
        orderManager.payOrder(order, paymentStrategy);
        InOrder orderVerifier = Mockito.inOrder(logService);
        orderVerifier.verify(logService).error("payment refused");
    }

    @SneakyThrows
    @Test
    void cancelOrderTest_inProgress() {
        order.setStatus(Order.OrderStatus.IN_PROGRESS);
        orderManager.setOrderState(new OrderInProgressState(logService));
        orderManager.cancelOrder(order);
        assertThat(order.getStatus(), equalTo(Order.OrderStatus.CANCELED));
        InOrder orderVerifier = Mockito.inOrder(logService);
        orderVerifier.verify(logService).info("Canceling in progress order");
        orderVerifier.verify(logService).debug("order 1 canceled");
    }

    @SneakyThrows
    @Test
    void cancelOrderTest_canceled() {
        order.setStatus(Order.OrderStatus.CANCELED);
        orderManager.setOrderState(new OrderInCanceledState(logService));
        assertThrows(OrderException.class, () -> orderManager.cancelOrder(order));
    }

    @SneakyThrows
    @Test
    void cancelOrderTest_paymentRefused() {
        order.setStatus(Order.OrderStatus.PAYMENT_REFUSED);
        orderManager.setOrderState(new OrderInPaymentRefusedState(logService));
        orderManager.cancelOrder(order);
        assertThat(order.getStatus(), equalTo(Order.OrderStatus.CANCELED));
        InOrder orderVerifier = Mockito.inOrder(logService);
        orderVerifier.verify(logService).info("Canceling refused order");
        orderVerifier.verify(logService).debug("order 1 canceled");
    }

    @SneakyThrows
    @Test
    void cancelOrderTest_paymentSuccess() {
        order.setStatus(Order.OrderStatus.PAYMENT_SUCCESS);
        orderManager.setOrderState(new OrderInPaymentSuccessState(logService));
        orderManager.cancelOrder(order);
        assertThat(order.getStatus(), equalTo(Order.OrderStatus.CANCELED));
        InOrder orderVerifier = Mockito.inOrder(logService);
        orderVerifier.verify(logService).info("Canceling already paid order");
        orderVerifier.verify(logService).debug("order 1 canceled");
        orderVerifier.verifyNoMoreInteractions();
    }
}
