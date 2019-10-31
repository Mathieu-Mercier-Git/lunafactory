package com.exercise.lunafactory.controller;

import com.exercise.lunafactory.model.Dimension;
import com.exercise.lunafactory.model.Product;
import com.exercise.lunafactory.model.ProductList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
public class ProductController {

    //private String jsonStr = "[{\"id\":1,\"ean\":\"3484611\",\"name\":\"ABSORB\",\"weight\":59.17,\"description\":\"leathercare set\",\"price\":996.01,\"assembled\":false,\"dimension\":{\"width\":25.23,\"depth\":60.85,\"height\":5.33}},{\"id\":2,\"ean\":\"2246942\",\"name\":\"ABSORB\",\"weight\":53.62,\"description\":\"leathercleaner\",\"price\":404.07,\"assembled\":false,\"dimension\":{\"width\":61.47,\"depth\":50.61,\"height\":18.45}},{\"id\":3,\"ean\":\"1655204\",\"name\":\"ADMETE RUND\",\"weight\":96.61,\"description\":\"chair pad\",\"price\":979.7,\"assembled\":true,\"dimension\":{\"width\":49.88,\"depth\":82.89,\"height\":94.67}}]";
    //private String jsonStr = "[{\"id\":1,\"ean\":\"3484611\",\"name\":\"ABSORB\",\"weight\":59.17,\"description\":\"leathercare set\",\"price\":996.01,\"assembled\":false,\"dimension\":{\"width\":25.23,\"depth\":60.85,\"height\":5.33}}]";
    //curl -X GET "http://factory.lunatech.fr/products" -H "accept: application/json" -H "X-API-LunaFactory: mathieu.mercier77360@gmail.com:12c71b271acfc1bf"

    //@RequestMapping(value="/Produits",produces = MediaType.APPLICATION_JSON_VALUE,method=RequestMethod.GET)
    @RequestMapping(value="/Produits",method=RequestMethod.GET)
    public String listeProduits() {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-API-LunaFactory", "mathieu.mercier77360@gmail.com:12c71b271acfc1bf");


        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange("http://factory.lunatech.fr/products", HttpMethod.GET, entity, String.class);
        //ProductList responseEntity = restTemplate.getForObject("http://factory.lunatech.fr/products", ProductList.class, entity);
        String responseContent = responseEntity.getBody();
        ObjectMapper mapper = new ObjectMapper();
        List<Product> productList;
        try {
            productList = Arrays.asList(mapper.readValue(responseContent, Product[].class));
            //productList = mapper.readValue(responseContent, ProductList.class);
        } catch (JsonProcessingException e) {
            productList = null;
            e.printStackTrace();
        }

        /*//Product(int id, String ean, String name, double weigth, String description, double price, boolean assembled, Dimension dimension)
        Dimension dim = new Dimension(23.23, 34.34, 56.56);
        Product product = new Product(2, "1233", "chaise", 23.34, "C'est une chaise tavu :))))", 45.001, true, dim);
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString;
        try{
            jsonInString = mapper.writeValueAsString(product);
        } catch (Exception e){
            jsonInString = "C'est un échec";
        }


        String jsonStr = "{\"id\":1,\"ean\":\"3484611\",\"name\":\"ABSORB\",\"weight\":59.17,\"description\":\"leathercare set\",\"price\":996.01,\"assembled\":false,\"dimension\":{\"width\":25.23,\"depth\":60.85,\"height\":5.33}}";
        Product product2;
        try{
            product2  = mapper.readValue(jsonStr, Product.class);
            product2.setDescription("AHAHAHA c'est le feu ;)");
        } catch (Exception e ){
            product2 = new Product();
            product2.setDescription("WESSSSSSSH");
        }*/


        //return jsonStr+"\n"+jsonInString;
        //return product2.getDescription()+jsonInString+"\n\n"+jsonStr;
        return productList.toString();
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
