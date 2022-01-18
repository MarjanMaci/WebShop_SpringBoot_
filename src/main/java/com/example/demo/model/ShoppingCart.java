package com.example.demo.model;

import com.example.demo.model.enumerations.ShoppingCartStatus;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime time;
    @ManyToOne
    private User user;
    @ManyToMany
    private List<Product> productList;
    @Enumerated(value = EnumType.STRING)
    private ShoppingCartStatus status;

    public ShoppingCart() {
        this.id=(long) (Math.random()*1000);
    }

    public ShoppingCart(User user) {
        this.id=(long) (Math.random()*1000);
        this.time = LocalDateTime.now();
        this.user = user;
        this.productList = new ArrayList<>();
        this.status = ShoppingCartStatus.CREATED;
    }
}
