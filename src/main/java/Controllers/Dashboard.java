package Controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Dashboard implements Initializable {


    @FXML
    private BorderPane mainView;

    @FXML
    private Text text;

    @FXML
    private Button login;

    @FXML
    private Button signup;

    @FXML
    private ImageView avatar;

    @FXML
    private Text name;

    @FXML
    private FontAwesomeIcon dropIcon;

    private String typeView;

    private boolean isLogin = false;


    @FXML
    void handlerLogin(ActionEvent event) {
        try {
            FXMLLoader screen = new FXMLLoader(getClass().getResource("/Views/login.fxml"));
            Parent parent = screen.load();
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(new Scene(parent));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handlerSignUp(ActionEvent event) {
        try {
            FXMLLoader screen = new FXMLLoader(getClass().getResource("/Views/signup.fxml"));
            Parent parent = screen.load();
            Stage stage = new Stage();
            stage.setTitle("Sign up");
            stage.setScene(new Scene(parent));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void handlerDashboard(ActionEvent event) {
        changeChildView("listview");
    }

    @FXML
    void handlerAddConference(ActionEvent event) {
        try {
            FXMLLoader screen = new FXMLLoader(getClass().getResource("/Views/addConference.fxml"));
            Parent parent = screen.load();
            Stage stage = new Stage();
            stage.setTitle("Add new conference");
            stage.setScene(new Scene(parent));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    void handlerRefreshView(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/" + typeView + ".fxml"));
            mainView.setCenter(loader.load());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void handleChangeView(ActionEvent actionEvent) {
        String view = ((Button) actionEvent.getSource()).getId();
        changeChildView(view);
    }

    @FXML
    void handlerApproval(ActionEvent actionEvent) {
        String view = ((Button) actionEvent.getSource()).getId();
//        System.out.println(view);
        changeChildView(view);
    }

    @FXML
    void handlerEditConference(ActionEvent actionEvent) {
        String view = ((Button) actionEvent.getSource()).getId();
//        System.out.println(view);
        changeChildView(view);
    }

    @FXML
    void handlerUserManagement(ActionEvent actionEvent) {
        String view = ((Button) actionEvent.getSource()).getId();
//        System.out.println(view);
        changeChildView(view);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void changeChildView(String view){
        try {
            typeView = view;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/" + view + ".fxml"));
            mainView.setCenter(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
