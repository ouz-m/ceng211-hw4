package engines.operation;

import engines.view.CustomerComplaintAppView;
import exceptions.InvalidOptionException;
import models.Complaint;
import models.Product;
import models.user.Producer;
import models.user.User;
import services.database.complaint_table.ComplaintTable;
import services.database.product_table.ProductTable;
import services.database.user_table.UserTable;
import services.fileio.FileIO;
import shared.Constants;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Scanner;
/**
 * Simulation class for Customer user
 *
 * @author 270201036 & 270201089
 * @author Oguzhan MERTTURK & Harun Eren MUTLU
 * @version HW4
 */
public class CustomerAppOperation implements IOperation {
    /**
     * Simulation method for Customer user
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
        // Simulation loop for customer
        label:
        while (true) {
            LinkedList<Complaint> complaintList;
            view.showMenuCustomer();
            String selection = sc.nextLine();

            switch (selection) {
                case "exit" -> {
                    view.goodBye();
                    break label;
                }
                case "1" -> {//add complaint
                    addComplaint(sc, userTable, productTable, complaintTable, view, user);
                    fileIO.updateComplaintTableCSV(complaintTable);
                }
                case "2" -> {// see previous complaints
                    do{
                        fileIO.updateComplaintTableCSV(complaintTable);
                        complaintList = complaintTable.getAllComplaintsForCustomer(user.getUserID());
                        view.showComplaints(complaintList, userTable, productTable);
                        if (complaintList.size() == 0) break;
                    }
                    while (!seeAndEditComplaints(sc,userTable,productTable,complaintTable,view,user,complaintList, fileIO));
                    fileIO.updateComplaintTableCSV(complaintTable);
                }
                default -> {// wrong entry
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
     * Helper method to add complaints.
     * @param sc
     * @param userTable
     * @param productTable
     * @param complaintTable
     * @param view
     * @param user
     */
    // Option 1
    private void addComplaint(Scanner sc, UserTable userTable, ProductTable productTable, ComplaintTable complaintTable, CustomerComplaintAppView view, User user){
        Producer selectedProducer = getProducer(sc, userTable, view);
        String selectedProductType = getProductType(selectedProducer, sc, productTable, view);
        Product product = getProduct(selectedProducer, sc, productTable, view, selectedProductType);
        String complaintTitle = getComplaintTitle(sc, view);
        String complaintContent = getComplaintContent(sc, view);

        // Add complaint object to database
        complaintTable.addComplaint(user.getUserID(), selectedProducer.getUserID(), product.getProductID(), complaintTitle, complaintContent, Constants.STATUS.NEW);
    }

    /**
     * Helper method to see complaints and edit them if intended. It returns whether should it be called again or not.
     * @param sc
     * @param userTable
     * @param productTable
     * @param complaintTable
     * @param view
     * @param user
     * @param complaintList
     * @param fileIO
     * @return boolean
     */
    // Option 2
    private boolean seeAndEditComplaints(Scanner sc, UserTable userTable,
                               ProductTable productTable, ComplaintTable complaintTable,
                               CustomerComplaintAppView view, User user, LinkedList<Complaint> complaintList, FileIO fileIO){
        view.askUserBackAndComplaints();
        String selection = sc.nextLine();
        switch (selection) {
            case "back":
                fileIO.updateComplaintTableCSV(complaintTable);
                return true;
            case "complaints":
                return false;
            default:

                Complaint selectedComplaint = getComplaint(complaintList, selection, view);
                // If there will be a mistaken entry customer need to fix it
                while (selectedComplaint == null) selectedComplaint = getComplaint(complaintList, sc.nextLine(), view);

                while (true) {
                    view.seeComplaintContent(selectedComplaint);
                    // if complaint's status is not FIXED give option to user to delete it
                    if (!selectedComplaint.getStatus().equals(Constants.STATUS.FIXED)) {
                        view.askUserBackOrDelete();
                        selection = sc.nextLine();
                        if (selection.equals("back")) break;
                        else if (selection.equals("delete")) {
                            selectedComplaint.setStatus("deleted");
                            fileIO.updateComplaintTableCSV(complaintTable);
                            view.deleteComplaint();
                            break;
                        } else view.notValidOption();
                        continue;
                    }
                    view.askUserBack();
                    selection = sc.nextLine();
                    if (selection.equals("back")) break;
                    else view.notValidOption();
                }
                break;
        }
        return false;
    }

