package ba.unsa.etf.rpr;

public class Bill {
    private int id;
    private String moneyToPay;
    private Counter counter;
    private String month;
    private int year;
    private boolean paid;

    public Bill(int id, String novacZaUplatu, String mjesec, int godina, Counter brojilo, int placen) {
        this.id = id;
        this.moneyToPay = novacZaUplatu;
        this.counter = brojilo;
        this.month =mjesec;
        this.year =godina;
        if(placen==1) this.paid =true;
        else this.paid =false;
    }

    public Bill(String novacZaUplatu, Counter brojilo, String mjesec, int godina, boolean placen) {
        this.moneyToPay = novacZaUplatu;
        this.counter = brojilo;
        this.month = mjesec;
        this.year = godina;
        this.paid = placen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMoneyToPay() {
        return moneyToPay;
    }

    public void setMoneyToPay(String moneyToPay) {
        this.moneyToPay = moneyToPay;
    }

    public Counter getCounter() {
        return counter;
    }

    public void setCounter(Counter counter) {
        this.counter = counter;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
