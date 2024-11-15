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
import java_mm_rent_a_car.classes.Customer;
import java_mm_rent_a_car.classes.Location;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Marcos Melo
 */
public class Form_Booking_Edit_Remove extends javax.swing.JFrame {

    /**
     * Creates new form Form_Booking_Edit_Remove
     */
    
    // Global Variables
    int customer_id = 0;
    int brand_id = 0;
    int car_id = 0;
    int pickup_location_id = 0;
    int dropoff_location_id = 0;
    float car_price = 0;
    float total_price = 0;
    String pickup_date, pickup_time, dropoff_date, dropoff_time;
    
    Booking booking = new Booking();
    ArrayList<Booking> bookingList = booking.bookingList();
    
    Customer customer = new Customer();
    Car car = new Car();
    ArrayList<Car> cars_list;
    
    Brand brand = new Brand();
    HashMap<Integer, String> brand_map = brand.brandsHashMap();
    
    Location location = new Location();
    HashMap<Integer, String> city_map = location.citiesHashMap();     
    
   
    
    public Form_Booking_Edit_Remove() {
        initComponents();
        
        // center form
        this.setLocationRelativeTo(null);
        
        // call populate methods
        populateJTable();
        populateComboboxBrands();
        populateComboboxCities();
        
         // set row height jtable
        jTable_bookingList.setRowHeight(30); 
    }
    
    
    // Method to populate the jTable with customers list
    public void populateJTable() {
        
        // clear arraylist
        bookingList.clear();
        
        // populate arraylist
        bookingList = booking.bookingList();
        
        /*            
            System.out.println("booking_id = "+booking_id + " car_id = "+car_id+ " customer_id = "+customer_id 
                    + " pickup_location_id = "+pickup_location_id+ " pickup_date = "+pickup_date+ " pickup_time = "+pickup_time                    
                    + " dropoff_location_id = "+dropoff_location_id+ " dropoff_date = "+dropoff_date+ " dropoff_time = "+dropoff_time
                    + " total_price = "+total_price);
            */
        
        // jTable columns
        String[] columnsName = {"ID", "Viatura",  "Cliente", "Lev. Local ID", "Lev. Data", "Lev. Hora",  "Ent. Local ID", "Ent. Data", "Ent. Hora", "Valor Total"};
        
        // jTable rows
        Object[][] rows = new Object[bookingList.size()][columnsName.length];
        
        for(int i = 0; i < bookingList.size(); i++) {
            rows[i][0] = bookingList.get(i).getId();
            rows[i][1] = car.getCarById(bookingList.get(i).getCar_id()).getModel(); // can be add brand + model
            rows[i][2] = customer.getCustomerById(bookingList.get(i).getCustomer_id()).getFullname();
            rows[i][3] = bookingList.get(i).getPickup_city_id();
            rows[i][4] = bookingList.get(i).getPickup_date();
            rows[i][5] = bookingList.get(i).getPickup_time();
            rows[i][6] = bookingList.get(i).getDropoff_city();
            rows[i][7] = bookingList.get(i).getDropoff_date();
            rows[i][8] = bookingList.get(i).getDropoff_time();
            rows[i][9] = bookingList.get(i).getTotal_price();            
        }
        
        DefaultTableModel model = new DefaultTableModel(rows, columnsName);
        jTable_bookingList.setModel(model);
    }
    
    
    // Method to receive data (id and name) from Form_CustomersList
    public void displayCustomer(String id, String name) {
        jTextField_customer.setText(name);
        customer_id = Integer.parseInt(id);
    }
    
    
   //Method to populate the ComboBox Brands.
    public void populateComboboxBrands() {      
        
        for(String key: brand_map.values()) {
            jComboBox_brands_book_edit.addItem(key);
        }
    }
    
