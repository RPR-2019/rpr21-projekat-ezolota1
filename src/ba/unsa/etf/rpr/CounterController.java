package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class CounterController {
    public TextField fldSifra, fldHod;
    public Counter brojilo;
    public ChoiceBox<User> choiceKorisnik;

    ObservableList<User> korisnici= FXCollections.observableArrayList();
    private boolean iznosIspravan=false;


    public CounterController(ArrayList<User> korisnici) {
        this.korisnici.addAll(korisnici);
    }

    public void initialize() {
        choiceKorisnik.setItems(korisnici);
        choiceKorisnik.getSelectionModel().selectFirst();
    }

    public void cancelAction(ActionEvent event) {
        Stage stage = (Stage) fldHod.getScene().getWindow();
        // do what you have to do
        stage.close();
    }


    public void okAction(ActionEvent event) {
        if (fldSifra.getText().isBlank() || fldHod.getText().isBlank()) {
            fldSifra.getStyleClass().removeAll("poljeJeIspravno");
            fldSifra.getStyleClass().add("poljeNijeIspravno");

        } else {
            fldSifra.getStyleClass().removeAll("poljeNijeIspravno");
            fldSifra.getStyleClass().add("poljeJeIspravno");
            iznosIspravan=true;
        }
        if(iznosIspravan) {
            brojilo=new Counter(Integer.parseInt(fldSifra.getText()), 0, fldHod.getText(), choiceKorisnik.getSelectionModel().getSelectedItem(), false);
            Stage stage = (Stage) fldHod.getScene().getWindow();
            stage.close();
        }

    }

    public Counter getBrojilo() { return brojilo; }
}
