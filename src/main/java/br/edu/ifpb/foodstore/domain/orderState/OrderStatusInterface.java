package br.edu.ifpb.foodstore.domain.orderState;

import br.edu.ifpb.foodstore.service.log.LogService;

public interface OrderStatusInterface {

    public String orderCancel(LogService logService);

}
