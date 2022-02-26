package ba.unsa.etf.rpr;

public class Brojilo {
    private int sifraBrojila;
    private int trenutnoStanje;
    private String hod;
    private Korisnik vlasnik;
    private boolean ocitano;

    public Brojilo() {}

    public Brojilo(int sifraBrojila, int trenutnoStanje, String hod, Korisnik vlasnik, boolean ocitano) {
        this.sifraBrojila = sifraBrojila;
        this.trenutnoStanje = trenutnoStanje;
        this.hod = hod;
        this.vlasnik = vlasnik;
        this.ocitano = ocitano;
    }

    public Brojilo(int sifraBrojila, int trenutnoStanje, String hod, int ocitano) {
        this.sifraBrojila = sifraBrojila;
        this.trenutnoStanje = trenutnoStanje;
        this.hod=hod;
        if(ocitano==1) this.ocitano=true;
        else this.ocitano=false;
    }

    public int getSifraBrojila() {
        return sifraBrojila;
    }

    public void setSifraBrojila(int sifraBrojila) {
        this.sifraBrojila = sifraBrojila;
    }

    public int getTrenutnoStanje() {
        return trenutnoStanje;
    }

    public void setTrenutnoStanje(int trenutnoStanje) {
        this.trenutnoStanje = trenutnoStanje;
    }

    public String getHod() {
        return hod;
    }

    public void setHod(String hod) {
        this.hod = hod;
    }

    public Korisnik getVlasnik() {
        return vlasnik;
    }

    public void setVlasnik(Korisnik vlasnik) {
        this.vlasnik = vlasnik;
    }

    public boolean isOcitano() {
        return ocitano;
    }

    public void setOcitano(boolean ocitano) {
        this.ocitano = ocitano;
    }

    @Override
    public String toString() {
        return ((Integer) sifraBrojila).toString();
    }
}
