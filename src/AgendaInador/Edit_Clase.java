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

public class Edit_Clase extends javax.swing.JFrame {
    int clave_usuario;
    int clave_clase;
     int clave_materia;
    String link_edit;
    Date fecha_edit;
    Date hora_edit;
    String materia_edit;
    ImageIcon img=new ImageIcon("AgendaInadorIcon.png");

    Edit_Clase(int clave_usuario, String linku, Date fecha, Date hora, String materia) {
        setIconImage(img.getImage());
        setVisible(true);
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Edit_Clase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        this.clave_usuario=clave_usuario;
        this.fecha_edit=fecha;
        this.hora_edit=hora;
        this.materia_edit=materia;
        this.link_edit=linku;
        setLocationRelativeTo(null);
        initComponents();
        selec_fecha.setDate(fecha);
        
        setTitle("Editar clase");
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                salir();
            }
        });
        leermateria();
        jButton1.setEnabled(false);
        Runnable r = () -> {
            habilitar_boton();
        };
        new Thread(r).start();
        jtf_nombre_materia.setSelectedItem(materia);
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
        label_meses = new javax.swing.JLabel();
        jtfmeses = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(0, 204, 204));
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });

        selec_hora.setToolTipText("");

        jButton1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jButton1.setText("Editar Clase");
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

        jtf_nombre_materia.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        check_box_repetir.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        check_box_repetir.setSelected(true);
        check_box_repetir.setText("Repetir cada semana");
        check_box_repetir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_box_repetirActionPerformed(evt);
            }
        });

        label_meses.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        label_meses.setText("¿Durante cuantos meses?");

        jtfmeses.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jtfmeses.setValue(1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(check_box_repetir)
                .addGap(0, 168, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel4))
                                .addGap(52, 52, 52)
                                .addComponent(selec_hora, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 158, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jtf_nombre_materia, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(selec_fecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(77, 77, 77)
                                .addComponent(label_meses)
                                .addGap(18, 18, 18)
                                .addComponent(jtfmeses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(182, 182, 182)
                                .addComponent(jButton1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selec_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(selec_hora, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jtf_nombre_materia, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(check_box_repetir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfmeses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_meses))
                .addGap(28, 28, 28)
                .addComponent(jButton1)
                .addGap(42, 42, 42))
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

    private void check_box_repetirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_box_repetirActionPerformed
        
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
        int clave_materia_leida;
         nombres_materia.iniBase_Datos();
        try {
            nombres_materia.query="SELECT clave_usuario, clave_materia, nombre_materia FROM materia";
            nombres_materia.rs=nombres_materia.stmt.executeQuery(nombres_materia.query);
            while(nombres_materia.rs.next()){
                int clave_usuario_leida=nombres_materia.rs.getInt("clave_usuario");
                clave_materia_leida=nombres_materia.rs.getInt("clave_materia");
                materia_leida=nombres_materia.rs.getString("nombre_materia");
                
                if(clave_usuario_leida==clave_usuario){
                    jtf_nombre_materia.addItem(materia_leida);
                    if(materia_edit.equals(materia_leida)){
                        clave_materia=clave_materia_leida;
                    }
                }
            }
            SimpleDateFormat a=new SimpleDateFormat("HH:mm");
            SimpleDateFormat dias=new SimpleDateFormat("dd/MM/yy");
            int clave_clase_leida;
            Date fecha_leida;
            Date hora_leida;
            String plataforma_leida;
            String link_leido;
            nombres_materia.query="SELECT clave_usuario, clave_clase, dia_clase, hora_clase, clave_materia FROM clase";
            nombres_materia.rs=nombres_materia.stmt.executeQuery(nombres_materia.query);
            while(nombres_materia.rs.next()){
                int clave_usuario_leida=nombres_materia.rs.getInt("clave_usuario");
                clave_clase_leida=nombres_materia.rs.getInt("clave_clase");
                fecha_leida=nombres_materia.rs.getDate("dia_clase");
                hora_leida=nombres_materia.rs.getTime("hora_clase");
                clave_materia_leida=nombres_materia.rs.getInt("clave_materia");
                if(clave_usuario_leida==clave_usuario&&fecha_leida.equals(fecha_edit)&&a.format(hora_leida).equals(a.format(this.hora_edit))&&clave_materia_leida==clave_materia){
                    this.clave_clase=clave_clase_leida;
                    
                }
            }
        }   catch (SQLException ex) {
            Logger.getLogger(Base_Datos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editar(){
        boolean repetir=false;
        if(check_box_repetir.isSelected()){
            repetir=true;
        }
        try {
            Date dia=selec_fecha.getDate();
            String hora_string=selec_hora.getSelectedTimeDB()+":00";
            Date hora_clase_date=new SimpleDateFormat("HH:mm:ss").parse(hora_string);
            String nombre_materia=String.valueOf(jtf_nombre_materia.getSelectedItem());
            java.sql.Time hora_clase=new java.sql.Time(hora_clase_date.getTime());
            
            if(dia!=null||nombre_materia!=null){
                if(repetir==false){
                    java.sql.Date fecha_entrega=new java.sql.Date(dia.getTime());
                    Base_Datos clase=new Base_Datos(clave_usuario,clave_clase,fecha_entrega,hora_clase,nombre_materia);
                    clase.obtener_clave_materia();
                    clase.edit_clase();
                    salir();
                    JOptionPane.showMessageDialog(null, "Se ha editado el registro","Registro editado",JOptionPane.INFORMATION_MESSAGE);
                    
                }else{
                    java.sql.Date fecha_entrega=new java.sql.Date(dia.getTime());
                    Base_Datos clase=new Base_Datos(clave_usuario,clave_clase,fecha_entrega,hora_clase,nombre_materia);
                    clase.obtener_clave_materia();
                    clase.edit_clase();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(dia);
                    int repeticiones=Integer.parseInt(String.valueOf(jtfmeses.getValue()));
                    for(int i=0;i<(repeticiones*5)-1;i++){
                        dia=cal.getTime();
                        fecha_entrega=new java.sql.Date(dia.getTime());
                        Base_Datos clase_añadir=new Base_Datos(clave_usuario,fecha_entrega,hora_clase,nombre_materia);
                        clase_añadir.obtener_clave_materia();
                        clase_añadir.reg_clase();
                        cal.add(Calendar.DATE, 7);
                    }
                    salir();
                    JOptionPane.showMessageDialog(null, "Se ha editado el registro y repetirá por "+repeticiones+" mes(es)","Registro correcto",JOptionPane.INFORMATION_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Por favor coloca los datos","Error de datos",JOptionPane.WARNING_MESSAGE);
            }
        } catch (ParseException ex) {
            Logger.getLogger(Edit_Clase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
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
}
