package sample;

import all_controllers.*;

import all_controllers.logicAnalitic.BundleForNegativeCount;
import all_controllers.logicAnalitic.BundleResultGraph;
import all_controllers.logicAnalitic.Bundle_For_WRS;
import all_controllers.logicAnalitic.LogicForAnalitik;
import all_controllers.mouse_event.ButtonRas4et.HoldButtodDisable;
import animation_elements.HideComponents;
import animation_elements.Repiat;
import decriptor.ConsoleAVR;
import decriptor.DecriptorBlock;
import graficAVR.scaleStepChoiceBox.SetStandartScale;
import all_paths.Paths_Main_File;
import animation_elements.AddPicutre;
import chekCrashPLU.CrashesPlu;
import chekCrashPLU.IgnoredPlu;
import chekCrashPLU.Karantin.KarantinPLU;
import chekCrashPLU.TableViewIgnoredPlu;
import chekVersion.CheckDefoltFolder;
import dateClass.DateIni;
import error_package.Modal_Error;
import error_package.SlideError.SlideModalError;
import exportXLSX.exportAppPrikaz.AplicationforOrder;
import exportXLSX.expotRaspiska.CreateRaspiska;
import exportXLSX.inv15i.ExportInv15;
import exportXLSX.kpSkladXlsX.KpppSKLADExport;
import fin_kp_AI.AddPositionToTableAndList;
import fin_kp_AI.BannedPlU;
import fin_kp_AI.BoxAnotherTMC;
import graficAVR.GraficAvrProd;
import graficAVR.ValueSlider;
import info_page.ButtonAction;
import info_page.ListOperation;
import info_page.smenaMOLPACKAGE.DescriptionSmenaMOL;
import inv15docpack.Inv15Field;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import megaBlock.MegaBlockYU2;
import newEngine.ResultsOSTATKU.ListStrings;
import newEngine.ResultsOSTATKU.ListTemps;
import newEngine.StartnewEngine;
import newEngine.StringClass.CurrentLinkedString;
import numberShopPack.AddNewMagazine;
import numberShopPack.ParserShop;
import organizationClass.AllOrganization;
import parseIntFromTextField.ParseField;
import parseNeprodam.StartNeprodamParse;
import parser_xml.not_standart_parser.StartedAnotherParse;
import passwordPackega.PassLogic;
import peopleComisson.PeopleComisson;
import setstandartcolors.SetColorsToObject;
import settings_main.BlockingClass;
import settings_main.Settings;
import sklad_KP_AI.CheckZonHall;
import sklad_KP_AI.Container_KP_Sklad;
import sklad_KP_AI.settngsHa.MetaHalfAutoSet;
import testPackege.PrintDialogPath;
import warehouse_plu.ExtendedOstatku;
import warehouse_plu.Ostatku;
import warehouse_plu.odds_from_group.Odds_level_One;

import java.util.ArrayList;

import static fin_kp_AI.AddPositionToTableAndList.clearAllList;


public class Controller {

    // Svod

    public Pane mainPainnn;
    @FXML
    public FlowPane flow_Message;

    public Button buttonadPath;
    public FlowPane flowConsole;

    // Ignored
    public TableView<IgnoredPlu> tableIgnor;
    public TableColumn<IgnoredPlu,String> ignorePluCol;
    public TableColumn<IgnoredPlu,String> simpledescignorcol;
    public TableColumn<IgnoredPlu,CheckBox> totalignorcolBox;
    public TableColumn<IgnoredPlu,CheckBox> onlyWrsIgnored;

    // SSKRR

    public ComboBox<String> optionView;
    public Button buttonView;
    public Button buttonPr;
    public TextField sdfsdfsdf;

    public Tab awrsos;
    public TabPane mainPain2;
    public TabPane main_pain;
    public ProgressBar progress_bar_main;
    public Label label_info;

    public Tab kp_sklad_tab;
    public Tab settings_tab;
    public Tab analitik_tab;
    public Tab tab_Hand_auto;

    public TextField info_path_field;
    public ComboBox<String> list_files_ch;
    public TextField qf_kp_sklad_c;
    public TextField qf_in_ayt_plu_c;
    public CheckBox ignor_in_out;
    public CheckBox sortByGroup;
    public Button create_act_cp_sklad;
    public Label info1;
    public Label info2;
    public Label last_info;
    public ComboBox<String> pick_algorith_sklad;
    public Accordion accordion_TEST;
    public ImageView image_main;


