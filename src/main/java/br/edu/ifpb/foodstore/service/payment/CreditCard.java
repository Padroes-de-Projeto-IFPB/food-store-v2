package br.edu.ifpb.foodstore.service.payment;

public class CreditCard implements Payment {

    @Override
    public String getPayment() {
        return "Do credit card payment!";
    }
}
