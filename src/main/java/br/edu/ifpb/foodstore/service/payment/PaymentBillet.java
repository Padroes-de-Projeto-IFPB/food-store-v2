package br.edu.ifpb.foodstore.service.payment;

public class PaymentBillet implements PaymentStrategy{
  @Override
  public String pay() {
    String order = "Compra finalizada no Boleto";
    return order;
  }
}
