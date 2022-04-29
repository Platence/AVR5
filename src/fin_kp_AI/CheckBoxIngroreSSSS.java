package fin_kp_AI;

import javafx.scene.control.CheckBox;

public class CheckBoxIngroreSSSS {

    private static CheckBox ignored;
    private static boolean currentBox;

    public CheckBoxIngroreSSSS(CheckBox ignored) {
       if(CheckBoxIngroreSSSS.ignored!=null){return;}
       else CheckBoxIngroreSSSS.ignored = ignored;
       currentBox = CheckBoxIngroreSSSS.ignored.isSelected();
       ignored.setOnAction(e->setOnCreated());
    }

    public static void setBoxSelected(){
        currentBox = CheckBoxIngroreSSSS.ignored.isSelected();
    }

    public static boolean isCurrentBox() {
        return currentBox;
    }

    private void setOnCreated(){
        setBoxSelected();
        System.out.println("Изменение причины выбора в акте КПФ на " + currentBox);
    }
}
