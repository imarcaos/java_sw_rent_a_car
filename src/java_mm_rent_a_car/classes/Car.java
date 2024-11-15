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
public class Car {
    
    // Needed 19 variables like table
    private int id;
    private int brand;
    private String model;
    private String plate;
    private int km;
    private String fuel;
    private String color;
    private int _class_;
    private int passengers;
    private int doors;
    private String gearbox;
    private float price;
    private String air_conditioning;
    private String sunroof;
    private String heated_seats;
    private String navigation_system;
    private String gps;
    private String bluetooth;
    private String wifi;   

    public Car() {
    }

    public Car(int id, int brand, String model, String plate, int km, String fuel, String color, int _class_, int passengers, int doors, String gearbox, float price, String air_conditioning, String sunroof, String heated_seats, String navigation_system, String gps, String bluetooth, String wifi) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.plate = plate;
        this.km = km;
        this.fuel = fuel;
        this.color = color;
        this._class_ = _class_;
        this.passengers = passengers;
        this.doors = doors;
        this.gearbox = gearbox;
        this.price = price;
        this.air_conditioning = air_conditioning;
        this.sunroof = sunroof;
        this.heated_seats = heated_seats;
        this.navigation_system = navigation_system;
        this.gps = gps;
        this.bluetooth = bluetooth;
        this.wifi = wifi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBrand() {
        return brand;
    }

