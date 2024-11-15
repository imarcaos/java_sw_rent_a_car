/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package java_mm_rent_a_car;

import java.util.ArrayList;
import java_mm_rent_a_car.classes.Booking;
import java_mm_rent_a_car.classes.Car;
import java_mm_rent_a_car.classes.Customer;

/**
 *
 * @author Marcos Melo
 */
public class Form_Dashboard extends javax.swing.JFrame {

    /**
     * Creates new form Form_Dashboard
     */
    
    //  Count records inserted
    // Classes
    Car car = new Car();
    Customer customer = new Customer();
    Booking booking = new Booking();
    // Arrays to count
    ArrayList<Car> carsList = car.carsList();
    ArrayList<Customer> customersList = customer.customersList();
    ArrayList<Booking> bookingsList = booking.bookingList();
    
    public Form_Dashboard() {
        initComponents();
        
        // center form
        this.setLocationRelativeTo(null);
        
        // Show counts
        jLabel_count_cars.setText(String.valueOf(carsList.size()));
        jLabel_count_customers.setText(String.valueOf(customersList.size()));
        jLabel_count_bookings.setText(String.valueOf(bookingsList.size()));        
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
        jLabel_cars = new javax.swing.JLabel();
        jLabel_locations = new javax.swing.JLabel();
        jLabel_customers = new javax.swing.JLabel();
        jLabel_bookings = new javax.swing.JLabel();
        jLabel_brands = new javax.swing.JLabel();
        jLabel_users = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel_count_cars = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel_count_customers = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel_count_bookings = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel_username = new javax.swing.JLabel();
        jLabel_userType = new javax.swing.JLabel();
        jLabel_userProfilePic = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jPanel3.setBackground(new java.awt.Color(204, 255, 153));

        jLabel_cars.setBackground(new java.awt.Color(204, 255, 153));
        jLabel_cars.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel_cars.setForeground(new java.awt.Color(51, 51, 51));
        jLabel_cars.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_cars.setText("Viaturas");
        jLabel_cars.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_cars.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_carsMouseClicked(evt);
            }
        });

        jLabel_locations.setBackground(new java.awt.Color(204, 255, 153));
        jLabel_locations.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel_locations.setForeground(new java.awt.Color(51, 51, 51));
        jLabel_locations.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_locations.setText("Localizações");
        jLabel_locations.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_locations.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_locationsMouseClicked(evt);
            }
        });

        jLabel_customers.setBackground(new java.awt.Color(204, 255, 153));
        jLabel_customers.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel_customers.setForeground(new java.awt.Color(51, 51, 51));
        jLabel_customers.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_customers.setText("Clientes");
        jLabel_customers.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_customers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_customersMouseClicked(evt);
            }
        });

        jLabel_bookings.setBackground(new java.awt.Color(204, 255, 153));
        jLabel_bookings.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel_bookings.setForeground(new java.awt.Color(51, 51, 51));
        jLabel_bookings.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_bookings.setText("Reservas");
        jLabel_bookings.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_bookings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_bookingsMouseClicked(evt);
            }
        });

        jLabel_brands.setBackground(new java.awt.Color(204, 255, 153));
        jLabel_brands.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel_brands.setForeground(new java.awt.Color(51, 51, 51));
        jLabel_brands.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_brands.setText("Marcas");
        jLabel_brands.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_brands.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_brandsMouseClicked(evt);
            }
        });

        jLabel_users.setBackground(new java.awt.Color(204, 255, 153));
        jLabel_users.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel_users.setForeground(new java.awt.Color(51, 51, 51));
        jLabel_users.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_users.setText("Utilizadores");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel_locations, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_customers, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_brands, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_bookings, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_cars, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_users, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel_brands)
                .addGap(27, 27, 27)
                .addComponent(jLabel_cars)
                .addGap(31, 31, 31)
                .addComponent(jLabel_locations)
                .addGap(31, 31, 31)
                .addComponent(jLabel_customers)
                .addGap(34, 34, 34)
                .addComponent(jLabel_bookings)
                .addGap(33, 33, 33)
                .addComponent(jLabel_users)
                .addContainerGap(186, Short.MAX_VALUE))
        );

        jLabel_count_cars.setBackground(new java.awt.Color(102, 102, 102));
        jLabel_count_cars.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel_count_cars.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_count_cars.setText("100");
        jLabel_count_cars.setOpaque(true);

        jLabel8.setBackground(new java.awt.Color(51, 51, 51));
        jLabel8.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Viaturas");
        jLabel8.setOpaque(true);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_count_cars, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_count_cars, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel_count_customers.setBackground(new java.awt.Color(102, 102, 102));
        jLabel_count_customers.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel_count_customers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_count_customers.setText("100");
        jLabel_count_customers.setOpaque(true);

        jLabel10.setBackground(new java.awt.Color(51, 51, 51));
        jLabel10.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Clientes");
        jLabel10.setOpaque(true);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_count_customers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel_count_customers, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel_count_bookings.setBackground(new java.awt.Color(102, 102, 102));
        jLabel_count_bookings.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel_count_bookings.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_count_bookings.setText("100");
        jLabel_count_bookings.setOpaque(true);

        jLabel12.setBackground(new java.awt.Color(51, 51, 51));
        jLabel12.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Reservas");
        jLabel12.setOpaque(true);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_count_bookings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel_count_bookings, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(204, 255, 153));

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("PAINEL DE CONTROLO");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(359, 359, 359)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel6)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jLabel_username.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_username.setForeground(new java.awt.Color(51, 51, 51));
        jLabel_username.setText("utilizador conectado");

        jLabel_userType.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel_userType.setForeground(new java.awt.Color(51, 51, 51));
        jLabel_userType.setText("tipo de utilizador");

        jLabel_userProfilePic.setOpaque(true);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(110, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_username)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel_userType)))
                        .addGap(37, 37, 37)
                        .addComponent(jLabel_userProfilePic, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(118, 118, 118))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jLabel_userProfilePic, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jLabel_username)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel_userType)))
                        .addGap(60, 60, 60)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))))
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
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 688, Short.MAX_VALUE)
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

    private void jLabel_brandsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_brandsMouseClicked
        // Open the Brands form
        Form_Brands frm_bds = new Form_Brands();
        frm_bds.setVisible(true);        
    }//GEN-LAST:event_jLabel_brandsMouseClicked

    private void jLabel_carsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_carsMouseClicked
        // Open the Cars form
        Form_Cars frm_crs = new Form_Cars();
        frm_crs.setVisible(true);  
    }//GEN-LAST:event_jLabel_carsMouseClicked

    private void jLabel_locationsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_locationsMouseClicked
        // Open the Locations form
        Form_Locations frm_lct = new Form_Locations();
        frm_lct.setVisible(true); 
    }//GEN-LAST:event_jLabel_locationsMouseClicked

    private void jLabel_customersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_customersMouseClicked
        // Open the Customers form
        Form_Customers frm_ctr = new Form_Customers();
        frm_ctr.setVisible(true);         
    }//GEN-LAST:event_jLabel_customersMouseClicked

    private void jLabel_bookingsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_bookingsMouseClicked
        // Open the Bookings Form
        Form_Bookings frm_bkg = new Form_Bookings();
        frm_bkg.setVisible(true);
    }//GEN-LAST:event_jLabel_bookingsMouseClicked

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
            java.util.logging.Logger.getLogger(Form_Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel_bookings;
    private javax.swing.JLabel jLabel_brands;
    private javax.swing.JLabel jLabel_cars;
    private javax.swing.JLabel jLabel_count_bookings;
    private javax.swing.JLabel jLabel_count_cars;
    private javax.swing.JLabel jLabel_count_customers;
    private javax.swing.JLabel jLabel_customers;
    private javax.swing.JLabel jLabel_locations;
    public static javax.swing.JLabel jLabel_userProfilePic;
    public static javax.swing.JLabel jLabel_userType;
    public static javax.swing.JLabel jLabel_username;
    private javax.swing.JLabel jLabel_users;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    // End of variables declaration//GEN-END:variables
}
