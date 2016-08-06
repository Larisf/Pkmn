/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkmn;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebEngine;
import javafx.stage.Modality;
import javafx.stage.Stage;

/** 
 *
 * @author Bambi^
 */
public class loginView extends Stage
{
	private static TextArea console;
	private static String path;
	private static WebEngine webEngine;
	public loginView(Stage primaryStage, WebEngine webEngine, TextArea console) 
	{
			showLoginView(primaryStage);
			loginView.console = console;
			loginView.webEngine = webEngine;
	}	
	public loginView(String path) 
	{
		loginView.path = path;
	}
	private void showLoginView(Stage prim)
	{
		Stage login = new Stage();
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10.0));
        grid.setVgap(5.0);
        grid.setHgap(10.0);
		
		Label l1 = new Label("Auth: ");
        Label l2 = new Label("Account: ");
		Label l3 = new Label("Passwort: ");
		Label l4 = new Label("Ort: ");
		Label l5 = new Label("Radius: ");
        Label l6 = new Label("Scan-Delay: ");
		Label l7 = new Label("Ip: ");
		Label l8 = new Label("Port: ");
		Label l9 = new Label("Arenen anzeigen: ");
		Label l11 = new Label("Lock-Module anzeigen: ");
		TextField auth = new TextField(null);
        TextField user = new TextField(null);
        PasswordField pass = new PasswordField();
		TextField location = new TextField(null);
		TextField radius = new TextField("5");
		TextField autoRefresh = new TextField("10");
		TextField ip = new TextField("127.0.0.1");
		TextField port = new TextField("5000");
		
		CheckBox gym = new CheckBox();
		CheckBox lured = new CheckBox();
		gym.setDisable(true);
		lured.setDisable(true);
		
		Button start = new Button("Login");
				start.setOnAction((ActionEvent event) -> {
					if(path == null) console.appendText("Keinen Pfad angegeben!\n");
					else
					{
						Buttons btn = new Buttons(auth, user, pass, location, radius, autoRefresh,ip,port,webEngine, gym, lured,console, login);
						btn.start();
					}
		});
		Button choose = new Button("Ordner wählen");
		choose.setOnAction((ActionEvent event) -> {
			Buttons btn1 = new Buttons(prim, console);
			btn1.Datei();
		});

		
		
		grid.add(l1, 1 ,0);
        grid.add(l2, 1, 1);
		grid.add(l3, 1, 2);
		grid.add(l4, 1, 3);
		grid.add(l5, 1, 4);
		grid.add(l6, 1, 5);
		grid.add(l7, 1, 6);
		grid.add(l8, 1, 7);
		grid.add(l9, 1, 8);
		grid.add(l11,1, 9);
        grid.add(auth, 2, 0);
        grid.add(user, 2, 1);
        grid.add(pass, 2, 2);
		grid.add(location, 2,3);
		grid.add(radius, 2,4);
		grid.add(autoRefresh, 2,5);
		grid.add(ip, 2,6);
		grid.add(port,2,7);
		grid.add(gym, 2,8);
		grid.add(lured,2,9);
		grid.add(start, 1, 12);
		grid.add(choose,2 ,12);
		
		login.setTitle("Login");
		login.initOwner(prim);
		login.initModality(Modality.WINDOW_MODAL);
		Scene sc = new Scene(grid);
		login.setScene(sc);
		login.show();
	}
}
