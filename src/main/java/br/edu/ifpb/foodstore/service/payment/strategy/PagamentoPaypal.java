package br.edu.ifpb.foodstore.service.payment.strategy;

public class PagamentoPaypal implements Pagamento {
    @Override
    public String getPagamento() {
        return "Do paypal payment!";
    }
}
