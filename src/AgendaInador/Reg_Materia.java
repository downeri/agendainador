package AgendaInador;

import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Reg_Materia {
    
    JFrame frame=new JFrame();
    JDesktopPane vent = new JDesktopPane();
    JLabel jlb7 = new JLabel("Nombre de la materia");//Se crea una nueva etiqueta con un texto escrito
    JLabel jlb8 = new JLabel("Nombre del maestro");//Se crea una nueva etiqueta con un texto escrito
    JLabel jlblink=new JLabel("Link para reuniones");
    JDateChooser calendario=new JDateChooser();
    JTextField jtf_materia = new JTextField();//Se coloca un text Field para dar respuesta a las instrucciones del labe7
    JTextField jtf_profesor = new JTextField();//Se coloca un text Field para dar respuesta a las instrucciones del labe8
    JTextField jtf_link = new JTextField();//Se coloca un text Field para dar respuesta a las instrucciones del labe8
    JButton reg_materia = new JButton("Confirmar");//Se crea el boton Confirmar
    ImageIcon img=new ImageIcon("AgendaInadorIcon.png");
    int clave_usuario;
    
    public Reg_Materia(int clave_usuario){
        
        this.clave_usuario=clave_usuario;
        frame.setTitle("Registrar Materia");
        frame.setSize(1100,600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(vent);
        frame.setVisible(true);
        frame.setIconImage(img.getImage());
        jlb7.setBounds(50,20,800,60);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada a JL9
        jlb7.setFont(new java.awt.Font("Arial", 0, 24));
        jtf_materia.setBounds(660,20,340,60);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada a JTF6
        jtf_materia.setFont(new java.awt.Font("Arial", 0, 24));
        jlb8.setBounds(50,100,800,60);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada a JTL10
        jlb8.setFont(new java.awt.Font("Arial", 0, 24));
        jlblink.setBounds(50,180,800,60);
        jlblink.setFont(new java.awt.Font("Arial", 0, 24));
        jtf_profesor.setBounds(660,100,340,60);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada a JTF7
        jtf_profesor.setFont(new java.awt.Font("Arial", 0, 24));
        jtf_link.setBounds(660,180,340,60);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada a JTF7
        jtf_link.setFont(new java.awt.Font("Arial", 0, 24));
        reg_materia.setBounds(300,420,300,60);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada a JBconfirl
        reg_materia.setFont(new java.awt.Font("Arial", 0, 24));
        vent.add(jlb7);//Añadir en la pantalla
        vent.add(jlb8);//Añadir en la pantalla
        vent.add(jtf_materia);//Añadir en la pantalla
        vent.add(jtf_profesor);//Añadir en la pantalla
        vent.add(jlblink);
        vent.add(jtf_link);
        vent.add(reg_materia);//Añadir en la pantalla
        reg_materia.addActionListener((ActionEvent e)->{
            registrar();
        });
        reg_materia.setEnabled(false);
        Runnable r = () -> {
            habilitar_boton();
        };
        new Thread(r).start();
    }

    private void registrar() {
        String materia=jtf_materia.getText();
        String profesor=jtf_profesor.getText();
        String link=jtf_link.getText();
        if(!materia.isEmpty()||!profesor.isEmpty()){
            Base_Datos regi_materia=new Base_Datos(materia,profesor,clave_usuario,link);
            boolean repedita=regi_materia.materia_repetida();
            if(repedita==false){
                regi_materia.reg_materia();
                frame.dispose();
            }else{
                JOptionPane.showMessageDialog(null, "Esa materia ya existe","Error de datos",JOptionPane.WARNING_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Por favor coloca los datos","Error de datos",JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void habilitar_boton(){
        while(true){
            if(!jtf_materia.getText().isEmpty()&&!jtf_profesor.getText().isEmpty()&&!jtf_link.getText().isEmpty()){
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