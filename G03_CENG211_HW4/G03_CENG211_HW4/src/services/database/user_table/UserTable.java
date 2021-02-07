package services.database.user_table;

import models.user.Admin;
import models.user.Customer;
import models.user.Producer;
import models.user.User;

import java.util.*;

/**
 * User table service class
 *
 * @author 270201036 & 270201089
 * @author Oguzhan MERTTURK & Harun Eren MUTLU
 * @version HW4
 */
public class UserTable implements IUserTable {

    private LinkedList<User> userList;

    /**
     * Instantiates a new User table.
     *
     * @param dataList the data list
     */
    public UserTable(ArrayList<ArrayList<String>> dataList) {
        LinkedList userDataList = new LinkedList<>();
        dataList.forEach(data -> {
            switch (data.get(4)) {
                case "admin" -> userDataList.add(new Admin(data));
                case "customer" -> userDataList.add(new Customer(data));
                case "producer" -> userDataList.add(new Producer(data));
            }
        });
        this.userList = userDataList;
    }
    @Override
    public User provideUser(String userName,String password){
        for (User user: this.userList) {
            if(user.checkUser(userName,password)){
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean checkUser(String userName){
        for (User user: this.userList) {
            if(user.checkUser(userName)){
                return true;
            }
        }
        return false;
    }

    @Override
    public LinkedList<Producer> getProducerList() {
        LinkedList producerList = new LinkedList<>();
        for (User user: userList) {
            if (user.getClass().equals(Producer.class)) producerList.add(user);
        }
        return producerList;
    }

    @Override
    public User createUser(String username, String password, String accountType, String displayedName) {
        if (!checkUser(username)) {
            switch (accountType) {
                case "1":
                    return createCustomer(username, password, displayedName);
                case "2":
                    return createProducer(username, password, displayedName);
            }
        }
        return null;
    }

    private Customer createCustomer(String username, String password, String displayedName) {
        Customer user = new Customer(UUID.randomUUID().toString(), username, password, displayedName);
        userList.add(user);
        return user;
    }

    private Producer createProducer(String username, String password, String displayedName) {
        Producer user = new Producer(UUID.randomUUID().toString(), username, password, displayedName);
        userList.add(user);
        return user;
    }

    @Override
    public String getDisplayedNameOfGivenUser(String userID){
        for (User user: this.userList) {
            if(user.getUserID().equals(userID)){
                return user.getDisplayedName();
            }
        }
        return null;
    }

    @Override
    public String[] toCSVArray() {
        String[] userCSV = new String[userList.size()];
        for (int i = 0; i < userList.size(); i++) {
            userCSV[i] = userList.get(i).toCSV();
        }
        return userCSV;
    }
}