    /**
     * Helper method to get complaint.
     * @param complaintList
     * @param selection
     * @param view
     * @return Complaint
     */
    private Complaint getComplaint(LinkedList<Complaint> complaintList, String selection, CustomerComplaintAppView view){
        // Input verify operation
        try {
            Complaint selectedComplaint = null;
            int selectedIndex = Integer.parseInt(selection) - 1;
            if ((selectedIndex < complaintList.size()) && (selectedIndex >= 0)) {
                selectedComplaint = complaintList.get(selectedIndex);
            } else throw new InvalidOptionException(selection);
            return selectedComplaint;
        } catch (NumberFormatException e){
            view.notValidOption();
            return null;
        }
        catch (InvalidOptionException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Helper method for customer to enter content of complaint
     * @param sc
     * @param view
     * @return String
     */
    private String getComplaintContent(Scanner sc, CustomerComplaintAppView view) {
        // Add a complaint
        view.enterComplaintContent();
        String complaintContent = sc.nextLine();
        return complaintContent;
    }

    /**
     * Helper method for customer to enter complaint title.
     * @param sc
     * @param view
     * @return String
     */
    private String getComplaintTitle(Scanner sc, CustomerComplaintAppView view) {
        // Add a complaint title
        view.enterComplaintTitle();
        String complaintTitle = sc.nextLine();
        return complaintTitle;
    }

    /**
     * Helper method for customer to select a product
     * @param user
     * @param sc
     * @param productTable
     * @param view
     * @param selectedProductType
     * @return Product
     */
    private Product getProduct(User user, Scanner sc, ProductTable productTable, CustomerComplaintAppView view, String selectedProductType) {
        // Choose a product
        LinkedList<Product> productList = productTable.getProductListForSpecifiedProductTypeForProducer((Producer) user,selectedProductType);
        view.seeProducts(productList);
        while (true) try {
            int selection = Integer.parseInt(sc.nextLine()); // Possible exception
            if ((selection - 1 < productList.size()) && (selection > 0)) {
                Product product = productList.get(selection-1);
                return product;
            }
            else throw new InvalidOptionException(String.valueOf(selection));
        } catch (NumberFormatException e) {
            view.notValidOption();
            continue;
        } catch (InvalidOptionException e){
            System.out.println(e.getMessage());
            continue;
        }


    }

    /**
     * Helper method to get product type from the customer.
     * @param user
     * @param sc
     * @param productTable
     * @param view
     * @return String
     */
    private String getProductType(User user, Scanner sc, ProductTable productTable, CustomerComplaintAppView view) {
        // Choose product type
        LinkedHashSet<String> productTypes = productTable.getProductTypeListForSpecifiedProducer((Producer) user);
        view.seeProductTypes(productTypes);
        String selectedProductType = sc.nextLine();
        // If entry of customer is not a created type
        while (!productTypes.contains(selectedProductType)){
            view.notValidType(selectedProductType);
            selectedProductType = sc.nextLine();
        }
        return selectedProductType;
    }

    /**
     * Helper method for customer to select producer
     * @param sc
     * @param userTable
     * @param view
     * @return Producer
     */
    private Producer getProducer(Scanner sc, UserTable userTable, CustomerComplaintAppView view) {
        // Selecting producer
        LinkedList<Producer> producers = userTable.getProducerList();
        view.seeProducerList(producers);
        while (true) try {
            int selection = Integer.parseInt(sc.nextLine()); // Possible exception
            if ((selection - 1 < producers.size()) && (selection > 0)) {
                return producers.get(selection - 1);
            }else throw new InvalidOptionException(String.valueOf(selection));
        } catch (NumberFormatException e) {
            view.notValidOption();
            continue;
        } catch (InvalidOptionException e){
            System.out.println(e.getMessage());
            continue;
        }
    }


}

