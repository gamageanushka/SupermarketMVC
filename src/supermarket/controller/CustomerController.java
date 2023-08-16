/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package supermarket.controller;

import supermarket.model.CustomerModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import db.DBConnection;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class CustomerController {

    public String saveCustomer(CustomerModel customerModel) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        String query = "INSERT INTO Customer VALUES(?,?,?,?,?,?,?,?,?)";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, customerModel.getCustId());
        statement.setString(2, customerModel.getTitle());
        statement.setString(3, customerModel.getName());
        statement.setString(4, customerModel.getDob());
        statement.setDouble(5, customerModel.getSalary());
        statement.setString(6, customerModel.getAddress());
        statement.setString(7, customerModel.getCity());
        statement.setString(8, customerModel.getProvince());
        statement.setString(9, customerModel.getPostalCode());

        if (statement.executeUpdate() > 0) {
            return "Success";
        } else {
            return "Fail";
        }

    }

    public ArrayList<CustomerModel> getALllCustomer() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        String query = "SELECT * FROM customer";
        PreparedStatement statement = connection.prepareStatement(query);

        ResultSet rst = statement.executeQuery();
        ArrayList<CustomerModel> customerModels = new ArrayList<>();
        while (rst.next()) {
            CustomerModel cm = new CustomerModel(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDouble(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9));
            customerModels.add(cm);
            
        }
        
        return customerModels;

    }
    
    public CustomerModel searchCustomer(String custId) throws SQLException{
        Connection connection = DBConnection.getInstance().getConnection();

        String query = "SELECT * FROM customer WHERE CustID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, custId);

        ResultSet rst = statement.executeQuery();
        while (rst.next()) {            
            return new CustomerModel(
                    rst.getString(1),
                    rst.getString(2), 
                    rst.getString(3), 
                    rst.getString(4), 
                    rst.getDouble(5),
                    rst.getString(6), 
                    rst.getString(7), 
                    rst.getString(8), 
                    rst.getString(9));
        }
        return null; 
    }
    
    public String updateCustomer(CustomerModel customerModel) throws SQLException{
        Connection connection = DBConnection.getInstance().getConnection();

        String query = "UPDATE customer SET CustTitle = ?, CustName = ?, DOB=? ,salary=?,CustAddress=?,City = ?, Province=?, PostalCode=? WHERE custID =?;";

        PreparedStatement statement = connection.prepareStatement(query);
        
        statement.setString(1, customerModel.getTitle());
        statement.setString(2, customerModel.getName());
        statement.setString(3, customerModel.getDob());
        statement.setDouble(4, customerModel.getSalary());
        statement.setString(5, customerModel.getAddress());
        statement.setString(6, customerModel.getCity());
        statement.setString(7, customerModel.getProvince());
        statement.setString(8, customerModel.getPostalCode());
        statement.setString(9, customerModel.getCustId());

        if (statement.executeUpdate() > 0) {
            return "Success";
        } else {
            return "Fail";
        }
    }
    
    

}
