package sample;


import all_controllers.Rule_contollers_Main;
import error_package.Modal_Error;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.util.converter.IntegerStringConverter;
import sklad_KP_AI.settngsHa.MetaHalfAutoSet;
import warehouse_plu.Ostatku;


import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControllerSkladHA {

    /*
          Динамический класс с таблицей
          УИ3, кф, кол-во ПЛЮ

          Будет иметь запуск прохода для акта КП склад
          Имеет кнопку присвоить значение QF всем TableView
     */

    public static TextField tempTXF = new TextField();
    public static TableView<MetaHalfAutoSet> tbtbtb = new TableView<>();

    public static String WORD_KEY_QF = "qf";
    public static String WORD_KEY_QMAX = "qm";

    public static ComboBox<String> current_combo = new ComboBox<>();
    public static String current_word = "EMP";



    public FlowPane fpGetNew(){

        new MetaHalfAutoSet().load();    // Загрузка всех полей
        FlowPane fpc = new FlowPane();
        upgradeFLP(fpc);                // Заполняется таблицей

        return fpc;

    }


    public FlowPane upgradeFLP(FlowPane fff){

        TableView<MetaHalfAutoSet> tableAHA = new TableView<>();
        tableAHA.setMinSize(800,530);
        tableAHA.setEditable(true);

        TableColumn<MetaHalfAutoSet,String> yu3_group = new TableColumn<>();
        yu3_group.setText("УИ3 группа        ");
        yu3_group.setMinWidth(370);
        yu3_group.setCellValueFactory(new PropertyValueFactory("nameYu3Field"));
        yu3_group.setCellFactory(TextFieldTableCell.forTableColumn());
        tableAHA.getColumns().add(0,yu3_group);

        TableColumn<MetaHalfAutoSet,Integer> qf_min_f = new TableColumn<>();
        qf_min_f.setText("QF_MIN");
        qf_min_f.setMinWidth(160);
        qf_min_f.setCellValueFactory(new PropertyValueFactory("count_qf_min"));
        qf_min_f.setEditable(true);
        qf_min_f.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter(){
             @Override
            public Integer fromString(String value) {
                 try{
                     int x = Integer.parseInt(value);
                     update_position(WORD_KEY_QF,x,tableAHA.getSelectionModel().getSelectedItem().getNameYu3Field());
                     return x;
                 }
                 catch (Exception e){
                     new Modal_Error().set_erroe_messege("Неверный ввод числа. Замена = 0");
                 }
                 return 0;
             }
        }
        ));

        tableAHA.getColumns().add(1,qf_min_f);

        TableColumn<MetaHalfAutoSet,Integer> max_plu = new TableColumn<>();
        max_plu.setText("Макс. PLU");
        max_plu.setMinWidth(300);
        max_plu.setCellValueFactory(new PropertyValueFactory("max_count_plu"));
        max_plu.setEditable(true);
        max_plu.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter(){
            @Override
            public Integer fromString(String value) {
                try
                {
                    int x = Integer.parseInt(value);
                    update_position(WORD_KEY_QMAX,x,tableAHA.getSelectionModel().getSelectedItem().getNameYu3Field());
                    return x;
                }
                catch (Exception e){
                    new Modal_Error().set_erroe_messege("Неверный ввод числа. Замена = 0");
                }
                return 0;
            }
        }));
        tableAHA.getColumns().add(2,max_plu);

        tableAHA.setItems(getList());

        fff.setOrientation(Orientation.HORIZONTAL);
        fff.getChildren().add(tableAHA);
        fff.getChildren().add(addComboBox());
        fff.getChildren().add(setButton());
        fff.getChildren().add(textNewGroupYU3());


        tbtbtb = tableAHA;
        tbtbtb.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                if(event.getCode().equals(KeyCode.DELETE)){
                    MetaHalfAutoSet temp = tbtbtb.getSelectionModel().getSelectedItem();
                    MetaHalfAutoSet.trytoDeletePosition(temp);
                    refreshlist();
                }

            }
        });

        return fff;
    }


    public static ObservableList<MetaHalfAutoSet> getList(){

        ObservableList<MetaHalfAutoSet> list = FXCollections.observableArrayList(MetaHalfAutoSet.listMeta);
        return list;
    }

    public static void disable_settings(){
        // Регулировка видимости

        Rule_contollers_Main.main_controller.save_button_hakpSklad.setDisable(false);
        Rule_contollers_Main.main_controller.tab_Hand_auto.setDisable(false);
    }

    public TextField textNewGroupYU3(){

        TextField textField = new TextField();
        textField.setPromptText("Консоль    ");
        textField.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.ENTER)){
                    changeQFOrAddNewGroup();
                }
            }
        });
        tempTXF = textField;
        return textField;
    }

    public Button setButton(){

        Button button = new Button();
        button.setOnAction(event -> changeQFOrAddNewGroup());
        button.setText("Добавить");
        return button;
    }


    public ComboBox<String> addComboBox(){

        // Добавление Combo_BOX со списком УИ3 которых нет!

        TreeSet<String> list = new TreeSet<>();
        boolean havvv = false;

        for(Ostatku ddd : Main.classOstatku){

            havvv = false;
            for(MetaHalfAutoSet meta : MetaHalfAutoSet.listMeta){

                if(ddd.getYu3().equals(meta.getNameYu3Field())){
                    havvv = true;
                }
            }
            if(!havvv)
            {
                list.add(ddd.getYu3());
            }
        }

        ComboBox<String> combo = new ComboBox<>();
        combo.setPromptText("Выбрать группу...");

        combo.setItems(FXCollections.observableArrayList(list));
        combo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                current_word = combo.getSelectionModel().getSelectedItem();
            }
        });

        return combo;


    }



    public static void setAll_qfinal(String words){

        // Присвоим всем группам один QF
        System.out.println("Полученное слово " + words);
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(words);

        String result = "";
        while (m.find()){
            result = m.group(0);
        }

        try {
            int sd = Integer.parseInt(result);

            for(MetaHalfAutoSet lll : MetaHalfAutoSet.listMeta){
                lll.setCount_qf_min(sd);
            }
        }
        catch (Exception e){
            new Modal_Error().set_erroe_messege("Не удалось присвоить/Прочитать число");
        }

    }

    public static void setAll_qPLU(String words){

        // Присвоим всем группам один QF
        System.out.println("Полученное слово " + words);
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(words);

        String result = "";
        while (m.find()){
            result = m.group(0);
        }

        try {
            int sd = Integer.parseInt(result);

            for(MetaHalfAutoSet lll : MetaHalfAutoSet.listMeta){
                lll.setMax_count_plu(sd);
            }
        }
        catch (Exception e){
            new Modal_Error().set_erroe_messege("Не удалось присвоить/Прочитать число");
        }

    }


    public void changeQFOrAddNewGroup(){
        String stars = "";
        try {
            stars = tempTXF.getText();
            tempTXF.clear();
            if(stars.length()<=1){
                stars = current_word;
            }
        }
        catch (Exception e){}

        if(stars.equals("EMP")){return;}

        if(stars.equals("ADDALL")){               // Нужно для добавления всех групп

            TreeSet<String> gplist = new TreeSet<>();

            for(Ostatku ddd : Main.classOstatku){
                gplist.add(ddd.getYu3());
                //Проход всех групп уи 3
            }


            MetaHalfAutoSet.listMeta.clear();

            for(String sss : gplist){
                MetaHalfAutoSet.listMeta.add(
                        new MetaHalfAutoSet(sss,7,3));
            }

            new MetaHalfAutoSet().save("");
            tbtbtb.setItems(getList());     // Присвоение
            return;
        } // end if



        if(stars.contains("F")&&stars.length()<=4){

            setAll_qfinal(stars);
            refreshlist();
            // присвоение всем значениям одного QF
            return;
        }

        if(stars.contains("Q")&&stars.length()<=4){

            setAll_qPLU(stars);
            refreshlist();
            // присвоение всем значениям одного QF
            return;
        }




        MetaHalfAutoSet.tryAddNewGroup(stars);
        refreshlist();

    }

    public static void refreshlist(){

        /*
             Обновляет содержимое TableView
         */

        new MetaHalfAutoSet().save("");
        new MetaHalfAutoSet().load();

        tbtbtb.getColumns().get(0).setVisible(false);
        tbtbtb.getColumns().get(0).setVisible(true);
        tbtbtb.setItems(getList());
    }


    public static void update_position(String what, int xkk, String name){

        if(what.equals(WORD_KEY_QF)) {

            for(MetaHalfAutoSet lll : MetaHalfAutoSet.listMeta){
                if(lll.getNameYu3Field().equals(name)){lll.setCount_qf_min(xkk);}
            }

        } if(what.equals(WORD_KEY_QMAX)) {

            for(MetaHalfAutoSet lll : MetaHalfAutoSet.listMeta){
                if(lll.getNameYu3Field().equals(name)){lll.setMax_count_plu(xkk);}
            }

        }

    }







}
