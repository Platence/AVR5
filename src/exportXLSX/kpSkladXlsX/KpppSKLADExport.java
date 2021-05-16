package exportXLSX.kpSkladXlsX;

import all_paths.Paths_Main_File;
import dateClass.DateIni;

import error_package.Modal_Error;
import exportXLSX.ExportExcel;
import exportXLSX.inv15i.InformationX;
import info_page.smenaMOLPACKAGE.DescriptionSmenaMOL;
import javafx.application.Platform;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import peopleComisson.PeopleComisson;
import sample.Main;
import settingsOther.SettingsOtherHere;
import sklad_KP_AI.Container_KP_Sklad;
import warehouse_plu.Ostatku;

import java.io.IOException;
import java.util.*;

import static exportXLSX.inv15i.ExportInv15.getInformationX;


public class KpppSKLADExport extends ExportExcel {

    public static LinkedList<Cell> cellListKpSklad = new LinkedList<>();

    public static  int interarion = 0;               // Переменная для итерации

    public static int rowCount = 26;                 //Счетчик ROW

    public static final int END_ITERATION = 76;      // Конец итераций


    public void createCheetCellKPSKLAD() throws Exception{


        cellListKpSklad.clear();
        interarion = 0;
        rowCount = 26;

        KpppSKLADExport kpppSKLADExport = new KpppSKLADExport();
        XSSFSheet sheet = kpppSKLADExport.genereteSheet(ExportExcel.COMAND_TO_EXPORT_KP_SKLAD);
        InformationX informationX = getInformationX();

        Row row = null;         // Строка для списка PLU ( итерируется )

        Row row_fio = null;
        Row row_fio1 = null;
        Row row_fio2 = null;
        Row row_fio3 = null;
        Row row_org = null;
        Row row_adress_mag = null;
        Row date_pu = null;
        Row fio_padej = null;

        row_fio1 = sheet.getRow(14);
        row_fio2 = sheet.getRow(15);
        row_fio3 = sheet.getRow(16);
        row_org = sheet.getRow(0);
        row_adress_mag = sheet.getRow(6);
        date_pu = sheet.getRow(8);
        fio_padej = sheet.getRow(19);
        row_fio = sheet.getRow(12);


        Cell cell_fio = row_fio.getCell(2);
        Cell cell_fio1 = row_fio1.getCell(2);
        Cell cell_fio2 = row_fio2.getCell(2);
        Cell cell_fio3 = row_fio3.getCell(2);
        Cell cell_org = row_org.getCell(2);
        Cell cell_mag = row_adress_mag.getCell(2);
        Cell cell_date_pu = date_pu.getCell(3);
        Cell cell_padej = fio_padej.getCell(3);


        cell_fio.setCellValue("СКРП, " + PeopleComisson.PEEK_MAN);
        cell_fio1.setCellValue(PeopleComisson.MAN_PART_ONE);
        cell_fio2.setCellValue(PeopleComisson.MAN_PART_TWO);
        cell_fio3.setCellValue(PeopleComisson.MAN_PART_THREE);
        cell_org.setCellValue(informationX.getOrganizacuya());
        cell_mag.setCellValue(informationX.getStruktyrnoe_podrazdelenie() + "," + informationX.getFullAdress());
        cell_date_pu.setCellValue(DateIni.dateFromPlanInventory);
        cell_padej.setCellValue("СКРП, " + PeopleComisson.PADEJ_SKRP);



        cellListKpSklad.add(cell_fio);
        cellListKpSklad.add(cell_fio1);
        cellListKpSklad.add(cell_fio2);
        cellListKpSklad.add(cell_fio3);
        cellListKpSklad.add(cell_org);
        cellListKpSklad.add(cell_mag);
        cellListKpSklad.add(cell_date_pu);
        cellListKpSklad.add(cell_padej);

        Cell dmSdalDol = null;
        dmSdalDol = sheet.getRow(77).getCell(3);
        dmSdalDol.setCellValue(PeopleComisson.dolMOL);
        cellListKpSklad.add(dmSdalDol);

        Cell dmSdalFio = null;
        dmSdalFio = sheet.getRow(77).getCell(5);
        dmSdalFio.setCellValue(PeopleComisson.MAN_MOL);
        cellListKpSklad.add(dmSdalFio);


        Cell infoSRKP = null;
        infoSRKP = sheet.getRow(79).getCell(5);
        infoSRKP.setCellValue(PeopleComisson.PEEK_MAN);
        cellListKpSklad.add(infoSRKP);

        Cell infoSRKP2 = null;
        infoSRKP2 = sheet.getRow(79).getCell(3);
        infoSRKP2.setCellValue("СКРП");
        cellListKpSklad.add(infoSRKP2);


        Cell d1 = null;
        Cell d2 = null;
        Cell d3 = null;

        Cell fio1 = null;
        Cell fio2 = null;
        Cell fio3 = null;

        d1 = sheet.getRow(81).getCell(3);
        d2 = sheet.getRow(83).getCell(3);
        d3 = sheet.getRow(85).getCell(3);
        fio1 = sheet.getRow(81).getCell(5);
        fio2 = sheet.getRow(83).getCell(5);
        fio3 = sheet.getRow(85).getCell(5);

        d1.setCellValue(PeopleComisson.dol1);
        d2.setCellValue(PeopleComisson.dol2);
        d3.setCellValue(PeopleComisson.dol3);
        fio1.setCellValue(PeopleComisson.MAN_PART_ONE);
        fio2.setCellValue(PeopleComisson.MAN_PART_TWO);
        fio3.setCellValue(PeopleComisson.MAN_PART_THREE);

        cellListKpSklad.add(d1);
        cellListKpSklad.add(d2);
        cellListKpSklad.add(d3);
        cellListKpSklad.add(fio1);
        cellListKpSklad.add(fio2);
        cellListKpSklad.add(fio3);




        List<String> xxx = getSortList();
        if(SettingsOtherHere.sortBygroup){xxx=getSortListByGroup();}


        for (int x = 0 ; x < xxx.size(); x++) {  // Здесь идёт работа с переданным списком

            String xaz = xxx.get(x);

            for (Ostatku temp_new : Main.classOstatku) {
                if (rowCount >= END_ITERATION) {
                    break;
                }

                if (xaz.equals(temp_new.plu)) {
                    row = sheet.getRow(rowCount);
                    Cell plu = row.getCell(1);
                    Cell name = row.getCell(2);
                    Cell qFIN = row.getCell(4);
                    Cell qf2 = row.getCell(5);
                    Cell zone = row.getCell(3);

                    try {

                        plu.setCellValue(temp_new.plu); interarion++;
                        name.setCellValue(temp_new.name);interarion++;
                        qFIN.setCellValue(temp_new.qfinal);interarion++;
                        zone.setCellValue(temp_new.gates.toString());interarion++;
                        qf2.setCellValue(temp_new.qfinal);interarion++;
                        rowCount++;
                        cellListKpSklad.add(plu);
                        cellListKpSklad.add(name);
                        cellListKpSklad.add(qFIN);
                        cellListKpSklad.add(zone);
                        cellListKpSklad.add(qf2);
                        break;
                    }

                    catch (Exception e) {
                        new Modal_Error().set_erroe_messege(" Ошибка в заполнении " + e + " В цикле заполнения ячеек  = " + interarion);
                        e.printStackTrace();
                        System.out.println(" Ошибка в заполнении " + e + " В цикле заполнения ячеек  = " + interarion);
                        return;
                    }
                }
            }
        }


        interarion = 0;



        try {
            sheet.getWorkbook().close();
            xxx.clear();
            informationX = null;
            kpppSKLADExport = null;
        } catch (IOException e) {
            e.printStackTrace();
            new Modal_Error().set_erroe_messege("Ошибка закрытия потока");
        }



    }

