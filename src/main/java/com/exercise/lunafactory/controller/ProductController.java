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

    @RequestMapping(value="/Lunafactory")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("home");

        return modelAndView;
    }

    @RequestMapping(value="/TopProducts")
    public ModelAndView greeting() {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("productItems", topPrices());
        ModelAndView modelAndView = new ModelAndView("top_products", model);

        return modelAndView;
    }

    //curl -X GET "http://factory.lunatech.fr/products" -H "accept: application/json" -H "X-API-LunaFactory: mathieu.mercier77360@gmail.com:12c71b271acfc1bf"

    //@RequestMapping(value="/Produits",produces = MediaType.APPLICATION_JSON_VALUE,method=RequestMethod.GET)
    @RequestMapping(value="/Products",method=RequestMethod.GET)
    public ModelAndView sortProducts() {
        List<Product> productList = productList();
        List<Product> sortedProductList = new ArrayList<>();


        for (Product prod : productList) {
            if (prod.isAssembled()) {
                sortedProductList.add(prod);
            }
        }

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("productItems", sortedProductList);
        ModelAndView modelAndView = new ModelAndView("list_products", model);

        return modelAndView;
    }

    public String sortProductsWithNoDuplicates() {
        boolean removeDup=true;

        List<Product> productList = productList();
        List<Product> sortedProductList = new ArrayList<>();
        String result = "";

        for(int i=0;i<productList.size();i++){
            Product prod = productList.get(i);
            if(prod.isAssembled()){
                if(i==0){
                    sortedProductList.add(prod);
                    result += prod.getName()+" / ";
                } else if (!prod.getName().equals(productList.get(i-1).getName())){
                    sortedProductList.add(prod);
                    result += prod.getName()+" / ";
                }

            }
        }
        return result;
    }

    public List<Product> topPrices(){
        String result="";
        List<Product> productList = new LinkedList<>();
        productList.addAll(productList());
        List<Product> topPricesList = new ArrayList<>();

        for(int i=0;i<15;i++){
            Product maxPriceProduct = getMaxPriceProd(productList);
            topPricesList.add(maxPriceProduct);
            result+=maxPriceProduct.getPrice()+" / ";
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

    public List<Product> productList(){
        RestTemplate restTemplate = new RestTemplate();

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

    /*@RequestMapping("/products")
    public List<Product> getProductList(){

        RestTemplate restTemplate = new RestTemplate();
        ProductList response = restTemplate.getForObject(
                "http://factory.lunatech.fr/products",
                ProductList.class);
        List<Product> products = response.getProducts();

        return products;
    }*/

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


}
