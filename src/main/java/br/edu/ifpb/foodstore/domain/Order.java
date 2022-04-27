package br.edu.ifpb.foodstore.domain;

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

    public enum OrderStatus implements OrderState{
        IN_PROGRESS {
            @Override
            public String CancelOrder() {
                return "Canceling in progress order";
            }
        },
        CANCELED {
            @Override
            public String CancelOrder() {
                return "Order already canceled!";
            }
        },
        PAYMENT_SUCCESS {
            @Override
            public String CancelOrder() {
                return "Canceling already paid order";
            }
        },
        PAYMENT_REFUSED {
            @Override
            public String CancelOrder() {
                return "Canceling refused order";
            }
        }
    }

    public void addItem(OrderItem item) {
        this.items.add(item);
    }

    public void removeItem(OrderItem item) {
        this.items.remove(item);
    }

}
