package all_paths;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Paths_Main_File {

    /*
      Здесь будут храниться ссылки(адреса) на TEMP и Static файлы
      // Ссылки на все файлы
     */
    public static String path_list_diff_current = "";  // Сюда присваевается желаемый адрес
    public static String path_zip_excel_xml = "src/unzippedFiled/";
    public static String path_Shared_String = "src/unzippedFiled/xl/sharedStrings";
    public static String path_final_out = "";

    // Respiska
    public static String path_to_Raspiska = "src\\binn\\baseFile\\raspiska.xlsx";

    //  KP SKLAD

    public static String kppp_SKLAD_ADR = "src\\binn\\saving\\kppp_plu.txt";
    public final static String PATH_KP_SKLAD_EXCEL = "src/binn/baseFile/KPPP_SKLAD.xlsx";
    public final static String PATH_KP_SKLAD_EXCEL_MOL = "src/binn/baseFile/KPPP_SKLADWHITHMOL.xlsx";

    // date

    public final static String PATH_TO_DATE = "src/binn/datePack/date";
    public final static String PATH_TO_ORDER_DATE = "src/binn/datePack/orderDate";

    // organization + number order

    public final static String PATH_TO_LIST_ORGANIZATION = "src/binn/org/listOrgs";
    public final static String PATH_TO_LAST_SHOP = "src/binn/numbermag/lastmagazine";

    // People comisson

    public final static String PATH_TO_PEOPLE_WORKER = "src/binn/partCommissonpack/comis";

    // Shop Description

    public static String path_shop_excel_xml = "src/unzippedShop/";
    public static String path_SharedStr_shop = "src/unzippedShop/xl/sharedStrings";
    public static String PATH_CURRENT_BASE_SHOP = "NOT LOAD YET";
    public static final String SETTINGS_PATH_SHOP_BASE = "";
    // Вывести заменить final на загрузчик

    // KP FIN PATH
    public final static String PATH_FIN_KP_LIST = "src/binn/saving/finKppp.txt";

    public final static String PATH_FIN_KP_EXCEL = "src/binn/baseFile/kppp_F.xlsx";
    public final static String PATH_FIN_KP_EXCELMOL = "src/binn/baseFile/kppp_FSMENA.xlsx";

    public final static String PATH_FIN_NULL_OST = "src/binn/baseFile/NULLOST.xlsx";
    public final static String PATH_FIN_ODDS = "src/binn/baseFile/ODDSLIST.xlsx";
    public final static String PATH_TG_ODDS = "src/binn/baseFile/TMC_TG.xlsx";
    public final static String Path_TO_SVODPU = "src/binn/baseFile/CreaterSvod.xlsx";


    // ChekBox
    public final static String PATH_SORT_BY_GROUP_VALUE = "src/binn/baseFile/sortvalue";


    // INV 15 Base file
    public static final String PATH_TO_USIALLY_INV15 = "src/binn/baseFile/FFF";

    //Inv 15 Saved File
    public static final String PATH_INFORMATION_INV15 = "src/binn/inv15field/savedinformation";


    public static final String CRASHES_PLU_ADDRES = "src/binn/baseFile/crash";

    public static final String PATH_TO_PASKHA = "src/binn/baseFile/paskha.txt";
    public static final String PATH_APP_ORD = "src/binn/baseFile/ApplicatORDEr.xlsx";
    public static final String PATH_APP_ORDSML = "src/binn/baseFile/ApplicatORDErSML.xlsx";

    public static final String PATH_BLOCK_S999 = "src/binn/S9999Sost";
    public static final String PATH_BLOCK_ALCO = "src/binn/AlcoNeprod";
    public static final String PATH_KARANTIN_PLU = "src/binn/KARANTINPLU.txt";
    public static final String PATH_CHAT_MESSSSS = "src/binn/mesz";
   //public static final String PATH_CHAR_ON_W = "W:\\08_Управление_по_ФК_и_анализу\\Группа КРП\\СПЕЦИАЛИСТЫ\\Списки разниц\\2017\\AVR\\FDBCKVR\\SNMMES";
    public static final String PATH_CHAR_ON_W = "W:\\08_Управление_по_ФК_и_анализу\\Группа КРП\\СПЕЦИАЛИСТЫ\\Списки разниц\\2017\\AVR\\FDBCKVR\\SNMMES";
    public static final String PATH_CHAR_SOURSE_NEW_TG= "TG_ODDS_DETAL.xlsx";

    //public static final String PATH_CHAR_ON_W = "D:\\CREATINGS.txt";


    public static void saveInformation(String path,String text) throws IOException {
        FileWriter wf = new FileWriter(path);
        wf.write(text);
        wf.close();

    }

    public static void saveWithTrue(String path,String text) throws IOException{
        FileWriter wf = new FileWriter(path);
        wf.write(text);
        wf.close();
    }

    public static String loadInformation(String path,String text) throws FileNotFoundException {
        FileReader fr = new FileReader(path);
        Scanner sc = new Scanner(fr);

        while (sc.hasNextLine()){
            String s = sc.nextLine();
            return s;
        }
        return "EMP";
    }




}
