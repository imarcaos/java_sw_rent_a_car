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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Marcos Melo
 */
public class Brand {
    
    private int id;
    private String name;
    private byte[] logo;
    
    public Brand() {}

    public Brand(int _id, String _name, byte[] _logo) {
        this.id = _id;
        this.name = _name;
        this.logo = _logo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }
    
    // Methods to add, edit and remove
    
    // Method to add a new Brand
    public void addBrand(String _name, byte[] _logo) {
        String insertQuery = "INSERT INTO mc_car_rental.brands (name, logo) VALUES(?, ?)";
        PreparedStatement ps;
        try {
            ps = DB.getConnection().prepareStatement(insertQuery);
            ps.setString(1, _name);
            ps.setBytes(2, _logo);
            
            if(ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "Marca adicionada com sucesso!", "Adicionar Marca", 1);
                //System.out.println("Marca adicionada");
            } else {
                JOptionPane.showMessageDialog(null, "Marca não adicionada", "Adicionar Marca", 2);
                //System.out.println("Marca não adicionada");
            }
        } catch (SQLException ex) {
            System.out.println("Erro addBrand: " + ex.getMessage());
        }          
    }
    
    
    // Method to edit a Brand
    public void editBrand(int _id, String _name, byte[] _logo) {
        String editQuery = "UPDATE mc_car_rental.brands SET name=?, logo=? WHERE id=?";        
        PreparedStatement ps;
        
        try {
            ps = DB.getConnection().prepareStatement(editQuery);
            ps.setString(1, _name);
            ps.setBytes(2, _logo);
            ps.setInt(3, _id);
            
            if(ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "Marca editada com sucesso!", "Editar Marca", 1);
                //System.out.println("Marca editada");
            } else {
                JOptionPane.showMessageDialog(null, "Marca não editada", "Eliminar Marca", 2);
                //System.out.println("Marca não editada");
            }
        } catch (SQLException ex) {
            System.out.println("Erro editBrand: " + ex.getMessage());
        }            
    }
    
    
    // Method to remove a Brand
    public void removeBrand(int _id) {
        String editQuery = "DELETE FROM mc_car_rental.brands WHERE id=?;";        
        PreparedStatement ps;
        
        try {
            ps = DB.getConnection().prepareStatement(editQuery);
            ps.setInt(1, _id);
            
            if(ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "Marca eliminada com sucesso!", "Eliminar Marca", 1);
                //System.out.println("Marca excluída");
            } else {
                JOptionPane.showMessageDialog(null, "Marca não eliminada", "Eliminar Marca", 2);
                //System.out.println("Marca não excluída");
            }
        } catch (SQLException ex) {
            System.out.println("Erro removeBrand: " + ex.getMessage());
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
    
    
    
    // Method to get all brands and return an arraylist
    public ArrayList<Brand> brandsList() {
        ArrayList<Brand> brdList = new ArrayList<>();
        
        ResultSet rs = getData("SELECT * FROM mc_car_rental.brands;");
        
        try {
            while(rs.next()) {
                Brand brand = new Brand(rs.getInt(1), rs.getString(2), rs.getBytes(3));
                brdList.add(brand);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao obter brandsList: " + ex.getMessage());
        }
        
        return brdList;
    }
    
    
    // Method to get brand by id
    public Brand getBrandById(int brand_id) {
        String query = "SELECT * FROM mc_car_rental.brands WHERE id=" + brand_id;
        ResultSet rs = getData(query);
        Brand brand = null;
        try {
            rs.next();
            brand = new Brand(rs.getInt(1), rs.getString(2), rs.getBytes(3));
        } catch (SQLException ex) {
            System.out.println("Erro ao obter getBrandById: " + ex.getMessage());
        }
        return brand;
    }
    
    // Method to populate with brands (id , brand)
    public HashMap<Integer, String> brandsHashMap() {
        HashMap<Integer, String> brands_map = new HashMap<>();
        //HashMap<Integer, String> brands_map = new HashMap<Integer, String>();
        
        ResultSet rs = getData("SELECT * FROM mc_car_rental.brands;");
        
        try {
            while(rs.next()) {
                brands_map.put(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao obter brandsHashMap: " + ex.getMessage());
        }
        
        return brands_map;
        
    }
    
        
}
