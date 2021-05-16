package exportXLSX.inv15i;

import dateClass.DateIni;
import exportXLSX.ExportExcel;
import info_page.smenaMOLPACKAGE.DescriptionSmenaMOL;
import info_page.smenaMOLPACKAGE.SmenaMOL;
import interfaces_all.GetInfo;
import inv15docpack.Inv15Field;
import numberShopPack.ShopDescription;
import numberShopPack.ShopNumber;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import organizationClass.AllOrganization;
import peopleComisson.PeopleComisson;

import java.io.IOException;
import java.util.LinkedList;

public class ExportInv15 extends ExportExcel implements GetInfo{

    private Cell cell;
    public static LinkedList<ExportInv15> listCell = new LinkedList<>();

    public void genereteallCellInv15(){

        listCell.clear();

        String typePU = DescriptionSmenaMOL.getTypePU();

        if(typePU.equals(DescriptionSmenaMOL.PLAN)) {
            ExportExcel exportInv15 = new ExportInv15();
            XSSFSheet sheet_fff = exportInv15.genereteSheet(ExportExcel.COMAND_TO_EXPORT_INV_15);
            InformationX informationX = getInformationX();
            ExtendedInformation extend = ExtendedInformation.genericNewInformation();


            Cell cell_O_1 = null;
            Cell cell_O_2 = null;
            Cell cell_O_3 = null;
            Cell cell_O_4 = null;
            Cell cell_R11_87 = null;
            Cell cell_48_60 = null;
            Cell cell_49_60 = null;
            Cell cell_5_0 = null;
            Cell cell_7_0 = null;
            Cell cell_9_0 = null;

            Cell cell_68_0 = null;


            Cell cell_money = null;
            Cell summa_fact_cell = null;
            Cell cell_money_3 = null;

            Cell summa_propisyu_fact = null;
            Cell propus_2 = null;
            Cell cell_46_49_izliwek = null;
            Cell cell_47_49_nedosta4a = null;


            // Копейки

            Cell cell_izliwek_kop = null;
            Cell cell_Nedosta4a_kop = null;

            Cell cell_FACT_KOP = null;
            Cell cell_FACT_KOP2 = null;
            Cell cell_FACT_KOP3 = null;

            Cell cell_REESTR_KOP = null;
            Cell cell_REESTR_KOP2 = null;


            cell_izliwek_kop = sheet_fff.getRow(46).getCell(92);
            cell_Nedosta4a_kop = sheet_fff.getRow(47).getCell(92);
            cell_FACT_KOP = sheet_fff.getRow(34).getCell(92);
            cell_FACT_KOP2 = sheet_fff.getRow(38).getCell(92);
            cell_FACT_KOP3 = sheet_fff.getRow(29).getCell(52);

            cell_REESTR_KOP = sheet_fff.getRow(40).getCell(92);
            cell_REESTR_KOP2 = sheet_fff.getRow(44).getCell(92);


            cell_izliwek_kop.setCellValue(extend.getIzliwek_kop());
            cell_Nedosta4a_kop.setCellValue(extend.getNedosta4a_kop());

            cell_FACT_KOP.setCellValue(extend.getFactKop());
            cell_FACT_KOP2.setCellValue(extend.getFactKop());
            cell_FACT_KOP3.setCellValue(extend.getFactKop());

            cell_REESTR_KOP.setCellValue(extend.getReestr_kop());
            cell_REESTR_KOP2.setCellValue(extend.getReestr_kop());


            //Заполняем второй ряд
            cell_O_1 = sheet_fff.getRow(12).getCell(87);
            cell_O_2 = sheet_fff.getRow(17).getCell(80);
            cell_O_3 = sheet_fff.getRow(19).getCell(59);  //Замена на месяц
            cell_O_4 = sheet_fff.getRow(61).getCell(5);   //Замена на месяц
            cell_R11_87 = sheet_fff.getRow(11).getCell(87);
            cell_48_60 = sheet_fff.getRow(48).getCell(60);
            cell_49_60 = sheet_fff.getRow(49).getCell(60);
            cell_5_0 = sheet_fff.getRow(5).getCell(0);
            cell_7_0 = sheet_fff.getRow(7).getCell(0);
            cell_9_0 = sheet_fff.getRow(9).getCell(0);
            cell_46_49_izliwek = sheet_fff.getRow(46).getCell(49);
            cell_47_49_nedosta4a = sheet_fff.getRow(47).getCell(49);


            cell_O_1.setCellValue(informationX.getDate_prikaz_x());
            cell_O_2.setCellValue(informationX.getDate_INVENT());
            cell_O_3.setCellValue(ReplaceDate.restart_date_month(informationX.getDate_INVENT()));
            cell_O_4.setCellValue(ReplaceDate.restart_date_month(informationX.getDate_INVENT()));
            cell_R11_87.setCellValue(informationX.getNumber_prikaz_x());
            cell_48_60.setCellValue(informationX.getPko());
            cell_49_60.setCellValue(informationX.getRko());
            cell_5_0.setCellValue(ReplaceDate.raplace_organization(informationX.getOrganizacuya()));
            cell_7_0.setCellValue(informationX.getStruktyrnoe_podrazdelenie());
            cell_9_0.setCellValue(informationX.getFullAdress());

            cell_money = sheet_fff.getRow(29).getCell(26);
            summa_fact_cell = sheet_fff.getRow(34).getCell(45);
            cell_money_3 = sheet_fff.getRow(40).getCell(38);

            summa_propisyu_fact = sheet_fff.getRow(36).getCell(0);
            propus_2 = sheet_fff.getRow(42).getCell(0);
            cell_68_0 = sheet_fff.getRow(68).getCell(0);

            cell_money.setCellValue(extend.getFactNumber());
            summa_fact_cell.setCellValue(extend.getFactNumber());
            cell_money_3.setCellValue(extend.getKassaNumber());
            summa_propisyu_fact.setCellValue(extend.getSummFactOnWords());
            propus_2.setCellValue(extend.getSummKassaWord());

            if (!extend.isHaveras()) {
                cell_46_49_izliwek.setCellValue("0");
                cell_47_49_nedosta4a.setCellValue("0");
                cell_68_0.setCellValue(extend.getResult());
            }

            if (extend.isHaveras()) {
                cell_46_49_izliwek.setCellValue(ExtendedInformation.raxhWord("IZ"));
                cell_47_49_nedosta4a.setCellValue(ExtendedInformation.raxhWord("NE"));
                cell_68_0.setCellValue(extend.getResult());
            }


            Cell r26c40 = null; // MOL
            r26c40 = sheet_fff.getRow(25).getCell(39);
            r26c40.setCellValue(PeopleComisson.dolMOL);
            Cell r26c83 = null; // FIO MOL
            r26c83 = sheet_fff.getRow(25).getCell(82);
            r26c83.setCellValue(PeopleComisson.MAN_MOL);
            Cell r60c37 = null; // MOL
            r60c37 = sheet_fff.getRow(59).getCell(36);
            r60c37.setCellValue(PeopleComisson.dolMOL);
            Cell r60c78 = null; // FIO MOL
            r60c78 = sheet_fff.getRow(59).getCell(77);
            r60c78.setCellValue(PeopleComisson.MAN_MOL);
            Cell r82c40 = null; // MOL
            r82c40 = sheet_fff.getRow(81).getCell(39);
            r82c40.setCellValue(PeopleComisson.dolMOL);
            Cell r82c79 = null; // FIO MOL
            r82c79 = sheet_fff.getRow(81).getCell(78);
            r82c79.setCellValue(PeopleComisson.MAN_MOL);


            Cell r53c32 = null; // DOL CHK1
            r53c32 = sheet_fff.getRow(52).getCell(31);
            r53c32.setCellValue(PeopleComisson.dol1);
            Cell r53c78 = null; // FIO CHK1
            r53c78 = sheet_fff.getRow(52).getCell(77);
            r53c78.setCellValue(PeopleComisson.MAN_PART_ONE);


            Cell r55c32 = null; // DOL CHK2
            r55c32 = sheet_fff.getRow(54).getCell(31);
            r55c32.setCellValue(PeopleComisson.dol2);
            Cell r55c78 = null; // FIO CHK2
            r55c78 = sheet_fff.getRow(54).getCell(77);
            r55c78.setCellValue(PeopleComisson.MAN_PART_TWO);


            Cell r57c32 = null; // DOL CHK1
            r57c32 = sheet_fff.getRow(56).getCell(31);
            r57c32.setCellValue(PeopleComisson.dol3);
            Cell r57c78 = null; // FIO CHK1
            r57c78 = sheet_fff.getRow(56).getCell(77);
            r57c78.setCellValue(PeopleComisson.MAN_PART_THREE);

            Cell revizor = null;
            revizor = sheet_fff.getRow(50).getCell(77);
            revizor.setCellValue(PeopleComisson.PEEK_MAN);

            Cell revizor2 = null;
            revizor2 = sheet_fff.getRow(50).getCell(31);
            revizor2.setCellValue("СКРП");

            listCell.add(new ExportInv15(revizor));
            listCell.add(new ExportInv15(revizor2));


            listCell.add(new ExportInv15(r26c40));
            listCell.add(new ExportInv15(r26c83));
            listCell.add(new ExportInv15(r60c37));
            listCell.add(new ExportInv15(r60c78));
            listCell.add(new ExportInv15(r82c40));
            listCell.add(new ExportInv15(r82c79));
            listCell.add(new ExportInv15(r53c32));
            listCell.add(new ExportInv15(r53c78));
            listCell.add(new ExportInv15(r55c32));
            listCell.add(new ExportInv15(r55c78));
            listCell.add(new ExportInv15(r57c32));
            listCell.add(new ExportInv15(r57c78));
            listCell.add(new ExportInv15(cell_O_1));
            listCell.add(new ExportInv15(cell_O_2));
            listCell.add(new ExportInv15(cell_O_3));
            listCell.add(new ExportInv15(cell_O_4));
            listCell.add(new ExportInv15(cell_R11_87));
            listCell.add(new ExportInv15(cell_48_60));
            listCell.add(new ExportInv15(cell_49_60));
            listCell.add(new ExportInv15(cell_5_0));
            listCell.add(new ExportInv15(cell_7_0));
            listCell.add(new ExportInv15(cell_9_0));
            listCell.add(new ExportInv15(cell_68_0));
            listCell.add(new ExportInv15(cell_money));
            listCell.add(new ExportInv15(summa_fact_cell));
            listCell.add(new ExportInv15(cell_money_3));
            listCell.add(new ExportInv15(summa_propisyu_fact));
            listCell.add(new ExportInv15(propus_2));
            listCell.add(new ExportInv15(cell_46_49_izliwek));
            listCell.add(new ExportInv15(cell_47_49_nedosta4a));

            //Добавить копейки

            listCell.add(new ExportInv15(cell_izliwek_kop));
            listCell.add(new ExportInv15(cell_Nedosta4a_kop));

            listCell.add(new ExportInv15(cell_FACT_KOP));
            listCell.add(new ExportInv15(cell_FACT_KOP2));
            listCell.add(new ExportInv15(cell_FACT_KOP3));

            listCell.add(new ExportInv15(cell_REESTR_KOP));
            listCell.add(new ExportInv15(cell_REESTR_KOP2));


            informationX = null;

            try {
                sheet_fff.getWorkbook().close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if(typePU.equals(DescriptionSmenaMOL.SMENAMOLString)){
            genereteCellInv15Smena();
        }

    }

    public void genereteCellInv15Smena(){

        listCell.clear();

        ExportExcel exportInv15 = new ExportInv15();
        XSSFSheet sheet_fff = exportInv15.genereteSheet(ExportExcel.COMAND_TO_EXPORT_INV_15);
        InformationX informationX = getInformationX();
        ExtendedInformation extend = ExtendedInformation.genericNewInformation();


        Cell cell_O_1 = null;
        Cell cell_O_2 = null;
        Cell cell_O_3 = null;
        Cell cell_O_4 = null;
        Cell cell_R11_87 = null;
        Cell cell_48_60 = null;
        Cell cell_49_60 = null;
        Cell cell_5_0 = null;
        Cell cell_7_0 = null;
        Cell cell_9_0 = null;

        Cell cell_68_0 = null;


        Cell cell_money = null;
        Cell summa_fact_cell = null;
        Cell cell_money_3 = null;

        Cell summa_propisyu_fact = null;
        Cell propus_2 = null;
        Cell cell_46_49_izliwek = null;
        Cell cell_47_49_nedosta4a = null;


        // Копейки

        Cell cell_izliwek_kop = null;
        Cell cell_Nedosta4a_kop = null;

        Cell cell_FACT_KOP = null;
        Cell cell_FACT_KOP2 = null;
        Cell cell_FACT_KOP3 = null;

        Cell cell_REESTR_KOP = null;
        Cell cell_REESTR_KOP2 = null;


        cell_izliwek_kop = sheet_fff.getRow(46).getCell(92);
        cell_Nedosta4a_kop = sheet_fff.getRow(47).getCell(92);
        cell_FACT_KOP = sheet_fff.getRow(34).getCell(92);
        cell_FACT_KOP2 = sheet_fff.getRow(38).getCell(92);
        cell_FACT_KOP3 = sheet_fff.getRow(29).getCell(52);

        cell_REESTR_KOP = sheet_fff.getRow(40).getCell(92);
        cell_REESTR_KOP2 = sheet_fff.getRow(44).getCell(92);


        cell_izliwek_kop.setCellValue(extend.getIzliwek_kop());
        cell_Nedosta4a_kop.setCellValue(extend.getNedosta4a_kop());

        cell_FACT_KOP.setCellValue(extend.getFactKop());
        cell_FACT_KOP2.setCellValue(extend.getFactKop());
        cell_FACT_KOP3.setCellValue(extend.getFactKop());

        cell_REESTR_KOP.setCellValue(extend.getReestr_kop());
        cell_REESTR_KOP2.setCellValue(extend.getReestr_kop());


        //Заполняем второй ряд
        cell_O_1 = sheet_fff.getRow(12).getCell(87);
        cell_O_2 = sheet_fff.getRow(17).getCell(80);
        cell_O_3 = sheet_fff.getRow(19).getCell(59);  //Замена на месяц
        cell_O_4 = sheet_fff.getRow(61).getCell(5);   //Замена на месяц
        cell_R11_87 = sheet_fff.getRow(11).getCell(87);
        cell_48_60 = sheet_fff.getRow(48).getCell(60);
        cell_49_60 = sheet_fff.getRow(49).getCell(60);
        cell_5_0 = sheet_fff.getRow(5).getCell(0);
        cell_7_0 = sheet_fff.getRow(7).getCell(0);
        cell_9_0 = sheet_fff.getRow(9).getCell(0);
        cell_46_49_izliwek = sheet_fff.getRow(46).getCell(49);
        cell_47_49_nedosta4a = sheet_fff.getRow(47).getCell(49);


        cell_O_1.setCellValue(informationX.getDate_prikaz_x());
        cell_O_2.setCellValue(informationX.getDate_INVENT());
        cell_O_3.setCellValue(ReplaceDate.restart_date_month(informationX.getDate_INVENT()));
        cell_O_4.setCellValue(ReplaceDate.restart_date_month(informationX.getDate_INVENT()));
        cell_R11_87.setCellValue(informationX.getNumber_prikaz_x());
        cell_48_60.setCellValue(informationX.getPko());
        cell_49_60.setCellValue(informationX.getRko());
        cell_5_0.setCellValue(ReplaceDate.raplace_organization(informationX.getOrganizacuya()));
        cell_7_0.setCellValue(informationX.getStruktyrnoe_podrazdelenie());
        cell_9_0.setCellValue(informationX.getFullAdress());

        cell_money = sheet_fff.getRow(29).getCell(26);
        summa_fact_cell = sheet_fff.getRow(34).getCell(45);
        cell_money_3 = sheet_fff.getRow(40).getCell(38);

        summa_propisyu_fact = sheet_fff.getRow(36).getCell(0);
        propus_2 = sheet_fff.getRow(42).getCell(0);
        cell_68_0 = sheet_fff.getRow(68).getCell(0);

        cell_money.setCellValue(extend.getFactNumber());
        summa_fact_cell.setCellValue(extend.getFactNumber());
        cell_money_3.setCellValue(extend.getKassaNumber());
        summa_propisyu_fact.setCellValue(extend.getSummFactOnWords());
        propus_2.setCellValue(extend.getSummKassaWord());

        if (!extend.isHaveras()) {
            cell_46_49_izliwek.setCellValue("0");
            cell_47_49_nedosta4a.setCellValue("0");
            cell_68_0.setCellValue(extend.getResult());
        }

        if (extend.isHaveras()) {
            cell_46_49_izliwek.setCellValue(ExtendedInformation.raxhWord("IZ"));
            cell_47_49_nedosta4a.setCellValue(ExtendedInformation.raxhWord("NE"));
            cell_68_0.setCellValue(extend.getResult());
        }


        Cell r26c40 = null; // MOL
        r26c40 = sheet_fff.getRow(25).getCell(39);
        r26c40.setCellValue(PeopleComisson.dolMOL);
        Cell r26c83 = null; // FIO MOL
        r26c83 = sheet_fff.getRow(25).getCell(82);
        r26c83.setCellValue(PeopleComisson.MAN_MOL);

        Cell dmPrynyal = null; // MOL
        dmPrynyal = sheet_fff.getRow(59).getCell(36);
        dmPrynyal.setCellValue(DescriptionSmenaMOL.getDolMOlSmenaMo());

        Cell podpusPrynyal = null; // FIO MOL
        podpusPrynyal = sheet_fff.getRow(59).getCell(77);
        podpusPrynyal.setCellValue(DescriptionSmenaMOL.getFIOPrynyalMOL());

        Cell r82c40 = null; // MOL
        r82c40 = sheet_fff.getRow(81).getCell(39);
        r82c40.setCellValue(DescriptionSmenaMOL.getDolMOlSmenaMo());

        Cell r82c79 = null; // FIO MOL
        r82c79 = sheet_fff.getRow(81).getCell(78);
        r82c79.setCellValue(DescriptionSmenaMOL.getFIOPrynyalMOL());


        Cell r53c32 = null; // DOL CHK1
        r53c32 = sheet_fff.getRow(52).getCell(31);
        r53c32.setCellValue(PeopleComisson.dol1);
        Cell r53c78 = null; // FIO CHK1
        r53c78 = sheet_fff.getRow(52).getCell(77);
        r53c78.setCellValue(PeopleComisson.MAN_PART_ONE);


        Cell r55c32 = null; // DOL CHK2
        r55c32 = sheet_fff.getRow(54).getCell(31);
        r55c32.setCellValue(PeopleComisson.dol2);
        Cell r55c78 = null; // FIO CHK2
        r55c78 = sheet_fff.getRow(54).getCell(77);
        r55c78.setCellValue(PeopleComisson.MAN_PART_TWO);


        Cell r57c32 = null; // DOL CHK1
        r57c32 = sheet_fff.getRow(56).getCell(31);
        r57c32.setCellValue(PeopleComisson.dol3);
        Cell r57c78 = null; // FIO CHK1
        r57c78 = sheet_fff.getRow(56).getCell(77);
        r57c78.setCellValue(PeopleComisson.MAN_PART_THREE);

        Cell revizor = null;
        revizor = sheet_fff.getRow(50).getCell(77);
        revizor.setCellValue(PeopleComisson.PEEK_MAN);

        Cell revizor2 = null;
        revizor2 = sheet_fff.getRow(50).getCell(31);
        revizor2.setCellValue("СКРП");

        listCell.add(new ExportInv15(revizor));
        listCell.add(new ExportInv15(revizor2));


        listCell.add(new ExportInv15(r26c40));
        listCell.add(new ExportInv15(r26c83));
        listCell.add(new ExportInv15(dmPrynyal));
        listCell.add(new ExportInv15(podpusPrynyal));
        listCell.add(new ExportInv15(r82c40));
        listCell.add(new ExportInv15(r82c79));
        listCell.add(new ExportInv15(r53c32));
        listCell.add(new ExportInv15(r53c78));
        listCell.add(new ExportInv15(r55c32));
        listCell.add(new ExportInv15(r55c78));
        listCell.add(new ExportInv15(r57c32));
        listCell.add(new ExportInv15(r57c78));
        listCell.add(new ExportInv15(cell_O_1));
        listCell.add(new ExportInv15(cell_O_2));
        listCell.add(new ExportInv15(cell_O_3));
        listCell.add(new ExportInv15(cell_O_4));
        listCell.add(new ExportInv15(cell_R11_87));
        listCell.add(new ExportInv15(cell_48_60));
        listCell.add(new ExportInv15(cell_49_60));
        listCell.add(new ExportInv15(cell_5_0));
        listCell.add(new ExportInv15(cell_7_0));
        listCell.add(new ExportInv15(cell_9_0));
        listCell.add(new ExportInv15(cell_68_0));
        listCell.add(new ExportInv15(cell_money));
        listCell.add(new ExportInv15(summa_fact_cell));
        listCell.add(new ExportInv15(cell_money_3));
        listCell.add(new ExportInv15(summa_propisyu_fact));
        listCell.add(new ExportInv15(propus_2));
        listCell.add(new ExportInv15(cell_46_49_izliwek));
        listCell.add(new ExportInv15(cell_47_49_nedosta4a));

        //Добавить копейки

        listCell.add(new ExportInv15(cell_izliwek_kop));
        listCell.add(new ExportInv15(cell_Nedosta4a_kop));

        listCell.add(new ExportInv15(cell_FACT_KOP));
        listCell.add(new ExportInv15(cell_FACT_KOP2));
        listCell.add(new ExportInv15(cell_FACT_KOP3));

        listCell.add(new ExportInv15(cell_REESTR_KOP));
        listCell.add(new ExportInv15(cell_REESTR_KOP2));


        informationX = null;

        try {
            sheet_fff.getWorkbook().close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static InformationX getInformationX(){

        String dateOrder =  DateIni.dateFromOrderMonth;
        String dateInvent1 = DateIni.dateFromPlanInventory;

        //String dateInvent2 = ReplaceDate.restart_date_month(DateIni.dateFromPlanInventory);
        // String dateInvent3 = ReplaceDate.restart_date_month(DateIni.dateFromPlanInventory);

        String factory = ReplaceDate.raplace_organization(AllOrganization.currentPickNamePrganization);
        String numberPrikaz = AllOrganization.currentPickOrderNumber;
        String nameObject = "";
        String fullAdress = "";

        String pko = Inv15Field.currentPrixko;
        String rko = Inv15Field.currentRko;


        for(ShopDescription sd : ShopDescription.listShopSheetOne){

            if(ShopNumber.currentNumberShop.equals(sd.getNumberShop())){
                nameObject = sd.getNumShopEx();
                fullAdress = sd.getMailIndex() +","+ sd.getAddres();
                break;
            }
        }

        InformationX ix = new InformationX(dateOrder,dateInvent1,numberPrikaz,pko,rko,factory,nameObject,fullAdress);

        /**
         * public InformationX(String date_prikaz_x, String date_INVENT, String number_prikaz_x, String pko,
         *                         String rko, String organizacuya, String struktyrnoe_podrazdelenie, String fullAdress) {
         */

        return ix;
    }

    public static LinkedList<Cell> getCellListInv15(){
        LinkedList<Cell> temp = new LinkedList<>();

        for(ExportInv15 obk : listCell){
            temp.add(obk.getCell());
        }

        return temp;
    }


    public ExportInv15(Cell cell) {
        this.cell = cell;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public ExportInv15() {

    }


    @Override
    public void havecall() {

    }

    public static String getAddress() {
        try {
            for (ShopDescription sd : ShopDescription.listShopSheetOne) {

                if (ShopNumber.currentNumberShop.equals(sd.getNumberShop())) {

                    return sd.getMailIndex() + "," + sd.getAddres();

                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "EMP";
    }


}
