package services.fileio;

import services.database.complaint_table.ComplaintTable;
import services.database.product_table.ProductTable;
import services.database.user_table.UserTable;
import shared.Constants;

import java.io.*;
import java.util.*;

/**
 * FileIO service class
 *
 * @author 270201036 & 270201089
 * @author Oguzhan MERTTURK & Harun Eren MUTLU
 * @version HW4
 */
public class FileIO implements  IFileIO{
    /**
     *Reads CSV files.
     */
    @Override
    public ArrayList<ArrayList<String>> readCSV(String path){
        String line;
        ArrayList<ArrayList<String>> dataList = new ArrayList<>();

        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);

            while ((line = br.readLine()) != null) {
                ArrayList<String> data = new ArrayList<String>();
                StringTokenizer st = new StringTokenizer(line, ";");

                while (st.hasMoreElements()) {
                    data.add(st.nextToken().trim());
                }
                dataList.add(data);
            }
            fr.close();br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return dataList;
    }

    /**
     *Updates CSV file according to given array of Strings (rows).
     */
    @Override
    public void updateCSV(String path, String[] data) {
        try{
            FileWriter fw = new FileWriter(path,false);
            BufferedWriter br = new BufferedWriter(fw);
            for (String line:data) {
                line = line + "\n";
                br.write(line);
            }
            br.close(); fw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUserTableCSV(UserTable userTable){
        updateCSV(Constants.USER_TABLE, userTable.toCSVArray());
    }
    @Override
    public void updateComplaintTableCSV(ComplaintTable complaintTable){
        updateCSV(Constants.COMPLAINT_TABLE, complaintTable.toCSVArray());
    }
    @Override
    public void updateProductTableCSV(ProductTable productTable){
        updateCSV(Constants.PRODUCT_TABLE, productTable.toCSVArray());
    }
}