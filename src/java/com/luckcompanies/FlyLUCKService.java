/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luckcompanies;

/**
 *
 * @author johnkirksey
 */
import java.lang.String;
import java.sql.*;
import com.google.gson.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.luckcompanies.Helpers;
/**
 *
 * @author johnkirksey
 */



public class FlyLUCKService {
    
    
    public String GetFlights(String fromDate, String toDate){
        
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        String json = "";
        
        try{
            cn = DBConnect();
            st = cn.createStatement();
            String sql = "select l.LEGID, l.TRIPNUM, ORIGIN.ICAO as ORIGIN, FROMAIRPORTNAME, DEST.ICAO as DEST, TOAIRPORTNAME, l.LOCALLEAVE from t_Leg l\n" +
                    "JOIN t_Airport ORIGIN on ORIGIN.airportID = l.FROMAIRPORTID\n" +
                    "JOIN t_Airport DEST on DEST.airportID = l.TOAIRPORTID\n" +
                    "where l.LEGLOCALDATE BETWEEN '" + fromDate + "' AND '" + toDate + "'\n" +
                    "ORDER BY l.LEGLOCALDATE";
            rs = st.executeQuery(sql);
            
            Gson gson = new Gson();
            json = gson.toJson(RSToArrayList(rs));
            //System.out.println(json);
            /*while(rs.next())
            {
                System.out.println(rs.getString(1) + " " + rs.getString(3) + " " + rs.getString(5)); 
            }*/
        }
        catch (SQLException se)
        {
            System.out.println(se.toString());
        }
        finally {  
            if (rs != null) try { rs.close(); } catch(Exception e) {}  
            if (st != null) try { st.close(); } catch(Exception e) {}  
            if (cn != null) try { cn.close(); } catch(Exception e) {}  
        }  
        return json;
    }
    
    private Connection DBConnect()
    {
        Helpers helper = new Helpers();
        
        Connection cn = null;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String conn = helper.GetConnectionString();
            cn = DriverManager.getConnection(conn);
        }
        catch (ClassNotFoundException e){
            System.out.println(e.toString());
        }
        catch (SQLException se){
            System.out.println(se.toString());
        }
        return cn;
    }
    
    private ArrayList RSToArrayList(ResultSet rs)
    {
        ArrayList al = new ArrayList();
        try{
            ResultSetMetaData rsmd = rs.getMetaData();
            
            while(rs.next())
            {
                Map<String, String> map = new HashMap<String, String>();
                map.put(rsmd.getColumnName(1), rs.getString(1));
                map.put(rsmd.getColumnName(2), rs.getString(2));
                map.put(rsmd.getColumnName(3), rs.getString(3));
                map.put(rsmd.getColumnName(4), rs.getString(4));
                map.put(rsmd.getColumnName(5), rs.getString(5));
                map.put(rsmd.getColumnName(6), rs.getString(6));
                map.put(rsmd.getColumnName(7), rs.getString(7));
                al.add(map);
            }
        }
        catch(SQLException se)
        {}
        
        return al;
    }
    
    
}
