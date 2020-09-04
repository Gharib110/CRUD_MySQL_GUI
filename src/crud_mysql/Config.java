/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud_mysql;
import javax.swing.*;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.*;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
/**
 *
 * @author ASUS
 */
@Configuration
public class Config {
    
    @Bean(name="url")
    public URLs geturl()
    {
        return new URLs();
        
    }
    
    @Bean(name="crud")
    public  CRUD_MySQL crud()
    {
        return new CRUD_MySQL();
    }
    
    @Bean(name="inputs")
    public Inputs_2 Input()
    {
        return new Inputs_2();
    }
    @Bean(name="update")
    public Input_update upd()
    {
        return new Input_update();
    }
}
