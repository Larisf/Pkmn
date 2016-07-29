package pkmn;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class Buttons 
{
	private TextField auth;
	private TextField user;
	private TextField location;
	private TextField radius;
	private TextField autoRefresh;
	private TextField ip;
	private TextField port;
	private PasswordField pass;
	private WebEngine webEngine;
	private CheckBox gym;
	private CheckBox lured;
	private TextArea console;
	private Stage primaryStage;
	private String path;
	
	public Buttons(){}
	public Buttons(TextField auth, TextField user, PasswordField pass, TextField location, TextField radius,TextField autoRefresh, WebEngine webEngine, CheckBox gym, CheckBox lured, TextArea console, String path)
	{
		this.auth = auth;
		this.user = user;
		this.location = location;
		this.radius = radius;
		this.pass = pass;
		this.autoRefresh = autoRefresh;
		this.webEngine = webEngine;
		this.gym = gym;
		this.lured = lured;
		this.console = console;
		this.ip = ip;
		this.port = port;
		this.path = path;
	}
	public Buttons(WebEngine webEngine)
	{
		this.webEngine = webEngine;
	}
	public Buttons(Stage primaryStage)
	{
		this.primaryStage = primaryStage;
	}
	public void start()
	{
	//	if (path == null) console.appendText("Kein Dateipfad angegeben!\n"+path+"\n");
	//	else 
		{
		try 
		{
			
			Pkmn pkmn = new Pkmn(auth.getText(),user.getText(),pass.getText(),location.getText(),radius.getText(), autoRefresh.getText(),getGym(),getLured(),console, path);
			pkmn.run();
		}
		catch(NullPointerException e)
		{
			console.appendText("Bitte die Notwendigen Felder ausfüllen!\n");
		}
		catch(NumberFormatException e)
		{
			console.appendText("Bitte gültige Werte eingeben!\n");
		}
		catch (Exception ex) 
		{
			Logger.getLogger(Buttons.class.getName()).log(Level.SEVERE, null, ex);
		}
		}
		
	}
	public void refresh()
	{
		webEngine.load("http://127.0.0.1:5000");
	}
	public void stopp() throws IOException
	{
		try
		{
			Runtime.getRuntime().exec("taskkill /F /IM Python.exe");
			Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
		} 
		catch (IOException ex) 
		{
			Logger.getLogger(Pkmn_Gui.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
		public void beenden() throws IOException
	{
		try
		{
			Runtime.getRuntime().exec("taskkill /F /IM Python.exe");
			Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
		} 
		catch (IOException ex) 
		{
			Logger.getLogger(Pkmn_Gui.class.getName()).log(Level.SEVERE, null, ex);
		}
		System.exit(0);
		
		
	}
	/*	public void Datei()
		{
			 DirectoryChooser directoryChooser = new DirectoryChooser();
                File selectedDirectory = 
                        directoryChooser.showDialog(primaryStage);
                if(selectedDirectory == null){
                    console.appendText("No Directory selected\n");
                }else
				{
                   console.appendText(selectedDirectory.getAbsolutePath()+"\n");
					//path = selectedDirectory.getAbsolutePath();
                }  
		}*/
	public String getGym()
	{
		String aGym = "";
		if(gym.isSelected() == true)
			aGym = "-dg";
		else
			aGym = "";
		return aGym;		
	}
	public String getLured()
	{
		String aLured ="";
		if(lured.isSelected() ==true)
			aLured = "-ol";
		else
			aLured = "";
		return aLured;
	}	
}
	