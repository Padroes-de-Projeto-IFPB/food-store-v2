package br.edu.ifpb.foodstore.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum OrderStatus {

    IN_PROGRESS("Canceling in progress order"),
    CANCELED("Order already canceled!"),
    PAYMENT_SUCCESS("Canceling already paid order"),
    PAYMENT_REFUSED("Canceling refused order");

    @Getter
    private String statusMensager;
}
