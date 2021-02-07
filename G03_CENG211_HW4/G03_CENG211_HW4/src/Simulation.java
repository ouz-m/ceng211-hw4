import engines.operation.AdminAppOperation;
import engines.operation.CustomerAppOperation;
import engines.operation.ProducerAppOperation;
import engines.view.CustomerComplaintAppView;
import models.user.User;
import services.authentication.Authentication;
import services.authentication.AuthenticationView;
import services.database.complaint_table.ComplaintTable;
import services.database.product_table.ProductTable;
import services.database.user_table.UserTable;
import services.fileio.FileIO;
import shared.Constants;

import java.util.Scanner;


/**
 * Simulation class
 *
 * @author 270201036 & 270201089
 * @author Oguzhan MERTTURK & Harun Eren MUTLU
 * @version HW4
 */
public class Simulation {

    /**
     * Run.
     *
     * @param fileIO the file io
     */
    public void run(FileIO fileIO){
        // Scanner
        Scanner sc = new Scanner(System.in);

        // Authentication service.
        AuthenticationView authView = new AuthenticationView();
        Authentication auth = new Authentication(sc, authView);

        // Database objects.
        UserTable userTable = new UserTable(fileIO.readCSV(Constants.USER_TABLE));
        ComplaintTable complaintTable = new ComplaintTable(fileIO.readCSV(Constants.COMPLAINT_TABLE));
        ProductTable productTable = new ProductTable(fileIO.readCSV(Constants.PRODUCT_TABLE));

        // View engine.
        CustomerComplaintAppView view = new CustomerComplaintAppView();

        //Initializing user with authentication.
        User currentUser = auth.authenticateUser(userTable);
        fileIO.updateUserTableCSV(userTable);

        // Calling specified user simulation for user's type
        switch (currentUser.getType()){
            case "Admin":
                AdminAppOperation admin = new AdminAppOperation();
                admin.operate(view, currentUser, fileIO, userTable, complaintTable, productTable, sc);
                break;
            case "Producer":
                ProducerAppOperation producer = new ProducerAppOperation();
                producer.operate(view, currentUser, fileIO, userTable, complaintTable, productTable, sc);
                break;
            case "Customer":
                CustomerAppOperation customer = new CustomerAppOperation();
                customer.operate(view, currentUser, fileIO, userTable, complaintTable, productTable, sc);
                break;
        }
        sc.close();
        fileIO.updateComplaintTableCSV(complaintTable);
        fileIO.updateProductTableCSV(productTable);
        fileIO.updateComplaintTableCSV(complaintTable);
    }
}