package AgendaInador;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Reg_Clase extends javax.swing.JFrame {
    int clave_usuario;
    ImageIcon img=new ImageIcon("AgendaInadorIcon.png");
    
    public Reg_Clase(int clave_usuario) {
        setIconImage(img.getImage());
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Reg_Clase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reg_Clase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reg_Clase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reg_Clase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        this.clave_usuario=clave_usuario;
        setLocationRelativeTo(null);
        initComponents();
        setTitle("Registrar clase");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                salir();
            }
        });
        jButton1.setEnabled(false);
        leermateria();
        Runnable r = () -> {
            habilitar_boton();
        };
        new Thread(r).start();
    } 
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        selec_hora = new cambodia.raven.Time();
        selec_fecha = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jtf_nombre_materia = new javax.swing.JComboBox<>();
        check_box_repetir = new javax.swing.JCheckBox();
        jtfmeses = new javax.swing.JSpinner();
        label_meses = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jButton1.setText("Añadir Clase");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Hora de clase");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel3.setText("Fecha de clase");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel4.setText("Materia");

        jtf_nombre_materia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_nombre_materiaActionPerformed(evt);
            }
        });

        check_box_repetir.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        check_box_repetir.setSelected(true);
        check_box_repetir.setText("Repetir cada semana");
        check_box_repetir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_box_repetirActionPerformed(evt);
            }
        });

        jtfmeses.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jtfmeses.setToolTipText("1");
        jtfmeses.setValue(1);

        label_meses.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        label_meses.setText("¿Durante cuantos meses?");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jtf_nombre_materia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(selec_hora, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selec_fecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(90, Short.MAX_VALUE)
                .addComponent(label_meses)
                .addGap(18, 18, 18)
                .addComponent(jtfmeses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(check_box_repetir, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(171, 171, 171)
                        .addComponent(jButton1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(selec_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(selec_hora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtf_nombre_materia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addComponent(check_box_repetir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfmeses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_meses))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(49, 49, 49))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        
    }//GEN-LAST:event_formKeyPressed

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_formKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jtf_nombre_materiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_nombre_materiaActionPerformed

    }//GEN-LAST:event_jtf_nombre_materiaActionPerformed

    private void check_box_repetirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_box_repetirActionPerformed
        System.out.println("Hola");
        if(check_box_repetir.isSelected()){
            jtfmeses.setVisible(true);
            label_meses.setVisible(true);
        }else{
            jtfmeses.setVisible(false);
            label_meses.setVisible(false);
        }
    }//GEN-LAST:event_check_box_repetirActionPerformed

    public void salir(){
        try {
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("Metal".equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reg_Clase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
            this.dispose();
    }
    
    private void leermateria() {
        Base_Datos nombres_materia=new Base_Datos(clave_usuario);
        String materia_leida;
        String default_link;
        nombres_materia.iniBase_Datos();
        try {
            nombres_materia.query="SELECT clave_usuario, nombre_materia, link_clase FROM materia";
            nombres_materia.rs=nombres_materia.stmt.executeQuery(nombres_materia.query);
            while(nombres_materia.rs.next()){
                int clave_usuario_leida=nombres_materia.rs.getInt("clave_usuario");
                materia_leida=nombres_materia.rs.getString("nombre_materia");
                default_link=nombres_materia.rs.getString("link_clase");
                if(clave_usuario_leida==clave_usuario){
                    jtf_nombre_materia.addItem(materia_leida);
                }
            } 
        }   catch (SQLException ex) {
            Logger.getLogger(Base_Datos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void registrar(){
        boolean repetir=false;
        if(check_box_repetir.isSelected()){
            repetir=true;
        }
        try {
//            String link=selec_plataforma.getText();
            Date dia=selec_fecha.getDate();
            String hora_string=selec_hora.getSelectedTimeDB()+":00";
            Date hora_clase_date=new SimpleDateFormat("HH:mm:ss").parse(hora_string);
            String nombre_materia=String.valueOf(jtf_nombre_materia.getSelectedItem());
            java.sql.Time hora_clase=new java.sql.Time(hora_clase_date.getTime());
            System.out.print(hora_clase);
            if(repetir==true){
                Calendar cal = Calendar.getInstance();
                cal.setTime(dia);
                int repeticiones=Integer.parseInt(String.valueOf(jtfmeses.getValue()));
                for(int i=0;i<repeticiones*5;i++){
                    dia=cal.getTime();
                    java.sql.Date fecha_entrega=new java.sql.Date(dia.getTime());
                    Base_Datos clase=new Base_Datos(clave_usuario,fecha_entrega,hora_clase,nombre_materia);
                    clase.obtener_clave_materia();
                    clase.reg_clase();
                    cal.add(Calendar.DATE, 7);
                }
                salir();
                JOptionPane.showMessageDialog(null, "Las clases ha sido registradas","Registro correcto",JOptionPane.INFORMATION_MESSAGE);
            }else{
                java.sql.Date fecha_entrega=new java.sql.Date(dia.getTime());
                Base_Datos clase_unica=new Base_Datos(clave_usuario,fecha_entrega,hora_clase,nombre_materia);
                clase_unica.obtener_clave_materia();
                clase_unica.reg_clase();
                
                salir();
                JOptionPane.showMessageDialog(null, "La clase ha sido registrada","Registro correcto",JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (ParseException ex) {
            Logger.getLogger(Reg_Clase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox check_box_repetir;
    public javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JComboBox<String> jtf_nombre_materia;
    private javax.swing.JSpinner jtfmeses;
    private javax.swing.JLabel label_meses;
    private com.toedter.calendar.JDateChooser selec_fecha;
    private cambodia.raven.Time selec_hora;
    // End of variables declaration//GEN-END:variables

//    private void obtener_link_materia() {
//        Base_Datos nombres_materia=new Base_Datos(clave_usuario);
//        String materia_leida;
//        String default_link;
//        nombres_materia.iniBase_Datos();
//        try {
//            nombres_materia.query="SELECT clave_usuario, nombre_materia, link_clase FROM materia";
//            nombres_materia.rs=nombres_materia.stmt.executeQuery(nombres_materia.query);
//            while(nombres_materia.rs.next()){
//                int clave_usuario_leida=nombres_materia.rs.getInt("clave_usuario");
//                materia_leida=nombres_materia.rs.getString("nombre_materia");
//                default_link=nombres_materia.rs.getString("link_clase");
//                if(clave_usuario_leida==clave_usuario&&materia_leida.equals(jtf_nombre_materia.getSelectedItem())){
//                    selec_plataforma.setText(default_link);
//                }
//            } 
//        }   catch (SQLException ex) {
//            Logger.getLogger(Base_Datos.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
    public void habilitar_boton(){
        while(true){
            Date dia=selec_fecha.getDate();
            if(dia==null){
                jButton1.setEnabled(false);
            }else{
                jButton1.setEnabled(true);
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}