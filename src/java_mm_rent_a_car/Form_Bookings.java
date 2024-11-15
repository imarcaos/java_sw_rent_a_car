/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package java_mm_rent_a_car;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java_mm_rent_a_car.classes.Booking;
import java_mm_rent_a_car.classes.Brand;
import java_mm_rent_a_car.classes.Car;
import java_mm_rent_a_car.classes.Location;
import javax.swing.JOptionPane;

/**
 *
 * @author Marcos Melo
 */
public class Form_Bookings extends javax.swing.JFrame {

    /**
     * Creates new form Form_Bookings
     */
    
    // Global Variables
    static int customer_id = 0;
    int brand_id = 0;
    int car_id = 0;
    int pickup_location_id = 0;
    int dropoff_location_id = 0;
    float car_price = 0;
    float total_price = 0;
    String pickup_date, pickup_time, dropoff_date, dropoff_time;
    
    
    Brand brand = new Brand();
    HashMap<Integer, String> brand_map = brand.brandsHashMap();
    
    Location location = new Location();
    HashMap<Integer, String> city_map = location.citiesHashMap();    
    
    
    Car car = new Car();
    ArrayList<Car> cars_list;
    
    Booking booking = new Booking();
    
    
    public Form_Bookings() {
        initComponents();
        
        // center form
        this.setLocationRelativeTo(null);
        
        // call populate methods
        populateComboboxBrands();
        populateComboboxCities();
    }
    
    
    // Method to receive data (id and name) from Form_CustomersList
    public static void displayCustomer(String id, String name) {
        jTextField_customer.setText(name);
        customer_id = Integer.parseInt(id);
    }
    
    
   //Method to populate the ComboBox Brands.
    public void populateComboboxBrands() {      
        
        for(String key: brand_map.values()) {
            jComboBox_brands_book.addItem(key);
        }
    }
    
    //Method to populate the ComboBox Cars (Models).
    public void populateComboboxCars(int brandId) {         
        cars_list = car.carsListByBrandId(brandId);
        
        jComboBox_car_book.removeAllItems();
        for(int i = 0; i < cars_list.size(); i++) {
            jComboBox_car_book.addItem(cars_list.get(i).getModel());
        }        
    }
    
    //Method to populate the ComboBox Cities.
    public void populateComboboxCities() {      
        
        for(String key: city_map.values()) {
            jComboBox_pickup_city.addItem(key);
            jComboBox_dropoff_city.addItem(key);
        }
    }
    
    //Method to populate the ComboBox Address.
    public void populateComboboxAddress(String pickup_or_dropoff, String city) {      
                
        if(pickup_or_dropoff.equals("pickup")) {
            jComboBox_pickup_address.removeAllItems();
            for(Location l : location.addressListByCity(city)) { // for with iteration
                jComboBox_pickup_address.addItem(l.getAddress());
            }            
        } else if(pickup_or_dropoff.equals("dropoff")) {
            jComboBox_dropoff_address.removeAllItems();
            for(Location l : location.addressListByCity(city)) {
                jComboBox_dropoff_address.addItem(l.getAddress());
            }            
        }
    }
    
    // Method to check empty fields
    public boolean verify(String add_edit_calc) {
        
        String _pickup_time = timePicker_pickup_time.getText();
        String _dropoff_time = timePicker_dropoff_time.getText();
        
        boolean val = false;
        if(add_edit_calc == "add") {
            if(customer_id != 0) {
            val = true;
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, Selecione um Cliente!", "Informação Incompleta", 2);
                val = false;
            }            
        } else if(add_edit_calc == "calc") {
            if(car_id != 0 && pickup_location_id != 0 && dropoff_location_id != 0 && !_pickup_time.trim().equals("") && !_dropoff_time.trim().equals("")) {
            val = true;
            } else {
                JOptionPane.showMessageDialog(null, "Campo Vazio! Por favor verifica os campo: Hora", "Verificar os Campos e informações", 2);
                val = false;
            }            
        }                
        return val;
    }
    
