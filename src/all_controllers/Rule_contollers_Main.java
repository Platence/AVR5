package all_controllers;

import WRSOnLine.OpenBrowser;
import all_paths.List_xlsx_files;
import all_paths.Paths_Main_File;
import animation_elements.AddPicutre;
import animation_elements.PositionElements;
import chekCrashPLU.CrashesPlu;
import chekCrashPLU.Karantin.ControlClassKarantin;
import dateClass.DateIni;
import dateClass.OpenWrs;
import error_package.Modal_Error;
import error_package.SlideError.SlideModalError;
import fin_kp_AI.BoxAnotherTMC;
import fin_kp_AI.KpFinObjects;
import fin_kp_AI.ShowForTableView;
import graficAVR.GraficAvrProd;
import graficAVR.ValueSlider;
import info_page.InformationForSlide;
import inv15docpack.Inv15Field;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import numberShopPack.ShopDescription;
import numberShopPack.ShopNumber;
import organizationClass.AllOrganization;
import passwordPackega.PassLogic;
import peopleComisson.PeopleComisson;
import sample.Controller;
import sample.Main;
import settingsOther.SettingsOtherHere;
import sklad_KP_AI.Algorithm_sklad;
import sklad_KP_AI.Description;
import sklad_KP_AI.TableViewClass.InitializateTable;
import sklad_KP_AI.settngsHa.AlgorithmHalfHandAuto;
import warehouse_plu.All_List_group_info;
import warehouse_plu.Ostatku;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.TreeSet;

public class Rule_contollers_Main {

    /*
        Отсюда ожидается управление любым контроллером из любой точки
        программы
     */


    public static Controller main_controller;

    public static boolean wasInitialized = false;           // Инициализирует таблицы (  1 раз )
    public static ImageView imageView;



    public static void set_settings_visible(boolean x,boolean y){
        // Выбираем видно инфо панель или нет
        main_controller.progress_bar_main.setVisible(x);
        main_controller.label_info.setVisible(y);
    }

