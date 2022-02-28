package ba.unsa.etf.rpr;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_PREF_SIZE;

public class LoginController {
    private GasDAO dao;
    public Button loginBtn;
    public TextField korisnickoImeFld;
    public PasswordField lozinkaFld;
    private User k;

    public LoginController() {
        dao = GasDAO.getInstance();
    }

    private boolean verifikacija() {
        boolean greska=false;
        if(korisnickoImeFld.getText().isBlank()) {
            korisnickoImeFld.getStyleClass().add("poljeNijeIspravno");

            korisnickoImeFld.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                    if (n.isEmpty()) {
                        korisnickoImeFld.getStyleClass().add("poljeNijeIspravno");
                    } else {
                        korisnickoImeFld.getStyleClass().removeAll("poljeNijeIspravno");
                    }
                }
            });


            greska=true;
        }
        if(lozinkaFld.getText().isBlank()) {

            lozinkaFld.getStyleClass().add("poljeNijeIspravno");

            lozinkaFld.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                    if (n.isEmpty()) {
                        lozinkaFld.getStyleClass().add("poljeNijeIspravno");
                    } else {
                        lozinkaFld.getStyleClass().removeAll("poljeNijeIspravno");
                    }
                }
            });
            greska=true;
        }
        if(greska) return false;
        k=dao.dajKorisnika(korisnickoImeFld.getText().toString().trim());
        if(k==null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška");
            alert.setHeaderText("Nepostojeći korisnik");

            alert.setResizable(false);
            alert.showAndWait();
            return false;
        }
        if(!k.getLozinka().equals(lozinkaFld.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška");
            alert.setHeaderText("Pogrešna lozinka");

            alert.setResizable(false);
            alert.showAndWait();
            return false;
        }
        return true;
    }

    public void loginAction(ActionEvent event) throws IOException {
        if(verifikacija() == true) {

            Stage myStage = new Stage();
            Parent p=null;
            if(k.getUloga().equals(Role.POTROSAC)) {
                ResourceBundle bundle = ResourceBundle.getBundle("Translation_bs");
                FXMLLoader ldr = new FXMLLoader( getClass().getResource(
                        "/fxml/consumers.fxml"), bundle);

                //FXMLLoader ldr = new FXMLLoader(getClass().getResource("/fxml/consumers.fxml"));
                ArrayList<Integer> brojila = new ArrayList<>();
                brojila.addAll(dao.dajBrojila(korisnickoImeFld.getText()));
                ObservableList<Integer> brojilaObs = FXCollections.observableArrayList(brojila);
                Controller gc = new Controller(korisnickoImeFld.getText(), brojilaObs);
                ldr.setController(gc);
                p = (Parent) ldr.load();
                myStage.setMinHeight(300);
                myStage.setMinWidth(350);
            } else if(k.getUloga().equals(Role.POPISIVAC)) {
                ResourceBundle bundle = ResourceBundle.getBundle("Translation_bs");
                FXMLLoader ldr = new FXMLLoader( getClass().getResource(
                        "/fxml/employees.fxml"), bundle);
                //FXMLLoader ldr = new FXMLLoader(getClass().getResource("/fxml/employees.fxml"));
                ArrayList<Integer> brojila = new ArrayList<>();
                brojila.addAll(dao.dajNepopisanaBrojila());
                ObservableList<Integer> brojilaObs = FXCollections.observableArrayList(brojila);
                EmployeesController gc = new EmployeesController(korisnickoImeFld.getText(), brojilaObs);
                ldr.setController(gc);
                p = (Parent) ldr.load();
            } else {
                FXMLLoader ldr = new FXMLLoader(getClass().getResource("/fxml/admin.fxml"));

                AdminController gc = new AdminController();
                ldr.setController(gc);
                p = (Parent) ldr.load();
                myStage.setMinHeight(500);
                myStage.setMinWidth(620);
            }
            myStage.setTitle("Popisivanje plina");
            myStage.setScene(new Scene(p, USE_PREF_SIZE, USE_PREF_SIZE));
/*
            myStage.setOnHiding(x -> {
                Grad noviGrad=gc.getGrad();
                if(noviGrad!=null) {
                    dao.dodajGrad(noviGrad);
                    posebna();
                }
            });

 */


            myStage.show();
            Stage stage = (Stage) lozinkaFld.getScene().getWindow();
            stage.close();
        }
    }
}
