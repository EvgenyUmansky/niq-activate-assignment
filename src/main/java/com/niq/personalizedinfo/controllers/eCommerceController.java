package com.niq.personalizedinfo.controllers;

import com.niq.personalizedinfo.services.DataService;
import com.niq.personalizedinfo.services.eCommerceService;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping(value = "/e-commerce")
public class eCommerceController {
    eCommerceService commerceService;

    @Autowired
    public eCommerceController(eCommerceService commerceService) {
        this.commerceService = commerceService;
    }

    @GetMapping(value = "products-by-shopper/get")
    public List<String> getProductsByShopper(@RequestParam("shopperId") String shopperId,
                                             @RequestParam(value = "category", required = false) String category,
                                             @RequestParam(value = "brand", required = false) String brand,
                                             @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit) {
        try {
            List<String> products = commerceService.getProductsByShopper(shopperId, category, brand, limit);
            String response = "[getProductsByShopper] Product List has been added successfully";
            log.info(response);

            return products;
        } catch (Exception e) {
            String response = "[getProductsByShopper] Failed to get Product List, response code: %d".formatted(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            log.error(response, e);
            return null;
        }
    }

    @GetMapping(value = "shoppers-by-products/get")
    public List<String> getShoppersByProduct(@RequestParam("productId") String productId,
                                             @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit) {
        try {
            List<String> shoppers = commerceService.getShoppersByProduct(productId, limit);
            String response = "[getShoppersByProduct] Product List has been added successfully";
            log.info(response);

            return shoppers;
        } catch (Exception e) {
            String response = "[getShoppersByProduct] Failed to get Product List, response code: %d".formatted(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            log.error(response, e);
            return null;
        }
    }
}
