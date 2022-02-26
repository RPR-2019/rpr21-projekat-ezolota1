package ba.unsa.etf.rpr;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PlinDAO {
    private static PlinDAO instance;
    private Connection conn;
    private PreparedStatement ps;

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

}
