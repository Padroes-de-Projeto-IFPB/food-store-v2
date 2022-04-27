package br.edu.ifpb.foodstore.service.order;

import br.edu.ifpb.foodstore.domain.Order;
import br.edu.ifpb.foodstore.domain.OrderStatus;
import br.edu.ifpb.foodstore.service.log.LogService;
import br.edu.ifpb.foodstore.service.mail.MailNotification;
import br.edu.ifpb.foodstore.service.payment.PaymentService;
import br.edu.ifpb.foodstore.strategy.PaymentType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderManager {

    private final PaymentService paymentService;
    private final MailNotification mailNotification;
    private final LogService logService;

    public void payOrder(Order order, PaymentType paymentType) {
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
        if(order.getStatus().equals(OrderStatus.CANCELED))
            throw new OrderException(order.getStatus().getStatusMensager());
        else
            logService.info(order.getStatus().getStatusMensager());

        order.setStatus(OrderStatus.CANCELED);
        mailNotification.sendMailNotificationToAdmin(String.format("Order %d canceled", order.getId()));
        mailNotification.sendMailNotificationToCustomer(String.format("Order %d canceled", order.getId()), order.getCustomer());
        logService.debug(String.format("order %d canceled", order.getId()));
    }

}
