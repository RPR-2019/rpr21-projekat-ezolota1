package ba.unsa.etf.rpr;

public class Consumer extends User {
    public Consumer(int id, String ime, String prezime, String korisnickoIme, String lozinka, Role uloga) {
        super(id, ime, prezime, korisnickoIme, lozinka, uloga);
    }

    public Consumer(String ime, String prezime, String korisnickoIme, String lozinka, Role uloga) {
        super(ime, prezime, korisnickoIme, lozinka, uloga);
    }
}
