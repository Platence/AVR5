package animation_elements;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;

public class WaitingAlarm extends Task<Void> {

    private volatile Alert alert;
    private volatile int count=0;


    public WaitingAlarm() {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("AVR5 say :");
        alert.setContentText("");
        alert.getDialogPane().setContentText("Подождите, сверяю данные...");
        alert.show();
    }

    @Override
    protected Void call() throws Exception {

        System.out.println("Create new Alert");
        while (true){
            if(alert==null){break;}
            if(!alert.isShowing()){break;}
            Thread.sleep(300);
        }
        return null;
    }

    public void closeAlert(){
        this.alert.hide();
        alert.close();
        alert = null;
    }

    public void setCountPercent(int x){
        this.count = x;
    }
}
