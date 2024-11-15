/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package java_mm_rent_a_car;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java_mm_rent_a_car.classes.Brand;
import java_mm_rent_a_car.classes.Car;
import javax.swing.JOptionPane;

/**
 *
 * @author Marcos Melo
 */
public class Form_Cars extends javax.swing.JFrame {

    /**
     * Creates new form Form_Cars
     */
    
    // Global Variables
    static int car_id_ = 0; // edit and delete
    static int brand_id_ = 0; // when combobox is selected    
    static String model_ = "";
    static String plate_ = "";
    static int km_ = 0;
    static String fuel_ = "";
    static String color_ = "";     
    static int class_ = 0;
    static int passengers_ = 0;
    static int doors_ = 0;
    static float price_ = 0;
    static String gearbox_ = "";
    
    static String air_conditioning_ = "";
    static String sunroof_ = "";
    static String heated_seats_ = "";        
    static String navigation_system_ = "";
    static String gps_ = "";
    static String bluetooth_ = "";
    static String wifi_ = ""; 
    
    
    Car car = new Car();
    
    Brand brand = new Brand();
    HashMap<Integer, String> map = brand.brandsHashMap();   
    
    public Form_Cars() {
        initComponents();
        
        // center form
        this.setLocationRelativeTo(null);
        
        // populate ComboBox Brand
        populateComboboxBrands();
    }
    
    //Method to populate the ComboBox Brands "jComboBox_brand" with brands.
    public void populateComboboxBrands() {      
        
        for(String key: map.values()) {
            //System.out.println(key);
            // add each loop the key = brand to ComboBox
            jComboBox_brand.addItem(key);
        }
    }
    
    // Method to check empty fields
    public boolean verify(String add_edit) {
                
        boolean val = false; // variable to check       

        if(add_edit == "add") {
            if(!model_.trim().equals("") && !plate_.trim().equals("") 
                    && km_ != 0 && !color_.trim().equals("") && !fuel_.trim().equals("") 
                    && class_ != 0 && passengers_ != 0 && doors_ != 0 && price_ != 0) {
                val = true;
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, verifique os campos vazios ou a zero!", "Informação Incompleta", 2);
                val = false;
            }            
        }                
        return val;
    }
    
    public static void clearFields() {
        // Clear Fields        
        jLabel_car_id.setText("000");
        jSpinner_passengers.setValue(0);
        jSpinner_priceDay.setValue(0);
        
        jTextField_km.setText("");
        jTextField_model.setText("");
        jTextField_plate.setText("");
        //jTextField_priceDay.setText("");
        
        jComboBox_brand.setSelectedIndex(0);
        jComboBox_class.setSelectedIndex(0);
        jComboBox_color.setSelectedIndex(0);
        jComboBox_doors.setSelectedIndex(0);
        jComboBox_fuel.setSelectedIndex(0);
        
        jRadioButton_gear_auto.setSelected(true);
        
        jCheckBox_features_aircond.setSelected(false);
        jCheckBox_features_bluetooth.setSelected(false);
        jCheckBox_features_gps.setSelected(false);
        jCheckBox_features_heatedseats.setSelected(false);
        jCheckBox_features_navsystem.setSelected(false);
        jCheckBox_features_sunroof.setSelected(false);
        jCheckBox_features_wifi.setSelected(false);
        
    }
    
