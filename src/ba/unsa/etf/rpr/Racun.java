package ba.unsa.etf.rpr;

import java.util.Date;

public class Racun {
    private int id;
    private double novacZaUplatu;
    private Brojilo brojilo;
    private Date datum;
    private boolean placen;

    public Racun(int id, double novacZaUplatu, Brojilo brojilo, Date datum) {
        this.id = id;
        this.novacZaUplatu = novacZaUplatu;
        this.brojilo = brojilo;
        this.datum = datum;
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

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public boolean isPlacen() {
        return placen;
    }

    public void setPlacen(boolean placen) {
        this.placen = placen;
    }
}
