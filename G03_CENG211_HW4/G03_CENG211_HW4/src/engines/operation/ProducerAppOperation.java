package engines.operation;

import engines.view.CustomerComplaintAppView;
import exceptions.InvalidOptionException;
import models.Complaint;
import models.user.User;
import services.database.complaint_table.ComplaintTable;
import services.database.product_table.ProductTable;
import services.database.user_table.UserTable;
import services.fileio.FileIO;
import shared.Constants;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Simulation class for Producer user
 *
 * @author 270201036 & 270201089
 * @author Oguzhan MERTTURK & Harun Eren MUTLU
 * @version HW4
 */
public class ProducerAppOperation implements IOperation{

    /**
     * Simulation method for Producer user
     * @param view           the view
     * @param user           the user
     * @param fileIO         the file io
     * @param userTable      the user table
     * @param complaintTable the complaint table
     * @param productTable   the product table
     * @param sc             the sc
     */
    @Override
    public void operate(CustomerComplaintAppView view,
                        User user, FileIO fileIO,
                        UserTable userTable,
                        ComplaintTable complaintTable,
                        ProductTable productTable, Scanner sc) {
        // Simulation loop for producer
        simulation:
        while (true) {
            view.showMenuProducer();
            String selection = sc.nextLine();
            // Updating tables to prevent any data loss from interrupts
            fileIO.updateProductTableCSV(productTable);
            fileIO.updateComplaintTableCSV(complaintTable);
            switch (selection) {
                case "exit" -> { // bye!
                    view.goodBye();
                    break simulation;
                }
                // Product add procedure initializer
                case "1" -> addProduct(productTable, userTable, view, user, sc, fileIO);
                // Complaint edit/see procedure
                case "2" -> seeAndEditComplaint(complaintTable, userTable, productTable, user, sc, view, fileIO);
                // Process invalid input
                default ->  {
                    try {
                        throw new InvalidOptionException(selection);
                    } catch (InvalidOptionException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                }
            }
        }
    }

    /**
     * Adds a product to database with input from user
     * @param productTable
     * @param userTable
     * @param view
     * @param user
     * @param sc
     * @param fileIO
     */
    private void addProduct(ProductTable productTable, UserTable userTable, CustomerComplaintAppView view, User user, Scanner sc, FileIO fileIO){

        LinkedList<String> typeList = productTable.getProductTypeList();
        view.seeProductTypesForProducer(typeList);
        // Taking input as lowercase to prevent interruptions
        // Product Type
        String selectedProductType = sc.nextLine().toLowerCase();
        // Product Name
        view.addProduct();
        String selectedProductName = sc.nextLine();
        // ProducerID
        String producerID = user.getUserID();
        productTable.addProduct(selectedProductName,selectedProductType,producerID);
        view.addedSuccessfully("Product");
        fileIO.updateProductTableCSV(productTable);
    }

    /**
     * Helper method that provides producer to see complaints related to them and edit the complaints.
     * @param complaintTable
     * @param userTable
     * @param productTable
     * @param user
     * @param sc
     * @param view
     * @param fileIO
     */
    private void seeAndEditComplaint(ComplaintTable complaintTable, UserTable userTable, ProductTable productTable, User user, Scanner sc, CustomerComplaintAppView view, FileIO fileIO){
        LinkedList<Complaint> complaintList = complaintTable.getProducerComplaints(user.getUserID());
        // If there is no complaint, go back
        if (complaintList.size() == 0) {
            view.thereIsNoComplaint();
            return;
        }
        view.showComplaints(complaintList,userTable,productTable);
        // Enter edit loop
        edit:
        while (true){
            complaintList = complaintTable.getProducerComplaints(user.getUserID());
            view.askUserBackAndComplaints();
            String selection = sc.nextLine();
            switch (selection) {
                case "back":
                    break edit;
                case "complaints":
                    view.showComplaints(complaintList, userTable, productTable);
                    break;
                default:
                    processEditSelection(complaintTable, user, sc, view, fileIO, complaintList, selection);
                    break;
            }
        }
    }

    /**
     * Processor method for editing operation (helper method to see switch statement clearly)
     * @param complaintTable
     * @param user
     * @param sc
     * @param view
     * @param fileIO
     * @param complaintList
     * @param selection
     */
    private void processEditSelection(ComplaintTable complaintTable, User user, Scanner sc, CustomerComplaintAppView view, FileIO fileIO, LinkedList<Complaint> complaintList, String selection) {
        try {
            // Checks if the selected index is in the list
            if ((Integer.parseInt(selection) - 1 < complaintList.size()) && (Integer.parseInt(selection) > 0)) {
                Complaint selectedComplaint = complaintList.get(Integer.parseInt(selection) - 1);
                checkStatus(selectedComplaint);
                view.seeComplaintContent(selectedComplaint);
                editComplaint(selectedComplaint, sc, view, user);
                fileIO.updateComplaintTableCSV(complaintTable);
            }
            else throw new InvalidOptionException(selection);
        } catch (NumberFormatException e){
            view.notValidOption();
            return;
        } catch (InvalidOptionException e){
            System.out.println(e.getMessage());
            return;
        }
    }

    /**
     * Helper method to convert current Status to seen from new.
     * @param selectedComplaint
     */
    private void checkStatus(Complaint selectedComplaint) {
        if (selectedComplaint.getStatus().equals(Constants.STATUS.NEW)) selectedComplaint.setStatus("seen");
    }

    /**
     * Helper method to edit a complaint
     * @param complaint
     * @param sc
     * @param view
     * @param user
     */
    private void editComplaint(Complaint complaint, Scanner sc, CustomerComplaintAppView view, User user){
        while (true){
            // It will print status list for producer
            view.showStatusList(user);
            String selection = sc.nextLine().toLowerCase();
            if (selection.equals("back")) return; // It entry is back it will end editing
            try {// The user is not perfect so the entry may not be a Status
                if (!Constants.isValidEnum(selection)){
                    throw new InvalidOptionException(selection);
                }
            } catch (InvalidOptionException e) {
                    System.out.println(e.getMessage());
                    continue;
            }

            complaint.setStatus(selection);
            view.statusChangeSuccessful(complaint.getStatus());
        }
    }
}
