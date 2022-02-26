package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_PREF_SIZE;

public class AdminController {
    public TableView tableViewRacuni;
    public TableColumn colRacunid;
    public TableColumn colRacunIznos;
    public TableColumn colRacunMjesec;
    public TableColumn colRacunGodina;
    public TableColumn colRacunBrojilo;
    public TableColumn colRacunPlacen;
    public Racun izabraniRacun;

    PlinDAO dao = PlinDAO.getInstance();

    public void initialize() {
        posebna();

    }

    private void posebna() {
        ObservableList<Racun> racuni = FXCollections.observableList(dao.racuni());
        colRacunid.setCellValueFactory(new PropertyValueFactory<Racun, Integer>("id"));
        colRacunIznos.setCellValueFactory(new PropertyValueFactory<Racun, String>("novacZaUplatu"));
        colRacunMjesec.setCellValueFactory(new PropertyValueFactory<Racun, String>("mjesec"));
        colRacunGodina.setCellValueFactory(new PropertyValueFactory<Racun, Integer>("godina"));
        colRacunBrojilo.setCellValueFactory(new PropertyValueFactory<Racun, Brojilo>("brojilo"));
        colRacunPlacen.setCellValueFactory(new PropertyValueFactory<Racun, Integer>("placen"));
        tableViewRacuni.setItems(racuni);
    }

    public void dodajRacunAction(ActionEvent event) throws IOException {
        /*
        Stage myStage = new Stage();
        FXMLLoader ldr = new FXMLLoader(getClass().getResource("/fxml/drzava.fxml"));
        DrzavaController dc = new DrzavaController(null, dao.gradovi());
        ldr.setController(dc);
        Parent p =(Parent) ldr.load();
        myStage.setTitle("Država");
        myStage.setScene(new Scene(p, USE_PREF_SIZE, USE_PREF_SIZE));
        myStage.setOnHiding(x -> {
            Drzava d=dc.getDrzava();
            if(d!=null) {
                dao.dodajDrzavu(d);
            }
        });
        myStage.show();

         */
    }

    public void dodajPotrosacaAction(ActionEvent event) throws IOException {
        /*
        Stage myStage = new Stage();
        FXMLLoader ldr = new FXMLLoader(getClass().getResource("/fxml/grad.fxml"));
        GradController gc = new GradController(null, dao.drzave());
        ldr.setController(gc);
        Parent p =(Parent) ldr.load();
        myStage.setTitle("Grad");
        myStage.setScene(new Scene(p, USE_PREF_SIZE, USE_PREF_SIZE));
        myStage.setOnHiding(x -> {
            Grad noviGrad=gc.getGrad();
            if(noviGrad!=null) {
                dao.dodajGrad(noviGrad);
                posebna();
            }
        });
        myStage.show();

         */
    }

    public void dodajPopisivacaAction(ActionEvent event) throws IOException {
        /*
        izabraniGrad=(Grad) tableViewGradovi.getSelectionModel().getSelectedItem();
        if(izabraniGrad!=null) {
            Stage myStage = new Stage();
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("/fxml/grad.fxml"));

            GradController gc = new GradController(izabraniGrad, dao.drzave());
            ldr.setController(gc);

            Parent p = (Parent) ldr.load();

            myStage.setTitle("Grad");
            myStage.setScene(new Scene(p, USE_PREF_SIZE, USE_PREF_SIZE));
            myStage.setOnHiding(x -> {

                Grad novi = gc.getGrad();
                if (novi != null) {

                    System.out.println(izabraniGrad.getNaziv());
                    dao.izmijeniGrad(novi);
                    posebna();
                }
            });

            myStage.show();
        }

         */
    }

    public void dodajBrojiloAction(ActionEvent event) throws IOException {
        /*
        izabraniGrad=(Grad) tableViewGradovi.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Optional<ButtonType> result = alert.showAndWait();
        if(!result.isPresent() || result.get() != ButtonType.OK) {
            return;
        } else {
            dao.obrisiGrad(izabraniGrad);
            tableViewGradovi.setItems(dao.gradove());
            tableViewGradovi.refresh();
        }

         */
    }

    public void izvjestajBrojilaAction(ActionEvent event) throws IOException {
        /*
        try {
            new GradoviReport().showReport(dao.getConnection());
        } catch (JRException e1) {
            e1.printStackTrace();
        }

         */
    }

    public void izvjestajKorisniciAction(ActionEvent event) throws IOException {
        /*
        String jezici[] = { "Bosanski", "Engleski", "Njemački", "Francuski" };
        ChoiceDialog d = new ChoiceDialog(jezici[1], jezici);
        // setheader text
        d.setHeaderText("Jezik");

        // set content text
        d.setContentText("Odaberite željeni jezik: ");

        // show the dialog
        Optional<ButtonType> result = d.showAndWait();
        System.out.println(d.getSelectedItem());

        if(!result.isPresent()) {
            return;
        } else {
            ResourceBundle bundle = ResourceBundle.getBundle("Translation_bs");

            if(d.getSelectedItem().toString().equals("Engleski")) {
                bundle = ResourceBundle.getBundle("Translation_en_US");

            } else if(d.getSelectedItem().toString().equals("Njemački")) {

                bundle = ResourceBundle.getBundle("Translation_de");
            } else if(d.getSelectedItem().toString().equals("Francuski")) {
                bundle = ResourceBundle.getBundle("Translation_fr");
            }
            btnDodajDrzavu.setText(bundle.getString("dodajDrzavu"));
            btnDodajGrad.setText(bundle.getString("dodajGrad"));
            btnIzmijeniGrad.setText(bundle.getString("izmijeniGrad"));
            btnObrisiGrad.setText(bundle.getString("obrisiGrad"));
            btnStampa.setText(bundle.getString("stampa"));
            btnJezik.setText(bundle.getString("jezik"));
        }
    }

         */
    }
}
