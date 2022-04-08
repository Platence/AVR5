package mailMessage;

import all_controllers.Rule_contollers_Main;
import all_controllers.logicAnalitic.Bundle_For_WRS;
import all_controllers.logicAnalitic.LogicForAnalitik;
import error_package.Modal_Error;
import exportXLSX.inv15i.ExportInv15;
import exportXLSX.inv15i.ExtendedInformation;
import exportXLSX.inv15i.InformationX;
import info_page.smenaMOLPACKAGE.DescriptionSmenaMOL;
import numberShopPack.ShopDescription;
import numberShopPack.ShopNumber;

import java.awt.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsolidationInformation {

    private Desktop fDesktop = Desktop.getDesktop();

    private static final Pattern SIMPLE_CHARS = Pattern.compile("[a-zA-Z0-9]");

    private String getEmailSubject(){
        String shop = Rule_contollers_Main.main_controller.number_shop.getText();
        String klaster = ShopDescription.getKlaster(shop);
        String reason = "";
        InformationX inv = ExportInv15.getInformationX();
        String date = inv.getDate_INVENT();
        if(DescriptionSmenaMOL.smenaMolD){reason = "ПИ ТМЦ и ОС ";}
        if(!DescriptionSmenaMOL.smenaMolD){reason = "ПИ ТМЦ ";}
        String fres = reason + shop + " " + date + " " + klaster;
        return encodeUnusualChars(fres);
    }

    public String getFullInformationString(){



        StringBuilder sb = new StringBuilder();
        InformationX inv = ExportInv15.getInformationX();

        String NL = System.getProperty("line.separator");

        String s = "Добрый день!"+NL;
        String reas = "";
        if(DescriptionSmenaMOL.smenaMolD){reas = "Смена МОЛ";}
        if(!DescriptionSmenaMOL.smenaMolD){reas = "Плановая";}
        String s2 = "Проведена ПИ  в магазине №" + ShopNumber.currentNumberShop +" по адресу : "+ NL
                + separeteAdress(inv.getFullAdress()) +NL;
        String s3 = "___________________________________________"+NL;
        String s4 = "Причина проведения ПИ: " + reas + " "  +NL;  // Заменить
        String resalut = Bundle_For_WRS.getresultformail();
        String s5 = "1. Результат ПИ - " + resalut+NL+NL;

        String s6 = "2.По товарной группе «Табачные изделия» после второго просчёта выявлены следующие результаты:"+NL;
        String s7 = "Излишек : " + Bundle_For_WRS.cigarettesSURPLUS + " руб."+NL;
        String s8 = "Недостача : " + Math.abs(Bundle_For_WRS.cigarettesShortage) + " руб."+NL+NL;
        String s9 = "3.Товар не подлежащий реализации (с истекшим сроком годности, бой/порча )"+NL;
        String s10 = ""+NL;
        String s11 = ""+System.lineSeparator()+NL;
        String s12 = "4.Результат инвентаризация ГК."+NL;
        String s13 = "Без расхождений.";
        ExtendedInformation z = ExtendedInformation.genericNewInformation();
        if(z.isHaveras()){
            if(z.getTempResalusurplus().length()>1){s13=z.getTempResalusurplus();}
            if(z.getTempResalusortage().length()>1){s13=z.getTempResalusortage();}
        }
        s13+=NL+NL;
        String s14 = "5.Потери по группам: " + NL+NL+NL;

        String s15 = "6.     ПИ ОС не проводилась.\n" +
                "\n" +
                " \n" +
                "7.    Проверка  закрытия документов прихода ОС :\n" +
                "       Не закрытых перемещений не обнаружено.\n" +
                "\n" +
                "8.   Нет открытых списаний по ОС.\n" +
                "\n" +
                " \n" +
                "9.   Комментарии по ПИ :";

        sb.append(s);
        sb.append(s2);
        sb.append(s3);
        sb.append(s4);
        sb.append(s5);
        sb.append(s6);
        sb.append(s7);
        sb.append(s8);
        sb.append(s9);
        sb.append(s10);
        sb.append(s11);
        sb.append(s12);
        sb.append(s13);
        sb.append(s14);
        sb.append(s15);

        return sb.toString();
    }

    public static String getTextResPI(){
        try {
            LogicForAnalitik.start_Analitics();
            if(Bundle_For_WRS.result_PU<0){return "Недостача : " + Bundle_For_WRS.result_PU;}
            if(Bundle_For_WRS.result_PU>=0){return "Излишек : " + Bundle_For_WRS.result_PU;}
        }
        catch (Exception e){
            new Modal_Error().set_erroe_messege("Критическая ошибка в просчете данных для письма");
        }
        return "СИСТЕМНАЯ ОШИБКА!!!!!!";
    }

    private static void log(Object aMessage){
        System.out.println(String.valueOf(aMessage));
    }

    private static String separeteAdress(String adress){
        int number = 0;
        number = adress.indexOf(",");
        return adress.substring(number+1);
    }

    private String encodeUnusualChars(String aText){
        StringBuilder result = new StringBuilder();
        CharacterIterator iter = new StringCharacterIterator(aText);
        for(char c = iter.first(); c != CharacterIterator.DONE; c = iter.next()) {
            char[] chars = {c};
            String character = new String(chars);
            if(isSimpleCharacter(character)){
                result.append(c);
            }
            else {
                hexEncode(character, "UTF-8", result);
            }
        }
        return result.toString();
    }

    private boolean isSimpleCharacter(String aCharacter){
        Matcher matcher = SIMPLE_CHARS.matcher(aCharacter);
        return matcher.matches();
    }

    /**
     For the given character and encoding, appends one or more hex-encoded characters.
     For double-byte characters, two hex-encoded items will be appended.
     */
    private static void hexEncode(String aCharacter, String aEncoding, StringBuilder aOut) {
        try  {
            String HEX_DIGITS = "0123456789ABCDEF";
            byte[] bytes = aCharacter.getBytes(aEncoding);
            for (int idx = 0; idx < bytes.length; idx++) {
                aOut.append('%');
                aOut.append(HEX_DIGITS.charAt((bytes[idx] & 0xf0) >> 4));
                aOut.append(HEX_DIGITS.charAt(bytes[idx] & 0xf));
            }
        }
        catch (UnsupportedEncodingException ex) {
            log(ex.getStackTrace());
        }
    }
}

