package br.edu.ifpb.foodstore.domain.orderState;

import br.edu.ifpb.foodstore.service.log.LogService;

public enum OrderStatus implements OrderStatusInterface {

    IN_PROGRESS{
        @Override
        public String orderCancel(LogService logService) {
            return "Canceling in progress order";
        }
    },
    CANCELED{
        @Override
        public String orderCancel(LogService logService) {
            return "Order already canceled!";
        }
    },
    PAYMENT_SUCCESS{
        @Override
        public String orderCancel(LogService logService){
            return "Canceling already paid order";
        }
    },
    PAYMENT_REFUSED{
        @Override
        public String orderCancel(LogService logService) {
            return "Canceling refused order";
        }
    };
}
