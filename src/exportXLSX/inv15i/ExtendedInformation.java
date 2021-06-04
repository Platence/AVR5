package exportXLSX.inv15i;

import decriptor.ConsoleAVR;
import inv15docpack.Inv15Field;
import numberShopPack.ShopNumber;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class ExtendedInformation {

    private String summFactOnWords;
    private String summKassaWord;
    private String factNumber;
    private String kassaNumber;
    private boolean haveras;
    private String izliwek_kop;
    private String nedosta4a_kop;

    private String factKop;
    private String reestr_kop;

    private String result;

    public String tempResalusurplus;
    public String tempResalusortage;

    public ExtendedInformation(String summFactOnWords, String summKassaWord, String factNumber, String kassaNumber,
                               boolean haveras, String izliwek_kop, String nedosta4a_kop, String factKop, String reestr_kop, String result) {
        this.summFactOnWords = summFactOnWords;
        this.summKassaWord = summKassaWord;
        this.factNumber = factNumber;
        this.kassaNumber = kassaNumber;
        this.haveras = haveras;
        this.izliwek_kop = izliwek_kop;
        this.nedosta4a_kop = nedosta4a_kop;
        this.factKop = factKop;
        this.reestr_kop = reestr_kop;
        this.result = result;
    }

    public static ExtendedInformation genericNewInformation(){

        //float resultFactOfKassa = Inv15Field.currentSUMMFACT - Inv15Field.currentSummREESTR;

        BigDecimal bd1 = new BigDecimal(Double.toString(Inv15Field.currentSUMMFACT)).setScale(3, RoundingMode.UP);
        BigDecimal bd2 = new BigDecimal(Double.toString(Inv15Field.currentSummREESTR)).setScale(3, RoundingMode.UP);

        BigDecimal bd3 = bd1.subtract(bd2, MathContext.UNLIMITED).setScale(2,RoundingMode.UP);

        ConsoleAVR.printlnn(Inv15Field.currentSUMMFACT+"Current Summ");

        System.out.println(bd1.doubleValue());
        System.out.println(bd2.doubleValue());
        System.out.println(bd3.doubleValue());

        double r = bd3.doubleValue();
        double resultFactOfKassa = r;


        ConsoleAVR.printlnn(resultFactOfKassa + " res kassa ");

        boolean rasx = resultFactOfKassa != 0;

        String reslut2 = "";

        String izliwekKop ="0";
        String nedosta4a_kop ="0";

        String factKop = "00";
        String reestrKop = "00";

        String surplusForMail = "";
        String sortageForMail = "";

        if(rasx){
            if(resultFactOfKassa>0){
                izliwekKop = getkop(resultFactOfKassa);
            }
            if(resultFactOfKassa<0){
                nedosta4a_kop = getkop(resultFactOfKassa);
            }

            factKop = getkop(bd1.doubleValue());
            reestrKop = getkop(bd2.doubleValue());
        }
        else {
            factKop = getkop(bd1.doubleValue());
            reestrKop = getkop(bd2.doubleValue());
        }


        if(resultFactOfKassa==0){reslut2 = "зации денежных средств в магазине №" + " " + ShopNumber.currentNumberShop + ", недостачи и излишков не обнаружено.";}

        if(resultFactOfKassa>0) {
            int res = (int) resultFactOfKassa;
            reslut2 = "зации денежных средств в магазине №" + " " +  ShopNumber.currentNumberShop + "," +
                    " обнаружен излишек в размере " + res + " руб. " + izliwekKop +" коп." ;
            rasx = true;
            surplusForMail =  "Обнаружен излишек в размере " + res + " руб. " + izliwekKop +" коп.";
        }

        if(resultFactOfKassa<0) {
            int res = (int) resultFactOfKassa;
            reslut2 = "зации денежных средств в магазине №" + " " +  ShopNumber.currentNumberShop + "," +
                    " обнаружена недостача в размере " + Math.abs(res) + " руб. " + nedosta4a_kop + " коп.";
            rasx = true;
            sortageForMail = "Обнаружена недостача в размере " + Math.abs(res) + " руб. " + nedosta4a_kop + " коп.";
        }



        int sumFuct = (int) Inv15Field.currentSUMMFACT;
        String factNumber = String.valueOf(sumFuct);
        double sf = sumFuct;
        String summFactOnWords = GiveMeMoneyWord.inwords(sf);
        summFactOnWords = firstCharZ(summFactOnWords);

        int sumkassa = (int) Inv15Field.currentSummREESTR;
        String kassaNumber = String.valueOf(sumkassa);
        double sc = sumkassa;
        String summKassaWord = GiveMeMoneyWord.inwords(sc);
        summKassaWord = firstCharZ(summKassaWord);







        ExtendedInformation extendedInformation =
                new ExtendedInformation(summFactOnWords,summKassaWord,factNumber,kassaNumber,rasx,izliwekKop,nedosta4a_kop,
                        factKop,reestrKop,reslut2);
        extendedInformation.setTempResalusurplus(surplusForMail);
        extendedInformation.setTempResalusortage(sortageForMail);

        return extendedInformation;
    }

    public static String raxhWord(String comand){

        double result = Inv15Field.currentSUMMFACT - Inv15Field.currentSummREESTR;

        if(comand.equals("IZ") && result > 0){
            int x = (int)result;
            return String.valueOf(x);

        }

        if(comand.equals("NE") && result < 0){
            int x = (int)result;
            int x2 = Math.abs(x);
            return String.valueOf(x2);
        }

        return "0";
    }

    public static String getkop(double x){

        try {
            String s = String.valueOf(x);
            if(s.contains("E")){return "EMP";}
            String[] d = s.split("\\.");

            return d[1].length()>1 ? d[1].substring(0,2) : d[1].substring(0,1)+"0";
        }
        catch (Exception e){
            return "EMP";
        }
    }

    public static String firstCharZ(String word){
        String str2 = word;
        str2 = str2.substring(0,1).toUpperCase() + str2.substring(1);

        word = str2;

        return word;
    }

    public String getSummFactOnWords() {
        return summFactOnWords;
    }

    public void setSummFactOnWords(String summFactOnWords) {
        this.summFactOnWords = summFactOnWords;
    }

    public String getSummKassaWord() {
        return summKassaWord;
    }

    public void setSummKassaWord(String summKassaWord) {
        this.summKassaWord = summKassaWord;
    }

    public String getFactNumber() {
        return factNumber;
    }

    public void setFactNumber(String factNumber) {
        this.factNumber = factNumber;
    }

    public String getKassaNumber() {
        return kassaNumber;
    }

    public void setKassaNumber(String kassaNumber) {
        this.kassaNumber = kassaNumber;
    }

    public boolean isHaveras() {
        return haveras;
    }

    public void setHaveras(boolean haveras) {
        this.haveras = haveras;
    }

    public String getIzliwek_kop() {
        return izliwek_kop;
    }

    public void setIzliwek_kop(String izliwek_kop) {
        this.izliwek_kop = izliwek_kop;
    }

    public String getNedosta4a_kop() {
        return nedosta4a_kop;
    }

    public void setNedosta4a_kop(String nedosta4a_kop) {
        this.nedosta4a_kop = nedosta4a_kop;
    }

    public String getFactKop() {
        return factKop;
    }

    public void setFactKop(String factKop) {
        this.factKop = factKop;
    }

    public String getReestr_kop() {
        return reestr_kop;
    }

    public void setReestr_kop(String reestr_kop) {
        this.reestr_kop = reestr_kop;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getTempResalusurplus() {
        return tempResalusurplus;
    }

    public void setTempResalusurplus(String tempResalusurplus) {
        this.tempResalusurplus = tempResalusurplus;
    }

    public String getTempResalusortage() {
        return tempResalusortage;
    }

    public void setTempResalusortage(String tempResalusortage) {
        this.tempResalusortage = tempResalusortage;
    }


}
