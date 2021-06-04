package warehouse_plu.BlockZap;

import all_controllers.Rule_contollers_Main;
import decriptor.ConsoleAVR;
import error_package.Modal_Error;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class BlockZapasPlu {



    private ArrayList<PluBlock> listbl = new ArrayList<>();


    public BlockZapasPlu() {

        ConsoleAVR.printlnn("Начата проверка данных по базе БЗ...");

        if (loadResourses()){

            try {
                createList(getBuilderList());
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private ArrayList<StringBuilder> getBuilderList() throws Exception {
        String path = Rule_contollers_Main.main_controller.sdfsdfsdf.getText();
        System.out.println("Path is : " + path);
        File f = new File(path);
        ConsoleAVR.printlnn("Установка соединения с : " + path);
        Scanner sc = new Scanner(f);
        System.out.println("Начало парсинга");

        ArrayList<StringBuilder> list = new ArrayList<>();

        while (sc.hasNext()){
            StringBuilder sb = new StringBuilder();
            sb.append(sc.nextLine());
            list.add(sb);
        }
        sc.close();
        f=null;
        System.out.println("Закрытие потоков прошло успешно. Список получен");
        return list;

    }

    private boolean loadResourses(){
        try {
            String path = Rule_contollers_Main.main_controller.sdfsdfsdf.getText();
            ConsoleAVR.printlnn("Path is : " + path);

            File f = new File(path);
            ConsoleAVR.printlnn("Установка соединения с : " + path);
            Scanner sc = new Scanner(f);
            ConsoleAVR.printlnn("Успешно");
            sc.close();
            f = null;
            return true;
        }
        catch (Exception e){
            String path = Rule_contollers_Main.main_controller.sdfsdfsdf.getText();
            System.out.println("Path is : " + path);
            new Modal_Error().set_erroe_messege("Не удалось установить соединение с " + System.lineSeparator()+"" +
                    path+System.lineSeparator()+"" +
                    "Попробуйте прописать путь вручную, используя вкладку настройки.");
            return false;
        }
    }

    private void createList(ArrayList<StringBuilder> list){

        for(StringBuilder sb : list){
            try {
                PluBlock pb = getPluBlock(sb);
                this.getListbl().add(pb);
            }
            catch (Exception e) {
                new Modal_Error().set_erroe_messege("Ошибка парсинга данных!"+e.getMessage());
                e.printStackTrace();
                break;
            }
            sb.setLength(0);
        }

    }

    public ArrayList<PluBlock> getListbl() {
        return listbl;
    }

    private PluBlock getPluBlock(StringBuilder sb) throws Exception{
        String[] mas = sb.toString().split("#");
        PluBlock pb = new PluBlock(mas[0]);
        getZas(mas[1],pb);
        return pb;
    }

    private void getZas (String x,PluBlock pb) throws Exception{

        String [] secLevelSap = x.split("@");

        for (int i = 0 ; i < secLevelSap.length;i++){

            if(secLevelSap[i].length()<1){continue;}

            SapAndCountBlockZap szabz = new SapAndCountBlockZap();
            String [] thrLevelCount = secLevelSap[i].split(":");

            szabz.setSapNumber(thrLevelCount[0]);
            try { szabz.setCount(Integer.parseInt(thrLevelCount[1])); }
            catch (Exception e){ szabz.setCount(0); }
            pb.getList().add(szabz);

//            System.out.println("Добавление объекта к PLU : " + pb.getPlu());
//            System.out.println("SAP = " + szabz.getSapNumber() + " " + " count = " + szabz.getCount());
//            System.out.println();
        }
    }

}