    public void set_default_start(){
        //Применяем настройки к элементам первое подключение
        main_controller.kp_sklad_tab.setDisable(true);
        main_controller.analitik_tab.setDisable(true);
        main_controller.wrsOnLine.setOnSelectionChanged(event -> new OpenBrowser().go(main_controller));

        main_controller.kp_sklad_tab.setOnSelectionChanged(event -> new OpenWrs());

        try{new AddPicutre().load();}
        catch (Exception e){new Modal_Error().set_erroe_messege("Ошибка загрузки изображения");}


        add_10_field();                          // stable size pick xlsx file

        new DateIni().createStandartDate();      // load date settings

                                                 // Load All organization list
                                                 // and Listner for mouse click

        AllOrganization.load();
        AllOrganization.setListner();

        main_controller.organization_box.setItems(FXCollections.observableArrayList(AllOrganization.listOrganizationAndOrder));

        // Table view Organization and settings

        AllOrganization.setTableSettins();


        // settings ShopNumber

        ShopNumber.setDefaultHandler();

        // Loading people
        new PeopleComisson().loading();

        //loading handler inv-15 field

        Inv15Field.setHandler();

        // InitializeTable

        setDefaultHandlerOnKpFin();

        // Handler button kp fin

        setHandlerForKpFin();

        // SetListToBoxTmc

        setListForBoxTmcFinKP();

        // draw Graf thenOpen graf

        addGrafListner(); setListnerToSliderGrathGroup();

        new Inv15Field().load(); // Загрузка полей ИНВ-15

        setTestToBasePath(); // Показывать путь к базе магазинов

        addListnerFromTableView(); // Удалить позицию по нажатии Delete

        new CrashesPlu().load();

        main_controller.crashes_tab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                CrashesPlu.setOpene();
                if(CrashesPlu.wasOpen){
                    CrashesPlu.setTableView();
                }
            }
        });

        //new PositionElements().setPositionToListLabel(); // Move Object chain

        new SettingsOtherHere().initGroup();

        createForInfo1();

        ControlClassKarantin.loadListKarantinPLU();             // Loading karantin PLU

        new InitializateTable(main_controller);       // Loading KpSkladTable

        loadPicture();                               // Loading picture For All bundleWRS

        main_controller.kp_sklad_tab.setOnSelectionChanged(e->saveall());

    }

    public void saveall(){
        if(main_controller.kp_sklad_tab.isSelected()){
            main_controller.savePeople();
        }

    }

    public void loadPicture(){
        FileInputStream input = null;
        try {
            input = new FileInputStream("src/binn/photoPicture/pic button.png");
            Image image = new Image(input,130,120,false,false);
            imageView = new ImageView(image);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void createForInfo1(){
        main_controller.skladlisttempbox.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                new SlideModalError().setMessage(InformationForSlide.messageForThirstTempSklad());
            }
        });
    }

    public void set_start(){
        //Применяем настройки к элементам Успешное подключение
        if(!PassLogic.status){main_controller.kp_sklad_tab.setDisable(false);}
        main_controller.analitik_tab.setDisable(false);
        main_controller.gravr_tab.setDisable(false);
    }

    public void init_list_path(){

        /*
           Заполняем комбо-бокс именами файлов, которые затем будем открывать.
         */

        main_controller.list_files_ch.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                Add_path_address add_path_address = new Add_path_address();
                add_path_address.go_all_around(new File(Paths_Main_File.path_final_out));

                ArrayList<String> al = new ArrayList<>();

                for(List_xlsx_files d : List_xlsx_files.list_paths_xlsx )
                {
                    al.add(d.getName_file());
                }
                ObservableList<String> oble = FXCollections.observableArrayList(al);
                main_controller.list_files_ch.setItems(oble);
                main_controller.list_files_ch.setVisibleRowCount(10);

            }
        });

       main_controller.number_shop.setOnKeyReleased(new EventHandler<KeyEvent>() {
           @Override
           public void handle(KeyEvent event) {

               String s = ShopDescription.getMeMagazine(main_controller.number_shop.getText());
               String s2 = Rule_contollers_Main.main_controller.number_shop.getText();
               new ShopNumber().save(s2);
               if(s.equals("EMPTY")){main_controller.number_shop.setStyle("-fx-background-color:red");
                                     main_controller.buttonAddNewMag.setVisible(true);}

               else {main_controller.number_shop.setStyle("-fx-background-color:LIGHTGREEN");
                     main_controller.buttonAddNewMag.setVisible(false);}

           }
       });


    }

    public static void set_visible_element_cp_sklad(){
        main_controller.info1.setVisible(true);
        main_controller.info2.setVisible(true);
        main_controller.ignor_in_out.setVisible(true);
        main_controller.qf_in_ayt_plu_c.setVisible(true);
        main_controller.qf_kp_sklad_c.setVisible(true);
        main_controller.create_act_cp_sklad.setVisible(true);

    }

    public static void run_pick_all_algorithm(){
        Algorithm_sklad.list_id_algorithm.clear();
        Algorithm_sklad.set_up_id();
        main_controller.pick_algorith_sklad.setVisible(true);

        main_controller.pick_algorith_sklad.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ArrayList<String> al = new ArrayList<>();

                for(String as : Algorithm_sklad.list_id_algorithm )
                {

                    al.add(as);
                }
                ObservableList<String> oble = FXCollections.observableArrayList(al);
                main_controller.pick_algorith_sklad.setItems(oble);
                main_controller.progress_bar_main.setVisible(false);

            }
        });
    }

    public static void set_description_KP_Sklad(String x){
        Description.showSliderInfo(x);

    }

    public static void set_event_handler_for_list_alg(){

        Rule_contollers_Main.main_controller.pick_algorith_sklad.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(Rule_contollers_Main.main_controller.pick_algorith_sklad.getValue()!=null)
                {
                    Rule_contollers_Main.set_description_KP_Sklad(Rule_contollers_Main.main_controller.pick_algorith_sklad.getValue());
                }
            }
        });
    }

    public static void set_parametrs_for_KPPP_Sklad(){
        // Назначение параметров для КППП_склад
        //Qфин минимальное, ин-аут PLU, и игнор плюсов

        try {
            Algorithm_sklad.min_QF = Integer.parseInt(main_controller.qf_kp_sklad_c.getText());
            Algorithm_sklad.max_pLU_OUT = Integer.parseInt(main_controller.qf_in_ayt_plu_c.getText());
            Algorithm_sklad.ignored_pulse = main_controller.ignor_in_out.isSelected();
            System.out.println("Игнор плюсов " + Algorithm_sklad.ignored_pulse);
        }
        catch (Exception e){
            new Modal_Error().set_erroe_messege("Произошла ошибка при выгрузке параметров поиска ( 143) " + System.lineSeparator()+"" +
                    "Рекомендуется ЗАВЕРШИТЬ поиск!");
        }

    }


    public static void creating_Act_CP_Sklad(String d){

        unmind_listner();

        Algorithm_sklad.current_pick = d;    //d - выбранный параметр

        //Получаем нужный алгоритм и помещаем его в отдельный поток
        // + передаем данные из TextField ( QF, ин-аут)

        Rule_contollers_Main.set_parametrs_for_KPPP_Sklad();

        Algorithm_sklad as = new Algorithm_sklad();
        main_controller.progress_bar_main.progressProperty().bind(as.progressProperty());
        main_controller.label_info.textProperty().bind(as.messageProperty());

        Thread th = new Thread(as);th.setDaemon(true);th.start();
    }

    public static void loadingExcel(String current_file){

        //Обнуление статических полей НЕОБХОДИМОСТЬ
        Refresh_Field rf = new Refresh_Field();
        rf.ref_for_sklad_kp();

        // Получаем путь файла с помощью имени

        String current_pick = current_file;
        for (List_xlsx_files zet : List_xlsx_files.list_paths_xlsx){
            if(zet.getName_file().equals(current_pick)){
                Paths_Main_File.path_list_diff_current = zet.getPath();
            }
        }

        //  Показываем интерфейс
        set_settings_visible(true,true);
    }


    public static void setInformationAfterNonStandartLoad(){
        //main_controller.label_info.textProperty().unbind();
        main_controller.progress_bar_main.setVisible(false);
        main_controller.label_info.textProperty().unbind();
        main_controller.label_info.setText("CROSS РЕЖИМ. НОВАЯ ВЕРСИЯ СВЕРКИ");

        set_settings_visible(false,true);
        set_visible_element_cp_sklad();
        run_pick_all_algorithm();
    }

    public static void launchStandartProgress(){

        System.out.println("Выбран алгоритм Стандартный файл GK");

        DownloadTask downloadTask = new DownloadTask();
        main_controller.progress_bar_main.progressProperty().bind(downloadTask.progressProperty());
        main_controller.label_info.textProperty().bind(downloadTask.messageProperty());

        Thread th = new Thread(downloadTask);
        th.setDaemon(true);
        th.start();
    }



    public static void null_all_static_field(){
        Main.classOstatku.clear(); Ostatku.count_sklad_plu = 0;
        Ostatku.final_result = 0.0;

    }

    public static void add_10_field(){

        ArrayList <String> temp = new ArrayList<>();
        for(int i = 0 ; i < 6; i ++){
            temp.add("EMPTY" + i);
        }
        ObservableList<String> asd = FXCollections.observableArrayList(temp);
        main_controller.list_files_ch.setItems(asd);
    }


    public static void start_Hand_Auto(){

        main_controller.progress_bar_main.setVisible(true);

        unmind_listner();

        AlgorithmHalfHandAuto alo= new AlgorithmHalfHandAuto();

        main_controller.label_info.textProperty().bind(alo.messageProperty());
        main_controller.progress_bar_main.progressProperty().bind(alo.progressProperty());


        Thread th = new Thread(alo); th.setDaemon(true); th.start();


    }


    public static void unmind_listner(){

        // Отключили все предыдущие слушатели

        main_controller.label_info.textProperty().unbind();
        main_controller.progress_bar_main.progressProperty().unbind();
    }

    public static void setDefaultHandlerOnKpFin(){

        /**
         * Инициализирует таблицу единожды.
         * При нажатии второй раз, метод не работает.
         */


        main_controller.tab_fin_kp.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {

                if(!wasInitialized){
                    wasInitialized = true;

                    main_controller.plu_c.setCellValueFactory(new PropertyValueFactory("plu"));
                    main_controller.name_c.setCellValueFactory(new PropertyValueFactory("name"));
                    main_controller.area_c.setCellValueFactory(new PropertyValueFactory("area_one_strok"));
                    main_controller.yu_c.setCellValueFactory(new PropertyValueFactory("yu2"));
                    main_controller.qf_c.setCellValueFactory(new PropertyValueFactory("qfinal"));
                    main_controller.qy_c.setCellValueFactory(new PropertyValueFactory("qychetnoe"));
                    main_controller.control_c.setCellValueFactory(new PropertyValueFactory("controlChek"));
                    main_controller.odds_c_c.setCellValueFactory(new PropertyValueFactory("oddsCOUNT"));
                    main_controller.neprodam_value.setCellValueFactory(new PropertyValueFactory("return_boolean"));
                    main_controller.prihod_value.setCellValueFactory(new PropertyValueFactory("lastDateImport"));
                    main_controller.prodaja_value.setCellValueFactory(new PropertyValueFactory("lastDateSale"));

                    System.out.println("TABLE KP FIN WAS INIT");


                }
            }
        });
    }

    public static void setHandlerForKpFin(){

        // Событие при нажатии кнопки "Проверить"
        main_controller.chek_old_kp_fin.setOnAction(e->new KpFinObjects().loadAndChekOldFile());
        main_controller.save_and_upload_new_fin.setOnAction(e-> new KpFinObjects().saveNewFile());
    }

    public static void setListForBoxTmcFinKP(){
        new BoxAnotherTMC().setForBoxNames(main_controller.boxTmc);

    }

    public static void addGrafListner(){

        /**
         *   Запуск модуля графика по недостаче к кол-ву PLU
         */
        main_controller.gravr_tab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                //Rule_contollers_Main.setListnerToChangeGroup();
                new GraficAvrProd().drawGrafic();
            }
        });

    }

    public static void setListnerToSliderGrathGroup(){

        main_controller.sliderOddsGrathic.setMax(2000);
        main_controller.sliderOddsGrathic.setMin(101);
        main_controller.sliderOddsGrathic.setValue(300);

        main_controller.sliderOddsGrathic.setOnMouseReleased(
                event ->
                new ValueSlider().setValue(GraficAvrProd.currentGroup));

    }

    public static void setTestToBasePath(){

        try {
            Paths_Main_File.PATH_CURRENT_BASE_SHOP =  Paths_Main_File.loadInformation("src/binn/baseShop/addresstoBase","LOADBASE");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            new Modal_Error().set_erroe_messege("ERROR BASE LOAD " + e.getMessage());
        }




    }

    public static TreeSet<String> getAllGroupYU2(){

        TreeSet<String> name = new TreeSet<>();
        for(Ostatku z : Main.classOstatku){
           if(All_List_group_info.acceptYU2Group(z.getYu2())){name.add(z.getYu2());}
        }
        name.add("ALL");

      return name ;
    }

    public static void setListnerToChangeGroup(){

        main_controller.change_group_from_oods_gr.setItems(FXCollections.observableArrayList(getAllGroupYU2()));

        main_controller.change_group_from_oods_gr.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String group = main_controller.change_group_from_oods_gr.getValue();
                GraficAvrProd.currentGroup = group;
                new ValueSlider().setValue(GraficAvrProd.currentGroup);
            }
        });

    }

    public static void addListnerFromTableView(){
        ShowForTableView.addListnerDelete();
    }







}
