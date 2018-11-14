package taskmanager;

public class CurrentUser {

    public static String currentUsr;

    public static String getCurrentUser() {
        return currentUsr;
    }

    public static void setCurrentUser(String currentUser) {
        currentUsr = currentUser;
    }
}
