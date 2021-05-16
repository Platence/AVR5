package autoOpen;

import java.awt.*;
import java.io.File;

public class AutoOpen {

    public AutoOpen(String path){

        try {
            File file=new File(path);
            Desktop.getDesktop().open(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
