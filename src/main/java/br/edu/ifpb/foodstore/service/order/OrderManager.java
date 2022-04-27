package br.edu.ifpb.foodstore.service.order;

import br.edu.ifpb.foodstore.domain.Administration;
import br.edu.ifpb.foodstore.domain.Order;
import br.edu.ifpb.foodstore.service.log.LogService;
import br.edu.ifpb.foodstore.service.mail.EventManager;
import br.edu.ifpb.foodstore.service.mail.MailNotification;
import br.edu.ifpb.foodstore.service.payment.PaymentService;
import br.edu.ifpb.foodstore.service.payment.estrategias.PaymentType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class OrderManager {

    private final PaymentService paymentService;
    private final MailNotification mailNotification;
    private final LogService logService;

    public void payOrder(Order order, PaymentType paymentType) {
        order.setStatus(Order.OrderStatus.IN_PROGRESS);
        try {
            paymentService.doPayment(paymentType);
            order.setStatus(Order.OrderStatus.PAYMENT_SUCCESS);
            mailNotification.sendMailNotificationToAdmin(String.format("Order %d completed successfully", order.getId()));
            mailNotification.sendMailNotificationToCustomer(String.format("Order %d completed successfully", order.getId()), order.getCustomer());
            logService.info("payment finished");
        } catch (Exception e) {
            logService.error("payment refused");
            order.setStatus(Order.OrderStatus.PAYMENT_REFUSED);
            mailNotification.sendMailNotificationToAdmin(String.format("Order %d refused", order.getId()));
        }
    }

    public void cancelOrder(Order order) throws OrderException {
        order.cancelar(logService);
        order.setStatus(Order.OrderStatus.CANCELED);
        mailNotification.sendMailNotificationToAdmin(String.format("Order %d canceled", order.getId()));
        mailNotification.sendMailNotificationToCustomer(String.format("Order %d canceled", order.getId()), order.getCustomer());
        logService.debug(String.format("order %d canceled", order.getId()));
    }

}
