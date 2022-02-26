package ba.unsa.etf.rpr;

import java.util.Date;

public class Racun {
    private int id;
    private String novacZaUplatu;
    private Brojilo brojilo;
    private String mjesec;
    private int godina;
    private boolean placen;

    public Racun(int id, String novacZaUplatu, String mjesec, int godina, Brojilo brojilo, int placen) {
        this.id = id;
        this.novacZaUplatu = novacZaUplatu;
        this.brojilo = brojilo;
        this.mjesec=mjesec;
        this.godina=godina;
        if(placen==1) this.placen=true;
        else this.placen=false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNovacZaUplatu() {
        return novacZaUplatu;
    }

    public void setNovacZaUplatu(String novacZaUplatu) {
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
