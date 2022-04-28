package br.edu.ifpb.foodstore.service.payment;

public class Debit implements PaymentInterface {
    @Override
    public String getPayment() {
        return "Do debit payment!";
    }
}
