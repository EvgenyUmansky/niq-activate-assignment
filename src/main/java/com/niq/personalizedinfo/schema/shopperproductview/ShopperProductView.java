package com.niq.personalizedinfo.schema.shopperproductview;

import com.niq.personalizedinfo.schema.shopperproduct.ShopperProductId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@IdClass(ShopperProductId.class)
@Table(name = "shopper_product_view")
public class ShopperProductView {
    @Id
    @Column(name = "shopper_id", nullable = false)
    private String shopperId;

    @Id
    @Column(name = "product_id", nullable = false)
    private String productId;

    @Column(name = "relevant_score", nullable = false)
    Double relevantScore;

    @Column(name = "category")
    private String category;

    @Column(name = "brand")
    private String brand;
}