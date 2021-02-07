package models;

import shared.Constants;

import java.util.List;
import java.util.UUID;

/**
 * Complaint class
 *
 * @author 270201036 & 270201089
 * @author Oguzhan MERTTURK & Harun Eren MUTLU
 * @version HW4
 */
public class Complaint {
    private String complaintID;
    private String customerID;
    private String producerID;
    private String productID;
    private String complaintTitle;
    private String complaint;
    private Constants.STATUS status;


    /**
     * Instantiates a new Complaint.
     *
     * @param complaintID    the complaint id
     * @param customerID     the customer id
     * @param producerID     the producer id
     * @param productID      the product id
     * @param complaintTitle the complaint title
     * @param complaint      the complaint
     * @param status         the status
     */
    public Complaint(String complaintID,
                     String customerID,
                     String producerID,
                     String productID,
                     String complaintTitle,
                     String complaint,
                     String status) {

        this.complaintID = complaintID;
        this.customerID = customerID;
        this.producerID = producerID;
        this.productID = productID;
        this.complaintTitle = complaintTitle;
        this.complaint = complaint;
        this.status = resolveStatus(status);
    }

    /**
     * Instantiates a new Complaint.
     *
     * @param customerID     the customer id
     * @param producerID     the producer id
     * @param productID      the product id
     * @param complaintTitle the complaint title
     * @param complaint      the complaint
     * @param status         the status
     */
    public Complaint(String customerID,
                     String producerID,
                     String productID,
                     String complaintTitle,
                     String complaint,
                     String status) {
        this(UUID.randomUUID().toString(), customerID, producerID, productID, complaintTitle, complaint, status);
    }

    /**
     * Instantiates a new Complaint.
     *
     * @param data the data
     */
    public Complaint(List<String> data) {
        this(data.get(0), data.get(1), data.get(2), data.get(3),
                data.get(4),data.get(5),data.get(6));
    }

    /**
     * Resolve status constants . status.
     *
     * @param status the status
     * @return the constants . status
     */
    public Constants.STATUS resolveStatus(String status){
        return switch (status.toLowerCase()) {
            case "new" -> Constants.STATUS.NEW;
            case "working" -> Constants.STATUS.WORKING;
            case "fixed" -> Constants.STATUS.FIXED;
            case "deleted" -> Constants.STATUS.DELETED;
            case "seen" -> Constants.STATUS.SEEN;
            default -> throw new IllegalStateException("Unexpected value: " + status);
        };

    }

    /**
     * Gets complaint id.
     *
     * @return the complaint id
     */
    public String getComplaintID() {
        return complaintID;
    }

    /**
     * Sets complaint id.
     *
     * @param complaintID the complaint id
     */
    public void setComplaintID(String complaintID) {
        this.complaintID = complaintID;
    }

    /**
     * Gets customer id.
     *
     * @return the customer id
     */
    public String getCustomerID() {
        return customerID;
    }

    /**
     * Sets customer id.
     *
     * @param customerID the customer id
     */
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
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
     * Gets complaint title.
     *
     * @return the complaint title
     */
    public String getComplaintTitle() {
        return complaintTitle;
    }

    /**
     * Sets complaint title.
     *
     * @param complaintTitle the complaint title
     */
    public void setComplaintTitle(String complaintTitle) {
        this.complaintTitle = complaintTitle;
    }

    /**
     * Gets complaint.
     *
     * @return the complaint
     */
    public String getComplaint() {
        return complaint;
    }

    /**
     * Sets complaint.
     *
     * @param complaint the complaint
     */
    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public Constants.STATUS getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(Constants.STATUS status) {
        this.status = status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(String status) {
        this.status = resolveStatus(status);
    }

    /**
     * To csv string.
     *
     * @return the string
     */
    public String toCSV(){
        return complaintID + ";" +
                customerID + ";" +
               producerID + ";" +
                productID + ";" +
                complaintTitle + ";" +
                complaint + ";" +
                status.toString().toLowerCase();
    }

    public String toString(){
        return complaintID + "," +
                "%s" + "," +
                "%s" + "," +
                "%s" + "," +
                complaintTitle + "," +
                status.toString().toLowerCase();

    }
}

