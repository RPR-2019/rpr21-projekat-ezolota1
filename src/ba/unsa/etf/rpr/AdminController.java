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
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_PREF_SIZE;

public class AdminController {
    public TableView tableViewRacuni;
    public TableColumn colRacunid;
    public TableColumn colRacunIznos;
    public TableColumn colRacunMjesec;
    public TableColumn colRacunGodina;
    public TableColumn colRacunBrojilo;
    public TableColumn colRacunPlacen;
    public Bill izabraniRacun;

    GasDAO dao = GasDAO.getInstance();

    public void initialize() {
        posebna();

    }

    private void posebna() {
        ObservableList<Bill> racuni = FXCollections.observableList(dao.racuni());
        colRacunid.setCellValueFactory(new PropertyValueFactory<Bill, Integer>("id"));
        colRacunIznos.setCellValueFactory(new PropertyValueFactory<Bill, String>("novacZaUplatu"));
        colRacunMjesec.setCellValueFactory(new PropertyValueFactory<Bill, String>("mjesec"));
        colRacunGodina.setCellValueFactory(new PropertyValueFactory<Bill, Integer>("godina"));
        colRacunBrojilo.setCellValueFactory(new PropertyValueFactory<Bill, Counter>("brojilo"));
        colRacunPlacen.setCellValueFactory(new PropertyValueFactory<Bill, Integer>("placen"));
        tableViewRacuni.setItems(racuni);
    }

    public void dodajRacunAction(ActionEvent event) throws IOException {

        Stage myStage = new Stage();
        FXMLLoader ldr = new FXMLLoader(getClass().getResource("/fxml/bill.fxml"));
        BillController dc = new BillController(dao.brojila());
        ldr.setController(dc);
        Parent p =(Parent) ldr.load();
        myStage.setTitle("Račun");
        myStage.setScene(new Scene(p, USE_PREF_SIZE, USE_PREF_SIZE));
        myStage.setOnHiding(x -> {
            Bill r=dc.getRacun();
            r.setId(dao.odrediIdRacuna());
            if(r!=null) {
                dao.dodajRacun(r);
                posebna();
            }
        });
        myStage.show();


    }

    public void dodajKorisnikaAction(ActionEvent event) throws IOException {

        Stage myStage = new Stage();
        FXMLLoader ldr = new FXMLLoader(getClass().getResource("/fxml/user.fxml"));
        UserController gc = new UserController();
        ldr.setController(gc);
        Parent p =(Parent) ldr.load();
        myStage.setTitle("Korisnik");
        myStage.setScene(new Scene(p, USE_PREF_SIZE, USE_PREF_SIZE));
        myStage.setOnHiding(x -> {
            User noviKorisnik=gc.getKorisnik();
            if(noviKorisnik!=null) {
                noviKorisnik.setId(dao.odrediIdKorisnika());
                dao.dodajKorisnika(noviKorisnik);
            }
        });
        myStage.show();
    }



    public void dodajBrojiloAction(ActionEvent event) throws IOException {

        Stage myStage = new Stage();
        FXMLLoader ldr = new FXMLLoader(getClass().getResource("/fxml/counter.fxml"));
        CounterController gc = new CounterController(dao.korisnici());
        ldr.setController(gc);
        Parent p =(Parent) ldr.load();
        myStage.setTitle("Brojilo");
        myStage.setScene(new Scene(p, USE_PREF_SIZE, USE_PREF_SIZE));
        myStage.setOnHiding(x -> {
            Counter b=gc.getBrojilo();
            if(b!=null) {
                dao.dodajBrojilo(b);
            }
        });
        myStage.show();


    }

    public void izvjestajRacuniAction(ActionEvent event) throws IOException {

        try {
            new ReportBills().showReport(dao.getConnection());
        } catch (JRException e1) {
            e1.printStackTrace();
        }


    }

    public void izvjestajBrojilaAction(ActionEvent event) throws IOException {
        try {
            new ReportCounters().showReport(dao.getConnection());
        } catch (JRException e1) {
            e1.printStackTrace();
        }
    }

    public void izvjestajKorisniciAction(ActionEvent event) throws IOException {
        try {
            new ReportUsers().showReport(dao.getConnection());
        } catch (JRException e1) {
            e1.printStackTrace();
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

    public void logOutAction (ActionEvent actionEvent) throws IOException {
        Stage myStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        myStage.setTitle("Login");
        myStage.setScene(new Scene(root, USE_PREF_SIZE, USE_PREF_SIZE));
        myStage.show();

        Stage stage = (Stage) tableViewRacuni.getScene().getWindow();
        stage.close();

    }
}
