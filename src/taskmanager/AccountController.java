package taskmanager;

import java.util.Timer;

public class AccountController {
    public static boolean isValidAccount() {

        Timer timer = new Timer(true);
        InterruptTimer interruptTimer =
                new InterruptTimer(Thread.currentThread());
        timer.schedule(interruptTimer, 2);
        try {
            DatabaseController dataControl = new DatabaseController();
            dataControl.handleConnect();
            throw new InterruptedException();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            timer.cancel();
            return true;
        }
    }
}
