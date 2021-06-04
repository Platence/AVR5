package numberShopPack;


import decriptor.ConsoleAVR;
import error_package.Modal_Error;
import java.io.*;
import java.util.Scanner;

public class ParserShop {
    public static StringBuilder s = new StringBuilder();;

    public static int countIndex = 0;                        // Переменная хранит индексы строк


    public static void startSet() {

        File file = new File("src/binn/baseShop/AllMagazine.txt");
        ConsoleAVR.printlnn("Файл списка магазинов получен...");
        Scanner sc = null;

        try {
            sc = new Scanner(file);
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
            new Modal_Error().set_erroe_messege("НЕ УДАЛОСЬ ПРОЧИТАТЬ МАГАЗИНЫ");
            sc.close();
        }

        ConsoleAVR.printlnn("Запуск цикла парсинга файла(Магазины)");

        while (sc.hasNextLine()) {
            s.setLength(0);
            s.append(sc.nextLine());
            String mas[] = s.toString().split("%");

            try {
                ShopDescription.tryAddnewPosition(mas[0], mas[1], mas[2], mas[3], mas[4], mas[5], mas[6]);
            }
            catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                ConsoleAVR.printlnn("РАЗРУШЕНИЕ ЦИКЛА ЧТЕНИЯ МАГАЗИНОВ!!!");
                return;
            }
        }

        sc.close();
        System.gc();
        ConsoleAVR.printlnn("Инъекция магазинов прошла успешно");

    }

}





