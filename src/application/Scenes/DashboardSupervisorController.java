package application.Scenes;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;

public class DashboardSupervisorController extends SceneChanger{

    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private Button signoutButton;
    @FXML private Label idLabel;
    @FXML private Label nameLabel;
    @FXML private Label locationLabel;
    @FXML private Tab noticeTab;
    @FXML private Tab newOrdersTab;
    @FXML private Tab ongoingOrdersTab;
    @FXML private Tab completedOrdersTab;
    @FXML private Tab transactionsTab;
    @FXML private Tab branchEmpListTab;
    @FXML private Tab designerListTab;
    @FXML private Tab personalInfoTab;
    @FXML private Tab complaintsTab;


    @FXML
    void signoutButtonOnClick(ActionEvent event) throws IOException {
        sceneChange(event, "WelcomeScene.fxml");
    }

    @FXML
    void initialize() {
        assert signoutButton != null : "fx:id=\"signoutButton\" was not injected: check your FXML file 'DashboardSupervisor.fxml'.";
        assert idLabel != null : "fx:id=\"idLabel\" was not injected: check your FXML file 'DashboardSupervisor.fxml'.";
        assert nameLabel != null : "fx:id=\"nameLabel\" was not injected: check your FXML file 'DashboardSupervisor.fxml'.";
        assert locationLabel != null : "fx:id=\"locationLabel\" was not injected: check your FXML file 'DashboardSupervisor.fxml'.";
        assert noticeTab != null : "fx:id=\"noticeTab\" was not injected: check your FXML file 'DashboardSupervisor.fxml'.";
        assert newOrdersTab != null : "fx:id=\"newOrdersTab\" was not injected: check your FXML file 'DashboardSupervisor.fxml'.";
        assert ongoingOrdersTab != null : "fx:id=\"ongoingOrdersTab\" was not injected: check your FXML file 'DashboardSupervisor.fxml'.";
        assert completedOrdersTab != null : "fx:id=\"completedOrdersTab\" was not injected: check your FXML file 'DashboardSupervisor.fxml'.";
        assert transactionsTab != null : "fx:id=\"transactionsTab\" was not injected: check your FXML file 'DashboardSupervisor.fxml'.";
        assert branchEmpListTab != null : "fx:id=\"branchEmpListTab\" was not injected: check your FXML file 'DashboardSupervisor.fxml'.";
        assert designerListTab != null : "fx:id=\"designerListTab\" was not injected: check your FXML file 'DashboardSupervisor.fxml'.";
        assert personalInfoTab != null : "fx:id=\"personalInfoTab\" was not injected: check your FXML file 'DashboardSupervisor.fxml'.";
        assert complaintsTab != null : "fx:id=\"complaintsTab\" was not injected: check your FXML file 'DashboardSupervisor.fxml'.";

    }
}
