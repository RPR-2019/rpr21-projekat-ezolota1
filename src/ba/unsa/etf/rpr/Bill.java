package ba.unsa.etf.rpr;

public class Bill {
    private int id;
    private String novacZaUplatu;
    private Counter brojilo;
    private String mjesec;
    private int godina;
    private boolean placen;

    public Bill(int id, String novacZaUplatu, String mjesec, int godina, Counter brojilo, int placen) {
        this.id = id;
        this.novacZaUplatu = novacZaUplatu;
        this.brojilo = brojilo;
        this.mjesec=mjesec;
        this.godina=godina;
        if(placen==1) this.placen=true;
        else this.placen=false;
    }

    public Bill(String novacZaUplatu, Counter brojilo, String mjesec, int godina, boolean placen) {
        this.novacZaUplatu = novacZaUplatu;
        this.brojilo = brojilo;
        this.mjesec = mjesec;
        this.godina = godina;
        this.placen = placen;
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

    public Counter getBrojilo() {
        return brojilo;
    }

    public void setBrojilo(Counter brojilo) {
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
