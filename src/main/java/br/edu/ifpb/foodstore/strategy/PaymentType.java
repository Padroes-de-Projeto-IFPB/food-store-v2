package br.edu.ifpb.foodstore.strategy;

import br.edu.ifpb.foodstore.service.log.LogService;

public interface PaymentType {

    void generateAction(LogService logService);

}
