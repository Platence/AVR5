package info_page;

import all_controllers.Rule_contollers_Main;
import all_controllers.logicAnalitic.GlobalRulesBlock;
import chekCrashPLU.CrashesPlu;
import chekCrashPLU.IgnoredPlu;
import chekCrashPLU.PasxaPLU;
import chekCrashPLU.searchSpace.SearchEngine;
import error_package.Modal_Error;
import exportXLSX.inv15i.InformationX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import megaBlock.MegaBlockYU2;
import numberShopPack.ShopDescription;
import numberShopPack.ShopNumber;
import sample.Main;
import warehouse_plu.BlockZap.BlockZapasPlu;
import warehouse_plu.BlockZap.PluBlock;
import warehouse_plu.Ostatku;

import java.util.*;

import static exportXLSX.inv15i.ExportInv15.getInformationX;

public class SelfWork {


    public ObservableList<Ostatku> getSelf(){
        ArrayList<Ostatku> temp = new ArrayList<>();
        boolean con = false;

        for(Ostatku z : Main.classOstatku){
            con = false;
            if(temp.size()>0) {

                for (Ostatku d : temp) {
                    if (d.getPlu().equals(z.getPlu())) {
                        con = true;
                        break;
                    }
                }
            }
            if(con){continue;}




            if(z.getYu3().contains("обственн")){
                temp.add(z);
            }
            if(z.getYu3().contains("аты для пекар")){temp.add(z);}
            if (MegaBlockYU2.chekPositionToMainRulesYU2(z)){temp.add(z);}
        }

        ObservableList<Ostatku> ost = FXCollections.observableArrayList(temp);
        return ost;
    }

    public ObservableList<Ostatku> getTara(){
        ArrayList<Ostatku> temp = new ArrayList<>();

        for(Ostatku z : Main.classOstatku){
            if(z.getYu2().contains("ара воз")){
                temp.add(z);
            }
        }

        ObservableList<Ostatku> ost = FXCollections.observableArrayList(temp);
        return ost;
    }

    public ObservableList<Ostatku> getzeroHave(){
        ArrayList<Ostatku> temp = new ArrayList<>();

        for(Ostatku z : Main.classOstatku){
            if(z.qychetnoe<0){
                temp.add(z);
            }
        }

        ObservableList<Ostatku> ost = FXCollections.observableArrayList(temp);
        return ost;
    }

    public ObservableList<Ostatku> getAllErrors(){

        ArrayList<Ostatku> temp = new ArrayList<>();

        for(Ostatku z : Main.classOstatku){

            for(CrashesPlu ss : CrashesPlu.listCrashes) {
                if(ss.getPlu().equals(z.getPlu())){
                    temp.add(z);
                }
            }

            for(String zxc : PasxaPLU.paskhaPLU){
                if(zxc.equals(z.getPlu())){
                    temp.add(z);
                }
            }

            for(IgnoredPlu zxc : IgnoredPlu.listIgnoredPly){
                if(zxc.getPli().equals(z.getPlu())){
                    temp.add(z);
                }
            }

        }

        ObservableList<Ostatku> ost = FXCollections.observableArrayList(temp);
        return ost;
    }

    public ObservableList<Ostatku> getBlockZapas(){

        BlockZapasPlu bzp = new BlockZapasPlu();

        final String sapNumber = ShopDescription.getMeSap(ShopNumber.currentNumberShop);

        if(sapNumber.length()<2){new Modal_Error().set_erroe_messege("SAP номер не обнаружен "+System.lineSeparator()+"" +
                "На данный момент продолжение невозможно");}

        ArrayList<Ostatku> tempList = new ArrayList<>();

        for(Ostatku z : Main.classOstatku){

            if(!GlobalRulesBlock.nonBred(z)){continue;}
            if(!GlobalRulesBlock.nonGramm1000(z)){continue;}
            if(z.getOddsCOUNT()==0){continue;}

            for (PluBlock s : bzp.getListbl()){

                  if(s.getPlu().equals(z.getPlu())){

                      if (!s.haveSap(sapNumber)){
                          continue;
                      }
                      int x = (int) (Math.abs(s.getCountFromSap(sapNumber)));
                      int testResult = (int) (x - Math.abs(z.getOddsCOUNT()));
                      if(Math.abs(testResult)>3){continue;}

                      tempList.add(z);
                      z.setBlockedZap(s.getCountFromSap(sapNumber));
                  }
            }
        }

        return FXCollections.observableArrayList(tempList);
    }

    public ObservableList<Ostatku> getSearchIndex(){

        Set<Ostatku> z = new SearchEngine().getListSearch();
        List<Ostatku> sortedList = new ArrayList<>(z);

        class RecipeCompare implements Comparator<Ostatku> {

            @Override
            public int compare(Ostatku o1, Ostatku o2) {
                // write comparison logic here like below , it's just a sample
                return o1.getName().compareTo(o2.getName());
            }
        }
        Collections.sort(sortedList,new RecipeCompare());

        System.gc();

        return FXCollections.observableArrayList(sortedList);
    }




}
