package error_package.SlideError;

import javafx.application.Platform;
import javafx.concurrent.Task;
import sample.Controller;

public class SlideLiveTimer extends Task<Void> {

    public static volatile int counts;


    @Override
    protected Void call() throws Exception {

        counts = 0;
        while (counts<100){
            Thread.sleep(125);
            counts++;
        }


        SlideModalError.hideStatus.play();
        Thread.sleep(1500);
        SlideModalError.showsStatus = false;


        return null;
    }
}
