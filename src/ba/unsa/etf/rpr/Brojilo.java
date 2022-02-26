package ba.unsa.etf.rpr;

public class Brojilo {
    private int sifraBrojila;
    private int trenutnoStanje;
    private Hod hod;
    private Potrosac vlasnik;

    public Brojilo(int sifraBrojila, int trenutnoStanje, Hod hod) {
        this.sifraBrojila = sifraBrojila;
        this.trenutnoStanje = trenutnoStanje;
        this.hod=hod;
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

    public Hod getHod() {
        return hod;
    }

    public void setHod(Hod hod) {
        this.hod = hod;
    }

    public Korisnik getVlasnik() {
        return vlasnik;
    }

    public void setVlasnik(Potrosac vlasnik) {
        this.vlasnik = vlasnik;
    }
}
