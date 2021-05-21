package exportXLSX.finKP;

import all_controllers.logicAnalitic.Bundle_For_WRS;
import all_paths.Paths_Main_File;
import dateClass.DateIni;
import error_package.Modal_Error;
import exportXLSX.ExportExcel;
import exportXLSX.inv15i.InformationX;
import exportXLSX.kpSkladXlsX.KpppSKLADExport;
import fin_kp_AI.AddPositionToTableAndList;
import info_page.smenaMOLPACKAGE.DescriptionSmenaMOL;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import peopleComisson.PeopleComisson;
import sample.Main;
import warehouse_plu.ExtendedOstatku;
import warehouse_plu.Ostatku;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

import static exportXLSX.inv15i.ExportInv15.getInformationX;
import static fin_kp_AI.KpFinObjects.list_fin_kp;

public class ExportKPPFIN extends ExportExcel {

    public static LinkedList<Cell> cellListFinKP = new LinkedList<>();
    public static LinkedList<Cell> cellListExFin = new LinkedList<>();

    public static  int interarion = 0;               // Переменная для итерации

    public static int rowCount = 26;                 //Счетчик ROW

    public static final int END_ITERATION = 76;      // Конец итераций


    public void createCheetCellKPSKLAD() throws Exception{

        if(list_fin_kp.size()>50){new Modal_Error().set_erroe_messege("ОТКАЗ СОХРАНЕНИЯ ПРЕВЫШЕН ЛИМИТ в 50 PLU");return;}
        cellListFinKP.clear();
        interarion = 0;
        rowCount = 26;

        KpppSKLADExport kpppSKLADExport = new KpppSKLADExport();
        XSSFSheet sheet = kpppSKLADExport.genereteSheet(ExportExcel.COMAND_TO_EXPORT_KP_FIN);
        InformationX informationX = getInformationX();
        List<String> xxx;

        Row row = null;
        Row row_fio0 = null;
        Row row_fio11 = null;
        Row row_fio22 = null;
        Row row_fio33 = null;
        Row row_org1 = null;
        Row row_adress_mag1 = null;
        Row date_pu1 = null;
        Row fio_padej = null;

        Row final_result = null;
        Row nedosta4a_sijki = null;
        Row izliwek_sijku = null;
        Row raznica_smoke = null;





        int i = 25;

        row_fio0 = sheet.getRow(12);
        Cell cell_fio = row_fio0.getCell(1);
        cell_fio.setCellValue("СКРП, " + PeopleComisson.PEEK_MAN);

        row_fio11 = sheet.getRow(14);
        row_fio22 = sheet.getRow(15);
        row_fio33 = sheet.getRow(16);
        row_org1 = sheet.getRow(0);
        row_adress_mag1 = sheet.getRow(6);
        date_pu1 = sheet.getRow(8);
        fio_padej = sheet.getRow(19);

        final_result = sheet.getRow(86);
        nedosta4a_sijki = sheet.getRow(88);
        izliwek_sijku = sheet.getRow(88);
        raznica_smoke = sheet.getRow(88);

        Cell cell_fio1 = row_fio11.getCell(1);
        Cell cell_fio2 = row_fio22.getCell(1);
        Cell cell_fio3 = row_fio33.getCell(1);
        Cell cell_org = row_org1.getCell(1);
        Cell cell_mag = row_adress_mag1.getCell(1);
        Cell cell_date_pu = date_pu1.getCell(2);
        Cell cell_padej = fio_padej.getCell(2);

        Cell result = final_result.getCell(2);
        Cell minus_sig = nedosta4a_sijki.getCell(3);
        Cell plus_sig = izliwek_sijku.getCell(2);
        Cell cmoke = raznica_smoke.getCell(4);


        cell_fio1.setCellValue(PeopleComisson.MAN_PART_ONE);
        cell_fio2.setCellValue(PeopleComisson.MAN_PART_TWO);
        cell_fio3.setCellValue(PeopleComisson.MAN_PART_THREE);
        cell_org.setCellValue(informationX.getOrganizacuya());
        cell_mag.setCellValue(informationX.getStruktyrnoe_podrazdelenie() + "," + informationX.getFullAdress());
        cell_date_pu.setCellValue(DateIni.dateFromPlanInventory);
        cell_padej.setCellValue("СКРП, " + PeopleComisson.PADEJ_SKRP);

        cellListFinKP.add(cell_fio);
        cellListFinKP.add(cell_fio1);
        cellListFinKP.add(cell_fio2);
        cellListFinKP.add(cell_fio3);
        cellListFinKP.add(cell_org);
        cellListFinKP.add(cell_mag);
        cellListFinKP.add(cell_date_pu);
        cellListFinKP.add(cell_padej);

        chekAllposition(); // Получение финальной суммы сигареты + результат ПИ



        result.setCellValue(Bundle_For_WRS.resultOnString());
        minus_sig.setCellValue(Bundle_For_WRS.cigarettesShortage);
        plus_sig.setCellValue(Bundle_For_WRS.cigarettesSURPLUS);
        cmoke.setCellValue(Bundle_For_WRS.resultOnSmoke());

        cellListFinKP.add(result);
        cellListFinKP.add(minus_sig);
        cellListFinKP.add(plus_sig);
        cellListFinKP.add(cmoke);




        for (Ostatku target : AddPositionToTableAndList.alllist) {
            if (i >= 76) {
                break;
            }

            row = sheet.getRow(i);
            Cell plu = row.getCell(0);
            Cell name = row.getCell(1);
            Cell qFIN = row.getCell(2);
            Cell qf2 = row.getCell(3);
            Cell zone = row.getCell(3);
            Cell raz_po_kp = row.getCell(4);

            try {

                plu.setCellValue(target.plu); interarion++;
                name.setCellValue(target.getName());interarion++;
                qFIN.setCellValue(target.qfinal);interarion++;
                zone.setCellValue(target.gates.toString());interarion++;

                //Если есть расхождения по КП
                if(target.controlChek.equals("EMP")){qf2.setCellValue(target.qfinal);interarion++;}

                if(!target.controlChek.equals("EMP")){
                    qf2.setCellValue(target.controlChek);interarion++;
                    raz_po_kp.setCellValue(getStringCountOfEmp(target.controlChek,target.qfinal));
                    cellListFinKP.add(raz_po_kp);

                }
                cellListFinKP.add(plu);
                cellListFinKP.add(name);
                cellListFinKP.add(qFIN);
                cellListFinKP.add(zone);
                cellListFinKP.add(qf2);
                i++;

            } catch (Exception e) {
                new Modal_Error().set_erroe_messege(" Ошибка в заполнении " + e + " В цикле заполнения ячеек  = " + interarion);
                e.printStackTrace();
                System.out.println(" Ошибка в заполнении " + e + " В цикле заполнения ячеек  = " + interarion);
                System.exit(1);
            }

        }

        Cell dmSdalDol = null;
        dmSdalDol = sheet.getRow(76).getCell(2);
        dmSdalDol.setCellValue(PeopleComisson.dolMOL);
        cellListFinKP.add(dmSdalDol);

        Cell dmSdalFio = null;
        dmSdalFio = sheet.getRow(76).getCell(4);
        dmSdalFio.setCellValue(PeopleComisson.MAN_MOL);
        cellListFinKP.add(dmSdalFio);

        Cell infoSRKP = null;
        infoSRKP = sheet.getRow(78).getCell(4);
        infoSRKP.setCellValue(PeopleComisson.PEEK_MAN);
        cellListFinKP.add(infoSRKP);

        Cell d1 = null;
        Cell d2 = null;
        Cell d3 = null;

        Cell fio1 = null;
        Cell fio2 = null;
        Cell fio3 = null;

        d1 = sheet.getRow(80).getCell(2);
        d2 = sheet.getRow(82).getCell(2);
        d3 = sheet.getRow(84).getCell(2);
        fio1 = sheet.getRow(80).getCell(4);
        fio2 = sheet.getRow(82).getCell(4);
        fio3 = sheet.getRow(84).getCell(4);

        d1.setCellValue(PeopleComisson.dol1);
        d2.setCellValue(PeopleComisson.dol2);
        d3.setCellValue(PeopleComisson.dol3);
        fio1.setCellValue(PeopleComisson.MAN_PART_ONE);
        fio2.setCellValue(PeopleComisson.MAN_PART_TWO);
        fio3.setCellValue(PeopleComisson.MAN_PART_THREE);

        cellListFinKP.add(d1);
        cellListFinKP.add(d2);
        cellListFinKP.add(d3);
        cellListFinKP.add(fio1);
        cellListFinKP.add(fio2);
        cellListFinKP.add(fio3);



        interarion = 0;


        try {
            sheet.getWorkbook().close();
            //xxx.clear();
            informationX = null;
            kpppSKLADExport = null;
        } catch (IOException e) {
            e.printStackTrace();
            new Modal_Error().set_erroe_messege("Ошибка закрытия потока");
        }



    }

