package br.edu.ifpb.foodstore.service.payment;

public class payment {
  public PaymentStrategy TipoPagamento(PaymentService.PaymentType type) throws Exception {
    switch (type)  {
        case CREDIT_CARD:
            return new PaymentBillet();

        case DEBIT:
            return new PaymentDebit();

        case PAYPAL:
            return new PaymentPayPal();

        case BILLET:
            return new PaymentBillet();

        default:
            throw new Exception("unknown payment method");
    }
}
}
