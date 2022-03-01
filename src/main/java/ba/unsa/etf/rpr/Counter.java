package ba.unsa.etf.rpr;

public class Counter {
    private int counterCode;
    private int currentState;
    private String walk;
    private User owner;
    private boolean read;

    public Counter() {}

    public Counter(int sifraBrojila, int trenutnoStanje, String hod, User vlasnik, boolean ocitano) {
        this.counterCode = sifraBrojila;
        this.currentState = trenutnoStanje;
        this.walk = hod;
        this.owner = vlasnik;
        this.read = ocitano;
    }

    public Counter(int sifraBrojila, int trenutnoStanje, String hod, int ocitano) {
        this.counterCode = sifraBrojila;
        this.currentState = trenutnoStanje;
        this.walk =hod;
        if(ocitano==1) this.read =true;
        else this.read =false;
    }

    public int getCounterCode() {
        return counterCode;
    }

    public void setCounterCode(int counterCode) {
        this.counterCode = counterCode;
    }

    public int getCurrentState() {
        return currentState;
    }

    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }

    public String getWalk() {
        return walk;
    }

    public void setWalk(String walk) {
        this.walk = walk;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    @Override
    public String toString() {
        return ((Integer) counterCode).toString();
    }
}
