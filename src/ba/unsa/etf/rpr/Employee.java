package ba.unsa.etf.rpr;

public class Employee extends User {

    public Employee(int id, String ime, String prezime, String korisnickoIme, String lozinka, Role uloga) {
        super(id, ime, prezime, korisnickoIme, lozinka, uloga);
    }

    public Employee(String ime, String prezime, String korisnickoIme, String lozinka, Role uloga) {
        super(ime, prezime, korisnickoIme, lozinka, uloga);
    }
}
