package br.edu.ifpb.foodstore.service.payment;

public class Paypal implements Payment {

    @Override
    public String getPayment() {
        return "Do paypal payment!";
    }
}
