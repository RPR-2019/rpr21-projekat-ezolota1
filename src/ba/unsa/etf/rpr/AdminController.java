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

        Stage myStage = new Stage();
        FXMLLoader ldr = new FXMLLoader(getClass().getResource("/fxml/racun.fxml"));
        RacunController dc = new RacunController(dao.brojila());
        ldr.setController(dc);
        Parent p =(Parent) ldr.load();
        myStage.setTitle("Račun");
        myStage.setScene(new Scene(p, USE_PREF_SIZE, USE_PREF_SIZE));
        myStage.setOnHiding(x -> {
            Racun r=dc.getRacun();
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
        FXMLLoader ldr = new FXMLLoader(getClass().getResource("/fxml/korisnik.fxml"));
        KorisnikController gc = new KorisnikController();
        ldr.setController(gc);
        Parent p =(Parent) ldr.load();
        myStage.setTitle("Korisnik");
        myStage.setScene(new Scene(p, USE_PREF_SIZE, USE_PREF_SIZE));
        myStage.setOnHiding(x -> {
            Korisnik noviKorisnik=gc.getKorisnik();
            if(noviKorisnik!=null) {
                noviKorisnik.setId(dao.odrediIdKorisnika());
                dao.dodajKorisnika(noviKorisnik);
            }
        });
        myStage.show();
    }



    public void dodajBrojiloAction(ActionEvent event) throws IOException {

        Stage myStage = new Stage();
        FXMLLoader ldr = new FXMLLoader(getClass().getResource("/fxml/brojilo.fxml"));
        BrojiloController gc = new BrojiloController(dao.korisnici());
        ldr.setController(gc);
        Parent p =(Parent) ldr.load();
        myStage.setTitle("Brojilo");
        myStage.setScene(new Scene(p, USE_PREF_SIZE, USE_PREF_SIZE));
        myStage.setOnHiding(x -> {
            Brojilo b=gc.getBrojilo();
            if(b!=null) {
                dao.dodajBrojilo(b);
            }
        });
        myStage.show();


    }

    public void izvjestajRacuniAction(ActionEvent event) throws IOException {

        try {
            new IzvjestajRacuni().showReport(dao.getConnection());
        } catch (JRException e1) {
            e1.printStackTrace();
        }


    }

    public void izvjestajBrojilaAction(ActionEvent event) throws IOException {
        try {
            new IzvjestajBrojila().showReport(dao.getConnection());
        } catch (JRException e1) {
            e1.printStackTrace();
        }
    }

    public void izvjestajKorisniciAction(ActionEvent event) throws IOException {
        try {
            new IzvjestajKorisnici().showReport(dao.getConnection());
        } catch (JRException e1) {
            e1.printStackTrace();
        }
    }
}
