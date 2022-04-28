package br.edu.ifpb.foodstore.service.payment;

public class Paypal implements PaymentInterface {
    @Override
    public String getPayment() {
        return "Do paypal payment!";
    }
}
