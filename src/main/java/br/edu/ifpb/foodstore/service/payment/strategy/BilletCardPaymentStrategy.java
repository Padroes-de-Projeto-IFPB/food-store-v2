package br.edu.ifpb.foodstore.service.payment.strategy;

import br.edu.ifpb.foodstore.service.log.LogService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BilletCardPaymentStrategy implements PaymentStrategy {

    private final LogService logService;

    @Override
    public void pay() throws Exception {
        logService.info("Do billet payment!");
    }
}