    public void setBrand(int brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getClass_() {
        return _class_;
    }

    public void setClass_(int _class_) {
        this._class_ = _class_;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    public String getGearbox() {
        return gearbox;
    }

    public void setGearbox(String gearbox) {
        this.gearbox = gearbox;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getAir_conditioning() {
        return air_conditioning;
    }

    public void setAir_conditioning(String air_conditioning) {
        this.air_conditioning = air_conditioning;
    }

    public String getSunroof() {
        return sunroof;
    }

    public void setSunroof(String sunroof) {
        this.sunroof = sunroof;
    }

    public String getHeated_seats() {
        return heated_seats;
    }

    public void setHeated_seats(String heated_seats) {
        this.heated_seats = heated_seats;
    }

    public String getNavigation_system() {
        return navigation_system;
    }

    public void setNavigation_system(String navigation_system) {
        this.navigation_system = navigation_system;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public String getBluetooth() {
        return bluetooth;
    }

    public void setBluetooth(String bluetooth) {
        this.bluetooth = bluetooth;
    }

    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }
    
     
    // Method to add a new car
    public void addCar(int _brand, String _model, String _plate, int _km, String _fuel, String _color,
                       int _class, int _passengers, int _doors, String _gearbox, float _price, String _air_conditioning,
                       String _sunroof, String _heated_seats, String _navigation_system, String _gps, String _bluetooth, String _wifi) {
        
        String insertQuery = "INSERT INTO mc_car_rental.cars (brand_id, model, plate, km, fuel, color, class, passengers, doors, gearbox, price, air_conditionating, sunroof, heated_seats, navigation_system, gps, bluetooth, wifi) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement ps;
        try {
            ps = DB.getConnection().prepareStatement(insertQuery);
            ps.setInt(1, _brand);
            ps.setString(2, _model);
            ps.setString(3, _plate);
            ps.setInt(4, _km);
            ps.setString(5, _fuel);
            ps.setString(6, _color);
            ps.setInt(7, _class);
            ps.setInt(8, _passengers);
            ps.setInt(9, _doors);
            ps.setString(10, _gearbox);
            ps.setFloat(11, _price);
            ps.setString(12, _air_conditioning);
            ps.setString(13, _sunroof);
            ps.setString(14, _heated_seats);
            ps.setString(15, _navigation_system);
            ps.setString(16, _gps);
            ps.setString(17, _bluetooth);
            ps.setString(18, _wifi);
            
            if(ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "Carro adicionado com sucesso!", "Adicionar Carro", 1);
            } else {
                JOptionPane.showMessageDialog(null, "Carro não adicionado", "Adicionar Carro", 2);
            }
        } catch (SQLException ex) {
            System.out.println("Erro addCar: " + ex.getMessage());
        }          
    }


// Method to edit a Car
    public void editCar(int _id, int _brand, String _model, String _plate, int _km, String _fuel, String _color,
                       int _class, int _passengers, int _doors, String _gearbox, float _price, String _air_conditioning,
                       String _sunroof, String _heated_seats, String _navigation_system, String _gps, String _bluetooth, String _wifi) {
        String editQuery = "UPDATE mc_car_rental.cars SET brand_id=?, model=?, plate=?, km=?, fuel=?,"
                + "color=?, class=?, passengers=?, doors=?, gearbox=?, price=?, air_conditionating=?,"
                + "sunroof=?, heated_seats=?, navigation_system=?, gps=?, bluetooth=?, wifi=? WHERE id=?;";        
        PreparedStatement ps;
        
        try {
            ps = DB.getConnection().prepareStatement(editQuery);            
            ps.setInt(1, _brand);
            ps.setString(2, _model);
            ps.setString(3, _plate);
            ps.setInt(4, _km);
            ps.setString(5, _fuel);
            ps.setString(6, _color);
            ps.setInt(7, _class);
            ps.setInt(8, _passengers);
            ps.setInt(9, _doors);
            ps.setString(10, _gearbox);
            ps.setFloat(11, _price);
            
            ps.setString(12, _air_conditioning);
            ps.setString(13, _sunroof);
            ps.setString(14, _heated_seats);
            ps.setString(15, _navigation_system);
            ps.setString(16, _gps);
            ps.setString(17, _bluetooth);
            ps.setString(18, _wifi);
            
            ps.setInt(19, _id);
            
            if(ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "Viatura editada com sucesso!", "Editar Viatura", 1);
            } else {
                JOptionPane.showMessageDialog(null, "Viatura não editada", "Editar Viatura", 2);
            }
        } catch (SQLException ex) {
            System.out.println("Erro editCar: " + ex.getMessage());
        }            
    }
    
    // Method to delete a Car
    public void deleteCar(int _id) {
        String editQuery = "DELETE FROM mc_car_rental.cars WHERE id=?;";        
        PreparedStatement ps;
        
        try {
            ps = DB.getConnection().prepareStatement(editQuery);
            ps.setInt(1, _id);
            
            if(ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "Viatura eliminada com sucesso!", "Eliminar Viatura", 1);
                //System.out.println("Marca excluída");
            } else {
                JOptionPane.showMessageDialog(null, "Viatura não eliminada", "Eliminar Viatura", 2);
                //System.out.println("Marca não excluída");
            }
        } catch (SQLException ex) {
            System.out.println("Erro deleteCar: " + ex.getMessage());
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
    
    
    // Method to get all cars and return an arraylist
    public ArrayList<Car> carsList() {
        ArrayList<Car> carsList = new ArrayList<>();
        
        ResultSet rs = getData("SELECT * FROM mc_car_rental.cars;");
        
        try {
            while(rs.next()) {
                
                Car car = new Car(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
                                   rs.getInt(5), rs.getString(6), rs.getString(7), rs.getInt(8),
                                  rs.getInt(9), rs.getInt(10), rs.getString(11), rs.getFloat(12),
                                  rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16),
                                  rs.getString(17), rs.getString(18), rs.getString(19));
                carsList.add(car);              
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao obter carsList: " + ex.getMessage());
        }
        
        return carsList;
    }   
    
    
    // Method to get all cars by id
    public ArrayList<Car> carsListByBrandId(int brandId) {
        ArrayList<Car> carsList = new ArrayList<>();
        
        ResultSet rs = getData("SELECT * FROM mc_car_rental.cars WHERE brand_id = " + brandId);
        
        try {
            while(rs.next()) {                
                Car car = new Car(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
                                   rs.getInt(5), rs.getString(6), rs.getString(7), rs.getInt(8),
                                  rs.getInt(9), rs.getInt(10), rs.getString(11), rs.getFloat(12),
                                  rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16),
                                  rs.getString(17), rs.getString(18), rs.getString(19));
                carsList.add(car);              
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao obter carsListByBrandId: " + ex.getMessage());
        }
        
        return carsList;
    } 
    
    // Method to get car by id
    public Car getCarById(int car_id) {
        String query = "SELECT * FROM mc_car_rental.cars WHERE id=" + car_id;
        ResultSet rs = getData(query);
        Car car = null;
        try {
            rs.next();
            car = new Car(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
                                   rs.getInt(5), rs.getString(6), rs.getString(7), rs.getInt(8),
                                  rs.getInt(9), rs.getInt(10), rs.getString(11), rs.getFloat(12),
                                  rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16),
                                  rs.getString(17), rs.getString(18), rs.getString(19));
        } catch (SQLException ex) {
            System.out.println("Erro ao obter getCarById: " + ex.getMessage());
        }
        return car;
    }
    
    
    
} // end of class


