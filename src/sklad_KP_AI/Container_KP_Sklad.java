package sklad_KP_AI;

import all_paths.Paths_Main_File;
import error_package.Modal_Error;
import interfaces_all.Import_and_export;
import sklad_KP_AI.TableViewClass.ListKPSkladOBJ;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.TreeSet;

public class Container_KP_Sklad implements Import_and_export {

    public static TreeSet<String> list_kp_sklad = new TreeSet<>();


    //Чтобы загрузить создаем новый объект этого класса
    //Вызываем saving или load
    //Из других методов обращаемся просто к списку


    @Override
    public TreeSet<String> get_kp_PP_early(String name) {



        if(name.equals("SKLAD")){
            TreeSet<String> temp = get_file_content(Paths_Main_File.kppp_SKLAD_ADR);
            return temp;
        }

        return new TreeSet<String>();
    }

    @Override
    public TreeSet<String> get_file_content(String path) {

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
    public void save_KPPP(TreeSet<String> x, String path,String name) {

        if(name.equals("SKLAD_SAVE"))
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

    public void loading(){

        list_kp_sklad.clear(); //Обязательно обнулим
        //Загружаем лист с диска
        list_kp_sklad = get_kp_PP_early("SKLAD");
        ListKPSkladOBJ.fillAllPos();
    }

    public void saving(){
        save_KPPP(list_kp_sklad,Paths_Main_File.kppp_SKLAD_ADR,"SKLAD_SAVE");
        System.out.println("Размер списка склада : " + list_kp_sklad.size());
        ListKPSkladOBJ.fillAllPos();
    }

    public static void chekAutoSave(){

        File fileCheck = new File(Paths_Main_File.kppp_SKLAD_ADR);

        if(!fileCheck.exists()){
            new Modal_Error().set_erroe_messege("Отсутствует файл " + fileCheck.getPath() + System.lineSeparator() + " " +
                    "Ваш список не будет сохранен автоматически (не документ)," + System.lineSeparator() + "" +
                    "И будет утерян в случае закрытия AVR.");
        }

    }
}
