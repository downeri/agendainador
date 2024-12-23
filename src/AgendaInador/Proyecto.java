package AgendaInador;

import java.awt.Color;
import javax.swing.*;//No otorga todas las herramientas de GUI y de esta manera poder tener toda la funcionalidad por el swing
import java.awt.event.*;//Proporciona interfaces y clases para lidiar con diferentes tipos de eventos
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Proyecto {
    JFrame frame = new JFrame("AgendaInador");//Se crea un nuevo jframe con la leyeynda "menu"
    JDesktopPane vent = new JDesktopPane(); //Se crea un nuevo JDeskPanel
    JLabel jlb1 = new JLabel("¡Bienvenido a la Agendainador!");//Se crea una nueva etiqueta con un texto escrito                             
    JButton jbtregistrar = new JButton("Nuevo Usuario");//Se crea el boton Registrar 
    JButton jbtacceder = new JButton("Acceder");//Se crea el boton Acceder
    ImageIcon img=new ImageIcon("AgendaInadorIcon.png");

    public Proyecto (){
        
        jlb1.setBounds(220,20,1000,60);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada
        jlb1.setFont(new java.awt.Font("Arial", 0, 24));
        jbtacceder.setBounds(190,100,400,60);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada
        jbtacceder.setFont(new java.awt.Font("Arial", 0, 24));
        jbtregistrar.setBounds(190,200,400,60);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada 
        jbtregistrar.setFont(new java.awt.Font("Arial", 0, 24));
        
        vent.add(jlb1);//Añadir en la pantalla
        vent.add(jbtacceder);//Añadir en la pantalla 
        vent.add(jbtregistrar);//Añadir en la pantalla

        frame.add(vent);//se añade el des
        frame.setResizable(false);
        frame.setSize(800,400);//Se le coloca un tamaño a el frame
        frame.setLocationRelativeTo(null);//Se coloca una locacion del frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//saca una operacion para cuando se cierre
        frame.setVisible(true);//Se hace visible el frame
        frame.setIconImage(img.getImage());
        Color aeiou=new Color(240,240,240);
        vent.setBackground(aeiou);
        
        jbtregistrar.addActionListener((ActionEvent e)->{
            registrar_usuario();
        });
       
        jbtacceder.addActionListener((ActionEvent e)-> {
            acceder();
        });
    }
    
    public static void main(String[]args){
        String usuario="";
        String contraseña="";
        try {
            try (BufferedReader br = new BufferedReader(new FileReader("E:\\usuario_recordado.txt"))) {
                String linea;
                while((linea=br.readLine())!=null){
                    try (Scanner leer = new Scanner(linea).useDelimiter("-")) {
                        usuario=leer.next();
                        contraseña=leer.next();
                    }
                }
            }
            Base_Datos login=new Base_Datos(usuario,contraseña);
            boolean x=login.leerdbacceder();
            if(x==true){
                MainMenu m=new MainMenu(login.getClave_usuario(),usuario);
            }if(x==false){
                Proyecto s=new Proyecto();
            }
        }catch (IOException ex) {
            Proyecto x=new Proyecto();//se crea un objeto de tipo Programa
        }
    }

    private void registrar_usuario() {
        Registrar_Usuario RU=new Registrar_Usuario();
        frame.dispose();
    }

    private void acceder() {
        Login A=new Login();
        frame.dispose();
    }
}