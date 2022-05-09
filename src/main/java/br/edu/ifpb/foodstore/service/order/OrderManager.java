package br.edu.ifpb.foodstore.service.order;

import br.edu.ifpb.foodstore.domain.Order;
import br.edu.ifpb.foodstore.service.log.LogService;
import br.edu.ifpb.foodstore.service.mail.observer.AdminEmailNotification;
import br.edu.ifpb.foodstore.service.mail.observer.UserEmailNotification;
import br.edu.ifpb.foodstore.service.order.state.*;
import br.edu.ifpb.foodstore.service.payment.strategy.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class OrderManager {

    private final LogService logService;

    @Setter
    private OrderState orderState;

    @PostConstruct
    public void init() {
         orderState = new OrderInProgressState(logService);
         orderState.addSubscriber(new AdminEmailNotification(logService));
         orderState.addSubscriber(new UserEmailNotification(logService));
    }

    public void payOrder(Order order, PaymentStrategy paymentStrategy) {
        try {
            OrderState orderInPaymentSuccess = orderState.payOrder(order, paymentStrategy);
            orderInPaymentSuccess.addSubscriber(new AdminEmailNotification(logService));
            orderInPaymentSuccess.addSubscriber(new UserEmailNotification(logService));
            changeState(order, orderInPaymentSuccess);
            logService.info("payment finished");
        } catch (Exception e) {
            logService.error("payment refused");
            changeState(order, new OrderInPaymentRefusedState(logService));
            order.setStatus(Order.OrderStatus.PAYMENT_REFUSED);
        }
    }

    public void cancelOrder(Order order) throws OrderException {
        OrderState orderInCanceledState = orderState.cancelOrder(order);
        orderInCanceledState.addSubscriber(new AdminEmailNotification(logService));
        changeState(order, orderInCanceledState);
        logService.debug(String.format("order %d canceled", order.getId()));
    }

    private void changeState(Order order, OrderState state) {
        this.orderState = state;
        state.notifyStatusChange(order);
    }

}
