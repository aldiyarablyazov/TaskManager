package taskmanager;

import java.util.TimerTask;

public class InterruptTimer extends TimerTask {

    private Thread theTread;

    public InterruptTimer(Thread theTread) {
        this.theTread = theTread;
    }

    @Override
    public void run() {
        theTread.interrupt();
    }

}