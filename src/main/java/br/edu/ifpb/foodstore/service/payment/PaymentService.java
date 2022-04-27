package br.edu.ifpb.foodstore.service.payment;

import br.edu.ifpb.foodstore.service.log.LogHandler;
import br.edu.ifpb.foodstore.service.log.LogService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {

    private PaymentStrategy paymentStrategy;

    private payment type = new payment();

    public enum PaymentType {
        CREDIT_CARD, DEBIT, BILLET, PAYPAL
    }

    private final LogService logService;

    public void doPayment(PaymentType paymentType) throws Exception {
        paymentStrategy = type.TipoPagamento(paymentType);

        logService.info(paymentStrategy.pay());
    }

}
