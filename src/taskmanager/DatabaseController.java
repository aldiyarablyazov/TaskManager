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
    private ArrayList userIDs = new ArrayList();


    @FXML
    public void handleConnect() {

        String user = "alexa";
        String password = "Alex2018";
        String url = "jdbc:mysql://remote-mysql3.servage.net:3306/alexa";
        String query = "SELECT * FROM alexa.Accounts ";

        try {

            System.out.println();

            Connection dbConn = DriverManager.getConnection(url, user, password);
            Statement statement = dbConn.createStatement();
            ResultSet dbResults = statement.executeQuery(query);
            ResultSetMetaData dbMetadata = dbResults.getMetaData();
            int columns = dbMetadata.getColumnCount();

            //for (int i = 1; i <= columns; i++)
              //  System.out.print(dbMetadata.getColumnName(i) + " ");
            //System.out.println();

            while (dbResults.next()) {
                for (int i = 1; i <= columns; i++) {
                    Object value = dbResults.getObject(i);
                    System.out.print(dbResults.getObject(i) + " ");
                    switch (i) {
                        case 1:

                            userIDs.add(value);
                            break;
                        case 2:

                            usernames.add(value);
                            break;
                        case 3:

                            passwords.add(value);
                            break;
                        case 4:

                            professions.add(value);
                            break;
                    }
                }

                System.out.println();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
        }

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


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}