

import com.toedter.calendar.JDateChooser;
import java.awt.Color;//una clase para poder obtener los colores de RGB
import java.awt.Dimension;
import javax.swing.*;//No otorga todas las herramientas de GUI y de esta manera poder tener toda la funcionalidad por el swing
import java.awt.Font;//Se usa para representa fuentes de letra y mostrarlas de manera visible
import java.awt.event.*;//Proporciona interfaces y clases para lidiar con diferentes tipos de eventos
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;//Para que podamos escribir dentro de los documentos generados
import java.io.IOException;//Nos demuestra si existe algun error en los I/O
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Scanner;//Se utiiliza para generar escaneres de lo que se escribe elije etc
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.scene.input.KeyCode.M;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


public class Proyecto {
    
    public static int xcedula;
    private static String cedula;
    private static String nombres;
    private static String correo;
    private static String contraseña;
    private static String nmateria;
    private static String materia;
    private static String maestros;
    private static String ntarea;
    private static String dec_tarea;
    private static String fecha_entrega;
    private static String hora_entrega;
    SimpleDateFormat formato=new SimpleDateFormat("dd-MM-yyy");
    public static FileWriter flwriter = null;
    public static Scanner leer = new Scanner(System.in);
    
    JFrame frame = new JFrame("Agendainador");//Se crea un nuevo jframe con la leyeynda
        JDesktopPane vent = new JDesktopPane(); //Se crea un nuevo JDeskPanel
        JLabel jlb1 = new JLabel("!Bienvenido a la Agendainador!");//Se crea una nueva etiqueta con un texto escrito                             
        JButton jbtregistrar = new JButton("Registrate aqui");//Se crea el boton Registrar 
        JButton jbtacceder = new JButton("Acceder");//Se crea el boton Acceder
        
        
        
        //Controles para el archivo "usuarios"
        JLabel jlb2 = new JLabel("Clave del usuario");//Se crea una nueva etiqueta con un texto escrito
        JLabel jlb3 = new JLabel("");//Se crea una nueva etiqueta con un texto escrito
        JLabel jlb4 = new JLabel("Nombre del usuario");//Se crea una nueva etiqueta con un texto escrito
        JLabel jlb5 = new JLabel("Correo del usuario");//Se crea una nueva etiqueta con un texto escrito
        JLabel jlb6 = new JLabel("Crea una contraseña");//Se crea una nueva etiqueta con un texto escrito
        JTextField jtfusuario = new JTextField();//Se coloca un text Field para dar respuesta a las instrucciones del labe4
        JTextField jtf2 = new JTextField();//Se coloca un text Field para dar respuesta a las instrucciones del labe5
        JTextField jtf3 = new JTextField();//Se coloca un text Field para dar respuesta a las instrucciones del labe6
        JButton jbtconfirm1 = new JButton("Confirmar");//Se crea el boton Confirmar
        
        //Controles para acceder
        JLabel jlbusuario=new JLabel("Ingresa el nombre de usuario");
        JLabel jlbcontraseña=new JLabel("Introduce la contraseña");
        JTextField jtfcontraseña=new JTextField();
        JButton jbtentrar =new JButton("Acceder");
        JButton jbtregresar = new JButton("Regresar");

        //Controles para el archivo "Materia"
        JLabel jlb7 = new JLabel("Nombre de la materia");//Se crea una nueva etiqueta con un texto escrito
        JLabel jlb8 = new JLabel("Nombre del maestro");//Se crea una nueva etiqueta con un texto escrito
        JLabel jlb9 = new JLabel("Clave de materia");//Se crea una nueva etiqueta con un texto escrito
        JLabel jlb10 = new JLabel("");//Se crea una nueva etiqueta 
        JTextField jtf4 = new JTextField();//Se coloca un text Field para dar respuesta a las instrucciones del labe7
        JTextField jtf5 = new JTextField();//Se coloca un text Field para dar respuesta a las instrucciones del labe8
        JButton jbtconfirm2 = new JButton("Confirmar");//Se crea el boton Confirmar
        
        
        //Controles para el archivo "Tareas"
        JLabel jlb13 = new JLabel("Descripcion de la tarea");//Se crea una nueva etiqueta con un texto escrito
        JLabel jlb14 = new JLabel("Fecha de entrega");//Se crea una nueva etiqueta con un texto escrito
        JLabel jlb15 = new JLabel("Hora de entrega");//Se crea una nueva etiqueta con un texto escrito
        JDateChooser fentrega= new com.toedter.calendar.JDateChooser();
        JTextField jtf6 = new JTextField();//Se coloca un text Field para dar respuesta a las instrucciones del labe13
        JTextField jtf7 = new JTextField();//Se coloca un text Field para dar respuesta a las instrucciones del labe14
        JTextField jtf8 = new JTextField();//Se coloca un text Field para dar respuesta a las instrucciones del labe15
        JButton jbtconfirm3 = new JButton("Confirmar");//Se crea el boton Confirmar
        
