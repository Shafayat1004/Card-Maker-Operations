package application.Scenes;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import application.Database.DatabaseManipulator;
import application.User.Employee.Supervisor.Supervisor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;

public class DashboardSupervisorController extends Controller{

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
    @FXML private BorderPane newOrdersBorderPane;
    private Supervisor currentUser;

    @FXML
    void signoutButtonOnClick(ActionEvent event) throws IOException {
        sceneChange(event, "WelcomeScene.fxml");
    }
    public void loadNewOrderUI(String fxmlfile) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlfile));
            root = loader.load();
            newOrdersBorderPane.setCenter(root);
        }
        catch (IOException ex) {
            Logger.getLogger(DashboardOwnerController.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    @FXML
    void newOrdersTabOnClick(ActionEvent event) {
        loadNewOrderUI("NewOrders.fxml");
    }

    @FXML
    void initialize() {

        currentUser = (Supervisor)DatabaseManipulator.getCurrentUser();
        idLabel.setText("ID: " + currentUser.getId());
        nameLabel.setText("Name: " + currentUser.getName());

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
