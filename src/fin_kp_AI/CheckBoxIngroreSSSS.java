package fin_kp_AI;

import javafx.scene.control.CheckBox;

public class CheckBoxIngroreSSSS {

    private static CheckBox ignored;
    private static CheckBox s9999;
    private static boolean currentBox;
    private static boolean s9999CMON;

    public CheckBoxIngroreSSSS(CheckBox ignored,CheckBox s9999) {
       if(CheckBoxIngroreSSSS.ignored!=null){return;}
       else {CheckBoxIngroreSSSS.ignored = ignored;}
       CheckBoxIngroreSSSS.s9999 = s9999;
       s9999.setSelected(false);
       currentBox = CheckBoxIngroreSSSS.ignored.isSelected();
       ignored.setOnAction(e->setOnCreated());
       s9999.setOnAction(e->changeS999Form());
    }

    public static void setBoxSelected(){
        currentBox = CheckBoxIngroreSSSS.ignored.isSelected();
        s9999CMON = s9999.isSelected();
    }

    public static boolean getS999Status(){
        return s9999CMON;
    }

    public static boolean isCurrentBox() {
        return currentBox;
    }
    private void setOnCreated(){
        setBoxSelected();
        System.out.println("Изменение причины выбора в акте КПФ на " + currentBox);
    }

    private void changeS999Form(){
        System.out.println("S9999 Mode changed!");
        s9999CMON = s9999.isSelected();
    }
}
