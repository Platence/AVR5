package parser_xml.not_standart_parser.unziper;

public class Field_A_Z {

    public int start_row ;        // С какой строки начинать просчет
    public int cirqle_count;      // Кол-во итераций на строку


    public Field_A_Z(int start_row, int cirqle_count) {
        this.start_row = start_row;
        this.cirqle_count = cirqle_count;
    }

    public int getStart_row() {
        return start_row;
    }

    public void setStart_row(int start_row) {
        this.start_row = start_row;
    }

    public int getCirqle_count() {
        return cirqle_count;
    }

    public void setCirqle_count(int cirqle_count) {
        this.cirqle_count = cirqle_count;
    }
}
