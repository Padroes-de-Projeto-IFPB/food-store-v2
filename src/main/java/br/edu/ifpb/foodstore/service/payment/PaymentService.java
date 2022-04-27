package br.edu.ifpb.foodstore.service.payment;

import br.edu.ifpb.foodstore.service.log.LogService;
import br.edu.ifpb.foodstore.service.payment.strategy.Pagamento;
import br.edu.ifpb.foodstore.service.payment.strategy.PagamentoFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {

    public Pagamento pagamento;
    public PagamentoFactory typePayment = new PagamentoFactory();

    public enum PaymentType {
        CREDIT_CARD, DEBIT, BILLET, PAYPAL
    }

    private final LogService logService;

    public void doPayment(PaymentService.PaymentType paymentType) throws Exception {
        this.pagamento = this.typePayment.typePayment(paymentType);
        logService.info(this.pagamento.getPagamento());
    }

}
