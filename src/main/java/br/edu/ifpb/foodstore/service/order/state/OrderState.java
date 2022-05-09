package br.edu.ifpb.foodstore.service.order.state;

import br.edu.ifpb.foodstore.domain.Order;
import br.edu.ifpb.foodstore.service.log.LogService;
import br.edu.ifpb.foodstore.service.mail.observer.OrderEventsSubscriber;
import br.edu.ifpb.foodstore.service.order.OrderException;
import br.edu.ifpb.foodstore.service.payment.strategy.PaymentStrategy;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public abstract class OrderState {

    protected final LogService logService;

    protected List<OrderEventsSubscriber> subscribers = new ArrayList<>();

    public abstract void notifyStatusChange(Order order);

    public void addSubscriber(OrderEventsSubscriber subscriber) {
        this.subscribers.add(subscriber);
    }

    public void removeSubscriber(OrderEventsSubscriber subscriber) {
        this.subscribers.remove(subscriber);
    }

    public OrderState payOrder(Order order, PaymentStrategy paymentStrategy) throws Exception {
        order.setStatus(Order.OrderStatus.IN_PROGRESS);
        paymentStrategy.pay();
        return new OrderInPaymentSuccessState(logService);
    }

    public OrderState cancelOrder(Order order) throws OrderException {
        order.setStatus(Order.OrderStatus.CANCELED);
        return new OrderInCanceledState(logService);
    }

}
