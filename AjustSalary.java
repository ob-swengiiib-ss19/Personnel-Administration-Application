package de.PersonalApp.PersonSalary;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.PersonalApp.NotClass.Note;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AjustSalary extends Button {
	
	public AjustSalary(String text) {
		super(text);
	}
	
	public void newWindow(Stage mainWindow) {
		
	GridPane grid = new GridPane();
	grid.setAlignment(Pos.BASELINE_CENTER);
	grid.setHgap(10);
	grid.setVgap(10);
	grid.setPadding(new Insets(10,10, 10, 10));
	
	Scene scene = new Scene(grid, 400, 300);
	Stage newWindow = new Stage();
	Stage noteWindow = new Stage();
	
	newWindow.setTitle("Gehalt anpassen");
	newWindow.setScene(scene);
	
	newWindow.initModality(Modality.WINDOW_MODAL);
	newWindow.initOwner(mainWindow);
	
	Text text = new Text("Geben Sie das neue Gehalt ein");
	text.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
	grid.add(text, 0, 0, 2, 1);
	
	Label employeenr = new Label("Personalnummer:");
	grid.add(employeenr, 0, 1);
	
	TextField input_employeenr = new TextField();
	grid.add(input_employeenr, 1, 1);
	
	Label newsalary= new Label("Neues Gehalt:");
	grid.add(newsalary, 0, 2);
	
	TextField input_newsalary = new TextField();
	grid.add(input_newsalary, 1, 2);
	
	Button ok_btn = new Button("Gehalt anpassen");
	grid.add(ok_btn, 0, 3);
	newWindow.show();
	
	ok_btn.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent e) {
			String employeenr = "";
			Double newsalary = 0.0;
			boolean inputIsCorrect = false;
			
			try {
				employeenr = input_employeenr.getText();
				newsalary = Double.parseDouble(input_newsalary.getText());
				
				if(employeenr != "" && newsalary !=0) {
					inputIsCorrect = true;
				}else {
					Note.output(noteWindow, "die Felder dürfen nicht leer sein");
				}
				
			}catch(NumberFormatException message) {
				System.out.println(message.getMessage());
				Note.output(noteWindow, "Bitte geben Sie die richtige Werte ein!");
			}
			
			if(inputIsCorrect) {
				String host = "jdbc:mysql://localhost:3306/employees";
				String username = "root";
				String password = "Eya2Soedo";
				
				try {
					Connection dbconnect = DriverManager.getConnection(host, username, password);
					Statement statement = dbconnect.createStatement();
					String sqlQuery = "SELECT * FROM employees.employees WHERE employeeNumber = " + employeenr;
					ResultSet result = statement.executeQuery(sqlQuery);
					
					int counter = 0;
					while (result.next()) {
						counter++;
						}
					if(counter > 0) {
						sqlQuery = "UPDATE employees SET salary = '"+newsalary+"' WHERE employeeNumber = "+employeenr;
						statement.executeUpdate(sqlQuery);
						Note.output(noteWindow, "Gehalt wurde erfolgreich geändert");
						newWindow.close();
					}else {
						Note.output(newWindow, "kein Mitarbeiter mit dem eingegeben Nummer gefunden!\n Bitte versuhen Sie es erneut");
					}
				}catch(SQLException err) {
					System.out.println(err.getMessage());
				}
			}
		}
	});
	}
	
}
