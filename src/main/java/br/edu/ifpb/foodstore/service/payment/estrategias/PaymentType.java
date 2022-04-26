package br.edu.ifpb.foodstore.service.payment.estrategias;

import br.edu.ifpb.foodstore.service.log.LogService;

public interface PaymentType {
    void pay(LogService logService);
}
