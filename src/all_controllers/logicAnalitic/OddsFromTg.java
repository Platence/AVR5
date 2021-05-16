package all_controllers.logicAnalitic;

import all_paths.Paths_Main_File;
import error_package.Modal_Error;
import exportXLSX.ExportExcel;
import exportXLSX.exportTGODDS.ExportTGGGGG;
import sample.Main;
import warehouse_plu.Ostatku;

import java.util.ArrayList;
import java.util.HashSet;

public class OddsFromTg {

    /**
     * просчет разницы по группам для выгрузки
     * в Excel
     */

    private double sticksAndSigarets=0;
    private double alckogol=0;
    private double konditerka=0;
    private double bakaleya=0;
    private double kids_tov=0;
    private double kids_pit=0;
    private double soputka=0;
    private double korm=0;
    private double sok_voda_Pivo=0;
    private double zamorozka=0;
    private double moloko=0;
    private double colbasa_myaso=0;
    private double odejda=0;
    private double spec_akc=0;
    private double in_aut=0;
    private double media=0;
    private double frov=0;
    private double another = 0;
    public HashSet<String> anotherName = new HashSet<>();




    public void createOddsGroup(){
        /*
        Выгрузка разницы по группам
         */
        Bundle_For_WRS.sigarets = 0;

        for(Ostatku ost : Main.classOstatku){
            CalculatorODDS.setCalc(this,ost);
            Bundle_For_WRS.setSigarets(ost);  // Расчет по сигаретам будем брать из сигарет
        }

        try {
            ExportTGGGGG tg = new ExportTGGGGG();
            tg.genereteNewCell(this);
            tg.exportFile(ExportExcel.COMAND_TO_EXPORT_TG_ODDS,tg.getCellODDSList(), Paths_Main_File.path_final_out);
        }
        catch (Exception e){
            new Modal_Error().set_erroe_messege("непредвиденная ошибка " + e.getLocalizedMessage());
            e.printStackTrace();
        }


    }


    public double getSticksAndSigarets() {
        return sticksAndSigarets;
    }

    public void setSticksAndSigarets(double sticksAndSigarets) {
        this.sticksAndSigarets = sticksAndSigarets;
    }

    public double getAlckogol() {
        return alckogol;
    }

    public void setAlckogol(double alckogol) {
        this.alckogol = alckogol;
    }

    public double getKonditerka() {
        return konditerka;
    }

    public void setKonditerka(double konditerka) {
        this.konditerka = konditerka;
    }

    public double getBakaleya() {
        return bakaleya;
    }

    public void setBakaleya(double bakaleya) {
        this.bakaleya = bakaleya;
    }

    public double getKids_tov() {
        return kids_tov;
    }

    public void setKids_tov(double kids_tov) {
        this.kids_tov = kids_tov;
    }

    public double getKids_pit() {
        return kids_pit;
    }

    public void setKids_pit(double kids_pit) {
        this.kids_pit = kids_pit;
    }

    public double getSoputka() {
        return soputka;
    }

    public void setSoputka(double soputka) {
        this.soputka = soputka;
    }

    public double getKorm() {
        return korm;
    }

    public void setKorm(double korm) {
        this.korm = korm;
    }

    public double getSok_voda_Pivo() {
        return sok_voda_Pivo;
    }

    public void setSok_voda_Pivo(double sok_voda_Pivo) {
        this.sok_voda_Pivo = sok_voda_Pivo;
    }

    public double getZamorozka() {
        return zamorozka;
    }

    public void setZamorozka(double zamorozka) {
        this.zamorozka = zamorozka;
    }

    public double getMoloko() {
        return moloko;
    }

    public void setMoloko(double moloko) {
        this.moloko = moloko;
    }

    public double getColbasa_myaso() {
        return colbasa_myaso;
    }

    public void setColbasa_myaso(double colbasa_myaso) {
        this.colbasa_myaso = colbasa_myaso;
    }

    public double getOdejda() {
        return odejda;
    }

    public void setOdejda(double odejda) {
        this.odejda = odejda;
    }

    public double getSpec_akc() {
        return spec_akc;
    }

    public void setSpec_akc(double spec_akc) {
        this.spec_akc = spec_akc;
    }

    public double getIn_aut() {
        return in_aut;
    }

    public void setIn_aut(double in_aut) {
        this.in_aut = in_aut;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }

    public double getFrov() {
        return frov;
    }

    public void setFrov(double frov) {
        this.frov = frov;
    }

    public double getAnother() {
        return another;
    }

    public void setAnother(double another) {
        this.another = another;
    }
}
