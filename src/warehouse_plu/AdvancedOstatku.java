package warehouse_plu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

public class AdvancedOstatku {

    private Ostatku ostatku;
    private double sklSumm;
    private double sklcount;
    private String nameYU3;

    public static ArrayList<AdvancedOstatku> ostat = new ArrayList<>();


    public AdvancedOstatku(Ostatku current, double sklcount, double sklSumm){
        this.ostatku = current;
        this.sklcount = sklcount;
        this.sklSumm = sklSumm;
        this.nameYU3 = current.getYu3();
    }

    public static LinkedList<AdvancedOstatku> getSortList(){

        LinkedList<AdvancedOstatku> list = new LinkedList<>();
        for(AdvancedOstatku ao : ostat){
            list.add(ao);
        }
        list.sort(new Comparator<AdvancedOstatku>() {
            @Override
            public int compare(AdvancedOstatku o1, AdvancedOstatku o2) {
               return o1.nameYU3.equals(o2.nameYU3) ? 0 : 1;
            }
        });
        return list;
    }




    public static void clearList(){
        ostat.clear();
    }

    public Ostatku getOstatku() {
        return ostatku;
    }

    public void setOstatku(Ostatku ostatku) {
        this.ostatku = ostatku;
    }

    public double getSklSumm() {
        return sklSumm;
    }

    public void setSklSumm(double sklSumm) {
        this.sklSumm = sklSumm;
    }

    public double getSklcount() {
        return sklcount;
    }

    public void setSklcount(double sklcount) {
        this.sklcount = sklcount;
    }

    @Override
    public String toString() {
        return    " PLU " + ostatku.getPlu() + System.lineSeparator()
                + " NAME " + ostatku.getName() + System.lineSeparator()
                + "First ODDS : " + getSklSumm() + " Second ODDS : " + ostatku.getOddsSUM()
                + "First Count : " + getSklcount() + " Second Count " + ostatku.getOddsCOUNT();
    }
}
