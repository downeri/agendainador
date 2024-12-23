package AgendaInador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Registrar_Usuario {
    
    JLabel jlb2 = new JLabel("Confirmar contraseña");//Se crea una nueva etiqueta con un texto escrito
    JLabel jlb4 = new JLabel("Nombre del usuario");//Se crea una nueva etiqueta con un texto escrito
    JLabel jlb6 = new JLabel("Crea una contraseña");//Se crea una nueva etiqueta con un texto escrito
    JTextField jtf_nombre_usuario_reg = new JTextField();//Se coloca un text Field para dar respuesta a las instrucciones del labe4
    JPasswordField jpf_pass_usuario_reg = new JPasswordField();//Se coloca un text Field para dar respuesta a las instrucciones del labe6
    JPasswordField jtf_confirm_pass_usuario_reg= new JPasswordField();
    JButton jbtreg = new JButton("Confirmar");//Se crea el boton Confirmar
    JButton back = new JButton("Back");//Se crea el boton Confirmar
    JFrame frame=new JFrame();
    JDesktopPane vent = new JDesktopPane();
    ImageIcon img=new ImageIcon("AgendaInadorIcon.png");
    
    public Registrar_Usuario(){
        
        frame.setTitle("Registrar");
        frame.setSize(1000,600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(vent);
        frame.setVisible(true);
        frame.setIconImage(img.getImage());
        Color aeiou=new Color(240,240,240);
        vent.setBackground(aeiou);
        
        jlb4.setBounds(50,100,800,60);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada 
        jlb4.setFont(new java.awt.Font("Arial", 0, 24));
        jtf_nombre_usuario_reg.setBounds(490,100,380,60);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada
        jtf_nombre_usuario_reg.setFont(new java.awt.Font("Arial", 0, 24));
        
        jlb6.setBounds(50,200,800,60);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada 
        jlb6.setFont(new java.awt.Font("Arial", 0, 24));
        jpf_pass_usuario_reg.setBounds(490,200,380,60);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada
        jpf_pass_usuario_reg.setFont(new java.awt.Font("Arial", 0, 24));
        
        jlb2.setBounds(50,300,800,60);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada
        jlb2.setFont(new java.awt.Font("Arial", 0, 24));
        jtf_confirm_pass_usuario_reg.setBounds(490,300,380,60);
        jtf_confirm_pass_usuario_reg.setFont(new java.awt.Font("Arial", 0, 24));
        
        jbtreg.setBounds(300,420,300,60);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada a JBconfirl
        jbtreg.setFont(new java.awt.Font("Arial", 0, 24));
        back.setBounds(8,460,140,60);
        back.setFont(new java.awt.Font("Arial", 0, 24));
        back.addActionListener((ActionEvent e)->{
            Proyecto n=new Proyecto();
            frame.dispose();
        });
        vent.add(back);
        vent.add(jtf_confirm_pass_usuario_reg);
        vent.add(jlb2);//Añadir en la pantalla
        vent.add(jlb4);//Añadir en la pantalla
        vent.add(jlb6);//Añadir en la pantalla
        vent.add(jtf_nombre_usuario_reg);//Añadir en la pantalla
        vent.add(jpf_pass_usuario_reg);//Añadir en la pantalla
        vent.add(jbtreg);
        jbtreg.addActionListener((ActionEvent e) -> {
            registrar();
        });
        jbtreg.setEnabled(false);
        Runnable r = () -> {
            habilitar_boton();
        };
        new Thread(r).start();
    }

    private void registrar() {
        String reg_user=jtf_nombre_usuario_reg.getText();
        String reg_pass=jpf_pass_usuario_reg.getText();
        String conf_pass=jtf_confirm_pass_usuario_reg.getText();
        Base_Datos alumno=new Base_Datos(reg_user,reg_pass);
        boolean se_repite=alumno.usuario_repetido();
        if(reg_pass.equals(conf_pass)){
            if(se_repite==false){
                alumno.registrar_usuario();
                frame.dispose();
                Base_Datos login=new Base_Datos(reg_user,reg_pass);
                login.leerdbacceder();
                MainMenu m=new MainMenu(login.getClave_usuario(),reg_user);
                try (FileWriter escribir = new FileWriter("E:\\usuario_recordado.txt")) {
                    escribir.write(reg_user+"-"+reg_pass);
                    escribir.close();
                }catch (IOException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(null, "Para editar o eliminar una tarea o una clase, primero seleccione la primera columna de la fila y luego utiliza los botones en la parte inferior de la tabla."+"\n"+"\n"+"Para ver la descripción completa de una tarea, de doble click en la descipción de la misma."+"\n"+"\n"+"De la misma forma, puede dar doble click al link de una clase para abrilo en su navegador de preferencia."+"\n","Uso de la tabla",JOptionPane.INFORMATION_MESSAGE);
                JOptionPane.showMessageDialog(null, "Su sesión quedará abierta al salir del programa, si desea cerrarla, utilice el botón en la parte inferior izquierda"+"\n"+"\n"+"¡Gracias por usar La AgendaInador!","Información Importante",JOptionPane.INFORMATION_MESSAGE); 
            }else{
                JOptionPane.showMessageDialog(null, "Ese nombre de usuario ya existe, por favor, elije otro","Usuario ya existe",JOptionPane.WARNING_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "La contraseña no coincide","Error de datos",JOptionPane.WARNING_MESSAGE);
        }

    } 
    public void habilitar_boton(){
        while(true){
            if(!jtf_nombre_usuario_reg.getText().isEmpty()&&!jpf_pass_usuario_reg.getText().isEmpty()&&!jtf_confirm_pass_usuario_reg.getText().isEmpty()){
                jbtreg.setEnabled(true);
            }else{
                jbtreg.setEnabled(false);
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}