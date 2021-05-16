package exportXLSX.inv15i;

public class InformationX  {

    private String date_prikaz_x;
    private String date_INVENT;
    private String number_prikaz_x;
    private String pko;
    private String rko;
    private String organizacuya;
    private String struktyrnoe_podrazdelenie;
    public String fullAdress;


    public InformationX(String date_prikaz_x, String date_INVENT, String number_prikaz_x, String pko,
                        String rko, String organizacuya, String struktyrnoe_podrazdelenie, String fullAdress) {

        this.date_prikaz_x = date_prikaz_x;
        this.date_INVENT = date_INVENT;
        this.number_prikaz_x = number_prikaz_x;
        this.pko = pko;
        this.rko = rko;
        this.organizacuya = organizacuya;
        this.struktyrnoe_podrazdelenie = struktyrnoe_podrazdelenie;
        this.fullAdress = fullAdress;
    }

    public InformationX(){}



    public String getDate_prikaz_x() {
        return date_prikaz_x;
    }

    public void setDate_prikaz_x(String date_prikaz_x) {
        this.date_prikaz_x = date_prikaz_x;
    }

    public String getDate_INVENT() {
        return date_INVENT;
    }

    public void setDate_INVENT(String date_INVENT) {
        this.date_INVENT = date_INVENT;
    }

    public String getNumber_prikaz_x() {
        return number_prikaz_x;
    }

    public void setNumber_prikaz_x(String number_prikaz_x) {
        this.number_prikaz_x = number_prikaz_x;
    }

    public String getPko() {
        return pko;
    }

    public void setPko(String pko) {
        this.pko = pko;
    }

    public String getRko() {
        return rko;
    }

    public void setRko(String rko) {
        this.rko = rko;
    }

    public String getOrganizacuya() {
        return organizacuya;
    }

    public void setOrganizacuya(String organizacuya) {
        this.organizacuya = organizacuya;
    }

    public String getStruktyrnoe_podrazdelenie() {
        return struktyrnoe_podrazdelenie;
    }

    public void setStruktyrnoe_podrazdelenie(String struktyrnoe_podrazdelenie) {
        this.struktyrnoe_podrazdelenie = struktyrnoe_podrazdelenie;
    }

    public String getFullAdress() {
        return fullAdress;
    }

    public void setFullAdress(String fullAdress) {
        this.fullAdress = fullAdress;
    }
}
