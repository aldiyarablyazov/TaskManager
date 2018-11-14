/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package taskmanager;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DatabaseController implements Initializable {

    private ArrayList usernames = new ArrayList();
    private ArrayList passwords = new ArrayList();
    private ArrayList professions = new ArrayList();

    private String user = "alexa";
    private String password = "Alex2018";
    private String url = "jdbc:mysql://remote-mysql3.servage.net:3306/alexa";
    private String query = "SELECT * FROM alexa.Accounts ";

    @FXML
    public void handleConnect() throws SQLException {
        Connection dbConn = DriverManager.getConnection(url, user, password);
        Statement statement = dbConn.createStatement();
        ResultSet dbResults = statement.executeQuery(query);
        ResultSetMetaData dbMetadata = dbResults.getMetaData();
        int columns = dbMetadata.getColumnCount();

        while (dbResults.next()) {
            for (int i = 1; i <= columns; i++) {
                Object value = dbResults.getObject(i);
                switch (i) {
                    case 1:
                        usernames.add(value);
                        break;
                    case 2:
                        passwords.add(value);
                        break;

                    case 3:
                        professions.add(value);
                        break;
                }
            }
        }

        dbConn.close();

    }

    public ArrayList getUsernames() {
        return usernames;
    }

    public ArrayList getPasswords() {
        return passwords;
    }

    public ArrayList getProfession() {
        return professions;
    }


    @FXML
    public Boolean handleRegisterConnect(String username, String password, Integer isTeacher) throws SQLException {

        handleConnect();
        ArrayList usernameArray = getUsernames();
        //checks if there is already the same username in the database
        int flag = 0;
        for (int i = 0; i < usernameArray.size();i++) {
            if (username.equals(usernameArray.get(i))) {
                flag = 1;
            }
        }

        //if there is no duplicate username, adds into database and returns username
        if (flag == 0) {
            Connection dbConn = DriverManager.getConnection(this.url, this.user, this.password);
            Statement statement = dbConn.createStatement();

            statement.execute("INSERT INTO alexa.Accounts SET "
                    + "username = '" + username + "', "
                    + "password = '" + password + "', "
                    + "isTeacher = " + isTeacher);
            dbConn.close();
            return false;
        } else {
            return true;
        }
    }


    public Boolean handleSetTask() {
        return true;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}
