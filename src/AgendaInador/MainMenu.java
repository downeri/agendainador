package AgendaInador;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.table.DefaultTableModel;

public class MainMenu {
    JFrame frame=new JFrame();
    JDesktopPane vent = new JDesktopPane();
    JTable pendientes=new JTable();
    JScrollPane pane=new JScrollPane(pendientes);
    DefaultTableModel m=new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
           //all cells false
           return false;
        }
    };
    
    String[]c={"Descripcion","Fecha de Entrega","Materia","Nombre del Profesor"};
    JLabel jlbViendo=new JLabel("Tareas");
    JLabel status=new JLabel("Vista general");
    JLabel bienvenida=new JLabel("");
    JLabel JLB_hora=new JLabel("");
    JLabel JLB_fecha=new JLabel("");
    JButton editar_tarea=new JButton("Editar tarea");
    JButton borrar_tarea=new JButton("Borrar tarea");
    JButton editar_clase=new JButton("Editar clase");
    JButton JBT_borrar_clase=new JButton("Borrar clase");
    JButton reg_materia=new JButton("Registrar Materia");
    JButton reg_clase=new JButton("Registrar Clase");
    JButton ayuda=new JButton("?");
    JToggleButton cons_materia=new JToggleButton("Consultar Materia");
    JToggleButton switch_tabla=new JToggleButton("Ver clases");
    JToggleButton switch_tabla_materia=new JToggleButton("Ver clases");
    JButton reg_tarea=new JButton("Registrar Tarea");
    JButton JBT_elim_materia=new JButton("Eliminar Materia");
    JButton back = new JButton("Cerrar Sesión");
    JButton JBT_editar_materia = new JButton("Editar Materia");
    int clave_usuario;
    String nombre_usuario;
    int tabla=0;
    int materia_seleccionada_tabla;
    ImageIcon img=new ImageIcon("AgendaInadorIcon.png");
    
    
    public MainMenu(int clave_usuario, String nombre_usuario){
        
        this.nombre_usuario=nombre_usuario;
        this.clave_usuario=clave_usuario;
        frame.setTitle("AgendaInador");
        frame.setSize(1600,900);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(vent);
        Color aeiou=new Color(240,240,240);
        vent.setBackground(aeiou);
        frame.setVisible(true);
        frame.setIconImage(img.getImage());
        
        pendientes.setModel(m);
        m.setColumnIdentifiers(c);
        pendientes.setRowHeight(50);
        pane.setBounds(100,140,1000,600);
        vent.add(pane);
        cons_materia.setBounds(1150,100,400,60);
        vent.add(cons_materia);
        cons_materia.setFont(new java.awt.Font("Arial", 0, 24));
        jlbViendo.setFont(new java.awt.Font("Arial", 0, 48));
        jlbViendo.setBounds(600,25,20000,140);
        vent.add(jlbViendo);
        status.setFont(new java.awt.Font("Arial", 0, 25));
        status.setBounds(100,-20,20000,140);
        vent.add(status);
        
        bienvenida.setFont(new java.awt.Font("Arial", 0, 30));
        bienvenida.setBounds(550,-50,20000,140);
        vent.add(bienvenida);
        
        JLB_hora.setFont(new java.awt.Font("Arial", 0, 30));
        JLB_hora.setBounds(1400,-50,20000,140);
        vent.add(JLB_hora);
        
        JLB_fecha.setFont(new java.awt.Font("Arial", 0, 30));
        JLB_fecha.setBounds(1100,-50,20000,140);
        vent.add(JLB_fecha);
        
        pendientes.getTableHeader().setFont(new java.awt.Font("Arial", 0, 24));
        pendientes.setFont(new java.awt.Font("Arial", 0, 24));
        pendientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                int columna=table.columnAtPoint(point);
                if (tabla==0&&columna==0&&mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    String o=String.valueOf(m.getValueAt(pendientes.getSelectedRow(), pendientes.getSelectedColumn()));
                    JOptionPane.showMessageDialog(null, o,"Recordatorio",JOptionPane.INFORMATION_MESSAGE);
                }
                if (tabla==1&&columna==0&&mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    try {
                        URI url=new URI(String.valueOf(m.getValueAt(pendientes.getSelectedRow(), pendientes.getSelectedColumn())));
                        int confirm=JOptionPane.showConfirmDialog(frame, "¿Abrir "+url+" en tu navegador?", "Confirmar acción",JOptionPane.YES_NO_OPTION);
                        if(confirm==0){
                            java.awt.Desktop.getDesktop().browse(url);
                        } 
                    } catch (URISyntaxException | IOException ex) {
                        Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        cons_materia.addItemListener((ItemEvent itemEvent) -> {
            int estado = itemEvent.getStateChange();
            if(estado != ItemEvent.SELECTED){
                status.setText("Vista General");
                cons_materia.setText("Consultar Materia");
                ver_tabla_tareas();
                switch_tabla.setSelected(false);
                switch_tabla_materia.setVisible(false);
                switch_tabla.setVisible(true);
            } else {
                Selec_Materia ola=new Selec_Materia(clave_usuario);
                ola.jbtconfirmar.addActionListener((ActionEvent e)-> {
                    materia_seleccionada_tabla=ola.selec_materia();
                    tabla_tareas(materia_seleccionada_tabla);
                    tabla=0;
                    switch_tabla.setVisible(false);
                    switch_tabla_materia.setVisible(true);
                    ola.frame.dispose();
                    status.setText("Materia: '"+ola.getNombreMateria(materia_seleccionada_tabla)+"' | Profesor: "+ola.getNombreProfesor(materia_seleccionada_tabla));
                });
                
                cons_materia.setText("Ver general");
            }
        });
        
        back.setBounds(10,825,170,40);
        back.setFont(new java.awt.Font("Arial", 0, 18));
        back.addActionListener((ActionEvent e)->{
            int confirm=JOptionPane.showConfirmDialog(frame, "¿Estas seguro que quieres cerrar sesión?", "Cerrar Sesión",JOptionPane.YES_NO_OPTION);
            if(confirm==0){
                FileWriter escribir = null;
                try {
                    escribir = new FileWriter("E:\\usuario_recordado.txt");
                    Proyecto n=new Proyecto();
                    frame.dispose();
                } catch (IOException ex) {
                    Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        escribir.close();
                    } catch (IOException ex) {
                        Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                JOptionPane.showMessageDialog(null, "Se ha cerrado tu sesión","Sesión cerrada",JOptionPane.INFORMATION_MESSAGE); 
            }
        });
        vent.add(back);
        
        ayuda.setBounds(100,745,40,40);
        ayuda.setFont(new java.awt.Font("Arial", 0, 11));
        ayuda.addActionListener((ActionEvent e)->{
            JOptionPane.showMessageDialog(null, "Para editar o eliminar una tarea o una clase, primero seleccione la primera columna de la fila y luego utiliza los botones en la parte inferior de la tabla."+"\n"+"\n"+"Para ver la descripción completa de una tarea, de doble click en la descipción de la misma."+"\n"+"\n"+"De la misma forma, puede dar doble click al link de una clase para abrilo en su navegador de preferencia."+"\n","Uso de la tabla",JOptionPane.INFORMATION_MESSAGE); 
        });
        vent.add(ayuda);
        
        reg_materia.setBounds(1150,440,400,60);
        vent.add(reg_materia);
        reg_materia.setFont(new java.awt.Font("Arial", 0, 24));
        reg_materia.addActionListener((ActionEvent e)->{
            Reg_Materia r=new Reg_Materia(this.clave_usuario);
            
        });
        
        JBT_elim_materia.setBounds(1150,740,400,60);
        vent.add(JBT_elim_materia);
        JBT_elim_materia.setFont(new java.awt.Font("Arial", 0, 24));
        JBT_elim_materia.addActionListener((ActionEvent e)->{
            Elim_Materia eliminar=new Elim_Materia(this.clave_usuario);
        });
        
        JBT_editar_materia.setBounds(1150,640,400,60);
        vent.add(JBT_editar_materia);
        JBT_editar_materia.setFont(new java.awt.Font("Arial", 0, 24));
        JBT_editar_materia.addActionListener((ActionEvent e)->{
            Edit_Materia editar_materia =new Edit_Materia(this.clave_usuario);
            editar_materia.reg_materia.addActionListener((ActionEvent o)->{
                editar_materia.editar();
                status.setText("Vista General");
                cons_materia.setText("Consultar Materia");
                ver_tabla_tareas();
                switch_tabla.setSelected(false);
                switch_tabla_materia.setVisible(false);
                switch_tabla.setVisible(true);
            });
        });
        
        reg_tarea.setBounds(1150,240,400,60);
        vent.add(reg_tarea);
        reg_tarea.setFont(new java.awt.Font("Arial", 0, 24));
        reg_tarea.addActionListener((ActionEvent e) -> {
            Reg_Tarea ta = new Reg_Tarea(MainMenu.this.clave_usuario);
            ta.jbtconfirm3.addActionListener((ActionEvent h)->{
                ta.registrar();
                borrar();
                status.setText("Vista General");
                cons_materia.setText("Consultar Materia");
                ver_tabla_tareas();
                switch_tabla.setSelected(false);
                switch_tabla_materia.setVisible(false);
                switch_tabla.setVisible(true);
            });
        });
        reg_clase.setBounds(1150,340,400,60);
        vent.add(reg_clase);
        reg_clase.setFont(new java.awt.Font("Arial", 0, 24));
        reg_clase.addActionListener((ActionEvent e)->{
            registrar_clase();
            status.setText("Vista General");
            cons_materia.setText("Consultar Materia");
            ver_tabla_clases();
            switch_tabla.setSelected(true);
            switch_tabla.setText("Ver tareas");
            switch_tabla_materia.setVisible(false);
            switch_tabla.setVisible(true);
        });
        
        switch_tabla.setBounds(100,80,200,40);
        vent.add(switch_tabla);
        switch_tabla.setFont(new java.awt.Font("Arial", 0, 24));
        switch_tabla.addItemListener((ItemEvent w) -> {
            int estado = w.getStateChange();
            if(estado != ItemEvent.SELECTED){
                ver_tabla_tareas();
                switch_tabla.setText("Ver clases");
                jlbViendo.setText("Tareas");
                String[]fila={"Descripcion","Fecha de Entrega","Materia","Nombre del Profesor"};
                m.setColumnIdentifiers(fila);
            } else {
                ver_tabla_clases();
                switch_tabla.setText("Ver tareas");
                jlbViendo.setText("Clases");
                String fila[]={"Link","Fecha","Hora","Materia","Nombre del Profesor"};
                m.setColumnIdentifiers(fila);
            }
            
        });
        
        switch_tabla_materia.setBounds(100,80,200,40);
        vent.add(switch_tabla_materia);
        switch_tabla_materia.setFont(new java.awt.Font("Arial", 0, 24));
        switch_tabla_materia.addItemListener((ItemEvent h) -> {
            int estado = h.getStateChange();
            if(estado != ItemEvent.SELECTED){
                String[]fila={"Descripcion","Fecha de Entrega","Materia","Nombre del Profesor"};
                m.setColumnIdentifiers(fila);
                tabla=0;
                tabla_tareas(materia_seleccionada_tabla);
                switch_tabla.setText("Ver clases");
                jlbViendo.setText("Tareas");
                
            } else {
                String fila[]={"Link","Fecha","Hora","Materia","Nombre del Profesor"};
                m.setColumnIdentifiers(fila);
                tabla=1;
                tabla_clases(materia_seleccionada_tabla);
                switch_tabla.setText("Ver tareas");
                jlbViendo.setText("Clases");
                
            }
            
        });
        
        borrar_tarea.setBounds(600,760,240,60);
        vent.add(borrar_tarea);
        borrar_tarea.setFont(new java.awt.Font("Arial", 0, 24));
        borrar_tarea.addActionListener((ActionEvent e)->{
            borrar_tarea();
        });
        editar_clase.setBounds(300,760,240,60);
        vent.add(editar_clase);
        editar_clase.setFont(new java.awt.Font("Arial", 0, 24));
        editar_clase.setVisible(false);
        editar_clase.addActionListener((ActionEvent e)->{
            editar_clase();
        });
        editar_tarea.setBounds(300,760,240,60);
        vent.add(editar_tarea);
        editar_tarea.setFont(new java.awt.Font("Arial", 0, 24));
        editar_tarea.addActionListener((ActionEvent e)->{
            editar_tarea();
        });
        
        JBT_borrar_clase.setBounds(600,760,240,60);
        vent.add(JBT_borrar_clase);
        JBT_borrar_clase.setFont(new java.awt.Font("Arial", 0, 24));
        JBT_borrar_clase.setVisible(false);
        JBT_borrar_clase.addActionListener((ActionEvent e)->{
            borrar_clase();
        });
        Runnable r = () -> {
            setTime();
        };
        new Thread(r).start();
        tabla_inicial_recordatorios();     
    }

    private void tabla_inicial_recordatorios() {
        SimpleDateFormat hora_tabla=new SimpleDateFormat("HH:mm");
        SimpleDateFormat a=new SimpleDateFormat("E dd/MM/yy");
        borrar();
        String url="jdbc:mysql://localhost:3306/agendainador";
        String username="root";
        String password=null;
        String driver="com.mysql.jdbc.Driver";
        String cmd;
        String query;
        int clave_clase_leida;
        Date hora_leida;
        try {
            Connection conn = DriverManager.getConnection(url,username,password);
            Statement stmt = conn.createStatement();
            Class.forName(driver);
            int clave_leida;
            String desc_leida;
            String nombre_profesor;
            Date fecha_leida;
            String hoy = a.format(Calendar.getInstance().getTime());
            Date hoy_fecha=Calendar.getInstance().getTime();
            String materia_leida;
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY,12);
            cal.set(Calendar.MINUTE,00);
            cal.set(Calendar.SECOND,0);
            cal.set(Calendar.MILLISECOND,0);

            Date mediodia = cal.getTime();
            cal.set(Calendar.HOUR_OF_DAY,20);
            cal.set(Calendar.MINUTE,00);
            cal.set(Calendar.SECOND,0);
            cal.set(Calendar.MILLISECOND,0);
            Date anochecer=cal.getTime();
            if(hoy_fecha.before(mediodia)){
                bienvenida.setText("Buenos dias, "+nombre_usuario);
            }
            if(hoy_fecha.after(mediodia)&&hoy_fecha.before(anochecer)){
                bienvenida.setText("Buenas tardes, "+nombre_usuario);
            }
            if(hoy_fecha.after(anochecer)){
                bienvenida.setText("Buenas noches, "+nombre_usuario);
            }
            int no_tarea_leido;
            query="SELECT tarea.clave_usuario, tarea.no_tarea, tarea.desc_tarea, tarea.fecha_entrega, materia.nombre_materia, materia.nombre_profesor FROM tarea, materia WHERE tarea.clave_materia=materia.clave_materia ORDER BY fecha_entrega ASC";
            ResultSet rs=stmt.executeQuery(query);
            while(rs.next()){
                clave_leida=rs.getInt("tarea.clave_usuario");
                no_tarea_leido=rs.getInt("tarea.no_tarea");
                desc_leida=rs.getString("tarea.desc_tarea");
                fecha_leida=rs.getDate("tarea.fecha_entrega");
                materia_leida=rs.getString("materia.nombre_materia");
                nombre_profesor=rs.getString("materia.nombre_profesor");
                if(clave_leida==this.clave_usuario){
                    String row[]={desc_leida,a.format(fecha_leida),materia_leida,nombre_profesor};
                    m.addRow(row);
                }
                if(hoy.equals(String.valueOf(a.format(fecha_leida)))&&clave_leida==clave_usuario){
                    JOptionPane.showMessageDialog(null, "Tu tarea de "+materia_leida+" se entrega el día de hoy","Recordatorio",JOptionPane.INFORMATION_MESSAGE);
                }
                if((hoy_fecha).after(fecha_leida)&&!hoy.equals(String.valueOf(a.format(fecha_leida)))&&clave_leida==clave_usuario){
                    int confirm=JOptionPane.showConfirmDialog(frame, "La fecha de entrega de tu tarea de "+materia_leida+" pasó"+"\n"+"¿Deseas eliminarla?", "Tarea caducada",JOptionPane.YES_NO_OPTION);
                    if(confirm==0){
                        Base_Datos borrar_no_tarea=new Base_Datos(this.clave_usuario,no_tarea_leido);
                        borrar_no_tarea.eliminar_tarea_pasada();
                    }
                }
            }
            
            query="SELECT clase.clave_usuario, clase.clave_clase, clase.dia_clase, clase.hora_clase, materia.nombre_materia FROM clase, materia WHERE clase.clave_materia=materia.clave_materia";
            rs=stmt.executeQuery(query);
            
            while(rs.next()){
                int x=0;
                clave_leida=rs.getInt("clase.clave_usuario");
                clave_clase_leida=rs.getInt("clase.clave_clase");
                fecha_leida=rs.getDate("clase.dia_clase");
                hora_leida=rs.getTime("clase.hora_clase");
                materia_leida=rs.getString("materia.nombre_materia");
                
                Date fecha_combinada=combinar_fechas(fecha_leida, hora_leida);
                if(hoy_fecha.after(fecha_combinada)&&clave_usuario==clave_leida){
                    ver_tabla_clases();
                    int confirm=JOptionPane.showConfirmDialog(frame, "Una de tus clases ya pasó"+"\n"+"¿Deseas eliminarla?", "Tarea caducada",JOptionPane.YES_NO_OPTION);
                    if(confirm==0){
                        Base_Datos borrar_clase=new Base_Datos(this.clave_usuario,clave_clase_leida);
                        borrar_clase.eliminar_clase_pasada();
                    }
                    x=1;
                }
                if(hoy.equals(String.valueOf(a.format(fecha_leida)))&&clave_leida==clave_usuario&&x==0){
                        ver_tabla_clases();
                        JOptionPane.showMessageDialog(null, "Tienes una clase el día de hoy de "+materia_leida+" a las "+hora_tabla.format(hora_leida)+"","Recordatorio",JOptionPane.INFORMATION_MESSAGE);
                    } 
            }
            ver_tabla_tareas();
        }   catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }catch(ArrayIndexOutOfBoundsException y) {
        }
    }
    
    private Date combinar_fechas(Date date, Date time){
        Calendar calendarA = Calendar.getInstance();
        calendarA.setTime(date);
        Calendar calendarB = Calendar.getInstance();
        calendarB.setTime(time);
        calendarA.set(Calendar.HOUR_OF_DAY, calendarB.get(Calendar.HOUR_OF_DAY));
        calendarA.set(Calendar.MINUTE, calendarB.get(Calendar.MINUTE));
        calendarA.set(Calendar.SECOND, calendarB.get(Calendar.SECOND));
        calendarA.set(Calendar.MILLISECOND, calendarB.get(Calendar.MILLISECOND));
        Date resultado = calendarA.getTime();
        return resultado;
    }
        
    public void tabla_tareas() {
        SimpleDateFormat a=new SimpleDateFormat("E dd/MM/yy");
        borrar();
        String url="jdbc:mysql://localhost:3306/agendainador";
        String username="root";
        String password=null;
        String driver="com.mysql.jdbc.Driver";
        String cmd;
        String query;
        try {
            Connection conn = DriverManager.getConnection(url,username,password);
            Statement stmt = conn.createStatement();
            Class.forName(driver);
            int clave_leida;
            String desc_leida;
            String nombre_profesor;
            Date fecha_leida;
            String hoy = a.format(Calendar.getInstance().getTime());
            String materia_leida;
            query="SELECT tarea.clave_usuario, tarea.desc_tarea, tarea.fecha_entrega, materia.nombre_materia, materia.nombre_profesor FROM tarea, materia WHERE tarea.clave_materia=materia.clave_materia ORDER BY fecha_entrega ASC";
            ResultSet rs=stmt.executeQuery(query);
            while(rs.next()){
                clave_leida=rs.getInt("tarea.clave_usuario");
                desc_leida=rs.getString("tarea.desc_tarea");
                fecha_leida=rs.getDate("tarea.fecha_entrega");
                materia_leida=rs.getString("materia.nombre_materia");
                nombre_profesor=rs.getString("nombre_profesor");
                if(clave_leida==this.clave_usuario){
                    String row[]={desc_leida,a.format(fecha_leida),materia_leida,nombre_profesor};
                    m.addRow(row);
                }                
            }
        }   catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void tabla_tareas(int materia_seleccionada) {
        
        SimpleDateFormat a=new SimpleDateFormat("E dd/MM/yy");
        borrar();
        String url="jdbc:mysql://localhost:3306/agendainador";
        String username="root";
        String password=null;
        String driver="com.mysql.jdbc.Driver";
        String cmd;
        String query;
        try {
            Connection conn = DriverManager.getConnection(url,username,password);
            Statement stmt = conn.createStatement();
            Class.forName(driver);
            int clave_leida;
            int clave_materia_leida;
            String desc_leida;
            String nombre_profesor;
            Date fecha_leida;
            String hoy = a.format(Calendar.getInstance().getTime());
            String materia_leida;
            int no_tarea_leido;
            query="SELECT tarea.clave_usuario, tarea.desc_tarea, tarea.fecha_entrega, materia.nombre_materia, tarea.clave_materia, materia.nombre_profesor FROM tarea, materia WHERE tarea.clave_materia=materia.clave_materia ORDER BY fecha_entrega ASC";
            ResultSet rs=stmt.executeQuery(query);
            while(rs.next()){
                clave_leida=rs.getInt("tarea.clave_usuario");
                desc_leida=rs.getString("tarea.desc_tarea");
                fecha_leida=rs.getDate("tarea.fecha_entrega");
                materia_leida=rs.getString("materia.nombre_materia");
                clave_materia_leida=rs.getInt("tarea.clave_materia");
                nombre_profesor=rs.getString("nombre_profesor");
                if(clave_leida==this.clave_usuario&&clave_materia_leida==materia_seleccionada){
                    String row[]={desc_leida,a.format(fecha_leida),materia_leida,nombre_profesor};
                    m.addRow(row);
                }                
            }
        }   catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void tabla_clases(){
        SimpleDateFormat a=new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat hora_tabla=new SimpleDateFormat("HH:mm");
        SimpleDateFormat dias=new SimpleDateFormat("E dd/MM/yy");
        borrar();
        String url="jdbc:mysql://localhost:3306/agendainador";
        String username="root";
        String password=null;
        String driver="com.mysql.jdbc.Driver";
        String cmd;
        String query;
        try {
            Connection conn = DriverManager.getConnection(url,username,password);
            Statement stmt = conn.createStatement();
            Class.forName(driver);
            int clave_leida;
            int clave_clase_leida;
            String link;
            Date fecha_leida;
            Date hora_leida;
            String materia_leida;
            String nombre_profesor;
            query="SELECT clase.clave_usuario, clase.clave_clase, materia.link_clase, clase.dia_clase, clase.hora_clase, materia.nombre_materia, materia.nombre_profesor FROM clase, materia WHERE clase.clave_materia=materia.clave_materia";
            ResultSet rs=stmt.executeQuery(query);
            while(rs.next()){
                clave_leida=rs.getInt("clase.clave_usuario");
                clave_clase_leida=rs.getInt("clase.clave_clase");
                link=rs.getString("materia.link_clase");
                fecha_leida=rs.getDate("clase.dia_clase");
                hora_leida=rs.getTime("clase.hora_clase");
                materia_leida=rs.getString("materia.nombre_materia");
                nombre_profesor=rs.getString("nombre_profesor");
                if(clave_leida==this.clave_usuario){
                    String row[]={link,dias.format(fecha_leida),a.format(hora_leida),materia_leida,nombre_profesor};
                    m.addRow(row);
                } else {
                }
            }
        }   catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void tabla_clases(int materia_seleccionada){
        SimpleDateFormat a=new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat hora_tabla=new SimpleDateFormat("HH:mm");
        SimpleDateFormat dias=new SimpleDateFormat("E dd/MM/yy");
        borrar();
        String url="jdbc:mysql://localhost:3306/agendainador";
        String username="root";
        String password=null;
        String driver="com.mysql.jdbc.Driver";
        String cmd;
        String query;
        try {
            Connection conn = DriverManager.getConnection(url,username,password);
            Statement stmt = conn.createStatement();
            Class.forName(driver);
            int clave_leida;
            int clave_clase_leida;
            int clave_materia_leida;
            String desc_leida;
            String link;
            String nombre_profesor;
            Date fecha_leida;
            Date hora_leida;
            String materia_leida;
            query="SELECT clase.clave_usuario, clase.clave_clase, materia.link_clase, clase.dia_clase, clase.hora_clase, materia.nombre_materia, clase.clave_materia, materia.nombre_profesor FROM clase, materia WHERE clase.clave_materia=materia.clave_materia";
            ResultSet rs=stmt.executeQuery(query);
            while(rs.next()){
                clave_leida=rs.getInt("clase.clave_usuario");
                clave_clase_leida=rs.getInt("clase.clave_clase");
                link=rs.getString("materia.link_clase");
                fecha_leida=rs.getDate("clase.dia_clase");
                hora_leida=rs.getTime("clase.hora_clase");
                materia_leida=rs.getString("materia.nombre_materia");
                clave_materia_leida=rs.getInt("clase.clave_materia");
                nombre_profesor=rs.getString("nombre_profesor");
                if(clave_leida==this.clave_usuario&&clave_materia_leida==materia_seleccionada){
                    String fila[]={link,dias.format(fecha_leida),a.format(hora_leida),materia_leida,nombre_profesor};
                    m.addRow(fila);
                } else {
                }
            }
        }   catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void borrar() {
        int p=pendientes.getRowCount();
        for(int j=p-1;j>=0;j--){
            m.removeRow(j);    
        }
    }

    private void borrar_tarea() {
        try {
            String desc=String.valueOf(m.getValueAt(pendientes.getSelectedRow(), pendientes.getSelectedColumn()));
            String fecha_string=String.valueOf(m.getValueAt(pendientes.getSelectedRow(), pendientes.getSelectedColumn()+1));
            Date fecha=new SimpleDateFormat("E dd/MM/yy").parse(fecha_string);
            java.sql.Date fecha_entrega=new java.sql.Date(fecha.getTime());
            String materia=String.valueOf(m.getValueAt(pendientes.getSelectedRow(), pendientes.getSelectedColumn()+2));
            int confirm=JOptionPane.showConfirmDialog(frame, "¿Estas seguro que quieres eliminar la tarea?", "Confirmar eliminar tarea",JOptionPane.YES_NO_OPTION);
            if(confirm==0){
                JOptionPane.showMessageDialog(null, "La tarea ha sido borrada","Borrar Tarea",JOptionPane.INFORMATION_MESSAGE);
                Base_Datos borrar_no_tarea=new Base_Datos(clave_usuario,materia,desc,fecha_entrega);
                borrar_no_tarea.obtener_clave_materia();
                borrar_no_tarea.eliminar_tarea();
                borrar();
                ver_tabla_tareas();
            }else{
                JOptionPane.showMessageDialog(null, "Operacion cancelada","Borrar Tarea",JOptionPane.INFORMATION_MESSAGE);  
            }
        } catch (ParseException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void editar_tarea() {
        try{
            String desc=String.valueOf(m.getValueAt(pendientes.getSelectedRow(), pendientes.getSelectedColumn()));
            String fecha_string=String.valueOf(m.getValueAt(pendientes.getSelectedRow(), pendientes.getSelectedColumn()+1));
            Date fecha=new SimpleDateFormat("E dd/MM/yy").parse(fecha_string);
            String materia=String.valueOf(m.getValueAt(pendientes.getSelectedRow(), pendientes.getSelectedColumn()+2));
            Edit_Tarea edit=new Edit_Tarea(this.clave_usuario,desc,fecha,materia);
            edit.jbtconfirm3.addActionListener((ActionEvent e)->{
                edit.editar();
                borrar();
                ver_tabla_tareas();
            });
        }catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Selecciona la primera celda de la fila","Error",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void registrar_clase() {
            java.awt.EventQueue.invokeLater(() -> {
                Reg_Clase g=new Reg_Clase(clave_usuario);
                g.setVisible(true);
                g.jButton1.addActionListener((ActionEvent h)->{
                    g.registrar();
                    borrar();
                    ver_tabla_clases();
                });
            });
    }

    private void borrar_clase() {
        try {
            String link=String.valueOf(m.getValueAt(pendientes.getSelectedRow(), pendientes.getSelectedColumn()));
            String fecha_string=String.valueOf(m.getValueAt(pendientes.getSelectedRow(), pendientes.getSelectedColumn()+1));
            Date fecha=new SimpleDateFormat("E dd/MM/yy").parse(fecha_string);
            java.sql.Date fecha_clase=new java.sql.Date(fecha.getTime());
            String hora_string=String.valueOf(m.getValueAt(pendientes.getSelectedRow(), pendientes.getSelectedColumn()+2));
            Date hora=new SimpleDateFormat("HH:mm").parse(hora_string);
            java.sql.Time hora_clase=new java.sql.Time(hora.getTime());
            String materia=String.valueOf(m.getValueAt(pendientes.getSelectedRow(), pendientes.getSelectedColumn()+3));
            int confirm=JOptionPane.showConfirmDialog(frame, "¿Estas seguro que quieres eliminar la clase?", "Confirmar eliminar clase",JOptionPane.YES_NO_OPTION);
            if(confirm==0){
                JOptionPane.showMessageDialog(null, "La clase ha sido borrada","Borrar Clase",JOptionPane.INFORMATION_MESSAGE);
                Base_Datos borrar_clase=new Base_Datos(this.clave_usuario,fecha_clase,hora_clase,materia);
                borrar_clase.obtener_clave_materia();
                borrar_clase.eliminar_clase();
                status.setText("Viendo General");
                cons_materia.setText("Consultar Materia");
                ver_tabla_clases();
                switch_tabla_materia.setVisible(false);
                switch_tabla.setVisible(true);
            }else{
                JOptionPane.showMessageDialog(null, "Operacion cancelada","Borrar Clase",JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Selecciona la primera celda de la fila","Error",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void editar_clase() {
        try{
            
            String link=String.valueOf(m.getValueAt(pendientes.getSelectedRow(), pendientes.getSelectedColumn()));
            String fecha_string=String.valueOf(m.getValueAt(pendientes.getSelectedRow(), pendientes.getSelectedColumn()+1));
            Date fecha=new SimpleDateFormat("E dd/MM/yy").parse(fecha_string);
            String hora_string=String.valueOf(m.getValueAt(pendientes.getSelectedRow(), pendientes.getSelectedColumn()+2));
            Date hora=new SimpleDateFormat("HH:mm").parse(hora_string);
            String materia=String.valueOf(m.getValueAt(pendientes.getSelectedRow(), pendientes.getSelectedColumn()+3));
            Edit_Clase s=new Edit_Clase(clave_usuario,link,fecha,hora,materia);
            s.jButton1.addActionListener((ActionEvent e)->{
                s.editar();
                status.setText("Viendo General");
                cons_materia.setText("Consultar Materia");
                switch_tabla_materia.setVisible(false);
                switch_tabla.setVisible(true);
                switch_tabla.setSelected(true);
                ver_tabla_clases();
            });
        }catch(ArrayIndexOutOfBoundsException | NumberFormatException j){
            JOptionPane.showMessageDialog(null, "Selecciona el # de la tarea a editar en la tabla","Error",JOptionPane.INFORMATION_MESSAGE);
        } catch (ParseException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ver_tabla_tareas() {
        jlbViendo.setText("Tareas");
        tabla=0;
        borrar();
        String[]fila={"Descripcion","Fecha de Entrega","Materia","Nombre del Profesor"};
        m.setColumnIdentifiers(fila);
        
        editar_tarea.setVisible(true);
        borrar_tarea.setVisible(true);
        
        editar_clase.setVisible(false);
        JBT_borrar_clase.setVisible(false);
        
       
        tabla_tareas();
    }

    public void ver_tabla_clases() {
        jlbViendo.setText("Clases");
        
        tabla=1;
        borrar();
        String fila[]={"Link","Fecha","Hora","Materia","Nombre del Profesor"};
        m.setColumnIdentifiers(fila);
        
        editar_tarea.setVisible(false);
        borrar_tarea.setVisible(false);
        
        editar_clase.setVisible(true);
        JBT_borrar_clase.setVisible(true);
        
       
        tabla_clases();
    }
    
    public void setTime() {
        Calendar calendar;
        SimpleDateFormat timeFormat;
        SimpleDateFormat dayFormat;
        SimpleDateFormat dateFormat;
        String time;
        String day;
        String date;
        timeFormat = new SimpleDateFormat("hh:mm:ss a");
        dayFormat = new SimpleDateFormat("EEEE");
        dateFormat = new SimpleDateFormat("MM/dd/yy");

        while(true) {
            time = timeFormat.format(Calendar.getInstance().getTime());
            JLB_hora.setText(time);
            day = dayFormat.format(Calendar.getInstance().getTime());
            date = dateFormat.format(Calendar.getInstance().getTime());
            JLB_fecha.setText(String.valueOf(day)+" "+String.valueOf(date));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}