package parseNeprodam;

import chekCrashPLU.IgnoredPlu;
import sample.Main;
import warehouse_plu.Ostatku;

import java.util.ArrayList;
import java.util.LinkedList;

public class NeprodamField {

    public static int NUMERIC_LAST_IMPORT = 11;
    public static int NUMERIC_LAST_SALE = 12;
    public static int NUMERIC_PLU = 1;

    public static ArrayList<NeprodamField> listNPS = new ArrayList<>();

    private String plu = "";
    private String lastdateImport = "";
    private String lastDateSale = "";

    public NeprodamField(String plu, String lastdateImport, String lastDateSale) {

        this.plu = plu;
        this.lastdateImport = lastdateImport;
        this.lastDateSale = lastDateSale;
    }

    public static void tryAddnewPosition(LinkedList<String> lll){
        String plu = "";
        String lastdateImport = "";
        String lastDateSale = "";

        for(int i = 0 ; i < lll.size(); i++){

            if(i == NUMERIC_PLU){plu=lll.get(i);continue;}
             if(i == NUMERIC_LAST_IMPORT){lastdateImport = lll.get(i);continue;}
             if(i == NUMERIC_LAST_SALE){lastDateSale = lll.get(i);continue;}
        }

        listNPS.add(new NeprodamField(plu,lastdateImport,lastDateSale));
        plu = null;
        lastdateImport = null;
        lastDateSale = null;

    }









    public String getPlu() {
        return plu;
    }

    public void setPlu(String plu) {
        this.plu = plu;
    }

    public String getLastdateImport() {
        return lastdateImport;
    }

    public void setLastdateImport(String lastdateImport) {
        this.lastdateImport = lastdateImport;
    }

    public String getLastDateSale() {
        return lastDateSale;
    }

    public void setLastDateSale(String lastDateSale) {
        this.lastDateSale = lastDateSale;
    }
}