    //Method to populate the ComboBox Cars (Models).
    public void populateComboboxCars(int brandId) {         
        cars_list = car.carsListByBrandId(brandId);
        
        jComboBox_car_book_edit.removeAllItems();
        for(int i = 0; i < cars_list.size(); i++) {
            jComboBox_car_book_edit.addItem(cars_list.get(i).getModel());
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
                jLabel_total_price_edit.setText(car_price + " x " + periodAsDays + " = " + dc.format(total_price) + " Euros");
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel_title2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_bookingList = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jComboBox_brands_book_edit = new javax.swing.JComboBox<>();
        jComboBox_car_book_edit = new javax.swing.JComboBox<>();
        jLabel_car_price_edit = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jComboBox_pickup_city = new javax.swing.JComboBox<>();
        jComboBox_pickup_address = new javax.swing.JComboBox<>();
        datePicker_pickup_date = new com.github.lgooddatepicker.components.DatePicker();
        timePicker_pickup_time = new com.github.lgooddatepicker.components.TimePicker();
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextField_customer = new javax.swing.JTextField();
        jButton_selectCustomer = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jComboBox_dropoff_city = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jComboBox_dropoff_address = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        datePicker_dropoff_date = new com.github.lgooddatepicker.components.DatePicker();
        jLabel20 = new javax.swing.JLabel();
        timePicker_dropoff_time = new com.github.lgooddatepicker.components.TimePicker();
        jPanel11 = new javax.swing.JPanel();
        jLabel_total_price_edit = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jButton_calcBooking_edit = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel_bookingId = new javax.swing.JLabel();
        jButton_booking_edit = new javax.swing.JButton();
        jButton_booking_delete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setBackground(new java.awt.Color(102, 102, 102));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jPanel5.setBackground(new java.awt.Color(204, 255, 153));

        jLabel_title2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_title2.setText("Editar e Remover Reservas");
        jLabel_title2.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabel_title2.setForeground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(294, 294, 294)
                .addComponent(jLabel_title2)
                .addContainerGap(337, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel_title2)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jTable_bookingList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable_bookingList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_bookingListMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable_bookingList);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Selecionar Automóvel");
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel3.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N

        jLabel8.setText("Marca");
        jLabel8.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N

        jLabel9.setText("Modelo");
        jLabel9.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N

        jLabel10.setText("Preço");
        jLabel10.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N

        jComboBox_brands_book_edit.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jComboBox_brands_book_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_brands_book_editActionPerformed(evt);
            }
        });

        jComboBox_car_book_edit.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jComboBox_car_book_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_car_book_editActionPerformed(evt);
            }
        });

        jLabel_car_price_edit.setText("...");
        jLabel_car_price_edit.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox_brands_book_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_car_book_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_car_price_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBox_brands_book_edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jComboBox_car_book_edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel_car_price_edit))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Levantar - Local e Data");
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel4.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N

        jLabel11.setText("Cidade");
        jLabel11.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N

        jLabel12.setText("Endereço");
        jLabel12.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N

        jLabel13.setText("Data");
        jLabel13.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N

        jLabel14.setText("Hora");
        jLabel14.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N

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

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox_pickup_city, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox_pickup_address, 0, 357, Short.MAX_VALUE)
                    .addComponent(datePicker_pickup_date, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(timePicker_pickup_time, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(56, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
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
                    .addComponent(jLabel11)
                    .addComponent(jComboBox_pickup_city, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox_pickup_address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(datePicker_pickup_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(timePicker_pickup_time, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Selecionar Cliente");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel1.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N

        jLabel15.setText("Cliente");
        jLabel15.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N

        jTextField_customer.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        jButton_selectCustomer.setText("Selecionar Cliente");
        jButton_selectCustomer.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButton_selectCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_selectCustomerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton_selectCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField_customer, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jTextField_customer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_selectCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setPreferredSize(new java.awt.Dimension(550, 233));

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Entregar - Local e Data");
        jLabel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel16.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N

        jLabel17.setText("Cidade");
        jLabel17.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N

        jComboBox_dropoff_city.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_dropoff_cityActionPerformed(evt);
            }
        });

        jLabel18.setText("Endereço");
        jLabel18.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N

        jComboBox_dropoff_address.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox_dropoff_address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_dropoff_addressActionPerformed(evt);
            }
        });

        jLabel19.setText("Data");
        jLabel19.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N

        jLabel20.setText("Hora");
        jLabel20.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel17)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox_dropoff_city, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox_dropoff_address, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(datePicker_dropoff_date, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(timePicker_dropoff_time, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(52, Short.MAX_VALUE))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jComboBox_dropoff_city, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox_dropoff_address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(datePicker_dropoff_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(timePicker_dropoff_time, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jLabel_total_price_edit.setText("...");
        jLabel_total_price_edit.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel_total_price_edit.setForeground(new java.awt.Color(102, 51, 0));

        jLabel22.setText("Preço Total:");
        jLabel22.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(102, 51, 0));

        jButton_calcBooking_edit.setText("Calcular");
        jButton_calcBooking_edit.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButton_calcBooking_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_calcBooking_editActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jButton_calcBooking_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_total_price_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_total_price_edit)
                    .addComponent(jLabel22)
                    .addComponent(jButton_calcBooking_edit))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jLabel23.setText("Reserva nº:");
        jLabel23.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N

        jLabel_bookingId.setText("000");
        jLabel_bookingId.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel_bookingId.setForeground(new java.awt.Color(0, 102, 0));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_bookingId)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel_bookingId))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jButton_booking_edit.setText("Editar");
        jButton_booking_edit.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButton_booking_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_booking_editActionPerformed(evt);
            }
        });

        jButton_booking_delete.setText("Eliminar");
        jButton_booking_delete.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButton_booking_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_booking_deleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton_booking_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton_booking_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1168, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 222, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton_booking_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_booking_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(128, 128, 128))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(138, 138, 138)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(618, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox_brands_book_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_brands_book_editActionPerformed
        // get id the selected brand
        for(Map.Entry<Integer, String> entry : brand_map.entrySet()) {
            if(entry.getValue().equals(jComboBox_brands_book_edit.getSelectedItem().toString())) {
                brand_id = entry.getKey();
            }
        }
        populateComboboxCars(brand_id);
    }//GEN-LAST:event_jComboBox_brands_book_editActionPerformed

    private void jComboBox_car_book_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_car_book_editActionPerformed
        // get id the selected car
        //jLabel_car_price.setText(String.valueOf(jComboBox_car_book.getSelectedIndex()));
        for(int i = 0; i < cars_list.size(); i++) {
            if(jComboBox_car_book_edit.getSelectedIndex() == i) {
                car_price = cars_list.get(i).getPrice();
                car_id = cars_list.get(i).getId();
                //                System.out.println("car id: " + car_id);
            }
        }
        jLabel_car_price_edit.setText(String.valueOf(car_price) + " Euros/dia");

    }//GEN-LAST:event_jComboBox_car_book_editActionPerformed

    private void jComboBox_pickup_cityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_pickup_cityActionPerformed
        // populate combobox address by choice city combobox
        populateComboboxAddress("pickup", jComboBox_pickup_city.getSelectedItem().toString());
    }//GEN-LAST:event_jComboBox_pickup_cityActionPerformed

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

    private void jButton_selectCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_selectCustomerActionPerformed
        // show customers list
        Form_CustomersList frm_ctrList = new Form_CustomersList();
        frm_ctrList.setVisible(true);
    }//GEN-LAST:event_jButton_selectCustomerActionPerformed

    private void jComboBox_dropoff_cityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_dropoff_cityActionPerformed
        // populate combobox address by choice city combobox
        populateComboboxAddress("dropoff", jComboBox_dropoff_city.getSelectedItem().toString());
    }//GEN-LAST:event_jComboBox_dropoff_cityActionPerformed

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

    private void jButton_calcBooking_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_calcBooking_editActionPerformed
        // Calculate total price of the booking
        checkDataAndCalc();
    }//GEN-LAST:event_jButton_calcBooking_editActionPerformed

    private void jTable_bookingListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_bookingListMouseClicked
        // When selected, they fill all form components
        
        int index = jTable_bookingList.getSelectedRow();
        int booking_id = (Integer) jTable_bookingList.getValueAt(index, 0);        
        
        jLabel_bookingId.setText(String.valueOf(booking_id));
        int car_id_ = booking.getBookingById(booking_id).getCar_id();
        int brand_id_ = car.getCarById(car_id_).getBrand();
        int customer_id_ = booking.getBookingById(booking_id).getCustomer_id();
        int pickup_location_id_ = booking.getBookingById(booking_id).getPickup_city_id();
        String pickup_date_ = booking.getBookingById(booking_id).getPickup_date(); 
        String pickup_time_ = booking.getBookingById(booking_id).getPickup_time();
        int dropoff_location_id_ = booking.getBookingById(booking_id).getDropoff_city();
        String dropoff_date_ = booking.getBookingById(booking_id).getDropoff_date();
        String dropoff_time_ = booking.getBookingById(booking_id).getDropoff_time();
        float total_price_ = booking.getBookingById(booking_id).getTotal_price();
        
        customer_id = customer_id_; // make attribuiton to global variable
        
        //System.out.println(car_id_);
        
        // Set customer car brand on Combobox
        jComboBox_brands_book_edit.setSelectedItem(brand.getBrandById(brand_id_).getName());
        
        // Set customer car on Combobox      
        jComboBox_car_book_edit.setSelectedItem(car.getCarById(car_id_).getModel());
        
        // Set customer
        jTextField_customer.setText(customer.getCustomerById(customer_id_).getFullname());
        
        // Set pickup location and address
        jComboBox_pickup_city.setSelectedItem(location.getLocationById(pickup_location_id_).getCity());
        jComboBox_pickup_address.setSelectedItem(location.getLocationById(pickup_location_id_).getAddress());
        
        // Set dropoff location and address
        jComboBox_dropoff_city.setSelectedItem(location.getLocationById(dropoff_location_id_).getCity());
        jComboBox_dropoff_address.setSelectedItem(location.getLocationById(dropoff_location_id_).getAddress());
        
        // format and set Date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
        
        try {
            // pickup
            LocalDate pickup_bdate = LocalDate.parse(pickup_date_, formatter);
            datePicker_pickup_date.setDate(pickup_bdate);
            timePicker_pickup_time.setText(pickup_time_);

            // dropoff
            LocalDate dropoff_bdate = LocalDate.parse(dropoff_date_, formatter);
            datePicker_dropoff_date.setDate(dropoff_bdate);
            timePicker_dropoff_time.setText(dropoff_time_);
            
        } catch (Exception e) {
            System.out.println("Erro ao processar a data: " + e.getMessage());
        }
        
        checkDataAndCalc();
    }//GEN-LAST:event_jTable_bookingListMouseClicked

    private void jButton_booking_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_booking_editActionPerformed
        // Edit a booking
        int booking_id = Integer.parseInt(jLabel_bookingId.getText());
        try {            
            // check if data fiels are valid and make the calc  
            checkDataAndCalc();  
            
            // check if fiels and variables are valid
            if(verify("add")) {
                booking.editBooking(booking_id, car_id, customer_id,
                        pickup_location_id, pickup_date, pickup_time,
                        dropoff_location_id, dropoff_date, dropoff_time, total_price);
                              
//            System.out.println("booking_id = "+booking_id + " car_id = "+car_id+ " customer_id = "+customer_id 
//                    + " pickup_location_id = "+pickup_location_id+ " pickup_date = "+pickup_date+ " pickup_time = "+pickup_time                    
//                    + " dropoff_location_id = "+dropoff_location_id+ " dropoff_date = "+dropoff_date+ " dropoff_time = "+dropoff_time
//                    + " total_price = "+total_price);
                
                // populate jTable
                populateJTable(); 
            }   
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Edit Customer", 2);
            //System.out.println("Erro ao ler dados: " + ex.getMessage());
        }   
    }//GEN-LAST:event_jButton_booking_editActionPerformed

    private void jButton_booking_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_booking_deleteActionPerformed
         // Remove a customer
        int booking_id = Integer.parseInt(jLabel_bookingId.getText());
        
        // confirm to delete
        int confirm = JOptionPane.showConfirmDialog(null, "Deseja realmente eliminar esta Reserva?", "Confirmar Ação", JOptionPane.YES_NO_OPTION);
        if(confirm == JOptionPane.YES_OPTION) {
            booking.deleteBooking(booking_id);
                    
            // populate jTable
            populateJTable();
        }
    }//GEN-LAST:event_jButton_booking_deleteActionPerformed

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
            java.util.logging.Logger.getLogger(Form_Booking_Edit_Remove.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_Booking_Edit_Remove.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_Booking_Edit_Remove.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_Booking_Edit_Remove.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_Booking_Edit_Remove().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.github.lgooddatepicker.components.DatePicker datePicker_dropoff_date;
    private com.github.lgooddatepicker.components.DatePicker datePicker_pickup_date;
    private javax.swing.JButton jButton_booking_delete;
    private javax.swing.JButton jButton_booking_edit;
    private javax.swing.JButton jButton_calcBooking_edit;
    private javax.swing.JButton jButton_selectCustomer;
    private javax.swing.JComboBox<String> jComboBox_brands_book_edit;
    private javax.swing.JComboBox<String> jComboBox_car_book_edit;
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
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_bookingId;
    private javax.swing.JLabel jLabel_car_price_edit;
    private javax.swing.JLabel jLabel_title2;
    private javax.swing.JLabel jLabel_total_price_edit;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable_bookingList;
    private static javax.swing.JTextField jTextField_customer;
    private com.github.lgooddatepicker.components.TimePicker timePicker_dropoff_time;
    private com.github.lgooddatepicker.components.TimePicker timePicker_pickup_time;
    // End of variables declaration//GEN-END:variables
}