        //Controles para interfaz Acceder
        JLabel jlbitp = new JLabel("Tareas pendientes");
        JButton jbtsalir = new JButton("Salir");
        JButton jbtregmat = new JButton("Registrar materia");//Se crea el boton
        JButton jbtregtar = new JButton("Registrar tarea");//Se crea el boton 
        JTextArea jtad = new JTextArea();
        JLabel jlbiusuario = new JLabel("");
        
        
        
        
    public Proyecto (){
        
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Materia");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Fecha de entrega");
        modelo.addColumn("Hora de entrega");
        
        String[]p1={"Materia","Descripcion","Fecha de entrega","Hora de entrega"};
        modelo.addRow(p1);
        JTable jtable=new JTable(modelo);
        
        
        //controles para el menu principal
        
        
        
        Font letra1 = new java.awt.Font("Arial", java.awt.Font.BOLD, 20);//Se genera un nuevo tipo de letra
        Font letra2 = new java.awt.Font("Arial", java.awt.Font.ITALIC, 16);//Generamos un nuevo tipo de letra
        
        
        
        //Dimensiones para los controles del menu principal
        jlb1.setBounds(25,10,500,30);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada
        jbtacceder.setBounds(95,40,190,30);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada
        jbtregistrar.setBounds(90,80,200,30);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada 
        
        
        //Controles para interfaz acceder
        jlbitp.setBounds(25,5,150,30);
        
        jbtregmat.setBounds(200,5,150,30);
        jbtregtar.setBounds(375,5,150,30);
        jbtsalir.setBounds(550,5,150,30);
        jlbiusuario.setBounds(725,5,150,30);
        jtad.setBounds(200,130,500,500);
        jtable.setBounds(200,100,500,30);
        jtad.setBackground(Color.green);
        
        
        
        //Dimensiones para los controles del archivo "Usuarios"
        jlb2.setBounds(25,10,400,30);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada
        jlb3.setBounds(235,10,200,30);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada 
        jlb4.setBounds(25,50,400,30);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada 
        jlb5.setBounds(25,90,400,30);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada 
        jtf2.setBounds(260,90,175,30);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada 
        jlb6.setBounds(25,130,400,30);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada 
        jtf3.setBounds(350,130,85,30);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada
        jbtconfirm1.setBounds(150,210,150,30);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada a JBconfirl
        
        
        //Dimensiones para los controles del archivo "Materia"
        jlb7.setBounds(25,10,400,30);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada a JL9
        jtf4.setBounds(330,10,200,30);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada a JTF6
        jlb8.setBounds(25,50,400,30);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada a JTL10
        jtf5.setBounds(330,50,200,30);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada a JTF7
        jlb9.setBounds(25,90,400,30);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada a JL11
        jlb10.setBounds(350,90,200,30);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada a JTF8
        jbtconfirm2.setBounds(150,210,150,30);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada a JBconfirl
        
        
        //Dimensiones para los controles del archivo "Tareas"
        jlb13.setBounds(25,10,400,30);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada
        jtf6.setBounds(260,10,200,30);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada 
        jlb14.setBounds(25,50,400,30);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada 
        jtf7.setBounds(260,50,200,30);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada 
        jlb15.setBounds(25,90,400,30);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada 
        jtf8.setBounds(260,90,200,30);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada 
        
