package parser_xml;

import all_controllers.DownloadTask;
import all_paths.Paths_Main_File;
import org.xml.sax.SAXException;
import sample.Main;
import settings_main.Settings;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class SaxParsGU {

    public void started(String path, String dest, DownloadTask dt){

        /*
           Здесь dest не работает
           path  - прием пути из главного окна
           Затем зип - распаковка и чтение полученного xml файла.
         */

        UnzipFiles.creater(path, dest);
        System.out.println(path +" PATH !~ " + " " + dest + " dest");

        dt.updating(10);

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = null;
        try {
            parser = factory.newSAXParser();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        dt.updating(50);
        dt.update_message("Распаковка завершена");

        SAXPars saxp = new SAXPars();
        saxp.ttt = dt;  //Передаём ссылку на контроллер прогресс бара дальше


        try
        {

            System.out.println(" SSSSS  " + Settings.standart_Path);
            parser.parse(new File(Settings.standart_Path), saxp);
            //Непосредственно сам парсинг
            dt.updating(99);
        }

        catch (SAXException e)
        {
            e.printStackTrace();
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }


    }
}
