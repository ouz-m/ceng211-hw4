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
 * Simulation class for admin user
 *
 * @author 270201036 & 270201089
 * @author Oguzhan MERTTURK & Harun Eren MUTLU
 * @version HW4
 */
public class AdminAppOperation implements IOperation {

    /**
     *  Simulation method for Admin user
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
                        ProductTable productTable, Scanner sc){



        LinkedList<Complaint> complaintList = complaintTable.getAllComplaints();
        view.showComplaints(complaintList, userTable, productTable);
        // Simulation loop for admin
        simulation:
        while (true) {
            // Updating table to prevent any data loss from interrupts
            fileIO.updateComplaintTableCSV(complaintTable);
            complaintList = complaintTable.getAllComplaints();
            view.showMenuAdmin();
            String selection = sc.nextLine();
            switch (selection) {
                // bye!
                case "exit" -> {
                    view.goodBye();
                    break simulation;
                }
                // review complaint table
                case "complaints" -> {
                    view.showComplaints(complaintList, userTable, productTable);
                    continue;
                }
                // start processing the selection
                default -> {
                    processSelection(view, user, fileIO, complaintTable, sc, complaintList, selection);
                    continue;
                }
            }
        }
    }

    /**
     * Implements necessary information for complaint edit/inspect (Helper method to see switch case clearly)
     * @param view
     * @param user
     * @param fileIO
     * @param complaintTable
     * @param sc
     * @param complaintList
     * @param selection
     * @return void
     *
     */
    private void processSelection(CustomerComplaintAppView view, User user, FileIO fileIO, ComplaintTable complaintTable, Scanner sc, LinkedList<Complaint> complaintList, String selection) {
        try {
            // Because we are not assuming a perfect user case, we should check if user inserts a valid value for list index;
            if ((Integer.parseInt(selection) - 1 < complaintList.size()) && (Integer.parseInt(selection) > 0)) {
                Complaint selectedComplaint = complaintList.get(Integer.parseInt(selection) - 1);
                // See and edit complaint until user requests to go back
                do{
                    view.seeComplaintContent(selectedComplaint);}
                while (editComplaint(selectedComplaint, sc, view, user)); // checker
                fileIO.updateComplaintTableCSV(complaintTable); // updater
            } else {
                throw new InvalidOptionException(selection);
            }
            // First exception of parse operation should be caught
        } catch (NumberFormatException e) {
            view.notValidOption();
        } catch (InvalidOptionException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Helper method that edits a complaint and returns whether it should be called again or not.
     * @param selectedComplaint
     * @param sc
     * @param view
     * @param user
     * @return boolean
     */
    private boolean editComplaint(Complaint selectedComplaint, Scanner sc, CustomerComplaintAppView view, User user){
        view.showStatusList(user);
        String selection = sc.nextLine();
        if (selection.equals("back")) return false;  // Break the loop

        try {
            // The input may not be in enum (We accept the user is not perfect)
            if (!Constants.isValidEnum(selection)){
                throw new InvalidOptionException(selection);
            }

        } catch (InvalidOptionException e) {
            System.out.println(e.getMessage());
            return true;  // Continue to loop
        }
        // Complaint status is updated if every input is valid.
        selectedComplaint.setStatus(selection);
        view.statusChangeSuccessful(selectedComplaint.getStatus());
        return true;  // Continue to loop
    }
}
