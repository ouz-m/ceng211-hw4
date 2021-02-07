package models;

import java.util.List;

/**
 * Product class
 *
 * @author 270201036 & 270201089
 * @author Oguzhan MERTTURK & Harun Eren MUTLU
 * @version HW4
 */
public class Product {
    private String productID;
    private String producerID;
    private String productName;
    private String productType;

    /**
     * Instantiates a new Product.
     *
     * @param productID   the product id
     * @param producerID  the producer id
     * @param productName the product name
     * @param productType the product type
     */
    public Product(String productID,
                   String producerID,
                   String productName,
                   String productType) {
        this.productID = productID;
        this.producerID = producerID;
        this.productName = productName;
        this.productType = productType;
    }

    /**
     * Instantiates a new Product.
     *
     * @param data the data
     */
    public Product(List<String> data) {
        this(data.get(0), data.get(1), data.get(2), data.get(3));
    }

    /**
     * Gets product id.
     *
     * @return the product id
     */
    public String getProductID() {
        return productID;
    }

    /**
     * Sets product id.
     *
     * @param productID the product id
     */
    public void setProductID(String productID) {
        this.productID = productID;
    }

    /**
     * Gets producer id.
     *
     * @return the producer id
     */
    public String getProducerID() {
        return producerID;
    }

    /**
     * Sets producer id.
     *
     * @param producerID the producer id
     */
    public void setProducerID(String producerID) {
        this.producerID = producerID;
    }

    /**
     * Gets product name.
     *
     * @return the product name
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Sets product name.
     *
     * @param productName the product name
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Gets product type.
     *
     * @return the product type
     */
    public String getProductType() {
        return productType;
    }

    /**
     * Sets product type.
     *
     * @param productType the product type
     */
    public void setProductType(String productType) {
        this.productType = productType;
    }

    @Override
    public String toString() {
        return
                productID  +
                producerID +
                productName +
                productType;
    }

    /**
     * To csv string.
     *
     * @return the string
     */
    public String toCSV() {
        return productID + ";" +
                producerID + ";" +
                productName + ";" +
                productType;
    }
}