    public FlowPane flow_panf_HA;
    public Button save_button_hakpSklad;

    // Date
    public DatePicker calendar_main;
    public DatePicker calendar_order;

    // Organizaton box

    public ChoiceBox <AllOrganization> organization_box;
    public TableColumn<AllOrganization,String> name_organization_col;
    public TableColumn<AllOrganization,String> number_organization_col;
    public TableView<AllOrganization> table_organization;

    // ShopNumber
    public TextField number_shop;

    // Comisson part

    public TextField peek_field;
    public TextField tvoy_padej; // ПЕРЕНЕСЕНЫ В НАСТРОЙКИ

    // ЧК

    public TextField partOne;
    public TextField parttWo;
    public TextField partThree;
    public TextField partMol;
    //Должность
    public TextField d1;
    public TextField d2;
    public TextField d3;
    public TextField dMOL;
    public Label info11;

    // inv 15

    public TextField pkoField;
    public TextField rko_field;
    public TextField reestr_summ;
    public TextField fact_summ;
    public CheckBox save_all_field_inv_15;

    // Button KP_FIN
    public Button chek_old_kp_fin;
    public Button save_and_upload_new_fin;
    // AnotherButton
    public Button buttonSave;

    // Table Fin KP

    public TableView<Ostatku> kp_fin_table_m;
    public TableColumn<Ostatku,String> plu_c;
    public TableColumn<Ostatku,String> name_c;
    public TableColumn<Ostatku,String> area_c;
    public TableColumn<Ostatku,String> yu_c;
    public TableColumn<Ostatku,Double> qf_c;
    public TableColumn<Ostatku,Double> qy_c;
    public TableColumn<Ostatku,String> control_c;
    public TableColumn<Ostatku,Double> odds_c_c;
    public TableColumn<Ostatku,String> prihod_value;
    public TableColumn<Ostatku,String> prodaja_value;
    public TableColumn<Ostatku,String> neprodam_value;

    public Button add_neprodam_but;

    public Button but_add_neprodam_ct;
    public TextField field_min_q_neprodam;
    public TextField field_max_odds_nepr;
    public Label qfInfoLab;
    public Label maxrash;
    public Slider slider_neprodam;
    public Label label_info_neprodan_max_Count;

    public PasswordField sskrrpasswordFIELD;

    public CheckBox blockS999KPFIN;
    public CheckBox blockAlcInNeprod;

    // + tab Fin

    public Tab tab_fin_kp;
    public Tab prepeareToPu;
    public Tab tabsskrr;
    // Box TMC

    public ChoiceBox<String> boxTmc;
    public TextField qfMinKpFin;
    public TextField maxOdsKpFin;
    public CheckBox control_p_block;
    public CheckBox skladlisttempbox;


    //Grathics

    public Tab gravr_tab;
    public FlowPane flowPaneGrafics;
    public ChoiceBox<String> change_group_from_oods_gr;
    public FlowPane flowPanePie;

    //Settings
    public CheckBox month_to_words;

    // Slider ( Grathics )
    public Slider sliderOddsGrathic;
    public Label labelSliderGrath;

    public TableView<CrashesPlu> table_crashes;
    public TableColumn<CrashesPlu,String> plu_crashes;
    public TableColumn<CrashesPlu,String> description_crashes;
    public Tab crashes_tab;

    public Label versionLabel;
    // LabelsInfo

    public Label label_changeDatePU;
    public Label orgDatePu;
    public Label ck1;
    public Label numberShop;
    public Label dateOrderLabel;

    // Tab
    public Tab kpskadskad;
    public Button button_kp_sklad_ras4et;
    public ComboBox<String> listSHTYPE;

    //SMENAMOL
    public TextField dolMOLfield;
    public TextField fieldSMENAMOL;

    //КарантинTable
    public TableView<KarantinPLU> karaPlutable;
    public TableColumn<KarantinPLU,String> pluKarantin;
    public TableColumn<KarantinPLU,String> descKarantin;
    public TableColumn<KarantinPLU,String> reazon;

