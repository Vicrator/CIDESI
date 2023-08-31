/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cidesi;

import cidesi.monto;
import codigo.auto;
import codigo.denominaciones;
import codigo.holacon;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class PanelP extends javax.swing.JFrame implements Runnable {

    String horas, minutos, segundos;
    Thread hi;
    Calendar calendario;
    Connection conn = holacon.Coneccion();

    public PanelP() {
        initComponents();
        llenar3();
        todo();
        llenar();
//////////////////////Se inabhilitan  los paneles de usuario
        musuario.setEditable(false);
        mcontraseña.setEditable(false);
        mcontraseña2.setEditable(false);
        error.setVisible(false);
        cambiar.setEnabled(false);
////////////////////////Para la hoa
        hi = new Thread(this);
        hi.start();
        setVisible(true);
//////////Realizamos el formato para la fehca 
        Date fechas = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("YYYY/MM/dd");
        String l = formato.format(fechas);
//Mostramos la fecha en el label 
        fecha1.setText(l);
        fecha2.setText(l);
////////El panel se mostrara en el centro iniciando la aplicacion 
        this.setLocationRelativeTo(null);
        jMenu1.setEnabled(false);
//Aqui inabilitamos los paneles de banco, caja chica y denomiaciones   
        jTable6.setEnabled(false);
        uss.setEnabled(false);
        confi.setEnabledAt(2, false);
        confi.setEnabledAt(1, false);
        confi.setEnabledAt(3, false);
        confi.setEnabledAt(4, false);
        confi.setEnabledAt(5, false);
////////Se coloca el logo de cidesi y se renderiza dependiendo del tamaño del lable 
        ImageIcon iman = new ImageIcon(getClass().getResource("/imagenes/cidesi.jpg"));
        Icon logol = new ImageIcon(iman.getImage().getScaledInstance(imagen.getWidth(), imagen.getHeight(), Image.SCALE_DEFAULT));
        imagen.setIcon(logol);
        this.repaint();
////////Se coloca el logo de cidesi y se renderiza dependiendo del tamaño del lable 
        ImageIcon ima = new ImageIcon(getClass().getResource("/imagenes/cidesi.jpg"));
        Icon logo = new ImageIcon(ima.getImage().getScaledInstance(jLabel7.getWidth(), jLabel7.getHeight(), Image.SCALE_DEFAULT));
        jLabel7.setIcon(logo);
        this.repaint();
////////Se coloca el logo de cidesi y se renderiza dependiendo del tamaño del lable 
        ImageIcon im = new ImageIcon(getClass().getResource("/imagenes/cidesi.jpg"));
        Icon log = new ImageIcon(ima.getImage().getScaledInstance(jLabel8.getWidth(), jLabel8.getHeight(), Image.SCALE_DEFAULT));
        jLabel8.setIcon(log);
        this.repaint();
////////Se coloca el logo de cidesi y se renderiza dependiendo del tamaño del lable 
        ImageIcon img = new ImageIcon(getClass().getResource("/imagenes/cidesi.jpg"));
        Icon lgo = new ImageIcon(ima.getImage().getScaledInstance(jLabel9.getWidth(), jLabel9.getHeight(), Image.SCALE_DEFAULT));
        jLabel9.setIcon(lgo);
        this.repaint();
////////////////////////////////////////////////////////////////////
        Icon lo = new ImageIcon(ima.getImage().getScaledInstance(jLabel17.getWidth(),
                jLabel17.getHeight(), Image.SCALE_DEFAULT));
        jLabel17.setIcon(lo);
        this.repaint();
////////colocamos el panel en el medio 
        this.setLocationRelativeTo(null);
    }

    void borrar() {
        autorizacion.setText("");
        cuenta.setText("");
        monto.setText("");
        recibido.setText("");
        importe.setText("");
        concepto.setText("");

    }
///Cuando se cierra la sesion se vuelve al panel 1 y se inabhilitan los otros

    void cerrar() {
        txtusuario.setText("");
        txtcontraseña.setText("");
        confi.setEnabledAt(1, false);
        confi.setEnabledAt(2, false);
        confi.setEnabledAt(3, false);
        confi.setEnabledAt(4, false);
        confi.setEnabledAt(5, false);
        confi.setSelectedIndex(0);
        confi.setEnabledAt(0, true);
        jMenu1.setEnabled(false);
        uss.setText("");
    }
//////Llamamos a la clas de denominaciones para usar su clase    totalC

    void todo() {
        denominaciones l = new denominaciones();
        l.totalC();
    }
///llenamos la talba de denomonaciones

    void llenar3() {

        try {

            conn = holacon.Coneccion();
            String sql = "select*from denominaciones where id=1";
            DefaultTableModel model = new DefaultTableModel();
            Statement sent = conn.createStatement();
            ResultSet rs = sent.executeQuery(sql);
            String fila[] = new String[6];
            String filam[] = new String[8];
            String colum[] = new String[8];
            colum[0] = "MIL";
            colum[1] = "QUINIENTOS";
            colum[2] = "DOSCIENTOS";
            colum[3] = "CIEN";
            colum[4] = "CINCUENTA";
            colum[5] = "VEINTE";
            String columna[] = new String[8];
            columna[0] = "VEINTE";
            columna[1] = "DIEZ";
            columna[2] = "CINCO";
            columna[3] = "DOS";
            columna[4] = "UNO";
            columna[5] = "CINCUENTA.C";
            columna[6] = "VEINTE.C";
            columna[7] = "DIEZ.C";
            while (rs.next()) {

                fila[0] = rs.getString("a1000");
                fila[1] = rs.getString("a500");
                fila[2] = rs.getString("a200");
                fila[3] = rs.getString("a100");
                fila[4] = rs.getString("a50");
                fila[5] = rs.getString("a20");
                filam[0] = rs.getString("M20");
                filam[1] = rs.getString("a10");
                filam[2] = rs.getString("a5");
                filam[3] = rs.getString("a2");
                filam[4] = rs.getString("a1");
                filam[5] = rs.getString("c50");
                filam[6] = rs.getString("c20");
                filam[7] = rs.getString("c10");
                model.addColumn("BILLETE", colum);
                model.addColumn("CANTIDAD", fila);
                model.addColumn("MONEDAS", columna);
                model.addColumn("CANTIDAD", filam);

            }

            jTable6.setModel(model);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error" + e);
            e.printStackTrace();
        }

    }

/////////////llenamos la tabla de caja,banco y gastos 
    void llenar() {

        try {
            conn =holacon.Coneccion();

            String sql = "select*from cheque where id=1";
            DefaultTableModel model = new DefaultTableModel();
            Statement sent = conn.createStatement();
            ResultSet rs = sent.executeQuery(sql);
            rs.next();
            String dinero[] = {"DINERO"};
            String banco[] = {rs.getString("banco")};
            String caja[] = {rs.getString("totalcaja")};
            String devoluciones[] = {rs.getString("gastos")};

            model.addColumn("CANTIDAD", dinero);
            model.addColumn("BANCO", banco);
            model.addColumn("TOTAL CAJA", caja);
            model.addColumn("GASTOS", devoluciones);

            jTable1.setModel(model);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error" + e);
            e.printStackTrace();
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

        confi = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtusuario = new javax.swing.JTextField();
        txtcontraseña = new javax.swing.JPasswordField();
        imagen = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        RealizarBanco = new javax.swing.JButton();
        DenominacionesBanco = new javax.swing.JButton();
        autorizacion = new javax.swing.JTextField();
        cuenta = new javax.swing.JTextField();
        monto = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        hora = new javax.swing.JLabel();
        fecha1 = new javax.swing.JLabel();
        ComboBoxBanco = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        escritorio = new javax.swing.JDesktopPane();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        recibido = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        concepto = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        importe = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        fecha2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        btnArqueo = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        musuario = new javax.swing.JTextField();
        mcontraseña = new javax.swing.JPasswordField();
        mcontraseña2 = new javax.swing.JPasswordField();
        cambiar = new javax.swing.JButton();
        error = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        ID = new javax.swing.JLabel();
        bienvenido = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        menu = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        cerrarsesion = new javax.swing.JMenuItem();
        uss = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        confi.setBackground(new java.awt.Color(0, 0, 255));
        confi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                confiMouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("USUARIO");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("CONTRASEÑA");

        txtcontraseña.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcontraseñaKeyPressed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton6.setText("Ingresar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jButton6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton6KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtcontraseña)
                            .addComponent(txtusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(242, 242, 242)
                        .addComponent(jButton6)))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtusuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtcontraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66)
                .addComponent(jButton6)
                .addContainerGap(89, Short.MAX_VALUE))
        );

        confi.addTab("USUARIO", jPanel4);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jDesktopPane1.setBackground(new java.awt.Color(0, 0, 255));

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("N. AUTORIZACION");

        jLabel2.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("CUENTA RETIRO");

        jLabel3.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("MONTO");
        jLabel3.setToolTipText("");

        RealizarBanco.setFont(new java.awt.Font("Swis721 BT", 0, 14)); // NOI18N
        RealizarBanco.setText("REALIZAR");
        RealizarBanco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RealizarBancoActionPerformed(evt);
            }
        });

        DenominacionesBanco.setFont(new java.awt.Font("Swis721 BT", 0, 14)); // NOI18N
        DenominacionesBanco.setText("DENOMINACIONES");
        DenominacionesBanco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DenominacionesBancoActionPerformed(evt);
            }
        });

        autorizacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                autorizacionKeyTyped(evt);
            }
        });

        monto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                montoKeyTyped(evt);
            }
        });

        hora.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        hora.setForeground(new java.awt.Color(255, 255, 255));

        fecha1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        fecha1.setForeground(new java.awt.Color(255, 255, 255));

        ComboBoxBanco.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        ComboBoxBanco.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar Opcion", "Transferencia", "Retiro" }));
        ComboBoxBanco.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ComboBoxBancoItemStateChanged(evt);
            }
        });

        jDesktopPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(RealizarBanco, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(DenominacionesBanco, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(autorizacion, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(cuenta, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(monto, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(hora, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(fecha1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(ComboBoxBanco, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(ComboBoxBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(monto, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(DenominacionesBanco))
                    .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(autorizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(60, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addComponent(fecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(hora, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(RealizarBanco)
                .addGap(273, 273, 273))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboBoxBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(autorizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(monto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DenominacionesBanco))
                .addGap(79, 79, 79)
                .addComponent(RealizarBanco)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(hora, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(fecha1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel1.add(jDesktopPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 440));

        confi.addTab("BANCO", jPanel1);

        escritorio.setBackground(new java.awt.Color(0, 0, 204));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona Opcion", "Prestamo", "Devoluciones" }));
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("IMPORTE ");

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("CONCEPTO:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("RECIBIDO POR:");

        importe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                importeKeyTyped(evt);
            }
        });

        jButton2.setText("DENOMINACIONES");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("GUARDAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        fecha2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        fecha2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("RECIBIDO POR:");

        escritorio.setLayer(jComboBox2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        escritorio.setLayer(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        escritorio.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        escritorio.setLayer(recibido, javax.swing.JLayeredPane.DEFAULT_LAYER);
        escritorio.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        escritorio.setLayer(concepto, javax.swing.JLayeredPane.DEFAULT_LAYER);
        escritorio.setLayer(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        escritorio.setLayer(importe, javax.swing.JLayeredPane.DEFAULT_LAYER);
        escritorio.setLayer(jButton2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        escritorio.setLayer(jButton3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        escritorio.setLayer(fecha2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        escritorio.setLayer(jLabel13, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout escritorioLayout = new javax.swing.GroupLayout(escritorio);
        escritorio.setLayout(escritorioLayout);
        escritorioLayout.setHorizontalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(escritorioLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(escritorioLayout.createSequentialGroup()
                .addComponent(fecha2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, escritorioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(165, 165, 165))
            .addGroup(escritorioLayout.createSequentialGroup()
                .addGroup(escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, escritorioLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(recibido, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, escritorioLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(39, 39, 39)
                        .addGroup(escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2)
                            .addComponent(importe, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(concepto, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
            .addGroup(escritorioLayout.createSequentialGroup()
                .addGap(259, 259, 259)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        escritorioLayout.setVerticalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(escritorioLayout.createSequentialGroup()
                .addGroup(escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(escritorioLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(escritorioLayout.createSequentialGroup()
                        .addComponent(concepto, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                        .addGroup(escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                            .addComponent(fecha2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(escritorioLayout.createSequentialGroup()
                        .addGroup(escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(recibido, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(30, 30, 30)
                        .addGroup(escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(importe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio)
        );

        confi.addTab("CAJA CHICA", jPanel2);

        jPanel3.setBackground(new java.awt.Color(0, 0, 204));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable6);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 440, 160));
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 290, 70));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, 440, 120));

        confi.addTab("DENOMINACIONES", jPanel3);

        jPanel5.setBackground(new java.awt.Color(0, 51, 204));

        btnArqueo.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        btnArqueo.setText("ARQUEO");
        btnArqueo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArqueoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(192, Short.MAX_VALUE)
                .addComponent(btnArqueo)
                .addGap(179, 179, 179))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(159, 159, 159)
                .addComponent(btnArqueo)
                .addContainerGap(210, Short.MAX_VALUE))
        );

        confi.addTab("ARQUEO", jPanel5);

        jPanel6.setBackground(new java.awt.Color(0, 51, 204));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("N.USUARIO");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("USUARIO");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("CONTRASEÑA");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("CONTRASEÑA");

        mcontraseña.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                mcontraseñaKeyReleased(evt);
            }
        });

        mcontraseña2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                mcontraseña2KeyReleased(evt);
            }
        });

        cambiar.setText("ACEPTAR");
        cambiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambiarActionPerformed(evt);
            }
        });

        error.setFont(new java.awt.Font("MV Boli", 0, 11)); // NOI18N
        error.setForeground(new java.awt.Color(255, 0, 51));
        error.setText("CONTRASEÑA NO COINCIDE");

        jRadioButton1.setText("MODIFICAR");
        jRadioButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton1MouseClicked(evt);
            }
        });
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        ID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        bienvenido.setFont(new java.awt.Font("Vijaya", 0, 24)); // NOI18N
        bienvenido.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel15)
                        .addComponent(jLabel16)
                        .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(cambiar)
                        .addGap(291, 291, 291))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(mcontraseña)
                                .addComponent(musuario)
                                .addComponent(mcontraseña2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jRadioButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(error))
                        .addGap(130, 130, 130))))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bienvenido, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bienvenido, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jRadioButton1)
                        .addGap(27, 27, 27)
                        .addComponent(error, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10)
                            .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(musuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(mcontraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(mcontraseña2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addComponent(cambiar)))
                .addContainerGap(159, Short.MAX_VALUE))
        );

        confi.addTab("CONFIGURACION", jPanel6);

        jMenu1.setText("CUENTA");

        cerrarsesion.setText("CERRAR SESION");
        cerrarsesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cerrarsesionMouseClicked(evt);
            }
        });
        cerrarsesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarsesionActionPerformed(evt);
            }
        });
        jMenu1.add(cerrarsesion);

        menu.add(jMenu1);
        menu.add(uss);

        setJMenuBar(menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(confi, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(confi, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cerrarsesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cerrarsesionMouseClicked

        txtusuario.setText("");
        txtcontraseña.setText("");
        confi.setEnabledAt(2, false);
        confi.setEnabledAt(1, false);
        confi.setEnabledAt(3, false);
        confi.setSelectedIndex(0);
        confi.setEnabledAt(0, true);
        jMenu1.setEnabled(false);
    }//GEN-LAST:event_cerrarsesionMouseClicked

    private void cerrarsesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarsesionActionPerformed
///llamamos clase cerrar
        cerrar();
    }//GEN-LAST:event_cerrarsesionActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        if (!recibido.getText().equals("") && !importe.getText().equals("") && !concepto.getText().equals("")) {
            auto l = new auto();
            int id = l.id_vales();
            String idd = String.valueOf(id);
            String sql = "insert into valess(id,Nombre,Monto,Concepto,Fecha,usuario,tipo)values(?,?,?,?,?,?,?)";
            try {
                PreparedStatement sent = conn.prepareStatement(sql);
                String timeset = fecha1.getText() + " " + hora.getText();
                sent.setString(1, idd);
                sent.setString(2, recibido.getText());
                sent.setString(3, importe.getText());
                sent.setString(4, concepto.getText());
                sent.setString(5, timeset);
                sent.setString(6, uss.getText());
                sent.setString(7, (String) jComboBox2.getSelectedItem());
                sent.executeUpdate();

                JOptionPane.showMessageDialog(null, "Datos cargados");

                jButton3.setEnabled(false);
                borrar();
                llenar3();
                todo();
                llenar();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "El error " + ex);

                Logger.getLogger(PanelP.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Tiene que llenar todos los campos ");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //Aqui agrego el label a mi JtabbletPanel y luego lo ago visible
        if (jComboBox2.getSelectedIndex() == 1) {

            monto l = new monto();
            escritorio.add(l);
            l.setVisible(true);
            l.jLabel17.setText("Prestamos");
        }
        if (jComboBox2.getSelectedIndex() == 2) {
            monto l = new monto();
            escritorio.add(l);
            l.setVisible(true);
            l.jLabel17.setText("Devoluciones");

        }
        if (jComboBox2.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Tienes que elegir una opcion primero");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void DenominacionesBancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DenominacionesBancoActionPerformed
        //Aqui agrego el label a mi JtabbletPanel y luego lo ago visible
        if (ComboBoxBanco.getSelectedIndex() == 1) {

            monto l = new monto();
            jDesktopPane1.add(l);
            l.setVisible(true);
            l.jLabel17.setText("Transferencia");
        }
        if (ComboBoxBanco.getSelectedIndex() == 2) {
            monto l = new monto();
            jDesktopPane1.add(l);
            l.setVisible(true);
            l.jLabel17.setText("Retiro");

        }
        if (ComboBoxBanco.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Tienes que elegir una opcion primero");
        }
        todo();
        llenar();
        llenar3();


    }//GEN-LAST:event_DenominacionesBancoActionPerformed

    private void RealizarBancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RealizarBancoActionPerformed
        try {
            if (!autorizacion.getText().equals("") && !cuenta.getText().equals("") && !monto.getText().equals("")) {
                monto l = new monto();
                denominaciones d = new denominaciones();
                d.banco();
                float mono = Float.valueOf(monto.getText());
                float bancos = d.banco();
                float totales = bancos + mono;
                JOptionPane.showMessageDialog(null, totales);
                try {
                    float todo = bancos - Float.valueOf(monto.getText());
                    if (todo >= 0) {
//////////////////////////////////////////////////////////RETIRO
                        if (ComboBoxBanco.getSelectedItem() == "Retiro") {
                            auto s = new auto();
                            int idc = s.id_caja();
                            String idd = String.valueOf(idc);
                            String insert = "insert into banco(id,Autorización,Cuenta,Monto,Fecha,usuario,tipo) values(?,?,?,?,?,?,?)";
                            String timeset = fecha1.getText() + " " + hora.getText();
                            PreparedStatement ps = null;
                            try {
                                ps = conn.prepareStatement(insert);
                                ps.setString(1, idd);
                                ps.setString(2, autorizacion.getText());
                                ps.setString(3, cuenta.getText());
                                ps.setString(4, monto.getText());
                                ps.setString(5, timeset);
                                ps.setString(6, uss.getText());
                                ps.setString(7, (String) ComboBoxBanco.getSelectedItem());
                                JOptionPane.showMessageDialog(null, "Retiro terminada con la cantidads de :" + monto.getText());
                                RealizarBanco.setEnabled(false);
                                llenar();
                                llenar3();
                                todo();
                                borrar();
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, "Error en banco" + e);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Saldo insuficiente, solo tiene para retirar $" + bancos);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Primero tienes que registrar los datos " + e);
                }
//////////////////////////////////////////////////////// TRANSFERENCIA
                if (ComboBoxBanco.getSelectedItem() == "Transferencia" && totales <= 20000) {
                    auto s = new auto();
                    int idc = s.id_caja();
                    String idd = String.valueOf(idc);
                    String insert = "insert into banco(id,Autorización,Cuenta,Monto,Fecha,usuario,tipo) values(?,?,?,?,?,?,?)";
                    String timeset = fecha1.getText() + " " + hora.getText();
                    PreparedStatement ps = null;
                    try {
                        ps = conn.prepareStatement(insert);
                        ps.setString(1, idd);
                        ps.setString(2, autorizacion.getText());
                        ps.setString(3, cuenta.getText());
                        ps.setString(4, monto.getText());
                        ps.setString(5, timeset);
                        ps.setString(6, uss.getText());
                        ps.setString(7, (String) ComboBoxBanco.getSelectedItem());
                        ps.executeUpdate();
                        String so = "select*from cheque where id=1";
                        Statement sent = conn.createStatement();
                        ResultSet rs = sent.executeQuery(so);
                        rs.next();
                        try {
                            float gas = Float.valueOf(rs.getString("gastos"));
                            float banco = Float.valueOf(rs.getString("banco"));
                            float mont = Float.valueOf(monto.getText());
                            float gast = Float.valueOf(gas - mont);
                            String gastos = String.valueOf(gast);
                            String total = String.valueOf(banco + mont);
                            String o = "Update cheque set banco=?, gastos=? where id=1";
                            PreparedStatement pss = conn.prepareCall(o);
                            pss.setString(1, total);
                            pss.setString(2, gastos);
                            int n = pss.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Transferencia terminada con la cantidads de :" + monto.getText());
                            todo();
                            llenar();
                            llenar3();
                            borrar();
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e);
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error en transferencia " + e);
                    }
                }
//////////////////////////// CANTIDAD EXCEDE 
                if (totales > 20000 && ComboBoxBanco.getSelectedItem() == "Transferencia") {
                    JOptionPane.showMessageDialog(null, "La cantidad excede los 20,000");
                    monto.setText("");
                }
///////////////// SELECCIONAR OPCION
                if (ComboBoxBanco.getSelectedItem() == "Seleccionar Opcion") {
                    JOptionPane.showMessageDialog(null, "Tienes que seleccionar primero una opcion");
                }
///////////////////// FIN DE AÑO 
                if (ComboBoxBanco.getSelectedItem() == "fin de año") {

                }
                llenar();
                llenar3();
                todo();
            } else {
                JOptionPane.showMessageDialog(null, "Tiene que llenar todos los campos ");
            }
            llenar();
            llenar3();
            todo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error todo " + e);
        }
    }//GEN-LAST:event_RealizarBancoActionPerformed

    private void jButton6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton6KeyPressed
/////////// ENTER

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String l = "";
            String usuario = txtusuario.getText();
            String contraseña = txtcontraseña.getText();
            String sql = "select*from usuario where nombre='" + usuario + "' and contraseña='" + contraseña + "'";
            try {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql);

                while (rs.next()) {
                    l = "si";

                    confi.setEnabledAt(2, true);
                    confi.setEnabledAt(1, true);
                    confi.setEnabledAt(3, true);
                    confi.setEnabledAt(4, true);
                    confi.setEnabledAt(5, true);
                    confi.setSelectedIndex(1);
                    confi.setEnabledAt(0, false);
                    jMenu1.setEnabled(true);
                    uss.setText(usuario);
                    bienvenido.setText("BIENVENIDO : " + usuario);
                    ID.setText(rs.getString("id"));
                    musuario.setText(rs.getString("nombre"));
                    mcontraseña.setText(rs.getString("contraseña"));
                    mcontraseña2.setText(rs.getString("contraseña"));
                }
                if (l.equals("si")) {

                } else {
                    JOptionPane.showMessageDialog(null, "Usuario y/o contraseña incorrectas");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error del sistema" + ex);
            }

        }

    }//GEN-LAST:event_jButton6KeyPressed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
//USUARIO INGRESAR 
        String l = "";
        String usuario = txtusuario.getText();
        String contraseña = txtcontraseña.getText();
        String sql = "select*from usuario where nombre='" + usuario + "' and contraseña='" + contraseña + "'";

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                l = "si";

                confi.setEnabledAt(2, true);
                confi.setEnabledAt(1, true);
                confi.setEnabledAt(3, true);
                confi.setEnabledAt(4, true);
                confi.setEnabledAt(5, true);
                confi.setSelectedIndex(1);
                confi.setEnabledAt(0, false);
                jMenu1.setEnabled(true);
                uss.setText(usuario);
                bienvenido.setText("BIENVENIDO : " + usuario);
                ID.setText(rs.getString("id"));
                musuario.setText(rs.getString("nombre"));
                mcontraseña.setText(rs.getString("contraseña"));
                mcontraseña2.setText(rs.getString("contraseña"));

            }
            if (l.equals("si")) {

            } else {
                JOptionPane.showMessageDialog(null, "Usuario y/o contraseña incorrectas");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error del sistema" + ex);
        }

        /*
        paneldepanel.setEnabledAt(2,true);
        paneldepanel.setEnabledAt(1,true);
        paneldepanel.setEnabledAt(3,true);
        paneldepanel.setSelectedIndex(1);
        paneldepanel.setEnabledAt(0,false);
        jMenu1.setEnabled(true);
         */
    }//GEN-LAST:event_jButton6ActionPerformed

    private void txtcontraseñaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcontraseñaKeyPressed
