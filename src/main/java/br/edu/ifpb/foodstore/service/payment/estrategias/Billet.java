package br.edu.ifpb.foodstore.service.payment.estrategias;

import br.edu.ifpb.foodstore.service.log.LogService;

public class Billet implements PaymentType {
    @Override
    public void pay(LogService logService) {
        logService.info("Do billet payment!");
    }
}
