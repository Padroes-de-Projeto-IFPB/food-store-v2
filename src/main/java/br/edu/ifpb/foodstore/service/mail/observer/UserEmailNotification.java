package br.edu.ifpb.foodstore.service.mail.observer;

import br.edu.ifpb.foodstore.domain.Order;
import br.edu.ifpb.foodstore.service.log.LogService;
import lombok.AllArgsConstructor;

/***
 * Padrão observer: implementa subscriber para publisher OrderService
 */
@AllArgsConstructor
public class UserEmailNotification implements EmailNotification {

    private final LogService logService;

    @Override
    public void sendMailNotification(String message, String email) {
        logService.info(String.format("Send user message %s to email %s", message, email));
    }

    @Override
    public void orderCreated(Order order) {
        sendMailNotification("Order "+order.getId()+ "was created", order.getCustomer().getEmail());
    }

    @Override
    public void orderCompleted(Order order) {
        sendMailNotification(String.format("Order %d completed successfully", order.getId()), order.getCustomer().getEmail());
    }

    @Override
    public void orderCanceled(Order order) {
        sendMailNotification(String.format("Order %d canceled", order.getId()), order.getCustomer().getEmail());
    }

}
