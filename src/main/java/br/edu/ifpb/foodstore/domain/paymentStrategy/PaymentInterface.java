package br.edu.ifpb.foodstore.domain.paymentStrategy;

import br.edu.ifpb.foodstore.service.log.LogService;

public interface PaymentInterface {

    public void paying(LogService logService);
}