        jbtconfirm3.setBounds(150,210,150,30);//Le damos especificaciones de tamaño y un lugar dentro de la pantalla creada a JBconfirl
        fentrega.setBounds(150,10,130,30);
        
        
        //Dimensiones para acceder
        jlbusuario.setBounds(25,90,400,30);
        jlbcontraseña.setBounds(25,200,400,30);
        jtfcontraseña.setBounds(25,250,400,30);
        jbtentrar.setBounds(25,300,400,30);
        jbtregresar.setBounds(25,350,400,30); 
        
        
        
        
        
        Border borde = new TitledBorder(new EtchedBorder());
        
        
        jlbitp.setBorder(borde);
        jtad.setBorder(borde);
       
        vent.add(jtad);
        vent.add(jbtregmat);
        vent.add(jbtregtar);
        vent.add(jlb1);//Añadir en la pantalla
        vent.add(jbtacceder);//Añadir en la pantalla 
        vent.add(jbtregistrar);//Añadir en la pantalla
        vent.add(jlbiusuario);
        
        vent.add(jlbitp);
        vent.add(jtable);
        
        vent.add(jlb2);//Añadir en la pantalla
        vent.add(jlb3);//Añadir en la pantalla
        vent.add(jlb4);//Añadir en la pantalla
        vent.add(jlb5);//Añadir en la pantalla
        vent.add(jlb6);//Añadir en la pantalla
        vent.add(jlb7);//Añadir en la pantalla
        vent.add(jlb8);//Añadir en la pantalla
        vent.add(jlb9);//Añadir en la pantalla
        vent.add(jlb10);//Añadir en la pantalla
        vent.add(jlb13);//Añadir en la pantalla
        vent.add(jlb14);//Añadir en la pantalla
        vent.add(jlb15);//Añadir en la pantalla
        vent.add(jlbusuario);
        vent.add(jlbcontraseña);
        vent.add(jbtentrar);
        vent.add(jtfcontraseña);
        vent.add(jtfusuario);
        vent.add(fentrega);
        
       
        vent.add(jtf2);//Añadir en la pantalla
        vent.add(jtf3);//Añadir en la pantalla
        vent.add(jtf4);//Añadir en la pantalla
        vent.add(jtf5);//Añadir en la pantalla
        vent.add(jtf6);//Añadir en la pantalla
        vent.add(jtf7);//Añadir en la pantalla
        vent.add(jtf8);//Añadir en la pantalla
        
        vent.add(jbtconfirm1);//Añadir en la pantalla
        vent.add(jbtconfirm2);//Añadir en la pantalla
        vent.add(jbtconfirm3);//Añadir en la pantalla
        vent.add(jbtregresar);
        vent.add(jbtsalir);
        
        
        jlb1.setFont(letra1);//Con esto le colocamos un tipo de letra
        jlb2.setFont(letra1);//Con esto le colocamos un tipo de letra
        jlb3.setFont(letra1);//Con esto le colocamos un tipo de letra
        jlb4.setFont(letra1);//Con esto le colocamos un tipo de letra
        jlb5.setFont(letra1);//Con esto le colocamos un tipo de letra
        jlb6.setFont(letra1);//Con esto le colocamos un tipo de letra
        jlb7.setFont(letra1);//Con esto le colocamos un tipo de letra
        jlb8.setFont(letra1);//Con esto le colocamos un tipo de letra
        jlb9.setFont(letra1);//Con esto le colocamos un tipo de letra
        jlb10.setFont(letra1);//Con esto le colocamos un tipo de letra
        jlb13.setFont(letra1);//Con esto le colocamos un tipo de letra
        jlb14.setFont(letra1);//Con esto le colocamos un tipo de letra
        jlb15.setFont(letra1);//Con esto le colocamos un tipo de letra
        
