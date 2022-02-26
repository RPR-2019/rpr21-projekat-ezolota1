package ba.unsa.etf.rpr;

public class Korisnik {
    private int id;
    private String ime, prezime;
    String korisnickoIme, lozinka;
    private Uloga uloga;

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public Korisnik(int id, String ime, String prezime, String korisnickoIme, String lozinka, Uloga uloga) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.uloga=uloga;
        this.korisnickoIme=korisnickoIme;
        this.lozinka=lozinka;
    }

    public Korisnik(String ime, String prezime, String korisnickoIme, String lozinka, Uloga uloga) {
        this.ime = ime;
        this.prezime = prezime;
        this.uloga=uloga;
        this.korisnickoIme=korisnickoIme;
        this.lozinka=lozinka;
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
