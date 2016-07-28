/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkmn;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;

/**
 *
 * @author Bambi^
 */
public class Buttons 
{
	private TextField auth;
	private TextField user;
	private TextField location;
	private TextField radius;
	private TextField autoRefresh;
	private PasswordField pass;
	private WebEngine webEngine;
	private CheckBox gym;
	private CheckBox lured;
	
	public Buttons()
	{
	}
	public Buttons(TextField auth, TextField user, PasswordField pass, TextField location, TextField radius,TextField autoRefresh, WebEngine webEngine, CheckBox gym, CheckBox lured)
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
	}
	public Buttons(WebEngine webEngine)
	{
		this.webEngine = webEngine;
	}
	public void start()
	{
		Pkmn pkmn = new Pkmn(auth.getText(),user.getText(),pass.getText(),location.getText(),radius.getText(), autoRefresh.getText(),getGym(),getLured());	
		try {
			pkmn.run();
		} catch (Exception ex) {
			Logger.getLogger(Pkmn_Gui.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	public void refresh()
	{
		webEngine.load("http://127.0.0.1:5000");
	}
	public void stopp() throws IOException
	{
		Runtime rt = Runtime.getRuntime();
		rt.exec("taskkill /F /IM cmd.exe");
		System.exit(0);
	}
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
	