        jtable.setVisible(false);
        jlbitp.setVisible(false);
        jbtsalir.setVisible(false);
        jbtregmat.setVisible(false);
        jbtregtar.setVisible(false);
        jtad.setVisible(false);
        jlbiusuario.setVisible(false);
        
        
        //"Usuarios"
        jlb2.setVisible(false);
        jlb3.setVisible(false);
        jlb4.setVisible(false);
        jlb5.setVisible(false); 
        jtf2.setVisible(false);
        jlb6.setVisible(false);
        jtf3.setVisible(false);
        jbtconfirm1.setVisible(false);
        jlbusuario.setVisible(false);
        jlbcontraseña.setVisible(false);
        jtfusuario.setVisible(false);
        jtfcontraseña.setVisible(false);
        jbtentrar.setVisible(false);
        jbtregresar.setVisible(false);
        
        
        //"Materia"
        jlb7.setVisible(false);
        jtf4.setVisible(false);
        jlb8.setVisible(false);
        jtf5.setVisible(false);
        jlb9.setVisible(false);
        jlb10.setVisible(false);
        jbtconfirm2.setVisible(false);
        
        
        //"Tareas"
        jlb13.setVisible(false);
        jtf6.setVisible(false);
        jlb14.setVisible(false);
        jtf7.setVisible(false);
        jlb15.setVisible(false);
        jtf8.setVisible(false);
        jbtconfirm3.setVisible(false);
        fentrega.setVisible(false);
        
        
       Color fondo = new Color(245,135,85);
       vent.setBackground(fondo);//Colocamos un cilor de fondo
       
        frame.add(vent);//se añade el des
        frame.setSize(400,630);//Se le coloca un tamaño a el frame
        frame.setLocationRelativeTo(null);//Se coloca una locacion del frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//saca una operacion para cuando se cierre
        frame.setVisible(true);//Se hace visible el frame
        
        jbtregistrar.addActionListener(new ActionListener() {//Se inicia el action listenener de jbtregistrar
            public void actionPerformed(ActionEvent e) {//Se inicia el action listener
                frame.setSize(500,635);//Se coloca un tamaño diferente al frame 
                jlb1.setVisible(false);//Se oculta el control
                jbtregistrar.setVisible(false);//Se oculta el control
                jbtacceder.setVisible(false);////Se oculta el control
                jlb2.setVisible(true);//Se hace visible el control
                jlb3.setVisible(true);//Se hace visible el control
                xcedula= (int) (Math.random() * 10000);//Se asigna un valor aleatorio a la variable
                cedula=String.valueOf(xcedula);//Se le asigna el valor a la cadena cadula
                jlb3.setText(cedula);//Se coloca el numero en la etiqueta
                jtfusuario.setBounds(245,50,190,30);
                jlb4.setVisible(true);//Se hace visible el control
                jtfusuario.setVisible(true);//Se hace visible el control
                jlb5.setVisible(true); //Se hace visible el control
                jtf2.setVisible(true);//Se hace visible el control
                jlb6.setVisible(true);//Se hace visible el control
                jtf3.setVisible(true);//Se hace visible el control
                jbtconfirm1.setVisible(true);//Se hace visible el control
            }//Se cierra el action listener
        });//Se cierra el action listener de JBcatcli
        
       
        
