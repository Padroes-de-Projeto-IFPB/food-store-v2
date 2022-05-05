package br.edu.ifpb.foodstore.service.payment;

import br.edu.ifpb.foodstore.domain.paymentStrategy.PaymentInterface;
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

    private final LogService logService;

    public void doPayment(PaymentInterface payment) throws Exception {

        payment.paying(logService);

    }

}
