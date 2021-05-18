package tgODDS;

public class InfoForTable {

    private String nameGroup;
    private int summPuls;     // Сумма излишка
    private int summShortage; // Сумма недостачи
    private int notSearhed;   // Сумма с зоной -
    private int countPluKP;   // Кол-во ПЛЮ с группы
    private int resultG;      // результат по группу

    public InfoForTable(String nameGroup, int summPuls, int summShortage, int notSearhed, int countPluKP, int resultG) {
        this.nameGroup = nameGroup;
        this.summPuls = summPuls;
        this.summShortage = summShortage;
        this.notSearhed = notSearhed;
        this.countPluKP = countPluKP;
        this.resultG = resultG;
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public int getSummPuls() {
        return summPuls;
    }

    public int getSummShortage() {
        return summShortage;
    }

    public int getNotSearhed() {
        return notSearhed;
    }

    public int getCountPluKP() {
        return countPluKP;
    }

    public int getResultG() {
        return resultG;
    }

    @Override
    public String toString() {
        return "InfoForTable{" +
                "nameGroup='" + nameGroup + '\'' +
                ", summPuls=" + summPuls +
                ", summShortage=" + summShortage +
                ", notSearhed=" + notSearhed +
                ", countPluKP=" + countPluKP +
                ", resultG=" + resultG +
                '}';
    }
}