    public void createEXTENDEDKPFIN() throws Exception{

        cellListExFin.clear();
        interarion = 0;
        rowCount = 1;

        ExportKPPFIN exportKPPFIN = new ExportKPPFIN();
        XSSFSheet sheet = exportKPPFIN.genereteSheet(ExportExcel.COMAND_TO_EXPORT_KP_FIN_EXTENDED);
        InformationX informationX = getInformationX();
        List<String> xxx;

        Row row = null;
        Row qfRow = null;





        int i = 1;



        for (Ostatku target : AddPositionToTableAndList.alllist) {
            if (i >= 150) {
                break;
            }

            System.out.println(target.plu);

            row = sheet.getRow(i);
            qfRow = sheet.getRow(i+1);
            Cell plu = row.getCell(0);
            Cell name = row.getCell(1);
            Cell qFIN = row.getCell(2);

            Cell zone = qfRow.getCell(2);

            //Cell qy4et = row.getCell(4);

            String tempQfin = ExtendedOstatku.getQfin(target.plu);
            String skladZone = ExtendedOstatku.getAllSkladZones(target.plu);

            Cell tempQF = row.getCell(3);
            Cell tempAllSkladZone = qfRow.getCell(3);



            try {

                plu.setCellValue(target.plu); interarion++;
                name.setCellValue(target.getName());interarion++;
                qFIN.setCellValue(target.qfinal);interarion++;
                zone.setCellValue(target.gates.toString());interarion++;
                //qy4et.setCellValue(String.valueOf(target.getQychetnoe()));
                tempQF.setCellValue(tempQfin);
                tempAllSkladZone.setCellValue(skladZone);

                cellListExFin.add(plu);
                cellListExFin.add(name);
                cellListExFin.add(qFIN);
                cellListExFin.add(zone);
                //cellListExFin.add(qy4et);
                cellListExFin.add(tempQF);
                cellListExFin.add(tempAllSkladZone);

                i+=4;

            } catch (Exception e) {
                new Modal_Error().set_erroe_messege(" Ошибка в заполнении " + e + " В цикле заполнения ячеек  = " + interarion);
                e.printStackTrace();
                System.out.println(" Ошибка в заполнении " + e + " В цикле заполнения ячеек  = " + interarion);
                System.exit(1);
            }

        }


        interarion = 0;



        try {
            sheet.getWorkbook().close();
            //xxx.clear();
            informationX = null;
            exportKPPFIN = null;
        } catch (IOException e) {
            e.printStackTrace();
            new Modal_Error().set_erroe_messege("Ошибка закрытия потока");
        }



    }

