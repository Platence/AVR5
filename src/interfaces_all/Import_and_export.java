package interfaces_all;

import java.util.TreeSet;

public interface Import_and_export {


    TreeSet<String> get_kp_PP_early(String name);
    //Загружает кп_склад с локального диска
    //На вход подаём параметр - что загружать

    TreeSet<String> get_file_content(String path);

    //Храним файлы с разделителем !   (Удаляем через Pattern)
    // plu!plu!plu!

    void save_KPPP(TreeSet<String> x, String path,String name);

    //Сохранение списка



}
