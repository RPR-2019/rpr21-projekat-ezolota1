package ba.unsa.etf.rpr;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static javafx.scene.layout.Region.USE_PREF_SIZE;

public class LoginController {
    private PlinDAO dao;
    public Button loginBtn;
    public TextField korisnickoImeFld;
    public PasswordField lozinkaFld;
    private Korisnik k;

    public LoginController() {
        dao = PlinDAO.getInstance();
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
        k=dao.dajKorisnika(korisnickoImeFld.getText());
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
            if(k.getUloga().equals(Uloga.POTROSAC)) {
                FXMLLoader ldr = new FXMLLoader(getClass().getResource("/fxml/usersConsumers.fxml"));
                ArrayList<Integer> brojila = new ArrayList<>();
                brojila.addAll(dao.dajBrojila(korisnickoImeFld.getText()));
                ObservableList<Integer> brojilaObs = FXCollections.observableArrayList(brojila);
                Controller gc = new Controller(korisnickoImeFld.getText(), brojilaObs);
                ldr.setController(gc);
                p = (Parent) ldr.load();
            } else if(k.getUloga().equals(Uloga.POPISIVAC)) {
                FXMLLoader ldr = new FXMLLoader(getClass().getResource("/fxml/usersEmployees.fxml"));
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
