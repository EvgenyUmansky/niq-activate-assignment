package com.niq.personalizedinfo.schema.shopperproduct;

import com.niq.personalizedinfo.schema.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(ShopperProductId.class)
@Table(name = "shopper_product")
public class ShopperProduct {
    @Id
    @Column(name = "shopper_id", nullable = false)
    private String shopperId;

    @Id
    @Column(name = "product_id", nullable = false)
    private String productId;

    @Column(name = "relevant_score", nullable = false)
    Double relevantScore;

    @ManyToOne
    @JoinColumn(name = "product_id", updatable = false, insertable = false)
    Product product; // get joined product data as nested object

    // constructor with specific args
    public ShopperProduct(String shopperId, String productId, Double relevantScore) {
        this.shopperId = shopperId;
        this.productId = productId;
        this.relevantScore = relevantScore;
    }
}