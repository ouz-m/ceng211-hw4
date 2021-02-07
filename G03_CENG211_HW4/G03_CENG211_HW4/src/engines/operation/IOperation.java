package engines.operation;

import engines.view.CustomerComplaintAppView;
import models.user.User;
import services.database.complaint_table.ComplaintTable;
import services.database.product_table.ProductTable;
import services.database.user_table.UserTable;
import services.fileio.FileIO;

import java.util.Scanner;


public interface IOperation {
    /**
     * Operate.
     * Implements necessary procedures to run the simulation according to type of user
     * @param view           the view
     * @param user           the user
     * @param fileIO         the file io
     * @param userTable      the user table
     * @param complaintTable the complaint table
     * @param productTable   the product table
     * @param sc             the sc
     */
    void operate(CustomerComplaintAppView view, User user, FileIO fileIO, UserTable userTable, ComplaintTable complaintTable, ProductTable productTable, Scanner sc);
}
