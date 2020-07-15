package de.PersonalApp.PersonelAdmin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import de.PersonalApp.AddClass.*;
import de.PersonalApp.PersonSalary.AjustSalary;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
    	      
        String host = "jdbc:mysql://localhost:3306/employees";
        String username = "root";
        String password = "Eya2Soedo";
        
        try {
        	Connection connect = DriverManager.getConnection(host, username, password);
        	Statement query = connect.createStatement();
        	String sqlQuery = "CREATE TABLE IF NOT EXISTS employees (employee_id BIGINT PRIMARY KEY Not Null AUTO_INCREMENT, employeeNumber VARCHAR(45), lastname VARCHAR(45), firstname VARCHAR(45), salary DECIMAL(9,2))";
        	query.execute(sqlQuery);
            connect.close();
        }catch(SQLException err) {
        	System.out.println(err.getMessage());
        }
        stage.setTitle("Personalverwaltung");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        
        grid.setHgap(30);
        grid.setVgap(50);      
        grid.setPadding(new Insets(20, 20, 20, 20));

        Text actionToChoose_text = new Text("Wählen Sie eine Aktion aus");
        actionToChoose_text.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        grid.add(actionToChoose_text, 0, 0, 2, 1);
        
        AddEmployee add_btn = new AddEmployee("Mitarbeiter hinzufügen");
        grid.add(add_btn, 0, 1);
        add_btn.setPrefSize(200, 50);
        
        Button remove_btn = new Button("Mitarbeiter entfernen");
        grid.add(remove_btn, 1, 1);
        remove_btn.setPrefSize(200, 50);
        
        AjustSalary changeSalary_btn = new AjustSalary("Gehalt anpassen");
        grid.add(changeSalary_btn, 0, 2);
        changeSalary_btn.setPrefSize(200, 50);
        
        Button showPerson_btn = new Button("Mitarbeiter anzeigen");
        grid.add(showPerson_btn, 1, 2);
        showPerson_btn.setPrefSize(200, 50);
        
        Button ok_btn = new Button("OK");
        grid.add(ok_btn, 0, 3, 2, 1);
        ok_btn.setPrefSize(100, 50);
        grid.setHalignment(ok_btn, HPos.CENTER);
        
        add_btn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		add_btn.newWindow(stage);
        	}
        });
        
        remove_btn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		
        	}
        });
        
        changeSalary_btn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		changeSalary_btn.newWindow(stage);
        	}
        });
        
        showPerson_btn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		
        	}
        });
        
        ok_btn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		
        		stage.close();
        	}
        });
        
        
        
        
        
        
        
        
     // create a scene 
        Scene scene = new Scene(grid, 1000, 600, Color.CORNFLOWERBLUE); 
  
        // set the scene 
        stage.setScene(scene); 
  
        stage.show(); 
    }

    public static void main(String[] args) {
        launch(args);
    }

}