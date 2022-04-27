package br.edu.ifpb.foodstore.service.payment;

public class PaymentDebit implements PaymentStrategy{
  @Override
  public String pay() {
    String order = "Compra finalizada no Cartão de Débito";
    return order;
  }
}
