package AgendaInador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login {
    
    JLabel jlb4 = new JLabel("Nombre del usuario");//Se crea una nueva etiqueta con un texto escrito
    JLabel jlb6 = new JLabel("Contraseña");//Se crea una nueva etiqueta con un texto escrito
    JTextField jtf1 = new JTextField();//Se coloca un text Field para dar respuesta a las instrucciones del labe4
    JPasswordField pass_field = new JPasswordField();//Se coloca un text Field para dar respuesta a las instrucciones del labe6
    JButton jbtreg = new JButton("Confirmar");//Se crea el boton Confirmar
    JButton back = new JButton("Back");//Se crea el boton Confirmar
    JFrame frame=new JFrame();
    JDesktopPane vent = new JDesktopPane();
    JCheckBox cb_recordar=new JCheckBox("Mantener sesión iniciada");
    ImageIcon img=new ImageIcon("AgendaInadorIcon.png");
    
    public Login(){
        
        frame.setTitle("Login");
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
        jtf1.setBounds(490,100,380,60);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada 
        jtf1.setFont(new java.awt.Font("Arial", 0, 24));
        cb_recordar.setBounds(50,340,380,60);
        cb_recordar.setFont(new java.awt.Font("Arial", 0, 24));
        jlb6.setBounds(50,260,800,60);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada 
        jlb6.setFont(new java.awt.Font("Arial", 0, 24));
        pass_field.setBounds(490,260,380,60);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada
        pass_field.setFont(new java.awt.Font("Arial", 0, 24));
        jbtreg.setBounds(300,420,300,60);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada a JBconfirl
        jbtreg.setFont(new java.awt.Font("Arial", 0, 24));
        back.setBounds(4,460,140,60);
        back.setFont(new java.awt.Font("Arial", 0, 24));
        back.addActionListener((ActionEvent e)->{
            Proyecto n=new Proyecto();
            frame.dispose();
        });
        vent.add(back);
        vent.add(cb_recordar);
        vent.add(jlb4);//Añadir en la pantalla
        vent.add(jlb6);//Añadir en la pantalla
        vent.add(jtf1);//Añadir en la pantalla
        vent.add(pass_field);//Añadir en la pantalla
        vent.add(jbtreg);
        jbtreg.setEnabled(false);
        jbtreg.addActionListener((ActionEvent e) -> {
            login();
        });
        
        Runnable r = new Runnable() {
            public void run() {
                habilitar_boton();
            }
        };
        new Thread(r).start();
    }

    private void login() {
        
        String reg_user=jtf1.getText();
        String reg_pass=pass_field.getText();
        Base_Datos login=new Base_Datos(reg_user,reg_pass);
        boolean x=login.leerdbacceder();
        if(x==true){
            frame.dispose();
            MainMenu m=new MainMenu(login.getClave_usuario(),reg_user);
            if(cb_recordar.isSelected()){
                try (FileWriter escribir = new FileWriter("E:\\usuario_recordado.txt")) {
                    escribir.write(reg_user+"-"+reg_pass);
                    escribir.close();
                }catch (IOException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }if(x==false){
            JOptionPane.showMessageDialog(null, "Usuario no encontrado","Error de datos",JOptionPane.WARNING_MESSAGE);
            frame.dispose();
            Proyecto s=new Proyecto();
        }
    }
    
    private void habilitar_boton(){
        while(true){
            if(!jtf1.getText().isEmpty()&&!pass_field.getText().isEmpty()){
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