    public void createCheetCellKPSKLADMOL() throws Exception{


        cellListKpSklad.clear();
        interarion = 0;
        rowCount = 26;

        KpppSKLADExport kpppSKLADExport = new KpppSKLADExport();
        XSSFSheet sheet = kpppSKLADExport.genereteSheet(ExportExcel.COMAND_TO_EXPORT_KP_SKLADMOL);
        InformationX informationX = getInformationX();

        Row row = null;         // Строка для списка PLU ( итерируется )

        Row row_fio = null;
        Row row_fio1 = null;
        Row row_fio2 = null;
        Row row_fio3 = null;
        Row row_org = null;
        Row row_adress_mag = null;
        Row date_pu = null;
        Row fio_padej = null;

        row_fio1 = sheet.getRow(14);
        row_fio2 = sheet.getRow(15);
        row_fio3 = sheet.getRow(16);
        row_org = sheet.getRow(0);
        row_adress_mag = sheet.getRow(6);
        date_pu = sheet.getRow(8);
        fio_padej = sheet.getRow(19);
        row_fio = sheet.getRow(12);


        Cell cell_fio = row_fio.getCell(2);
        Cell cell_fio1 = row_fio1.getCell(2);
        Cell cell_fio2 = row_fio2.getCell(2);
        Cell cell_fio3 = row_fio3.getCell(2);
        Cell cell_org = row_org.getCell(2);
        Cell cell_mag = row_adress_mag.getCell(2);
        Cell cell_date_pu = date_pu.getCell(3);
        Cell cell_padej = fio_padej.getCell(3);


        cell_fio.setCellValue("СКРП, " + PeopleComisson.PEEK_MAN);
        cell_fio1.setCellValue(PeopleComisson.MAN_PART_ONE);
        cell_fio2.setCellValue(PeopleComisson.MAN_PART_TWO);
        cell_fio3.setCellValue(PeopleComisson.MAN_PART_THREE);
        cell_org.setCellValue(informationX.getOrganizacuya());
        cell_mag.setCellValue(informationX.getStruktyrnoe_podrazdelenie() + "," + informationX.getFullAdress());
        cell_date_pu.setCellValue(DateIni.dateFromPlanInventory);
        cell_padej.setCellValue("СКРП, " + PeopleComisson.PADEJ_SKRP);

        cellListKpSklad.add(cell_fio);
        cellListKpSklad.add(cell_fio1);
        cellListKpSklad.add(cell_fio2);
        cellListKpSklad.add(cell_fio3);
        cellListKpSklad.add(cell_org);
        cellListKpSklad.add(cell_mag);
        cellListKpSklad.add(cell_date_pu);
        cellListKpSklad.add(cell_padej);



        List<String> xxx = getSortList();
        if(SettingsOtherHere.sortBygroup){xxx=getSortListByGroup();}


        for (int x = 0 ; x < xxx.size(); x++) {  // Здесь идёт работа с переданным списком

            String xaz = xxx.get(x);

            for (Ostatku temp_new : Main.classOstatku) {
                if (rowCount >= END_ITERATION) {
                    break;
                }

                if (xaz.equals(temp_new.plu)) {
                    row = sheet.getRow(rowCount);
                    Cell plu = row.getCell(1);
                    Cell name = row.getCell(2);
                    Cell qFIN = row.getCell(4);
                    Cell qf2 = row.getCell(5);
                    Cell zone = row.getCell(3);

                    try {

                        plu.setCellValue(temp_new.plu); interarion++;
                        name.setCellValue(temp_new.name);interarion++;
                        qFIN.setCellValue(temp_new.qfinal);interarion++;
                        zone.setCellValue(temp_new.gates.toString());interarion++;
                        qf2.setCellValue(temp_new.qfinal);interarion++;
                        rowCount++;
                        cellListKpSklad.add(plu);
                        cellListKpSklad.add(name);
                        cellListKpSklad.add(qFIN);
                        cellListKpSklad.add(zone);
                        cellListKpSklad.add(qf2);
                        break;
                    }

                    catch (Exception e) {
                        new Modal_Error().set_erroe_messege(" Ошибка в заполнении " + e + " В цикле заполнения ячеек  = " + interarion);
                        e.printStackTrace();
                        System.out.println(" Ошибка в заполнении " + e + " В цикле заполнения ячеек  = " + interarion);
                        return;
                    }
                }
            }
        }


        interarion = 0;

        Cell dmSdalDol = null;
        dmSdalDol = sheet.getRow(77).getCell(3);
        dmSdalDol.setCellValue(PeopleComisson.dolMOL);
        cellListKpSklad.add(dmSdalDol);

        Cell dmSdalFio = null;
        dmSdalFio = sheet.getRow(77).getCell(5);
        dmSdalFio.setCellValue(PeopleComisson.MAN_MOL);
        cellListKpSklad.add(dmSdalFio);

        Cell dmprnd = null;
        dmprnd = sheet.getRow(79).getCell(3);
        dmprnd.setCellValue(DescriptionSmenaMOL.getDolMOlSmenaMo());
        cellListKpSklad.add(dmprnd);

        Cell dmprnFio = null;
        dmprnFio = sheet.getRow(79).getCell(5);
        dmprnFio.setCellValue(DescriptionSmenaMOL.getFIOPrynyalMOL());
        cellListKpSklad.add(dmprnFio);



        Cell infoSRKP = null;
        infoSRKP = sheet.getRow(81).getCell(5);
        infoSRKP.setCellValue(PeopleComisson.PEEK_MAN);
        cellListKpSklad.add(infoSRKP);


        Cell d1 = null;
        Cell d2 = null;
        Cell d3 = null;

        Cell fio1 = null;
        Cell fio2 = null;
        Cell fio3 = null;

        d1 = sheet.getRow(83).getCell(3);
        d2 = sheet.getRow(85).getCell(3);
        d3 = sheet.getRow(87).getCell(3);
        fio1 = sheet.getRow(83).getCell(5);
        fio2 = sheet.getRow(85).getCell(5);
        fio3 = sheet.getRow(87).getCell(5);

        d1.setCellValue(PeopleComisson.dol1);
        d2.setCellValue(PeopleComisson.dol2);
        d3.setCellValue(PeopleComisson.dol3);
        fio1.setCellValue(PeopleComisson.MAN_PART_ONE);
        fio2.setCellValue(PeopleComisson.MAN_PART_TWO);
        fio3.setCellValue(PeopleComisson.MAN_PART_THREE);

        cellListKpSklad.add(d1);
        cellListKpSklad.add(d2);
        cellListKpSklad.add(d3);
        cellListKpSklad.add(fio1);
        cellListKpSklad.add(fio2);
        cellListKpSklad.add(fio3);






        try {
            sheet.getWorkbook().close();
            xxx.clear();
            informationX = null;
            kpppSKLADExport = null;
        } catch (IOException e) {
            e.printStackTrace();
            new Modal_Error().set_erroe_messege("Ошибка закрытия потока");
        }



    }

