package com.niq.personalizedinfo.controllers;

import com.niq.personalizedinfo.requests.ShopperPersonalizedProductRequest;
import com.niq.personalizedinfo.schema.product.Product;
import com.niq.personalizedinfo.services.DataService;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping(value = "/data")
public class DataController {
    DataService dataService;

    @Autowired
    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @PostMapping(value = "/add-product-list")
    public String addProductList(@RequestBody List<Product> request) {
        try {
            dataService.insertOrUpdateProduct(request);

            String response = "[addProductList] Product List has been added successfully, response code: %d".formatted(HttpStatus.SC_CREATED);
            log.info(response);
            return response;
        } catch (Exception e) {
            String response = "[addProductList] Failed to add Product List, response code: %d".formatted(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            log.error(response, e);
            return response;
        }
    }

    @PostMapping(value = "/add-shopper-personalized-product-list")
    public String addShopperPersonalizedProductList(@RequestBody List<ShopperPersonalizedProductRequest> request) {
        try {
            dataService.insertOrUpdateShopperPersonalizedProduct(request);

            String response = "[addShopperPersonalizedProductList] Shopper Personalized Product List has been added successfully, response code: %d".formatted(HttpStatus.SC_CREATED);
            log.info(response);
            return response;
        } catch (Exception e) {
            String response = "[addShopperPersonalizedProductList] Failed to add Shopper Personalized Product List, response code: %d".formatted(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            log.error(response, e);
            return response;
        }
    }
}
