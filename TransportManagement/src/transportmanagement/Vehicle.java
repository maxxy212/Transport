/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transportmanagement;

import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Maxwell
 */
public class Vehicle extends javax.swing.JFrame {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
     private ImageIcon format = null;
    String filename = null;
    byte[] person_image = null;
    InputStream is = null;
    /**
     * Creates new form Vehicle
     */
    public Vehicle() {
        initComponents();
        conn = dbConnect.cone();
        update_table();
        update_tableInsurance();
        update_tableTyre();
        Fillcombo();
        FillcomboTyre();
        update_tableVTyre();
        jComboBoxVid.setSelectedItem(null);
        jComboBoxTyre.setSelectedItem(null);
        jComboBox4Locate.setSelectedItem(null);
    }
    
    private void Fillcombo()
    {
        try
        {
           String sql = "select * from vehicleDetails"; 
           pst = conn.prepareStatement(sql);
           rs = pst.executeQuery();
           
           while(rs.next())
           {
               String car = rs.getString("make");
               String model = rs.getString("model");
               String color = rs.getString("color");
               String reg = rs.getString("Vreg");
               int name = rs.getInt("vId");
               jComboBoxVid.addItem(name);
               jComboBoxVid.setToolTipText(color +" "+ car +" "+ model + " with registration Id: " + reg);
           }

        }
        catch(Exception e)
                {
                   JOptionPane.showMessageDialog(null, e); 
                }
    }
    
     private void updateCombo()
     {
          Thread comb = new Thread()
                    {
                        public void run()
                        {
                           try
        {
           DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
           String sql = "select * from vehicleDetails"; 
           pst = conn.prepareStatement(sql);
           rs = pst.executeQuery();
           
           while(rs.next())
           {
               int name = rs.getInt("vId");
               model.addElement(String.valueOf(name));   
           }
           jComboBoxVid.setModel(model);
             
        }
        catch(Exception e)
                {
                   JOptionPane.showMessageDialog(null, e); 
                } 
                        }
                        
                    };
                    comb.start();
     }
     
    private void FillcomboTyre()
    {
        
        try
        {
           String sql = "select * from tyre"; 
           pst = conn.prepareStatement(sql);
           rs = pst.executeQuery();
           
           while(rs.next())
           {
               String mak = rs.getString("make");
               String size = rs.getString("size");
               String serial = rs.getString("serial");
               int name = rs.getInt("tyreId");
               jComboBoxTyre.addItem(name);
               jComboBoxTyre.setToolTipText(mak +" tyre of size "+ size +" with serial number: "+ serial );
           }
       
        }
        catch(Exception e)
                {
                   JOptionPane.showMessageDialog(null, e); 
                }
    }
     private void updateComboTyre()
     {
          Thread comb = new Thread()
                    {
                        public void run()
                        {
                           try
        {
           DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
           String sql = "select * from tyre"; 
           pst = conn.prepareStatement(sql);
           rs = pst.executeQuery();
           
           while(rs.next())
           {
               int name = rs.getInt("tyreId");
               model.addElement(String.valueOf(name));   
           }
           jComboBoxTyre.setModel(model);
             
        }
        catch(Exception e)
                {
                   JOptionPane.showMessageDialog(null, e); 
                } 
                        }
                        
                    };
                    comb.start();
     }
    
