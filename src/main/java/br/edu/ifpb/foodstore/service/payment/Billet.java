package br.edu.ifpb.foodstore.service.payment;

public class Billet implements Payment {

    @Override
    public String getPayment() {
        return "Do billet payment!";
    }
}
