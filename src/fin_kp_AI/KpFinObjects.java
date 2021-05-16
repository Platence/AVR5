package fin_kp_AI;

import all_paths.Paths_Main_File;
import error_package.Modal_Error;
import exportXLSX.finKP.ExportKPPFIN;
import interfaces_all.Import_and_export;
import sample.Main;
import sklad_KP_AI.Container_KP_Sklad;
import warehouse_plu.Ostatku;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class KpFinObjects implements Import_and_export{

    public static LinkedList<Ostatku> list_fin_kp = new LinkedList<>();

    public static String COMMAND_SAVE_KP_FIN = "SAVEFIN";




    public void loadAndChekOldFile(){

        if(Main.classOstatku.size()<1){new Modal_Error().set_erroe_messege("Сначала проведите раcчет Списка разниц");return;}
        //System.out.println("Метод в разработке");

        System.out.println("Попытка загрузки");


            loading();



        String s = errorsInKpFin(AddPositionToTableAndList.alllist);

        if(list_fin_kp.isEmpty()){new Modal_Error().set_erroe_messege(
                "Поскольку ваш КП_ЛИСТ заполнен " + System.lineSeparator()+"" +
                "AVR не стал загружать файл из памяти, а проверил текущий." + System.lineSeparator() + s);
        return;
        }

        new Modal_Error().set_erroe_messege(s);

    }


    public void saveNewFile(){

        /**
         *  Сохраняет весь список КП СКЛАД
         *  как в Excel так и в обычный word
         */

        list_fin_kp = AddPositionToTableAndList.alllist;
        if(Main.classOstatku.size()<1){new Modal_Error().set_erroe_messege("Сначала проведите раcчет Списка разниц");return;}
        if(AddPositionToTableAndList.alllist.size()<1){new Modal_Error().set_erroe_messege("Нельзя выгрузить пустой лист ");return;}

        new ExportKPPFIN().startExport();

        saving();

    }

    public static void loading(){

            if(list_fin_kp.isEmpty()){

               TreeSet<String> list = new KpFinObjects().get_file_content(Paths_Main_File.PATH_FIN_KP_LIST);

               for(String target : list){
                   for(Ostatku temp : Main.classOstatku){
                       if(target.equals(temp.getPlu())){
                           list_fin_kp.add(temp);
                       }
                   }
               }
                System.out.println("Загрузка проведена");
                AddPositionToTableAndList.alllist = list_fin_kp;
                ShowForTableView.updateTable();
               return;

            }

        System.out.println("Загрузка не требуется");

    }

    public static void saving(){
        list_fin_kp = AddPositionToTableAndList.alllist;

        if(list_fin_kp.size()<50){new Modal_Error().set_erroe_messege("Внимание,список содержит менее 50 PLU");}
        if(list_fin_kp.size()>50){new Modal_Error().set_erroe_messege("ОТКАЗ СОХРАНЕНИЯ ПРЕВЫШЕН ЛИМИТ в 50 PLU");return;}

        TreeSet<String> listplu = new TreeSet<>();

        for(Ostatku temp : list_fin_kp){
            listplu.add(temp.getPlu());
        }

        new KpFinObjects().save_KPPP(listplu,Paths_Main_File.PATH_FIN_KP_LIST,COMMAND_SAVE_KP_FIN);

        SaveToExcel.saving();
    }


    @Override
    public TreeSet<String> get_kp_PP_early(String name) {
        return null;
    }

    @Override
    public TreeSet<String> get_file_content(String path) {

        // Возвращает список с PLU

        StringBuilder sb = new StringBuilder();

        try(FileReader fileReader = new FileReader(path))
        {
            int c;
            while((c=fileReader.read())!=-1){
                sb.append((char)c);
                //System.out.print((char)c);
            }
        }
        catch (Exception e){
            new Modal_Error().set_erroe_messege("Ошибка при загрузке файла  = " + path);
            System.out.println("Exception in THREAD CONTAINER");
        }

        String temp = sb.toString();
        String [] res;
        res = temp.split("!");

        TreeSet<String> tempSet = new TreeSet<>(Arrays.asList(res));

        return tempSet;
    }

    @Override
    public void save_KPPP(TreeSet<String> x, String path, String name) {

        if(name.equals(COMMAND_SAVE_KP_FIN))
        {
            StringBuilder sb = new StringBuilder();

            for (String x_value : x) {
                sb.append(x_value+"!");
                //Добавляем всё в одну строку
            }

            System.out.println("Запись " + sb + System.lineSeparator() + path);

            File filez = new File(path);

            try(FileWriter wf = new FileWriter(filez))
            {
                String s = sb.toString();
                wf.write(s);
            }
            catch (Exception e){
                new Modal_Error().set_erroe_messege("Ошибка записи файла  = " + path);
            }

            sb.setLength(0);


        }

    }

    public static String errorsInKpFin(List<Ostatku> list){

        String resal  = "Вы загрузили старые PLU КП ФИН - > Размер листа :" + list.size() + " позиций. " + System.lineSeparator();
        System.out.println("Размер складского листа : " + Container_KP_Sklad.list_kp_sklad.size());

        for(Ostatku z : list){
            for(Ostatku zxc : Main.classOstatku){
                if(z.plu.equals(zxc.plu)){
                    if(!z.controlChek.equals("EMP")){resal+="ЕСТЬ КП ПО PLU : " + z.getPlu() + System.lineSeparator();}
                }
            }
            for(String skladPlu : Container_KP_Sklad.list_kp_sklad){
                if(z.plu.equals(skladPlu)){
                    if(!z.controlChek.equals("EMP")){resal+="ЕСТЬ КП ( СКЛАДСКОЙ ) ПО PLU : " + z.getPlu() + System.lineSeparator();}
                }
            }
            }
        return resal;
        }





}
