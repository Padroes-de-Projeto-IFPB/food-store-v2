package br.edu.ifpb.foodstore.domain.paymentStrategy;

import br.edu.ifpb.foodstore.service.log.LogService;

public class CreditCard implements PaymentInterface {

    @Override
    public void paying(LogService logService){
        logService.info("Do credit card payment!");
    }


}
