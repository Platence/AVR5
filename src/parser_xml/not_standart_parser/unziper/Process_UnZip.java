package parser_xml.not_standart_parser.unziper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Process_UnZip {

    /*
           1 - Цель
           2 - SharedString
           3 - Sheet_1

           adress_1 - > source
           adress_2 - > dest

           Если нужно добавить ещё, добавляем к if
           путь к желаемым страницам.
           Иначе кастыли и палки в колеса со стороны ОС


            Адреса к ячейкам хранятся тут :
            UnziperNonStandart

     */


    public static void beginn(String adress_1, String adress_2){

        File dir = new File(adress_2);      // create output directory if it doesn't exist
        if(!dir.exists()) dir.mkdirs();


        FileInputStream fis;
        //buffer for read and write data to file

        byte[] buffer = new byte[1024];

        try {

            fis = new FileInputStream(adress_1);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry ze = zis.getNextEntry();

            while(ze != null){

                String fileName = ze.getName();
                File newFile = new File(adress_2 + File.separator + fileName);
                //create directories for sub directories in zip

                new File(newFile.getParent()).mkdirs();


                if(fileName.equals(UnziperNonStandart.name_file_Sheet_1)){

                    //Важный момент передача path_unzip_file
                    // Остальные файлы нас не интересуют ( пока что )

                    UnziperNonStandart.final_path_sheet1 = newFile.getPath();

                    FileOutputStream fos = new FileOutputStream(newFile);
                    int len;

                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                    fos.close();
                    //close this ZipEntry
                    zis.closeEntry();
                    ze = zis.getNextEntry();
                    continue;
                }


                //Shared String
                if(fileName.equals(UnziperNonStandart.name_file_Shared_String)) {


                    //Важный момент передача path_unzip_file
                    // Остальные файлы нас не интересуют ( пока что )

                    UnziperNonStandart.final_path_shared_String = newFile.getPath();
                    FileOutputStream fos = new FileOutputStream(newFile);
                    int len;

                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                    fos.close();
                    //close this ZipEntry
                    zis.closeEntry();
                    ze = zis.getNextEntry();
                    continue;
                }

                ze = zis.getNextEntry();
            }
            //close last ZipEntry
            zis.closeEntry();
            zis.close();
            fis.close();
            dir = null;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