    //TableKPSklad
    public TableView<Ostatku> kpskladTable;
    public TableColumn<Ostatku,String> plukpSklTAB;
    public TableColumn<Ostatku,String> plukpdescTAB;
    public TableColumn<Ostatku,String> plukpdareaTAB;
    public TableColumn<Ostatku,Double> plukpdoddsTAB;
    public TableColumn<Ostatku,Double> plukpdqyyyTAB;
    public TableColumn<Ostatku,Double> plukpdqFFFFTAB;
    public TableColumn<Ostatku,String> plukpdqPPPPPTAB;

    public TextField fieldNan;
    public TextField fieldNan1;

    public Button buttonLoad;

    public Button buttonAddNewMag;

    public Pane extendPaneGrapth;

    public ChoiceBox<Integer> choiceStep;

    public void initialize(){

//        ConsoleAVR consoleAVR = new ConsoleAVR(flowConsole);
//        Thread thread = new Thread(consoleAVR);
//        thread.start();

        buttonAddNewMag.setVisible(false);
        Rule_contollers_Main.main_controller = this;

        Rule_contollers_Main rule_contollers_main = new Rule_contollers_Main();
        rule_contollers_main.set_default_start();
        // Many settings saw here!
        Rule_contollers_Main.set_settings_visible(false,false);

        rule_contollers_main.init_list_path();   // Установка слушателей

        list_files_ch.setOpacity(35);

         ParserShop.startSet();
        // Установка всех магазинов
        // Необходимо изменить "дежурный файл"
        new Container_KP_Sklad().loading();       // Загрузка старого списка КП_Склад

        StartNeprodamParse.setVisiblePriority();  // Уберем кнопки загрузки

        advancedStart();
        gravr_tab.setDisable(true);

        new SetColorsToObject().setColors(this);
        new DescriptionSmenaMOL().createSmenaMolList(this);
        Platform.runLater(()-> new CheckDefoltFolder().startLogicDefolt()); // Проверка на статическую папку

        new SlideModalError().defultXYFlowPane();                             // Установка слайда окна сообщений

        BlockingClass.loadStateS999KPF(Paths_Main_File.PATH_BLOCK_ALCO,blockAlcInNeprod);
        BlockingClass.loadStateS999KPF(Paths_Main_File.PATH_BLOCK_S999,blockS999KPFIN);

        MegaBlockYU2.loadingYU2();

        Platform.runLater(()-> new BundleResultGraph(this));
        StartNeprodamParse.setPositionToButton();
        createListNode();

    }

    public void createListNode(){
        ArrayList<Node> list = new ArrayList<>();
        list.add(d1);list.add(d2);list.add(d3);
        list.add(partOne);list.add(parttWo);list.add(partThree);
        list.add(listSHTYPE);list.add(calendar_main);
        list.add(number_shop);list.add(dMOL);
        list.add(partMol);list.add(organization_box);
        list.add(calendar_order);
        new HideComponents(list);
    }

    public void loadingExcelForOstatku(){
        Platform.runLater(()-> button_kp_sklad_ras4et.setVisible(false));
        repression();
        new HoldButtodDisable().holdbutton(button_kp_sklad_ras4et); // Временная блокировка
    }

