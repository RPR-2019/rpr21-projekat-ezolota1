package ba.unsa.etf.rpr;

public abstract class Korisnik {
    private int id;
    private String ime, prezime;
    private Uloga uloga;

    public Korisnik(int id, String ime, String prezime, Uloga uloga) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.uloga=uloga;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Uloga getUloga() {
        return uloga;
    }

    public void setUloga(Uloga uloga) {
        this.uloga = uloga;
    }
}
