package br.edu.ifpb.foodstore.domain;

import br.edu.ifpb.foodstore.service.log.LogService;

public class Debit implements Payment {

    @Override
    public void doPay(LogService logService) {
        logService.info("Do debit payment!");
    }
}
