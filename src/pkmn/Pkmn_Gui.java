package pkmn;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Pkmn_Gui extends Application
{
	Buttons btn = new Buttons();
	public Pkmn_Gui(){}
	private static String path=null;
	@Override
	public void start(Stage primaryStage) throws InterruptedException 
	{
		
		AnchorPane grid = new AnchorPane();
		grid.setPadding(new Insets(10.0));
        //grid.setVgap(5.0);
        //grid.setHgap(10.0);

		TextArea console = new TextArea(null);
		WebView browser = new WebView();
		WebEngine webEngine =  browser.getEngine();
		webEngine.setJavaScriptEnabled(true);
		webEngine.setUserAgent("AppleWebKit/537.44");
		MenuBar menuBar = new MenuBar();
 
		Menu options = new Menu("Optionen");
		MenuItem start = new MenuItem("Start");
		start.setAccelerator(KeyCombination.keyCombination("F4"));
				start.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				btn.start();
			}
		});

		MenuItem refresh = new MenuItem("Aktualisieren");		
		refresh.setAccelerator(KeyCombination.keyCombination("F5"));
		refresh.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				btn = new Buttons(webEngine);
				btn.refresh();
			}
		});
		
		MenuItem stopp = new MenuItem("Stopp");
		stopp.setAccelerator(KeyCombination.keyCombination("F6"));
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
		
		MenuItem quit = new MenuItem("Beenden");
		quit.setAccelerator(KeyCombination.keyCombination("F12"));
		quit.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				try {
					btn.beenden();
				} catch (IOException ex) {
					Logger.getLogger(Pkmn_Gui.class.getName()).log(Level.SEVERE, null, ex);
				}
        	}
		});
	
        Menu login = new Menu("Login");
		MenuItem log = new MenuItem("Login");
		log.setOnAction(new EventHandler<ActionEvent>() 
		{     @Override
            public void handle(ActionEvent event) {
				loginView loginV = new loginView(primaryStage,webEngine, console);

            }
        });
 
        Menu dir = new Menu("Ordner");
		MenuItem choose = new MenuItem("Auswählen");
		choose.setOnAction(new EventHandler<ActionEvent>() 
		{     @Override
            public void handle(ActionEvent event) {
			   Buttons btn = new Buttons(primaryStage, console);
               btn.Datei();  
            }
    
        });
 		
        grid.setTopAnchor(browser,22.0);
		grid.setLeftAnchor(browser,0.0);
		grid.setRightAnchor(browser,0.0);
		grid.setBottomAnchor(browser,200.0);
        grid.setBottomAnchor(console,0.0);
		grid.setLeftAnchor(console,0.0);
		grid.setRightAnchor(console,0.0);
		grid.setTopAnchor(menuBar,0.0);
		grid.setLeftAnchor(menuBar,0.0);
		grid.setRightAnchor(menuBar,0.0);
		grid.getChildren().addAll(browser,console);

		options.getItems().addAll(start,refresh,stopp,quit);
		login.getItems().addAll(log);
		dir.getItems().addAll(choose);

        menuBar.getMenus().addAll(options, login, dir);
 
		console.appendText("Radius: 1 = 200m\nScan-Delay: in Sekunden(Standart: 10)\nRegion: z.B. Adlerweg, Ahaus, Ger\n");
		console.setEditable(false);
		Scene scene = new Scene(grid);
 
        ((AnchorPane) scene.getRoot()).getChildren().addAll(menuBar);
        primaryStage.setTitle("PokemonGo Map");
        primaryStage.setScene(scene);
        primaryStage.show();		
	}
	public static void main(String[] args) {
		launch(args);
	}
}
