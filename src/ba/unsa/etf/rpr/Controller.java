package ba.unsa.etf.rpr;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import net.sf.jasperreports.engine.JRException;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private PlinDAO dao;
    public TextField fldText;
    public Label pozdravLabel;
    private String korisnickoIme;
    public ChoiceBox<Integer> brojilaChoice=new ChoiceBox<>();
    private ObservableList<Integer> brojila=FXCollections.observableArrayList();

    private SimpleStringProperty result=new SimpleStringProperty("");
    public SimpleStringProperty resultProperty(){
        return result;
    }
    public String getResult(){
        return result.get();
    }


    public Controller(String korisnickoIme, ObservableList<Integer> brojila) {
        this.korisnickoIme=korisnickoIme;
        dao = PlinDAO.getInstance();
        this.brojila=brojila;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pozdravLabel.setText("Pozdrav " + korisnickoIme + "!");
        fldText.textProperty().bindBidirectional(result);
        brojilaChoice.setItems(brojila);
        brojilaChoice.getSelectionModel().selectFirst();
    }

    public void uzmiBrojeve(ActionEvent event) {
        String value = ((Button)event.getSource()).getText();
        if(result.get().equals("0")) result.set(value);
        else result.set(result.get() + value);
    }

    public void potrosnjaAction(ActionEvent event) {
        int potrosnja=Integer.parseInt(fldText.getText());
        if(fldText.getText()!=null) {
            int brojBrojila=brojilaChoice.getSelectionModel().getSelectedItem();
            dao.postaviStanjeBrojila(brojBrojila, potrosnja);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Potvrda");
            alert.setHeaderText("Stanje brojila uspješno dodato");
            alert.show();
        }
    }

    public void racunAction(ActionEvent event) {
        IzvjestajPosljRacun r=new IzvjestajPosljRacun();
        r.setBrojilo(brojilaChoice.getSelectionModel().getSelectedItem().toString());
        try {
            r.showReport(dao.getConnection());
        } catch (JRException e1) {
            e1.printStackTrace();
        }
    }

    public void dugovanjaAction(ActionEvent event) {
        ArrayList<Racun> dugovanja=dao.dugovanja(brojilaChoice.getSelectionModel().getSelectedItem());
        if(dugovanja.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Nepostojeća dugovanja");
            alert.setHeaderText("Trenutno nemate nikakvih dugovanja");

            alert.show();
        } else {
            IzvjestajDugovanja r=new IzvjestajDugovanja();
            r.setBrojilo(brojilaChoice.getSelectionModel().getSelectedItem().toString());
            try {
                r.showReport(dao.getConnection());
            } catch (JRException e1) {
                e1.printStackTrace();
            }
        }
    }
}