    public void repression(){
        try
        {
            Main.classOstatku.clear();
            Main.classOstatku = new ArrayList<>();
            BannedPlU.clearList();
            Rule_contollers_Main.null_all_static_field();
            clearAllList();

            String s = list_files_ch.getSelectionModel().getSelectedItem();

            setInfLastLoad(s); // Лейбл последнего просчета

            Rule_contollers_Main.loadingExcel(s);
            // Путь хранится в  Paths_Main_File.path_list_diff_current
            //Проверим на Shared_String
            ConsoleAVR.printlnn("Выбран файл " + Paths_Main_File.path_list_diff_current);

            boolean res = StartedAnotherParse.go_go_go(Paths_Main_File.path_list_diff_current, Settings.destenation_from_target,"SPR");
            // При неудачном считывании, запускаем старый вариант res = false;

            if(!res) {
                //Запуск отдельного потока где читается файл ( Стандарт )
                Rule_contollers_Main.launchStandartProgress();
            }

            if(res){
                Alert alert = new HoldButtodDisable().showAllertNonOroginal("НЕСТАНДАРТНАЯ СТРУКТУРА xlsx файла, ПОЖАЛУЙСТА, ОЖИДАЙТЕ...");
                Rule_contollers_Main.setInformationAfterNonStandartLoad();
                // При удачном закроем поля и выдадим оповещение
                ListStrings.clearAllList();
                // ПРИДУМАТЬ ГДЕ ОБНУЛЯТЬ ЛИСТ СВЕРКИ
                Platform.runLater(()->{
                    new StartnewEngine(Paths_Main_File.path_list_diff_current, StartNeprodamParse.PATH_EXTRACT);
                    ListTemps.genereteNewOstatku();
                    CurrentLinkedString.clearMap();
                    System.gc();
                    if(!PassLogic.status){CrashesPlu.chek_all_point_PLU();}
                    alert.close();
                    /// TEST
                });
            }
            //Показывать какой алгоритм выбран(не относится к парсеру)
            Rule_contollers_Main.set_event_handler_for_list_alg();

        }
        catch (Exception e){
            ConsoleAVR.printlnn("Критическая ошибка loadingExcelForOstatku " + e.getMessage());
            e.printStackTrace();
        }

        if(skladlisttempbox.isSelected()){
            new Modal_Error().set_erroe_messege("Указанный файл, будет зафиксирован, как <<1 слив>>." + System.lineSeparator()+"" +
                    "Работает только с оригинальными файлами из GK");
        }
        AddPositionToTableAndList.lastSuccefulSort.clear();
        CurrentLinkedString.clearMap();
        System.gc();
    }

    public void advancedStart(){
        // запуск колонки доп информации
        optionView.setItems(new ListOperation().getObsListOperation());
        optionView.getSelectionModel().select(0);
        buttonView.setOnMouseClicked(e-> new ButtonAction().startChek());
        // Запуск сбора информации
        buttonPr.setOnMouseClicked(e->new ButtonAction().startChek(1));
    }

    public void addstandartPositopn(){
        IgnoredPlu.saveCurrentList("0000","UNKNOWN","0","0");
        new TableViewIgnoredPlu().updateTable();
    }

    public void saveallIgnor(){
        IgnoredPlu.saveCurrentList("1","1");
        new Modal_Error().set_erroe_messege("Saved");
        new TableViewIgnoredPlu().updateTable();
    }

    public void createDefoltFolder(){
        Platform.runLater(()-> new CheckDefoltFolder().save()); // Проверка на статическую папку
    }

    public void add_directory_path(){
        //Добавляем папку выгрузки
        Add_path_address add_path_address = new Add_path_address();
        add_path_address.add_new_path();

        if(add_path_address.dir_accept){
            new Rule_contollers_Main().set_start();
            info_path_field.setText(Paths_Main_File.path_final_out);
            buttonadPath.setStyle("-fx-background-color:green");
        }
        try {
            IgnoredPlu.listIgnoredPly.clear();
            new TableViewIgnoredPlu(this).updateTable();
        }
        catch (Exception e){
            new Modal_Error().set_erroe_messege("Ошибка загрузки IGNORED PLU");
        }

    }

    public static void add_directory_path_DEFOLT(){
        // Папка статическая - Проверка производится в классе CheckDefoltFolder
        new Rule_contollers_Main().set_start();
        Rule_contollers_Main.main_controller.info_path_field.setText(Paths_Main_File.path_final_out);
        Rule_contollers_Main.main_controller.buttonadPath.setStyle("-fx-background-color:green");

        try {
            IgnoredPlu.listIgnoredPly.clear();
            new TableViewIgnoredPlu(Rule_contollers_Main.main_controller).updateTable();
        }
        catch (Exception e){
            new Modal_Error().set_erroe_messege("Ошибка загрузки IGNORED PLU");
        }

    }

