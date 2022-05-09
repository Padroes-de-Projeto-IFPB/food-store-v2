package br.edu.ifpb.foodstore.service.payment;

import br.edu.ifpb.foodstore.service.payment.strategy.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    public void doPayment(PaymentStrategy paymentStrategy) throws Exception {
        paymentStrategy.pay();
    }

}
