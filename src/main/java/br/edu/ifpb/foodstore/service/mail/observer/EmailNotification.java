package br.edu.ifpb.foodstore.service.mail.observer;

import br.edu.ifpb.foodstore.service.mail.observer.OrderEventsSubscriber;

public interface EmailNotification extends OrderEventsSubscriber {

    void sendMailNotification(String message, String email);

}
