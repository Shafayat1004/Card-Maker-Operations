package application.Scenes;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.regex.*;

import application.Database.Credentials;
import application.Database.DatabaseManipulator;
import application.User.Customer.Customer;
import application.User.Employee.CustomerService.CustomerService;
import application.User.Employee.Designer.Designer;
import application.User.Employee.Supervisor.Supervisor;
import application.User.Owner.Owner;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;


public abstract class Controller{
    private Parent root;
    private Scene scene;
    private Stage stage;
    private String emailStructure; //TODO regex to be determined
    protected String emailFromField, passwordFromField, confirmedPasswordFromField, userIDFromField;
    protected Customer customer;
    protected CustomerService customerService;
    protected Owner owner;
    protected Supervisor supervisor;
    protected Designer designer;
    protected Credentials credentials;
    // protected File credentialsDatabase;
    // protected File customerDatabase;
    // protected File CSEmployeeDatabase;
    // protected File SupervisorDatabase;
    // protected File DesignerDatabase;
    // protected File OwnerDatabase;

    public boolean isValidEmail(String inputEmail) {
        //TODO check for validity
        return true;
    }
    public boolean idExistsInDatabase(String inputID, String databaseToLookAt) {
        Credentials retrieved =(Credentials) DatabaseManipulator.getUserDataFromDatabase(inputID, "src/application/Database/Credentials.bin");
        if(retrieved.getUserType().equals(databaseToLookAt)){
            return true;
        }
        else return false;
    }
    public boolean idExistsInDatabase(String inputID) {
        return DatabaseManipulator.existsInDatabase("ID", inputID, "src/application/Database/Credentials.bin");
    }

    public boolean passMatchesForID(String id, String pass) {
        Credentials retrieved =(Credentials) DatabaseManipulator.getUserDataFromDatabase(id, "src/application/Database/Credentials.bin");
        if(retrieved.getPassword().equals(pass)){
            return true;
        }
        else return false;
    }

    public boolean emailExistsInDatabase(String inputEmail) {
        if (DatabaseManipulator.existsInDatabase("Email", inputEmail, "src/application/Database/Credentials.bin")){
            return true;    
        }
        else{
            return false;
        }   
    }

    public void sceneChange(ActionEvent event, String sceneString) throws IOException{
        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource(sceneString));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void sceneChange(ActionEvent event, String sceneString, Object object) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(sceneString));
        root = loader.load();
        if (object instanceof Customer) {
            
            DashboardCustomerController dashboardCustomerController = loader.getController();
            dashboardCustomerController.setCurrentUser(customer);
        } 
        else if (object instanceof CustomerService){
            
            DashboardCSEmpController dashboardCSEmpController = loader.getController();
            dashboardCSEmpController.setCurrentUser(customerService);
        }
        else if (object instanceof Supervisor){
            
            DashboardSupervisorController dashboardSupervisorController = loader.getController();
            dashboardSupervisorController.setCurrentUser(supervisor);
        }
        else if (object instanceof Designer){

            DashboardDesignerController dashboardDesignerController = loader.getController();
            dashboardDesignerController.setCurrentUser(designer);
        }
        else if (object instanceof Owner){
            
            DashboardOwnerController dashboardOwnerController = loader.getController();
            dashboardOwnerController.setCurrentUser(owner);
        }
        else {
            //placeholder
        }

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
