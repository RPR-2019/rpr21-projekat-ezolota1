package ba.unsa.etf.rpr;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class PlinDAO {
    private static PlinDAO instance;
    private Connection conn;
    private PreparedStatement ps, dajBrojilaOdIstogKorisnikaStatement, postaviStanjeStatement,
    dajNeocitanaBrojilaStatement, dajRacuneStatement, dajBrojiloStatement, dajBrojilaStatement,
    dodajRacunStatement, odrediIDRacunuStatement, odrediIDKorisnikuStatement, dodajKorisnikaStatement;

    public static PlinDAO getInstance() {
        if (instance == null) instance = new PlinDAO();
        return instance;
    }

    private PlinDAO() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:baza.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            ps = conn.prepareStatement("SELECT id, ime, prezime, korisnicko_ime, lozinka, uloga FROM korisnik WHERE korisnicko_ime=?");
            dajBrojilaOdIstogKorisnikaStatement = conn.prepareStatement("SELECT sifra, stanje, hod, vlasnik, popisano FROM brojilo WHERE vlasnik=?");
            postaviStanjeStatement = conn.prepareStatement("UPDATE brojilo SET stanje=?, popisano=1 WHERE sifra=?");
            dajNeocitanaBrojilaStatement = conn.prepareStatement("SELECT sifra, stanje, hod, vlasnik, popisano FROM brojilo WHERE popisano=0");
            dajRacuneStatement = conn.prepareStatement("SELECT id, novac_za_uplatu, mjesec, godina, brojilo, placen FROM racun");
            dajBrojiloStatement = conn.prepareStatement("SELECT sifra, stanje, hod, vlasnik, popisano FROM brojilo WHERE sifra=?");
            dajBrojilaStatement = conn.prepareStatement("SELECT sifra, stanje, hod, vlasnik, popisano FROM brojilo");
            dodajRacunStatement = conn.prepareStatement("INSERT INTO racun VALUES(?,?,?,?,?,?)");
            odrediIDRacunuStatement = conn.prepareStatement("SELECT id FROM racun");
            odrediIDKorisnikuStatement = conn.prepareStatement("SELECT id FROM korisnik");
            dodajKorisnikaStatement = conn.prepareStatement("INSERT INTO korisnik VALUES(?,?,?,?,?,?)");
        } catch (SQLException e) {
            regenerisiBazu();
            try {
                ps = conn.prepareStatement("SELECT id, ime, prezime, korisnicko_ime, lozinka, uloga FROM korisnik WHERE korisnicko_ime=?");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }


    }

}

    private void regenerisiBazu() {
        Scanner ulaz = null;
        try {
            ulaz = new Scanner(new FileInputStream("baza.db.sql"));
            String sqlUpit = "";
            while (ulaz.hasNext()) {
                sqlUpit += ulaz.nextLine();
                if ( sqlUpit.length() > 1 && sqlUpit.charAt( sqlUpit.length()-1 ) == ';') {
                    try {
                        Statement stmt = conn.createStatement();
                        stmt.execute(sqlUpit);
                        sqlUpit = "";
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            ulaz.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Korisnik dajKorisnika(String korisnickoIme) {
        try {
            ps.setString(1, korisnickoIme);
            ResultSet rs = ps.executeQuery();
            if(!rs.next()) return null;
            Korisnik korisnik = new Korisnik(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), Uloga.POTROSAC);
            if(rs.getString(6).equals("POPISIVAC")) korisnik.setUloga(Uloga.POPISIVAC);
            if(rs.getString(6).equals("ADMIN")) korisnik.setUloga(Uloga.ADMIN);
            return korisnik;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Integer> dajBrojila(String korisnickoIme) {
        Korisnik k=dajKorisnika(korisnickoIme);
        try {
            dajBrojilaOdIstogKorisnikaStatement.setInt(1, k.getId());
            ResultSet rs = dajBrojilaOdIstogKorisnikaStatement.executeQuery();
            if(!rs.next()) return null;
            ArrayList<Integer> brojila=new ArrayList<>();
            brojila.add(rs.getInt(1));
            while(rs.next()) brojila.add(rs.getInt(1));
            return brojila;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void postaviStanjeBrojila(int brojBrojila, int stanje) {
        try {
            postaviStanjeStatement.setInt(1, stanje);
            postaviStanjeStatement.setInt(2, brojBrojila);
            postaviStanjeStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> dajNepopisanaBrojila() {

        try {

            ResultSet rs = dajNeocitanaBrojilaStatement.executeQuery();
            if(!rs.next()) return null;
            ArrayList<Integer> brojila=new ArrayList<>();
            brojila.add(rs.getInt(1));
            while(rs.next()) brojila.add(rs.getInt(1));
            return brojila;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Brojilo dajBrojilo(int sifra) throws SQLException {
        Brojilo brojilo = new Brojilo();
        dajBrojiloStatement.setInt(1, sifra);
        ResultSet rs=dajBrojiloStatement.executeQuery();
        if(!rs.next()) return null;
        brojilo.setSifraBrojila(rs.getInt(1));
        brojilo.setTrenutnoStanje(rs.getInt(2));
        brojilo.setHod(rs.getString(3));
        //brojilo.setVlasnik(null);
        if(rs.getInt(5)==1) brojilo.setOcitano(true);
        else brojilo.setOcitano(false);
        return brojilo;
    }

    public ArrayList<Racun> racuni() {
        ArrayList<Racun> racuni=new ArrayList<>();
        try {
            ResultSet rs=dajRacuneStatement.executeQuery();
            while(rs.next()) {
                System.out.println("Iznos:" + rs.getString(2));
                Racun racun=new Racun(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), null, rs.getInt(6));
                Brojilo brojilo=dajBrojilo(rs.getInt(5));
                racun.setBrojilo(brojilo);
                racuni.add(racun);

            }
         } catch (SQLException e) {
            e.printStackTrace();
        }

        return racuni;
    }

    public ArrayList<Brojilo> brojila() {
        ArrayList<Brojilo> brojila = new ArrayList<>();
        try {
            ResultSet rs=dajBrojilaStatement.executeQuery();
            while(rs.next()) {
                Brojilo b=new Brojilo(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(5));
                brojila.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return brojila;
    }

    public void dodajRacun(Racun r) {
        try {
            dodajRacunStatement.setInt(1, r.getId());
            dodajRacunStatement.setString(2, r.getNovacZaUplatu());
            dodajRacunStatement.setString(3, r.getMjesec());
            dodajRacunStatement.setInt(4, r.getGodina());
            dodajRacunStatement.setInt(5, r.getBrojilo().getSifraBrojila());
            if(r.isPlacen()) dodajRacunStatement.setInt(6, 1);
            else dodajRacunStatement.setInt(6, 0);
            dodajRacunStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int odrediIdRacuna() {
        int id=0;
        try {
            ResultSet rs=odrediIDRacunuStatement.executeQuery();
            while(rs.next()) id=rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id+1;
    }

    public int odrediIdKorisnika() {
        int id=0;
        try {
            ResultSet rs=odrediIDKorisnikuStatement.executeQuery();
            while(rs.next()) id=rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id+1;
    }

    public void dodajKorisnika(Korisnik k) {
        try {
            dodajKorisnikaStatement.setInt(1, k.getId());
            dodajKorisnikaStatement.setString(2, k.getIme());
            dodajKorisnikaStatement.setString(3, k.getPrezime());
            dodajKorisnikaStatement.setString(4, k.getKorisnickoIme());
            dodajKorisnikaStatement.setString(5, k.getLozinka());
            if(k.getUloga().equals(Uloga.POTROSAC)) dodajKorisnikaStatement.setString(6, "POTROSAC");
            else dodajKorisnikaStatement.setString(6, "POPISIVAC");
            dodajKorisnikaStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
