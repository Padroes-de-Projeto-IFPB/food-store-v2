package br.edu.ifpb.foodstore.service.payment.strategy;

public class PagamentoDebit implements Pagamento {
    @Override
    public String getPagamento() {
        return "Do debit payment!";
    }
}
