package ba.unsa.etf.rpr;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_PREF_SIZE;

public class LoginController {

    public Button loginBtn;
    public TextField korisnickoImeFld;
    public PasswordField lozinkaFld;

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
        return true;
    }

    public void loginAction(ActionEvent event) throws IOException {
        if(verifikacija() == true) {

            Stage myStage = new Stage();
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("/fxml/usersConsumers.fxml"));
            Controller gc = new Controller();
            ldr.setController(gc);
            Parent p =(Parent) ldr.load();
            myStage.setTitle("Grad");
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

        }
    }
}
