package ba.unsa.etf.rpr;

public enum Role {
    POTROSAC, POPISIVAC, ADMIN;

    @Override
    public String toString() {
        if(this.equals(POTROSAC)) return "POTROSAC";
        else if(this.equals(POPISIVAC)) return "POPISIVAC";
        return "ADMIN";
    }
}
