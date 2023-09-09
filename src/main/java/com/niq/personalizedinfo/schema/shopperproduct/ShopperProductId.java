package com.niq.personalizedinfo.schema.shopperproduct;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ShopperProductId implements Serializable {
    private String shopperId;
    private String productId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ShopperProductId entity = (ShopperProductId) o;
        return Objects.equals(this.productId, entity.productId) &&
                Objects.equals(this.shopperId, entity.shopperId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, shopperId);
    }
}
