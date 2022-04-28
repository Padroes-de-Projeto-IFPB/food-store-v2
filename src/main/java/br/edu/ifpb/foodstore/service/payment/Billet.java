package br.edu.ifpb.foodstore.service.payment;

public class Billet implements PaymentInterface {
    @Override
    public String getPayment() {
        return "Do billet payment!";
    }
}
