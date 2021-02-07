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

/**
 * View engine class for Simulation
 *
 * @author 270201036 & 270201089
 * @author Oguzhan MERTTURK & Harun Eren MUTLU
 * @version HW4
 */
public class CustomerComplaintAppView implements ICustomerComplaintAppView{
    @Override
    public void showComplaints(LinkedList<Complaint> complaintList, UserTable userTable, ProductTable productTable){
        if (complaintList.size() == 0) {
            thereIsNoComplaint();
            return;
        }
        System.out.println("Complaints:");
        complaintList.forEach(complaint -> {
            System.out.print(String.format("%d- ",complaintList.indexOf(complaint)+1));
            seeComplaintWithoutComplaintInfo(complaint, userTable.getDisplayedNameOfGivenUser(complaint.getCustomerID()),
                                                        userTable.getDisplayedNameOfGivenUser(complaint.getProducerID()),
                                                        productTable.getProductNameOfGivenProduct(complaint.getProductID()));
        });
        System.out.println("Please choose a complaint from the complaint list: ");
    }
    @Override
    public void seeComplaintWithoutComplaintInfo(Complaint complaint, String customerName, String producerName, String productName){
        System.out.println(String.format(complaint.toString(),customerName,producerName,productName));
    }
    @Override
    public void seeComplaintContent(Complaint complaint){
        System.out.println("    TITLE: " + complaint.getComplaintTitle() + "  STATUS: " + complaint.getStatus().toString());
        System.out.println("        CONTENT: \n         " + complaint.getComplaint());}
    @Override
    public void seeProducerList(LinkedList<Producer> producers){
        System.out.println("Producers:");
        producers.forEach(producer -> { System.out.println(String.format("%d- %s",
                producers.indexOf(producer)+1,
                producer.getDisplayedName()));
        });
        System.out.println("Please choose a producer from the producer list: ");
    }
    @Override
    public void seeProductTypes(Collection<String> productTypesList){
        System.out.println("Product types:");
        productTypesList.forEach(type -> { System.out.println(String.format("-%s", type));
        });
        System.out.println("Please enter a type from the type list (e.g. 'knitting'): ");
    }
    @Override
    public void seeProductTypesForProducer(LinkedList<String> productTypesList){
        seeProductTypes(productTypesList);
        System.out.println("NOTE: Please enter new product type (e.g. 'knitting') if none of the above is your product type.");
    }
    @Override
    public void seeProducts(LinkedList<Product> productList){
        System.out.println("Product List:");
        productList.forEach(product -> { System.out.println(String.format("%d- %s",
                productList.indexOf(product)+1,
                product.getProductName()));
        });
        System.out.println("Please choose a product from the product list: ");
    }
    @Override
    public void showMenuCustomer(){
        System.out.println("[1] to add a complaint");
        System.out.println("[2] to see your previous complaint");
        System.out.println("'exit' to log out");
        System.out.println("Please enter an option: ");
    }
    @Override
    public void showMenuProducer(){
        System.out.println("[1] to add a product");
        System.out.println("[2] to see the complaints that related to you");
        System.out.println("'exit' to log out");
        System.out.println("Please enter an option: ");
    }
    @Override
    public void showMenuAdmin(){
        System.out.println("Select the complaint that you want to view/edit or enter 'exit' to exit the program: ");
        System.out.println("Enter 'complaints' to see the complaints again."); //complaints
    }
    @Override
    public void showStatusList(User user){
        System.out.println("Enter the new status (i.e. 'fixed') or 'back' to go back:");
        switch (user.getType()){
            case "Admin":
                System.out.println("    Available statuses");
                for (Constants.STATUS status: Constants.STATUS.values()) {
                    System.out.println(String.format("     -%s", status.toString()));
                }
            case "Producer":
                for (Constants.STATUS status: Constants.STATUS.values()) {
                    if (status.equals(Constants.STATUS.FIXED) || status.equals(Constants.STATUS.WORKING))
                        System.out.println(String.format("     -%s", status.toString()));
                }
        }
    }
    @Override
    public void statusChangeSuccessful(Constants.STATUS status){
        System.out.println(String.format("Status successfully to %s.",status.toString()));
    }
    @Override
    public void enterComplaintTitle(){
        System.out.println("Please write the title of the complaint: ");
    }
    @Override
    public void enterComplaintContent() {
        System.out.println("Please write the content of the complaint: ");
    }
    @Override
    public void askUserBackOrDelete(){ System.out.println("Please enter 'back' to go back or enter 'delete' to delete your complaint: ");}
    @Override
    public void askUserBack(){
        System.out.println("Please enter 'back' to go back: ");
    }
    @Override
    public void thereIsNoComplaint(){
        System.out.println("There is no complaint to see.");
    }
    @Override
    public void addProduct(){
        System.out.println("Please enter product name: ");
    }
    @Override
    public void addedSuccessfully(String string){ System.out.println(String.format("%s added successfully.",string)); }
    @Override
    public void deleteComplaint(){ System.out.println("Complaint is deleted.");}
    @Override
    public void goodBye(){ System.out.println("Good bye!"); }
    @Override
    public void notValidOption(){
        System.out.println("Please enter a valid option");
    }
    @Override
    public void notValidType(String selectedProductType){
        System.out.println(String.format("%s is not a valid type, please enter a valid type:",selectedProductType)); }
    @Override
    public void askUserBackAndComplaints() {System.out.println("Please enter 'complaints' to see complaints again or 'back' to go back to menu");}
}
