package cours.controller;


import cours.java.model.Personne;
import cours.java.model.Rue;
import cours.java.model.Ville;
import cours.utils.Fabrique;
import cours.utils.Utils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class PersonneController implements Initializable {
    @FXML
    private TableView<Personne> tbPersonne;
    @FXML
    public ChoiceBox<Ville> cbxVille;
    @FXML
    public ChoiceBox<Rue> cbxRue;
    public Button btnAjouter;
    public Button btnModifier;
    public Button btnSupprimer;
    public TableColumn<Personne, String> colNom;
    public TableColumn<Personne, String> colPrenom;
    public TableColumn<Personne, String> colDateNaiss;
    public TableColumn<Personne, String> colTelephone;
    public TableColumn<Personne, String> colAdresse;
    @FXML
    private TextField tfdNom;

    @FXML
    private TextField tfdPrenom;

    @FXML
    private DatePicker dpDateNaissance;

    @FXML
    private TextField tfdTelephone;

    long idModify;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            List<Ville> listeVille = Fabrique.getiVille().findAll();
            cbxVille.setItems(FXCollections.observableArrayList(listeVille));

            chargerTable();

            cbxVille.valueProperty().addListener(new ChangeListener<Ville>() {
                @Override
                public void changed(ObservableValue<? extends Ville> observable, Ville oldValue, Ville newValue) {

                    try{
                        int idVille = (int) cbxVille.getValue().getId();
                        List<Rue> listeRue = Fabrique.getiRue().findByVilleId(idVille);
                        cbxRue.setItems(FXCollections.observableArrayList(listeRue));

                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }

            });

            tbPersonne.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showPersonne(newValue));
        } catch (Exception e) {

        }

    }

    public void savePersonne(ActionEvent event) throws Exception {
        if(verifierChamp()){
            Personne p = new Personne();
            p.setNom(tfdNom.getText());
            p.setPrenom(tfdPrenom.getText());
            p.setDatenaissance(dpDateNaissance.getValue().toString());
            p.setTel(tfdTelephone.getText());
            p.setRue(cbxRue.getValue());
            Fabrique.getiPersonne().add(p);
            Utils.showMessage("Gestion des personnes", "Message de succés", "Personne Sauvegarder!!");
            chargerTable();
            viderChamp();
        }else{
            Utils.showMessage("Gestion des personnes", "Message d'Erreur", "Veuillez Remplir tous les Champs!");
        }


    }

    public void chargerTable() {
        colNom.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getNom()));
        colPrenom.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getPrenom()));
        colDateNaiss.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getDatenaissance()));
        colTelephone.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getTel()));
        colAdresse.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getRue().toString()+" (" +cellData.getValue().getRue().getVille().toString() + " )"));
        try {
            tbPersonne.setItems(FXCollections.
                    observableArrayList(Fabrique.getiPersonne().getAllPersonne()));
        }
        catch(Exception e){
            Utils.showMessage("Cours RMI","Gestion des Personne","Erreur : "+e.getMessage());
        }
    }

    public void viderChamp() throws Exception {
        tfdNom.setText("");
        tfdPrenom.setText("");
        tfdTelephone.setText("");
        dpDateNaissance.setValue(null);
    }

    public boolean verifierChamp(){
        if(tfdNom.getText().trim().isEmpty() || tfdPrenom.getText().trim().isEmpty() || tfdTelephone.getText().trim().isEmpty() || dpDateNaissance.getValue()==null || cbxVille.getValue()==null || cbxRue.getValue() == null)
        {
            return false;

        }else{
            return true;
        }
    }

    private void showPersonne(Personne personne){
        if(personne != null){
            idModify = personne.getId();
            tfdNom.setText(personne.getNom());
            tfdPrenom.setText(personne.getPrenom());
            tfdTelephone.setText(personne.getTel());
            dpDateNaissance.setValue(LocalDate.parse(personne.getDatenaissance()));
            btnModifier.setDisable(false);
            btnSupprimer.setDisable(false);
        }
    }

    public void modifyPersonne(ActionEvent event) throws Exception {
        Personne p = new Personne();
        p.setId(idModify);
        p.setNom(tfdNom.getText());
        p.setPrenom(tfdPrenom.getText());
        p.setDatenaissance(dpDateNaissance.getValue().toString());
        p.setTel(tfdTelephone.getText());
        p.setRue(cbxRue.getValue());
        Fabrique.getiPersonne().update(p);
        viderChamp();
        chargerTable();
    }

    public void deletePersonne(ActionEvent event) throws Exception {
        Fabrique.getiPersonne().deletePersonne(idModify);
        viderChamp();
        chargerTable();
    }
}
