package exportXLSX.exportODDSLIST;

import all_controllers.Rule_contollers_Main;
import decriptor.ConsoleAVR;
import exportXLSX.ExportExcel;
import exportXLSX.exportAppPrikaz.AplicationforOrder;
import exportXLSX.exportAppPrikaz.DateTomorrow;
import exportXLSX.inv15i.ExportInv15;
import exportXLSX.inv15i.InformationX;
import exportXLSX.inv15i.ReplaceDate;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import peopleComisson.PeopleComisson;

import java.util.LinkedList;



public class GetMeListForOrderApp extends AplicationforOrder {


    public static LinkedList<Cell> getMelistAppORD(AplicationforOrder examp){

        ConsoleAVR.printlnn(examp.isSmenaMol() + " --- > SMENA MOL");

        if(examp.isSmenaMol()){return getMelistAppORDSMENAMOL(examp);} // Смена мол

        // Обычная

        LinkedList<Cell> listCell = examp.getCellListOrd();
        ExportODDSList eno = new ExportODDSList();
        XSSFSheet sheet = eno.genereteSheet(ExportExcel.COMAND_TO_EXPORT_APP_ORDER);
        InformationX informationX = ExportInv15.getInformationX();

        Cell orderTemplate = sheet.getRow(6).getCell(7);
        Cell dateOrder = sheet.getRow(7).getCell(7);

        Cell scrp = sheet.getRow(12).getCell(3);
        Cell chk1 = sheet.getRow(15).getCell(3);
        Cell chk2 = sheet.getRow(17).getCell(3);
        Cell chk3 = sheet.getRow(19).getCell(3);
        Cell mol1 = sheet.getRow(22).getCell(3);

        Cell scrpFIO = sheet.getRow(12).getCell(6);
        Cell chk1FIO = sheet.getRow(15).getCell(6);
        Cell chk2FIO = sheet.getRow(17).getCell(6);
        Cell chk3FIO = sheet.getRow(19).getCell(6);
        Cell mol1FIO = sheet.getRow(22).getCell(6);

        Cell podrazdelenie = sheet.getRow(26).getCell(3);
        Cell extandPORD = sheet.getRow(27).getCell(3);

        Cell reazon = sheet.getRow(28).getCell(4);
        Cell dateStart = sheet.getRow(30).getCell(5);
        Cell dateEnd = sheet.getRow(31).getCell(5);


        orderTemplate.setCellValue(informationX.getNumber_prikaz_x());
        dateOrder.setCellValue(dateWithTree(informationX.getDate_prikaz_x()) + " г.");
        scrp.setCellValue("СКРП");
        chk1.setCellValue(PeopleComisson.dol1);
        chk2.setCellValue(PeopleComisson.dol2);
        chk3.setCellValue(PeopleComisson.dol3);
        mol1.setCellValue(PeopleComisson.dolMOL);

        scrpFIO.setCellValue(PeopleComisson.PEEK_MAN);
        chk1FIO.setCellValue(PeopleComisson.MAN_PART_ONE);
        chk2FIO.setCellValue(PeopleComisson.MAN_PART_TWO);
        chk3FIO.setCellValue(PeopleComisson.MAN_PART_THREE);
        mol1FIO.setCellValue(PeopleComisson.MAN_MOL);

        String resAdrAndPodr = getMeResultOrgAndAdress(informationX);
        if(resAdrAndPodr.length()>60){
            int index = knownIndexFromString(resAdrAndPodr);
            podrazdelenie.setCellValue(resAdrAndPodr.substring(0,index));
            extandPORD.setCellValue(resAdrAndPodr.substring(index));
            listCell.add(extandPORD);
        }
        else { podrazdelenie.setCellValue(informationX.getOrganizacuya()
                + "," + informationX.getFullAdress());
                extandPORD.setCellValue(" ");listCell.add(extandPORD);
        }


        reazon.setCellValue(examp.wordReazon());

        dateStart.setCellValue(ReplaceDate.restart_date_month(informationX.getDate_INVENT()));
        dateEnd.setCellValue(replaceDateApp(informationX.getDate_INVENT()));


        listCell.add(orderTemplate);
        listCell.add(dateOrder);
        listCell.add(scrp);
        listCell.add(chk1);
        listCell.add(chk2);
        listCell.add(chk3);
        listCell.add(scrpFIO);
        listCell.add(chk1FIO);
        listCell.add(chk2FIO);
        listCell.add(chk3FIO);
        listCell.add(podrazdelenie);
        listCell.add(reazon);
        listCell.add(dateStart);
        listCell.add(dateEnd);
        listCell.add(mol1);
        listCell.add(mol1FIO);
        informationX = null;
        eno = null;

        return listCell;
    }

