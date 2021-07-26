package com.example.codebasev1;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Base64;
import android.util.Log;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

    private int bill_amount =25;
    private double city_average =0;
    private String username;
    private String password;
    private String mail;
    private int user_id;
    private String images;
    private byte barr[];
    Connection con;


    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public byte[] getBarr() {
        return barr;
    }

    public void setBarr(byte[] barr) {
        this.barr = barr;
    }


    public String getImages() { return images; }

    public void setImages(String images) { this.images = images; }


    public int getUser_id() { return user_id; }

    public void setUser_id(int user_id) { this.user_id = user_id; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getCity_average() { return city_average; }

    public void setCity_average(double city_average) { this.city_average = city_average; }


    public int getBill_amount() {
        return bill_amount;
    }

    public void setBill_amount(int bill_amount) {
        this.bill_amount = bill_amount;
    }

    public boolean login_connection(){
        Connection con = connectionclass();
        String user_name,pass;
        user_name=getUsername();
        pass=getPassword();
        try{
            byte barr1[];
            String query = "select * from users where username= '"+ user_name+ "' and password = '"+crypt(pass)+"'  ";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next())
            {

                query = "select * from images where user_id='"+ rs.getString("Id")+ "'";
                stmt = con.createStatement();
                ResultSet rs1 = stmt.executeQuery(query);

                if(rs1.next()){
                    barr1=(byte[])rs1.getBytes("image");
                    setBarr(barr1);
                    setImages(new String(rs1.getBytes("image"), Charset.forName("UTF-8"))) ;
                }
                /*if(rs1.next())
                {
                    //img=rs.getString("image");
                    Log.e("Image",""+rs1.getBytes(1));
                }*/

                con.close();
                return true;
            }
            else
                return false;

        }catch (SQLException e){
            Log.e("Error:",e.toString());
            return false;
        }
    }

    public boolean setPic(){
        Connection con = connectionclass();
        String user_name,email;
        user_name=getUsername();
        try{
            byte barr1[];
            String query = "select * from users where username= '"+ user_name+ "' ";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next())
            {
                email=rs.getString("email");
                setMail(email);
                query = "select * from images where user_id='"+ rs.getString("Id")+ "'";
                stmt = con.createStatement();
                ResultSet rs1 = stmt.executeQuery(query);

                if(rs1.next()){
                    barr1=(byte[])rs1.getBytes("image");
                    setBarr(barr1);
                }
                con.close();
                return true;
            }
            else
                return false;

        }catch (SQLException e){
            Log.e("Error:",e.toString());
            return false;
        }
    }

    public void findUserId(){
        Connection con = connectionclass();
        String user_name=getUsername();
        try{
            String query = "select id from users where username= '"+ user_name+ "'";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next())
            {
                setUser_id(rs.getInt(1)) ;
                con.close();
            }
            else
                setUser_id(0) ;

        }catch (SQLException e){
            Log.e("Error:",e.toString());
            setUser_id(0) ;
        }
    }
    public void put_UUI(String uui){
        Connection con = connectionclass();
        Log.e("User",getUser_id()+" -- Ui:"+uui);
        try{
            String query = "UPDATE  QR_Code SET user_id='"+getUser_id()+ "' where code= '"+ uui+ "'";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            con.close();
        }catch (SQLException e){
            Log.e("Error:","Invalid QR Code.");
        }
    }

    @SuppressLint("NewApi")
    public Connection connectionclass()
    {
        String un,pass,db,ip;
//  Data Source=DESKTOP-QFO4P47;Initial Catalog=proje;Integrated Security=True
        ip="";
        db = "";
        un = "";
        pass = "";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;
        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "jdbc:jtds:sqlserver://" + ip+ ":1433;user=" + un+ ";password=" + pass + ";";
            connection = DriverManager.getConnection(ConnectionURL);
        }
        catch (SQLException se)
        {
            Log.e("error here 1 : ", se.getMessage());
        }
        catch (ClassNotFoundException e)
        {
            Log.e("error here 2 : ", e.getMessage());
        }
        catch (Exception e)
        {
            Log.e("error here 3 : ", e.getMessage());
        }
        return connection;
    }


    public static String crypt(String password) {
        try {
            MessageDigest digest = null;
            try {
                digest = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException e1) {
                e1.printStackTrace();
            }
            digest.reset();
            return bin2hex(digest.digest(password.getBytes()));
        } catch (Exception ignored) {
            return null;
        }
    }
    private static String bin2hex(byte[] data) {
        StringBuilder hex = new StringBuilder(data.length * 2);
        for (byte b : data)
            hex.append(String.format("%02x", b & 0xFF));
        return Base64.encodeToString(data, Base64.NO_WRAP);
    }
}