////INGRESAR USUARIO CON LA TECLA ENTER EN EL LABEL CONTRASEÑA
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String l = "";
            String usuario = txtusuario.getText();
            String contraseña = txtcontraseña.getText();
            String sql = "select*from usuario where nombre='" + usuario + "' and contraseña='" + contraseña + "'";
            try {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql);

                while (rs.next()) {
                    l = "si";

                    confi.setEnabledAt(2, true);
                    confi.setEnabledAt(1, true);
                    confi.setEnabledAt(3, true);
                    confi.setEnabledAt(4, true);
                    confi.setEnabledAt(5, true);
                    confi.setSelectedIndex(1);
                    confi.setEnabledAt(0, false);
                    jMenu1.setEnabled(true);
                    uss.setText(usuario);
                    bienvenido.setText("BIENVENIDO : " + usuario);
                    ID.setText(rs.getString("id"));
                    musuario.setText(rs.getString("nombre"));
                    mcontraseña.setText(rs.getString("contraseña"));
                    mcontraseña2.setText(rs.getString("contraseña"));
                }
                if (l.equals("si")) {

                } else {
                    JOptionPane.showMessageDialog(null, "Usuario y/o contraseña incorrectas");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error del sistema" + ex);
                System.out.println("error del sistema" + ex);
            }

        }
    }//GEN-LAST:event_txtcontraseñaKeyPressed

    private void mcontraseñaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mcontraseñaKeyReleased
        if (mcontraseña.getText().equals(mcontraseña2.getText())) {
            cambiar.setEnabled(true);
            error.setVisible(false);
        } else {
            cambiar.setEnabled(false);
            error.setVisible(true);

        }
    }//GEN-LAST:event_mcontraseñaKeyReleased

    private void mcontraseña2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mcontraseña2KeyReleased
        if (mcontraseña2.getText().equals(mcontraseña.getText())) {
            cambiar.setEnabled(true);
            error.setVisible(false);
        } else {
            cambiar.setEnabled(false);
            error.setVisible(true);
        }
    }//GEN-LAST:event_mcontraseña2KeyReleased

    private void cambiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambiarActionPerformed

        int fila = Integer.valueOf(ID.getText());

        String sql = "Update usuario set nombre=?,contraseña=? where id=?";
        try {
            PreparedStatement sent = conn.prepareCall(sql);
            sent.setString(1, musuario.getText());
            sent.setString(2, mcontraseña.getText());
            sent.setString(3, ID.getText());
            int n = sent.executeUpdate();
            if (n > 0) {
                uss.setText(musuario.getText());
                JOptionPane.showMessageDialog(null, "Usuario modificado");
                cerrar();
                borrar();
            } else {
                JOptionPane.showMessageDialog(null, "Error bruto");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e);
        }

    }//GEN-LAST:event_cambiarActionPerformed

    private void jRadioButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton1MouseClicked
        musuario.setEditable(true);
        mcontraseña.setEditable(true);
        mcontraseña2.setEditable(true);
    }//GEN-LAST:event_jRadioButton1MouseClicked

    private void autorizacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_autorizacionKeyTyped
        char l = evt.getKeyChar();

        if (Character.isLetter(l)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_autorizacionKeyTyped

    private void montoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_montoKeyTyped
        char l = evt.getKeyChar();

        if (Character.isLetter(l)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_montoKeyTyped

    private void importeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_importeKeyTyped
        char l = evt.getKeyChar();

        if (Character.isLetter(l)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_importeKeyTyped

    private void ComboBoxBancoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ComboBoxBancoItemStateChanged
        if (ComboBoxBanco.getSelectedItem() == "Retiro") {
            RealizarBanco.setEnabled(false);
            DenominacionesBanco.setEnabled(true);
        }
        else if (ComboBoxBanco.getSelectedItem() == "Transferencia") {
            String sql = "select gastos from cheque ";
            try {
                Statement sent = conn.createStatement();
                ResultSet rs = sent.executeQuery(sql);
                DenominacionesBanco.setEnabled(false);
                rs.next();

                float gastos = Float.valueOf(rs.getString("gastos"));
                monto.setText(String.valueOf(gastos));

            } catch (Exception e) {
            }

        } else {
            RealizarBanco.setEnabled(true);
        }
    }//GEN-LAST:event_ComboBoxBancoItemStateChanged

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        if (jComboBox2.getSelectedIndex() == 0) {
            jButton3.setEnabled(true);
        } else {
            jButton3.setEnabled(false);
        }
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed

    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void btnArqueoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArqueoActionPerformed
        
        try {
          /*  //////////////////
            String sql = "select*from denominaciones where id=1";
            Statement sent = conn.createStatement();
            ResultSet rs = sent.executeQuery(sql);

            rs.next();

            //
            int mil = Integer.parseInt(rs.getString("a1000")) * 1000;
            int quinientos = Integer.parseInt(rs.getString("a500")) * 500;
            int doscientos = Integer.parseInt(rs.getString("a200")) * 200;
            int cien = Integer.parseInt(rs.getString("a100")) * 100;
            int cincuenta = Integer.parseInt(rs.getString("a50")) * 50;
            int veinte = Integer.parseInt(rs.getString("a20")) * 20;
            int veinteM = Integer.parseInt(rs.getString("M20")) * 20;
            int diez = Integer.parseInt(rs.getString("a10")) * 10;
            int cinco = Integer.parseInt(rs.getString("a5")) * 5;
            int dos = Integer.parseInt(rs.getString("a2")) * 2;
            int uno = Integer.parseInt(rs.getString("a1")) * 1;
            float Ccincuenta = Integer.parseInt(rs.getString("C50")) * 0.5f;
            float Cveinte = Integer.parseInt(rs.getString("C20")) * 0.2f;
            float Cdiez = Integer.parseInt(rs.getString("C10")) * 0.1f;
            float totalmoneda = veinteM + diez + cinco + dos + uno + Ccincuenta + Cveinte + Cdiez;
            float total = mil + quinientos + doscientos + cien + cincuenta + veinte + veinteM + diez + cinco + dos + uno + Ccincuenta
                    + Cveinte + Cdiez;
            String s = "select*from cheque where id=1";

            Statement sen = conn.createStatement();
            ResultSet r = sen.executeQuery(s);
            r.next();
            float banco = Float.valueOf(r.getString("banco"));
            float gastos = Float.valueOf(r.getString("gastos"));
            float totalbillete = mil + quinientos + doscientos + cien + cincuenta + veinte;

            float arqueo = banco + gastos + total;
            float faltante = arqueo - 20000;*/
            //////////////////////
          /*  Map parametro = new HashMap<String, Object>();
            parametro.put("mil", mil);
            parametro.put("quinientos", quinientos);
            parametro.put("doscientos", doscientos);
            parametro.put("cien", cien);
            parametro.put("cincuenta", cincuenta);
            parametro.put("veinte", veinte);
            parametro.put("monedaveinte", veinteM);
            parametro.put("diez", diez);
            parametro.put("cinco", cinco);
            parametro.put("dos", dos);
            parametro.put("uno", uno);
            parametro.put("cincuentacentavos", Ccincuenta);
            parametro.put("veintecentavos", Cveinte);
            parametro.put("diezcentavos", Cdiez);
            parametro.put("arqueo", arqueo);
            parametro.put("faltante", faltante);
            parametro.put("totalbillete", totalbillete);
            parametro.put("totalmoneda", totalmoneda);
            parametro.put("hora", hora.getText());*/
            JasperReport reportes = null;
            String ruta = "src\\reporte\\report1.jasper";
            reportes = JasperCompileManager.compileReport(ruta);

            JasperPrint pint = JasperFillManager.fillReport(reportes, null, conn);
            JasperViewer view = new JasperViewer(pint, false);
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);

        } catch (JRException ex) {
            Logger.getLogger(PanelP.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"JRE" + ex);
            System.out.println(ex);
        }/* catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "arqueo " + ex);
            System.out.println(ex);
            Logger.getLogger(PanelP.class.getName()).log(Level.SEVERE, null, ex);
        }*/


    }//GEN-LAST:event_btnArqueoActionPerformed

    private void confiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confiMouseClicked
        if (autorizacion.getText().equals("") && cuenta.getText().equals("") && monto.getText().equals("")) {

            ComboBoxBanco.setSelectedIndex(0);
        }
        if (recibido.getText().equals("") && importe.getText().equals("") && concepto.getText().equals("")) {
            jComboBox2.setSelectedIndex(0);
        } else {

        }
    }//GEN-LAST:event_confiMouseClicked

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
            java.util.logging.Logger.getLogger(PanelP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PanelP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PanelP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PanelP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PanelP().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBoxBanco;
    private javax.swing.JButton DenominacionesBanco;
    private javax.swing.JLabel ID;
    public static javax.swing.JButton RealizarBanco;
    private javax.swing.JTextField autorizacion;
    private javax.swing.JLabel bienvenido;
    private javax.swing.JButton btnArqueo;
    private javax.swing.JButton cambiar;
    private javax.swing.JMenuItem cerrarsesion;
    private javax.swing.JTextField concepto;
    private javax.swing.JTabbedPane confi;
    private javax.swing.JTextField cuenta;
    private javax.swing.JLabel error;
    private javax.swing.JDesktopPane escritorio;
    private javax.swing.JLabel fecha1;
    private javax.swing.JLabel fecha2;
    private javax.swing.JLabel hora;
    private javax.swing.JLabel imagen;
    public static javax.swing.JTextField importe;
    private javax.swing.JButton jButton2;
    public static javax.swing.JButton jButton3;
    private javax.swing.JButton jButton6;
    public static javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable6;
    private javax.swing.JPasswordField mcontraseña;
    private javax.swing.JPasswordField mcontraseña2;
    private javax.swing.JMenuBar menu;
    public static javax.swing.JTextField monto;
    private javax.swing.JTextField musuario;
    private javax.swing.JTextField recibido;
    private javax.swing.JPasswordField txtcontraseña;
    private javax.swing.JTextField txtusuario;
    public static javax.swing.JMenu uss;
    // End of variables declaration//GEN-END:variables

    public void run() {
        Thread ct = Thread.currentThread();
        while (ct == hi) {
            calcula();
            jLabel6.setText(horas + ":" + minutos + ":" + segundos);
            hora.setText(horas + ":" + minutos + ":" + segundos);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }

    private void calcula() {
        Calendar calendario = new GregorianCalendar();
        Date horass = new Date();
        calendario.setTime(horass);
        horas = calendario.get(Calendar.HOUR_OF_DAY) > 9 ? "" + calendario.get(Calendar.HOUR_OF_DAY)
                : "0" + calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE)
                : "0" + calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND)
                : "0" + calendario.get(Calendar.SECOND);
    }

}
