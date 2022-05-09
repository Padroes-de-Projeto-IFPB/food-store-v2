package br.edu.ifpb.foodstore.service.mail.observer;

import br.edu.ifpb.foodstore.domain.Order;
import br.edu.ifpb.foodstore.service.log.LogService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AdminEmailNotification implements EmailNotification {

    private final LogService logService;

    private String emailAdministration = "contact@food-store.com";

    @Override
    public void sendMailNotification(String message, String email) {
        logService.info(String.format("Send system message %s to email %s", message, email));
    }

    @Override
    public void orderCanceled(Order order) {
        sendMailNotification(String.format("Order %d canceled", order.getId()), emailAdministration);
    }

    @Override
    public void orderCompleted(Order order) {
        sendMailNotification(String.format("Order %d completed successfully", order.getId()), emailAdministration);
    }

    @Override
    public void orderRefused(Order order) {
        sendMailNotification(String.format("Order %d refused", order.getId()), emailAdministration);
    }

}
