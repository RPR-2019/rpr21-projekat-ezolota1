package ba.unsa.etf.rpr;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private PlinDAO dao;
    public TextField fldText;

    private SimpleStringProperty result=new SimpleStringProperty("");
    public SimpleStringProperty resultProperty(){
        return result;
    }
    public String getResult(){
        return result.get();
    }


    public Controller() {
        dao = PlinDAO.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fldText.textProperty().bindBidirectional(result);
    }

    public void uzmiBrojeve(ActionEvent event) {
        String value = ((Button)event.getSource()).getText();
        if(result.get().equals("0")) result.set(value);
        else result.set(result.get() + value);
    }
}
