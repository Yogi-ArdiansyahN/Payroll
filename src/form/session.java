/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

/**
 *
 * @author MrY
 */
public class session {
 
    private static String id_log;
    private static String level;
    
    public static String getIdLog(){
        return id_log;
    }
    
    public static String getLevel(){
        return level;
    }
    
    public static void set_Level(String level){
        session.level = level;
    }
    
    public static void set_Id_login(String level){
        session.id_log = id_log;
    }
}
