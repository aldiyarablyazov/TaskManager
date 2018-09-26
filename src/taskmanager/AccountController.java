package taskmanager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccountController {

    public boolean isValidAccount(String username, String password, int isTeacher) throws SQLException,IOException {

        DatabaseController databaseController = new DatabaseController();
        databaseController.handleConnect();

        ArrayList usernames = databaseController.getUsernames();
        ArrayList passwords = databaseController.getPasswords();
        ArrayList isTeachers = databaseController.getProfession();

        int usernameMatchIndex = -1;

        for (int i = 0; i < (usernames.size()); i++) {

            if ( ((usernames.get(i)).equals(username)) && (passwords.get(i).equals(password)) && (isTeachers.get(i).equals(isTeacher)) ) {
                usernameMatchIndex = i;
            }
        }

        if (usernameMatchIndex != (-1)) {
            return true;
        } else {
            return false;
        }

    }
}