    public void createCellSmenaMolFIN() throws Exception{
        if(list_fin_kp.size()>50){new Modal_Error().set_erroe_messege("ОТКАЗ СОХРАНЕНИЯ ПРЕВЫШЕН ЛИМИТ в 50 PLU");return;}
        cellListFinKP.clear();
        interarion = 0;
        rowCount = 26;

        KpppSKLADExport kpppSKLADExport = new KpppSKLADExport();
        XSSFSheet sheet = kpppSKLADExport.genereteSheet(ExportExcel.COMAND_TO_EXPORT_KP_FINSMENA);
        InformationX informationX = getInformationX();
        List<String> xxx;

        Row row = null;
        Row row_fio0 = null;
        Row row_fio11 = null;
        Row row_fio22 = null;
        Row row_fio33 = null;
        Row row_org1 = null;
        Row row_adress_mag1 = null;
        Row date_pu1 = null;
        Row fio_padej = null;

        Row final_result = null;
        Row nedosta4a_sijki = null;
        Row izliwek_sijku = null;
        Row raznica_smoke = null;





        int i = 25;

        row_fio0 = sheet.getRow(12);
        Cell cell_fio = row_fio0.getCell(1);
        cell_fio.setCellValue("СКРП, " + PeopleComisson.PEEK_MAN);

        row_fio11 = sheet.getRow(14);
        row_fio22 = sheet.getRow(15);
        row_fio33 = sheet.getRow(16);
        row_org1 = sheet.getRow(0);
        row_adress_mag1 = sheet.getRow(6);
        date_pu1 = sheet.getRow(8);
        fio_padej = sheet.getRow(19);

        final_result = sheet.getRow(88);
        nedosta4a_sijki = sheet.getRow(90);
        izliwek_sijku = sheet.getRow(90);
        raznica_smoke = sheet.getRow(90);

        Cell cell_fio1 = row_fio11.getCell(1);
        Cell cell_fio2 = row_fio22.getCell(1);
        Cell cell_fio3 = row_fio33.getCell(1);
        Cell cell_org = row_org1.getCell(1);
        Cell cell_mag = row_adress_mag1.getCell(1);
        Cell cell_date_pu = date_pu1.getCell(2);
        Cell cell_padej = fio_padej.getCell(2);

        Cell result = final_result.getCell(2);
        Cell minus_sig = nedosta4a_sijki.getCell(3);
        Cell plus_sig = izliwek_sijku.getCell(2);
        Cell cmoke = raznica_smoke.getCell(4);


        cell_fio1.setCellValue(PeopleComisson.MAN_PART_ONE);
        cell_fio2.setCellValue(PeopleComisson.MAN_PART_TWO);
        cell_fio3.setCellValue(PeopleComisson.MAN_PART_THREE);
        cell_org.setCellValue(informationX.getOrganizacuya());
        cell_mag.setCellValue(informationX.getStruktyrnoe_podrazdelenie() + "," + informationX.getFullAdress());
        cell_date_pu.setCellValue(DateIni.dateFromPlanInventory);
        cell_padej.setCellValue("СКРП, " + PeopleComisson.PADEJ_SKRP);

        cellListFinKP.add(cell_fio);
        cellListFinKP.add(cell_fio1);
        cellListFinKP.add(cell_fio2);
        cellListFinKP.add(cell_fio3);
        cellListFinKP.add(cell_org);
        cellListFinKP.add(cell_mag);
        cellListFinKP.add(cell_date_pu);
        cellListFinKP.add(cell_padej);

        chekAllposition(); // Получение финальной суммы сигареты + результат ПИ



        result.setCellValue(Bundle_For_WRS.resultOnString());
        minus_sig.setCellValue(Bundle_For_WRS.cigarettesShortage);
        plus_sig.setCellValue(Bundle_For_WRS.cigarettesSURPLUS);
        cmoke.setCellValue(Bundle_For_WRS.resultOnSmoke());

        cellListFinKP.add(result);
        cellListFinKP.add(minus_sig);
        cellListFinKP.add(plus_sig);
        cellListFinKP.add(cmoke);




        for (Ostatku target : AddPositionToTableAndList.alllist) {
            if (i >= 76) {
                break;
            }

            row = sheet.getRow(i);
            Cell plu = row.getCell(0);
            Cell name = row.getCell(1);
            Cell qFIN = row.getCell(2);
            Cell qf2 = row.getCell(3);
            Cell zone = row.getCell(3);
            Cell raz_po_kp = row.getCell(4);

            try {

                plu.setCellValue(target.plu); interarion++;
                name.setCellValue(target.getName());interarion++;
                qFIN.setCellValue(target.qfinal);interarion++;
                zone.setCellValue(target.gates.toString());interarion++;

                //Если есть расхождения по КП
                if(target.controlChek.equals("EMP")){qf2.setCellValue(target.qfinal);interarion++;}

                if(!target.controlChek.equals("EMP")){
                    qf2.setCellValue(target.controlChek);interarion++;
                    raz_po_kp.setCellValue(getStringCountOfEmp(target.controlChek,target.qfinal));
                    cellListFinKP.add(raz_po_kp);

                }
                cellListFinKP.add(plu);
                cellListFinKP.add(name);
                cellListFinKP.add(qFIN);
                cellListFinKP.add(zone);
                cellListFinKP.add(qf2);
                i++;

            } catch (Exception e) {
                new Modal_Error().set_erroe_messege(" Ошибка в заполнении " + e + " В цикле заполнения ячеек  = " + interarion);
                e.printStackTrace();
                System.out.println(" Ошибка в заполнении " + e + " В цикле заполнения ячеек  = " + interarion);
                System.exit(1);
            }

        }


        interarion = 0;


        Cell dmSdalDol = null;
        dmSdalDol = sheet.getRow(76).getCell(2);
        dmSdalDol.setCellValue(PeopleComisson.dolMOL);
        cellListFinKP.add(dmSdalDol);

        Cell dmSdalFio = null;
        dmSdalFio = sheet.getRow(76).getCell(4);
        dmSdalFio.setCellValue(PeopleComisson.MAN_MOL);
        cellListFinKP.add(dmSdalFio);

        Cell dmprYnDol = null;
        dmprYnDol = sheet.getRow(78).getCell(2);
        dmprYnDol.setCellValue(DescriptionSmenaMOL.getDolMOlSmenaMo());
        cellListFinKP.add(dmprYnDol);

        Cell dmPRNFIO = null;
        dmPRNFIO = sheet.getRow(78).getCell(4);
        dmPRNFIO.setCellValue(DescriptionSmenaMOL.getFIOPrynyalMOL());
        cellListFinKP.add(dmPRNFIO);

        Cell infoSdal = null;
        infoSdal = sheet.getRow(93).getCell(2);
        infoSdal.setCellValue(PeopleComisson.dolMOL);
        cellListFinKP.add(infoSdal);

        Cell infoPrn = null;
        infoPrn = sheet.getRow(95).getCell(2);
        infoPrn.setCellValue(DescriptionSmenaMOL.getDolMOlSmenaMo());
        cellListFinKP.add(infoPrn);

        Cell infoSRKP = null;
        infoSRKP = sheet.getRow(80).getCell(4);
        infoSRKP.setCellValue(PeopleComisson.PEEK_MAN);
        cellListFinKP.add(infoSRKP);


        Cell d1 = null;
        Cell d2 = null;
        Cell d3 = null;

        Cell fio1 = null;
        Cell fio2 = null;
        Cell fio3 = null;

        d1 = sheet.getRow(82).getCell(2);
        d2 = sheet.getRow(84).getCell(2);
        d3 = sheet.getRow(86).getCell(2);
        fio1 = sheet.getRow(82).getCell(4);
        fio2 = sheet.getRow(84).getCell(4);
        fio3 = sheet.getRow(86).getCell(4);

        d1.setCellValue(PeopleComisson.dol1);
        d2.setCellValue(PeopleComisson.dol2);
        d3.setCellValue(PeopleComisson.dol3);
        fio1.setCellValue(PeopleComisson.MAN_PART_ONE);
        fio2.setCellValue(PeopleComisson.MAN_PART_TWO);
        fio3.setCellValue(PeopleComisson.MAN_PART_THREE);

        cellListFinKP.add(d1);
        cellListFinKP.add(d2);
        cellListFinKP.add(d3);
        cellListFinKP.add(fio1);
        cellListFinKP.add(fio2);
        cellListFinKP.add(fio3);




        try {
            sheet.getWorkbook().close();
            //xxx.clear();
            informationX = null;
            kpppSKLADExport = null;
        } catch (IOException e) {
            e.printStackTrace();
            new Modal_Error().set_erroe_messege("Ошибка закрытия потока");
        }



    }

