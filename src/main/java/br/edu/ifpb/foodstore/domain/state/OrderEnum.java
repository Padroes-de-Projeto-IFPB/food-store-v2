package br.edu.ifpb.foodstore.domain.state;

public enum OrderEnum implements OrderStatus {
    IN_PROGRESS {
        @Override
        public String cancelar() {
            return "Canceling in progress order";
        }
    }
    ,CANCELED {
        @Override
        public String cancelar() {
            return "Order already canceled!";
        }
    }
    ,PAYMENT_SUCCESS {
        @Override
        public String cancelar() {
            return "Canceling already paid order";
        }
    }
    ,PAYMENT_REFUSED {
        @Override
        public String cancelar() {
            return "Canceling refused order";
        }
    }

}
