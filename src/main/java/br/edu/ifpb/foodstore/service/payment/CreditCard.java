package br.edu.ifpb.foodstore.service.payment;

public class CreditCard implements PaymentInterface {
    @Override
    public String getPayment() {
        return "Do credit card payment!";
    }
}
