package br.edu.ifpb.foodstore.service.order;

import br.edu.ifpb.foodstore.domain.Order;
import br.edu.ifpb.foodstore.domain.orderState.OrderStatus;
import br.edu.ifpb.foodstore.domain.paymentStrategy.PaymentInterface;
import br.edu.ifpb.foodstore.service.log.LogService;
import br.edu.ifpb.foodstore.service.mail.MailNotification;
import br.edu.ifpb.foodstore.service.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderManager {

    private final PaymentService paymentService;
    private final MailNotification mailNotification;
    private final LogService logService;

    public void payOrder(Order order, PaymentInterface paymentType) {
        order.setStatus(OrderStatus.IN_PROGRESS);
        try {
            paymentService.doPayment(paymentType);
            order.setStatus(OrderStatus.PAYMENT_SUCCESS);
            mailNotification.sendMailNotificationToAdmin(String.format("Order %d completed successfully", order.getId()));
            mailNotification.sendMailNotificationToCustomer(String.format("Order %d completed successfully", order.getId()), order.getCustomer());
            logService.info("payment finished");
        } catch (Exception e) {
            logService.error("payment refused");
            order.setStatus(OrderStatus.PAYMENT_REFUSED);
            mailNotification.sendMailNotificationToAdmin(String.format("Order %d refused", order.getId()));
        }
    }

    public void cancelOrder(Order order) throws OrderException {
        switch(order.getStatus()) {
            case CANCELED:
                throw new OrderException("Order already canceled!");
            case IN_PROGRESS:
                logService.info("Canceling in progress order");
                break;
            case PAYMENT_REFUSED:
                logService.info("Canceling refused order");
                break;
            case PAYMENT_SUCCESS:
                logService.info("Canceling already paid order");
                break;
        }
        order.setStatus(OrderStatus.CANCELED);
        mailNotification.sendMailNotificationToAdmin(String.format("Order %d canceled", order.getId()));
        mailNotification.sendMailNotificationToCustomer(String.format("Order %d canceled", order.getId()), order.getCustomer());
        logService.debug(String.format("order %d canceled", order.getId()));
    }

}
