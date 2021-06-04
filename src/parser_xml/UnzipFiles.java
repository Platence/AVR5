package parser_xml;

import all_paths.Paths_Main_File;
import decriptor.ConsoleAVR;
import settings_main.Settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnzipFiles {

    public static void creater (String zipFilePath,String destDir) {
        unzip(zipFilePath, destDir);
        System.out.println(zipFilePath + "  ^^^ " + destDir);
    }

    private static void unzip(String zipFilePath, String destDir) {
        File dir = new File(destDir);
        // create output directory if it doesn't exist
        if(!dir.exists()) dir.mkdirs();

        FileInputStream fis;
        //buffer for read and write data to file
        byte[] buffer = new byte[1024];

        try {
            fis = new FileInputStream(zipFilePath);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry ze = zis.getNextEntry();

            while(ze != null){

                String fileName = ze.getName();
                File newFile = new File(destDir + File.separator + fileName);

                //create directories for sub directories in zip

                new File(newFile.getParent()).mkdirs();



                if(fileName.equals(Settings.path_unzip_file)){

                    //Важный момент передача path_unzip_file
                    // Остальные файлы нас не интересуют ( пока что )

                    Settings.standart_Path = newFile.getPath();
                    FileOutputStream fos = new FileOutputStream(newFile);
                    int len;

                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                    fos.close();
                    //close this ZipEntry
                    zis.closeEntry();
                    ze = zis.getNextEntry();

                }


                ze = zis.getNextEntry();
            }
            //close last ZipEntry
            zis.closeEntry();
            zis.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void save_file_String_shares(){

        try {


        }
        catch (Exception e){
            ConsoleAVR.printlnn("! Exception in read xlsx");
        }
    }

}
