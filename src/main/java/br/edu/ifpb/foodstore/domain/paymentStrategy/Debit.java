package br.edu.ifpb.foodstore.domain.paymentStrategy;

import br.edu.ifpb.foodstore.service.log.LogService;

public class Debit implements PaymentInterface {

    @Override
    public void paying(LogService logService){
        logService.info("Do debit payment!");
    }
}