    // Method to check Date, Time are filled correctly, check Car and location is filled and Calculate Total Price of Reservation
    public void checkDataAndCalc() {
        long periodAsHours, periodAsDays; // variables to calculate days when car is returned
                 
        try {
            // pickup
            pickup_date = datePicker_pickup_date.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
            LocalDate pickup_localDate = LocalDate.parse(pickup_date);
            pickup_time = timePicker_pickup_time.getText();

            // dropoff
            dropoff_date = datePicker_dropoff_date.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
            LocalDate dropoff_localDate = LocalDate.parse(dropoff_date);
            dropoff_time = timePicker_dropoff_time.getText();
            
            // Calculate days when car is returned
            periodAsDays = ChronoUnit.DAYS.between(pickup_localDate, dropoff_localDate);
            System.out.println(periodAsDays);

            if(verify("calc")) {
                // check date and time if dropoff is before pickup
                if(dropoff_localDate.isBefore(pickup_localDate)) {
                    JOptionPane.showMessageDialog(null, "A data de Entrega tem que superior a Data de Levantar", "Datas Inválidas", 2);
                } else if(dropoff_localDate.isEqual(pickup_localDate)) {
                    if(timePicker_dropoff_time.getTime().isBefore(timePicker_pickup_time.getTime())) {
                        JOptionPane.showMessageDialog(null, "A Hora de Entrega tem que superior a Hora de Levantar", "Datas Inválidas", 2);
                    } else {  
                        periodAsDays += 1;
                    }              
                } else {
                        periodAsDays = ChronoUnit.DAYS.between(pickup_localDate, dropoff_localDate);
                        periodAsHours  = ChronoUnit.HOURS.between(timePicker_pickup_time.getTime(), timePicker_dropoff_time.getTime());
                        if (periodAsHours > 24) {
                            periodAsDays += 1;
                        }
                }
                DecimalFormat dc = new DecimalFormat("0.00");
                total_price = car_price * periodAsDays;
                jLabel_total_price.setText(car_price + " x " + periodAsDays + " = " + dc.format(total_price) + " Euros");
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Escolha uma data válida!", "Informação Inválida", 2);
        }        
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel_title = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jComboBox_brands_book = new javax.swing.JComboBox<>();
        jComboBox_car_book = new javax.swing.JComboBox<>();
        jLabel_car_price = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jComboBox_pickup_city = new javax.swing.JComboBox<>();
        jComboBox_pickup_address = new javax.swing.JComboBox<>();
        datePicker_pickup_date = new com.github.lgooddatepicker.components.DatePicker();
        timePicker_pickup_time = new com.github.lgooddatepicker.components.TimePicker();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField_customer = new javax.swing.JTextField();
        jButton_selectCustomer = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jComboBox_dropoff_city = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jComboBox_dropoff_address = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        datePicker_dropoff_date = new com.github.lgooddatepicker.components.DatePicker();
        jLabel16 = new javax.swing.JLabel();
        timePicker_dropoff_time = new com.github.lgooddatepicker.components.TimePicker();
        jPanel8 = new javax.swing.JPanel();
        jLabel_total_price = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jButton_calcBooking = new javax.swing.JButton();
        jButton_addBooking = new javax.swing.JButton();
        jButton_bookingList = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jPanel3.setBackground(new java.awt.Color(204, 255, 153));

        jLabel_title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_title.setText("Reservas");
        jLabel_title.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabel_title.setForeground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(506, 506, 506)
                .addComponent(jLabel_title)
                .addContainerGap(500, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel_title)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Selecionar Automóvel");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel2.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N

        jLabel5.setText("Marca");
        jLabel5.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N

        jLabel6.setText("Modelo");
        jLabel6.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N

        jLabel7.setText("Preço");
        jLabel7.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N

        jComboBox_brands_book.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jComboBox_brands_book.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_brands_bookActionPerformed(evt);
            }
        });

        jComboBox_car_book.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jComboBox_car_book.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_car_bookActionPerformed(evt);
            }
        });

        jLabel_car_price.setText("...");
        jLabel_car_price.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox_brands_book, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_car_book, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_car_price, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox_brands_book, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBox_car_book, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel_car_price))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Levantar - Local e Data");
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel3.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N

        jLabel9.setText("Cidade");
        jLabel9.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N

        jLabel10.setText("Endereço");
        jLabel10.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N

        jLabel11.setText("Data");
        jLabel11.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N

        jLabel12.setText("Hora");
        jLabel12.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N

        jComboBox_pickup_city.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_pickup_cityActionPerformed(evt);
            }
        });

        jComboBox_pickup_address.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox_pickup_address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_pickup_addressActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox_pickup_city, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox_pickup_address, 0, 357, Short.MAX_VALUE)
                    .addComponent(datePicker_pickup_date, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(timePicker_pickup_time, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jComboBox_pickup_city, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox_pickup_address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(datePicker_pickup_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(timePicker_pickup_time, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Selecionar Cliente");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel1.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N

        jLabel8.setText("Cliente");
        jLabel8.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N

        jTextField_customer.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        jButton_selectCustomer.setText("Selecionar Cliente");
        jButton_selectCustomer.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButton_selectCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_selectCustomerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jTextField_customer, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton_selectCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField_customer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton_selectCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setPreferredSize(new java.awt.Dimension(550, 233));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Entregar - Local e Data");
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel4.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N

        jLabel13.setText("Cidade");
        jLabel13.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N

        jComboBox_dropoff_city.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_dropoff_cityActionPerformed(evt);
            }
        });

        jLabel14.setText("Endereço");
        jLabel14.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N

        jComboBox_dropoff_address.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox_dropoff_address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_dropoff_addressActionPerformed(evt);
            }
        });

        jLabel15.setText("Data");
        jLabel15.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N

        jLabel16.setText("Hora");
        jLabel16.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel13)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox_dropoff_city, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox_dropoff_address, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(datePicker_dropoff_date, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(timePicker_dropoff_time, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(52, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jComboBox_dropoff_city, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox_dropoff_address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(datePicker_dropoff_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(timePicker_dropoff_time, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jLabel_total_price.setText("...");
        jLabel_total_price.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel_total_price.setForeground(new java.awt.Color(102, 51, 0));

        jLabel18.setText("Preço Total:");
        jLabel18.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 51, 0));

        jButton_calcBooking.setText("Calcular");
        jButton_calcBooking.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButton_calcBooking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_calcBookingActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jButton_calcBooking, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_total_price, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_total_price)
                    .addComponent(jLabel18)
                    .addComponent(jButton_calcBooking))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jButton_addBooking.setText("Adicionar Reserva");
        jButton_addBooking.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButton_addBooking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_addBookingActionPerformed(evt);
            }
        });

        jButton_bookingList.setText("Lista de Reservas (Editar | Eliminar)");
        jButton_bookingList.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButton_bookingList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_bookingListActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton_addBooking, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_bookingList)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton_bookingList, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_addBooking, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_selectCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_selectCustomerActionPerformed
        // show customers list
        Form_CustomersList frm_ctrList = new Form_CustomersList();
        frm_ctrList.setVisible(true);
    }//GEN-LAST:event_jButton_selectCustomerActionPerformed

    private void jComboBox_brands_bookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_brands_bookActionPerformed
        // get id the selected brand
        for(Map.Entry<Integer, String> entry : brand_map.entrySet()) {
            if(entry.getValue().equals(jComboBox_brands_book.getSelectedItem().toString())) {
                brand_id = entry.getKey();
            }
        }
        populateComboboxCars(brand_id);
    }//GEN-LAST:event_jComboBox_brands_bookActionPerformed

    private void jComboBox_car_bookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_car_bookActionPerformed
        // get id the selected car
        //jLabel_car_price.setText(String.valueOf(jComboBox_car_book.getSelectedIndex()));
        for(int i = 0; i < cars_list.size(); i++) {
            if(jComboBox_car_book.getSelectedIndex() == i) {
                car_price = cars_list.get(i).getPrice();
                car_id = cars_list.get(i).getId();
//                System.out.println("car id: " + car_id);
            }
        }
        jLabel_car_price.setText(String.valueOf(car_price) + " Euros/dia");
        
    }//GEN-LAST:event_jComboBox_car_bookActionPerformed

    private void jComboBox_pickup_cityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_pickup_cityActionPerformed
        // populate combobox address by choice city combobox
        populateComboboxAddress("pickup", jComboBox_pickup_city.getSelectedItem().toString());
    }//GEN-LAST:event_jComboBox_pickup_cityActionPerformed

    private void jComboBox_dropoff_cityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_dropoff_cityActionPerformed
        // populate combobox address by choice city combobox
        populateComboboxAddress("dropoff", jComboBox_dropoff_city.getSelectedItem().toString());
    }//GEN-LAST:event_jComboBox_dropoff_cityActionPerformed

    private void jComboBox_pickup_addressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_pickup_addressActionPerformed
        // Get id by city and address
        ArrayList<Location> address_list;
        address_list = location.addressListByCity(jComboBox_pickup_city.getSelectedItem().toString());
        
        for(int i = 0; i < address_list.size(); i++) {
                if(jComboBox_pickup_address.getSelectedIndex() == i) {
                pickup_location_id = address_list.get(i).getId();
            }
        }
