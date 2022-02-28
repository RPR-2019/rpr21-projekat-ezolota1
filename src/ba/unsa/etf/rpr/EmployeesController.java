package ba.unsa.etf.rpr;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_PREF_SIZE;

public class EmployeesController implements Initializable {
    private GasDAO dao;
    public TextField fldText;
    public Label pozdravLabel, brojBrojilaLabel;
    private String korisnickoIme;
    public ChoiceBox<Integer> brojilaChoice=new ChoiceBox<>();
    private ObservableList<Integer> brojila=FXCollections.observableArrayList();
    public Button potvrdiButton;

    private SimpleStringProperty result=new SimpleStringProperty("");
    public SimpleStringProperty resultProperty(){
        return result;
    }
    public String getResult(){
        return result.get();
    }


    public EmployeesController(String korisnickoIme, ObservableList<Integer> brojila) {
        this.korisnickoIme=korisnickoIme;
        dao = GasDAO.getInstance();
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

    public void akcijaKraj(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void akcijaAbout(ActionEvent actionEvent) throws IOException {
        Stage myStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/about.fxml"));
        myStage.setTitle("About");
        myStage.setScene(new Scene(root, USE_PREF_SIZE, USE_PREF_SIZE));
        myStage.show();

    }


    public void akcijaBosanski(ActionEvent actionEvent) {

        Stage stage = (Stage) fldText.getScene().getWindow();
        stage.setTitle("Popisivanje plina");
        ResourceBundle bundle = ResourceBundle.getBundle("Translation_bs");

        pozdravLabel.setText("Pozdrav " + korisnickoIme + "!");
        brojBrojilaLabel.setText(bundle.getString("brojBrojila"));
        potvrdiButton.setText(bundle.getString("potvrdiPotrosnju"));


    }

    public void akcijaEngleski(ActionEvent actionEvent) {
        Stage stage = (Stage) fldText.getScene().getWindow();
        stage.setTitle("Gas inventory");
        ResourceBundle bundle = ResourceBundle.getBundle("Translation_en_US");
        pozdravLabel.setText("Hello " + korisnickoIme + "!");
        brojBrojilaLabel.setText(bundle.getString("brojBrojila"));
        potvrdiButton.setText(bundle.getString("potvrdiPotrosnju"));

    }

    public void logOutAction (ActionEvent actionEvent) throws IOException {
        Stage myStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        myStage.setTitle("Login");
        myStage.setScene(new Scene(root, USE_PREF_SIZE, USE_PREF_SIZE));
        myStage.show();

        Stage stage = (Stage) pozdravLabel.getScene().getWindow();
        stage.close();

    }
}
