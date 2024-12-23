package AgendaInador;

import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Selec_Materia{
    JFrame frame = new JFrame("Mostrar Materia");//Se crea un nuevo jframe con la leyeynda "menu"
    JDesktopPane vent = new JDesktopPane(); //Se crea un nuevo JDeskPanel
    JLabel jlb1 = new JLabel("Selecciona la materia para mostrar");//Se crea una nueva etiqueta con un texto escrito                             
    JComboBox lista_materias = new JComboBox(); 
    JButton jbtconfirmar = new JButton("Aceptar");//Se crea el boton Acceder
    int clave_usuario;
  
    
    public Selec_Materia(int clave_usuario){
        this.clave_usuario=clave_usuario;
        frame.add(vent);//se añade el des
        frame.setResizable(false);
        frame.setSize(800,400);//Se le coloca un tamaño a el frame
        frame.setLocationRelativeTo(null);//Se coloca una locacion del frame
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//saca una operacion para cuando se cierre
        frame.setVisible(true);//Se hace visible el frame
        
        vent.add(jlb1);//Añadir en la pantalla
        jlb1.setBounds(220,20,1000,60);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada
        jlb1.setFont(new java.awt.Font("Arial", 0, 24));
        lista_materias.setBounds(190,100,400,60);
        lista_materias.setFont(new java.awt.Font("Arial", 0, 24));
        vent.add(lista_materias);
        jbtconfirmar.setBounds(190,260,400,60);
        jbtconfirmar.setFont(new java.awt.Font("Arial", 0, 24));
        vent.add(jbtconfirmar);
        jbtconfirmar.addActionListener((ActionEvent e)-> {
            frame.setVisible(false);
        });
        leermateria();
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
                    lista_materias.addItem(materia_leida);
                }
            } 
        }   catch (SQLException ex) {
            Logger.getLogger(Base_Datos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int selec_materia() {
            String materia=String.valueOf(lista_materias.getSelectedItem());
            Base_Datos clave_materia_leer=new Base_Datos(clave_usuario,materia);
            clave_materia_leer.obtener_clave_materia();
            return clave_materia_leer.clave_materia;
    }
    public String getNombreProfesor(int materia_a_saber){
        Base_Datos nombres_materia=new Base_Datos(clave_usuario);
        int materia_leida;
        nombres_materia.iniBase_Datos();
        try {
            nombres_materia.query="SELECT clave_usuario, clave_materia, nombre_profesor FROM materia";
            nombres_materia.rs=nombres_materia.stmt.executeQuery(nombres_materia.query);
            while(nombres_materia.rs.next()){
                int clave_usuario_leida=nombres_materia.rs.getInt("clave_usuario");
                materia_leida=nombres_materia.rs.getInt("clave_materia");
                String profesor=nombres_materia.rs.getString("nombre_profesor");
                System.out.print(materia_leida);
                if(clave_usuario_leida==clave_usuario&&materia_leida==materia_a_saber){
                    return profesor;
                }
            }
        }   catch (SQLException ex) {
            Logger.getLogger(Base_Datos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String getNombreMateria(int materia_a_saber){
        Base_Datos nombres_materia=new Base_Datos(clave_usuario);
        int materia_leida;
        nombres_materia.iniBase_Datos();
        try {
            nombres_materia.query="SELECT clave_usuario, clave_materia, nombre_materia FROM materia";
            nombres_materia.rs=nombres_materia.stmt.executeQuery(nombres_materia.query);
            while(nombres_materia.rs.next()){
                int clave_usuario_leida=nombres_materia.rs.getInt("clave_usuario");
                materia_leida=nombres_materia.rs.getInt("clave_materia");
                String nombre_materia=nombres_materia.rs.getString("nombre_materia");
                System.out.print(materia_leida);
                if(clave_usuario_leida==clave_usuario&&materia_leida==materia_a_saber){
                    return nombre_materia;
                }
            }
        }   catch (SQLException ex) {
            Logger.getLogger(Base_Datos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public boolean isSeleccionado(){
        return isSeleccionado();
    }
}