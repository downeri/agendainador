package AgendaInador;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Edit_Materia {
    
    JFrame frame=new JFrame();
    JDesktopPane vent = new JDesktopPane();
    JLabel jlb7 = new JLabel("Nombre de la materia");//Se crea una nueva etiqueta con un texto escrito
    JLabel jlb8 = new JLabel("Nombre del maestro");//Se crea una nueva etiqueta con un texto escrito
    JLabel jlblink=new JLabel("Link para reuniones");
    JDateChooser calendario=new JDateChooser();
    JComboBox jtf4 = new JComboBox();//Se coloca un text Field para dar respuesta a las instrucciones del labe7
    JTextField jtf5 = new JTextField();//Se coloca un text Field para dar respuesta a las instrucciones del labe8
    JTextField jtf6 = new JTextField();//Se coloca un text Field para dar respuesta a las instrucciones del labe8
    JButton reg_materia = new JButton("Confirmar");//Se crea el boton Confirmar
    ImageIcon img=new ImageIcon("AgendaInadorIcon.png");
    int clave_usuario;
    int clave_materia;
    
    public Edit_Materia(int clave_usuario){
        
        this.clave_usuario=clave_usuario;
        frame.setTitle("Registrar Clase");
        frame.setSize(1100,600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(vent);
        frame.setVisible(true);
        frame.setIconImage(img.getImage());
        Color aeiou=new Color(240,240,240);
        vent.setBackground(aeiou);
        
        jlb7.setBounds(50,20,800,60);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada a JL9
        jlb7.setFont(new java.awt.Font("Arial", 0, 24));
        jtf4.setBounds(660,20,340,60);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada a JTF6
        jtf4.setFont(new java.awt.Font("Arial", 0, 24));
        jlb8.setBounds(50,100,800,60);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada a JTL10
        jlb8.setFont(new java.awt.Font("Arial", 0, 24));
        jlblink.setBounds(50,180,800,60);
        jlblink.setFont(new java.awt.Font("Arial", 0, 24));
        jtf5.setBounds(660,100,340,60);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada a JTF7
        jtf5.setFont(new java.awt.Font("Arial", 0, 24));
        jtf6.setBounds(660,180,340,60);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada a JTF7
        jtf6.setFont(new java.awt.Font("Arial", 0, 24));
        reg_materia.setBounds(300,420,300,60);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada a JBconfirl
        reg_materia.setFont(new java.awt.Font("Arial", 0, 24));
        vent.add(jlb7);//Añadir en la pantalla
        vent.add(jlb8);//Añadir en la pantalla
        vent.add(jtf4);//Añadir en la pantalla
        vent.add(jtf5);//Añadir en la pantalla
        vent.add(jlblink);
        vent.add(jtf6);
        vent.add(reg_materia);//Añadir en la pantalla
        jtf4.addActionListener((ActionEvent o)->{
            Base_Datos nombres_materia=new Base_Datos(clave_usuario);
            nombres_materia.iniBase_Datos();
            try {
                nombres_materia.query="SELECT clave_usuario, clave_materia, nombre_materia, nombre_profesor, link_clase FROM materia";
                nombres_materia.rs=nombres_materia.stmt.executeQuery(nombres_materia.query);
                while(nombres_materia.rs.next()){
                    int clave_usuario_leida=nombres_materia.rs.getInt("clave_usuario");
                    int clave_materia_leida=nombres_materia.rs.getInt("clave_materia");
                    String nombre_materia=nombres_materia.rs.getString("nombre_materia");
                    String nombre_profesor=nombres_materia.rs.getString("nombre_profesor");
                    String link_clase=nombres_materia.rs.getString("link_clase");
                    if(clave_usuario_leida==this.clave_usuario&&nombre_materia.equals(jtf4.getSelectedItem())){
                        this.clave_materia=clave_materia_leida;
                        jtf5.setText(nombre_profesor);
                        jtf6.setText(link_clase);
                    }
                } 
            }   catch (SQLException ex) {
                Logger.getLogger(Base_Datos.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        leermateria();
        
        reg_materia.setEnabled(false);
        Runnable r = () -> {
            habilitar_boton();
        };
        new Thread(r).start();
    }

    public void editar() {
        String materia=String.valueOf(jtf4.getSelectedItem());
        String profesor=jtf5.getText();
        String link=jtf6.getText();
        if(!materia.isEmpty()||!profesor.isEmpty()){
            Base_Datos regi_materia=new Base_Datos(materia,profesor,clave_usuario,link,clave_materia);
            regi_materia.editar_materia();
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
                    jtf4.addItem(materia_leida);
                }
            } 
        }   catch (SQLException ex) {
            Logger.getLogger(Base_Datos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void habilitar_boton(){
        while(true){
            if(!jtf5.getText().isEmpty()&&!jtf6.getText().isEmpty()){
                reg_materia.setEnabled(true);
            }else{
                reg_materia.setEnabled(false);
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}