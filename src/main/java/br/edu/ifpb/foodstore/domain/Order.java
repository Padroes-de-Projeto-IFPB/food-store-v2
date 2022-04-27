package br.edu.ifpb.foodstore.domain;

import br.edu.ifpb.foodstore.service.log.LogService;
import br.edu.ifpb.foodstore.service.order.OrderException;
import br.edu.ifpb.foodstore.service.order.OrderActions;
import lombok.*;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Customer customer;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar creationDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items;

    private OrderStatus status = OrderStatus.IN_PROGRESS;


    public enum OrderStatus implements OrderActions {
        IN_PROGRESS{
            @Override
            public OrderActions cancelar(LogService logService) {
                logService.info("Canceling in progress order");
                return CANCELED;
            }
        },

        CANCELED{
            @Override
            public OrderActions cancelar(LogService logService) throws OrderException {
                throw new OrderException("Order already canceled!");
            }
        },

        PAYMENT_SUCCESS{
            @Override
            public OrderActions cancelar(LogService logService){
                logService.info("Canceling already paid order");
                return CANCELED;
            }
        },

        PAYMENT_REFUSED{
            @Override
            public OrderActions cancelar(LogService logService) {
                logService.info("Canceling refused order");
                return CANCELED;
            }
        };

    }

    public void addItem(OrderItem item) {
        this.items.add(item);
    }

    public void removeItem(OrderItem item) {
        this.items.remove(item);
    }

    public void cancelar(LogService logService) throws OrderException {
        this.status = (OrderStatus) status.cancelar(logService);
    }

}