    private void update_tableInsurance()
    {
        try{
        String sql = "select * from Vinsurance";
        pst = conn.prepareStatement(sql);
        rs = pst.executeQuery();
        jTableVinsure.setModel(DbUtils.resultSetToTableModel(rs));
        
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        finally
        {
            try
            {
                rs.close();
                pst.close();
            }
            catch(Exception e)
            {
                
            }
        }
    }

    
     private void update_table()
    {
        try{
        String sql = "select * from vehicleDetails";
        pst = conn.prepareStatement(sql);
        rs = pst.executeQuery();
        jTableVDetails.setModel(DbUtils.resultSetToTableModel(rs));
        
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        finally
        {
            try
            {
                rs.close();
                pst.close();
            }
            catch(Exception e)
            {
                
            }
        }
    }
     
      private void update_tableVTyre()
    {
        try{
        String sql = "select * from Vtyre";
        pst = conn.prepareStatement(sql);
        rs = pst.executeQuery();
        jTable5VTyre.setModel(DbUtils.resultSetToTableModel(rs));
        
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        finally
        {
            try
            {
                rs.close();
                pst.close();
            }
            catch(Exception e)
            {
                
            }
        }
    }
     
      private void update_tableTyre()
    {
        try{
        String sql = "select * from tyre";
        pst = conn.prepareStatement(sql);
        rs = pst.executeQuery();
        jTableTyre.setModel(DbUtils.resultSetToTableModel(rs));
        
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        finally
        {
            try
            {
                rs.close();
                pst.close();
            }
            catch(Exception e)
            {
                
            }
        }
    }
    
    public void ReadImage(String user, JLabel jl){
        try{

            String query = "SELECT img FROM vehicleDetails WHERE Vreg='"+user+"'";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            byte[] imageData = rs.getBytes(1);
            ImageIcon ii = new ImageIcon(imageData);
            Image image = ii.getImage();
            image = image.getScaledInstance(214, 142, Image.SCALE_SMOOTH);
            ii = new ImageIcon(image);
            jl.setIcon(ii);

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
     public static BufferedImage resize(BufferedImage image, int width, int height) 
    {
    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
    Graphics2D g2d = (Graphics2D) bi.createGraphics();
    g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
    g2d.drawImage(image, 0, 0, width, height, null);
    g2d.dispose();
    return bi;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jButtonHome = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextRegId = new javax.swing.JTextField();
        jTextChasis = new javax.swing.JTextField();
        jTextInsurance = new javax.swing.JTextField();
        jTextAmount = new javax.swing.JTextField();
        jTextMake = new javax.swing.JTextField();
        jTextModel = new javax.swing.JTextField();
        jTextSeat = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaNote = new javax.swing.JTextArea();
        jDatePurchase = new com.toedter.calendar.JDateChooser();
        jRegDate = new com.toedter.calendar.JDateChooser();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jimg = new javax.swing.JLabel();
        jimgattach = new javax.swing.JButton();
        jTextImage = new javax.swing.JTextField();
        jButtonSave = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableVDetails = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jTextSearch = new javax.swing.JTextField();
        jButtonUpdate = new javax.swing.JButton();
        jButtonDel = new javax.swing.JButton();
        jButtonClear = new javax.swing.JButton();
        jComboBoxEngine = new javax.swing.JComboBox();
        jTextColor = new javax.swing.JTextField();
        jCombotrans = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        jtextVid = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jTextSerial = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jComboBoxSize = new javax.swing.JComboBox();
        jTextmake = new javax.swing.JTextField();
        jDateChooserdate = new com.toedter.calendar.JDateChooser();
        jTextAMt = new javax.swing.JTextField();
        jDateChooserExpiry = new com.toedter.calendar.JDateChooser();
        jButton1Save = new javax.swing.JButton();
        jButton1Update = new javax.swing.JButton();
        jButton1Del = new javax.swing.JButton();
        jButtonClearF = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableTyre = new javax.swing.JTable();
        jTextSearchField = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jTextFieldTyre = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jComboBox4Locate = new javax.swing.JComboBox();
        jButton15Save = new javax.swing.JButton();
        jButton16Update = new javax.swing.JButton();
        jButton17Del = new javax.swing.JButton();
        jButton18clear = new javax.swing.JButton();
        jTextField23Search = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable5VTyre = new javax.swing.JTable();
        jComboBoxTyre = new javax.swing.JComboBox();
        jComboBoxVid = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jinsure = new javax.swing.JTextField();
        jCompany = new javax.swing.JTextField();
        jPay = new javax.swing.JTextField();
        jType = new javax.swing.JComboBox();
        jrenew = new javax.swing.JComboBox();
        jissue = new com.toedter.calendar.JDateChooser();
        jExpiry = new com.toedter.calendar.JDateChooser();
        jBSave = new javax.swing.JButton();
        jbuttonUpdate = new javax.swing.JButton();
        jDel = new javax.swing.JButton();
        jclear = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableVinsure = new javax.swing.JTable();
        jSearch = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jTextvIns = new javax.swing.JTextField();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButtonHome.setText("Home Page");
        jButtonHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHomeActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Sylfaen", 1, 24)); // NOI18N
        jLabel1.setText("Vehicle Management");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButtonHome, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jButtonHome, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jTabbedPane4.setForeground(new java.awt.Color(0, 0, 204));
        jTabbedPane4.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane4.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N

        jLabel2.setText("Registration Id:");

        jLabel3.setText("Chasis Id:");

        jLabel4.setText("Insurance Id:");

        jLabel5.setText("Make:");

        jLabel6.setText("Model:");

        jLabel7.setText("Color:");

        jLabel8.setText("Registration Date:");

        jLabel9.setText("Note:");

        jLabel10.setText("Seating Capacity:");

        jLabel11.setText("Engine Type:");

        jLabel12.setText("Transmission:");

        jLabel13.setText("Purchase Date:");

        jLabel14.setText("Purchase Amount:");

        jTextChasis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextChasisActionPerformed(evt);
            }
        });

        jTextAreaNote.setColumns(20);
        jTextAreaNote.setRows(5);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTextAreaNote, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jTextAreaNote, org.jdesktop.beansbinding.BeanProperty.create("lineWrap"));
        bindingGroup.addBinding(binding);

        jScrollPane1.setViewportView(jTextAreaNote);

        jimg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jimgMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jimg, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jimg, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
        );
        jDesktopPane1.setLayer(jimg, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jimgattach.setText("Attach Image");
        jimgattach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jimgattachActionPerformed(evt);
            }
        });

