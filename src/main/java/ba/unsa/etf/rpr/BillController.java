package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class BillController {
    public TextField iznosFld, godinaFld;
    public Bill racun;
    public ChoiceBox<Counter> choiceBrojilo;
    public ChoiceBox<String> choiceMjesec;
    ObservableList<Counter> brojila= FXCollections.observableArrayList();
    private boolean iznosIspravan=false;


    public BillController(ArrayList<Counter> brojila) {
        this.brojila.addAll(brojila);
    }

    public void initialize() {
        choiceBrojilo.setItems(brojila);
        choiceBrojilo.getSelectionModel().selectFirst();
        ArrayList<String> mjeseci=new ArrayList<>();
        mjeseci.add("Januar"); mjeseci.add("Februar"); mjeseci.add("Mart"); mjeseci.add("April");
        mjeseci.add("Maj"); mjeseci.add("Juni"); mjeseci.add("Juli"); mjeseci.add("August");
        mjeseci.add("Septembar"); mjeseci.add("Oktobar"); mjeseci.add("Novembar"); mjeseci.add("Decembar");
        ObservableList<String> mjesecii=FXCollections.observableArrayList(mjeseci);
        choiceMjesec.setItems(mjesecii);
        choiceMjesec.getSelectionModel().select(1);
        iznosFld.setText("2022");
    }

    public void cancelAction(ActionEvent event) {
        Stage stage = (Stage) iznosFld.getScene().getWindow();
        // do what you have to do
        stage.close();
    }


    public void okAction(ActionEvent event) {
        if (iznosFld.getText().isBlank() || Integer.parseInt(iznosFld.getText())<0) {
            iznosFld.getStyleClass().removeAll("poljeJeIspravno");
            iznosFld.getStyleClass().add("poljeNijeIspravno");

        } else {
            iznosFld.getStyleClass().removeAll("poljeNijeIspravno");
            iznosFld.getStyleClass().add("poljeJeIspravno");
            iznosIspravan=true;
        }
        if(iznosIspravan) {
            racun=new Bill(iznosFld.getText(), choiceBrojilo.getSelectionModel().getSelectedItem(), choiceMjesec.getSelectionModel().getSelectedItem(), Integer.parseInt(godinaFld.getText()), false);
            Stage stage = (Stage) iznosFld.getScene().getWindow();
            stage.close();
        }

    }

    public Bill getRacun() { return racun; }
}
