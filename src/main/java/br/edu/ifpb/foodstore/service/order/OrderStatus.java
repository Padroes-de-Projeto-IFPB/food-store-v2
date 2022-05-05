package br.edu.ifpb.foodstore.domain;

import br.edu.ifpb.foodstore.service.log.LogService;
import br.edu.ifpb.foodstore.service.order.OrderException;
import br.edu.ifpb.foodstore.service.order.OrderInterface;

public enum OrderStatus implements OrderInterface {

//    public enum OrderStatus {
//        IN_PROGRESS, CANCELED, PAYMENT_SUCCESS, PAYMENT_REFUSED
//    }

    IN_PROGRESS{
        @Override
        public String cancel(LogService logService) {
            return "Canceling in progress order";
        }
    },
    CANCELED{
        @Override
        public String cancel(LogService logService) {
            return "Order already canceled!";
        }
    },
    PAYMENT_SUCCESS{
        @Override
        public String cancel(LogService logService){
            return "Canceling already paid order";
        }
    },
    PAYMENT_REFUSED{
        @Override
        public String cancel(LogService logService) {
           return "Canceling refused order";
        }
    };
}