package SmarterPOS;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.security.PrivateKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Controller {
    ///@FXML
    TextField usertext,passtext;

    public void doConnection(){

        Connection con = null;
        PreparedStatement stmt=null;

        try {

            Class.forName("com.mysql.jdbc.Driver");
            if(con == null) {
                con = DriverManager.getConnection("jdbc:mysql://81.8.2.7:3306/rd?useSSL=false", "hazem", "123456hN");

                //get data from user
                String user = usertext.getText();
                String pass= passtext.getText();

                // Create Query
                String query = "Select * from permanent_users where username=?  and id=?";
                stmt= con.prepareStatement(query);
                stmt.setString(1,user);
                stmt.setString(2,pass);

                //Result

                ResultSet set = stmt.executeQuery();
                if(set.next()) {
                    //Method To Bring the Administration Panel
                    //Alreat Box For Error
                   // Main ob = new Main();
                   // ob.doAdminWindow();
                    stmt.close();
                    con.close();
                }

                else {
                    //Alreat Box For Error
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Login Error");
                    alert.setContentText("Username Or Password Are Incorrect");
                    alert.showAndWait();
                }
            }



        }

        catch (Exception e) {
            //Alreat Box For Error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Connection Error");
            alert.setHeaderText("Connection Info");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }



    }


}

