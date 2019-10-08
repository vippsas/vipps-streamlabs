package no.vipps.twitchecom.entity;

import no.vipps.twitchecom.OrderStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "Orders")
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private String vippsOrderId;

    @NotNull
    private long created;

    public OrderModel() {
    }

    public OrderModel(OrderStatus orderStatus, long created, String vippsOrderId) {
        this.orderStatus = orderStatus;
        this.created = created;
        this.vippsOrderId = vippsOrderId;
    }

    public Integer getId() {
        return id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public String getVippsOrderId() {
        return vippsOrderId;
    }

    public void setVippsOrderId(String vippsOrderId) {
        this.vippsOrderId = vippsOrderId;
    }
}


