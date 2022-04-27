package br.edu.ifpb.foodstore.service.payment.strategy;

public class PagamentoBillet implements Pagamento {
    @Override
    public String getPagamento() {
        return "Do billet payment!";
    }
}
