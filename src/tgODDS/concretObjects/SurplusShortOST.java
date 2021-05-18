package tgODDS.concretObjects;

import warehouse_plu.Ostatku;

public class SurplusShortOST {

    private int surplus;
    private int shortage;
    private int nonCount;
    private int haveKP;
    private int result;

    public SurplusShortOST() {
        this.surplus  = 0;
        this.shortage = 0;
        this.nonCount = 0;
        this.haveKP   = 0;
        this.result   = 0;
    }

    public void updateCount(Ostatku d){

        if(d.getOddsSUM()>0){
           this.surplus+=d.getOddsSUM();
        }

        if(d.getOddsSUM()<0){
            this.shortage+=d.getOddsSUM();
        }

        if(d.getQfinal() == 0 ){
            if(d.getControlChek().equals("EMP") || d.getControlChek().equals("0")){
                this.nonCount+=d.getOddsSUM();
            }
        }

        if(!d.getControlChek().equals("EMP") ){
            this.haveKP++;
        }

        this.result+=d.getOddsSUM();

    }

    public int getSurplus() {
        return surplus;
    }

    public int getShortage() {
        return Math.abs(shortage);
    }

    public int getNonCount() {
        return nonCount;
    }

    public int getHaveKP() {
        return haveKP;
    }

    public int getResult() {
        return result;
    }
}