    public void creat_kp_s_act(){

        info11.setText("Обработка....");

        Container_KP_Sklad.chekAutoSave();

        String x = pick_algorith_sklad.getSelectionModel().getSelectedItem();

        if(x.equals("Случайный") || x.equals("Стандарт")){Rule_contollers_Main.creating_Act_CP_Sklad(x);}

        if(x.equals("Расширенный")){open_Hand_AutoSettings();}

        try {
            new ExtendedOstatku().copy(); // Копирование остатков для просмотра подсчитанного в складе
            new SlideModalError().setMessage("AVR запомнил текущую сверку как складскую." + System.lineSeparator()
            +"Пользуйтесь вкладкой (DETAL)" + System.lineSeparator() +
                    "При выгрузке КП ФИН в excel");
        }
        catch (Exception e){
            new Modal_Error().set_erroe_messege("Ошибка при копировании списка склада");
        }
        buttonLoad.setVisible(true);
    }

    public void anakitiksfromDan(){

        if(!awrsos.isSelected()){
            ConsoleAVR.printlnn("Вкладка просмотр данных НЕ активна.");
        }

        LogicForAnalitik logicForAnalitik = new LogicForAnalitik();
        logicForAnalitik.start_option();


            Odds_level_One.clear_map();
            try { LogicForAnalitik.start_Analitics(); }
            catch (Exception e) { System.out.println(e.getMessage()); }

            accordion_TEST.getPanes().clear();
            accordion_TEST.getPanes().add(new Accordeon_Controller().createAccordeonOddsGroup());
            accordion_TEST.getPanes().add(Bundle_For_WRS.get_bundle_WRS_Pane());
            accordion_TEST.getPanes().add(BundleForNegativeCount.getBundleFromNegative());

            new SetStandartScale(this); // load StepList

    }

    public void go_analitiks(){

        // Запускаем только при нажатии вкладки
        /*
        Запуск и формирование вкладки аналитика
        Выгрузка в excel
        Выгрузка разница по группам
         */

        LogicForAnalitik logicForAnalitik = new LogicForAnalitik();
        logicForAnalitik.start_option();

        if(LogicForAnalitik.need_start) {

            Odds_level_One.clear_map();
            try { LogicForAnalitik.start_Analitics(); }
            catch (Exception e) { System.out.println(e.getMessage()); }

            accordion_TEST.getPanes().clear();
            accordion_TEST.getPanes().add(new Accordeon_Controller().createAccordeonOddsGroup());
            accordion_TEST.getPanes().add(Bundle_For_WRS.get_bundle_WRS_Pane());
            accordion_TEST.getPanes().add(BundleForNegativeCount.getBundleFromNegative());
        }
    }

    public void empty_method(){
        System.out.println(Ostatku.count_sklad_plu);
    }

    public void add_picture_from_podgotovka(){
        new AddPicutre().get_PathToPodgotovkaPicture();
    }

    public void setInfLastLoad(String temp){
        last_info.setText("Проведена ( Последний расчет)  : " + temp);
    }

    public void open_Hand_AutoSettings(){
        ControllerSkladHA.disable_settings();              // Включить кнопку и допуск к полю
        flow_panf_HA.getChildren().clear();
        flow_panf_HA.getChildren().add(new ControllerSkladHA().fpGetNew());
        main_pain.getSelectionModel().select(awrsos);
        mainPain2.getSelectionModel().select(4);
    }

    public void savekpAI(){
        new MetaHalfAutoSet().save("");
        Rule_contollers_Main.main_controller.main_pain.getSelectionModel().select(kp_sklad_tab);
        Rule_contollers_Main.main_controller.save_button_hakpSklad.setDisable(true);
        Rule_contollers_Main.main_controller.tab_Hand_auto.setDisable(true);
        //Rule_contollers_Main.start_Hand_Auto();
    }

    public void savekpAI2(){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("AVR MESSAGE");
        alert.setHeaderText("Предупреждение: Будет создан новый КП СКЛАД");
        Platform.runLater(()-> Rule_contollers_Main.main_controller.main_pain.getSelectionModel().select(kp_sklad_tab));
        alert.setContentText("Нажмите ОК, чтобы начать...");
        alert.showAndWait();

        Rule_contollers_Main.start_Hand_Auto();

    }



    public void saveDateInventory(){
        String temp = calendar_main.getEditor().getText();
        new DateIni().save(temp,Paths_Main_File.PATH_TO_DATE,DateIni.OPERATION_FROM_DATE);
    }

    public void saveMonthOrderDate(){
        String temp = calendar_order.getEditor().getText();
        new DateIni().save(temp,Paths_Main_File.PATH_TO_ORDER_DATE,DateIni.OPERATION_FROM_ORDER_DATE);
    }