    // Method to receive data from Form_CarsList to edit
    public static void displayCars(String car_id, String brand_id, String model, String plate, 
            String km, String fuel, String color, String _class, String passengers, String doors,
            String gearbox, String price, String air_cond, String sunroof, String heated_seats,
            String navigation_system, String gps, String bluetooth, String wifi) {

           clearFields();        
        
                
//        // Its necessary to create a new instance of Car and Brand, because this method is static  
//        Car car_ = new Car();
//        Car e_car = car_.getCarById(Integer.parseInt(car_id));
//        
//        Brand e_brand = new Brand();
//        
//        // set all components with data
//        jLabel_car_id.setText(car_id);
//        jComboBox_brand.setSelectedItem(e_brand.getBrandById(Integer.parseInt(brand_id)).getName());
//        jTextField_model.setText(model);

        // Its necessary to create a new instance of Brand, because this method is static 
        Brand e_brand = new Brand();
        
        // No String vars        
        car_id_ = Integer.parseInt(car_id);
        brand_id_ = Integer.parseInt(brand_id); 
        km_ = Integer.parseInt(km);
        class_ = Integer.parseInt(_class); 
        passengers_ = Integer.parseInt(passengers); 
        doors_ = Integer.parseInt(doors); 
        price_ = Float.valueOf(price);
        
        // String vars   
        model_ = model;
        plate_ = plate;
        fuel_ = fuel;
        color_ = color;
        gearbox_ = gearbox;

        air_conditioning_ = air_cond;
        sunroof_ = sunroof;
        heated_seats_ = heated_seats;        
        navigation_system_ = navigation_system;
        gps_ = gps;
        bluetooth_ = bluetooth;
        wifi_ = wifi;        
        
        // set all components with data
        jLabel_car_id.setText(car_id);
        jComboBox_brand.setSelectedItem(e_brand.getBrandById(brand_id_).getName());
        jTextField_model.setText(model);
        jTextField_plate.setText(plate);
        jTextField_km.setText(km);
        jComboBox_color.setSelectedItem(color);
        jComboBox_class.setSelectedItem(class_);
        jSpinner_passengers.setValue(passengers_);
        if(gearbox.equals("manual")) {
            jRadioButton_gear_manual.setSelected(true);
        } else {
            jRadioButton_gear_auto.setSelected(true);
        }
        jComboBox_fuel.setSelectedItem(fuel);
        jComboBox_doors.setSelectedItem(doors_);
        jSpinner_priceDay.setValue(price_);
        
        // Features checkbox's
        if(air_cond.equals("yes")) {jCheckBox_features_aircond.setSelected(true);}
        if(sunroof.equals("yes")) {jCheckBox_features_sunroof.setSelected(true);}
        if(heated_seats.equals("yes")) {jCheckBox_features_heatedseats.setSelected(true);}
        if(navigation_system.equals("yes")) {jCheckBox_features_navsystem.setSelected(true);}
        if(gps.equals("yes")) {jCheckBox_features_gps.setSelected(true);}
        if(bluetooth.equals("yes")) {jCheckBox_features_bluetooth.setSelected(true);}
        if(wifi.equals("yes")) {jCheckBox_features_wifi.setSelected(true);}
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton_edit = new javax.swing.JButton();
        jButton_add = new javax.swing.JButton();
        jButton_remove = new javax.swing.JButton();
        jButton_clearFields = new javax.swing.JButton();
        jComboBox_brand = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jCheckBox_features_aircond = new javax.swing.JCheckBox();
        jCheckBox_features_heatedseats = new javax.swing.JCheckBox();
        jCheckBox_features_wifi = new javax.swing.JCheckBox();
        jCheckBox_features_bluetooth = new javax.swing.JCheckBox();
        jCheckBox_features_navsystem = new javax.swing.JCheckBox();
        jCheckBox_features_gps = new javax.swing.JCheckBox();
        jCheckBox_features_sunroof = new javax.swing.JCheckBox();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextField_model = new javax.swing.JTextField();
        jTextField_plate = new javax.swing.JTextField();
        jTextField_km = new javax.swing.JTextField();
        jComboBox_color = new javax.swing.JComboBox<>();
        jComboBox_class = new javax.swing.JComboBox<>();
        jComboBox_fuel = new javax.swing.JComboBox<>();
        jComboBox_doors = new javax.swing.JComboBox<>();
        jSpinner_passengers = new javax.swing.JSpinner();
        jButton_brandList = new javax.swing.JButton();
        jButton_addImage = new javax.swing.JButton();
        jButton_carList = new javax.swing.JButton();
        jRadioButton_gear_manual = new javax.swing.JRadioButton();
        jRadioButton_gear_auto = new javax.swing.JRadioButton();
        jSpinner_priceDay = new javax.swing.JSpinner();
        jLabel_car_id = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jPanel3.setBackground(new java.awt.Color(0, 204, 204));

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Viaturas");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(444, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(436, 436, 436))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(40, 40, 40))
        );

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("ID");

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Modelo");

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Marca");

