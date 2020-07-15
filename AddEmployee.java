package de.PersonalApp.AddClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddEmployee extends Button {

	public AddEmployee(String text) {
		super(text);
	}

	public void newWindow(Stage mainWindow) {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.BASELINE_CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 10, 10));
		Scene scene = new Scene(grid, 400, 400, Color.AQUA);
		Stage newWindow = new Stage();
		Stage noteWindow = new Stage();

		newWindow.setTitle("Mitarbeiter hinzufügen");
		newWindow.setScene(scene);

		/*
		 * noteWindow.setTitle("Fehlermeldung"); noteWindow.setScene(new Scene(grid,
		 * 200, 300));
		 */

		newWindow.initModality(Modality.WINDOW_MODAL);
		newWindow.initOwner(mainWindow);
		// newWindow.setX(mainWindow.getX());
		// newWindow.setY(mainWindow.getHeight() + 50);

		Text text = new Text("Geben Sie die Daten des neuen Mitarbeiter ein:");
		text.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
		grid.add(text, 0, 0, 2, 1);

		Label employNumber = new Label("personr");
		grid.add(employNumber, 0, 1);

		TextField input_employeeNumber = new TextField();
		grid.add(input_employeeNumber, 1, 1);

		Label lastname = new Label("Nachname");
		grid.add(lastname, 0, 2);

		TextField input_lastname = new TextField();
		grid.add(input_lastname, 1, 2);

		Label firstname = new Label("vorname");
		grid.add(firstname, 0, 3);

		TextField input_firstname = new TextField();
		grid.add(input_firstname, 1, 3);

		Label salary = new Label("gehalt");
		grid.add(salary, 0, 4);

		TextField input_salary = new TextField("0");
		grid.add(input_salary, 1, 4);

		Button add_btn = new Button("Mitarbeiter hinzufügen");
		grid.add(add_btn, 0, 5);
		newWindow.show();
		// update gui
		/*
		 * Platform.runLater(() -> { grid.getChildren().setAll(); mainWindow.show(); });
		 */

		add_btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				String employeeNumber = "";
				String lastname = "";
				String firstname = "";
				double salary = 0;
				boolean inputIsCorrect = false;

				try {
					employeeNumber = input_employeeNumber.getText();
					lastname = input_lastname.getText();
					firstname = input_firstname.getText();
					salary = Double.parseDouble(input_salary.getText());

					if (salary != 0 && employeeNumber != "" && lastname != "" && firstname != "") {
						inputIsCorrect = true;
					}

					else {
						System.out.println("kein Feld darf leer sein");
						Note.output(noteWindow, "Bitte geben Sie die richtige Werte ein!");
					}

				} catch (NumberFormatException message) {
					System.out.println(message.getMessage());
					Note.output(noteWindow, "Bitte geben Sie die richtige Werte ein!");
				}

				if (inputIsCorrect) {
					String host = "jdbc:mysql://localhost:3306/employees";
					String username = "root";
					String password = "Eya2Soedo";

					try {
						Connection dbconnection = DriverManager.getConnection(host, username, password);
						Statement statement = dbconnection.createStatement();
						String sqlQuery = "SELECT lastname FROM employees WHERE employeeNumber = " + employeeNumber;
						ResultSet result = statement.executeQuery(sqlQuery);

						int counter = 0;
						while (result.next()) {
							counter++;
						}
						if (counter == 0) {
							sqlQuery = "INSERT INTO employees (employeeNumber, lastname, firstname, salary) VALUES ('"
									+ employeeNumber + "', '" + lastname + "', '" + firstname + "', '" + salary + "')";
							statement.executeUpdate(sqlQuery);
							Note.output(noteWindow, "Mitaerbeiter erfogreich hinzugefügt");
							newWindow.close();
						} else {
							Note.output(noteWindow, "Personalnummer ist bereit vorhanden");
						}

					} catch (SQLException err) {
						System.out.println(err.getMessage());
					}
				}

			}

		});

	}
}
