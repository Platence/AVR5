package numberShopPack;

import decriptor.ConsoleAVR;

import java.util.ArrayList;
import java.util.Objects;

public class ShopDescription {

    public static String indexShope = "EMP";
    public static String adressShop = "EMP";

    public static ArrayList<ShopDescription> listShopSheetOne = new ArrayList<>();

    private String sap;
    private String numShopEx;
    private String numberShop;
    private String klaster;
    private String mailIndex;
    private String addres;
    private String arhitect;


    public ShopDescription(String sap, String numShopEx, String numberShop, String klaster, String mailIndex, String addres, String arhitect) {
        this.sap = sap;
        this.numShopEx = numShopEx;
        this.numberShop = numberShop;
        this.klaster = klaster;
        this.mailIndex = mailIndex;
        this.addres = addres;
        this.arhitect = arhitect;
    }

    public static void tryAddnewPosition(String sap, String numShopEx, String numberShop, String klaster, String mailIndex, String addres, String arhitect){

        // Стандартно, проверим если есть.
        for(ShopDescription sd : listShopSheetOne){

            if(sd.getSap().equals(sap)){return;}
        }
        listShopSheetOne.add(new ShopDescription(sap,numShopEx,numberShop,klaster,mailIndex,addres,arhitect));
    }


    public static String getMeMagazine(String numberShop){


        for(ShopDescription sp : listShopSheetOne){
            if(numberShop.equals(sp.getNumberShop())){
                return sp.toString();
            }
        }
        ConsoleAVR.printlnn(numberShop + " не найден (ER)");

        return "EMPTY";
    }

    public static String getMeSap(String numberShop){
        ConsoleAVR.printlnn("Поиск сап номера для " + numberShop);

        for(ShopDescription sp : listShopSheetOne){
            if(numberShop.equals(sp.getNumberShop())){
                return sp.getSap();
            }
        }
        ConsoleAVR.printlnn(numberShop + " не найден (ER)");
        ConsoleAVR.printlnn("Размер списка магазинов "+listShopSheetOne.size());
        return "EMPTY";
    }

    public static String getKlaster(String numberShop){
        for(ShopDescription sp : listShopSheetOne){
            if(numberShop.equals(sp.getNumberShop())){
                return sp.getKlaster();
            }
        }
        System.out.println(numberShop + " не найден");
        return "EMPTY";
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopDescription that = (ShopDescription) o;
        return Objects.equals(sap, that.sap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sap);
    }

    public String getSap() {
        return sap;
    }

    public void setSap(String sap) {
        this.sap = sap;
    }

    public String getNumShopEx() {
        return numShopEx;
    }

    public void setNumShopEx(String numShopEx) {
        this.numShopEx = numShopEx;
    }

    public String getKlaster() {
        return klaster;
    }

    public void setKlaster(String klaster) {
        this.klaster = klaster;
    }

    public String getMailIndex() {
        return mailIndex;
    }

    public void setMailIndex(String mailIndex) {
        this.mailIndex = mailIndex;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public String getArhitect() {
        return arhitect;
    }

    public void setArhitect(String arhitect) {
        this.arhitect = arhitect;
    }

    @Override
    public String toString() {
        return "ShopDescription{" +
                sap + '\'' +
                numShopEx + '\'' +
                " " + numberShop + '\'' +
                " " + klaster + '\'' +
                " " + mailIndex + '\'' +
                " " + addres + '\'' +
                ", arhitect='" + arhitect + '\'' +
                '}';
    }

    public String getNumberShop() {
        return numberShop;
    }

    public void setNumberShop(String numberShop) {
        this.numberShop = numberShop;
    }


}
