package services.database.complaint_table;


import models.Complaint;
import services.database.ITable;
import shared.Constants;

import java.util.LinkedList;

/**
 * Complaint table service interface
 *
 * @author 270201036 & 270201089
 * @author Oguzhan MERTTURK & Harun Eren MUTLU
 * @version HW4
 */
public interface IComplaintTable extends ITable {

    /**
     * Add complaint.
     *
     * @param customerID     the customer id
     * @param producerID     the producer id
     * @param productID      the product id
     * @param complaintTitle the complaint title
     * @param complaint      the complaint
     * @param status         the status
     */
    void addComplaint(String customerID,
                      String producerID,
                      String productID,
                      String complaintTitle,
                      String complaint,
                      Constants.STATUS status);

    /**
     * Delete complaint.
     *
     * @param complaintID the complaint id
     */
    void deleteComplaint(String complaintID);

    /**
     * Gets complaint.
     *
     * @param complaintID the complaint id
     * @return the complaint
     */
    Complaint getComplaint(String complaintID);

    /**
     * Gets all complaints.
     *
     * @return the all complaints
     */
    LinkedList<Complaint> getAllComplaints();

    /**
     * Gets all complaints for customer.
     *
     * @param customerID the customer id
     * @return the all complaints for customer
     */
    LinkedList<Complaint> getAllComplaintsForCustomer(String customerID);

    /**
     * Gets product complaints.
     *
     * @param productID the product id
     * @return the product complaints
     */
    LinkedList<Complaint> getProductComplaints(String productID);

    /**
     * Gets producer complaints.
     *
     * @param producerID the producer id
     * @return the producer complaints
     */
    LinkedList<Complaint> getProducerComplaints(String producerID);

    /**
     * Sets status.
     *
     * @param status      the status
     * @param complaintID the complaint id
     */
    void setStatus(Constants.STATUS status, String complaintID);

}
