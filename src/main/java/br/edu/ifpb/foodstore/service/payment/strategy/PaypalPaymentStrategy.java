package br.edu.ifpb.foodstore.service.payment.strategy;

import br.edu.ifpb.foodstore.service.log.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaypalPaymentStrategy implements PaymentStrategy {

    private final LogService logService;

    @Override
    public void pay() {
        logService.info("Do paypal payment!");
    }
}
