package com.exercise.lunafactory.controller;

import com.exercise.lunafactory.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@RestController
public class ProductController {

    //Value to change to keep or remove the duplicates in the list of products
    boolean removeDuplicates = true;

    //current method called to show homepage of the web application
    @RequestMapping(value="/Lunafactory")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("home");

        return modelAndView;
    }

    //Method called to show the web page of the most expensive products
    @RequestMapping(value="/TopProducts")
    public ModelAndView greeting() {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("productItems", topPrices());
        ModelAndView modelAndView = new ModelAndView("top_products", model);

        return modelAndView;
    }

    //Method called to show the web page of the assembled expensive products
    @RequestMapping(value="/Products",method=RequestMethod.GET)
    public ModelAndView sortedProducts() {
        List<Product> sortedProductList = new ArrayList<>();

        //We check the boolean value to know if we want to remove the duplicates
        if(removeDuplicates){
            sortedProductList=sortProductsWithNoDuplicates();
        } else {
            sortedProductList=sortAssembledProducts();
        }

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("productItems", sortedProductList);
        ModelAndView modelAndView = new ModelAndView("list_products", model);

        return modelAndView;
    }

    //Method used to remove all products with Assembled value equal to false
    public List<Product> sortAssembledProducts() {
        List<Product> productList = productList();
        List<Product> sortedProductList = new ArrayList<>();
        for (Product prod : productList) {
            if (prod.isAssembled()) {
                sortedProductList.add(prod);
            }
        }
        return sortedProductList;
    }

    //Method used to remove duplicates, checking if Product[i-1] has the same name
    public List<Product> sortProductsWithNoDuplicates() {

        List<Product> productList = productList();
        List<Product> sortedProductList = new ArrayList<>();
        for(int i=0;i<productList.size();i++){
            Product prod = productList.get(i);
            if(prod.isAssembled()){
                if(i==0){
                    sortedProductList.add(prod);
                } else if (!prod.getName().equals(productList.get(i-1).getName())){
                    sortedProductList.add(prod);
                }

            }
        }
        return sortedProductList;
    }


    public List<Product> topPrices(){
        List<Product> productList = new LinkedList<>();
        productList.addAll(productList());
        List<Product> topPricesList = new ArrayList<>();

        for(int i=0;i<15;i++){
            Product maxPriceProduct = getMaxPriceProd(productList);
            topPricesList.add(maxPriceProduct);
            if (productList.contains(maxPriceProduct)){
                productList.remove(maxPriceProduct);
            }

        }
        return topPricesList;
    }

    public Product getMaxPriceProd(List<Product> prodList){
        Product maxPriceProd= prodList.iterator().next();
        double maxPrice = maxPriceProd.getPrice();
        for(Product prod : prodList){
            if(prod.getPrice()>maxPrice){
                maxPriceProd = prod;
                maxPrice = prod.getPrice();
            }
        }
        return maxPriceProd;
    }

    //Make a get call on the RESTAPI
    //We get a json with the list of products and we store it in a java List of Product objects
    public List<Product> productList(){
        RestTemplate restTemplate = new RestTemplate();

        //Store the token to send it with the GET request
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-API-LunaFactory", "mathieu.mercier77360@gmail.com:12c71b271acfc1bf");
        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange("http://factory.lunatech.fr/products", HttpMethod.GET, entity, String.class);
        String responseContent = responseEntity.getBody();
        ObjectMapper mapper = new ObjectMapper();
        List<Product> productList;
        try {
            //new LinkedList<String>(Arrays.asList(split)); CAN BE USED
            productList = Arrays.asList(mapper.readValue(responseContent, Product[].class));
        } catch (JsonProcessingException e) {
            productList = null;
            e.printStackTrace();
        }

        return productList;
    }

}