    public static List<String> getSortList(){


        LinkedList<String> xxx = null;
        xxx = new LinkedList<>();

        LinkedHashSet<String> all_group = new LinkedHashSet<>();

        for (Ostatku dd : Main.classOstatku) {
            for (String zz : Container_KP_Sklad.list_kp_sklad) {
                if (zz.equals(dd.plu)) {
                    all_group.add(dd.getYu3());
                }
            }
        }

        /**
         *  Сортировка списка по УИ3
         *  Сперва добавляются все группы
         *  Затем по каждой группе ищем совпадения между листами xxx и Ostatku
         */

        for (String kkk : all_group) {
            for (String ll : Container_KP_Sklad.list_kp_sklad) {
                for (Ostatku as : Main.classOstatku) {
                    if (as.plu.equals(ll)) {
                        if (kkk.equals(as.yu3)) {
                            System.out.println("Сортировка " + ll + " / " + kkk);
                            xxx.add(0, ll);
                        }
                    }
                }
            }
        }



        return xxx;
    }

    public static List<String> getSortListByGroup(){

        LinkedList<String> listStrings = new LinkedList<>();
        LinkedList<Ostatku> listOstatku = new LinkedList<>();

        /*
            Сначала добавим все объекты Ostatku из акта
         */

        for (String zz : Container_KP_Sklad.list_kp_sklad) {
            for(Ostatku z : Main.classOstatku){
                if(zz.equals(z.plu)){
                    listOstatku.add(z);
                }
            }
        }

       listOstatku.sort(Comparator.comparing(Ostatku::getArea_one_strok));

        for(int i = 0 ; i < listOstatku.size(); i++){
            listStrings.add(0,listOstatku.get(i).plu);
        }

        return listStrings;
    }

    public void startExport(){

        try{

            Platform.runLater(()->{

              if(!DescriptionSmenaMOL.getTypePU().equals(DescriptionSmenaMOL.SMENAMOLString)){

                try {
                    createCheetCellKPSKLAD();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                this.exportFile(ExportExcel.COMAND_TO_EXPORT_KP_SKLAD,cellListKpSklad, Paths_Main_File.path_final_out);
              }



                if(DescriptionSmenaMOL.getTypePU().equals(DescriptionSmenaMOL.SMENAMOLString)){
                    try {
                        createCheetCellKPSKLADMOL();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    this.exportFile(ExportExcel.COMAND_TO_EXPORT_KP_SKLADMOL,cellListKpSklad, Paths_Main_File.path_final_out);
                }

            });
        }


        catch (Exception e){
            e.printStackTrace();
            new Modal_Error().set_erroe_messege("Возникла ошибка при сохранении КППП_склад. Отказ сохранения"+System.lineSeparator()+"" +
                    ""+e.getMessage());

        }


    }

}