    public void addNewPositionToOrgList(){
        AllOrganization.addEmptyOrg();
    }

    public void savePeople(){

        new PeopleComisson().save();
        try{
            ConsoleAVR.printlnn(organization_box.getSelectionModel().getSelectedItem().toString() + " WAS PICKED");
            new Repiat();
            //new Modal_Error().set_erroe_messege("Данные успешно внесены");

            buttonSave.setStyle("-fx-background-color:green");
            ExportInv15.getInformationX(); //Обновляет все параметры
        }
        catch (Exception e){
            new Modal_Error().set_erroe_messege("ЮЛ/Приказ не выбран! " + e.getMessage() +
                    System.lineSeparator()+System.lineSeparator()
            +"ДАННЫЕ НЕ СОХРАНЕНЫ, ВЕРНИТЕСЬ В ПОДГОТОВКУ");
            return;
        }
        ConsoleAVR.printlnn("AUTOSAVE...OK");
        //new SlideModalError().setMessage("Данные по ПИ внесены...");
    }

    public void savePeople2(){
        new PeopleComisson().save();
        new Modal_Error().set_erroe_messege("Данные успешно внесены" + System.lineSeparator()+"" +
                "Изменения вступят в силу после перезапуска.");
    }

    public void uploadNewInv15(){
        try { Inv15Field.startUpload(); }
        catch (Exception e){ System.out.println(e);}
    }

    public void addPositionFinKP(){

        // Add Some position to KP FIN From Box name
        System.out.println("Выбрано : " + BoxAnotherTMC.getNowPicked());

        int kf = new ParseField().getIntFromString(qfMinKpFin.getText());
        int oddsMax = new ParseField().getIntFromString(maxOdsKpFin.getText());
        boolean tempbool = control_p_block.isSelected();

        new AddPositionToTableAndList().addPosition(BoxAnotherTMC.getNowPicked(),kf,oddsMax,tempbool);
    }

    public void add_neprodam_path(){
        StartNeprodamParse.startUnzip();
    }

    public void addNewCrashesPLU(){
        CrashesPlu.listCrashes.add(new CrashesPlu("EMPTY".concat(GetRandom.getRandomNumber()),"EMPTY".concat(GetRandom.getRandomNumber())));
        CrashesPlu.setTableView();
    }

    public void savesrushesPLU(){
        new CrashesPlu().save("");
    }

    public void deletePos(){
        // Удаляет позицию из битых PLU
        CrashesPlu temp = table_crashes.getSelectionModel().getSelectedItem();
        new CrashesPlu().deletePosition(temp.getPlu(),temp.getDescription());
        new CrashesPlu().save("");
        CrashesPlu.setTableView();
    }

    public void crRaspuska(){
        new CreateRaspiska().creteRaspiska(Paths_Main_File.path_final_out);
    }

    public void crApplicORDER(){
        new AplicationforOrder();
    }

    public void changeS999Status(){
        BlockingClass.changeStatusS999(Paths_Main_File.PATH_BLOCK_S999, blockS999KPFIN);
    }

    public void blockNeprods (){
        BlockingClass.changeStatusS999(Paths_Main_File.PATH_BLOCK_ALCO, blockAlcInNeprod);
    }

    public void uploadNewSklad(){

        if(CheckZonHall.getcheck()){
            new Modal_Error().set_erroe_messege(CheckZonHall.getMessageError());
            return;
        }

        new Container_KP_Sklad().saving();
        // Выгрузка в XLSX
        KpppSKLADExport kpppSKLADExport = new KpppSKLADExport();
        kpppSKLADExport.startExport();
    }

    public void reopnet(){
        new ValueSlider().setValue(GraficAvrProd.currentGroup);
        new BundleResultGraph(2);
    }

    public void showmesNewShop(){
        AddNewMagazine addNewMagazine = new AddNewMagazine(number_shop.getText());
    }

    public void updownPane(){
        new BundleResultGraph(1);
    }

    public void closeProgram(){
        new CloseOperation();
    }

    public void startTest(){
        new PrintDialogPath();
    }

    public void tested(){
        new DecriptorBlock();
    }



}
