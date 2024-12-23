package AgendaInador;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Reg_Tarea {
    
    int clave_usuario;
    JFrame frame=new JFrame();
    JDesktopPane vent = new JDesktopPane();
    JLabel jlb11 = new JLabel("Nombre de la Materia");//Se crea una nueva etiqueta con un texto escrito
    JLabel jlb12 = new JLabel("");//Se crea una nueva etiqueta 
    JLabel jlb13 = new JLabel("Descripción");//Se crea una nueva etiqueta con un texto escrito
    JLabel jlb14 = new JLabel("Fecha de entrega");//Se crea una nueva etiqueta con un texto escrito
    JLabel jlb15 = new JLabel("Hora de entrega");//Se crea una nueva etiqueta con un texto escrito
    JLabel jlb10 = new JLabel("");//Se crea una nueva etiqueta 
    JTextArea jtf_desc_materia = new JTextArea();//Se coloca un text Field para dar respuesta a las instrucciones del labe13
    JComboBox jtf_nombre_materia = new JComboBox();//Se coloca un text Field para dar respuesta a las instrucciones del labe13
    JButton jbtconfirm3 = new JButton("Confirmar");//Se crea el boton Confirmar
    JDateChooser calendario=new JDateChooser();
    ImageIcon img=new ImageIcon("AgendaInadorIcon.png");
    
    public Reg_Tarea(int clave_usuario){
        
        this.clave_usuario=clave_usuario;
        frame.setTitle("Registrar Tarea");
        frame.setSize(1800,600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(vent);
        frame.setVisible(true);
        frame.setIconImage(img.getImage());
        Color aeiou=new Color(240,240,240);
        vent.setBackground(aeiou);
        
        jlb10.setBounds(700,180,300,60);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada a JTF8
        jlb10.setFont(new java.awt.Font("Arial", 0, 24));
        jlb11.setBounds(50,340,300,60);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada a JBconfir2
        jlb11.setFont(new java.awt.Font("Arial", 0, 24));
        jtf_nombre_materia.setBounds(490,340,400,60);
        jtf_nombre_materia.setFont(new java.awt.Font("Arial", 0, 24));
        jlb12.setBounds(380,260,200,60);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada a JL12
        jlb12.setFont(new java.awt.Font("Arial", 0, 24));
        jlb13.setBounds(50,20,200,60);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada
        jlb13.setFont(new java.awt.Font("Arial", 0, 24));
        jtf_desc_materia.setBounds(480,20,1000,200);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada 
        jtf_desc_materia.setFont(new java.awt.Font("Arial", 0, 24));
        jlb14.setBounds(50,240,200,60);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada 
        jlb14.setFont(new java.awt.Font("Arial", 0, 24));
        calendario.setBounds(490,240,380,60);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada 
        calendario.setFont(new java.awt.Font("Arial", 0, 24));
        jlb15.setBounds(50,180,200,60);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada 
        jlb15.setFont(new java.awt.Font("Arial", 0, 24));
        jtf_desc_materia.setBorder(new javax.swing.border.EtchedBorder());
        jtf_desc_materia.setFont(new java.awt.Font("Arial", 0, 24));
        jbtconfirm3.setBounds(750,450,300,60);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada a JBconfirl
        jbtconfirm3.setFont(new java.awt.Font("Arial", 0, 24));
        vent.add(calendario);
        vent.add(jtf_nombre_materia);
        vent.add(jlb10);//Añadir en la pantalla
        vent.add(jlb11);//Añadir en la pantalla
        vent.add(jlb12);//Añadir en la pantalla
        vent.add(jlb13);//Añadir en la pantalla
        vent.add(jlb14);//Añadir en la pantalla
        vent.add(jtf_desc_materia);//Añadir en la pantalla
        vent.add(jbtconfirm3);//Añadir en la pantalla
        jbtconfirm3.setEnabled(false);
        leermateria();
        Runnable r = () -> {
            habilitar_boton();
        };
        new Thread(r).start();
    }
    
    public void registrar() {
        String desc_tarea=jtf_desc_materia.getText();
        Date dia=calendario.getDate();
        String nombre_materia=String.valueOf(jtf_nombre_materia.getSelectedItem());
        if(desc_tarea!=null||dia!=null||nombre_materia!=null){
            java.sql.Date fecha_entrega=new java.sql.Date(dia.getTime());
            Base_Datos tarea=new Base_Datos(clave_usuario,nombre_materia,desc_tarea,fecha_entrega);
            tarea.obtener_clave_materia();
            tarea.reg_tarea();
            frame.dispose();
            
        }else{
            JOptionPane.showMessageDialog(null, "Por favor coloca los datos","Error de datos",JOptionPane.WARNING_MESSAGE);
        }
    }
    private void leermateria() {
        Base_Datos nombres_materia=new Base_Datos(clave_usuario);
         String materia_leida;
         nombres_materia.iniBase_Datos();
        try {
            nombres_materia.query="SELECT clave_usuario, nombre_materia FROM materia";
            nombres_materia.rs=nombres_materia.stmt.executeQuery(nombres_materia.query);
            while(nombres_materia.rs.next()){
                int clave_usuario_leida=nombres_materia.rs.getInt("clave_usuario");
                materia_leida=nombres_materia.rs.getString("nombre_materia");
                System.out.print(materia_leida);
                if(clave_usuario_leida==clave_usuario){
                    jtf_nombre_materia.addItem(materia_leida);
                }
            } 
        }   catch (SQLException ex) {
            Logger.getLogger(Base_Datos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void habilitar_boton(){
        while(true){
            try {
                Date dia=calendario.getDate();
                String nombre_materia=String.valueOf(jtf_nombre_materia.getSelectedItem());
                if(jtf_desc_materia.getText().isEmpty()||dia==null||nombre_materia==null){
                    jbtconfirm3.setEnabled(false);
                }else{
                    jbtconfirm3.setEnabled(true);
                }
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Reg_Tarea.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}