//        System.out.println("pickup: " + pickup_location_id);
    }//GEN-LAST:event_jComboBox_pickup_addressActionPerformed

    private void jComboBox_dropoff_addressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_dropoff_addressActionPerformed
        // Get id by city and address
        ArrayList<Location> address_list;
        address_list = location.addressListByCity(jComboBox_dropoff_city.getSelectedItem().toString());
        
        for(int i = 0; i < address_list.size(); i++) {
                if(jComboBox_dropoff_address.getSelectedIndex() == i) {
                dropoff_location_id = address_list.get(i).getId();
            }
        }
//        System.out.println("dropoff: " + dropoff_location_id);
    }//GEN-LAST:event_jComboBox_dropoff_addressActionPerformed

    private void jButton_addBookingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_addBookingActionPerformed
        // Add new Booking
        checkDataAndCalc();
        if(verify("add")) {
            booking.addBooking(car_id, customer_id, pickup_location_id,
                            pickup_date, pickup_time, dropoff_location_id,
                            dropoff_date, dropoff_time, total_price);
        }
    }//GEN-LAST:event_jButton_addBookingActionPerformed

    private void jButton_calcBookingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_calcBookingActionPerformed
        // Calculate total price of the booking
        checkDataAndCalc();
    }//GEN-LAST:event_jButton_calcBookingActionPerformed

    private void jButton_bookingListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_bookingListActionPerformed
        // show Booking list
        Form_Booking_Edit_Remove frm_bkgEditRemove = new Form_Booking_Edit_Remove();
        frm_bkgEditRemove.setVisible(true);
    }//GEN-LAST:event_jButton_bookingListActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Form_Bookings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_Bookings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_Bookings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_Bookings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_Bookings().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.github.lgooddatepicker.components.DatePicker datePicker_dropoff_date;
    private com.github.lgooddatepicker.components.DatePicker datePicker_pickup_date;
    private javax.swing.JButton jButton_addBooking;
    private javax.swing.JButton jButton_bookingList;
    private javax.swing.JButton jButton_calcBooking;
    private javax.swing.JButton jButton_selectCustomer;
    private javax.swing.JComboBox<String> jComboBox_brands_book;
    private javax.swing.JComboBox<String> jComboBox_car_book;
    private javax.swing.JComboBox<String> jComboBox_dropoff_address;
    private javax.swing.JComboBox<String> jComboBox_dropoff_city;
    private javax.swing.JComboBox<String> jComboBox_pickup_address;
    private javax.swing.JComboBox<String> jComboBox_pickup_city;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_car_price;
    private javax.swing.JLabel jLabel_title;
    private javax.swing.JLabel jLabel_total_price;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private static javax.swing.JTextField jTextField_customer;
    private com.github.lgooddatepicker.components.TimePicker timePicker_dropoff_time;
    private com.github.lgooddatepicker.components.TimePicker timePicker_pickup_time;
    // End of variables declaration//GEN-END:variables
}  // End Form_Bookings
