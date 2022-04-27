package br.edu.ifpb.foodstore.service.payment;

public interface PaymentStrategy {
  default String pay(){
    return "";
  }
}
