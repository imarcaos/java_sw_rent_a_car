/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java_mm_rent_a_car.classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Marcos Melo
 */
public class Customer {
    
    private int id;
    private String fullname;
    private int nif;
    private String birthdate;
    private String phone;
    private String email;
    private String address;

    public Customer() {
    }

    public Customer(int id, String fullname, int nif, String birthdate, String phone, String email, String address) {
        this.id = id;
        this.fullname = fullname;
        this.nif = nif;
        this.birthdate = birthdate;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    /*
        int id, String fullname, int nif, String birthdate, String phone, String email, String address
    */
    
    // Method to add a new Customer
    public void addCustomer(String _fullname, int _nif, String _birthdate, String _phone, String _email, String _address) {
        String insertQuery = "INSERT INTO mc_car_rental.customers (fullname, nif, birth_date, phone, email, address) VALUES(?, ?, ?, ?, ?, ?);";
        PreparedStatement ps;
        try {
            ps = DB.getConnection().prepareStatement(insertQuery);
            ps.setString(1, _fullname);
            ps.setInt(2, _nif);
            ps.setString(3, _birthdate);
            ps.setString(4, _phone);
            ps.setString(5, _email);
            ps.setString(6, _address);
            
            if(ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "Cliente adicionada com sucesso!", "Adicionar Cliente", 1);
                //System.out.println("Localização adicionada");
            } else {
                JOptionPane.showMessageDialog(null, "Cliente não adicionado", "Adicionar Cliente", 2);
                //System.out.println("Localização não adicionada");
            }
        } catch (SQLException ex) {
            System.out.println("Erro addCustomer: " + ex.getMessage());
        }          
    }
    
    // Method to edit a Customer
    public void editCustomer(int _id, String _fullname, int _nif, String _birthdate, String _phone, String _email, String _address) {
        String editQuery = "UPDATE mc_car_rental.customers SET fullname=?, nif=?, birth_date=?, phone=?, email=?, address=? WHERE id=?;";        
        PreparedStatement ps;
        
        try {
            ps = DB.getConnection().prepareStatement(editQuery);
            ps.setString(1, _fullname);
            ps.setInt(2, _nif);
            ps.setString(3, _birthdate);
            ps.setString(4, _phone);
            ps.setString(5, _email);
            ps.setString(6, _address);
            ps.setInt(7, _id);
            
            if(ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "Informação editada com sucesso!", "Editar Cliente", 1);
                //System.out.println("Localização editada");
            } else {
                JOptionPane.showMessageDialog(null, "Infomação não editada", "Editar Cliente", 2);
                //System.out.println("Localização não editada");
            }
        } catch (SQLException ex) {
            System.out.println("Erro editCustomer: " + ex.getMessage());
        }            
    }
    
    // Method to remove a Customer
    public void removeCustomer(int _id) {
        String editQuery = "DELETE FROM mc_car_rental.customers WHERE id=?;";        
        PreparedStatement ps;
        
        try {
            ps = DB.getConnection().prepareStatement(editQuery);
            ps.setInt(1, _id);
            
            if(ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "Cliente eliminado com sucesso!", "Eliminar Cliente", 1);
            } else {
                JOptionPane.showMessageDialog(null, "Client não eliminado ou inexistente", "Eliminar Cliente", 2);
            }
        } catch (SQLException ex) {
            System.out.println("Erro removeCustomer: " + ex.getMessage());
        }            
    }
    
    
    // Method to return resultset
    public ResultSet getData(String query) {
        
        PreparedStatement ps;
        ResultSet rs = null;
        
        try {           
            ps = DB.getConnection().prepareStatement(query);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            System.out.println("Erro ao obter getData: " + ex.getMessage());
        }        
        return rs;
    }
    
    
    
    // Method to get all customers and return an arraylist
    public ArrayList<Customer> customersList() {
        ArrayList<Customer> customerList = new ArrayList<>();
        
        ResultSet rs = getData("SELECT * FROM mc_car_rental.customers;");
        
        try {
            while(rs.next()) {
                Customer customer = new Customer(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6),  rs.getString(7));
                customerList.add(customer);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao obter custsomersList: " + ex.getMessage());
        }
        
        return customerList;
    }
    
    
    // Method to get customer by id
    public Customer getCustomerById(int customer_id) {
        String query = "SELECT * FROM mc_car_rental.customers WHERE id=" + customer_id;
        ResultSet rs = getData(query);
        Customer customer = null;
        try {
            rs.next();
            customer = new Customer(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6),  rs.getString(7));
        } catch (SQLException ex) {
            System.out.println("Erro ao obter getCustomerById: " + ex.getMessage());
        }
        return customer;
    }
            
    
}