    public void startExport(){

        try{
            if(DescriptionSmenaMOL.getTypePU().equals(DescriptionSmenaMOL.SMENAMOLString)){
                createCellSmenaMolFIN();
                this.exportFile(ExportExcel.COMAND_TO_EXPORT_KP_FINSMENA, cellListFinKP, Paths_Main_File.path_final_out);
            }
            if(!DescriptionSmenaMOL.getTypePU().equals(DescriptionSmenaMOL.SMENAMOLString)){createCheetCellKPSKLAD();createEXTENDEDKPFIN();
                this.exportFile(ExportExcel.COMAND_TO_EXPORT_KP_FIN, cellListFinKP, Paths_Main_File.path_final_out);
            }


        }
        catch (Exception e){
            e.printStackTrace();
            new Modal_Error().set_erroe_messege("Возникла ошибка при сохранении КППП_ФИН. Отказ сохранения"+System.lineSeparator()+"" +
                    ""+e.getMessage());

        }



    }

    public static void chekAllposition(){
        new Bundle_For_WRS();

        for(Ostatku ddd : Main.classOstatku){
            Bundle_For_WRS.start_lit(ddd);
        }
    }


    public static String getStringCountOfEmp(String controlEMP, double qF){

        double cemp = 0;

        if(controlEMP.contains(",")){
            controlEMP = controlEMP.replace(',','.');
            cemp = Double.parseDouble(controlEMP);
            double result = cemp - qF;
            DecimalFormat df = new DecimalFormat("0.00##");
            String resultSTR = df.format(result);
            return  resultSTR;
        }

        cemp = Double.parseDouble(controlEMP);
        double result = cemp - qF;
        DecimalFormat df = new DecimalFormat("0.00##");
        String resultSTR = df.format(result);
        return  resultSTR;
    }


}
