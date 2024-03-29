package Controllers;

import Handlers.JoinTheConferenceHandler;
import Models.Account;
import Models.Conference;
import Models.Join;
import Models.JoinTheConference;
import Utils.AlertDialog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class RegisterationListController implements Initializable {

    @FXML
    private TableView<Join> tableView;
    int accountID = DashboardUserController.account.getAccountId();
    ObservableList<Join> joinConferenceLists = null;
    Callback<TableColumn<Join, String>, TableCell<Join, String>> cancelFactory;

    TableColumn<Join, Integer> id;
    TableColumn<Join, String> name;
    TableColumn<Join, String> place;
    TableColumn<Join, LocalDate> startDate;
    TableColumn<Join, LocalDate> endDate;
    TableColumn cancelCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id = new TableColumn<>("ID");
        id.setCellValueFactory(new PropertyValueFactory<Join, Integer>("id"));
        name = new TableColumn<>("Name");
        name.setCellValueFactory(new PropertyValueFactory<Join, String>("name"));
        place = new TableColumn<>("Place");
        place.setCellValueFactory(new PropertyValueFactory<Join, String>("place"));
        startDate = new TableColumn<>("Start date");
        startDate.setCellValueFactory(new PropertyValueFactory<Join, LocalDate>("startDate"));
        endDate = new TableColumn<>("End date");
        endDate.setCellValueFactory(new PropertyValueFactory<Join, LocalDate>("endDate"));

        cancelFactory = new Callback<TableColumn<Join, String>, TableCell<Join, String>>() {
            @Override
            public TableCell call(final TableColumn<Join, String> param) {
                return new TableCell<Join, String>() {

                    Button btn = new Button("Cancel");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                 Join joinConference = getTableView().getItems().get(getIndex());
                                 int confId = joinConference.getId();
                                if(JoinTheConferenceHandler.delete(confId)){
                                    AlertDialog.showAlertWithoutHeaderText("Alert", "Successfully", "success");
                                    refreshTable();
                                }else{
                                    AlertDialog.showAlertWithoutHeaderText("Alert", "Failed! Something went wrong", "failed");
                                }
                            });
                            btn.setStyle("-fx-background-color: #ea0000");
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
            }
        };

        refreshTable();

    }

    public void refreshTable(){
        tableView.getItems().clear();
        tableView.getColumns().clear();
        
        cancelCol = new TableColumn("Action");
        cancelCol.setCellValueFactory(new PropertyValueFactory<>("Cancel"));

        List<Join> lists = JoinTheConferenceHandler.loadUserJoinConference(accountID);
        joinConferenceLists = FXCollections.observableList(lists);
        tableView.setItems(joinConferenceLists);
        cancelCol.setCellFactory(cancelFactory);
        tableView.getColumns().addAll(id, name, place,startDate, endDate, cancelCol);
    }
}
