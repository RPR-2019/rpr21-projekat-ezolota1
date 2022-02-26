package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class KorisnikController {
    public TextField fldIme, fldPrezime, fldKorisnickoIme, fldLozinka;
    public Korisnik korisnik;

    public CheckBox popisivac;
    private boolean ispravnost=false;


    public KorisnikController() {}


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
            korisnik=new Korisnik(fldIme.getText(), fldPrezime.getText(), fldKorisnickoIme.getText(), fldLozinka.getText(), Uloga.POTROSAC);
            if(popisivac.isSelected()) korisnik.setUloga(Uloga.POPISIVAC);
            Stage stage = (Stage) fldIme.getScene().getWindow();
            stage.close();
        }

    }

    public Korisnik getKorisnik() { return korisnik; }
}
