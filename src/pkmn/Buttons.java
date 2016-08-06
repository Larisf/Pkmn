package pkmn;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
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
	private static TextField auth;
	private static TextField user;
	private static TextField location;
	private static TextField radius;
	private static TextField autoRefresh;
	private static TextField ip;
	private static TextField port;
	private static PasswordField pass;
	private static WebEngine webEngine;
	private static CheckBox gym;
	private static CheckBox lured;
	private static TextArea console;
	private static Stage primaryStage;
	private static String path;
	private static Boolean first = true;
	private static Stage login;
	private static final String EXEC = "runserver.py";	
	private String name;
	
	public Buttons(){}
	public Buttons(TextField auth, TextField user, PasswordField pass, TextField location, TextField radius,TextField autoRefresh,TextField ip,TextField port, WebEngine webEngine, CheckBox gym, CheckBox lured, TextArea console, Stage login)
	{
		Buttons.auth = auth;
		Buttons.user = user;
		Buttons.location = location;
		Buttons.radius = radius;
		Buttons.pass = pass;
		Buttons.autoRefresh = autoRefresh;
		Buttons.webEngine = webEngine;
		Buttons.gym = gym;
		Buttons.lured = lured;
		Buttons.console = console;
		Buttons.ip = ip;
		Buttons.port = port;
		Buttons.login = login;
	}
	public Buttons(WebEngine webEngine)
	{
		Buttons.webEngine = webEngine;
	}
	public Buttons(Stage primaryStage, TextArea console)
	{
		Buttons.console = console;
		Buttons.primaryStage = primaryStage;
	}
	public void start()
	{
		if (path == null) console.appendText("Keinen Dateipfad angegeben!\n");
		else 
		{
		try 
		{	
			Pkmn pkmn = new Pkmn(EXEC,auth.getText(),user.getText(),pass.getText(),location.getText(),radius.getText(), autoRefresh.getText(),getGym(),getLured(),console, path,port.getText(),ip.getText());
			if((auth.getText().toUpperCase().equals("PTC"))||(auth.getText().toUpperCase().equals("GOOGLE")))
				if(user.getText() != null)
					if(pass.getText().length() != 0)
						if(location.getText() != null)
							if(Integer.parseInt(radius.getText()) != 0)
								if(Integer.parseInt(autoRefresh.getText()) != 0)
								{
									pkmn.run();
									login.close();
								}
								else console.appendText("Bitte einen Wert größer 0 eingeben!\n");
							else console.appendText("Bitte einen Radius größer 0 eingeben!\n");
						else console.appendText("Bitte Region eingeben!\n");
					else console.appendText("Bitte Passwort eingeben!\n");
				else console.appendText("Bitte Account namen eingeben!\n");
			else console.appendText("Bitte google oder ptc eingeben!\n");
		}
		catch(NullPointerException e)
		{
			console.appendText("Bitte die Notwendigen Felder ausfüllen!\n");
		}
		catch(NumberFormatException e)
		{
			console.appendText("Bitte gültige Werte eingeben!\n");
		}
		catch (IOException | InterruptedException e) 
		{
			System.out.println("something went wrong");
		}
		}	
	}
	public void refresh()
	{
		if (first == true)
		{
			webEngine.load("http://"+ip.getText()+":"+port.getText());
			first = false;
		}
		else
			webEngine.reload();
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
		
	public void Datei()
	{
		DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(primaryStage);
        if(selectedDirectory == null)
		{
           console.appendText("No Directory selected\n");
        }
		else
		{
			path = selectedDirectory.getAbsolutePath();
			File dir = new File(path);
			FilenameFilter filter = (File dir1, String name1) -> name1.toLowerCase().equals(EXEC);
			String[] children = dir.list(filter);
			for (int i=0; i <= children.length-1; i++) 
			{
				String filename = children[i];
				name = filename;
			}
			try
			{
			if(name.equalsIgnoreCase(EXEC))
				{
					console.appendText(path+"\n");
					loginView logV = new loginView(path);
				}
			}
			catch(NullPointerException e) 
			{
				console.appendText("Keine Datei mit dem Namen: "+EXEC+" gefunden!\n");
				Datei();
			}
		} 
	}
	
	public String getGym()
	{
		String aGym;
		if(gym.isSelected() == true)
			aGym = "-dg";
		else
			aGym = "";
		return aGym;		
	}
	public String getLured()
	{
		String aLured;
		if(lured.isSelected() ==true)
			aLured = "-ol";
		else
			aLured = "";
		return aLured;
	}	
}
	