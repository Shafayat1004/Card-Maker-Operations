package application.User.Customer;


import application.Database.DatabaseManipulator;
import application.Order.Order;
import application.User.User;


public class Customer extends User{
    
    public Customer(String emailFromField, String userIDFromField, String passFromField) {
        super(emailFromField, userIDFromField, passFromField);
    }
    
    public Customer(String userIDFromField) {
        super(userIDFromField);
        this.getClass().cast(DatabaseManipulator.getCustomerDataFromDatabase(id));
        
    }
    
    public Order getOrder(){
        //TODO return order details from details
        return null;
    }


}
