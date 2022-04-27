package br.edu.ifpb.foodstore.strategy;

import br.edu.ifpb.foodstore.service.log.LogService;

public class Debit implements PaymentType {

    @Override
    public void generateAction(LogService logService) {
        logService.info("Do debit payment!");
    }
}
