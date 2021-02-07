package engines.view;

import models.Complaint;
import models.Product;
import models.user.Producer;
import models.user.User;
import services.database.product_table.ProductTable;
import services.database.user_table.UserTable;
import shared.Constants;

import java.util.Collection;
import java.util.LinkedList;

public interface ICustomerComplaintAppView {

    /**
     * Prints all complaints that is in the given list
     * @param complaintList
     * @param userTable
     * @param productTable
     */
    void showComplaints(LinkedList<Complaint> complaintList, UserTable userTable, ProductTable productTable);

    /**
     * Prints complaint information without content
     * @param complaint
     * @param customerName
     * @param producerName
     * @param productName
     */
    void seeComplaintWithoutComplaintInfo(Complaint complaint, String customerName, String producerName, String productName);

    /**
     * Prints the content of given complaint
     * @param complaint
     */
    void seeComplaintContent(Complaint complaint);

    /**
     * Prints all producers that is in the given list
     * @param producers
     */
    void seeProducerList(LinkedList<Producer> producers);

    /**
     * Prints all types of products that is in the given list
     * @param productTypesList
     */
    void seeProductTypes(Collection<String> productTypesList);

    void seeProductTypesForProducer(LinkedList<String> productTypesList);

    /**
     * Prints all products that is in the given list
     * @param productList
     */
    void seeProducts(LinkedList<Product> productList);

    /**
     * Prints the menu for admin
     */
    void showMenuAdmin();

    /**
     * Prints the menu for customer
     */
    void showMenuCustomer();

    /**
     * Prints the menu for customer
     */
    void showMenuProducer();

    /**
     * Prints the status list according to given user's permission
     * @param user
     */
    void showStatusList(User user);

    /**
     * Prints the successful message
     * @param status
     */
    void statusChangeSuccessful(Constants.STATUS status);

    /**
     * Prints a input text for complaint title
     */
    void enterComplaintTitle();

    /**
     * Prints a input text for complaint content
     */
    void enterComplaintContent();

    /**
     * Prints a input text for back or reprint operation
     */
    void askUserBackOrDelete();

    /**
     * Prints a input text for back operation
     */
    void askUserBack();

    /**
     * Prints no complaint message
     */
    void thereIsNoComplaint();

    /**
     * Prints a input text for product name
     */
    void addProduct();

    /**
     * Prints the given string is added successfully.
     * @param string
     */
    void addedSuccessfully(String string);

    /**
     * Prints complaint is deleted
     */
    void deleteComplaint();

    /**
     * Prints good bye!
     */
    void goodBye();

    /**
     * Prints the option is not valid
     */
    void notValidOption();

    /**
     * Prints the given product type is not valid
     * @param selectedProductType
     */
    void notValidType(String selectedProductType);

    /**
     *  Prints the message that ask user to enter 'back' or 'complaints'
     */
    void askUserBackAndComplaints();
}
