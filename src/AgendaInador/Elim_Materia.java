package AgendaInador;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class Elim_Materia {
    JFrame frame = new JFrame("Eliminar Materia");//Se crea un nuevo jframe con la leyeynda "menu"
    JDesktopPane vent = new JDesktopPane(); //Se crea un nuevo JDeskPanel
    JLabel jlb1 = new JLabel("Selecciona la materia a eliminar");//Se crea una nueva etiqueta con un texto escrito                             
    JComboBox lista_materias = new JComboBox(); 
    JButton jbtconfirmar = new JButton("Eliminar");//Se crea el boton Acceder
    int clave_usuario;
    
    public Elim_Materia(int clave_usuario){
        this.clave_usuario=clave_usuario;
        frame.add(vent);//se añade el des
        frame.setResizable(false);
        frame.setSize(800,400);//Se le coloca un tamaño a el frame
        frame.setLocationRelativeTo(null);//Se coloca una locacion del frame
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//saca una operacion para cuando se cierre
        frame.setVisible(true);//Se hace visible el frame
        
        vent.add(jlb1);//Añadir en la pantalla
        jlb1.setBounds(220,20,1000,60);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada
        lista_materias.setBounds(190,100,400,60);
        vent.add(lista_materias);
        jbtconfirmar.setBounds(190,260,400,60);
        vent.add(jbtconfirmar);
        jbtconfirmar.addActionListener((ActionEvent e)-> {
            
        Font letra1 = new java.awt.Font("Dialog", java.awt.Font.BOLD, 24);
        jlb1.setFont(new java.awt.Font("Arial", 0, 24));
        lista_materias.setFont(new java.awt.Font("Arial", 0, 24));
        jbtconfirmar.setFont(new java.awt.Font("Arial", 0, 24));
            eliminar();
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

    private void eliminar() {
        int confirm=JOptionPane.showConfirmDialog(frame, "¿Estas seguro que quieres eliminar la materia "+lista_materias.getSelectedItem()+"?", "Confirmar eliminar materia",JOptionPane.YES_NO_OPTION);
            if(confirm==0){
                int confirm2=JOptionPane.showConfirmDialog(frame, "¿Estas realmente seguro?"+"\n"+"Perderás todas las tareas y clases de esa materia y no se pueden recuperar", "Confirmar eliminar clase",JOptionPane.YES_NO_OPTION);
                    if(confirm2==0){
                        String materia_a_matar=String.valueOf(lista_materias.getSelectedItem());
                        Base_Datos borrar_clase=new Base_Datos(this.clave_usuario,materia_a_matar);
                        borrar_clase.obtener_clave_materia();
                        borrar_clase.eliminar_materia();
                        frame.dispose();
                    }else{
                        JOptionPane.showMessageDialog(null, "Operacion cancelada","Borrar Clase",JOptionPane.INFORMATION_MESSAGE);
                    }       
            }else{
                JOptionPane.showMessageDialog(null, "Operacion cancelada","Borrar Clase",JOptionPane.INFORMATION_MESSAGE);
            }
    }
}