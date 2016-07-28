/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkmn;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 *
 * @author Bambi^
 */
public class Pkmn_Gui extends Application
{
	Buttons btn = new Buttons();
	public Pkmn_Gui(){}
	@Override
	public void start(Stage primaryStage) throws InterruptedException 
	{
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10.0));
        grid.setVgap(5.0);
        grid.setHgap(10.0);
        
        Label l1 = new Label("Auth: ");
        Label l2 = new Label("Account: ");
		Label l3 = new Label("Passwort: ");
		Label l4 = new Label("Ort: ");
		Label l5 = new Label("Radius: ");
        Label l6 = new Label("Auto-Refresh: ");
		Label l7 = new Label("Ip: ");
		Label l8 = new Label("Port: ");
		Label l9 = new Label("Arenen anzeigen: ");
		Label l11 = new Label("Nur Lock-Module anzeigen: ");
		
		TextArea console = new TextArea("");
        TextField auth = new TextField("");
        TextField user = new TextField("");
        PasswordField pass = new PasswordField();
		TextField location = new TextField();
		TextField radius = new TextField("5");
		TextField autoRefresh = new TextField("3");
		TextField ip = new TextField("");
		TextField port = new TextField("");
		
		CheckBox gym = new CheckBox();
		CheckBox lured = new CheckBox();
		
		WebView browser = new WebView();
		WebEngine webEngine =  browser.getEngine();

        Button start = new Button("Start");
		start.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				btn = new Buttons(auth, user, pass, location, radius, autoRefresh, webEngine, gym, lured, console);
				btn.start();
			}
		});
		
		Button refresh = new Button("Refresh");
		refresh.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				btn = new Buttons(webEngine);
				btn.refresh();
			}
		});
		
		Button stopp = new Button("Beenden");
		stopp.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				try {
					btn.stopp();
				} catch (IOException ex) {
					Logger.getLogger(Pkmn_Gui.class.getName()).log(Level.SEVERE, null, ex);
				}
        	}
		});

        grid.add(browser, 0, 0, 1, 15);
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
        grid.add(start, 2, 12);
		grid.add(refresh,1, 12);
		grid.add(stopp,3,12);
		grid.add(console,0,16);
        
		Scene scene = new Scene(grid);
        primaryStage.setTitle("PokemonGo Map");
        primaryStage.setScene(scene);
        primaryStage.show();		
	}
	public static void main(String[] args) {
		launch(args);
	}
}
