package ba.unsa.etf.rpr;

public class Popisivac extends Korisnik {
    private String hod;

    public Popisivac(int id, String ime, String prezime, Uloga uloga, String hod) {
        super(id, ime, prezime, uloga);
        this.hod=hod;
    }

    public String getHod() {
        return hod;
    }

    public void setHod(String hod) {
        this.hod = hod;
    }
}
