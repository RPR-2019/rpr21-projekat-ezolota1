package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BrojiloController {
    public TextField fldSifra, fldHod;
    public Brojilo brojilo;
    public ChoiceBox<Korisnik> choiceKorisnik;

    ObservableList<Korisnik> korisnici= FXCollections.observableArrayList();
    private boolean iznosIspravan=false;


    public BrojiloController(ArrayList<Korisnik> korisnici) {
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
            brojilo=new Brojilo(Integer.parseInt(fldSifra.getText()), 0, fldHod.getText(), choiceKorisnik.getSelectionModel().getSelectedItem(), false);
            Stage stage = (Stage) fldHod.getScene().getWindow();
            stage.close();
        }

    }

    public Brojilo getBrojilo() { return brojilo; }
}
