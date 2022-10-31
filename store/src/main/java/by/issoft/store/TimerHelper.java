package by.issoft.store;

import java.util.TimerTask;

public class TimerHelper extends TimerTask {

    public void run() {
        Store.getInstance().clear();
    }

}