        jButtonSave.setText("SAVE");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jTableVDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableVDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableVDetailsMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTableVDetails);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Action Event", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(0, 0, 204))); // NOI18N

        jLabel15.setFont(new java.awt.Font("Sylfaen", 1, 14)); // NOI18N
        jLabel15.setText("Search:");

        jTextSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextSearchKeyReleased(evt);
            }
        });

        jButtonUpdate.setText("Update");
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });

        jButtonDel.setText("Delete");
        jButtonDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelActionPerformed(evt);
            }
        });

        jButtonClear.setText("Clear Fields");
        jButtonClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonDel, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonClear, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jTextSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonUpdate)
                    .addComponent(jButtonDel)
                    .addComponent(jButtonClear))
                .addGap(18, 18, 18))
        );

        jComboBoxEngine.setEditable(true);
        jComboBoxEngine.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "V-3", "v-4", "v-6", "v-8", "v-12", "v-16" }));
        jComboBoxEngine.setSelectedIndex(-1);

        jCombotrans.setEditable(true);
        jCombotrans.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Fuel", "Diesel", "Gas" }));
        jCombotrans.setSelectedIndex(-1);

        jLabel16.setText("Vehicle ID:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel3)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jTextChasis, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel4)
                                            .addGap(18, 18, 18)
                                            .addComponent(jTextInsurance, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jTextRegId, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jRegDate, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel14)
                                                .addGap(18, 18, 18)
                                                .addComponent(jTextAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel13)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jDatePurchase, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jTextColor))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel5)
                                                    .addComponent(jLabel6))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jTextMake, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jTextModel, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGap(25, 25, 25)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel11)
                                            .addComponent(jLabel12))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jComboBoxEngine, 0, 123, Short.MAX_VALUE)
                                            .addComponent(jTextSeat, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jCombotrans, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                                        .addComponent(jimgattach)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextImage, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(18, 18, 18)
                                .addComponent(jtextVid, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(jLabel10)
                    .addComponent(jTextRegId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextMake, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextSeat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jimgattach)
                    .addComponent(jTextImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6)
                            .addComponent(jLabel11)
                            .addComponent(jTextChasis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxEngine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7)
                            .addComponent(jLabel12)
                            .addComponent(jTextInsurance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCombotrans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jDatePurchase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel8))
                                    .addComponent(jRegDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(jTextAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jtextVid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)))
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Vehicle Details", jPanel2);

        jLabel25.setText("Serial No:");

        jLabel26.setText("Tyre Make:");

        jLabel27.setText("Tyre Size:");

        jLabel28.setText("Purchase Date:");

        jLabel29.setText("Expiry Date:");

        jLabel30.setText("Amount Purchased");

        jComboBoxSize.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxSize.setSelectedIndex(-1);

        jButton1Save.setText("Save");
        jButton1Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1SaveActionPerformed(evt);
            }
        });

        jButton1Update.setText("Update");
        jButton1Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1UpdateActionPerformed(evt);
            }
        });

        jButton1Del.setText("Delete");
        jButton1Del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1DelActionPerformed(evt);
            }
        });

        jButtonClearF.setText("Clear Fields");
        jButtonClearF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearFActionPerformed(evt);
            }
        });

        jTableTyre.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableTyre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableTyreMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTableTyre);

        jTextSearchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextSearchFieldKeyReleased(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Sylfaen", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 0, 204));
        jLabel32.setText("Search:");

        jLabel38.setText("TyreId:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane5)
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLabel32)
                .addGap(18, 18, 18)
                .addComponent(jTextSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel38, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jTextSerial, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jTextmake, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jComboBoxSize, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel28)
                                    .addComponent(jLabel29)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel30)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(jButton1Save, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jButton1Update, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jDateChooserExpiry, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(jDateChooserdate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jTextAMt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton1Del, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonClearF)
                        .addContainerGap(197, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jTextFieldTyre, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldTyre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38))
                .addGap(34, 34, 34)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextSerial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(jLabel28)
                            .addComponent(jLabel31)
                            .addComponent(jTextmake, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextAMt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30))
                        .addGap(18, 18, 18)
                        .addComponent(jDateChooserdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel27)
                        .addComponent(jLabel29)
                        .addComponent(jComboBoxSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateChooserExpiry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1Save)
                    .addComponent(jButton1Update)
                    .addComponent(jButton1Del)
                    .addComponent(jButtonClearF))
                .addGap(47, 47, 47)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Tyre Management", jPanel5);

        jLabel33.setText("Vehicle ID:");

        jLabel34.setText("Tyre Id:");

        jLabel35.setText("Tyre Location:");

        jComboBox4Locate.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "FrontRightExterior", "FrontLeftExterior", "RearRightExterior", "RearLeftInterior" }));
        jComboBox4Locate.setSelectedIndex(-1);

        jButton15Save.setText("Save");
        jButton15Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15SaveActionPerformed(evt);
            }
        });

        jButton16Update.setText("Update");
        jButton16Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16UpdateActionPerformed(evt);
            }
        });

        jButton17Del.setText("Delete");
        jButton17Del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17DelActionPerformed(evt);
            }
        });

        jButton18clear.setText("Clear Fields");
        jButton18clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18clearActionPerformed(evt);
            }
        });

        jTextField23Search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField23SearchKeyReleased(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Sylfaen", 1, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 0, 204));
        jLabel36.setText("Search:");

        jTable5VTyre.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable5VTyre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable5VTyreMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTable5VTyre);

        jComboBoxTyre.setEditable(true);
        jComboBoxTyre.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));

        jComboBoxVid.setEditable(true);
        jComboBoxVid.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBoxVidMouseClicked(evt);
            }
        });
        jComboBoxVid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBoxVidKeyReleased(evt);
            }
        });

        jButton1.setText("c");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("c");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel35)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox4Locate, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel33)
                                    .addComponent(jLabel34))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBoxTyre, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBoxVid, 0, 135, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1)
                                    .addComponent(jButton2)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(jLabel36)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField23Search))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                        .addComponent(jButton15Save, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton16Update, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton17Del, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(jButton18clear))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 953, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(jComboBoxVid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(22, 22, 22)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(jComboBoxTyre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(23, 23, 23)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(jComboBox4Locate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton15Save)
                    .addComponent(jButton16Update)
                    .addComponent(jButton17Del)
                    .addComponent(jButton18clear))
                .addGap(34, 34, 34)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField23Search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Vehicle Tire Relation", jPanel6);

        jLabel17.setText("Insurance ID:");

        jLabel18.setText("Insurance company:");

        jLabel19.setText("Insurance type:");

        jLabel20.setText("Insurance amount paid:");

        jLabel21.setText("Renewal Interval:");

        jLabel22.setText("Issue Date:");

        jLabel23.setText("Expiry Date:");

        jType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jType.setSelectedIndex(-1);

        jrenew.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jrenew.setSelectedIndex(-1);

        jBSave.setText("Save");
        jBSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSaveActionPerformed(evt);
            }
        });

        jbuttonUpdate.setText("Update");
        jbuttonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonUpdateActionPerformed(evt);
            }
        });

        jDel.setText("Delete");
        jDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDelActionPerformed(evt);
            }
        });

        jclear.setText("Clear Fields");
        jclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jclearActionPerformed(evt);
            }
        });

        jTableVinsure.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableVinsure.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableVinsureMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTableVinsure);

        jSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jSearchKeyReleased(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 0, 204));
        jLabel24.setText("Search:");

        jLabel37.setText("ID:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(230, 230, 230)
                        .addComponent(jBSave, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbuttonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jDel, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jclear)
                        .addGap(57, 57, 57)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel18)
                                        .addGap(18, 18, 18)
                                        .addComponent(jCompany, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel20)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jPay, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel19)
                                        .addGap(18, 18, 18)
                                        .addComponent(jType, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(40, 40, 40)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel21)
                                        .addGap(18, 18, 18)
                                        .addComponent(jrenew, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel23)
                                        .addGap(18, 18, 18)
                                        .addComponent(jExpiry, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel22)
                                        .addGap(22, 22, 22)
                                        .addComponent(jissue, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jinsure, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel37)
                        .addGap(18, 18, 18)
                        .addComponent(jTextvIns, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(96, 96, 96))))
            .addComponent(jScrollPane4)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel21)
                                    .addComponent(jinsure, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jrenew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(33, 33, 33)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel22)
                                    .addComponent(jCompany, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jissue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextvIns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel37))))
                .addGap(35, 35, 35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel19)
                        .addComponent(jLabel23)
                        .addComponent(jType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jExpiry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel20)
                    .addComponent(jPay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBSave)
                    .addComponent(jbuttonUpdate)
                    .addComponent(jDel)
                    .addComponent(jclear)
                    .addComponent(jSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Vehicle Insurance", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane4)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane4)
                .addContainerGap())
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDelActionPerformed
        // TODO add your handling code here:
        int p = JOptionPane.showConfirmDialog(null, "Do you really want to delete", "Delete",JOptionPane.YES_NO_OPTION);
        if(p==0)
        {
        String sql ="delete from Vinsurance where VinsuranceId=?";
        try
        {
            pst = conn.prepareStatement(sql);
            pst.setString(1, jTextRegId.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Deleted");
        }
        catch(SQLException | HeadlessException e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
         update_table();
        jTextvIns.setText(null);
        jinsure.setText(null);
        jCompany.setText(null);
        jType.setSelectedItem(null);
        jPay.setText(null);
        jrenew.setSelectedItem(null);
        ((JTextField)jissue.getDateEditor().getUiComponent()).setText(null);
        ((JTextField)jExpiry.getDateEditor().getUiComponent()).setText(null);
        }
    }//GEN-LAST:event_jDelActionPerformed

    private void jButton1DelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1DelActionPerformed
        // TODO add your handling code here:
        int p = JOptionPane.showConfirmDialog(null, "Do you really want to delete", "Delete",JOptionPane.YES_NO_OPTION);
        if(p==0)
        {
        String sql ="delete from tyre where tyreId=?";
        try
        {
            pst = conn.prepareStatement(sql);
            pst.setString(1, jTextFieldTyre.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Deleted");
        }
        catch(SQLException | HeadlessException e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
         update_tableTyre();
        jTextFieldTyre.setText(null);
        jTextSerial.setText(null);
        jTextmake.setText(null);
        jTextAMt.setText(null);
        jComboBoxSize.setSelectedItem(null);
        ((JTextField)jDateChooserdate.getDateEditor().getUiComponent()).setText(null);
        ((JTextField)jDateChooserExpiry.getDateEditor().getUiComponent()).setText(null);
        }
        updateComboTyre();
    }//GEN-LAST:event_jButton1DelActionPerformed

    private void jButton17DelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17DelActionPerformed
        // TODO add your handling code here:
        int vid = Integer.parseInt(jComboBoxVid.getSelectedItem().toString());
         int p = JOptionPane.showConfirmDialog(null, "Do you really want to delete", "Delete",JOptionPane.YES_NO_OPTION);
        if(p==0)
        {
        String sql ="delete from Vtyre where vId=?";
        try
        {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, vid);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Deleted");
        }
        catch(SQLException | HeadlessException e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
         update_tableVTyre();
        jComboBoxVid.setSelectedItem(null);
        jComboBoxTyre.setSelectedItem(null);
        jComboBox4Locate.setSelectedItem(null);
        }
    }//GEN-LAST:event_jButton17DelActionPerformed

    private void jButtonHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHomeActionPerformed
        // TODO add your handling code here:
        Home h = new Home();
        h.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButtonHomeActionPerformed

    private void jimgattachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jimgattachActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        filename = f.getAbsolutePath();
        jTextImage.setText(filename);
        
        try
        {
           File image = new File(filename); 
           BufferedImage imagery = ImageIO.read(image);
           BufferedImage resizedImage = resize(imagery,214,142);//resize the image
           FileInputStream fis = new FileInputStream(image);
           ByteArrayOutputStream bos = new ByteArrayOutputStream();
           byte[] buf = new byte[1024];
           for(int readNum; (readNum = fis.read(buf))!= -1;)
           {
               bos.write(buf,0,readNum);
           }
           person_image = bos.toByteArray();
           format = new ImageIcon(resizedImage);
           jimg.setIcon(format);
          
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jimgattachActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        // TODO add your handling code here:
        String eng = jComboBoxEngine.getSelectedItem().toString();
        String trans = jCombotrans.getSelectedItem().toString();
        
        try
        {
           String sql = "Insert into vehicleDetails (vId, Vreg, chasisId, insuranceId, regDate, make, model, color, note, seat, engine, transmission, purchasedate, amount, img) "
                   + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
           pst = conn.prepareStatement(sql);
           pst.setString(1, jtextVid.getText());
           pst.setString(2, jTextRegId.getText());
           pst.setString(3, jTextChasis.getText());
           pst.setString(4, jTextInsurance.getText());
           pst.setString(5, ((JTextField)jRegDate.getDateEditor().getUiComponent()).getText());
           pst.setString(6, jTextMake.getText());
           pst.setString(7, jTextModel.getText());
           pst.setString(8, jTextColor.getText());
           pst.setString(9, jTextAreaNote.getText());
           pst.setInt(10, Integer.parseInt(jTextSeat.getText()));
           pst.setString(11, eng);
           pst.setString(12, trans);
           pst.setString(13, ((JTextField)jDatePurchase.getDateEditor().getUiComponent()).getText());
           pst.setDouble(14, Double.parseDouble(jTextAmount.getText()));
           pst.setBytes(15, person_image);
           
           pst.execute();
           
           JOptionPane.showMessageDialog(null, "Saved");
        }
        catch(SQLException | NumberFormatException | HeadlessException e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
       finally
        {
             try
                {
                    update_table();
                    jtextVid.setText(null);
                    jTextRegId.setText(null);
                    jTextChasis.setText(null);
                    jTextInsurance.setText(null);
                    ((JTextField)jRegDate.getDateEditor().getUiComponent()).setText(null);
                    jTextMake.setText(null);
                    jTextModel.setText(null);
                    jTextColor.setText(null);
                    jTextAreaNote.setText(null);
                    jTextSeat.setText(null);
                    jComboBoxEngine.setSelectedItem(null);
                    jCombotrans.setSelectedItem(null);
                    ((JTextField)jDatePurchase.getDateEditor().getUiComponent()).setText(null);
                    jTextAmount.setText(null);
                    jimg.setIcon(null);
                }
                catch(Exception e)
                {
                
                }
        }
            
        updateCombo();
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jTextChasisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextChasisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextChasisActionPerformed

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        try {
            // TODO add your handling code here:
            String id = jtextVid.getText();
            String reg = jTextRegId.getText();
            String chas = jTextChasis.getText();
            String ins = jTextInsurance.getText();
            String regDate = ((JTextField)jRegDate.getDateEditor().getUiComponent()).getText();
            String make = jTextMake.getText();
            String model = jTextModel.getText();
            String color = jTextColor.getText();
            String note = jTextAreaNote.getText();
            int seat = Integer.parseInt(jTextSeat.getText());
            String eng = jComboBoxEngine.getSelectedItem().toString();
            String trans = jCombotrans.getSelectedItem().toString();
            String purDate = ((JTextField)jDatePurchase.getDateEditor().getUiComponent()).getText();
            double amt = Double.parseDouble(jTextAmount.getText());
            
            String sql = "update vehicleDetails set vId='"+id+"' , Vreg='"+reg+"' , chasisId='"+chas+"' , insuranceId='"+ins+"' , regDate='"+regDate+"' , make='"+make+"', "
                    + "model='"+model+"' , color='"+color+"' , note='"+note+"' , seat='"+seat+"' , engine='"+eng+"' , transmission='"+trans+"' , purchasedate='"+purDate+"' , amount='"+amt+"'   where vId='"+id+"' ";
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Updated");
        } 
        catch (SQLException | HeadlessException ex) 
        {
            Logger.getLogger(Vehicle.class.getName()).log(Level.SEVERE, null, ex);
        }
         update_table();
        jtextVid.setText(null);
        jTextRegId.setText(null);
        jTextChasis.setText(null);
        jTextInsurance.setText(null);
        ((JTextField)jRegDate.getDateEditor().getUiComponent()).setText(null);
        jTextMake.setText(null);
        jTextModel.setText(null);
        jTextColor.setText(null);
        jTextAreaNote.setText(null);
        jTextSeat.setText(null);
        jComboBoxEngine.setSelectedItem(null);
        jCombotrans.setSelectedItem(null);
        ((JTextField)jDatePurchase.getDateEditor().getUiComponent()).setText(null);
        jTextAmount.setText(null);
        jimg.setIcon(null);
        
        updateCombo();
    }//GEN-LAST:event_jButtonUpdateActionPerformed

    private void jButtonDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelActionPerformed
        // TODO add your handling code here:
        int p = JOptionPane.showConfirmDialog(null, "Do you really want to delete", "Delete",JOptionPane.YES_NO_OPTION);
        if(p==0)
        {
        String sql ="delete from vehicleDetails where vId=?";
        try
        {
            pst = conn.prepareStatement(sql);
            pst.setString(1, jTextRegId.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Deleted");
        }
        catch(SQLException | HeadlessException e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
         update_table();
         jtextVid.setText(null);
        jTextRegId.setText(null);
        jTextChasis.setText(null);
        jTextInsurance.setText(null);
        ((JTextField)jRegDate.getDateEditor().getUiComponent()).setText(null);
        jTextMake.setText(null);
        jTextModel.setText(null);
        jTextColor.setText(null);
        jTextAreaNote.setText(null);
        jTextSeat.setText(null);
        jComboBoxEngine.setSelectedItem(null);
        jCombotrans.setSelectedItem(null);
        ((JTextField)jDatePurchase.getDateEditor().getUiComponent()).setText(null);
        jTextAmount.setText(null);
        jimg.setIcon(null);
        }
        updateCombo();
    }//GEN-LAST:event_jButtonDelActionPerformed

    private void jButtonClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearActionPerformed
        // TODO add your handling code here:
        jtextVid.setText(null);
        jTextRegId.setText(null);
        jTextChasis.setText(null);
        jTextInsurance.setText(null);
        ((JTextField)jRegDate.getDateEditor().getUiComponent()).setText(null);
        jTextMake.setText(null);
        jTextModel.setText(null);
        jTextColor.setText(null);
        jTextAreaNote.setText(null);
        jTextSeat.setText(null);
        jComboBoxEngine.setSelectedItem(null);
        jCombotrans.setSelectedItem(null);
        ((JTextField)jDatePurchase.getDateEditor().getUiComponent()).setText(null);
        jTextAmount.setText(null);
        jimg.setIcon(null);
    }//GEN-LAST:event_jButtonClearActionPerformed

    private void jTextSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextSearchKeyReleased
        // TODO add your handling code here:
        jTableVDetails.setAutoCreateRowSorter(true);
        try
        {
            String me = jTextSearch.getText();
            String sql = "Select * from vehicleDetails where Vreg like ? or make like ? or model like ? or amount like ? or seat like ? or vId like ? ";
            pst = conn.prepareStatement(sql);
            pst.setString(1, me + "%");
            pst.setString(2, me + "%");
            pst.setString(3, me + "%");
            pst.setString(4, me + "%");
            pst.setString(5, me + "%");
            pst.setString(6, me + "%");
            
            rs = pst.executeQuery();
            jTableVDetails.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        finally
        {
            try
            {
                rs.close();
                pst.close();
            }
            catch(Exception e)
            {
                
            }
        }
    }//GEN-LAST:event_jTextSearchKeyReleased

    private void jimgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jimgMouseClicked
        // TODO add your handling code here:
        String id = jTextRegId.getText();
        String name = jTextMake.getText();
        String mod = jTextModel.getText();
        int p = JOptionPane.showConfirmDialog(null, "Do you really want to change picture", "Update Profile Picture",JOptionPane.YES_NO_OPTION);
        if(p==0)
        {
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(null);
            File f = chooser.getSelectedFile();
            filename = f.getAbsolutePath();
            jTextImage.setText(filename);
            
            try
        {
           File image = new File(filename); 
           BufferedImage imagery = ImageIO.read(image);
           BufferedImage resizedImage = resize(imagery,214,142);//resize the image
           FileInputStream fis = new FileInputStream(image);
           ByteArrayOutputStream bos = new ByteArrayOutputStream();
           byte[] buf = new byte[1024];
           for(int readNum; (readNum = fis.read(buf))!= -1;)
           {
               bos.write(buf,0,readNum);
           }
           person_image = bos.toByteArray();
           format = new ImageIcon(resizedImage);
           jimg.setIcon(format);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
            
        
        String sql ="update vehicleDetails set img=? where vId='"+id+"'";
        try
        {
            pst = conn.prepareStatement(sql);
            pst.setBytes(1, person_image);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Profile picture of "+name+ " " +mod+" has been changed");
        }
        catch(SQLException | HeadlessException e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        update_table();
        }
    }//GEN-LAST:event_jimgMouseClicked

    private void jTableVDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableVDetailsMouseClicked
        // TODO add your handling code here:
        int row = jTableVDetails.getSelectedRow();
        String tClick = (jTableVDetails.getModel().getValueAt(row, 0).toString());
        try
        {
            String sql ="select * from vehicleDetails where vId="+tClick+" ";
            pst = conn.prepareStatement(sql);
             rs = pst.executeQuery();
       
        jTextImage.setText(null);
       
             if(rs.next())
             {
                 int add14 = rs.getInt("vId");
                 jtextVid.setText(String.valueOf(add14));
                 String add1 = rs.getString("Vreg");
                 jTextRegId.setText(add1);
                 String add2 = rs.getString("chasisId");
                 jTextChasis.setText(add2);
                 String add3 = rs.getString("insuranceId");
                 jTextInsurance.setText(add3);
                 String add4 = rs.getString("regDate");
                 ((JTextField)jRegDate.getDateEditor().getUiComponent()).setText(add4);
                 String add5 = rs.getString("make");
                 jTextMake.setText(add5);
                 String add6 = rs.getString("model");
                 jTextModel.setText(add6);
                 String add7 = rs.getString("color");
                 jTextColor.setText(add7);
                 String add8 = rs.getString("note");
                 jTextAreaNote.setText(add8);
                 int add9 = rs.getInt("seat");
                 jTextSeat.setText(String.valueOf(add9));
                 String add10 = rs.getString("engine");
                 jComboBoxEngine.setSelectedItem(add10);
                 String add11 = rs.getString("transmission");
                 jCombotrans.setSelectedItem(add11);
                 String add12 = rs.getString("purchasedate");
                 ((JTextField)jDatePurchase.getDateEditor().getUiComponent()).setText(add12);
                 double add13 = rs.getDouble("amount");
                 jTextAmount.setText(String.valueOf(add13));
                 ReadImage(add1,jimg);
             }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jTableVDetailsMouseClicked

    private void jBSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSaveActionPerformed
        // TODO add your handling code here:
        String type = jType.getSelectedItem().toString();
        String renew = jrenew.getSelectedItem().toString();
        
        try
        {
           String sql = "Insert into Vinsurance (VinsuranceId, insuranceId, company, type, amt, renew, issue, expire) "
                   + "values (?,?,?,?,?,?,?,?)";
           pst = conn.prepareStatement(sql);
           pst.setString(1, jTextvIns.getText());
           pst.setString(2, jinsure.getText());
           pst.setString(3, jCompany.getText());
           pst.setString(4, type);
           pst.setString(5, jPay.getText());
           pst.setString(6, renew);
           pst.setString(7, ((JTextField)jissue.getDateEditor().getUiComponent()).getText());
           pst.setString(8, ((JTextField)jExpiry.getDateEditor().getUiComponent()).getText());
          
           pst.execute();
           
           JOptionPane.showMessageDialog(null, "Saved");
        }
        catch(SQLException | NumberFormatException | HeadlessException e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
       finally
        {
             try
                {
                    update_tableInsurance();
                    jTextvIns.setText(null);
                    jinsure.setText(null);
                    jCompany.setText(null);
                    jType.setSelectedItem(null);
                    jPay.setText(null);
                    jrenew.setSelectedItem(null);
                    ((JTextField)jissue.getDateEditor().getUiComponent()).setText(null);
                    ((JTextField)jExpiry.getDateEditor().getUiComponent()).setText(null);
                    
                }
                catch(Exception e)
                {
                
                }
        }
    }//GEN-LAST:event_jBSaveActionPerformed

    private void jTableVinsureMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableVinsureMouseClicked
        // TODO add your handling code here:
        int row = jTableVinsure.getSelectedRow();
        String tClick = (jTableVinsure.getModel().getValueAt(row, 0).toString());
        try
        {
            String sql ="select * from Vinsurance where VinsuranceId="+tClick+" ";
            pst = conn.prepareStatement(sql);
             rs = pst.executeQuery();
      
             if(rs.next())
             {
                 int add8 = rs.getInt("VinsuranceId");
                 jTextvIns.setText(String.valueOf(add8));
                 String add1 = rs.getString("insuranceId");
                 jinsure.setText(add1);
                 String add2 = rs.getString("company");
                 jCompany.setText(add2);
                 String add3 = rs.getString("type");
                 jType.setSelectedItem(add3);
                 String add4 = rs.getString("amt");
                 jPay.setText(add4);
                 String add5 = rs.getString("renew");
                 jrenew.setSelectedItem(add5);
                 String add6 = rs.getString("issue");
                 ((JTextField)jissue.getDateEditor().getUiComponent()).setText(add6);
                 String add7 = rs.getString("expire");
                 ((JTextField)jExpiry.getDateEditor().getUiComponent()).setText(add7);
             }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jTableVinsureMouseClicked

    private void jbuttonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonUpdateActionPerformed
        // TODO add your handling code here:
        try
        {
        String id = jTextvIns.getText();
        String vid = jinsure.getText();
        String comp = jCompany.getText();
        String type = jType.getSelectedItem().toString();
        double amt = Double.parseDouble(jPay.getText());
        String renew = jrenew.getSelectedItem().toString();
        String issue = ((JTextField)jissue.getDateEditor().getUiComponent()).getText();
        String expire = ((JTextField)jExpiry.getDateEditor().getUiComponent()).getText();
        
        String sql = "update Vinsurance set VinsuranceId='"+id+"' , insuranceId='"+vid+"' , company='"+comp+"' , type='"+type+"' , amt='"+amt+"' , renew='"+renew+"', "
                    + "issue='"+issue+"' , expire='"+expire+"'  where VinsuranceId='"+id+"' ";
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Updated");
        }
        catch(NumberFormatException | SQLException | HeadlessException e)
        {
             JOptionPane.showMessageDialog(null, e);
        }
        update_tableInsurance();
    }//GEN-LAST:event_jbuttonUpdateActionPerformed

    private void jclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jclearActionPerformed
        // TODO add your handling code here:
        jTextvIns.setText(null);
        jinsure.setText(null);
        jCompany.setText(null);
        jType.setSelectedItem(null);
        jPay.setText(null);
        jrenew.setSelectedItem(null);
        ((JTextField)jissue.getDateEditor().getUiComponent()).setText(null);
        ((JTextField)jExpiry.getDateEditor().getUiComponent()).setText(null);
    }//GEN-LAST:event_jclearActionPerformed

    private void jSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jSearchKeyReleased
        // TODO add your handling code here:
        jTableVinsure.setAutoCreateRowSorter(true);
        try
        {
            String me = jSearch.getText();
            String sql = "Select * from Vinsurance where VinsuranceId like ? or insuranceId like ? or company like ? or type like ? ";
            pst = conn.prepareStatement(sql);
            pst.setString(1, me + "%");
            pst.setString(2, me + "%");
            pst.setString(3, me + "%");
            pst.setString(4, me + "%");
            
            rs = pst.executeQuery();
            jTableVinsure.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        finally
        {
            try
            {
                rs.close();
                pst.close();
            }
            catch(Exception e)
            {
                
            }
        }
    }//GEN-LAST:event_jSearchKeyReleased

    private void jButton1SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1SaveActionPerformed
        // TODO add your handling code here:
        String Tsize = jComboBoxSize.getSelectedItem().toString();
        
        try
        {
           String sql = "Insert into tyre (tyreId, serial, make, size, amt, purchaseD, expiry) "
                   + "values (?,?,?,?,?,?,?)";
           pst = conn.prepareStatement(sql);
           pst.setString(1, jTextFieldTyre.getText());
           pst.setString(2, jTextSerial.getText());
           pst.setString(3, jTextmake.getText());
           pst.setString(4, Tsize);
           pst.setString(5, jTextAMt.getText());
           pst.setString(6, ((JTextField)jDateChooserdate.getDateEditor().getUiComponent()).getText());
           pst.setString(7, ((JTextField)jDateChooserExpiry.getDateEditor().getUiComponent()).getText());
          
           pst.execute();
           
           JOptionPane.showMessageDialog(null, "Saved");
        }
        catch(SQLException | NumberFormatException | HeadlessException e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
       finally
        {
             try
                {
                    update_tableTyre();
                    jTextFieldTyre.setText(null);
                    jTextSerial.setText(null);
                    jTextmake.setText(null);
                    jTextAMt.setText(null);
                    jComboBoxSize.setSelectedItem(null);
                    ((JTextField)jDateChooserdate.getDateEditor().getUiComponent()).setText(null);
                    ((JTextField)jDateChooserExpiry.getDateEditor().getUiComponent()).setText(null);
                }
                catch(Exception e)
                {
                
                }
             updateComboTyre();
        }
    }//GEN-LAST:event_jButton1SaveActionPerformed

    private void jButton1UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1UpdateActionPerformed
        // TODO add your handling code here:
        try
        {
            String tId = jTextFieldTyre.getText();
            String sery = jTextSerial.getText();
            String tMake = jTextmake.getText();
            String size = jComboBoxSize.getSelectedItem().toString();
            double amt = Double.parseDouble(jTextAMt.getText());
            String pDate = ((JTextField)jDateChooserdate.getDateEditor().getUiComponent()).getText();
            String eDate = ((JTextField)jDateChooserExpiry.getDateEditor().getUiComponent()).getText();
        
        String sql = "update tyre set tyreId='"+tId+"' , serial='"+sery+"' , make='"+tMake+"' , size='"+size+"' , amt='"+amt+"' , purchaseD='"+pDate+"', "
                    + "expiry='"+eDate+"'  where tyreId='"+tId+"' ";
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Updated");
        }
        catch(NumberFormatException | SQLException | HeadlessException e)
        {
            
        }
        update_tableTyre();
        updateComboTyre();
    }//GEN-LAST:event_jButton1UpdateActionPerformed

    private void jButtonClearFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearFActionPerformed
        // TODO add your handling code here:
        jTextFieldTyre.setText(null);
        jTextSerial.setText(null);
        jTextmake.setText(null);
        jTextAMt.setText(null);
        jComboBoxSize.setSelectedItem(null);
        ((JTextField)jDateChooserdate.getDateEditor().getUiComponent()).setText(null);
        ((JTextField)jDateChooserExpiry.getDateEditor().getUiComponent()).setText(null);
    }//GEN-LAST:event_jButtonClearFActionPerformed

    private void jTableTyreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableTyreMouseClicked
        // TODO add your handling code here:
        int row = jTableTyre.getSelectedRow();
        String tClick = (jTableTyre.getModel().getValueAt(row, 0).toString());
        try
        {
            String sql ="select * from tyre where tyreId="+tClick+" ";
            pst = conn.prepareStatement(sql);
             rs = pst.executeQuery();
      
             if(rs.next())
             {
                 String add1 = rs.getString("tyreId");
                 jTextFieldTyre.setText(add1);
                 String add2 = rs.getString("serial");
                 jTextSerial.setText(add2);
                 String add3 = rs.getString("make");
                 jTextmake.setText(add3);
                 String add4 = rs.getString("size");
                 jComboBoxSize.setSelectedItem(add4);
                 double add5 = rs.getDouble("amt");
                 jTextAMt.setText(String.valueOf(add5));
                 String add6 = rs.getString("purchaseD");
                 ((JTextField)jDateChooserdate.getDateEditor().getUiComponent()).setText(add6);
                 String add7 = rs.getString("expiry");
                 ((JTextField)jDateChooserExpiry.getDateEditor().getUiComponent()).setText(add7);
             }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jTableTyreMouseClicked

    private void jTextSearchFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextSearchFieldKeyReleased
        // TODO add your handling code here:
        jTableTyre.setAutoCreateRowSorter(true);
        try
        {
            String me = jTextSearch.getText();
            String sql = "Select * from tyre where tyreId like ? or serial like ? or make like ? or size like ? or amt like ? ";
            pst = conn.prepareStatement(sql);
            pst.setString(1, me + "%");
            pst.setString(2, me + "%");
            pst.setString(3, me + "%");
            pst.setString(4, me + "%");
            pst.setString(5, me + "%");
            
            rs = pst.executeQuery();
            jTableTyre.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        finally
        {
            try
            {
                rs.close();
                pst.close();
            }
            catch(Exception e)
            {
                
            }
        }
    }//GEN-LAST:event_jTextSearchFieldKeyReleased

    private void jComboBoxVidMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxVidMouseClicked
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jComboBoxVidMouseClicked

    private void jComboBoxVidKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBoxVidKeyReleased
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jComboBoxVidKeyReleased

    private void jButton15SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15SaveActionPerformed
        // TODO add your handling code here:
        String vId = jComboBoxVid.getSelectedItem().toString();
        String tId = jComboBoxTyre.getSelectedItem().toString();
        String loc = jComboBox4Locate.getSelectedItem().toString();
        try
        {
           String sql = "Insert into Vtyre (vId, tyreId, tyreLocate) "
                   + "values (?,?,?)";
           pst = conn.prepareStatement(sql);
           pst.setString(1, vId);
           pst.setString(2, tId);
           pst.setString(3, loc);
          
           pst.execute();
           
           JOptionPane.showMessageDialog(null, "Saved");
        }
        catch(SQLException | NumberFormatException | HeadlessException e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
       finally
        {
             try
                {
                    update_tableVTyre();
                    jComboBoxVid.setSelectedItem(null);
                    jComboBoxTyre.setSelectedItem(null);
                    jComboBox4Locate.setSelectedItem(null);
                    
                }
                catch(Exception e)
                {
                
                }
        }
    }//GEN-LAST:event_jButton15SaveActionPerformed

    private void jButton16UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16UpdateActionPerformed
        // TODO add your handling code here:
        try
        {
            
        int vId = Integer.parseInt(jType.getSelectedItem().toString());
        int tId = Integer.parseInt(jrenew.getSelectedItem().toString());
        String loc = jComboBox4Locate.getSelectedItem().toString();
        
        String sql = "update Vtyre set vId='"+vId+"' , tyreId='"+tId+"' , tyreLocate='"+loc+"' ";
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Updated");
        }
        catch(NumberFormatException | SQLException | HeadlessException e)
        {
             JOptionPane.showMessageDialog(null, e);
        }
        update_tableVTyre();
    }//GEN-LAST:event_jButton16UpdateActionPerformed

    private void jButton18clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18clearActionPerformed
        // TODO add your handling code here:
        jComboBoxVid.setSelectedItem(null);
        jComboBoxTyre.setSelectedItem(null);
        jComboBox4Locate.setSelectedItem(null);
    }//GEN-LAST:event_jButton18clearActionPerformed

    private void jTextField23SearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField23SearchKeyReleased
        // TODO add your handling code here:
         jTable5VTyre.setAutoCreateRowSorter(true);
        try
        {
            String me = jTextField23Search.getText();
            String sql = "Select * from Vtyre where vId like ? or tyreId like ? or tyreLocate like ? ";
            pst = conn.prepareStatement(sql);
            pst.setString(1, me + "%");
            pst.setString(2, me + "%");
            pst.setString(3, me + "%");
            
            rs = pst.executeQuery();
            jTable5VTyre.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        finally
        {
            try
            {
                rs.close();
                pst.close();
            }
            catch(Exception e)
            {
                
            }
        }
    }//GEN-LAST:event_jTextField23SearchKeyReleased

    private void jTable5VTyreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5VTyreMouseClicked
        // TODO add your handling code here:
        int row = jTable5VTyre.getSelectedRow();
        String tClick = (jTable5VTyre.getModel().getValueAt(row, 0).toString());
        try
        {
            String sql ="select * from Vtyre where vId="+tClick+" ";
            pst = conn.prepareStatement(sql);
             rs = pst.executeQuery();
      
             if(rs.next())
             {
                 int add1 = rs.getInt("vId");
                 jComboBoxVid.setSelectedItem(String.valueOf(add1));
                 int add2 = rs.getInt("tyreId");
                 jComboBoxTyre.setSelectedItem(String.valueOf(add2));
                 String add3 = rs.getString("tyreLocate");
                 jComboBox4Locate.setSelectedItem(add3);
             }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jTable5VTyreMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try
        {
            int id = Integer.parseInt(jComboBoxVid.getSelectedItem().toString());
            String sql = "select * from vehicleDetails where vId= ? ";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            while(rs.next())
            {
                String car = rs.getString("make");
                String model = rs.getString("model");
                String color = rs.getString("color");
                String reg = rs.getString("Vreg");
                //jComboBoxVid.setToolTipText(color +" "+ car +" "+ model + " with registration Id: " + reg);
                //jComboBoxVID.setToolTipText(color +" "+ car +" "+ model + " with registration Id: " + reg);
                JOptionPane.showMessageDialog(null, color +" "+ car +" "+ model + " with registration Id: " + reg);

            }

        }
        catch (NumberFormatException | SQLException ex)
        {
            Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try
        {
            int id = Integer.parseInt(jComboBoxTyre.getSelectedItem().toString());
            String sql = "select * from tyre where tyreId= ? ";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            while(rs.next())
            {
                String car = rs.getString("make");
                String model = rs.getString("serial");
                String color = rs.getString("size");
                JOptionPane.showMessageDialog(null, car +" tyre of size "+ color +" "+ "with serial number: " + model);

            }

        }
        catch (NumberFormatException | SQLException ex)
        {
            Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(Vehicle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vehicle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vehicle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vehicle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vehicle().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBSave;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton15Save;
    private javax.swing.JButton jButton16Update;
    private javax.swing.JButton jButton17Del;
    private javax.swing.JButton jButton18clear;
    private javax.swing.JButton jButton1Del;
    private javax.swing.JButton jButton1Save;
    private javax.swing.JButton jButton1Update;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonClear;
    private javax.swing.JButton jButtonClearF;
    private javax.swing.JButton jButtonDel;
    private javax.swing.JButton jButtonHome;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JComboBox jComboBox4Locate;
    private javax.swing.JComboBox jComboBoxEngine;
    private javax.swing.JComboBox jComboBoxSize;
    private javax.swing.JComboBox jComboBoxTyre;
    private javax.swing.JComboBox jComboBoxVid;
    private javax.swing.JComboBox jCombotrans;
    private javax.swing.JTextField jCompany;
    private com.toedter.calendar.JDateChooser jDateChooserExpiry;
    private com.toedter.calendar.JDateChooser jDateChooserdate;
    private com.toedter.calendar.JDateChooser jDatePurchase;
    private javax.swing.JButton jDel;
    private javax.swing.JDesktopPane jDesktopPane1;
    private com.toedter.calendar.JDateChooser jExpiry;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JTextField jPay;
    private com.toedter.calendar.JDateChooser jRegDate;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextField jSearch;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable5VTyre;
    private javax.swing.JTable jTableTyre;
    private javax.swing.JTable jTableVDetails;
    private javax.swing.JTable jTableVinsure;
    private javax.swing.JTextField jTextAMt;
    private javax.swing.JTextField jTextAmount;
    private javax.swing.JTextArea jTextAreaNote;
    private javax.swing.JTextField jTextChasis;
    private javax.swing.JTextField jTextColor;
    private javax.swing.JTextField jTextField23Search;
    private javax.swing.JTextField jTextFieldTyre;
    private javax.swing.JTextField jTextImage;
    private javax.swing.JTextField jTextInsurance;
    private javax.swing.JTextField jTextMake;
    private javax.swing.JTextField jTextModel;
    private javax.swing.JTextField jTextRegId;
    private javax.swing.JTextField jTextSearch;
    private javax.swing.JTextField jTextSearchField;
    private javax.swing.JTextField jTextSeat;
    private javax.swing.JTextField jTextSerial;
    private javax.swing.JTextField jTextmake;
    private javax.swing.JTextField jTextvIns;
    private javax.swing.JComboBox jType;
    private javax.swing.JButton jbuttonUpdate;
    private javax.swing.JButton jclear;
    private javax.swing.JLabel jimg;
    private javax.swing.JButton jimgattach;
    private javax.swing.JTextField jinsure;
    private com.toedter.calendar.JDateChooser jissue;
    private javax.swing.JComboBox jrenew;
    private javax.swing.JTextField jtextVid;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