        jbtacceder.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                frame.setSize(500,635);
                jbtregistrar.setVisible(false);//Se oculta el control
                jbtacceder.setVisible(false);////Se oculta el control
                jlbusuario.setVisible(true);
                jlbcontraseña.setVisible(true);
                jtfusuario.setBounds(25,150,400,30);
                jtfusuario.setVisible(true);
                jtfcontraseña.setVisible(true);
                jbtentrar.setVisible(true);
                jbtregresar.setVisible(true);
                
            }   
        });
        jbtregresar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                frame.setSize(400,630);
                jbtregistrar.setVisible(true);//Se oculta el control
                jbtacceder.setVisible(true);////Se oculta el control
                jlbusuario.setVisible(false);
                jlbcontraseña.setVisible(false);
                jtfusuario.setVisible(false);
                jtfcontraseña.setVisible(false);
                jbtentrar.setVisible(false);
                jbtregresar.setVisible(false);
            }   
        });
                
        jbtregmat.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                frame.setSize(650,500);
                jlbitp.setVisible(false);
                jbtsalir.setVisible(false);
                jtable.setVisible(false);
                jbtregmat.setVisible(false);
                jbtregtar.setVisible(false);
                jlbiusuario.setVisible(false);
                jtad.setVisible(false);
                
                jbtconfirm2.setVisible(true);
                jlb7.setVisible(true);
                jtf4.setVisible(true);
                jlb8.setVisible(true);
                jtf5.setVisible(true);
                jlb9.setVisible(true);
                jlb10.setVisible(true);
                nmateria=nmateria+1;
                
            }   
        });
        jbtregtar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                frame.setSize(650,500);
                jlbitp.setVisible(false);
                jbtsalir.setVisible(false);
                jtable.setVisible(false);
                jbtregmat.setVisible(false);
                jbtregtar.setVisible(false);
                jlbiusuario.setVisible(false);
                jtad.setVisible(false);
                
                jlb7.setBounds(25,130,400,30);
                jtf4.setBounds(260,130,200,30);
                jlb13.setVisible(true);
                jtf6.setVisible(true);
                jlb14.setVisible(true);
                jtf7.setVisible(true);
                jlb15.setVisible(true);
                jtf8.setVisible(true);
                jlb7.setVisible(true);
                jtf4.setVisible(true);
                jbtconfirm3.setVisible(true);
                ntarea=ntarea+1;
          }   
        });
        
        jbtentrar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                
                File lector = new File("E:\\Tareas"+jtfusuario.getText()+".txt");//Se crea un objeto de tipo file con la direccion del fichero
                Scanner leer = null;//Genera un nuevo scanner en nulo
                if(!lector.exists()){//Se genera un if con su respectiva condicion
                    JOptionPane.showMessageDialog(null, "El archivo no se ha encontrado","Error al leer archivo",JOptionPane.WARNING_MESSAGE);//Se muestra un mensaje de error debido a que hay error en los datos
                } else {//en caso contrario
                jtad.setEditable(false);
                jtad.setText("");
                try {//Se abre el try
                    leer = new Scanner(lector);//Le da un valor nuevo a leer
                } catch (FileNotFoundException ex) {//Se inicia un nuevo catch junto con su tipo de excepcion
                    Logger.getLogger(Proyecto.class.getName()).log(Level.SEVERE, null, ex);//Se usa el metodo logger
                }//Se cierra el catch
                while(leer.hasNextLine()){//Se inicia un while
                String linea = leer.nextLine();//Se crea un nuevo string linea obteniendo la linea leida
                    jtad.append(linea+"\n");//Se escribe en el JTextArea 
                }//Se cierra el while
                }//Se cierra el else
                
                
                try{
           BufferedReader br = new BufferedReader(new FileReader("E:\\Usuarios.txt"));
           String cl="", clx=""; 
           int f=0;
           String user=jtfusuario.getText()+"-"+jtfcontraseña.getText();
                    while ((cl=br.readLine())!=null){
                        
                        Scanner delimitar = new Scanner(cl);
                        delimitar.useDelimiter(",");
                        clx= delimitar.next();
                        System.out.println(clx);
                        if(clx.equals(user)) {
                            f++;
                            br.close();
                            break;
                        }else{
                            for(int i=0;f!=0;i++){
                                clx=delimitar.nextLine();
                            }
                        }
                    }   
                if(f==0){
                    JOptionPane.showMessageDialog(null,"El usuario o la contraseña son incorrectos","Error",JOptionPane.ERROR_MESSAGE); 
                        br.close();//se cierra el objeto br
                } else {
                    frame.setSize(1000,1000);//Se  le dio un nuevo tamaño a el Frame
                //Habilitamos la interfaz
                jlb1.setVisible(false);//Se oculta el control
                jlbusuario.setVisible(false);
                jlbcontraseña.setVisible(false);
                jtfusuario.setVisible(false);
                jtfcontraseña.setVisible(false);
                jbtentrar.setVisible(false);
                jbtregistrar.setVisible(false);
                jbtregresar.setVisible(false);
                
                jlbitp.setVisible(true);
                jbtsalir.setVisible(true);
                jtable.setVisible(true);
                jbtregmat.setVisible(true);
                jbtregtar.setVisible(true);
                jlbiusuario.setVisible(true);
                jtad.setVisible(true);
                jlbiusuario.setText(jtfusuario.getText());
                    JOptionPane.showMessageDialog(null,"Bienvenido "+jtfusuario.getText(),"Bienvenido",JOptionPane.PLAIN_MESSAGE);
                    BufferedReader br2 = new BufferedReader(new FileReader("E:\\Tarea.txt"));
                    String cl2="", clx2=""; 
                    int f2=0;
                }
        }catch(IOException er){}
            }
            });
        
        jbtconfirm1.addActionListener(new ActionListener() {//Se añade un action listener de confirm1
            public void actionPerformed(ActionEvent e) {//se inicia el action listener
                FileWriter file = null;//Se inicializa el fw1 de tipo FileWriter con valor nulo
                try{//Se inicializa un try
                file = new FileWriter("E:\\Usuarios.txt", true);//Se le asigna una ubicacion al archivo
                    try{//Se inicializa un try
                    }catch(NumberFormatException err){//Se abre un chatch por si hay un error
                    }//Se cierra el catch
                    nombres=jtfusuario.getText();//Se le asigna a nombres el contenido de jtf1
                    correo=jtf2.getText();//Se le asigna a nombres el contenido de jtf2
                    contraseña=jtf3.getText();//Se le asigna a nombres el contenido de jtf3
                    if(!jtfusuario.getText().isEmpty() || !jtf2.getText().isEmpty() || !jtf3.getText().isEmpty()){//Se coloca una condicion comprobando si jtf1 esta en blanco
                        file.write(nombres+"-"+contraseña+","+cedula+","+correo+"\n");//Se escribe dentro del fw1 Los datos obtenidos
                        file.close();//Se cierra el documento fw1
                        JOptionPane.showMessageDialog(null, "Sus datos han sido guardados","Registro correcto",JOptionPane.INFORMATION_MESSAGE);//Suelta en pantalla un OptionPane dandoa etender que se almaceno la informacion
                        frame.setSize(1000,1000);//Se  le dio un nuevo tamaño a el Frame
                        jlbitp.setVisible(true);
                        jbtsalir.setVisible(true);
                        jtable.setVisible(true);
                        jbtregmat.setVisible(true);
                        jbtregtar.setVisible(true);
                        jlbiusuario.setVisible(true);
                        jtad.setVisible(true);
                        jlb4.setVisible(false);//Se hace visible el control
                        jtfusuario.setVisible(false);//Se hace visible el control
                        jlb5.setVisible(false); //Se hace visible el control
                        jtf2.setVisible(false);//Se hace visible el control
                        jlb6.setVisible(false);//Se hace visible el control
                        jtf3.setVisible(false);//Se hace visible el control
                        jlb2.setVisible(false);//Se hace visible el control
                        jlb3.setVisible(false);
                        jbtconfirm1.setVisible(false);//Se hace visible el control
                    } else {//Si no se logra cumplir la condicion, pasara lo siguiente
                       
                        JOptionPane.showMessageDialog(null, "Por favor coloca los datos","Error de datos",JOptionPane.WARNING_MESSAGE);//Si no se cumple, saltara un nuev mensaje evitando que continues sin llenar los espacios
                    }//Se cierra la condicion contraria
                } catch (IOException er) {//Se inicializa un catch encapsulando un er
                } finally {//Se inicializa y se cierra el catch
                if (file != null) {//Se inicializa un if con uan cierta condicion
                    try {//Se inicializa un nuevo try
                        file.close();//Si las condiciones se cumplen se cerrara el fw1
                    } catch (IOException er) {//Se genera otro catch por si surge una Excepcion
                    }//Se cierra el ultimo catch 
                }//se cierra el if
                }//se cierra el finally
            }//Se cierra el listener
        });//Se cierra el listener de jbtconfirm1
        
        jbtconfirm2.addActionListener(new ActionListener() {//Se añade un action listener de confir1
            public void actionPerformed(ActionEvent e) {//se inicia el action listener
                FileWriter fw1 = null;//Se inicializa el fw1 de tipo FileWriter con valor nulo
                try{//Se inicializa un try
                fw1 = new FileWriter("E:\\Materia"+jtfusuario.getText()+".txt", true);//Se le da un valor al Filewriter y ubicacion 
                    try{//Se inicializa un try
                    }catch(NumberFormatException err){//Se crea la condicion a dar error
                    }//Se cierra el catch
                    materia=jtf4.getText();//Se le da un valor a CVVEN obteniendola de JTF3
                    maestros=jtf5.getText();//Se le da un valor a CVCDPR obteniendola de JTF4
                        if(!jtf4.getText().isEmpty() || !jtf5.getText().isEmpty()){//Se coloca una condicion comprobando si jtf1 esta en blanco
                        fw1.write(cedula+"-"+materia+"-"+maestros+"-"+nmateria+"\n");//Se escribe dentro del fw1 Los datos obtenidos
                        fw1.close();//Se cierra el documento fw1
                        JOptionPane.showMessageDialog(null, "Registro guardado con exito","Registro correcto",JOptionPane.INFORMATION_MESSAGE);//Suelta en pantalla un OptionPane dandoa etender que se almaceno la informacion
                        frame.setSize(1000,1000);//Se  le dio un nuevo tamaño a el Frame
                        jbtsalir.setVisible(true);
                        jlbitp.setVisible(true);
                        jtable.setVisible(true);
                        jbtregmat.setVisible(true);
                        jbtregtar.setVisible(true);
                        jlbiusuario.setVisible(true);
                        jtad.setVisible(true);
                        jbtconfirm2.setVisible(false);
                        jlb7.setVisible(false);
                        jtf4.setVisible(false);
                        jlb8.setVisible(false);
                        jtf5.setVisible(false);
                        jlb9.setVisible(false);
                        jlb10.setVisible(false);
                    } else {//Si no se logra cumplir la condicion, pasara lo siguiente
                    JOptionPane.showMessageDialog(null, "Por favor coloca los datos","Error de datos",JOptionPane.WARNING_MESSAGE);//Si no se cumple, saltara un nuev mensaje evitando que continues sin llenar los espacios
                    }//Se cierra la condicion contraria
                    
                } catch (IOException er) {//Se inicializa un catch encapsulando un er
                } finally {//Se inicializa y se cierra el catch
                if (fw1 != null) {//Se inicializa un if con uan cierta condicion
                    try {//Se inicializa un nuevo try
                        fw1.close();//Si las condiciones se cumplen se cerrara el fw1
                    } catch (IOException er) {//Se genera otro catch por si surge una Excepcion
                    }//Se cierra el ultimo catch 
                }//se cierra el if
                }//se cierra el finally
            }//Se cierra el listener
        });//Se cierra el listener de jbtconfirm1
        
        jbtconfirm3.addActionListener(new ActionListener() {//Se añade un action listener de jbtconfirm3
            public void actionPerformed(ActionEvent e) {//se inicia el action listener
                FileWriter fw1 = null;//Se inicializa el fw1 de tipo FileWriter con valor nulo
                try{//Se inicializa un try
                fw1 = new FileWriter("E:\\Tareas"+jtfusuario.getText()+".txt", true);//Se le da un valor al Filewriter y ubicacion 
                    try{//Se inicializa un try
                    }catch(NumberFormatException err){//Se crea la condicion a dar error
                    }//Se cierra el catch
                    dec_tarea=jtf6.getText();//Se le da un valor a CVVEN obteniendola de 
                    fecha_entrega=jtf7.getText();//Se le da un valor a CVCDPR obteniendola de 
                    hora_entrega=jtf8.getText();//Se le da un valor a CVCDPR obteniendola de 
                    materia=jtf4.getText();
                    
                        if(!jtf6.getText().isEmpty() || !jtf7.getText().isEmpty() || !jtf8.getText().isEmpty()){//Se coloca una condicion comprobando si jtf1 esta en blanco
                        fw1.write(materia+"     "+dec_tarea+"    "+fecha_entrega+"    "+hora_entrega+"\n");//Se escribe dentro del fw1 Los datos obtenidos
                        fw1.close();//Se cierra el documento fw1
                        JOptionPane.showMessageDialog(null, "Registro guardado con exito","Registro correcto",JOptionPane.INFORMATION_MESSAGE);//Suelta en pantalla un OptionPane dandoa etender que se almaceno la informacion
                        frame.setSize(1000,1000);//Se  le dio un nuevo tamaño a el Frame
                        jlb13.setVisible(false);
                        jtf6.setVisible(false);
                        jlb14.setVisible(false);
                        jtf7.setVisible(false);
                        jlb15.setVisible(false);
                        jtf8.setVisible(false);
                        jlb7.setVisible(false);
                        jtf4.setVisible(false);
                        jbtconfirm3.setVisible(false);
                        jbtsalir.setVisible(true);
                        jlbitp.setVisible(true);
                        jtable.setVisible(true);
                        jbtregmat.setVisible(true);
                        jbtregtar.setVisible(true);
                        jlbiusuario.setVisible(true);
                        jtad.setVisible(true);
                    } else {//Si no se logra cumplir la condicion, pasara lo siguiente
                    JOptionPane.showMessageDialog(null, "Por favor coloca los datos","Error de datos",JOptionPane.WARNING_MESSAGE);//Si no se cumple, saltara un nuev mensaje evitando que continues sin llenar los espacios
                    }//Se cierra la condicion contraria
                } catch (IOException er) {//Se inicializa un catch encapsulando un er
                } finally {//Se inicializa y se cierra el catch
                if (fw1 != null) {//Se inicializa un if con uan cierta condicion
                    try {//Se inicializa un nuevo try
                        fw1.close();//Si las condiciones se cumplen se cerrara el fw1
                    } catch (IOException er) {//Se genera otro catch por si surge una Excepcion
                    }//Se cierra el ultimo catch 
                }//se cierra el if
                }//se cierra el finally
                File lector = new File("E:\\Tareas"+jtfusuario.getText()+".txt");//Se crea un objeto de tipo file con la direccion del fichero
                Scanner leer = null;//Genera un nuevo scanner en nulo
                if(!lector.exists()){//Se genera un if con su respectiva condicion
                    JOptionPane.showMessageDialog(null, "El archivo no se ha encontrado \n Primero haga un registro","Error al leer archivo",JOptionPane.WARNING_MESSAGE);//Se muestra un mensaje de error debido a que hay error en los datos
                } else {//en caso contrario
                jtad.setEditable(false);
                jtad.setText("");
                try {//Se abre el try
                    leer = new Scanner(lector);//Le da un valor nuevo a leer
                } catch (FileNotFoundException ex) {//Se inicia un nuevo catch junto con su tipo de excepcion
                    Logger.getLogger(Proyecto.class.getName()).log(Level.SEVERE, null, ex);//Se usa el metodo logger
                }//Se cierra el catch
                while(leer.hasNextLine()){//Se inicia un while
                String linea = leer.nextLine();//Se crea un nuevo string linea obteniendo la linea leida
                    jtad.append(linea+"\n");//Se escribe en el JTextArea 
                }//Se cierra el while
                }//Se cierra el else
                jtf6.setText("");
                jtf7.setText("");
                jtf8.setText("");
                jtf4.setText("");
            }//Se cierra el listener
        });//Se cierra el listener de jbtconfirm1
        
        
        jbtsalir.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                frame.setSize(400,630);
                jlb1.setVisible(true);//Se oculta el control
                jbtregistrar.setVisible(true);//Se oculta el control
                jbtacceder.setVisible(true);////Se oculta el control
                
                jlbitp.setVisible(false);
                jbtsalir.setVisible(false);
                jtable.setVisible(false);
                jbtregmat.setVisible(false);
                jbtregtar.setVisible(false);
                jlbiusuario.setVisible(false);
                jtfusuario.setText("");
                jtfusuario.setVisible(false);
                jtf2.setVisible(false);
                jtf3.setVisible(false); 
                jbtconfirm1.setVisible(false);
                jlb6.setVisible(false);
                jlb5.setVisible(false);
                jlb7.setVisible(false);
                jlb4.setVisible(false);
                jlb8.setVisible(false);
                jlb2.setVisible(false);
                jtad.setVisible(false);
       
                
            }   
        });
    }
    public static void main(String[]args){
        Proyecto obj = new Proyecto();//se crea un objeto de tipo Programa
    }

    
}
