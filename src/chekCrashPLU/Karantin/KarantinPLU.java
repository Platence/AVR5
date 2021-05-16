package chekCrashPLU.Karantin;

import java.util.Objects;

public class KarantinPLU {

    private String plu;
    private String desctiption;
    private String reazon;

    public KarantinPLU(String plu, String desctiption, String reazon) {
        this.plu = plu;
        this.desctiption = desctiption;
        this.reazon = reazon;
    }


    public String getPlu() {
        return plu;
    }

    public void setPlu(String plu) {
        this.plu = plu;
    }

    public String getDesctiption() {
        return desctiption;
    }

    public void setDesctiption(String desctiption) {
        this.desctiption = desctiption;
    }

    public String getReazon() {
        return reazon;
    }

    public void setReazon(String reazon) {
        this.reazon = reazon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KarantinPLU that = (KarantinPLU) o;
        return plu.equals(that.plu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plu);
    }
}
