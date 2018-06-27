package taskmanager;

import java.util.ArrayList;

public class AccountController {

    public boolean isValidAccount(String username, String password, int isTeacher) {

        DatabaseController dataControl = new DatabaseController();
        dataControl.handleConnect();

        ArrayList usernames = dataControl.getUsernames();
        ArrayList passwords = dataControl.getPasswords();
        ArrayList isTeachers = dataControl.getProfession();

        int usernameMatchIndex = -1;

        for (int i = 0; i < (usernames.size()); i++) {

            if ( ((usernames.get(i)).equals(username)) && (passwords.get(i).equals(password)) && (isTeachers.get(i).equals(isTeacher)) ) {
                usernameMatchIndex = i;
            }
        }

        if (usernameMatchIndex != (-1)) {
            return true;
        } else {
            return false;
        }

    }
}
