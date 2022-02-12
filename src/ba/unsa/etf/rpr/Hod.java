package ba.unsa.etf.rpr;

public class Hod {
    private String oznaka;
    private int ukupanBrojBrojila;

    public Hod(String oznaka, int ukupanBrojBrojila) {
        this.oznaka = oznaka;
        this.ukupanBrojBrojila = ukupanBrojBrojila;
    }

    public String getOznaka() {
        return oznaka;
    }

    public void setOznaka(String oznaka) {
        this.oznaka = oznaka;
    }

    public int getUkupanBrojBrojila() {
        return ukupanBrojBrojila;
    }

    public void setUkupanBrojBrojila(int ukupanBrojBrojila) {
        this.ukupanBrojBrojila = ukupanBrojBrojila;
    }
}
