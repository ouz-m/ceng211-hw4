package services.database.product_table;

import models.Product;
import models.user.Producer;
import services.database.ITable;

import java.util.LinkedHashSet;
import java.util.LinkedList;

/**
 * Product table service interface
 *
 * @author 270201036 & 270201089
 * @author Oguzhan MERTTURK & Harun Eren MUTLU
 * @version HW4
 */
public interface IProductTable extends ITable {

    /**
     * Travels on the table and collects the product types as a unique variable and returns it
     *
     * @return the product type list
     */
    LinkedList<String> getProductTypeList();

    /**
     * Travels on table and returns the product object list
     *
     * @return the product list
     */
    LinkedList<Product> getProductList();

    /**
     * Travels on table and returns the product object list according to specified producer
     *
     * @param producer the producer
     * @return the product list for specified producer
     */
    LinkedList<Product> getProductListForSpecifiedProducer(Producer producer);

    /**
     * Gets product type list for specified producer.
     *
     * @param producer the producer
     * @return the product type list for specified producer
     */
    LinkedHashSet<String> getProductTypeListForSpecifiedProducer(Producer producer);

    /**
     * Gets product list for specified product type for producer.
     *
     * @param producer the producer
     * @param type     the type
     * @return the product list for specified product type for producer
     */
    LinkedList<Product> getProductListForSpecifiedProductTypeForProducer(Producer producer, String type);

    /**
     * Adds a product to table; if type is unknown to the system, method creates a new type in system
     *
     * @param productName the product name
     * @param productType the product type
     * @param producerID  the producer id
     */
    void addProduct(String productName, String productType, String producerID);

    /**
     * Travels on table and returns the product object list for specified type
     *
     * @param type the type
     * @return the product list of specified type
     */
    LinkedList<Product> getProductListOfSpecifiedType(String type);

    /**
     * Gets product name of given product.
     *
     * @param productID the product id
     * @return the product name of given product
     */
    String getProductNameOfGivenProduct(String productID);

    /**
     * Verify product type boolean.
     *
     * @param type the type
     * @return the boolean
     */
    Boolean verifyProductType(String type);
}
