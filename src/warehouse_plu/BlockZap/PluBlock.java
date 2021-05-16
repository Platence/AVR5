package warehouse_plu.BlockZap;

import java.util.ArrayList;

public class PluBlock {

    private String plu;

    private ArrayList<SapAndCountBlockZap> list;

    public PluBlock(String plu) {
        this.plu = plu;
        this.list = new ArrayList<>();
    }


    public String getPlu() {
        return plu;
    }

    public void setPlu(String plu) {
        this.plu = plu;
    }

    public ArrayList<SapAndCountBlockZap> getList() {
        return list;
    }

    public boolean haveSap(String sap){
        try {
            for (SapAndCountBlockZap ff : list) {
                if (sap.equals(ff.getSapNumber())) {
                    return ff.getCount() != 0;
                }
            }
        }
        catch (Exception e){
            return false;
        }
        return false;
    }

    public int getCountFromSap(String sap){
        try {
            for (SapAndCountBlockZap ff : list) {
                if (sap.equals(ff.getSapNumber())) {
                    return ff.getCount();
                }
            }
        }
        catch (Exception e){
            return 0;
        }
        return 0;
    }




    public void setList(ArrayList<SapAndCountBlockZap> list) {
        this.list = list;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

}
