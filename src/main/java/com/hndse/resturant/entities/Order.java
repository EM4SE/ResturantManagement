package com.hndse.resturant.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer tableNumber;
    private String orderType;
    private String tableAssistant;
    private int numberOfCustomers;
    @Column(updatable = false, nullable = false)
    private LocalDateTime orderedAt;
    private double totalAmount;
    private String orderStatus;

    @PrePersist
    protected void onCreate(){
        this.orderedAt = LocalDateTime.now();
    }

}
