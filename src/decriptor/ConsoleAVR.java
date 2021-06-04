package decriptor;

import com.sun.istack.internal.NotNull;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Orientation;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.LinkedList;

public class ConsoleAVR extends Task<Void> {

    private FlowPane flowPane;
    private static final int maxSizeMes = 15;
    private volatile static LinkedList<String> messages = new LinkedList<>();
    public static boolean alive = true;
    private static volatile boolean needRestart = true;

    public ConsoleAVR(FlowPane flowPane) {
        this.flowPane = flowPane;

        for(int i = 0 ; i< maxSizeMes ; i ++){
            messages.add("--");
        }
    }
    @NotNull
    public static void printlnn(String s){
        if(messages.size()>maxSizeMes){
            messages.remove(0);
        }
        messages.addLast(s+System.lineSeparator());
        needRestart = true;
    }

    @Override
    protected Void call() throws Exception {

        while (alive) {
            Thread.sleep(2000);
            System.out.println("WORKING ANOTHER CONSOLE....");
            if(!needRestart){continue;}

            try {
                Platform.runLater(() -> {
                    this.flowPane.getChildren().clear();
                });
            }
            catch (Exception e){
                e.printStackTrace();
            }

            for (int i = messages.size() - 1; i > 0; i--) {
                Text t = new Text(messages.get(i));
                t.setOpacity(20);
                t.setFill(Color.LIGHTGREEN);
                Platform.runLater(() -> {
                    this.flowPane.getChildren().add(t);
                });
                Platform.runLater(()->{
                    this.flowPane.setOrientation(Orientation.VERTICAL);
                });
                needRestart = false;
            }
        }

        return null;
    }

    public FlowPane getFlowPane() {
        return flowPane;
    }

    public void setFlowPane(FlowPane flowPane) {
        this.flowPane = flowPane;
    }
}
