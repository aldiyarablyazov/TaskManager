package taskmanager;

public class AccountController {
    public static boolean isValidAccount() {

        DatabaseController dataControl = new DatabaseController();
        dataControl.handleConnect();
        return true;

    }
}
