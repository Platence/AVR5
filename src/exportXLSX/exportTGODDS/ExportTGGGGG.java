package exportXLSX.exportTGODDS;

import all_controllers.logicAnalitic.Bundle_For_WRS;
import all_controllers.logicAnalitic.OddsFromTg;
import exportXLSX.ExportExcel;
import exportXLSX.exportODDSLIST.ExportODDSList;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import warehouse_plu.AdvancedOstatku;

import java.util.LinkedList;

public class ExportTGGGGG extends ExportExcel {

    /**
     * Класс выгрузки разницы по группам
     */

    public static LinkedList<Cell> cellTGODDS = new LinkedList<>();

    public LinkedList<Cell> getCellODDSList() {
        return cellTGODDS;
    }

    public void genereteNewCell(OddsFromTg of) throws Exception{

        cellTGODDS.clear();

        ExportODDSList eno = new ExportODDSList();
        XSSFSheet sheet = eno.genereteSheet(ExportExcel.COMAND_TO_EXPORT_TG_ODDS);

        Row alco_ROW = sheet.getRow(4);
        Row konditerRow = sheet.getRow(5);
        Row bakaleyaRow = sheet.getRow(6);
        Row detskietovary_ROW = sheet.getRow(7);
        Row detskoepitanieROW = sheet.getRow(8);
        Row soputkaROW = sheet.getRow(9);
        Row kormROW = sheet.getRow(10);
        Row sokVodaPivoRow = sheet.getRow(11);
        Row zamorozkaRow = sheet.getRow(12);
        Row molokoROW = sheet.getRow(13);
        Row masoROW = sheet.getRow(14);
        Row odejdaROW = sheet.getRow(15);
        Row specRow = sheet.getRow(16);
        Row intAutROW = sheet.getRow(17);
        Row media_ROW = sheet.getRow(18);
        Row frov_row = sheet.getRow(19);

        Cell alc = alco_ROW.getCell(pulseOrMinus(of.getAlckogol()));
        alc.setCellValue((int)of.getAlckogol());
        cellTGODDS.add(alc);

        Cell konditer = konditerRow.getCell(pulseOrMinus(of.getKonditerka()));
        konditer.setCellValue((int)of.getKonditerka());
        cellTGODDS.add(konditer);

        Cell bakaleya = bakaleyaRow.getCell(pulseOrMinus(of.getBakaleya()));
        bakaleya.setCellValue((int)of.getBakaleya());
        cellTGODDS.add(bakaleya);

        Cell detstov = detskietovary_ROW.getCell(pulseOrMinus(of.getKids_tov()));
        detstov.setCellValue((int)of.getKids_tov());
        cellTGODDS.add(detstov);

        Cell detspit = detskoepitanieROW.getCell(pulseOrMinus(of.getKids_pit()));
        detspit.setCellValue((int)of.getKids_pit());
        cellTGODDS.add(detspit);

        Cell soputka = soputkaROW.getCell(pulseOrMinus(of.getSoputka()));
        soputka.setCellValue((int)of.getSoputka());
        cellTGODDS.add(soputka);

        Cell kormCell= kormROW.getCell(pulseOrMinus(of.getKorm()));
        kormCell.setCellValue((int)of.getKorm());
        cellTGODDS.add(kormCell);

        Cell sokVoda = sokVodaPivoRow.getCell(pulseOrMinus(of.getSok_voda_Pivo()));
        sokVoda.setCellValue((int)of.getSok_voda_Pivo());
        cellTGODDS.add(sokVoda);

        Cell zamorozka = zamorozkaRow.getCell(pulseOrMinus(of.getZamorozka()));
        zamorozka.setCellValue((int)of.getZamorozka());
        cellTGODDS.add(zamorozka);

        Cell moloko = molokoROW.getCell(pulseOrMinus(of.getMoloko()));
        moloko.setCellValue((int)of.getMoloko());
        cellTGODDS.add(moloko);

        Cell maso = masoROW.getCell(pulseOrMinus(of.getColbasa_myaso()));
        maso.setCellValue((int)of.getColbasa_myaso());
        cellTGODDS.add(maso);

        Cell odejds = odejdaROW.getCell(pulseOrMinus(of.getOdejda()));
        odejds.setCellValue((int)of.getOdejda());
        cellTGODDS.add(odejds);

        Cell spec = specRow.getCell(pulseOrMinus(of.getSpec_akc()));
        spec.setCellValue((int)of.getSpec_akc());
        cellTGODDS.add(spec);

        Cell inaut = intAutROW.getCell(pulseOrMinus(of.getIn_aut()));
        inaut.setCellValue((int)of.getIn_aut());
        cellTGODDS.add(inaut);

        Cell media = media_ROW.getCell(pulseOrMinus(of.getMedia()));
        media.setCellValue((int)of.getMedia());
        cellTGODDS.add(media);

        Cell frov = frov_row.getCell(pulseOrMinus(of.getFrov()));
        frov.setCellValue((int)of.getFrov());
        cellTGODDS.add(frov);


        Row rowAnotherSumm = sheet.getRow(24);
        Cell cellAnotherSumm = rowAnotherSumm.getCell(2);
        cellAnotherSumm.setCellValue(of.getAnother());

        Cell cellAnotherLIST = rowAnotherSumm.getCell(3);
        cellAnotherLIST.setCellValue(of.anotherName.toString());

        Row sigarets = sheet.getRow(3);
        Cell cellSigs = sigarets.getCell(pulseOrMinus(Bundle_For_WRS.sigarets));
        cellSigs.setCellValue(Bundle_For_WRS.sigarets);

        cellTGODDS.add(cellAnotherSumm);
        cellTGODDS.add(cellAnotherLIST);
        cellTGODDS.add(cellSigs);

    }


    public static int pulseOrMinus(double summ){

        return summ >= 0 ? 2 : 3;
        // Излишек или недостача = номер колонки
    }


}
