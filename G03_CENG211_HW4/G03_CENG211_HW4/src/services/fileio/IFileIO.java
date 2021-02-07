package services.fileio;

import services.database.complaint_table.ComplaintTable;
import services.database.product_table.ProductTable;
import services.database.user_table.UserTable;

import java.util.ArrayList;

/**
 * This interface lists the required methods for FileIO.
 *
 * @author 270201036 & 270201089
 * @author Oguzhan MERTTURK & Harun Eren MUTLU
 * @version HW4
 */
public interface IFileIO {

    /**
     * Read csv array list.
     * Get data from specified csv file
     * @param path the path
     * @return the array list
     */
    ArrayList<ArrayList<String>> readCSV(String path);

    /**
     * Update csv.
     * Update data in the specified csv file according to given data
     * @param path the path
     * @param data the data
     */
    void updateCSV(String path, String[] data);

    /**
     * Update user table csv.
     *
     * @param userTable the user table
     */
    void updateUserTableCSV(UserTable userTable);

    /**
     * Update complaint table csv.
     *
     * @param complaintTable the complaint table
     */
    void updateComplaintTableCSV(ComplaintTable complaintTable);

    /**
     * Update product table csv.
     *
     * @param productTable the product table
     */
    void updateProductTableCSV(ProductTable productTable);
}
