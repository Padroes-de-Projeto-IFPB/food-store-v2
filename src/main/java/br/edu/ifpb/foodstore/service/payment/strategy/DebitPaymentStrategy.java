package br.edu.ifpb.foodstore.service.payment.strategy;

import br.edu.ifpb.foodstore.service.log.LogService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DebitPaymentStrategy implements PaymentStrategy {

    private final LogService logService;

    @Override
    public void pay() {
        logService.info("Do debit payment!");
    }
}
