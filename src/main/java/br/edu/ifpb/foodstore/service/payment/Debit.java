package br.edu.ifpb.foodstore.service.payment;

public class Debit implements Payment {

    @Override
    public String getPayment() {
        return "Do debit payment!";
    }
}
