package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private PlinDAO dao;

    public Controller() {
        dao = PlinDAO.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
