package br.edu.ifpb.foodstore.service.order.state;

import br.edu.ifpb.foodstore.domain.Order;
import br.edu.ifpb.foodstore.service.log.LogService;
import br.edu.ifpb.foodstore.service.order.OrderException;

public class OrderInProgressState extends OrderState {

    public OrderInProgressState(LogService logService) {
        super(logService);
    }

    @Override
    public OrderState cancelOrder(Order order) throws OrderException {
        logService.info("Canceling in progress order");
        return super.cancelOrder(order);
    }

    @Override
    public void notifyStatusChange(Order order) {
        this.subscribers.forEach( s -> s.orderCreated(order));
    }
}
