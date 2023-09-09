package com.niq.personalizedinfo.schema.shopperproduct;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopperProductRepository extends JpaRepository<ShopperProduct, ShopperProductId>, JpaSpecificationExecutor<ShopperProduct> {
}
