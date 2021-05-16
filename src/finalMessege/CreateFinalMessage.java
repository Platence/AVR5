package finalMessege;

import all_controllers.logicAnalitic.Bundle_For_WRS;
import exportXLSX.inv15i.ExportInv15;
import exportXLSX.inv15i.InformationX;
import numberShopPack.ShopNumber;
import sample.Main;
import warehouse_plu.Ostatku;

public class CreateFinalMessage {

    public String finalWord ;


    public CreateFinalMessage(String finalWord) {
        this.finalWord = finalWord;
        System.out.println(this.finalWord);
    }


    public static CreateFinalMessage giveMemessage(){


        new Bundle_For_WRS();

        for(Ostatku ddd : Main.classOstatku){
            Bundle_For_WRS.start_lit(ddd);
        }

        InformationX ifx = ExportInv15.getInformationX();


        String text1 = "Проведена ПИ в магазине " + ShopNumber.currentNumberShop + ", по адресу " +
                ifx.getFullAdress() + System.lineSeparator();

        String text2 = "Причина проведения ПИ: Плановая" + System.lineSeparator()+ System.lineSeparator();


        String text3 = "  1. Результат ПИ - " + Bundle_For_WRS.result_PU + " руб." + System.lineSeparator();



        StringBuilder sb = new StringBuilder();
        sb.append(text1);
        sb.append(text2);
        sb.append(text3);

        return new CreateFinalMessage(sb.toString());
    }
}
