package br.edu.ifpb.foodstore.service.payment;

public class PaymentPayPal implements PaymentStrategy{
  @Override
  public String pay() {
    String order = "Compra finalizada no PayPal";
    return order;
  }
}
