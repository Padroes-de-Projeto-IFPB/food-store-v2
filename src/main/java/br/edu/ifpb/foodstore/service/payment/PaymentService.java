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

    public enum PaymentType {
        CREDIT_CARD, DEBIT, BILLET, PAYPAL
    }

    private final LogService logService;
    private PaymentInterface payment;
    private ManagerPayment manager = new ManagerPayment();


    public void doPayment(PaymentType paymentType) throws Exception {
        try {
            payment = manager.typePayment(paymentType);
            logService.info(payment.getPayment());
        }
        catch (Exception e) {
            throw new Exception(e);
        }
    }
}
