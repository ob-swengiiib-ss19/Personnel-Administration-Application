package de.PersonalApp.NotClass;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Note {
	public static void output(Stage window, String text) {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 10, 10));
		
		Scene scene = new Scene(grid, 300, 200);
		
		Label label = new Label(text);
		Button ok_btn = new Button("OK");
		grid.add(label, 0, 0);
		grid.add(ok_btn, 0, 1);
		
		Stage noteWindow = new Stage();
		noteWindow.initModality(Modality.WINDOW_MODAL);
		noteWindow.initOwner(window);
		//noteWindow.setX(window.getX() + 30);
		//noteWindow.setY(window.getY() + 30);
		noteWindow.setScene(scene);
		noteWindow.show();
		/*
		 * Platform.runLater(() -> { window.show(); });
		 */
		
		ok_btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				noteWindow.close();
			}
		});
		
	}

}
