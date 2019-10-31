package com.exercise.lunafactory.controller;

import com.exercise.lunafactory.model.Product;
import com.exercise.lunafactory.model.ProductList;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ProductController {

    //curl -X GET "http://factory.lunatech.fr/products" -H "accept: application/json" -H "X-API-LunaFactory: mathieu.mercier77360@gmail.com:12c71b271acfc1bf"

    @RequestMapping(value="/Produits", method=RequestMethod.GET)
    public String listeProduits() {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("X-API-LunaFactory", "mathieu.mercier77360@gmail.com:12c71b271acfc1bf");


        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange("http://factory.lunatech.fr/products", HttpMethod.GET, entity, String.class);

        return responseEntity.toString();
    }

    /*@RequestMapping("/products")
    public List<Product> getProductList(){

        RestTemplate restTemplate = new RestTemplate();
        ProductList response = restTemplate.getForObject(
                "http://factory.lunatech.fr/products",
                ProductList.class);
        List<Product> products = response.getProducts();

        return products;
    }*/


}
