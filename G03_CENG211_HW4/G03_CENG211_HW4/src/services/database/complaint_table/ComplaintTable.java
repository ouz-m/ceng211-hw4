package services.database.complaint_table;

import models.Complaint;
import shared.Constants;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Complaint table service class
 *
 * @author 270201036 & 270201089
 * @author Oguzhan MERTTURK & Harun Eren MUTLU
 * @version HW4
 */
public class ComplaintTable implements IComplaintTable{

    private LinkedList<Complaint> complaintList;

    /**
     * Instantiates a new Complaint table.
     *
     * @param dataList the data list
     */
    public ComplaintTable(ArrayList<ArrayList<String>> dataList) {
        LinkedList<Complaint> complaintDataList = new LinkedList<>();
        for (ArrayList<String> complaintData: dataList) complaintDataList.add(new Complaint(complaintData));
        this.complaintList = complaintDataList;
    }

    @Override
    public void addComplaint(String customerID, String producerID, String productID, String complaintTitle, String complaintDescription, Constants.STATUS status) {
        complaintList.add(new Complaint(customerID, producerID, productID, complaintTitle, complaintDescription, "new"));
    }

    @Override
    public void deleteComplaint(String complaintID) {
        getComplaint(complaintID).setStatus(Constants.STATUS.DELETED);
    }

    @Override
    public Complaint getComplaint(String complaintID) {
        for (Complaint complaint: complaintList) if (complaint.getComplaintID().equals(complaintID)) return complaint;
        return null;
    }

    @Override
    public LinkedList<Complaint> getAllComplaints(){
        return complaintList;
    }

    @Override
    public LinkedList<Complaint> getAllComplaintsForCustomer(String customerID){
        LinkedList<Complaint> newComplaintList = new LinkedList<>();
        complaintList.forEach(complaint -> {
            if (!complaint.getStatus().equals(Constants.STATUS.DELETED) && complaint.getCustomerID().equals(customerID)) {
                newComplaintList.add(complaint);}});
        return newComplaintList;
    }

    @Override
    public LinkedList<Complaint> getProductComplaints(String productID) {
        LinkedList<Complaint> linkedComplaintList = new LinkedList<>();
        complaintList.forEach(
                complaint -> {
                    if (complaint.getProductID().equals(productID)) linkedComplaintList.add(complaint);
        });
        return linkedComplaintList;
    }

    @Override
    public LinkedList<Complaint> getProducerComplaints(String producerID) {
        LinkedList<Complaint> linkedComplaintList = new LinkedList<>();
        complaintList.forEach(
                complaint -> {
                    if (!complaint.getStatus().equals(Constants.STATUS.DELETED) && complaint.getProducerID().equals(producerID)) linkedComplaintList.add(complaint);
        });
        return linkedComplaintList;
    }

    @Override
    public void setStatus(Constants.STATUS status, String complaintID) {
        getComplaint(complaintID).setStatus(status);
    }

    @Override
    public String[] toCSVArray() {
        String[] userCSV = new String[complaintList.size()];
        for (int i = 0; i < complaintList.size(); i++) {
            userCSV[i] = complaintList.get(i).toCSV();
        }
        return userCSV;
    }
}