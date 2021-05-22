package organizationClass;

import all_paths.Paths_Main_File;
import error_package.Modal_Error;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static all_controllers.Rule_contollers_Main.main_controller;

public class AllOrganization {


    /*
        Class for Combo-box organization
     */

    private char [] nameAndNumber;
    private char [] name;
    private char [] number;

    public static ArrayList<AllOrganization> listOrganizationAndOrder = new ArrayList<>();
    public static String currentPickNamePrganization = "EMP";
    public static String currentPickOrderNumber = "EMP";
    private static StringBuilder st = new StringBuilder();



    public AllOrganization(String name, String number) {
        this.name = name.toCharArray();
        this.number = number.toCharArray();
        st.append(name);st.append(number);
        this.nameAndNumber = st.toString().toCharArray();
        st.setLength(0);
    }

    public AllOrganization() {

    }

    public String getName() {
        StringBuilder sb = new StringBuilder();
        for (char c : this.name) {
            sb.append(c);
        }
        return sb.toString();
    }

    public void setName(String name) {
        this.name = name.toCharArray();
    }

    public String getNumber() {
        StringBuilder sb = new StringBuilder();
        for (char c : this.number) {
            sb.append(c);
        }
        return sb.toString();
    }

    public void setNumber(String number) {
        this.number = number.toCharArray();
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        for (char c : this.nameAndNumber) {
            sb.append(c);
        }
        return sb.toString();
    }

    public static void load(){

        listOrganizationAndOrder.clear();                                     // Clear old list

        try(FileReader fr = new FileReader(new File(Paths_Main_File.PATH_TO_LIST_ORGANIZATION))){

            Scanner sc = new Scanner(fr);

            while (sc.hasNextLine()){

                listOrganizationAndOrder.add(getloaderOrganization(sc.nextLine()));

                // Try add new(old) position from Pattern. return new Object in list
            }
            sc.close();

        }
        catch (Exception e){
            System.out.println("Error with loading order and name organization line 33");
        }

    }

    public static void save(){

        //standart save operation

        try(FileWriter wf = new FileWriter(new File(Paths_Main_File.PATH_TO_LIST_ORGANIZATION))){
            StringBuilder sb = new StringBuilder();
            for(AllOrganization list : listOrganizationAndOrder){
                sb.append(list.getName()).append("!").append(list.getNumber()).append("!").append(System.lineSeparator());
                // name ! number ! + LineSeparator
            }
            wf.write(sb.toString());
        }
        catch (Exception e){
            System.out.println("Error with save file organization line 96");
            new Modal_Error().set_erroe_messege("Возникла ошибка при сохранении приказов. " + e.getMessage());
        }

    }

    public static void deletePosition(AllOrganization deleted){
        listOrganizationAndOrder.remove(deleted);
        save();
        load();
        goview();
    }

    public static void updateOrg(String oldValue, String newValue, int operation){

        // 1 - set new name
        // 2 - set new number

        for(AllOrganization tempAO : listOrganizationAndOrder){

            if(tempAO.getName().equals(oldValue)){

                if(operation == 1){
                    tempAO.setName(newValue);
                }
            }
        }

        // then end, save and reset

        save();
        load();
        goview();
    }

    public static void updateOrg(AllOrganization oldValue, String newValue, int operation){

        // 1 - set new name
        // 2 - set new number

        for(AllOrganization tempAO : listOrganizationAndOrder){

            if(tempAO.getName().equals(oldValue.getName())){

                if(operation == 2){
                    tempAO.setNumber(newValue);
                }
            }
        }

        // then end, save and reset

        save();
        load();
        goview();
    }


    public static AllOrganization getloaderOrganization(String line){

        // Name ! number !

        Pattern p = Pattern.compile("(.*?)!");
        Matcher m = p.matcher(line);

        String[] tempMassive = new String[2];

        int count = 0;

        while (m.find()){
            tempMassive[count] = m.group(1);
            count++;
        }

        return new AllOrganization(tempMassive[0],tempMassive[1]);
    }


    public static void setTableSettins(){

        main_controller.table_organization.setEditable(true);

        main_controller.name_organization_col.setCellValueFactory(new PropertyValueFactory<>("name"));
        main_controller.number_organization_col.setCellValueFactory(new PropertyValueFactory<>("number"));

        // Set settings from column
        main_controller.name_organization_col.setCellFactory(TextFieldTableCell.forTableColumn());    // Important option!!!!!!!!!!!!!
        main_controller.number_organization_col.setCellFactory(TextFieldTableCell.forTableColumn());  // Important option!!!!!!!!!!!!!

        // Reset
        goview();

        // Save Settings
        setStandartSaveAndDelete();

    }

    public ObservableList<AllOrganization> resetListOBS(){

        // get new List organization

        return FXCollections.observableArrayList(listOrganizationAndOrder);
    }

    public static void goview(){
        // View table (reset)

        main_controller.table_organization.setItems(new AllOrganization().resetListOBS());
        main_controller.table_organization.refresh();

        main_controller.organization_box.getItems().clear();
        main_controller.organization_box.setItems(new AllOrganization().resetListOBS());
    }

    public static void setListner(){

        // update box every on click

        main_controller.organization_box.setOnAction(event -> {
            try {
                currentPickNamePrganization = main_controller.organization_box.getSelectionModel().getSelectedItem().getName();
                currentPickOrderNumber = main_controller.organization_box.getSelectionModel().getSelectedItem().getNumber();
                System.out.println("Action метод " + currentPickOrderNumber + " / " + currentPickNamePrganization);
            }
            catch (Exception e){
                System.out.println("Error pick org " + e.getMessage() + " line 236");
            }
        });
    }

    public static void addEmptyOrg(){
        AllOrganization.listOrganizationAndOrder.add(new AllOrganization("Unknown","Empty number"));
        goview();
    }

    public static void setStandartSaveAndDelete(){


        // Для имени обновление имени
        // Для № приказа обновить номер приказа

        main_controller.name_organization_col.setOnEditCommit(event -> {
            String tempOld = event.getOldValue();
            String tempnew = event.getNewValue();
            updateOrg(tempOld,tempnew,1);
        });

        main_controller.number_organization_col.setOnEditCommit(event -> {
            AllOrganization tempCurrent = event.getRowValue();
            String newValue = event.getNewValue();
            updateOrg(tempCurrent,newValue,2);
        });

        main_controller.table_organization.setOnKeyReleased(event -> {
            if(event.getCode() == KeyCode.DELETE){
                deletePosition(main_controller.table_organization.getSelectionModel().getSelectedItem());
                main_controller.organization_box.getItems().clear();
                main_controller.organization_box.setItems(new AllOrganization().resetListOBS());
            }
        });

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AllOrganization that = (AllOrganization) o;
        return Arrays.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash((Object) name);
    }
}
