package ba.unsa.etf.rpr;

public class Admin extends User {
    public Admin(int id, String ime, String prezime, String korisnickoIme, String lozinka, Role uloga) {
        super(id, ime, prezime, korisnickoIme, lozinka, uloga);
    }

    public Admin(String ime, String prezime, String korisnickoIme, String lozinka, Role uloga) {
        super(ime, prezime, korisnickoIme, lozinka, uloga);
    }
}
