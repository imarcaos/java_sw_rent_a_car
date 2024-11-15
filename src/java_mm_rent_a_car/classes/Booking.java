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
public class Booking {
    
    private int id;
    private int car_id;
    private int customer_id;
    private int pickup_city_id;
    private String pickup_date;
    private String pickup_time;
    private int dropoff_city;
    private String dropoff_date;
    private String dropoff_time;
    private float total_price;

    public Booking() {
    }

    public Booking(int id, int car_id, int customer_id, int pickup_city_id, String pickup_date, String pickup_time, int dropoff_city, String dropoff_date, String dropoff_time, float total_price) {
        this.id = id;
        this.car_id = car_id;
        this.customer_id = customer_id;
        this.pickup_city_id = pickup_city_id;
        this.pickup_date = pickup_date;
        this.pickup_time = pickup_time;
        this.dropoff_city = dropoff_city;
        this.dropoff_date = dropoff_date;
        this.dropoff_time = dropoff_time;
        this.total_price = total_price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getPickup_city_id() {
        return pickup_city_id;
    }

    public void setPickup_city_id(int pickup_city_id) {
        this.pickup_city_id = pickup_city_id;
    }

    public String getPickup_date() {
        return pickup_date;
    }

    public void setPickup_date(String pickup_date) {
        this.pickup_date = pickup_date;
    }

    public String getPickup_time() {
        return pickup_time;
    }

    public void setPickup_time(String pickup_time) {
        this.pickup_time = pickup_time;
    }

    public int getDropoff_city() {
        return dropoff_city;
    }

    public void setDropoff_city(int dropoff_city) {
        this.dropoff_city = dropoff_city;
    }

    public String getDropoff_date() {
        return dropoff_date;
    }

    public void setDropoff_date(String dropoff_date) {
        this.dropoff_date = dropoff_date;
    }

    public String getDropoff_time() {
        return dropoff_time;
    }

    public void setDropoff_time(String dropoff_time) {
        this.dropoff_time = dropoff_time;
    }

    public float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(float total_price) {
        this.total_price = total_price;
    }
    
    
    // Add new booking
    public void addBooking(int _car_id, int _customer_id, int _pickup_city_id, String _pickup_date, String _pickup_time,
            int _dropoff_city_id, String _dropoff_date, String _dropoff_time, float _total_price) {
      String insertQuery = "INSERT INTO mc_car_rental.bookings (car_id, customer_id, pickup_city_id,"
              + " pickup_date, pickup_time, dropoff_city_id, dropoff_date, dropoff_time, total_price)"
              + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement ps;
        try {
            ps = DB.getConnection().prepareStatement(insertQuery);
            ps.setInt(1, _car_id);
            ps.setInt(2, _customer_id);
            ps.setInt(3, _pickup_city_id);
            ps.setString(4, _pickup_date);
            ps.setString(5, _pickup_time);
            ps.setInt(6, _dropoff_city_id);
            ps.setString(7, _dropoff_date);
            ps.setString(8, _dropoff_time);
            ps.setFloat(9, _total_price);
            
            if(ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "Reserva adicionada com sucesso!", "Adicionar Reserva", 1);
            } else {
                JOptionPane.showMessageDialog(null, "Reserva não adicionada", "Adicionar Reserva", 2);
            }
        } catch (SQLException ex) {
            System.out.println("Erro addBooking: " + ex.getMessage());
        }   
    }
    
    // Method to edit a Booking
    public void editBooking(int _id, int _car_id, int _customer_id, int _pickup_city_id, String _pickup_date, String _pickup_time,
            int _dropoff_city_id, String _dropoff_date, String _dropoff_time, float _total_price) {
        String editQuery = "UPDATE mc_car_rental.bookings SET car_id=?, customer_id=?, pickup_city_id=?,"
                + " pickup_date=?, pickup_time=?, dropoff_city_id=?, dropoff_date=?, dropoff_time=?, total_price=? WHERE id=?;";        
        PreparedStatement ps;
        
        try {
            ps = DB.getConnection().prepareStatement(editQuery);
            ps.setInt(1, _car_id);
            ps.setInt(2, _customer_id);
            ps.setInt(3, _pickup_city_id);
            ps.setString(4, _pickup_date);
            ps.setString(5, _pickup_time);
            ps.setInt(6, _dropoff_city_id);
            ps.setString(7, _dropoff_date);
            ps.setString(8, _dropoff_time);
            ps.setFloat(9, _total_price);
            ps.setInt(10, _id);
            
            if(ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "Reserva editada com sucesso!", "Editar Reserva", 1);
                //System.out.println("Localização editada");
            } else {
                JOptionPane.showMessageDialog(null, "Reserva não editada", "Editar Reserva", 2);
                //System.out.println("Localização não editada");
            }
        } catch (SQLException ex) {
            System.out.println("Erro editBooking: " + ex.getMessage());
        }            
    }
    
    // Method to delete a Booking
    public void deleteBooking(int _id) {
        String editQuery = "DELETE FROM mc_car_rental.bookings WHERE id=?;";        
        PreparedStatement ps;
        
        try {
            ps = DB.getConnection().prepareStatement(editQuery);
            ps.setInt(1, _id);
            
            if(ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "Reserva eliminada com sucesso!", "Eliminar Reserva", 1);
                //System.out.println("Localização excluída");
            } else {
                JOptionPane.showMessageDialog(null, "Reserva não eliminada ou inexistente", "Eliminar Reserva", 2);
                //System.out.println("Localização não excluída");
            }
        } catch (SQLException ex) {
            System.out.println("Erro removeBooking: " + ex.getMessage());
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
    
    
    // Method to get all Bookings and return an arraylist
    public ArrayList<Booking> bookingList() {
        ArrayList<Booking> bookingList = new ArrayList<>();
        
        ResultSet rs = getData("SELECT * FROM mc_car_rental.bookings;");
        
        try {
            while(rs.next()) {
                Booking booking = new Booking(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4),
                        rs.getString(5), rs.getString(6),  rs.getInt(7), rs.getString(8),
                        rs.getString(9), rs.getFloat(10));
                bookingList.add(booking);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao obter bookingssList: " + ex.getMessage());
        }
        
        return bookingList;
    }
    
    // Method to get booking by id
    public Booking getBookingById(int booking_id) {
        String query = "SELECT * FROM mc_car_rental.bookings WHERE id=" + booking_id;
        ResultSet rs = getData(query);
        Booking booking = null;
        try {
            rs.next();
            booking = new Booking(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4),
                        rs.getString(5), rs.getString(6),  rs.getInt(7), rs.getString(8),
                        rs.getString(9), rs.getFloat(10));
        } catch (SQLException ex) {
            System.out.println("Erro ao obter getBookingById: " + ex.getMessage());
        }
        return booking;
    }
    
} // end Class Booking