    public static LinkedList<Cell> getMelistAppORDSMENAMOL(AplicationforOrder examp){

        ConsoleAVR.printlnn("Сработал режим смены мол для приложения к приказу");

        LinkedList<Cell> listCell = examp.getCellListOrd();
        ExportODDSList eno = new ExportODDSList();
        XSSFSheet sheet = eno.genereteSheet(ExportExcel.COMAND_TO_EXPORT_APP_ORDERSML);
        InformationX informationX = ExportInv15.getInformationX();

        Cell orderTemplate = sheet.getRow(6).getCell(7);
        Cell dateOrder = sheet.getRow(7).getCell(7);

        Cell scrp = sheet.getRow(12).getCell(3);
        Cell chk1 = sheet.getRow(15).getCell(3);
        Cell chk2 = sheet.getRow(17).getCell(3);
        Cell chk3 = sheet.getRow(19).getCell(3);
        Cell mol1 = sheet.getRow(22).getCell(3);
        Cell mol2 = sheet.getRow(24).getCell(3);

        Cell scrpFIO = sheet.getRow(12).getCell(6);
        Cell chk1FIO = sheet.getRow(15).getCell(6);
        Cell chk2FIO = sheet.getRow(17).getCell(6);
        Cell chk3FIO = sheet.getRow(19).getCell(6);
        Cell mol1FIO = sheet.getRow(22).getCell(6);
        Cell mol2Fio = sheet.getRow(24).getCell(6);

        Cell podrazdelenie = sheet.getRow(27).getCell(3);
        Cell extandPORD = sheet.getRow(28).getCell(3);
        Cell reazon = sheet.getRow(29).getCell(4);

        Cell dateStart = sheet.getRow(31).getCell(5);
        Cell dateEnd = sheet.getRow(32).getCell(5);


        orderTemplate.setCellValue(informationX.getNumber_prikaz_x());
        dateOrder.setCellValue(dateWithTree(informationX.getDate_prikaz_x()) + " г.");
        scrp.setCellValue("СКРП");
        chk1.setCellValue(PeopleComisson.dol1);
        chk2.setCellValue(PeopleComisson.dol2);
        chk3.setCellValue(PeopleComisson.dol3);
        mol1.setCellValue(PeopleComisson.dolMOL);
        mol2.setCellValue(getMe2Mol());

        scrpFIO.setCellValue(PeopleComisson.PEEK_MAN);
        chk1FIO.setCellValue(PeopleComisson.MAN_PART_ONE);
        chk2FIO.setCellValue(PeopleComisson.MAN_PART_TWO);
        chk3FIO.setCellValue(PeopleComisson.MAN_PART_THREE);
        mol1FIO.setCellValue(PeopleComisson.MAN_MOL);
        mol2Fio.setCellValue(getMe2MolFIO());

        String resAdrAndPodr = getMeResultOrgAndAdress(informationX);
        if(resAdrAndPodr.length()>60){
            int index = knownIndexFromString(resAdrAndPodr);
            podrazdelenie.setCellValue(resAdrAndPodr.substring(0,index));
            extandPORD.setCellValue(resAdrAndPodr.substring(index));
            listCell.add(extandPORD);
        }
        else { podrazdelenie.setCellValue(informationX.getOrganizacuya()
                + "," + informationX.getFullAdress());
            extandPORD.setCellValue(" ");listCell.add(extandPORD);
        }




        reazon.setCellValue(examp.wordReazon());
        dateStart.setCellValue(ReplaceDate.restart_date_month(informationX.getDate_INVENT()));
        dateEnd.setCellValue(replaceDateApp(informationX.getDate_INVENT()));


        listCell.add(orderTemplate);
        listCell.add(dateOrder);
        listCell.add(scrp);
        listCell.add(chk1);
        listCell.add(chk2);
        listCell.add(chk3);
        listCell.add(scrpFIO);
        listCell.add(chk1FIO);
        listCell.add(chk2FIO);
        listCell.add(chk3FIO);
        listCell.add(podrazdelenie);
        listCell.add(reazon);
        listCell.add(dateStart);
        listCell.add(dateEnd);
        listCell.add(mol1);
        listCell.add(mol1FIO);
        listCell.add(mol2);
        listCell.add(mol2Fio);
        informationX = null;
        eno = null;

        return listCell;
    }


    public static String dateWithTree(String date){

        try {
            String temp = ReplaceDate.restart_date_month(date);
            System.out.println(temp);
            String[] xzc = temp.split(" ");

            return "«"+ xzc[0] +"»" + " " +xzc[1] + " " + xzc[2];
        }
        catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }

        return date;
    }


    public static String replaceDateApp(String startDate){
        String result = new DateTomorrow().getMeDateTomorrow(startDate);
        return ReplaceDate.restart_date_month(result);
    }

    public static String getMe2Mol(){
        String s = Rule_contollers_Main.main_controller.dolMOLfield.getText();
        return s;
    }

    public static String getMe2MolFIO(){
        String s = Rule_contollers_Main.main_controller.fieldSMENAMOL.getText();
        return s;
    }

    public static String getMeResultOrgAndAdress(InformationX informationX){
        String z = informationX.getOrganizacuya() + "," + informationX.getFullAdress();
        return z;
    }

    public static int knownIndexFromString(String s){
        // Ищем третью запятую, это будет индекс
        int count = 0;
        for (int i = 0 ; i < s.length(); i++){
            if(s.charAt(i)==','){count++;}
            if(count==3){return i+1;}
        }
        return 50;
    }


}
