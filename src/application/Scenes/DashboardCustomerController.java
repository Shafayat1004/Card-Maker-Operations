package application.Scenes;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;


import application.Assets.Assets;
import application.Database.DatabaseManipulator;
import application.Order.Order;
import application.User.Customer.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class DashboardCustomerController extends Controller{

    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private Button signoutButton;
    @FXML private Label idLabel;
    @FXML private Label nameLabel;
    @FXML private Label locationLabel;

    @FXML private Tab noticeTab;
    @FXML private Tab createNewOrderTab;
    @FXML private Tab myOrderStatusTab;
    @FXML private Tab fileComplaintTab;
    @FXML private Tab personalInfoTab;
    
    @FXML private ComboBox<String> cardTypeCBox;
    @FXML private ComboBox<String> paperTypeCBox;
    @FXML private ComboBox<String> colorCBox;
    @FXML private TextField orderQuantityTextField;
    @FXML private ComboBox<String> branchCBox;
    @FXML private Button seeSamplesButton;
    @FXML private Button createCustomButton;
    @FXML private Label cardTypeLabel;
    @FXML private Label paperTypeLabel;
    @FXML private Label orderQuantityLabel;
    @FXML private Label deliverByLabel;
    @FXML private Label sampleTypeLabel;
    @FXML private Label colorLabel;
    @FXML private Label deliverToLabel;
    @FXML private TextArea orderNoteArea;
    @FXML private Button placeOrderButton;
    @FXML private DatePicker deliveryByDate;
    
    @FXML private FlowPane createOrderFlowPane;

    private Customer currentUser;

    @FXML
    void signoutButtonOnClick(ActionEvent event) throws IOException {
        sceneChange(event, "WelcomeScene.fxml");
    }
    


/*
================================================================================================================================
                                        vvvvvvvvvvvv< Create New Order Tab >vvvvvvvvvvvvvvvv
================================================================================================================================
*/
    
    @FXML
    void cardTypeCBoxOnSelected(ActionEvent event) {
        if (cardTypeCBox.getValue() != null) {
            paperTypeCBox.setDisable(false);
            cardTypeLabel.setText(cardTypeCBox.getValue());
        }
        else{
            paperTypeCBox.setDisable(true);
        }
    }
    @FXML
    void paperTypeCBoxOnSelected(ActionEvent event) {
        if (paperTypeCBox.getValue() != null) {
            colorCBox.setDisable(false);
            paperTypeLabel.setText(paperTypeCBox.getValue());
        }
        else{
            colorCBox.setDisable(true);
        }
    }
    @FXML
    void colorCBoxOnSelected(ActionEvent event) {
        if (colorCBox.getValue() != null) {
            orderQuantityTextField.setDisable(false);
            colorLabel.setText(colorCBox.getValue());
        }
        else{
            orderQuantityTextField.setDisable(true);
        }
    }
    @FXML
    void quantityOnSelected(ActionEvent event) {
        int quantity = 0;
        try {
            quantity = Integer.parseInt(orderQuantityTextField.getText());
        } catch (NumberFormatException e) {
            showWarningAlert("Invalid Number", "Please enter any number from 1 to 2000");
            return;
        }
            
        if (quantity<=1000 && quantity>=1) {
            branchCBox.setDisable(false);
            orderQuantityLabel.setText("" + quantity);

        } else {
            
            branchCBox.setDisable(true);
            showWarningAlert("Unsupported Quantity", "Please enter any number from 1 to 1000.\nIf your order is larger that 1000, please contact our closest branch directly");

        }
    }
    @FXML
    void branchCBoxOnSelected(ActionEvent event) {
        if (branchCBox.getValue() != null) {
            deliveryByDate.setDisable(false);
            deliverToLabel.setText(branchCBox.getValue());
            
        } else {
            deliveryByDate.setDisable(true);
        }
    }
    @FXML
    void deliveryByDateOnSelected(ActionEvent event) {
        if (deliveryByDate.getValue().compareTo(LocalDate.now()) > 3 ){
            seeSamplesButton.setDisable(false);
            createCustomButton.setDisable(false);
            createOrderFlowPane.setDisable(false);
            deliverByLabel.setText(deliveryByDate.getValue().toString());

        } else {
            seeSamplesButton.setDisable(true);
            createCustomButton.setDisable(true);
            createOrderFlowPane.setDisable(true);
            showWarningAlert("Invalid Date", "Please select any date after 3 Days");
        }
    }
    
    @FXML
    void createCustomButtonOnClick(ActionEvent event) {
        FileChooser imgChooser = new FileChooser();
        imgChooser.getExtensionFilters().add(new ExtensionFilter("Image Files", Assets.imgExtensions));

        File imageFile = imgChooser.showOpenDialog(null);

        if (imageFile!=null) {
            ImageView imageView = new ImageView(new Image(imageFile.toURI().toString()));
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(createOrderFlowPane.getWidth());
            sampleTypeLabel.setText("Custom " + Paths.get(imageView.getImage().getUrl()).getFileName());
            createOrderFlowPane.getChildren().clear();
            createOrderFlowPane.getChildren().add(imageView);
        }
    }
    @FXML
    void seeSamplesButtonOnClick(ActionEvent event) {
        createOrderFlowPane.getChildren().clear();
        File sampleDir = new File("src/application/Database/SampleCards/");
        File[] allSamplesFiles = sampleDir.listFiles();
        ArrayList<ImageView> allImages = new ArrayList<ImageView>();
        for (File file : allSamplesFiles) {
            allImages.add(new ImageView(new Image(file.toURI().toString())));
        }
        for (ImageView imageView : allImages) {
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(createOrderFlowPane.getWidth()/5);
            imageView.setOnMouseClicked(
                e -> {
                    sampleTypeLabel.setText("Premade " + Paths.get(imageView.getImage().getUrl()).getFileName());
                    orderNoteArea.setDisable(false);
                    placeOrderButton.setDisable(false);
                }
            );
        }
        createOrderFlowPane.getChildren().addAll(allImages);

        // File imageFile = new File("src/application/Database/SampleCards/CAL3.jpg");
        // ImageView newImage = new ImageView(new Image(imageFile.toURI().toString()));
        // newImage.setPreserveRatio(true);
        // newImage.setFitWidth(createOrderFlowPane.getWidth()/4);
        // createOrderFlowPane.getChildren().add(newImage);
        System.out.println(currentUser.getMobileNum());


    }


    @FXML
    void placeOrderButtonOnClick(ActionEvent event) {
        if (orderNoteArea.getText() != null && orderNoteArea.getLength() > 100) {
            Alert orderConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
            orderConfirmation.setTitle("Confirmation");
            orderConfirmation.setContentText("Are You Sure you want to place order?");
            orderConfirmation.setHeaderText(null);

            ButtonType yes = new ButtonType("Yes");
            ButtonType no = new ButtonType("No)");
        
            orderConfirmation.getButtonTypes().setAll(yes, no);

            Optional<ButtonType> result = orderConfirmation.showAndWait();
            if(result.get() == yes){
                
                if (currentUser.getMobileNum() == null) {
                    TextInputDialog dialog = new TextInputDialog();
                    dialog.setTitle("Mobile Number");
                    dialog.setHeaderText(null);
                    dialog.setContentText("Please enter your mobile number");
                    
                    Optional<String> input = dialog.showAndWait();
                    if (input.get() != null && (input.get().length() == 11 || input.get().length() == 14)){

                        currentUser.setMobileNum(input.get());
                        DatabaseManipulator.editUser(currentUser.getId(), currentUser, Assets.customersFilePath);
                        currentUser = (Customer)DatabaseManipulator.getObjectFromDatabase(currentUser.getId(), Assets.customersFilePath);

                        System.out.println(currentUser.getMobileNum());

                    }
                    else{
                        showWarningAlert("Important", "Your mobile number is essential for a safe order. Please enter again");
                        dialog.showAndWait();
                    }
                    
                } 
                
                Order newOrder = currentUser.createOrder(cardTypeLabel.getText(), paperTypeLabel.getText(), colorLabel.getText(), orderQuantityLabel.getText(), deliverToLabel.getText(), sampleTypeLabel.getText(), orderNoteArea.getText(), deliveryByDate.getValue());
                
                System.out.println(newOrder.getOrderID());

                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Verification");
                dialog.setHeaderText(null);
                dialog.setContentText("Please enter the Order ID that you received as SMS");
                
                Optional<String> input = dialog.showAndWait();
                
                if(input.get().equals(newOrder.getOrderID())){
                    DatabaseManipulator.writeToDatabase(Assets.ordersFilePath, newOrder, true);
                    showInformationAlert("Success", "Your Order Is Created and Pending approval :)");
                }
                else {
                    showWarningAlert("Mismatch", "Your input does not match the order ID");
                } 

                
            }
            else if(result.get() == no){
                orderConfirmation.close();
            }
        }
        else{
            showInformationAlert("More Information", "You might want to write a bit more about your order for a smooth experience :)");
        }
        
    }
    

/*
================================================================================================================================
                                        ^^^^^^^^^^^</ Create New Order Tab >^^^^^^^^^^^
================================================================================================================================
*/


    
    @FXML
    void initialize() {

        currentUser = (Customer)DatabaseManipulator.getCurrentUser();
        idLabel.setText("ID: " + currentUser.getId());
        nameLabel.setText("Name: " + currentUser.getName());
        
        branchCBox.getItems().addAll(Assets.districts);
        //branchCBox.setValue(branchCBox.getItems().get(0));

        cardTypeCBox.getItems().addAll(Assets.cardTypes);
        //cardTypeCBox.setValue(cardTypeCBox.getItems().get(0));

        paperTypeCBox.getItems().addAll(Assets.paperTypes);
        //paperTypeCBox.setValue(paperTypeCBox.getItems().get(0));
        colorCBox.getItems().addAll(Assets.colorOptions);


        
        assert nameLabel != null : "fx:id=\"nameLabel\" was not injected: check your FXML file 'DashboardCustomer.fxml'.";
        assert idLabel != null : "fx:id=\"idLabel\" was not injected: check your FXML file 'DashboardCustomer.fxml'.";
        assert signoutButton != null : "fx:id=\"signoutButton\" was not injected: check your FXML file 'DashboardCustomer.fxml'.";
        assert noticeTab != null : "fx:id=\"noticeTab\" was not injected: check your FXML file 'DashboardCustomer.fxml'.";
        assert createNewOrderTab != null : "fx:id=\"createNewOrderTab\" was not injected: check your FXML file 'DashboardCustomer.fxml'.";
        assert cardTypeCBox != null : "fx:id=\"cardTypeCBox\" was not injected: check your FXML file 'DashboardCustomer.fxml'.";
        assert paperTypeCBox != null : "fx:id=\"paperTypeCBox\" was not injected: check your FXML file 'DashboardCustomer.fxml'.";
        assert colorCBox != null : "fx:id=\"colorCBox\" was not injected: check your FXML file 'DashboardCustomer.fxml'.";
        assert orderQuantityTextField != null : "fx:id=\"orderQuantityTextField\" was not injected: check your FXML file 'DashboardCustomer.fxml'.";
        assert branchCBox != null : "fx:id=\"branchCBox\" was not injected: check your FXML file 'DashboardCustomer.fxml'.";
        assert deliveryByDate != null : "fx:id=\"deliveryByDate\" was not injected: check your FXML file 'DashboardCustomer.fxml'.";
        assert cardTypeLabel != null : "fx:id=\"cardTypeLabel\" was not injected: check your FXML file 'DashboardCustomer.fxml'.";
        assert paperTypeLabel != null : "fx:id=\"paperTypeLabel\" was not injected: check your FXML file 'DashboardCustomer.fxml'.";
        assert orderQuantityLabel != null : "fx:id=\"orderQuantityLabel\" was not injected: check your FXML file 'DashboardCustomer.fxml'.";
        assert deliverByLabel != null : "fx:id=\"deliverByLabel\" was not injected: check your FXML file 'DashboardCustomer.fxml'.";
        assert sampleTypeLabel != null : "fx:id=\"sampleTypeLabel\" was not injected: check your FXML file 'DashboardCustomer.fxml'.";
        assert orderNoteArea != null : "fx:id=\"orderNoteArea\" was not injected: check your FXML file 'DashboardCustomer.fxml'.";
        assert placeOrderButton != null : "fx:id=\"placeOrderButton\" was not injected: check your FXML file 'DashboardCustomer.fxml'.";
        assert createOrderFlowPane != null : "fx:id=\"createOrderFlowPane\" was not injected: check your FXML file 'DashboardCustomer.fxml'.";
        assert seeSamplesButton != null : "fx:id=\"seeSamplesButton\" was not injected: check your FXML file 'DashboardCustomer.fxml'.";
        assert createCustomButton != null : "fx:id=\"createCustomButton\" was not injected: check your FXML file 'DashboardCustomer.fxml'.";
        assert myOrderStatusTab != null : "fx:id=\"myOrderStatusTab\" was not injected: check your FXML file 'DashboardCustomer.fxml'.";
        assert fileComplaintTab != null : "fx:id=\"fileComplaintTab\" was not injected: check your FXML file 'DashboardCustomer.fxml'.";
        assert personalInfoTab != null : "fx:id=\"personalInfoTab\" was not injected: check your FXML file 'DashboardCustomer.fxml'.";

    }
}
