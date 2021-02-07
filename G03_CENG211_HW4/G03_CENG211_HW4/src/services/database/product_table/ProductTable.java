package services.database.product_table;

import models.Product;
import models.user.Producer;

import java.util.*;

/**
 * Product table service class
 *
 * @author 270201036 & 270201089
 * @author Oguzhan MERTTURK & Harun Eren MUTLU
 * @version HW4
 */

public class ProductTable implements  IProductTable {

    private Hashtable<String, LinkedList<Product>> productsMap;

    /**
     * Instantiates a new Product table.
     *
     * @param dataList the data list
     */
    public ProductTable(ArrayList<ArrayList<String>> dataList) {
        productsMap = new Hashtable<>();
        // According to given data, for loop adds new types as a key to the productMap instance variable.
        // If given data's product type is in the system, loop creates new product object in specified key list.
        for (ArrayList<String> productData: dataList) {
            addProduct(productData.get(0),productData.get(2),productData.get(3),productData.get(1));
        }

    }

    @Override
    public LinkedList<String> getProductTypeList() {
        LinkedList<String> productList = new LinkedList<>();
        for (String key: productsMap.keySet()) { productList.add(key); }
        return productList;
    }

    @Override
    public LinkedList<Product> getProductList() {
        LinkedList<Product> productList = new LinkedList<>();
        productsMap.forEach(
                (s, products) -> products.forEach(
                        product -> productList.add(product)));
        return productList;
    }
    @Override
    public LinkedList<Product> getProductListOfSpecifiedType(String type){
        return productsMap.get(type);
    }

    @Override
    public LinkedList<Product> getProductListForSpecifiedProducer(Producer producer) {
        LinkedList<Product> productsFromSpecifiedProducer = new LinkedList<>();
        productsMap.forEach(
                (s, products) -> products.forEach(
                        product -> {
                            if(product.getProducerID().equals(producer.getUserID())) productsFromSpecifiedProducer.add(product);
                        }));
        return productsFromSpecifiedProducer;
    }
    @Override
    public LinkedHashSet<String> getProductTypeListForSpecifiedProducer(Producer producer) {
        LinkedHashSet<String> productsFromSpecifiedProducer = new LinkedHashSet<>();
        productsMap.forEach(
                (s, products) -> products.forEach(
                        product -> {
                            if(product.getProducerID().equals(producer.getUserID())) productsFromSpecifiedProducer.add(s);
                        }));
        return productsFromSpecifiedProducer;
    }

    @Override
    public LinkedList<Product> getProductListForSpecifiedProductTypeForProducer(Producer producer, String type) {
        LinkedList<Product> productsFromSpecifiedProducer = new LinkedList<>();
        getProductListOfSpecifiedType(type).forEach(product -> {
            if (product.getProducerID().equals(producer.getUserID())) productsFromSpecifiedProducer.add(product);
        });
        return productsFromSpecifiedProducer;
    }

    @Override
    public void addProduct(String productName,
                           String productType,
                           String producerID) {

        String key = productType.toLowerCase();

        // If given product type is not in the hashtable, statement creates a new key-value pair
        if (!productsMap.containsKey(key)){
            LinkedList<Product> newProductTypeList = new LinkedList<>();
            newProductTypeList.add(new Product(UUID.randomUUID().toString(), producerID, productName, key));
            productsMap.put(key, newProductTypeList);
            return;
        }
        productsMap.get(key).add(new Product(UUID.randomUUID().toString(), producerID, productName, key));
    }

    /**
     * Add product.
     * Overloading add method to simplify constructor
     * @param productID   the product id
     * @param productName the product name
     * @param productType the product type
     * @param producerID  the producer id
     */
    public void addProduct(String productID,
                           String productName,
                           String productType,
                           String producerID) {

        String key = productType.toLowerCase();

        // If given product type is not in the hashtable, statement creates a new key-value pair
        if (!productsMap.containsKey(key)){
            LinkedList<Product> newProductTypeList = new LinkedList<>();
            newProductTypeList.add(new Product(productID, producerID, productName, key));
            productsMap.put(key, newProductTypeList);
            return;
        }
        productsMap.get(key).add(new Product(productID, producerID, productName, key));
    }

    @Override
    public String getProductNameOfGivenProduct(String productID){
        for (Product product: getProductList()) {
            if(product.getProductID().equals(productID)){
                return product.getProductName();
            }
        }
        return null;
    }

    @Override
    public String[] toCSVArray() {
        LinkedList<Product> products = getProductList();
        LinkedList<String> productsListCSV = new LinkedList<>();
        for (Product product: products) {
            productsListCSV.add(product.toCSV());
        }
        String[] productsCSV = new String[productsListCSV.size()];
        return productsListCSV.toArray(productsCSV);
    }

    @Override
    public Boolean verifyProductType(String type){
        type = type.toLowerCase();
        if(productsMap.containsKey(type)) return true;
        return false;
    }
}