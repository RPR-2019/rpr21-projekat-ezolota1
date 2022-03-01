package ba.unsa.etf.rpr;

public abstract class User {
    private int id;
    private String ime, prezime;
    String korisnickoIme, lozinka;
    private Role uloga;

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

    public User(int id, String ime, String prezime, String korisnickoIme, String lozinka, Role uloga) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.uloga=uloga;
        this.korisnickoIme=korisnickoIme;
        this.lozinka=lozinka;
    }

    public User(String ime, String prezime, String korisnickoIme, String lozinka, Role uloga) {
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

    public Role getUloga() {
        return uloga;
    }

    public void setUloga(Role uloga) {
        this.uloga = uloga;
    }

    @Override
    public String toString() {
        return id + ", " + ime + " " + prezime;
    }
}
