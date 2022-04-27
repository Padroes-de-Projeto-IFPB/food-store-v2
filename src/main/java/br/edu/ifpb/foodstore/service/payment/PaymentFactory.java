package br.edu.ifpb.foodstore.service.payment;

public class PaymentFactory {

    public Payment TypePayment(PaymentService.PaymentType type) throws Exception {
        switch (type)  {
            case CREDIT_CARD:
                return new CreditCard();
            case DEBIT:
                return new Debit();
            case PAYPAL:
                return new Paypal();
            case BILLET:
                return new Billet();
            default:
                throw new Exception("unknown payment method");
        }
    }
}
