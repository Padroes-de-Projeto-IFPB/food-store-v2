package br.edu.ifpb.foodstore.service.mail.observer;

import br.edu.ifpb.foodstore.domain.Order;

/**
 * Padrão Observer
 */
public interface OrderEventsSubscriber {

    default void orderCreated(Order order) {
        //no message was triggered
    }

    default void orderCanceled(Order order) {
        //no message was triggered
    }

    default void orderCompleted(Order order) {
        //no message was triggered
    }

    default void orderRefused(Order order) {
        //no message was triggered
    }

}
