package br.edu.ifpb.foodstore.service.order;

import br.edu.ifpb.foodstore.service.log.LogService;

public interface OrderActions {
    OrderActions cancelar(LogService logService) throws OrderException;
}
