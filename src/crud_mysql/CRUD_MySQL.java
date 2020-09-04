package crud_mysql;
import crud_mysql.*;
import javax.swing.*;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.context.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import static org.springframework.jdbc.core.JdbcOperationsExtensionsKt.query;
import static org.springframework.util.Assert.state;
import static org.springframework.util.Assert.state;

/**
 *
 * @author ASUS
 */

public class CRUD_MySQL {

    /**
     * @param args the command line arguments
     */
   
    
    private Inputs_2 in2;
    private String user, pass, name, cellphone, email;
    private int age, id;
    private URLs url;
    private Input_update inup;
    
    public void Inserting(String user, String pass, String name, String cellphone, String email, int id, int age) throws ClassNotFoundException, IllegalAccessException, SQLException, InstantiationException
    {
        String query;
        this.age = age;
        this.cellphone = cellphone;
        this.email = email;
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.user = user;
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String urll = this.url.getUrl();
            Connection conn = DriverManager.getConnection(urll, this.url.getUser(), this.url.getPass());
            Statement state = conn.createStatement();
            query = String.format("insert into %s (id, username, password, email, cellphone, name, age) values (%s, '%s', '%s', '%s', '%s', '%s', %s)",this.url.getTablename(), this.id, this.user, this.pass, this.email, this.cellphone, this.name, this.age);
            state.execute(query);
            state.close();
            conn.close();
        }
        
        catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex)
        {
            
            throw ex;
            
        }
    }
    
    
    public void LoadingEntireData(JTable sqltable) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException 
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection connect = DriverManager.getConnection(this.url.getUrl(), this.url.getUser(), this.url.getPass());
            Statement state = connect.createStatement();
            String query = String.format("select id, username, password, email from %s", this.url.getTablename());
            ResultSet result = state.executeQuery(query);
            DefaultTableModel dtm = (DefaultTableModel) sqltable.getModel();
            int rows = dtm.getRowCount();
            
            for(int i=rows - 1;i >= 0;i--)
            {
                dtm.removeRow(i);
            }
            
            while(result.next())
            {
                String idd= result.getString(1);
                String userr = result.getString(2);
                String passs = result.getString(3);
                String emaill = result.getString(4);
                Object[] content = {idd, userr, passs, emaill};
                DefaultTableModel dtm2 = (DefaultTableModel) sqltable.getModel();
                dtm2.addRow(content);
            }
            
            state.close();
            connect.close();
            
        }
        catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex)
        {
            throw ex;
        }
    }
    
    public void Delete(int id) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException
    {
        this.id = id;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection connect = DriverManager.getConnection(this.url.getUrl(), this.url.getUser(), this.url.getPass());
            Statement state = connect.createStatement();
            String query = String.format("delete from %s where id = %s",this.url.getTablename(), this.id);
            state.executeUpdate(query);
            state.close();
            connect.close();
        }
        catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex)
        {
            throw ex;
        }
         
    }
    
    public void Searching(JTable jtable) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException
    {
        
        try
        {
            
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection connect = DriverManager.getConnection(this.url.getUrl(), this.url.getUser(), this.url.getPass());
            Statement state = connect.createStatement();
            String query = String.format("select id, username, password, email from %s where id = %s or username = '%s'",this.url.getTablename(), this.in2.getId(), this.in2.getUser());
            ResultSet result = state.executeQuery(query);
            DefaultTableModel tblmodel = (DefaultTableModel) jtable.getModel();
            int rows = tblmodel.getRowCount();
            
            for(int i=rows - 1;i>=0;i--)
            {
                tblmodel.removeRow(i);
            }
            
            while(result.next())
            {
                String idd= result.getString(1);
                String userr = result.getString(2);
                String passs = result.getString(3);
                String emaill = result.getString(4);
                Object[] content = {idd, userr, passs, emaill};
                DefaultTableModel tbl = (DefaultTableModel) jtable.getModel();
                tbl.addRow(content);
            }
            state.close();
            connect.close();
        }
        
        catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex)
        {
            throw ex;
        }
    }
    
    public void Update(JTable jtable) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            try (Connection connect = DriverManager.getConnection(this.url.getUrl(), this.url.getUser(), this.url.getPass())) {
                Statement state = connect.createStatement();
                String query = String.format("update '%s' set ", this.url.getTablename());
                
                if(!("".equals(this.inup.getUser())))
                {
                    query = query + "username=" + this.inup.getUser() + ",";
                }
                
                if(!("".equals(this.inup.getName())))
                {
                    query = query + "name=" + this.inup.getName() + ",";
                }
                
                if(!("".equals(this.inup.getCellphone())))
                {
                    query = query + "cellphone=" + this.inup.getCellphone() + ",";
                }
                
                if(!("".equals(this.inup.getPass())))
                {
                    query = query + "password=" + this.inup.getPass() + ",";
                }
                
                if(!("".equals(this.inup.getAge())))
                {
                    query = query + "age=" + this.inup.getAge() + ",";
                }
                int lst = query.lastIndexOf(',');
                char[] query_list;
                query_list = query.toCharArray();
                query_list[lst] = ' ';
                query = Arrays.toString(query_list);
                
                query = query + String.format("where id = '%s' or username='%s'", this.in2.getId(), this.in2.getUser());
                state.execute(query);
                
                query = String.format("select id, username, password, email from %s", this.url.getTablename());
                ResultSet result = state.executeQuery(query);
                DefaultTableModel tblmodel = (DefaultTableModel) jtable.getModel();
                int rows = tblmodel.getRowCount();
                
                for(int i=rows - 1;i>=0;i--)
                {
                    tblmodel.removeRow(i);
                }
                
                while(result.next())
                {
                    String idd= result.getString(1);
                    String userr = result.getString(2);
                    String passs = result.getString(3);
                    String emaill = result.getString(4);
                    Object[] content = {idd, userr, passs, emaill};
                    tblmodel.addRow(content);
                }
                
                state.close();
            }
            
        }
        catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex)
        {
            ex.printStackTrace();
            throw ex;
        }
    }

    @Autowired
    public void GetUrl(URLs url)
    {
        this.url = url;
    }
    
    
    
    @Autowired
    public void GetInputs2(Inputs_2 in2)
    {
        this.in2 = in2;
    }
    
    @Autowired
    public void GetInputupdate(Input_update inup)
    {
        this.inup = inup;
    }
}
