package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UserController {
    public TextField fldIme, fldPrezime, fldKorisnickoIme, fldLozinka;
    public User korisnik;

    public CheckBox popisivac;
    private boolean ispravnost=false;


    public UserController() {}


    public void cancelAction(ActionEvent event) {
        Stage stage = (Stage) fldIme.getScene().getWindow();
        // do what you have to do
        stage.close();
    }


    public void okAction(ActionEvent event) {
        if (fldIme.getText().isBlank() || fldPrezime.getText().isBlank() || fldKorisnickoIme.getText().isBlank() || fldLozinka.getText().isBlank()) {
            fldIme.getStyleClass().removeAll("poljeIspravno");
            fldIme.getStyleClass().add("poljeNijeIspravno");

            fldPrezime.getStyleClass().removeAll("poljeIspravno");
            fldPrezime.getStyleClass().add("poljeNijeIspravno");

            fldKorisnickoIme.getStyleClass().removeAll("poljeIspravno");
            fldKorisnickoIme.getStyleClass().add("poljeNijeIspravno");

            fldLozinka.getStyleClass().removeAll("poljeIspravno");
            fldLozinka.getStyleClass().add("poljeNijeIspravno");

        } else {
            fldIme.getStyleClass().removeAll("poljeNijeIspravno");
            fldIme.getStyleClass().add("poljeIspravno");

            fldPrezime.getStyleClass().removeAll("poljeNijeIspravno");
            fldPrezime.getStyleClass().add("poljeIspravno");

            fldKorisnickoIme.getStyleClass().removeAll("poljeNijeIspravno");
            fldKorisnickoIme.getStyleClass().add("poljeIspravno");

            fldLozinka.getStyleClass().removeAll("poljeNijeIspravno");
            fldLozinka.getStyleClass().add("poljeIspravno");
            ispravnost=true;
        }
        if(ispravnost) {
            korisnik=new User(fldIme.getText(), fldPrezime.getText(), fldKorisnickoIme.getText(), fldLozinka.getText(), Role.POTROSAC);
            if(popisivac.isSelected()) korisnik.setUloga(Role.POPISIVAC);
            Stage stage = (Stage) fldIme.getScene().getWindow();
            stage.close();
        }

    }

    public User getKorisnik() { return korisnik; }
}
