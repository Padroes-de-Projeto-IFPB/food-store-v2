package br.edu.ifpb.foodstore.service.payment.strategy;

public class PagamentoCreditCard implements Pagamento {
    @Override
    public String getPagamento() {
        return "Do credit card payment!";
    }
}
