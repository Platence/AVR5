package parser_xml;

import all_controllers.DownloadTask;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import decriptor.ConsoleAVR;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import warehouse_plu.Add_new_Object;

public class SAXPars extends DefaultHandler {


    //String thisElement = "";
    public static StringBuilder thisElement = new StringBuilder();
    public static int count_start_cp = 1;
    public static StringBuilder sb = new StringBuilder();

    public static byte count_for_add = 1;
    public static int size_cieqle = 13;
    public static boolean succefull = false;

    public static int count_size_list = 0;
    public DownloadTask ttt ;
    public static int percent = 51;
    public static boolean newsverka = false;

    @Override
    public void startDocument() throws SAXException {
        ConsoleAVR.printlnn("Start parse XML...");
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        thisElement.setLength(0);
        thisElement.append(qName);

        // Далее метод для прогресс бара
        count_size_list++;

        if(count_size_list>5500)
        {
            ttt.updating(percent++);count_size_list=0;
            ttt.update_message("Добавление объектов... подождите");
            try
            {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {



        if(count_start_cp==0&&!thisElement.toString().equals("")){

                Add_new_Object.init_String(sb.toString(),count_for_add);

                if(!newsverka){ // Если работаем со старой сверкой
                    if(count_for_add==size_cieqle){count_for_add=0;}
                count_for_add++;
                }
                if(newsverka){
                    if(count_for_add==size_cieqle+1){count_for_add=0;}
                    count_for_add++;
                }

        }

        if(sb.toString().equals("Контр. пересчет")){count_start_cp=0;sb.setLength(0);succefull=true;}
        if(sb.toString().equals("Подсчет на ТСД")) {count_start_cp=0;sb.setLength(0);newsverka = true;count_for_add=1;}



        if(!thisElement.toString().equals("")){
            sb.setLength(0);
        }

        thisElement.setLength(0);

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {



        if (thisElement.toString().equals("t")) {
            final String temp_text = new String(ch, start, length);
            sb.append(temp_text);
            //System.out.print(temp_text);
        }
        if (thisElement.toString().equals("v")) {
            final String temp_number_double = new String(ch, start, length);
            sb.append(temp_number_double);
            //System.out.print(temp_number_double);
        }



    }

    @Override
    public void endDocument() {
        System.out.println("Stop parse XML...");
    }

    public static void clear_all_in_parse(){

        count_start_cp = 1;
        sb.setLength(0);

        count_for_add = 1;
        size_cieqle = 13;
        percent = 51; // Не относится к акту КП
    }


}
