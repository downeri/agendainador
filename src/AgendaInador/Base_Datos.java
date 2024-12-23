package AgendaInador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public final class Base_Datos {
    int clave_usuario;
    String nombre_usuario;
    String contraseña;
    int clave_materia;
    String nombre_profesor;
    String nombre_materia;
    int no_tarea;
    String desc_tarea;
    String desc_edit;
    java.sql.Date fecha_entrega;
    java.sql.Time hora_clase;
    int clave_clase;
    String link;
    String query;
    ResultSet rs=null;
    String url="jdbc:mysql://localhost:3306/agendainador";
    String username="root";
    String password=null;
    Connection conn;
    String driver="com.mysql.jdbc.Driver";
    Statement stmt;
    String cmd;
    
    public Base_Datos(String nombre_usuario,String contraseña){ //Registro y login
        this.nombre_usuario=nombre_usuario;
        this.contraseña=contraseña;
        iniBase_Datos();
    }
    
    public Base_Datos(int clave_usuario,String desc_tarea, double o){ //Editar tarea
        this.clave_usuario=clave_usuario;
        this.desc_tarea=desc_tarea;
        iniBase_Datos();
    }

    public Base_Datos(int clave_usuario,String nombre_materia, String desc_tarea,java.sql.Date fecha_entrega){ //Tarea
        this.clave_usuario=clave_usuario;
        this.nombre_materia=nombre_materia;
        this.desc_tarea=desc_tarea;
        this.fecha_entrega=fecha_entrega;
        iniBase_Datos();
    }
    
    public Base_Datos(int clave_usuario){ //Tabla
        this.clave_usuario=clave_usuario;
        iniBase_Datos();
    }
    
    public Base_Datos(String nombre_materia,String profesor, int clave_usuario, String link){ //Registrar Materia
        this.link=link;
        this.clave_usuario=clave_usuario;
        this.nombre_profesor=profesor;
        this.nombre_materia=nombre_materia;
        iniBase_Datos();
    }

    Base_Datos(int clave_usuario,String desc_edit, String desc_tarea_editado, java.sql.Date fecha_entrega_editada, String nombre_materia_editado) {//Editar tarea
        this.desc_edit=desc_edit;
        this.clave_usuario=clave_usuario;
        this.desc_tarea=desc_tarea_editado;
        this.fecha_entrega=fecha_entrega_editada;
        this.nombre_materia=nombre_materia_editado;
        iniBase_Datos();
    }
    
    public Base_Datos(int clave_usuario, int no_tarea_a_borrar){ //Tabla
        this.clave_usuario=clave_usuario;
        this.no_tarea=no_tarea_a_borrar;
        iniBase_Datos();
    }

    Base_Datos(int clave_usuario, java.sql.Date fecha_entrega, java.sql.Time hora_clase, String nombre_materia) {//Registrar clase
        this.hora_clase=hora_clase;
        
        this.clave_usuario=clave_usuario;
        this.fecha_entrega=fecha_entrega;
        this.nombre_materia=nombre_materia;
        iniBase_Datos();
    }
    
    public Base_Datos(int clave_usuario, int no_clase_a_borrar, int nose){ //Tabla
        this.clave_usuario=clave_usuario;
        this.clave_clase=no_clase_a_borrar;
        iniBase_Datos();
    }
    
    public Base_Datos(int clave_usuario, String nombre_materia){
        this.clave_usuario=clave_usuario;
        this.nombre_materia=nombre_materia;
        iniBase_Datos();
    }
    
    public Base_Datos(int clave_usuario, String desc_tarea, int o){
        this.clave_usuario=clave_usuario;
        this.desc_tarea=desc_tarea;
        iniBase_Datos();
    }

    Base_Datos(int clave_usuario, int clave_clase, java.sql.Date fecha_entrega, Time hora_clase, String nombre_materia) {
        this.clave_clase=clave_clase;
        this.clave_usuario=clave_usuario;
        this.link=link;
        this.fecha_entrega=fecha_entrega;
        this.hora_clase=hora_clase;
        this.nombre_materia=nombre_materia;
        iniBase_Datos();
    }

    Base_Datos(String materia, String profesor, int clave_usuario, String link, int clave_materia) {
        this.nombre_materia=materia;
        this.nombre_profesor=profesor;
        this.clave_usuario=clave_usuario;
        this.link=link;
        this.clave_materia=clave_materia;
        iniBase_Datos();
    }
  
    public void iniBase_Datos(){
        try {
            this.conn = DriverManager.getConnection(url,username,password);
            this.stmt = conn.createStatement();
            Class.forName(driver);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Base_Datos.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Se ha producido un error al conectar con la base de datos","Error de conexion",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void registrar_usuario(){
        try {
            this.cmd="INSERT INTO `usuario` (`clave_usuario`, `nombre_usuario`, `contraseña`) VALUES(NULL,'"+nombre_usuario+"','"+contraseña+"')";
            this.stmt.executeUpdate(cmd);
            JOptionPane.showMessageDialog(null, "Su cuenta ha sido creada "+"\n"+"¡Bienvenid@ a AgendaInador!","Registro exitoso",JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(Base_Datos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean leerdbacceder(){
        boolean coinciden=false;
        int clave_leida;
        String usuario_leido;
        String contraseña_leida;
        try {
            query="SELECT clave_usuario, nombre_usuario, contraseña FROM usuario";
            rs=stmt.executeQuery(query);
            while(rs.next()){
                clave_leida=rs.getInt("clave_usuario");
                usuario_leido=rs.getString("nombre_usuario");
                contraseña_leida=rs.getString("contraseña");
                if(usuario_leido.equals(this.nombre_usuario)&&contraseña_leida.equals(this.contraseña)){
                    coinciden=true;
                    this.clave_usuario=clave_leida;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Base_Datos.class.getName()).log(Level.SEVERE, null, ex);
           
        }
        return coinciden;
    }
    
    public void reg_tarea(){
        try {
            this.cmd="INSERT INTO `tarea` (`clave_usuario`, `no_tarea`, `desc_tarea`, `fecha_entrega`, `clave_materia`) VALUES ('"+clave_usuario+"', NULL, '"+desc_tarea+"', '"+fecha_entrega+"', '"+clave_materia+"');";
            System.out.println(cmd);
            this.stmt.executeUpdate(cmd);
            JOptionPane.showMessageDialog(null, "La tarea ha sido registrada","Registro correcto",JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(Base_Datos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void reg_materia(){
        try {
            this.cmd="INSERT INTO `materia` (`clave_usuario`, `clave_materia`, `nombre_profesor`, `nombre_materia`, `link_clase`) VALUES ('"+clave_usuario+"', NULL, '"+nombre_profesor+"', '"+nombre_materia+"', '"+link+"');";
            System.out.println(cmd);
            this.stmt.executeUpdate(cmd);
            JOptionPane.showMessageDialog(null, "La materia ha sido registrada","Registro correcto",JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(Base_Datos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean materia_repetida(){
        boolean se_repite=false;
        int clave_leida;
        String materia_leida;
        try {
            query="SELECT clave_usuario, nombre_materia FROM materia";
            rs=stmt.executeQuery(query);
            while(rs.next()){
                clave_leida=rs.getInt("clave_usuario");
                materia_leida=rs.getString("nombre_materia");
                if(clave_leida==clave_usuario&&materia_leida.equals(nombre_materia)){
                    se_repite=true;
                    return se_repite;
                }
            } 
        } catch (SQLException ex) {
            Logger.getLogger(Base_Datos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return se_repite;
    }
    
    public boolean usuario_repetido(){
        boolean se_repite=false;
        int clave_leida;
        String nombre_leido;
        try {
            query="SELECT clave_usuario, nombre_usuario FROM usuario";
            rs=stmt.executeQuery(query);
            while(rs.next()){
                clave_leida=rs.getInt("clave_usuario");
                nombre_leido=rs.getString("nombre_usuario");
                if(nombre_leido.equals(nombre_usuario)){
                    se_repite=true;
                    return se_repite;
                }
            } 
        } catch (SQLException ex) {
            Logger.getLogger(Base_Datos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return se_repite;
    }
    
    public void editar_tarea() {
        try {
            query="SELECT clave_usuario, desc_tarea, fecha_entrega, clave_materia, no_tarea FROM tarea";
            rs=stmt.executeQuery(query);
            String sql = "UPDATE `tarea` SET `desc_tarea` = '"+desc_tarea+"', `fecha_entrega` = '"+fecha_entrega+"', `clave_materia` = '"+clave_materia+"' WHERE `tarea`.`desc_tarea` = '"+desc_edit+"' AND `tarea`.`clave_usuario` = "+clave_usuario+";";
            stmt.executeUpdate(sql);
            rs = stmt.executeQuery(query);

        }   catch (SQLException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminar_tarea(){
        try {
            cmd = "DELETE FROM `tarea` WHERE `tarea`.`clave_materia` = '"+clave_materia+"' AND `tarea`.`fecha_entrega` = '"+fecha_entrega+"' AND `tarea`.`desc_tarea` = '"+desc_tarea+"' AND `tarea`.`clave_usuario` = "+clave_usuario;
            stmt.executeUpdate(cmd);
        } catch (SQLException ex) {
            Logger.getLogger(Base_Datos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminar_tarea_pasada(){
        try {
            cmd = "DELETE FROM `tarea` WHERE `tarea`.`no_tarea` = "+no_tarea;
            System.out.print(cmd);
            stmt.executeUpdate(cmd);
        } catch (SQLException ex) {
            Logger.getLogger(Base_Datos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void reg_clase(){
        try {
            this.cmd="INSERT INTO `clase` (`clave_usuario`, `clave_clase`, `dia_clase`, `hora_clase`, `clave_materia`) VALUES ('"+clave_usuario+"', NULL, '"+fecha_entrega+"', '"+hora_clase+"', '"+clave_materia+"'); ";
            System.out.println(cmd);
            this.stmt.executeUpdate(cmd);
            
        } catch (SQLException ex) {
            Logger.getLogger(Base_Datos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getClave_usuario() {
        return clave_usuario;
    }

    public void eliminar_clase() {
        try {
            cmd = "DELETE FROM `clase` WHERE `clase`.`dia_clase` = '"+fecha_entrega+"' AND `clase`.`hora_clase`= '"+hora_clase+"' AND `clase`.`clave_materia` = '"+clave_materia+"' AND `clase`.`clave_usuario` ="+clave_usuario;
            stmt.executeUpdate(cmd);
        } catch (SQLException ex) {
            Logger.getLogger(Base_Datos.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    public void eliminar_clase_pasada() {
        try {
            cmd = "DELETE FROM `clase` WHERE `clase`.`clave_clase` ="+no_tarea;
            stmt.executeUpdate(cmd);
        } catch (SQLException ex) {
            Logger.getLogger(Base_Datos.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }

    public void edit_clase() {
        try {
            query="SELECT clave_usuario, clave_clase, dia_clase, hora_clase, clave_materia FROM clase";
            rs=stmt.executeQuery(query);
            cmd = "UPDATE `clase` SET `dia_clase` = '"+fecha_entrega+"', `hora_clase` = '"+hora_clase+"', `clave_materia` = '"+clave_materia+"' WHERE `clase`.`clave_clase` = "+clave_clase+"; ";
            System.out.println(cmd);
            stmt.executeUpdate(cmd);
        }   catch (SQLException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminar_materia(){
        try {
            cmd = "DELETE FROM `materia` WHERE `materia`.`clave_materia` = "+clave_materia;
            stmt.executeUpdate(cmd);
            cmd = "DELETE FROM `tarea` WHERE `tarea`.`clave_materia` = '"+clave_materia+"' AND `tarea`.`clave_usuario` = "+clave_usuario;
            stmt.executeUpdate(cmd);
            cmd = "DELETE FROM `clase` WHERE `clase`.`clave_materia` = '"+clave_materia+"' AND `clase`.`clave_usuario` = "+clave_usuario;
            stmt.executeUpdate(cmd);
            JOptionPane.showMessageDialog(null, "La materia ha sido eliminada",":(",JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(Base_Datos.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    public void editar_materia(){
        try {
            query="SELECT clave_usuario, clave_materia, nombre_profesor, nombre_materia, link_clase FROM materia";
            rs=stmt.executeQuery(query);
            cmd = "UPDATE `materia` SET  `nombre_profesor` = '"+nombre_profesor+"', `nombre_materia` = '"+nombre_materia+"', `link_clase` = '"+link+"' WHERE `materia`.`clave_materia` = "+clave_materia+"; ";
            System.out.println(cmd);
            stmt.executeUpdate(cmd);
        }   catch (SQLException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void obtener_clave_materia(){
        int clave_usuario_leida;
        int clave_materia_leida;
        String materia_leida;
        System.out.println("El nombre enviado es: "+nombre_materia);
        try {
            query="SELECT clave_usuario, nombre_materia, clave_materia FROM materia";
            rs=stmt.executeQuery(query);
            while(rs.next()){
                clave_usuario_leida=rs.getInt("clave_usuario");
                materia_leida=rs.getString("nombre_materia");
                clave_materia_leida=rs.getInt("clave_materia");
                if(clave_usuario_leida==clave_usuario&&materia_leida.equals(this.nombre_materia)){
                    this.clave_materia=clave_materia_leida;
                    System.out.println("La clave es: "+clave_materia);
                }
            }
        }   catch (SQLException ex) {
            Logger.getLogger(Base_Datos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}