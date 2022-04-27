package br.edu.ifpb.foodstore.service.payment.strategy;

import br.edu.ifpb.foodstore.service.payment.PaymentService;

public class PagamentoFactory {

    public PaymentService.PaymentType type;

    public Pagamento typePayment(PaymentService.PaymentType type) throws Exception {
        this.type = type;

        switch (this.type) {
            case CREDIT_CARD:
                return new PagamentoCreditCard();
            case BILLET:
                return new PagamentoBillet();
            case DEBIT:
                return new PagamentoDebit();
            case PAYPAL:
                return new PagamentoPaypal();
            default:
                throw new Exception("unknown payment method");
        }
    }
}