        jButton_edit.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton_edit.setText("Editar");
        jButton_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_editActionPerformed(evt);
            }
        });

        jButton_add.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton_add.setText("Adicionar");
        jButton_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_addActionPerformed(evt);
            }
        });

        jButton_remove.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton_remove.setText("Eliminar");
        jButton_remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_removeActionPerformed(evt);
            }
        });

        jButton_clearFields.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton_clearFields.setText("Limpar Campos");
        jButton_clearFields.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_clearFieldsActionPerformed(evt);
            }
        });

        jComboBox_brand.setBackground(new java.awt.Color(51, 51, 51));
        jComboBox_brand.setEditable(true);
        jComboBox_brand.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jComboBox_brand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_brandActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Combustível");

        jLabel6.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Cor");

        jLabel7.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Passageiros");

        jLabel8.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Caixa Velocidade");

        jLabel9.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Classe");

        jLabel10.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Preço/dia");

        jLabel11.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 51, 0));
        jLabel11.setText("Opcionais:");

        jCheckBox_features_aircond.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jCheckBox_features_aircond.setText("Ar Condicionado");

        jCheckBox_features_heatedseats.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jCheckBox_features_heatedseats.setText("Bancos Aquecidos");

        jCheckBox_features_wifi.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jCheckBox_features_wifi.setText("Wifi - Internet");

        jCheckBox_features_bluetooth.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jCheckBox_features_bluetooth.setText("Bluetooth");

        jCheckBox_features_navsystem.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jCheckBox_features_navsystem.setText("Sistema de Navegação");

        jCheckBox_features_gps.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jCheckBox_features_gps.setText("GPS");

        jCheckBox_features_sunroof.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jCheckBox_features_sunroof.setText("Tecto de Abrir");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jCheckBox_features_aircond)
                .addGap(18, 18, 18)
                .addComponent(jCheckBox_features_sunroof, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jCheckBox_features_heatedseats, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jCheckBox_features_navsystem, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jCheckBox_features_gps, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jCheckBox_features_bluetooth, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jCheckBox_features_wifi, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jCheckBox_features_aircond)
                    .addComponent(jCheckBox_features_heatedseats)
                    .addComponent(jCheckBox_features_navsystem)
                    .addComponent(jCheckBox_features_bluetooth)
                    .addComponent(jCheckBox_features_gps)
                    .addComponent(jCheckBox_features_wifi)
                    .addComponent(jCheckBox_features_sunroof))
                .addGap(16, 16, 16))
        );

        jLabel12.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Matrícula");

        jLabel13.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("KM");

        jLabel14.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Portas");

        jTextField_model.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N

        jTextField_plate.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N

        jTextField_km.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N

        jComboBox_color.setBackground(new java.awt.Color(51, 51, 51));
        jComboBox_color.setEditable(true);
        jComboBox_color.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jComboBox_color.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Branco", "Preto", "Cinzento", "Vermelho", "Verde", "Azul", "Castanho", "Laranja" }));

        jComboBox_class.setBackground(new java.awt.Color(51, 51, 51));
        jComboBox_class.setEditable(true);
        jComboBox_class.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jComboBox_class.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3" }));

        jComboBox_fuel.setBackground(new java.awt.Color(51, 51, 51));
        jComboBox_fuel.setEditable(true);
        jComboBox_fuel.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jComboBox_fuel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gasóleo", "Gasolina", "Eléctrico", "Híbrido" }));

        jComboBox_doors.setBackground(new java.awt.Color(51, 51, 51));
        jComboBox_doors.setEditable(true);
        jComboBox_doors.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jComboBox_doors.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2", "3", "5", "6" }));

        jSpinner_passengers.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N

        jButton_brandList.setBackground(new java.awt.Color(102, 102, 102));
        jButton_brandList.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton_brandList.setText("Lista de Marcas");
        jButton_brandList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_brandListActionPerformed(evt);
            }
        });

        jButton_addImage.setBackground(new java.awt.Color(102, 102, 102));
        jButton_addImage.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton_addImage.setText("Adicionar Imagem");
        jButton_addImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_addImageActionPerformed(evt);
            }
        });

        jButton_carList.setBackground(new java.awt.Color(102, 102, 102));
        jButton_carList.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton_carList.setText("Escolher Viaturas - Lista Geral");
        jButton_carList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_carListActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton_gear_manual);
        jRadioButton_gear_manual.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jRadioButton_gear_manual.setForeground(new java.awt.Color(51, 51, 51));
        jRadioButton_gear_manual.setText("Manual");

        buttonGroup1.add(jRadioButton_gear_auto);
        jRadioButton_gear_auto.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jRadioButton_gear_auto.setForeground(new java.awt.Color(51, 51, 51));
        jRadioButton_gear_auto.setSelected(true);
        jRadioButton_gear_auto.setText("Automático");

        jSpinner_priceDay.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N

        jLabel_car_id.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel_car_id.setForeground(new java.awt.Color(0, 51, 51));
        jLabel_car_id.setText("000");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextField_km, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox_color, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(448, 448, 448))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel12)
                                                .addGap(12, 12, 12))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addGap(18, 18, 18)))
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jTextField_model)
                                            .addComponent(jTextField_plate, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel1))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel_car_id, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jComboBox_brand, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(241, 241, 241)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jRadioButton_gear_auto)
                                .addGap(27, 27, 27)
                                .addComponent(jRadioButton_gear_manual))
                            .addComponent(jComboBox_fuel, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jSpinner_passengers, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jComboBox_class, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jComboBox_doors, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSpinner_priceDay, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(83, 83, 83))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton_add, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(jButton_remove, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton_carList, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton_clearFields, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(104, 104, 104)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton_brandList, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_addImage, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_car_id, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox_brand, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_model, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jTextField_plate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jTextField_km, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jComboBox_color, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jComboBox_class, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jSpinner_passengers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jRadioButton_gear_auto)
                            .addComponent(jRadioButton_gear_manual))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jComboBox_fuel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jComboBox_doors, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jSpinner_priceDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(7, 7, 7)
                .addComponent(jLabel11)
                .addGap(12, 12, 12)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton_carList, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_addImage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton_remove, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton_brandList, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton_add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_clearFields, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(37, 37, 37))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1188, Short.MAX_VALUE)
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

    private void jButton_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_addActionPerformed
        // Add a new car
        // car essential information      

        model_ = jTextField_model.getText();
        plate_ = jTextField_plate.getText();
        km_ = Integer.parseInt(jTextField_km.getText());

        // Check empty fields
        if (model_.isEmpty() || plate_.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos obrigatórios.");
            return;
        }


        // JComboBox check
        if (jComboBox_fuel.getSelectedItem() == null || 
            jComboBox_color.getSelectedItem() == null || 
            jComboBox_class.getSelectedItem() == null || 
            jComboBox_doors.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um item para cada combobox.");
            return; 
        }

        fuel_ = jComboBox_fuel.getSelectedItem().toString();
        color_ = jComboBox_color.getSelectedItem().toString();
        class_ = Integer.parseInt(jComboBox_class.getSelectedItem().toString());
        passengers_ = (int)jSpinner_passengers.getValue();
        String gearbox = jRadioButton_gear_manual.isSelected() ? "manual" : "automatic";
        doors_ = Integer.parseInt(jComboBox_doors.getSelectedItem().toString());
        price_ = ((Number) jSpinner_priceDay.getValue()).floatValue();

        // car opcional information
        String air_conditioning = jCheckBox_features_aircond.isSelected() ? "yes" : "no";
        String sunroof = jCheckBox_features_sunroof.isSelected() ? "yes" : "no";
        String heated_seats = jCheckBox_features_heatedseats.isSelected() ? "yes" : "no";        
        String navigation_system = jCheckBox_features_navsystem.isSelected() ? "yes" : "no";
        String gps = jCheckBox_features_gps.isSelected() ? "yes" : "no";
        String bluetooth = jCheckBox_features_bluetooth.isSelected() ? "yes" : "no";
        String wifi = jCheckBox_features_wifi.isSelected() ? "yes" : "no"; 
        
        // Check for empty fields and Call the method addCar in Brand Class
        if (verify("add")) {
//            System.out.println("dentro do verify add");
             car.addCar(brand_id_, model_, plate_, km_, fuel_, color_,
                        class_, passengers_, doors_, gearbox, price_,
                        air_conditioning, sunroof, heated_seats,
                        navigation_system, gps, bluetooth, wifi);
        }
        clearFields();        
    }//GEN-LAST:event_jButton_addActionPerformed

    private void jButton_removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_removeActionPerformed
        // Delete a Car
        
        // confirm to delete
        int confirm = JOptionPane.showConfirmDialog(null, "Deseja realmente eliminar esta Veículo?", "Confirmar Ação", JOptionPane.YES_NO_OPTION);
        if(confirm == JOptionPane.YES_OPTION) {
            car.deleteCar(car_id_);
//            System.out.println(car_id_);
        }        
        clearFields();  
    }//GEN-LAST:event_jButton_removeActionPerformed

    private void jButton_clearFieldsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_clearFieldsActionPerformed
        // Call method to clear fields
        clearFields();
    }//GEN-LAST:event_jButton_clearFieldsActionPerformed

    private void jButton_brandListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_brandListActionPerformed
        // Open the Brand List
        Form_BrandsList frm_brdList = new Form_BrandsList();
        frm_brdList.setVisible(true);
    }//GEN-LAST:event_jButton_brandListActionPerformed

    private void jButton_addImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_addImageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_addImageActionPerformed

    private void jButton_carListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_carListActionPerformed
        // Open the Car List
        Form_CarsList frm_carsList = new Form_CarsList();
        frm_carsList.setVisible(true);
    }//GEN-LAST:event_jButton_carListActionPerformed

    private void jButton_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_editActionPerformed
        // Edit a Car

        model_ = jTextField_model.getText();
        plate_ = jTextField_plate.getText();
        km_ = Integer.parseInt(jTextField_km.getText());

        // Check empty fields
        if (model_.isEmpty() || plate_.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos obrigatórios.");
            return;
        }


        // JComboBox check
        if (jComboBox_fuel.getSelectedItem() == null || 
            jComboBox_color.getSelectedItem() == null || 
            jComboBox_class.getSelectedItem() == null || 
            jComboBox_doors.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um item para cada combobox.");
            return; 
        }

        fuel_ = jComboBox_fuel.getSelectedItem().toString();
        color_ = jComboBox_color.getSelectedItem().toString();
        class_ = Integer.parseInt(jComboBox_class.getSelectedItem().toString());
        passengers_ = (int)jSpinner_passengers.getValue();
        gearbox_ = jRadioButton_gear_manual.isSelected() ? "manual" : "automatic";
        doors_ = Integer.parseInt(jComboBox_doors.getSelectedItem().toString());
        price_ = ((Number) jSpinner_priceDay.getValue()).floatValue();

        // car opcional information
        air_conditioning_ = jCheckBox_features_aircond.isSelected() ? "yes" : "no";
        sunroof_ = jCheckBox_features_sunroof.isSelected() ? "yes" : "no";
        heated_seats_ = jCheckBox_features_heatedseats.isSelected() ? "yes" : "no";        
        navigation_system_ = jCheckBox_features_navsystem.isSelected() ? "yes" : "no";
        gps_ = jCheckBox_features_gps.isSelected() ? "yes" : "no";
        bluetooth_ = jCheckBox_features_bluetooth.isSelected() ? "yes" : "no";
        wifi_ = jCheckBox_features_wifi.isSelected() ? "yes" : "no"; 
        
        if (verify("add")) {
//            System.out.println("car_id_: " + car_id_);
//            System.out.println("brand_id_: " + brand_id_);
//            System.out.println("model_: " + model_);
//            System.out.println("plate_: " + plate_);
//            System.out.println("km_: " + km_);
//            System.out.println("fuel_: " + fuel_);
//            System.out.println("color_: " + color_);
//            System.out.println("class_: " + class_);
//            System.out.println("passengers_: " + passengers_);
//            System.out.println("doors_: " + doors_);
//            System.out.println("price_: " + price_);
//            System.out.println("gearbox_: " + gearbox_);
//            
//            System.out.println("air_conditioning_: " + air_conditioning_);
//            System.out.println("sunroof_: " + sunroof_);
//            System.out.println("heated_seats_: " + heated_seats_);
//            System.out.println("navigation_system_: " + navigation_system_);
//            System.out.println("gps_: " + gps_);
//            System.out.println("bluetooth_: " + bluetooth_);
//            System.out.println("wifi_: " + wifi_);

            
        car.editCar(car_id_, brand_id_, model_, plate_, km_, fuel_, color_,
                        class_, passengers_, doors_, gearbox_, price_,
                        air_conditioning_, sunroof_, heated_seats_,
                        navigation_system_, gps_, bluetooth_, wifi_);
        }
        clearFields();
    }//GEN-LAST:event_jButton_editActionPerformed

    private void jComboBox_brandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_brandActionPerformed
        // get id the selected brand
        for(Map.Entry<Integer, String> entry : map.entrySet()) {
            if(entry.getValue().equals(jComboBox_brand.getSelectedItem().toString())) {
                brand_id_ = entry.getKey();
            }
        }
    }//GEN-LAST:event_jComboBox_brandActionPerformed

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
            java.util.logging.Logger.getLogger(Form_Cars.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_Cars.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_Cars.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_Cars.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_Cars().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton_add;
    private javax.swing.JButton jButton_addImage;
    private javax.swing.JButton jButton_brandList;
    private javax.swing.JButton jButton_carList;
    private javax.swing.JButton jButton_clearFields;
    private javax.swing.JButton jButton_edit;
    private javax.swing.JButton jButton_remove;
    private static javax.swing.JCheckBox jCheckBox_features_aircond;
    private static javax.swing.JCheckBox jCheckBox_features_bluetooth;
    private static javax.swing.JCheckBox jCheckBox_features_gps;
    private static javax.swing.JCheckBox jCheckBox_features_heatedseats;
    private static javax.swing.JCheckBox jCheckBox_features_navsystem;
    private static javax.swing.JCheckBox jCheckBox_features_sunroof;
    private static javax.swing.JCheckBox jCheckBox_features_wifi;
    private static javax.swing.JComboBox<String> jComboBox_brand;
    private static javax.swing.JComboBox<String> jComboBox_class;
    private static javax.swing.JComboBox<String> jComboBox_color;
    private static javax.swing.JComboBox<String> jComboBox_doors;
    private static javax.swing.JComboBox<String> jComboBox_fuel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private static javax.swing.JLabel jLabel_car_id;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private static javax.swing.JRadioButton jRadioButton_gear_auto;
    private static javax.swing.JRadioButton jRadioButton_gear_manual;
    private static javax.swing.JSpinner jSpinner_passengers;
    private static javax.swing.JSpinner jSpinner_priceDay;
    private static javax.swing.JTextField jTextField_km;
    private static javax.swing.JTextField jTextField_model;
    private static javax.swing.JTextField jTextField_plate;
    // End of variables declaration//GEN-END:variables
}
