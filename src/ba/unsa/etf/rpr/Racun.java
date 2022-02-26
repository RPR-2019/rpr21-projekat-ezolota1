package ba.unsa.etf.rpr;

import java.util.Date;

public class Racun {
    private int id;
    private double novacZaUplatu;
    private Brojilo brojilo;
    private String mjesec;
    private int godina;
    private boolean placen;

    public Racun(int id, double novacZaUplatu, Brojilo brojilo, String mjesec, int godina) {
        this.id = id;
        this.novacZaUplatu = novacZaUplatu;
        this.brojilo = brojilo;
        this.mjesec=mjesec;
        this.godina=godina;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getNovacZaUplatu() {
        return novacZaUplatu;
    }

    public void setNovacZaUplatu(double novacZaUplatu) {
        this.novacZaUplatu = novacZaUplatu;
    }

    public Brojilo getBrojilo() {
        return brojilo;
    }

    public void setBrojilo(Brojilo brojilo) {
        this.brojilo = brojilo;
    }

    public String getMjesec() {
        return mjesec;
    }

    public void setMjesec(String mjesec) {
        this.mjesec = mjesec;
    }

    public int getGodina() {
        return godina;
    }

    public void setGodina(int godina) {
        this.godina = godina;
    }

    public boolean isPlacen() {
        return placen;
    }

    public void setPlacen(boolean placen) {
        this.placen = placen;
    }
}
