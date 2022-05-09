package br.edu.ifpb.foodstore.service.order.state;

import br.edu.ifpb.foodstore.domain.Order;
import br.edu.ifpb.foodstore.service.log.LogService;
import br.edu.ifpb.foodstore.service.order.OrderException;
import br.edu.ifpb.foodstore.service.payment.strategy.PaymentStrategy;

public class OrderInCanceledState extends OrderState {
    public OrderInCanceledState(LogService logService) {
        super(logService);
    }

    @Override
    public OrderState cancelOrder(Order order) throws OrderException {
        throw new OrderException("Order already canceled!");
    }

    @Override
    public OrderState payOrder(Order order, PaymentStrategy paymentStrategy) throws Exception {
        throw new OrderException("Not allowed to pay order already canceled!");
    }

    @Override
    public void notifyStatusChange(Order order) {
        this.subscribers.forEach( s -> s.orderCanceled(order));
    }

}
