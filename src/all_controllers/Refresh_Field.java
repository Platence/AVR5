package all_controllers;

import parser_xml.SAXPars;
import parser_xml.SaxParsGU;
import sample.Main;
import warehouse_plu.Add_new_Object;
import warehouse_plu.Ostatku;

public class Refresh_Field {

    public void ref_for_sklad_kp(){

        Main.classOstatku.clear(); Ostatku.count_sklad_plu = 0;
        Add_new_Object.null_for_all_field();
        SAXPars.clear_all_in_parse();

    }
}
