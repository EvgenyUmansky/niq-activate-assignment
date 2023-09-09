package com.niq.personalizedinfo.schema.shopperproductview;

import com.niq.personalizedinfo.schema.shopperproduct.ShopperProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShopperProductViewRepository extends JpaRepository<ShopperProductView, ShopperProductId>,
        JpaSpecificationExecutor<ShopperProductView> {
}
