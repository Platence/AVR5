package tgODDS;

import all_controllers.logicAnalitic.GlobalRulesBlock;
import sample.Main;
import tgODDS.concretObjects.SurplusShortOST;
import warehouse_plu.Ostatku;

import java.util.ArrayList;

public class SearchData {

    // Получает имя групп(ы) на вход,
    // Возвращает объект

    public static int yuL2 = 2;
    public static int yuL3 = 3;
    public static int yuL2Ex = 4;


    public static InfoForTable getObject(int levelYU, ArrayList<String> nameSearchGroup,
                                         ArrayList<String> ignoreList, String nameInfo) {

        SurplusShortOST ssot = new SurplusShortOST();

        if (levelYU == yuL2Ex) {
            for (Ostatku ost : Main.classOstatku) {

                if (ignoreList.contains(ost.getYu2())) {
                    continue;
                }
                if (!GlobalRulesBlock.nonBred(ost)) {
                    continue;
                }
                ssot.updateCount(ost);
            }
        }

            if (levelYU == yuL2) {
                // Search Level 2
                for (Ostatku ost : Main.classOstatku) {
                    if (ignoreList.contains(ost.getYu2())) {
                        continue;
                    }

                    if (ignoreList.contains(ost.getYu3())) {
                        continue;
                    }
                    if (!GlobalRulesBlock.nonBred(ost)) {
                        continue;
                    }
                    if (nameSearchGroup.contains(ost.getYu2())) {
                        ssot.updateCount(ost);
                    }
                }
            }

            if (levelYU == yuL3) {
                // Search Level 3
                for (Ostatku ost : Main.classOstatku) {
                    if (ignoreList.contains(ost.getYu3())) {
                        continue;
                    }
                    if (!GlobalRulesBlock.nonBred(ost)) {
                        continue;
                    }
                    if (nameSearchGroup.contains(ost.getYu3())) {
                        ssot.updateCount(ost);
                    }
                }
            }

            return new InfoForTable(nameInfo, ssot.getSurplus(), ssot.getShortage(), ssot.getNonCount(),
                    ssot.getHaveKP(), ssot.getResult());

        }
    }

