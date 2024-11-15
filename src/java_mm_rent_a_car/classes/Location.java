/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java_mm_rent_a_car.classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author Marcos Melo
 */
public class Location {
    
    private int id;
    private String city;
    private String address;

    public Location() {
    }

    public Location(int _id, String _city, String _address) {
        this.id = _id;
        this.city = _city;
        this.address = _address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }    
    
    // Methods to add, edit and remove
    
    // Method to add a new Location
    public void addLocation(String _city, String _address) {
        String insertQuery = "INSERT INTO mc_car_rental.locations (city, address) VALUES(?, ?)";
        PreparedStatement ps;
        try {
            ps = DB.getConnection().prepareStatement(insertQuery);
            ps.setString(1, _city);
            ps.setString(2, _address);
            
            if(ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "Localização adicionada com sucesso!", "Adicionar Localização", 1);
                //System.out.println("Localização adicionada");
            } else {
                JOptionPane.showMessageDialog(null, "Localização não adicionada", "Adicionar Localização", 2);
                //System.out.println("Localização não adicionada");
            }
        } catch (SQLException ex) {
            System.out.println("Erro addLocation: " + ex.getMessage());
        }          
    }
    
    // Method to edit a Location
    public void editLocation(int _id, String _city, String _address) {
        String editQuery = "UPDATE mc_car_rental.locations SET city=?, address=? WHERE id=?;";        
        PreparedStatement ps;
        
        try {
            ps = DB.getConnection().prepareStatement(editQuery);
            ps.setString(1, _city);
            ps.setString(2, _address);
            ps.setInt(3, _id);
            
            if(ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "Localização editada com sucesso!", "Editar Localização", 1);
                //System.out.println("Localização editada");
            } else {
                JOptionPane.showMessageDialog(null, "Localização não editada", "Editar Localização", 2);
                //System.out.println("Localização não editada");
            }
        } catch (SQLException ex) {
            System.out.println("Erro editLocation: " + ex.getMessage());
        }            
    }
    
    // Method to remove a Location
    public void removeLocation(int _id) {
        String editQuery = "DELETE FROM mc_car_rental.locations WHERE id=?;";        
        PreparedStatement ps;
        
        try {
            ps = DB.getConnection().prepareStatement(editQuery);
            ps.setInt(1, _id);
            
            if(ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "Localização eliminada com sucesso!", "Eliminar Localização", 1);
                //System.out.println("Localização excluída");
            } else {
                JOptionPane.showMessageDialog(null, "Localização não eliminada ou inexistente", "Eliminar Localização", 2);
                //System.out.println("Localização não excluída");
            }
        } catch (SQLException ex) {
            System.out.println("Erro removeLocation: " + ex.getMessage());
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
    
    
    
    // Method to get all location and return an arraylist
    public ArrayList<Location> locationsList() {
        ArrayList<Location> locList = new ArrayList<>();
        
        ResultSet rs = getData("SELECT * FROM mc_car_rental.locations;");
        
        try {
            while(rs.next()) {
                Location location = new Location(rs.getInt(1), rs.getString(2), rs.getString(3));
                locList.add(location);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao obter locationsList: " + ex.getMessage());
        }
        
        return locList;
    }
    
    // Method to get location address by city    
    public ArrayList<Location> addressListByCity(String city) {
        ArrayList<Location> locList = new ArrayList<>();

        // attention to the query '' with the parameter city.
        String query = "SELECT * FROM mc_car_rental.locations WHERE city = '" + city + "'";
        ResultSet rs = getData(query);

        if (rs != null) {
            try {
                while (rs.next()) {
                    Location location = new Location(rs.getInt(1), rs.getString(2), rs.getString(3));
                    locList.add(location);
                }
            } catch (SQLException ex) {
                System.out.println("Erro ao obter addressListByCity: " + ex.getMessage());
            }        
        } else {
            System.out.println("O ResultSet é nulo. Verifique a consulta.");
        }
        return locList;
    }
    
    
    // Method to get location by id
    public Location getLocationById(int location_id) {
        String query = "SELECT * FROM mc_car_rental.locations WHERE id=" + location_id;
        ResultSet rs = getData(query);
        Location location = null;
        try {
            rs.next();
            location = new Location(rs.getInt(1), rs.getString(2), rs.getString(3));
        } catch (SQLException ex) {
            System.out.println("Erro ao obter getLocationById: " + ex.getMessage());
        }
        return location;
    }
    
    // Method to populate with cities (id , city)
    public HashMap<Integer, String> citiesHashMap() {
        HashMap<Integer, String> cities_map = new HashMap<Integer, String>();
        
        ResultSet rs = getData("SELECT * FROM mc_car_rental.locations;");
        
        try {
            while(rs.next()) {
                cities_map.put(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao obter citiesHashMap: " + ex.getMessage());
        }
        
        return cities_map;
        
    }
    
}
