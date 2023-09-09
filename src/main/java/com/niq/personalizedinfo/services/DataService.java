package com.niq.personalizedinfo.services;

import com.niq.personalizedinfo.requests.ShopperPersonalizedProductRequest;
import com.niq.personalizedinfo.schema.product.Product;
import com.niq.personalizedinfo.schema.product.ProductRepository;
import com.niq.personalizedinfo.schema.shopper.Shopper;
import com.niq.personalizedinfo.schema.shopper.ShopperRepository;
import com.niq.personalizedinfo.schema.shopperproduct.ShopperProduct;
import com.niq.personalizedinfo.schema.shopperproduct.ShopperProductId;
import com.niq.personalizedinfo.schema.shopperproduct.ShopperProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataService {
    ProductRepository productRepository;
    ShopperRepository shopperRepository;
    ShopperProductRepository shopperProductRepository;

    @Autowired
    public DataService(ProductRepository productRepository, ShopperRepository shopperRepository, ShopperProductRepository shopperProductRepository) {
        this.productRepository = productRepository;
        this.shopperRepository = shopperRepository;
        this.shopperProductRepository = shopperProductRepository;
    }

    public void insertOrUpdateProduct(List<Product> request) {
        productRepository.saveAll(request);
    }

    @Transactional
    public void insertOrUpdateShopperPersonalizedProduct(List<ShopperPersonalizedProductRequest> request) {
        List<Shopper> shopperList = new ArrayList<>();
        List<ShopperProduct> shopperProductList = new ArrayList<>();
        request.forEach(shopperPersonalizedProductPojo -> {
            // insert or update are expensive actions
            // select is cheaper action
            // check if shopper already exists and add to list if not
            if (!shopperRepository.existsByShopperIdContainsIgnoreCase(shopperPersonalizedProductPojo.getShopperId())) {
                Shopper shopper = new Shopper();
                shopper.setShopperId(shopperPersonalizedProductPojo.getShopperId());

                shopperList.add(shopper);
            }

            // iterate over shelves
            // add the shelves to list
            shopperPersonalizedProductPojo.getShelfList().forEach(shelf -> {
                if(productRepository.existsByProductIdContainsIgnoreCaseAllIgnoreCase(shelf.getProductId())) { // there are product_ids that do not exist in product json
                    ShopperProduct shopperProduct = new ShopperProduct(shopperPersonalizedProductPojo.getShopperId(), shelf.getProductId(), shelf.getRelevancyScore());
                    shopperProductList.add(shopperProduct);
                }
            });
        });

        // save lists of data in batches for better performance
        // batch size is defined in application properties
        shopperRepository.saveAll(shopperList);
        shopperProductRepository.saveAll(shopperProductList);
    }
}
